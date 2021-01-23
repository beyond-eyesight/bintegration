package beyondeyesight.user.domain.repository;

import beyondeyesight.user.domain.model.user.role.UserRole;

public interface UserRoleRepository {

    UserRole save(UserRole userRole);
}
