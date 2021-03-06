package beyondeyesight.user.infra.persistence;

import beyondeyesight.user.domain.model.user.role.Privilege;
import beyondeyesight.user.domain.model.user.role.Role;
import beyondeyesight.user.domain.model.user.role.RolePrivilege;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class RoleJpaRepositoryTest {

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    @Test
    public void save() {
        // todo: check
        Role created = Role.withoutPrivilege("Member");
        assertThat(created.getId()).isNull();

        Role saved = roleJpaRepository.save(created);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.countPrivileges()).isEqualTo(0);

        Privilege readOnly = Privilege.of("READ_ONLY");
        saved.add(RolePrivilege.of(saved, readOnly));
        assertThat(saved.countPrivileges()).isEqualTo(1);

        Role found = roleJpaRepository.findById(saved.getId())
            .orElseThrow(IllegalStateException::new);
        assertThat(found.countPrivileges()).isEqualTo(1);
    }
}
