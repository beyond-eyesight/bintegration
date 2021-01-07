package beyondeyesight.user.infra.persistence;

import beyondeyesight.user.domain.model.role.UserRole;
import beyondeyesight.user.domain.repository.UserRoleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleJpaRepositoryProxy implements UserRoleRepository {

    @Override
    public UserRole save(UserRole userRole) {
        return null;
    }
}
