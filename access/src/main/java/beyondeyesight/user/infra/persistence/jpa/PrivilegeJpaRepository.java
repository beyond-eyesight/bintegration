package beyondeyesight.user.infra.persistence.jpa;

import beyondeyesight.user.domain.model.user.role.Privilege;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeJpaRepository extends JpaRepository<Privilege, UUID> {
    Privilege findByName(String name);
}
