package testtask.dirsandfiles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.time.Clock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class BeanConfig {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public ExecutorService getExecutorService() {
        int threadsNum = Runtime.getRuntime().availableProcessors();
        return Executors.newFixedThreadPool(threadsNum);
    }

    @Bean
    public FileSystem getFileSystem() {
        return FileSystems.getDefault();
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("db/initDB_hsql.sql")
                .addScript("db/populate_db.sql")
                .build();
    }
}