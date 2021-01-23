package beyondeyesight.user.domain.model.user.role;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RoleTest {
    @Test
    public void add() {
        Role role = Role.withoutPrivilege("ROLE_NAME");
        Privilege readOnly = Privilege.of("READ_ONLY");
        role.add(RolePrivilege.of(role, readOnly));
        assertThat(role.countPrivileges()).isEqualTo(1);
    }
}
