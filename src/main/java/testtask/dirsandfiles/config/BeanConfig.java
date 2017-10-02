package testtask.dirsandfiles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
