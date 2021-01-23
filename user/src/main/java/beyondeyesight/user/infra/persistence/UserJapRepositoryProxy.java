package beyondeyesight.user.infra.persistence;

import beyondeyesight.user.domain.model.user.User;
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
    public User findById(UUID id) {
        return userJpaRepository.findById(id).orElseThrow(IllegalStateException::new);
    }

    @Override
    public User findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }
}
