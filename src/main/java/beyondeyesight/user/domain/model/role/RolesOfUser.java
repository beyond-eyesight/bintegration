package beyondeyesight.user.domain.model.role;

import beyondeyesight.user.domain.model.user.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
public class RolesOfUser {

    @NonNull
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles;

    public static RolesOfUser empty() {
        return new RolesOfUser(Collections.emptyList());
    }

    public static RolesOfUser of(UserRole userRole) {
        return new RolesOfUser(Collections.singletonList(userRole));
    }

    public RolesOfUser merge(RolesOfUser roles) {
        if (relatedToDifferentUser(roles)) {
            throw new IllegalArgumentException("다수의 사용자를 가질 수 업습니다.");
        }
        List<UserRole> merged = new ArrayList<>(this.roles);
        merged.addAll(roles.roles);
        return new RolesOfUser(merged);
    }

    private Optional<User> findUser() {
        return this.roles.stream().findAny().map(UserRole::getUser);
    }

    public List<Privilege> toPrivileges() {
        PrivilegesOfRole privileges = this.roles.stream()
            .map(UserRole::getRole)
            .map(Role::getPrivileges)
            .reduce(PrivilegesOfRole::merge)
            .orElse(PrivilegesOfRole.empty());
        return privileges.get();
    }

    public RolesOfUser add(UserRole role) {
        List<UserRole> roles = new ArrayList<>(this.roles);
        roles.add(role);
        return new RolesOfUser(roles);
    }


    int count() {
        return roles.size();
    }

    private boolean relatedToDifferentUser(RolesOfUser roles) {
        if (isEmpty() || roles.isEmpty()) {
            return false;
        }
        User user = this.findUser().orElseThrow(IllegalStateException::new);
        User comparisonTarget = roles.findUser().orElseThrow(IllegalStateException::new);
        return !user.equals(comparisonTarget);
    }

    private boolean isEmpty() {
        return this.roles.isEmpty();
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
        RolesOfUser roles1 = (RolesOfUser) o;
        return Objects.equals(roles, roles1.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roles);
    }
}
