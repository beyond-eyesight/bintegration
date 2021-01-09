package beyondeyesight.user.infra.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.user.domain.model.role.Privilege;
import beyondeyesight.user.domain.model.role.Role;
import beyondeyesight.user.domain.model.role.RolePrivilege;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//@ActiveProfiles("test")
@SpringBootTest
public class RolePrivilegeJpaRepositoryTest {


    @Autowired
    private RolePrivilegeJpaRepository rolePrivilegeJpaRepository;

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    @Autowired
    private PrivilegeJpaRepository privilegeJpaRepository;

    @Test
    public void saveAnd() {
        // given
        final String ROLE_NAME = "Role";
        final String PRIVILEGE_NAME = "Privilege";
        Role role = Role.withoutPrivilege(ROLE_NAME);
        role = roleJpaRepository.save(role);
        Privilege privilege = Privilege.of(PRIVILEGE_NAME);
        privilegeJpaRepository.save(privilege);
        RolePrivilege rolePrivilege = RolePrivilege.of(role, privilege);

        // when
        rolePrivilege = rolePrivilegeJpaRepository.save(rolePrivilege);
        // then
        assertThat(rolePrivilege.getId()).isNotNull();

        //when
        rolePrivilege = rolePrivilegeJpaRepository.findByRole(role);
        //then
        assertThat(rolePrivilege).isNotNull();
    }

    // todo: test - 롤, 프리빌리지, 롤프리빌리지 다 저장 후 롤에서 프리빌리지 카운트하는 테스트
}
