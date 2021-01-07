package beyondeyesight.user.domain.model.role;

import beyondeyesight.user.domain.model.BaseEntity;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Privilege extends BaseEntity {
    private String name;

}
