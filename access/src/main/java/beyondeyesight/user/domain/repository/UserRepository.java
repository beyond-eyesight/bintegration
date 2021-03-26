package beyondeyesight.user.domain.repository;

import beyondeyesight.user.domain.model.Realm;
import beyondeyesight.user.domain.model.user.User;
import java.util.UUID;

// todo: optional로 바꾸기
public interface UserRepository {
    User findById(UUID id);

    User findByIdAndRealm(UUID id, Realm realm);
    // signature 추상화하면, findBySignature로 할지 췤
    User findByEmail(String email);

    User save(User user);
}
