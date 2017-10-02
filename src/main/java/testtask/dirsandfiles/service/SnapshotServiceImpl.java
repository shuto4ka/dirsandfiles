package testtask.dirsandfiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import testtask.dirsandfiles.domain.Path;
import testtask.dirsandfiles.domain.Snapshot;
import testtask.dirsandfiles.repository.SnapshotRepository;
import testtask.dirsandfiles.util.PathUtil;
import testtask.dirsandfiles.util.exception.SnapshotCreationFailed;

import java.nio.file.FileSystem;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;

@Service
public class SnapshotServiceImpl implements SnapshotService {
    private SnapshotRepository repository;
    private PathService pathService;

    private Clock clock;
    private ExecutorService executorService;
    private FileSystem fileSystem;

    @Autowired
    public SnapshotServiceImpl(SnapshotRepository repository, PathService pathService, Clock clock,
                               ExecutorService executorService, FileSystem fileSystem) {
        this.repository = repository;
        this.pathService = pathService;
        this.clock = clock;
        this.executorService = executorService;
        this.fileSystem = fileSystem;
    }

    @Override
    public Snapshot getByDateTimeAndDir(LocalDateTime dateTime, String dir) {
        return repository.getByDateTimeAndDir(dateTime, dir);
    }

    @Override
    public List<Snapshot> getAll() {
        return repository.getAll();
    }

    @Override
    public Snapshot add(Snapshot snapshot) {
        Assert.isTrue(snapshot.isNew(), "Snapshot id must be null");
        snapshot.setDateTime(LocalDateTime.now(clock).withSecond(0).withNano(0));
        Future<List<Path>> futurePaths = executorService.submit(
                () -> isAlreadyExists(snapshot) ? null : PathUtil.getDirFirstLevelPaths(snapshot.getDir(), fileSystem));
        List<Path> paths = null;
        try {
            paths = futurePaths.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        if (paths == null) {
            throw new SnapshotCreationFailed("Creation of snapshot was failed");
        }
        setSnapshotAttributes(snapshot, paths);
        return addWithPaths(snapshot, paths);
    }

    @Transactional
    public Snapshot addWithPaths(Snapshot snapshot, List<Path> paths) {
        snapshot = repository.save(snapshot);
        if (snapshot.getId() == null) {
            throw new SnapshotCreationFailed("Snapshot was not got id");
        }
        Integer snapshotId = snapshot.getId();
        paths.forEach(p -> p.setSnapshotId(snapshotId));
        pathService.addAll(paths);
        return snapshot;
    }

    private void setSnapshotAttributes(Snapshot snapshot, List<Path> paths) {
        paths.forEach(p -> {
            if (p.getSize() != null) {
                snapshot.setTotalSize(snapshot.getTotalSize() + p.getSize());
                snapshot.setFilesCount(snapshot.getFilesCount() + 1);
            } else {
                snapshot.setDirsCount(snapshot.getDirsCount() + 1);
            }
        });
    }

    private boolean isAlreadyExists(Snapshot snapshot) {
        return getByDateTimeAndDir(snapshot.getDateTime(), snapshot.getDir()) != null;
    }
}
