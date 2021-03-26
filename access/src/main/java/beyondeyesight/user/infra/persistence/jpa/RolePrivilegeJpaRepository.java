package beyondeyesight.user.infra.persistence.jpa;

import beyondeyesight.user.domain.model.user.role.Role;
import beyondeyesight.user.domain.model.user.role.RolePrivilege;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePrivilegeJpaRepository extends JpaRepository<RolePrivilege, UUID> {
    RolePrivilege findByRole(Role role);

}
