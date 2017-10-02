package testtask.dirsandfiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testtask.dirsandfiles.domain.Path;
import testtask.dirsandfiles.repository.PathRepository;
import testtask.dirsandfiles.util.PathUtil;

import java.util.List;

@Service
public class PathServiceImpl implements PathService {
    private PathRepository repository;

    @Autowired
    public PathServiceImpl(PathRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addAll(List<Path> paths) {
        repository.saveAll(paths);
    }

    @Override
    public List<Path> getAllSorted(int snapshotId) {
        List<Path> paths = repository.getAll(snapshotId);
        paths.sort(PathUtil::comparePaths);
        return paths;
    }
}