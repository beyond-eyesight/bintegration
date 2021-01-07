package beyondeyesight.user.domain;

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
@AllArgsConstructor
public class Roles  {

    @NonNull
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles;

    static Roles initialize(User user) {
        return new Roles(Collections.singletonList(new UserRole(user, Role.initialize())));
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
