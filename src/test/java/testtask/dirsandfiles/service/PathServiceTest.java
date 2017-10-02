package testtask.dirsandfiles.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import testtask.dirsandfiles.TestData;
import testtask.dirsandfiles.domain.Path;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:db/populate_db.sql", config = @SqlConfig(encoding = "UTF-8"))
public class PathServiceTest {
    @Autowired
    private PathService service;

    @Test
    public void testAddAll() throws Exception {
        Path newPath1 = new Path("path1", 100L, TestData.START_SEQ + 1);
        Path newPath2 = new Path("path2", 200L, TestData.START_SEQ + 1);
        List<Path> testData = Arrays.asList(newPath1, newPath2);
        service.addAll(testData);
        Assert.assertEquals(testData, service.getAllSorted(TestData.START_SEQ + 1));
    }

    @Test
    public void testGetAllSorted() throws Exception {
        Assert.assertEquals(TestData.PATHS, service.getAllSorted(TestData.START_SEQ));
    }
}