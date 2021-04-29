package beyondeyesight.user.infra.persistence;

import beyondeyesight.user.domain.model.user.role.Role;
import beyondeyesight.user.domain.model.user.role.RolePrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RolePrivilegeJpaRepository extends JpaRepository<RolePrivilege, UUID> {
    RolePrivilege findByRole(Role role);

}
