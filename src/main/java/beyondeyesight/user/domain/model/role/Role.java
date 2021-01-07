package beyondeyesight.user.domain.model.role;

import beyondeyesight.user.domain.model.BaseEntity;
import java.util.List;
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
    public static Role initialize() {
        return new Role(DEFAULT_NAME, Privileges.empty());
    }

    static Role withoutPrivilege(String name) {
        return new Role(name, Privileges.empty());
    }

    void add(Privilege privilege) {
        this.privileges = this.privileges.add(this, privilege);
    }

    // todo: 리턴타입 그냥 Privilges로
    List<Privilege> getPrivileges() {
        return this.privileges.get();
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

    public int countPrivileges() {
        return this.privileges.count();
    }
}
