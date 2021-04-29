package beyondeyesight.user.infra.persistence;

import beyondeyesight.user.domain.model.user.role.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRoleJpaRepository extends JpaRepository<UserRole, UUID> {

}
