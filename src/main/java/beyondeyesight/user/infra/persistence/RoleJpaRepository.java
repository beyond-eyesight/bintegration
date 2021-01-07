package beyondeyesight.user.infra.persistence;

import beyondeyesight.user.domain.model.role.Role;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJpaRepository extends JpaRepository<Role, UUID> {
}
