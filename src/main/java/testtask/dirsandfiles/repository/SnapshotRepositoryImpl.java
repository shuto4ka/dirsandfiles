package testtask.dirsandfiles.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import testtask.dirsandfiles.domain.Snapshot;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class SnapshotRepositoryImpl implements SnapshotRepository {
    private static final RowMapper<Snapshot> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Snapshot.class);
    private static final Logger log = LoggerFactory.getLogger(SnapshotRepositoryImpl.class);

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
    public Snapshot save(Snapshot entity) {
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
                log.debug("Snapshot added: {}", entity);
        } else {
            throw new IllegalArgumentException("Update is not supported");
        }
        return entity;
    }

    @Override
    public List<Snapshot> getAll() {
        return jdbcTemplate.query("SELECT * FROM snapshots", ROW_MAPPER);
    }

    @Override
    public Snapshot getByDateTimeAndDir(LocalDateTime dateTime, String dir) {
        List<Snapshot> snapshot = jdbcTemplate.query(
                "SELECT * FROM snapshots WHERE date_time=? AND dir=?", ROW_MAPPER, dateTime, dir);
        return DataAccessUtils.singleResult(snapshot);
    }
}