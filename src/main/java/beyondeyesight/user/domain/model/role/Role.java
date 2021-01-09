package beyondeyesight.user.domain.model.role;

import beyondeyesight.user.domain.model.BaseEntity;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role extends BaseEntity {
    private static final String DEFAULT_NAME = "Outsider";

    private String name;

    @Embedded
    @NonNull
    private Privileges privileges;

    private Role(String name, Privileges privileges) {
        super();
        this.name = name;
        this.privileges = privileges;
    }

    // todo: default 접근
    public static Role outsider() {
        return new Role(DEFAULT_NAME, Privileges.empty());
    }

    public static Role withoutPrivilege(String name) {
        return new Role(name, Privileges.empty());
    }

    public void add(RolePrivilege privilege) {
        this.privileges = this.privileges.add(privilege);
    }

    public int countPrivileges() {
        return this.privileges.count();
    }

    Privileges getPrivileges() {
        return this.privileges;
    }

    @Override
    public String toString() {
        return "Role{" +
            "name='" + name + '\'' +
            ", privileges=" + privileges +
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
