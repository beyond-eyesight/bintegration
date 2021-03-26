package beyondeyesight.user.infra.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.user.infra.persistence.jpa.RoleJpaRepository;
import beyondeyesight.user.infra.persistence.jpa.UserJpaRepository;
import beyondeyesight.user.infra.persistence.jpa.UserRoleJpaRepository;
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
    public void saveAndFindById() {
        // given
//        Role role = Role.outsider();
//        role = roleJpaRepository.save(role);
//        User user = userJpaRepository.save(User.withoutRole("wom2277@naver.com", "geunwon", "1234"));
//        UserRole initialized = new UserRole(user, role);
//        assertThat(initialized.getId()).isNull();
//
//        // when
//        UserRole saved = userRoleJpaRepository.save(initialized);
//        // then
//        assertThat(saved).isNotNull();
//        assertThat(saved.getId()).isNotNull();
//
//        // when
//        UserRole found = userRoleJpaRepository.findById(UUID.fromString(saved.getId()))
//            .orElseThrow(IllegalStateException::new);
//        // then
//        assertThat(found).isNotNull();
    }
}
