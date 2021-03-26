package beyondeyesight.user.infra.persistence.jpa;

import beyondeyesight.user.domain.model.Realm;
import beyondeyesight.user.domain.model.user.DeprecateUser;
import beyondeyesight.user.domain.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserJapRepositoryProxy implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    // todo: check optional, 등등
    @Override
    public DeprecateUser findById(UUID id) {
        return userJpaRepository.findById(id).orElseThrow(IllegalStateException::new);
    }

    @Override
    public DeprecateUser findByIdAndRealm(UUID id, Realm realm) {
        return null;
    }

    @Override
    public DeprecateUser findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public DeprecateUser save(DeprecateUser user) {
        return userJpaRepository.save(user);
    }
}
