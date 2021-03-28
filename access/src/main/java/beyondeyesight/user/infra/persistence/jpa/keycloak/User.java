package beyondeyesight.user.infra.persistence.jpa.keycloak;

import beyondeyesight.user.domain.model.BaseEntity;
import beyondeyesight.user.domain.model.user.UserEntity;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.keycloak.models.ClientModel;
import org.keycloak.models.GroupModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.UserModel;

@Entity
@Getter
@Table(name = "USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseEntity implements UserModel, UserEntity {
    @Column(nullable = false, unique = true)
    private String username;
    @Column
    private Boolean enabled;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    // todo: 일단 다른 레퍼런스 보고 넣었는데, 굳이 이 필드는 필요 없을듯. 아예 유저의 상태를 이메일 베리파이드, 뭐, 뭐 등 여러가지로 둘듯.

     private User(UUID id, String username, Boolean enabled, String password, String firstName, String lastName) {
        super(id);
        this.username = username;
        this.enabled = enabled;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static User of(UUID id, String username, Boolean enabled, String password, String firstName, String lastName) {
         return new User(id, username, enabled, password, firstName, lastName);
    }

    @Override
    public String getId() {
        return this.id.toString();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Long getCreatedTimestamp() {
        //todo: check
        return null;
    }

    @Override
    public void setCreatedTimestamp(Long timestamp) {
        // todo: impl

    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void setSingleAttribute(String name, String value) {
        // todo: impl
    }

    @Override
    public void setAttribute(String name, List<String> values) {
        // todo: impl
    }

    @Override
    public void removeAttribute(String name) {
        // todo: impl
    }

    @Override
    public String getFirstAttribute(String name) {
        // todo: impl
        return null;
    }

    @Override
    public List<String> getAttribute(String name) {
        // todo: impl
        return null;
    }

    @Override
    public Map<String, List<String>> getAttributes() {
        // todo: impl
        return null;
    }

    @Override
    public Set<String> getRequiredActions() {
        // todo: impl
        return null;
    }

    @Override
    public void addRequiredAction(String action) {
        // todo: impl
    }

    @Override
    public void removeRequiredAction(String action) {
        // todo: impl
    }

    @Override
    public String getFirstName() {
        // todo: impl
        return null;
    }

    @Override
    public void setFirstName(String firstName) {
        // todo: impl
    }

    @Override
    public String getLastName() {
        // todo: impl
        return null;
    }

    @Override
    public void setLastName(String lastName) {
        // todo: impl
    }

    @Override
    public String getEmail() {
        // todo: impl
        return null;
    }

    @Override
    public void setEmail(String email) {
        // todo: impl
    }

    @Override
    public boolean isEmailVerified() {
        return false;
    }

    @Override
    public void setEmailVerified(boolean verified) {
        // todo: impl
    }

    @Override
    public Set<GroupModel> getGroups() {
        // todo: impl
        return null;
    }

    @Override
    public void joinGroup(GroupModel group) {
        // todo: impl
    }

    @Override
    public void leaveGroup(GroupModel group) {
        // todo: impl
    }

    @Override
    public boolean isMemberOf(GroupModel group) {
        // todo: impl
        return false;
    }

    @Override
    public String getFederationLink() {
        // todo: impl
        return null;
    }

    @Override
    public void setFederationLink(String link) {
        // todo: impl
    }

    @Override
    public String getServiceAccountClientLink() {
        // todo: impl
        return null;
    }

    @Override
    public void setServiceAccountClientLink(String clientInternalId) {
        // todo: impl
    }

    @Override
    public Set<RoleModel> getRealmRoleMappings() {
        // todo: impl
        return null;
    }

    @Override
    public Set<RoleModel> getClientRoleMappings(ClientModel app) {
        // todo: impl
        return null;
    }

    @Override
    public boolean hasRole(RoleModel role) {
        // todo: impl
        return false;
    }

    @Override
    public void grantRole(RoleModel role) {
        // todo: impl
    }

    @Override
    public Set<RoleModel> getRoleMappings() {
        // todo: impl
        return null;
    }

    @Override
    public void deleteRoleMapping(RoleModel role) {
        // todo: impl
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            '}';
    }
}
