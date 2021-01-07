package beyondeyesight.user.domain.repository;

import beyondeyesight.user.domain.model.user.User;

public interface UserRepository {
    User findByEmail(String email);

    User save(User user);
}
