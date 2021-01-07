package beyondeyesight.user.domain.model.role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// access
@AllArgsConstructor
public class Roles {

    @NonNull
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles;

    public static Roles empty() {
        return new Roles(Collections.emptyList());
    }

    public static Roles of(UserRole userRole) {
        return new Roles(Collections.singletonList(userRole));
    }

    public Roles add(UserRole role) {
        List<UserRole> roles = new ArrayList<>(this.roles);
        roles.add(role);
        return new Roles(roles);
    }

    public Roles merge(Roles roles) {
        // todo: validation logic - user가 같은지
        List<UserRole> merged = new ArrayList<>(this.roles);
        merged.addAll(roles.roles);
        return new Roles(merged);
    }

    // todo: 아예 리턴 타입을 Privileges로 하는게 나을
    public List<Privilege> toPrivileges() {
        return this.roles.stream()
            .map(UserRole::getRole)
            .map(Role::getPrivileges)
            .reduce((List<Privilege> a, List<Privilege> b) -> {
                List<Privilege> merged = new ArrayList<>(a);
                merged.addAll(b);
                return merged;
            }).orElseThrow(IllegalStateException::new);
    }

    public int count() {
        return roles.size();
    }

    @Override
    public String toString() {
        return "Roles{" +
            "roles=" + roles +
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
        Roles roles1 = (Roles) o;
        return Objects.equals(roles, roles1.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roles);
    }
}
