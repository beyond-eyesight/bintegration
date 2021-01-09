package beyondeyesight.user.domain.repository;

import beyondeyesight.user.domain.model.user.User;
import java.util.UUID;

// todo: optional로 바꾸기
public interface UserRepository {
    User findById(UUID id);
    User findByEmail(String email);

    User save(User user);
}
