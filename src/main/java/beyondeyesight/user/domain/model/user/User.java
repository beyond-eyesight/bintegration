package beyondeyesight.user.domain.model.user;

import beyondeyesight.user.domain.model.BaseEntity;
import beyondeyesight.user.domain.model.role.Roles;
import java.util.Collection;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseEntity implements UserDetails {
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
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

    private User(UUID id, String email, String name, String password, Roles roles) {
        super(id);
        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }



    public static User withoutRole(String email, String name, String password) {
        return new User(email, name, password, Roles.empty());
    }

    public static User withoutRole(UUID id, String email, String name, String password) {
        return new User(id, email, name, password, Roles.empty());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.toPrivileges();
    }

    @Override
    public String getUsername() {
        return getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addRoles(Roles roles) {
        this.roles = this.roles.merge(roles);
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
