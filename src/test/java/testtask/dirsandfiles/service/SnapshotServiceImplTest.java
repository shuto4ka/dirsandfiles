package testtask.dirsandfiles.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import testtask.dirsandfiles.Application;
import testtask.dirsandfiles.TestConfig;
import testtask.dirsandfiles.domain.Snapshot;
import testtask.dirsandfiles.util.exception.SnapshotCreationFailed;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static testtask.dirsandfiles.TestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, TestConfig.class})
@Sql(scripts = "classpath:db/populate_db.sql", config = @SqlConfig(encoding = "UTF-8"))
public class SnapshotServiceImplTest {
    @Autowired
    private SnapshotService service;

    @Test
    public void testGetByDateTimeAndDir() throws Exception {
        assertThat(SNAPSHOT_1)
                .isEqualToComparingFieldByField(service.getByDateTimeAndDir(SNAPSHOT_1.getDateTime(), SNAPSHOT_1.getDir()));
    }

    @Test
    public void testGetAll() throws Exception {
        assertThat(Arrays.asList(SNAPSHOT_1, SNAPSHOT_2))
                .usingFieldByFieldElementComparator()
                .containsExactlyElementsOf(service.getAll());
    }

    @Test
    public void testAdd() throws Exception {
        Snapshot added = service.add(new Snapshot(TEST_DIR));
        assertThat(added.getId()).isNotNull();

        List<Snapshot> actual = service.getAll();
        assertThat(Arrays.asList(getCreatedSnapshot(), SNAPSHOT_1, SNAPSHOT_2))
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(actual.toArray(new Snapshot[actual.size()]));
    }

    @Test(expected = SnapshotCreationFailed.class)
    public void testAddDuplicate() throws Exception {
        service.add(new Snapshot(SNAPSHOT_2.getDir()));
    }
}