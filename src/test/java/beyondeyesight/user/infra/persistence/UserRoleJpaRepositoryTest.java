package beyondeyesight.user.infra.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.user.domain.model.role.Privilege;
import beyondeyesight.user.domain.model.role.Role;
import beyondeyesight.user.domain.model.role.RolePrivilege;
import beyondeyesight.user.domain.model.role.UserRole;
import beyondeyesight.user.domain.model.user.User;
import beyondeyesight.user.domain.model.user.UserPrincipal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class UserRoleJpaRepositoryTest {
    @Autowired
    private UserRoleJpaRepository userRoleJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    @Autowired
    private PrivilegeJpaRepository privilegeJpaRepository;

    @Autowired
    private RolePrivilegeJpaRepository rolePrivilegeJpaRepository;

    @Test
    public void save() {
        // given
        final String ROLE_NAME = "Role";
        final String PRIVILEGE_NAME = "Privilege";
        Role role = Role.withoutPrivilege(ROLE_NAME);
        role = roleJpaRepository.save(role);
        Privilege privilege = Privilege.of(PRIVILEGE_NAME);
        privilegeJpaRepository.save(privilege);
        RolePrivilege rolePrivilege = RolePrivilege.of(role, privilege);
        rolePrivilege = rolePrivilegeJpaRepository.save(rolePrivilege);
        User user = userJpaRepository.save(new User("wom2277@naver.com", "geunwon", "1234"));
        UserRole initialized = new UserRole(user, role);
        assertThat(initialized.getId()).isNull();

        //when
        UserRole saved = userRoleJpaRepository.save(initialized);
        //then
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();

        User found = userJpaRepository.findById(user.getId())
            .orElseThrow(IllegalStateException::new);
        assertThat(found).isNotNull();
    }
}
