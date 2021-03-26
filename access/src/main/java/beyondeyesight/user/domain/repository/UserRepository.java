package beyondeyesight.user.domain.repository;

import beyondeyesight.user.domain.model.Realm;
import beyondeyesight.user.domain.model.user.DeprecateUser;
import java.util.UUID;

// todo: optional로 바꾸기
public interface UserRepository {
    DeprecateUser findById(UUID id);

    DeprecateUser findByIdAndRealm(UUID id, Realm realm);
    // signature 추상화하면, findBySignature로 할지 췤
    DeprecateUser findByEmail(String email);

    DeprecateUser save(DeprecateUser user);
}
