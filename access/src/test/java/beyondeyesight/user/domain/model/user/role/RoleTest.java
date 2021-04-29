package beyondeyesight.user.domain.model.user.role;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleTest {
    @Test
    public void add() {
        Role role = Role.withoutPrivilege("ROLE_NAME");
        Privilege readOnly = Privilege.of("READ_ONLY");
        role.add(RolePrivilege.of(role, readOnly));
        assertThat(role.countPrivileges()).isEqualTo(1);
    }
}
