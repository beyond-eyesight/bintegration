package beyondeyesight.user.domain.model.user.role;

import beyondeyesight.user.domain.model.user.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RolesOfUserTest {

    @Test
    public void mergeSucceed() {
        Role outsiderRole = Role.outsider();

        Privilege readOnly = Privilege.of("READ_ONLY");
        Privilege writeOnly = Privilege.of("WRITE_ONLY");
        Privilege readAndWrite = Privilege.of("READ_AND_WRITE");
        Role memberRole = Role.withoutPrivilege("Member");
        for (Privilege privilege : Arrays.asList(readOnly, writeOnly, readAndWrite)) {
            memberRole.add(RolePrivilege.of(memberRole, privilege));
        }

        User user = User.withoutRole("email", "name", "password");

        RolesOfUser roles1 = RolesOfUser.of(new UserRole(user, outsiderRole));
        RolesOfUser roles2 = RolesOfUser.of(new UserRole(user, memberRole));
        RolesOfUser merged = roles1.merge(roles2);
        assertThat(merged.count()).isEqualTo(roles1.count() + roles2.count());
    }

    @Test
    public void mergeFailWithDifferentUsers() {
        // given
        Role outsiderRole = Role.outsider();
        Privilege readOnly = Privilege.of("READ_ONLY");
        Privilege writeOnly = Privilege.of("WRITE_ONLY");
        Privilege readAndWrite = Privilege.of("READ_AND_WRITE");
        Role memberRole = Role.withoutPrivilege("Member");
        for (Privilege privilege : Arrays.asList(readOnly, writeOnly, readAndWrite)) {
            memberRole.add(RolePrivilege.of(memberRole, privilege));
        }

        User user1 = User
            .withoutRole(UUID.fromString("1d96c03e-65b3-473c-a533-4d6283ad41e5"), "email", "name",
                "password");
        User user2 = User
            .withoutRole(UUID.fromString("6dc16589-f66d-4549-b674-9ccc2e02f88d"), "email", "name",
                "password");

        RolesOfUser roles1 = RolesOfUser.of(new UserRole(user1, outsiderRole));
        RolesOfUser roles2 = RolesOfUser.of(new UserRole(user2, memberRole));
        //when & then
        assertThatThrownBy(() -> roles1.merge(roles2)).isInstanceOf(IllegalArgumentException.class);
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

        RolesOfUser roles = RolesOfUser.empty();
        roles = roles.add(new UserRole(user, memberRole));
        assertThat(roles.toPrivileges()).isEqualTo(privileges);

    }

    @Test
    void add() {
        Role role = Role.outsider();
        User user = User.withoutRole("email", "name", "password");
        UserRole userRole = new UserRole(user, role);
        RolesOfUser roles = RolesOfUser.empty();

        roles = roles.add(userRole);
        assertThat(roles.count()).isEqualTo(1);
    }
}
