package testtask.dirsandfiles.repository;

import testtask.dirsandfiles.domain.PathEntity;

import java.util.List;

public interface PathRepository {
    void saveAll(List<PathEntity> paths);

    List<PathEntity> getAll(int snapshotId);
}
