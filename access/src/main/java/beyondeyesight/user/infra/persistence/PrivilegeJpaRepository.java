package beyondeyesight.user.infra.persistence;

import beyondeyesight.user.domain.model.user.role.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrivilegeJpaRepository extends JpaRepository<Privilege, UUID> {
    Privilege findByName(String name);
}
