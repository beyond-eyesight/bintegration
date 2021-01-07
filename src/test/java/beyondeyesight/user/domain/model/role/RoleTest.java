package beyondeyesight.user.domain.model.role;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RoleTest {
    @Test
    public void add() {
        Role role = Role.withoutPrivilege("ROLE_NAME");
        Privilege readOnly = new Privilege("READ_ONLY");
        role.add(readOnly);
        assertThat(role.countPrivileges()).isEqualTo(1);
    }
}
