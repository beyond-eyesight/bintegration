package beyondeyesight.user.domain.model.role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class PrivilegesOfRole {

    @NonNull
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RolePrivilege> privileges;

    static PrivilegesOfRole empty() {
        return new PrivilegesOfRole(Collections.emptyList());
    }

    static PrivilegesOfRole of(RolePrivilege rolePrivilege) {
        return new PrivilegesOfRole(Collections.singletonList(rolePrivilege));
    }

    List<Privilege> get() {
        List<Privilege> privileges = new ArrayList<>();
        for (RolePrivilege privilege : this.privileges) {
            privileges.add(privilege.getPrivilege());
        }
        return privileges;
    }

    PrivilegesOfRole add(RolePrivilege privilege) {
        List<RolePrivilege> privileges = new ArrayList<>(this.privileges);
        privileges.add(privilege);
        return new PrivilegesOfRole(privileges);
    }

    int count() {
        return this.privileges.size();
    }

    PrivilegesOfRole merge(PrivilegesOfRole privileges) {
        if (relatedToDifferentRole(privileges)) {
            throw new IllegalArgumentException("다수의 사용자를 가질 수 업습니다.");

        }
        List<RolePrivilege> merged = new ArrayList<>(this.privileges);
        merged.addAll(privileges.privileges);
        return new PrivilegesOfRole(merged);
    }

    private boolean relatedToDifferentRole(PrivilegesOfRole privileges) {
        if (isEmpty() || privileges.isEmpty()) {
            return false;
        }
        Role role = this.findRole().orElseThrow(IllegalStateException::new);
        Role comparisonTarget = privileges.findRole().orElseThrow(IllegalStateException::new);
        return !role.equals(comparisonTarget);
    }

    private boolean isEmpty() {
        return this.privileges.isEmpty();
    }

    private Optional<Role> findRole() {
        return this.privileges.stream().findAny().map(RolePrivilege::getRole);
    }

    @Override
    public String toString() {
        return "Privileges{" +
            "privileges=" + privileges +
            '}';
    }
}
