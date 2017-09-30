package testtask.dirsandfiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testtask.dirsandfiles.domain.SnapshotEntity;
import testtask.dirsandfiles.repository.SnapshotRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SnapshotServiceImpl implements SnapshotService {
    private SnapshotRepository repository;

    @Autowired
    public SnapshotServiceImpl(SnapshotRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SnapshotEntity> getAll() {
        return repository.getAll();
    }

    @Override
    public SnapshotEntity add(String dir) {
        //TODO get entity from filesystem
        return repository.save(new SnapshotEntity(LocalDateTime.now(), dir, 1,1,1));
    }
}
