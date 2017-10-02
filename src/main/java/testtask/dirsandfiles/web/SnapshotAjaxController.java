package testtask.dirsandfiles.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import testtask.dirsandfiles.domain.Path;
import testtask.dirsandfiles.domain.Snapshot;
import testtask.dirsandfiles.service.PathService;
import testtask.dirsandfiles.service.SnapshotService;

import java.util.List;

@RestController
@RequestMapping("/ajax/snapshots")
public class SnapshotAjaxController {
    private SnapshotService snapshotService;
    private PathService pathService;

    @Autowired
    public SnapshotAjaxController(SnapshotService snapshotService, PathService pathService) {
        this.snapshotService = snapshotService;
        this.pathService = pathService;
    }

    @GetMapping
    public List<Snapshot> getAll() {
        return snapshotService.getAll();
    }

    @GetMapping("/{id}/paths")
    public List<Path> getPaths(@PathVariable("id") int snapshotId) {
        return pathService.getAllSorted(snapshotId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Snapshot create(Snapshot snapshot) {
        return snapshotService.add(snapshot);
    }
}