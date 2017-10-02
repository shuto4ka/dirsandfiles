package testtask.dirsandfiles;

import com.google.common.collect.ImmutableList;
import com.google.common.jimfs.Jimfs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Configuration
public class TestConfig {
    @Bean
    @Primary
    public Clock clock() {
        return Clock.fixed(Instant.parse("2017-09-30T13:00:00.00Z"),
                ZoneId.ofOffset("GMT", ZoneOffset.ofHours(0)));
    }

    @Bean
    @Primary
    public FileSystem getFileSystem() throws IOException {
        FileSystem fs = Jimfs.newFileSystem(com.google.common.jimfs.Configuration.unix());
        Path testDir = fs.getPath(TestData.TEST_DIR);
        Files.createDirectory(testDir);

        Path testFile = testDir.resolve(TestData.TEST_FILE);
        Files.write(testFile, ImmutableList.of(TestData.TEST_FILE_TEXT), StandardCharsets.UTF_8);
        return fs;
    }
}
