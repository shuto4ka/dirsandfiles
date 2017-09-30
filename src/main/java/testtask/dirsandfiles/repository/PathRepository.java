package testtask.dirsandfiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testtask.dirsandfiles.domain.PathEntity;

public interface PathRepository extends JpaRepository<PathEntity, Integer> {
}
