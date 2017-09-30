package testtask.dirsandfiles.service;

import testtask.dirsandfiles.domain.SnapshotEntity;

import java.util.List;

public interface SnapshotService {
    List<SnapshotEntity> getAll();

    SnapshotEntity add(String dir);
}
