package testtask.dirsandfiles.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import testtask.dirsandfiles.Application;
import testtask.dirsandfiles.TestConfig;
import testtask.dirsandfiles.TestData;
import testtask.dirsandfiles.domain.Path;
import testtask.dirsandfiles.domain.Snapshot;
import testtask.dirsandfiles.service.SnapshotService;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {Application.class, TestConfig.class})
@Sql(scripts = "classpath:db/populate_db.sql", config = @SqlConfig(encoding = "UTF-8"))
public class SnapshotAjaxControllerTest extends AbstractControllerTest {
    private static final String URL_TEMPLATE = "/ajax/snapshots";

    private JacksonTester<Snapshot> json;
    private JacksonTester<Object[]> jsonArr;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SnapshotService service;

    @Before
    public void setupJackson() {
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void testGetAll() throws Exception {
        Snapshot[] expected = {TestData.SNAPSHOT_1, TestData.SNAPSHOT_2};

        mvc.perform(get(URL_TEMPLATE))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonArr.write(expected).getJson()));
    }

    @Test
    public void testGetPaths() throws Exception {
        Object[] expected = TestData.PATHS.toArray();

        mvc.perform(get(URL_TEMPLATE + "/" + TestData.SNAPSHOT_1.getId() + "/paths"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonArr.write(expected).getJson()));
    }

    @Test
    public void testCreate() throws Exception {
        Snapshot expected = TestData.getCreatedSnapshot();
        mvc.perform(post(URL_TEMPLATE)
                .param("dir", TestData.TEST_DIR))
                .andExpect(status().isCreated())
                .andExpect(content().json(json.write(expected).getJson()));
    }

    @Test
    public void testCreateDuplicate() throws Exception {
        mvc.perform(post(URL_TEMPLATE)
                .param("dir", TestData.SNAPSHOT_2.getDir()))
                .andExpect(status().isConflict());
    }

    @Test
    public void testCreateNotExists() throws Exception {
        mvc.perform(post(URL_TEMPLATE)
                .param("dir", TestData.TEST_DIR_NOT_EXISTS))
                .andExpect(status().isConflict());
    }

}