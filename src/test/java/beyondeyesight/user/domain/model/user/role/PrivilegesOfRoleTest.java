package beyondeyesight.user.domain.model.user.role;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.UUID;
import org.junit.jupiter.api.Test;

public class PrivilegesOfRoleTest {

    @Test
    public void mergeFailWithDifferentRoles() {

        Privilege privilege1 = Privilege.of("READ_ONLY");
        Privilege privilege2 = Privilege.of("WRITE_ONLY");

        Role role1 = Role
            .withoutPrivilege(UUID.fromString("1d96c03e-65b3-473c-a533-4d6283ad41e5"), "testRole1");
        Role role2 = Role
            .withoutPrivilege(UUID.fromString("6dc16589-f66d-4549-b674-9ccc2e02f88d"), "testRole2");

        PrivilegesOfRole privileges1 = PrivilegesOfRole.of(RolePrivilege.of(role1, privilege1));
        PrivilegesOfRole privileges2 = PrivilegesOfRole.of(RolePrivilege.of(role2, privilege2));

        assertThatThrownBy(() -> privileges1.merge(privileges2)).isInstanceOf(IllegalArgumentException.class);
    }
}
