package beyondeyesight.user.domain.model.user;

import beyondeyesight.user.domain.model.BaseEntity;
import beyondeyesight.user.domain.model.user.role.RolesOfUser;
import java.util.Collection;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "DEPREACTE_USER")
public class DeprecateUser extends BaseEntity implements UserDetails, Authentication {

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    @Embedded
    @NonNull
    private RolesOfUser roles;

    private DeprecateUser(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = RolesOfUser.empty();
    }

    private DeprecateUser(UUID id, String email, String name, String password, RolesOfUser roles) {
        super(id);
        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }


    public static DeprecateUser withoutRole(String email, String name, String password) {
        return new DeprecateUser(email, name, password, RolesOfUser.empty());
    }

    public static DeprecateUser withoutRole(UUID id, String email, String name, String password) {
        return new DeprecateUser(id, email, name, password, RolesOfUser.empty());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.toPrivileges();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return this;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getUsername() {
        return getId();
    }

    public void addRoles(RolesOfUser roles) {
        this.roles = this.roles.merge(roles);
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
