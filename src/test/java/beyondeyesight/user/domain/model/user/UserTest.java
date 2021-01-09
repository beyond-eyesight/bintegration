package beyondeyesight.user.domain.model.user;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.user.domain.model.role.Privilege;
import beyondeyesight.user.domain.model.role.Role;
import beyondeyesight.user.domain.model.role.RolePrivilege;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void toPrincipal() {
        Privilege readOnly = Privilege.of("READ_ONLY");
        Role role = Role.withoutPrivilege("Role");
        role.add(RolePrivilege.of(role, readOnly));
        User user = User.withoutRole(UUID.randomUUID(), "email", "name", "password");
        // when
        UserPrincipal principal = user.toPrincipal();
        // then
        assertThat(principal).isNotNull();
    }
}
