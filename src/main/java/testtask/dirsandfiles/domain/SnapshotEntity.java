package testtask.dirsandfiles.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "snapshots", uniqueConstraints =
    @UniqueConstraint(columnNames = {"date_time", "dir"}, name = "snapshots_unique_date_time_dir_idx"))
public class SnapshotEntity extends BaseEntity {
    @NotNull
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @NotBlank
    @Column(name = "dir", nullable = false)
    private String dir;

    @Column(name = "dirs_count")
    private int dirsCount;

    @Column(name = "files_count")
    private int filesCount;

    @Column(name = "total_size")
    private long totalSize;

    public SnapshotEntity() {
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
}
