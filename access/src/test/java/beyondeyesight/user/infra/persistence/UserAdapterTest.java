package beyondeyesight.user.infra.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.user.domain.model.user.DeprecateUser;
import beyondeyesight.user.domain.model.user.role.Role;
import beyondeyesight.user.domain.model.user.role.RolesOfUser;
import beyondeyesight.user.domain.model.user.role.UserRole;
import beyondeyesight.user.infra.persistence.jpa.RoleJpaRepository;
import beyondeyesight.user.infra.persistence.jpa.UserJpaRepository;
import beyondeyesight.user.infra.persistence.jpa.UserRoleJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@DataJpaTest
public class UserAdapterTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    @Autowired
    private UserRoleJpaRepository userRoleJpaRepository;

    @Test
    public void save() {
        DeprecateUser user = DeprecateUser.withoutRole("wom2277@naver.com", "ttkmw", "ttkmw");
        DeprecateUser saved = userJpaRepository.save(user);
        assertThat(saved).isNotNull();
    }

    @Test
    @Transactional
    public void saveAndFindByEmail() {
        Role role = Role.outsider();
        role = roleJpaRepository.save(role);
        DeprecateUser user = userJpaRepository.save(
            DeprecateUser.withoutRole("wom2277@naver.com", "geunwon", "1234"));

        UserRole userRole = userRoleJpaRepository.save(new UserRole(user, role));
        user.addRoles(RolesOfUser.of(userRole));

        DeprecateUser foundByEmail = userJpaRepository.findByEmail("wom2277@naver.com");
        System.out.println(foundByEmail);
        System.out.println("here!!!");
    }
}
