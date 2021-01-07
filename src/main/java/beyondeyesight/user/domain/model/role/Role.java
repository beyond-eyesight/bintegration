package beyondeyesight.user.domain.model.role;

import beyondeyesight.user.domain.model.BaseEntity;
import java.util.Objects;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role extends BaseEntity {
    private static final String DEFAULT_NAME = "Outsider";

    private String name;

    private Role(String name) {
        this.name = name;
    }

    // todo: default 접근
    public static Role initialize() {
        return new Role(DEFAULT_NAME);
    }

    @Override
    public String toString() {
        return "Role{" +
            "name='" + name + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
