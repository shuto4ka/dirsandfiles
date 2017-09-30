package testtask.dirsandfiles.domain;

public class PathEntity extends BaseEntity {
    private String name;

    private Long size;

    private int snapshotId;

    public PathEntity() {
    }

    public PathEntity(String name, Long size, int snapshotId) {
        this(null, name, size, snapshotId);
    }

    public PathEntity(Integer id, String name, Long size, int snapshotId) {
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

    public int getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(int snapshotId) {
        this.snapshotId = snapshotId;
    }
}
