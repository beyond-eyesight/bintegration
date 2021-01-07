package beyondeyesight.user.infra.persistence;

import beyondeyesight.user.domain.model.role.UserRole;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleJpaRepository extends JpaRepository<UserRole, UUID> {

}
