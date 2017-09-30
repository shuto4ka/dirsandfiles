package testtask.dirsandfiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testtask.dirsandfiles.domain.SnapshotEntity;

public interface SnapshotRepository extends JpaRepository<SnapshotEntity, Integer> {
}
