package beyondeyesight.user.infra.persistence;

import beyondeyesight.user.domain.model.user.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleJpaRepository extends JpaRepository<Role, UUID> {
    Role findByName(String name);
}
