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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Path path = (Path) o;

        if (!name.equals(path.name)) return false;
        if (size != null ? !size.equals(path.size) : path.size != null) return false;
        return snapshotId != null ? snapshotId.equals(path.snapshotId) : path.snapshotId == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (snapshotId != null ? snapshotId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Path{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", snapshotId=" + snapshotId +
                '}';
    }
}
