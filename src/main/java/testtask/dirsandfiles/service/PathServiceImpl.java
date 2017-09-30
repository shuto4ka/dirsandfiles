package testtask.dirsandfiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testtask.dirsandfiles.domain.PathEntity;
import testtask.dirsandfiles.repository.PathRepository;

import java.util.List;

@Service
public class PathServiceImpl implements PathService {
    private PathRepository repository;

    @Autowired
    public PathServiceImpl(PathRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addAll(List<PathEntity> paths) {
        repository.saveAll(paths);
    }

    @Override
    public List<PathEntity> getAllSorted(int snapshotId) {
        List<PathEntity> list = repository.getAll(snapshotId);
        //TODO sort
        return list;
    }
}
