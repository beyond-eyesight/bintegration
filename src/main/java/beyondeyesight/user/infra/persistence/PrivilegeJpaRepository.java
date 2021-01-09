package beyondeyesight.user.infra.persistence;

import beyondeyesight.user.domain.model.role.Privilege;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeJpaRepository extends JpaRepository<Privilege, UUID> {
    Privilege findByName(String name);
}
