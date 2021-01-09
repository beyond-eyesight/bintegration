package beyondeyesight.user.infra.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.user.domain.model.role.Role;
import beyondeyesight.user.domain.model.role.Roles;
import beyondeyesight.user.domain.model.role.UserRole;
import beyondeyesight.user.domain.model.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@DataJpaTest
public class UserJpaRepositoryTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    @Autowired
    private UserRoleJpaRepository userRoleJpaRepository;

    @Test
    public void save() {
        User user = User.withoutRole("wom2277@naver.com", "ttkmw", "ttkmw");
        User saved = userJpaRepository.save(user);
        assertThat(saved).isNotNull();
    }

    @Test
    @Transactional
    public void saveAndFindByEmail() {
        Role role = Role.outsider();
        role = roleJpaRepository.save(role);
        User user = userJpaRepository.save(User.withoutRole("wom2277@naver.com", "geunwon", "1234"));

        UserRole userRole = userRoleJpaRepository.save(new UserRole(user, role));
        user.addRoles(Roles.of(userRole));

        User foundByEmail = userJpaRepository.findByEmail("wom2277@naver.com");
        System.out.println(foundByEmail);
        System.out.println("here!!!");
    }
}
