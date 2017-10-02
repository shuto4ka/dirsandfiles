package testtask.dirsandfiles.repository;

import testtask.dirsandfiles.domain.Path;

import java.util.List;

public interface PathRepository {
    void saveAll(List<Path> paths);

    List<Path> getAll(int snapshotId);
}
