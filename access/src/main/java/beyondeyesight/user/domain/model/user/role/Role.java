package beyondeyesight.user.domain.model.user.role;

import beyondeyesight.user.domain.model.BaseEntity;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role extends BaseEntity {

    private static final String DEFAULT_NAME = "Outsider";

    @Column(nullable = false, unique = true)
    private String name;

    @Embedded
    @NonNull
    private PrivilegesOfRole privileges;

    private Role(UUID id, String name, PrivilegesOfRole privileges) {
        super(id);
        this.name = name;
        this.privileges = privileges;
    }

    private Role(String name, PrivilegesOfRole privileges) {
        super();
        this.name = name;
        this.privileges = privileges;
    }

    public static Role outsider() {
        return new Role(DEFAULT_NAME, PrivilegesOfRole.empty());
    }

    // todo: factory 클래스를 만들 필요가 있을듯? Privileges들 넣어주는...
    public static Role withoutPrivilege(String name) {
        return new Role(name, PrivilegesOfRole.empty());
    }

    static Role withoutPrivilege(UUID id, String name) {
        return new Role(id, name, PrivilegesOfRole.empty());
    }

    public void add(RolePrivilege privilege) {
        this.privileges = this.privileges.add(privilege);
    }

    public int countPrivileges() {
        return this.privileges.count();
    }

    PrivilegesOfRole getPrivileges() {
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
