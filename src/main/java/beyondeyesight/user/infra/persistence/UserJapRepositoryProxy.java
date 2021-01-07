package beyondeyesight.user.infra.persistence;

import beyondeyesight.user.domain.User;
import beyondeyesight.user.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserJapRepositoryProxy implements UserRepository {

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }
}
