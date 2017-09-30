package testtask.dirsandfiles.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "paths")
public class PathEntity extends BaseEntity {
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "size")
    private Long size;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "snapshot_id", referencedColumnName = "id", nullable = false)
    private SnapshotEntity snapshot;

    public PathEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public SnapshotEntity getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(SnapshotEntity snapshot) {
        this.snapshot = snapshot;
    }
}
