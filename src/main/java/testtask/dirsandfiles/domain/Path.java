package testtask.dirsandfiles.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
public class Path extends BaseEntity {
    @Getter @Setter private String name;
    @Getter @Setter private Long size;
    @Getter @Setter private Integer snapshotId;

    public Path() {
    }

    @Builder
    public Path(Integer id, String name, Long size, Integer snapshotId) {
        super(id);
        this.name = name;
        this.size = size;
        this.snapshotId = snapshotId;
    }
}
