package testtask.dirsandfiles.domain;

import java.time.LocalDateTime;

public class SnapshotEntity extends BaseEntity {
    private LocalDateTime dateTime;

    private String dir;

    private int dirsCount;

    private int filesCount;

    private long totalSize;

    public SnapshotEntity() {
    }

    public SnapshotEntity(Integer id) {
        super(id);
    }

    public SnapshotEntity(LocalDateTime dateTime, String dir, int dirsCount, int filesCount, long totalSize) {
        this(null, dateTime, dir, dirsCount, filesCount, totalSize);
    }

    public SnapshotEntity(Integer id, LocalDateTime dateTime, String dir, int dirsCount, int filesCount, long totalSize) {
        super(id);
        this.dateTime = dateTime;
        this.dir = dir;
        this.dirsCount = dirsCount;
        this.filesCount = filesCount;
        this.totalSize = totalSize;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public int getDirsCount() {
        return dirsCount;
    }

    public void setDirsCount(int dirsCount) {
        this.dirsCount = dirsCount;
    }

    public int getFilesCount() {
        return filesCount;
    }

    public void setFilesCount(int filesCount) {
        this.filesCount = filesCount;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    @Override
    public String toString() {
        return "SnapshotEntity{" +
                "dateTime=" + dateTime +
                ", dir='" + dir + '\'' +
                ", dirsCount=" + dirsCount +
                ", filesCount=" + filesCount +
                ", totalSize=" + totalSize +
                '}';
    }
}
