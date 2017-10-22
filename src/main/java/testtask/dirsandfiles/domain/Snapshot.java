package testtask.dirsandfiles.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString(callSuper = true)
public class Snapshot extends BaseEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    @Getter @Setter private LocalDateTime dateTime;

    @Getter @Setter private String dir;
    @Getter @Setter private int dirsCount;
    @Getter @Setter private int filesCount;
    @Getter @Setter private long totalSize;

    public Snapshot() {
    }

    public Snapshot(String dir) {
        this.dir = dir;
    }

    @Builder
    public Snapshot(Integer id, LocalDateTime dateTime, String dir, int dirsCount, int filesCount, long totalSize) {
        super(id);
        this.dateTime = dateTime;
        this.dir = dir;
        this.dirsCount = dirsCount;
        this.filesCount = filesCount;
        this.totalSize = totalSize;
    }
}
