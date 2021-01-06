package beyondeyesight.user.domain.repository;

import beyondeyesight.user.domain.User;

public interface UserRepository {
    User findByEmail(String email);
}
