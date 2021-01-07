package beyondeyesight.user.infra.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
class RoleJpaRepositoryTest {

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    @Test
    public void save() {
        // todo: check
//        Role created = Role.initialize();
//        assertThat(created.getId()).isNull();
//
//        Role saved = roleJpaRepository.save(created);
//        assertThat(saved).isNotNull();
//        assertThat(saved.getId()).isNotNull();
//
//        Role updated = roleJpaRepository.save(saved);
//        assertThat(updated).isNotNull();
//        assertThat(updated.getId()).isNotNull();
//
//        assertThat(updated).isEqualTo(saved);
    }
}
