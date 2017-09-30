package testtask.dirsandfiles.service;

import testtask.dirsandfiles.domain.PathEntity;

import java.util.List;

public interface PathService {
    List<PathEntity> addAll(List<PathEntity> paths);

    List<PathEntity> getAllSorted(int snapshotId);
}
