package testtask.dirsandfiles.repository;

import org.springframework.stereotype.Repository;
import testtask.dirsandfiles.domain.SnapshotEntity;

import java.util.List;

public interface SnapshotRepository {
    List<SnapshotEntity> getAll();

    SnapshotEntity save(SnapshotEntity entity);
}
