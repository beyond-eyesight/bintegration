package beyondeyesight.user.domain.model.role;

import beyondeyesight.user.domain.model.BaseEntity;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Privilege extends BaseEntity implements GrantedAuthority {
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }

    public static Privilege of(String name) {
        return new Privilege(name);
    }
}
