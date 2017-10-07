package testtask.dirsandfiles.service;

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

import static org.assertj.core.api.Assertions.assertThat;
import static testtask.dirsandfiles.TestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:db/populate_db.sql", config = @SqlConfig(encoding = "UTF-8"))
public class PathServiceTest {
    @Autowired
    private PathService service;

    @Test
    public void testAddAll() throws Exception {
        Path newPath1 = new Path("path1", 100L, SNAPSHOT_2.getId());
        Path newPath2 = new Path("path2", 200L, SNAPSHOT_2.getId());
        List<Path> testData = Arrays.asList(newPath1, newPath2);
        service.addAll(testData);

        newPath1.setId(START_SEQ + 10);
        newPath2.setId(START_SEQ + 11);
        assertThat(testData)
                .usingFieldByFieldElementComparator()
                .containsExactlyElementsOf(service.getAllSorted(SNAPSHOT_2.getId()));
    }

    @Test
    public void testGetAllSorted() throws Exception {
        assertThat(TestData.PATHS)
                .usingFieldByFieldElementComparator()
                .containsExactlyElementsOf(service.getAllSorted(SNAPSHOT_1.getId()));
    }
}