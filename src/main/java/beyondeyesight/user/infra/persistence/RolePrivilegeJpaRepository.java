package beyondeyesight.user.infra.persistence;

import beyondeyesight.user.domain.model.role.Role;
import beyondeyesight.user.domain.model.role.RolePrivilege;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePrivilegeJpaRepository extends JpaRepository<RolePrivilege, UUID> {
    RolePrivilege findByRole(Role role);

}
