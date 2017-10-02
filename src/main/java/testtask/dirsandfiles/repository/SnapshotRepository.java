package testtask.dirsandfiles.repository;

import testtask.dirsandfiles.domain.Snapshot;

import java.time.LocalDateTime;
import java.util.List;

public interface SnapshotRepository {
    List<Snapshot> getAll();

    Snapshot getByDateTimeAndDir(LocalDateTime dateTime, String dir);

    Snapshot save(Snapshot entity);
}