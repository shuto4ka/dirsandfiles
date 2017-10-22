package testtask.dirsandfiles.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {
    private Integer id;

    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }
}
