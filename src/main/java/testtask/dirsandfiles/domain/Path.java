package testtask.dirsandfiles.domain;

public class Path extends BaseEntity {
    private String name;

    private Long size;

    private Integer snapshotId;

    public Path() {
    }

    public Path(String name, Long size, Integer snapshotId) {
        this(null, name, size, snapshotId);
    }

    public Path(Integer id, String name, Long size, Integer snapshotId) {
        super(id);
        this.name = name;
        this.size = size;
        this.snapshotId = snapshotId;
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

    public Integer getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(Integer snapshotId) {
        this.snapshotId = snapshotId;
    }

    @Override
    public String toString() {
        return "Path{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", snapshotId=" + snapshotId +
                '}';
    }
}
