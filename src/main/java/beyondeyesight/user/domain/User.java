package beyondeyesight.user.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {
    private String email;
    private String name;
    private String password;

    @OneToOne
    private Roles roles;
}
