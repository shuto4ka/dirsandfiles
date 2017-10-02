package testtask.dirsandfiles.service;

import testtask.dirsandfiles.domain.Path;

import java.util.List;

public interface PathService {
    void addAll(List<Path> paths);

    List<Path> getAllSorted(int snapshotId);
}
