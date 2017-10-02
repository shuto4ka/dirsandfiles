package testtask.dirsandfiles.service;

import testtask.dirsandfiles.domain.Snapshot;

import java.time.LocalDateTime;
import java.util.List;

public interface SnapshotService {
    List<Snapshot> getAll();

    Snapshot getByDateTimeAndDir(LocalDateTime dateTime, String dir);

    Snapshot add(Snapshot snapshot);
}
