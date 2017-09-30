package testtask.dirsandfiles.service;

import testtask.dirsandfiles.domain.PathEntity;

import java.util.List;

public interface PathService {
    void addAll(List<PathEntity> paths);

    List<PathEntity> getAllSorted(int snapshotId);
}
