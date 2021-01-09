package beyondeyesight.user.domain.model.role;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.user.domain.model.user.User;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RolesTest {

    @Test
    public void merge() {
        Role outsiderRole = Role.outsider();

        Privilege readOnly = Privilege.of("READ_ONLY");
        Privilege writeOnly = Privilege.of("WRITE_ONLY");
        Privilege readAndWrite = Privilege.of("READ_AND_WRITE");

        Role memberRole = Role.withoutPrivilege("Member");
        for (Privilege privilege : Arrays.asList(readOnly, writeOnly, readAndWrite)) {
            memberRole.add(RolePrivilege.of(memberRole, privilege));
        }



        User user = User.withoutRole("email", "name", "password");

        Roles roles1 = Roles.of(new UserRole(user, outsiderRole));
        Roles roles2 = Roles.of(new UserRole(user, memberRole));
        Roles merged = roles1.merge(roles2);
        assertThat(merged.count()).isEqualTo(roles1.count() + roles2.count());
    }

    @Test
    public void toPrivileges() {
        List<Privilege> privileges = Arrays.asList(
            Privilege.of("READ_ONLY"),
            Privilege.of("WRITE_ONLY"),
            Privilege.of("READ_AND_WRITE")
        );

        Role memberRole = Role.withoutPrivilege("Member");
        for (Privilege privilege : privileges) {
            memberRole.add(RolePrivilege.of(memberRole, privilege));
        }

        User user = User.withoutRole("email", "name", "password");

        Roles roles = Roles.empty();
        roles = roles.add(new UserRole(user, memberRole));
        assertThat(roles.toPrivileges()).isEqualTo(privileges);

    }

    @Test
    void add() {
        Role role = Role.outsider();
        User user = User.withoutRole("email", "name", "password");
        UserRole userRole = new UserRole(user, role);
        Roles roles = Roles.empty();

        roles = roles.add(userRole);
        assertThat(roles.count()).isEqualTo(1);
    }
}
