package beyondeyesight.user.infra.persistence.jpa;

import beyondeyesight.user.domain.model.user.role.UserRole;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleJpaRepository extends JpaRepository<UserRole, UUID> {

}
