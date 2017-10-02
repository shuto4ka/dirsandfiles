package testtask.dirsandfiles.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import testtask.dirsandfiles.Application;
import testtask.dirsandfiles.TestConfig;
import testtask.dirsandfiles.TestData;
import testtask.dirsandfiles.domain.Snapshot;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, TestConfig.class})
@Sql(scripts = "classpath:db/populate_db.sql", config = @SqlConfig(encoding = "UTF-8"))
public class SnapshotServiceImplTest {
    @Autowired
    private SnapshotService service;

    @Test
    public void testGetByDateTimeAndDir() throws Exception {
        Assert.assertEquals(TestData.SNAPSHOT_1,
                service.getByDateTimeAndDir(TestData.SNAPSHOT_1.getDateTime(), TestData.SNAPSHOT_1.getDir()));
    }

    @Test
    public void testGetAll() throws Exception {
        Assert.assertEquals(Arrays.asList(TestData.SNAPSHOT_1, TestData.SNAPSHOT_2), service.getAll());
    }

    @Test
    public void testAdd() throws Exception {
        Assert.assertEquals(TestData.getCreatedSnapshot(), service.add(new Snapshot(TestData.TEST_DIR)));
    }

    @Test(expected = RuntimeException.class)
    public void testAddDuplicate() throws Exception {
        service.add(new Snapshot(TestData.SNAPSHOT_2.getDir()));
    }
}