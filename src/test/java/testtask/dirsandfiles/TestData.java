package testtask.dirsandfiles;

import testtask.dirsandfiles.domain.Path;
import testtask.dirsandfiles.domain.Snapshot;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TestData {
    public static final int START_SEQ = 100000;

    public static final Snapshot SNAPSHOT_1 = new Snapshot(START_SEQ, LocalDateTime.of(2017, 9, 30, 10, 0),
            "/opt/tomcat/temp", 12, 251, 134217728L);
    public static final Snapshot SNAPSHOT_2 = new Snapshot(START_SEQ + 1, LocalDateTime.of(2017, 9, 30, 13, 0),
            "/opt/tomcat/temp", 12, 51, 262144L);

    public static final Path PATH_1 = new Path(START_SEQ + 2, "innerTemp", null, START_SEQ);
    public static final Path PATH_2 = new Path(START_SEQ + 3, "X-FILES", null, START_SEQ);
    public static final Path PATH_3 = new Path(START_SEQ + 4, "f.txt", 4383L, START_SEQ);
    public static final Path PATH_4 = new Path(START_SEQ + 5, "F1.txt", 12872L, START_SEQ);
    public static final Path PATH_5 = new Path(START_SEQ + 6, "f4_99.JPG", 1593836L, START_SEQ);
    public static final Path PATH_6 = new Path(START_SEQ + 7, "F4_00127.pdf", 923116L, START_SEQ);
    public static final Path PATH_7 = new Path(START_SEQ + 8, "f0008.doc", 26634L, START_SEQ);
    public static final Path PATH_8 = new Path(START_SEQ + 9, "function.cpp", 3656L, START_SEQ);

    public static final List<Path> PATHS = Arrays.asList(PATH_1, PATH_2, PATH_3, PATH_4, PATH_5, PATH_6, PATH_7, PATH_8);

    public static final String TEST_DIR = "/test";
    public static final String TEST_DIR_NOT_EXISTS = "/123123123";
    public static final String TEST_FILE = "test.txt";
    public static final String TEST_FILE_TEXT = "test file";

    public static Snapshot getCreatedSnapshot() {
        return new Snapshot(START_SEQ + 10, LocalDateTime.of(2017, 9, 30, 13, 0),
                TEST_DIR, 0, 1, 11);
    }
}
