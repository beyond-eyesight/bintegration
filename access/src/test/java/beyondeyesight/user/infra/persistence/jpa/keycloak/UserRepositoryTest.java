package beyondeyesight.user.infra.persistence.jpa.keycloak;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() {
        User user = User.of(UUID.randomUUID(), "haha", true, "password", "geunwon", "Lim");
        user = userRepository.save(user);
        assertThat(user).isNotNull();
    }
}
