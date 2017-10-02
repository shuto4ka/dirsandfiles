package testtask.dirsandfiles.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Snapshot extends BaseEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime dateTime;

    private String dir;

    private int dirsCount;

    private int filesCount;

    private long totalSize;

    public Snapshot() {
    }

//    public Snapshot(Integer id) {
//        super(id);
//    }

    public Snapshot(String dir) {
        this.dir = dir;
    }
//
//    public Snapshot(LocalDateTime dateTime, String dir, int dirsCount, int filesCount, long totalSize) {
//        this(null, dateTime, dir, dirsCount, filesCount, totalSize);
//    }

    public Snapshot(Integer id, LocalDateTime dateTime, String dir, int dirsCount, int filesCount, long totalSize) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Snapshot snapshot = (Snapshot) o;

        if (dirsCount != snapshot.dirsCount) return false;
        if (filesCount != snapshot.filesCount) return false;
        if (totalSize != snapshot.totalSize) return false;
        if (!dateTime.equals(snapshot.dateTime)) return false;
        return dir.equals(snapshot.dir);
    }

    @Override
    public int hashCode() {
        int result = dateTime.hashCode();
        result = 31 * result + dir.hashCode();
        result = 31 * result + dirsCount;
        result = 31 * result + filesCount;
        result = 31 * result + (int) (totalSize ^ (totalSize >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Snapshot{" +
                "dateTime=" + dateTime +
                ", dir='" + dir + '\'' +
                ", dirsCount=" + dirsCount +
                ", filesCount=" + filesCount +
                ", totalSize=" + totalSize +
                '}';
    }
}
