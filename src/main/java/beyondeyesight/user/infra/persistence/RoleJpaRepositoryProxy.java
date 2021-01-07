package beyondeyesight.user.infra.persistence;

import beyondeyesight.user.domain.model.role.Role;
import beyondeyesight.user.domain.repository.RoleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RoleJpaRepositoryProxy implements RoleRepository {

    @Override
    public Role save(Role role) {
        return null;
    }
}
