package beyondeyesight.user.domain.repository;

import beyondeyesight.user.domain.model.user.role.Role;

public interface RoleRepository {

    Role save(Role role);
}
