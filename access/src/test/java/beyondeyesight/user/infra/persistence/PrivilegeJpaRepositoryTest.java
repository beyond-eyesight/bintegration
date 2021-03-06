package beyondeyesight.user.infra.persistence;

import beyondeyesight.user.domain.model.user.role.Privilege;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
public class PrivilegeJpaRepositoryTest {

    @Autowired
    private PrivilegeJpaRepository privilegeJpaRepository;

    @Test
    public void save() {
        Privilege created = Privilege.of("READ_ONLY");
        assertThat(created.getId()).isNull();

        Privilege saved = privilegeJpaRepository.save(created);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();

        Privilege found = privilegeJpaRepository.findById(saved.getId())
            .orElseThrow(IllegalStateException::new);
        assertThat(found).isNotNull();
    }
}
