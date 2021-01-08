package beyondeyesight.user.domain.model.role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// todo: 접근제어자
public class Privileges {

    @NonNull
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RolePrivilege> privileges;

    Privileges(List<RolePrivilege> privileges) {
        this.privileges = privileges;
    }

    static Privileges empty() {
        return new Privileges(Collections.emptyList());
    }

    static Privileges of(RolePrivilege privilege) {
        return new Privileges(Collections.singletonList(privilege));
    }

    List<Privilege> get() {
        List<Privilege> privileges = new ArrayList<>();
        for (RolePrivilege privilege : this.privileges) {
            privileges.add(privilege.getPrivilege());
        }
        return privileges;
    }

    public Privileges add(RolePrivilege privilege) {
        List<RolePrivilege> privileges = new ArrayList<>(this.privileges);
        privileges.add(privilege);
        return new Privileges(privileges);
    }

    public int count() {
        return this.privileges.size();
    }

    public Privileges merge(Privileges privileges) {
        List<RolePrivilege> merged = new ArrayList<>(this.privileges);
        merged.addAll(privileges.privileges);
        return new Privileges(merged);
    }
}
