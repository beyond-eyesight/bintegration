package beyondeyesight.user.infra.persistence.jpa;

import beyondeyesight.user.domain.model.user.role.UserRole;
import beyondeyesight.user.domain.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRoleJpaRepositoryProxy implements UserRoleRepository {

    private final UserRoleJpaRepository userRoleJpaRepository;

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleJpaRepository.save(userRole);
    }
}
