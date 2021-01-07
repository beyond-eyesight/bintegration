package beyondeyesight.user.infra.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.user.domain.Role;
import beyondeyesight.user.domain.User;
import beyondeyesight.user.domain.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
class UserRoleJpaRepositoryTest {
    @Autowired
    private UserRoleJpaRepository userRoleJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    @Test
    public void save() {
        // given
        Role role = roleJpaRepository.save(Role.initialize());
        User user = userJpaRepository.save(new User("wom2277@naver.com", "geunwon", "1234"));
        UserRole initialized = new UserRole(user, role);
        assertThat(initialized.getId()).isNull();

        //when
        UserRole saved = userRoleJpaRepository.save(initialized);
        //then
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }

}
