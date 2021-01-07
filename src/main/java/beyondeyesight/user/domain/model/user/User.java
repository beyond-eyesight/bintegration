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
@AllArgsConstructor
public class User extends BaseEntity {
    private String email;
    private String name;
    private String password;

    @Embedded
    @NonNull
    private Roles roles;

    // todo: 캡슐화
    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = Roles.empty();
    }

    // entitiy에 있는 애들 다 보이드로 바꾸기
    public User addRoles(Roles roles) {
        this.roles.merge(roles);
        return new User(this.email, this.name, this.password, roles);
    }
}
