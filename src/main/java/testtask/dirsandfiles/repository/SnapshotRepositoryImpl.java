package testtask.dirsandfiles.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import testtask.dirsandfiles.domain.SnapshotEntity;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class SnapshotRepositoryImpl implements SnapshotRepository {
    private static final RowMapper<SnapshotEntity> ROW_MAPPER = BeanPropertyRowMapper.newInstance(SnapshotEntity.class);

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertMeal;

    @Autowired
    public SnapshotRepositoryImpl(@Qualifier("dataSource") DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.insertMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("snapshots")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public SnapshotEntity save(SnapshotEntity entity) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", entity.getId())
                .addValue("date_time", entity.getDateTime())
                .addValue("dir", entity.getDir())
                .addValue("dirs_count", entity.getDirsCount())
                .addValue("files_count", entity.getFilesCount())
                .addValue("total_size", entity.getTotalSize());
        if (entity.isNew()) {
            Number newId = insertMeal.executeAndReturnKey(map);
            entity.setId(newId.intValue());
        } else {
            return null;
        }
        return entity;
    }

    @Override
    public List<SnapshotEntity> getAll() {
        return jdbcTemplate.query("SELECT * FROM snapshots", ROW_MAPPER);
    }
}