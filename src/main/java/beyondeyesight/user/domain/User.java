package beyondeyesight.user.domain;

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
    @NonNull
    @Embedded
    // todo: 여기서 선언할 필요가 없다. 이제 파이널이 아니라..
    private Roles roles;

    public User addRoles(Roles roles) {
        this.roles.merge(roles);
        return new User(this.email, this.name, this.password, roles);
    }
}
