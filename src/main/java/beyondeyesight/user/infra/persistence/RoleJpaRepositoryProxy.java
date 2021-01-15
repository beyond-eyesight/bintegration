package beyondeyesight.user.infra.persistence;

import beyondeyesight.user.domain.model.user.role.Role;
import beyondeyesight.user.domain.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleJpaRepositoryProxy implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;

    @Override
    public Role save(Role role) {
        return roleJpaRepository.save(role);
    }
}
