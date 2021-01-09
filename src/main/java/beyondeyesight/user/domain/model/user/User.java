package beyondeyesight.user.domain.model.user;

import beyondeyesight.user.domain.model.BaseEntity;
import beyondeyesight.user.domain.model.role.Roles;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    private String email;
    private String name;
    private String password;

    @Embedded
    @NonNull
    private Roles roles;

    private User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = Roles.empty();
    }

    public static User withoutRole(String email, String name, String password) {
        return new User(email, name, password);
    }

    public void addRoles(Roles roles) {
        this.roles = this.roles.merge(roles);
    }

    public UserPrincipal toPrincipal() {
        return new UserPrincipal(getId().toString(), password, roles.toPrivileges());
    }

    @Override
    public String toString() {
        return "User{" +
            "email='" + email + '\'' +
            ", name='" + name + '\'' +
            ", password='" + password + '\'' +
            ", roles=" + roles +
            '}';
    }
}
