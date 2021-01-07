package beyondeyesight.user.domain.model.role;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.user.domain.model.user.User;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RolesTest {

    @Test
    public void merge() {
        Role outsiderRole = Role.initialize();

        Privilege readOnly = new Privilege("READ_ONLY");
        Privilege writeOnly = new Privilege("WRITE_ONLY");
        Privilege readAndWrite = new Privilege("READ_AND_WRITE");

        Role memberRole = Role.withoutPrivilege("Member");
        for (Privilege privilege : Arrays.asList(readOnly, writeOnly, readAndWrite)) {
            memberRole.add(privilege);
        }



        User user = new User("email", "name", "password");

        Roles roles1 = Roles.of(new UserRole(user, outsiderRole));
        Roles roles2 = Roles.of(new UserRole(user, memberRole));
        Roles merged = roles1.merge(roles2);
        assertThat(merged.count()).isEqualTo(roles1.count() + roles2.count());
    }

    @Test
    public void toPrivileges() {
        List<Privilege> privileges = Arrays.asList(
            new Privilege("READ_ONLY"),
            new Privilege("WRITE_ONLY"),
            new Privilege("READ_AND_WRITE")
        );

        Role memberRole = Role.withoutPrivilege("Member");
        for (Privilege privilege : privileges) {
            memberRole.add(privilege);
        }

        User user = new User("email", "name", "password");

        Roles roles = Roles.empty();
        roles = roles.add(new UserRole(user, memberRole));
        assertThat(roles.toPrivileges()).isEqualTo(privileges);

    }

    @Test
    void add() {
        Role role = Role.initialize();
        User user = new User("email", "name", "password");
        UserRole userRole = new UserRole(user, role);
        Roles roles = Roles.empty();

        roles = roles.add(userRole);
        assertThat(roles.count()).isEqualTo(1);
    }
}
