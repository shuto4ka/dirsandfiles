package testtask.dirsandfiles.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import testtask.dirsandfiles.domain.Path;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class PathRepositoryImpl implements PathRepository {
    private static final RowMapper<Path> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Path.class);
    private static final int BATCH_SIZE = 20;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PathRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void saveAll(List<Path> paths) {
        jdbcTemplate.batchUpdate("INSERT INTO paths (name, size, snapshot_id) VALUES (?, ?, ?)", paths, BATCH_SIZE,
                (ps, path) -> {
                    ps.setString(1, path.getName());
                    ps.setObject(2, path.getSize());
                    ps.setInt(3, path.getSnapshotId());
                });
    }

    @Override
    public List<Path> getAll(int snapshotId) {
        return jdbcTemplate.query("SELECT * FROM paths WHERE snapshot_id=?", ROW_MAPPER, snapshotId);
    }
}
