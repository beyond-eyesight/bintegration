package beyondeyesight.user.infra.security.keycloak;

import org.keycloak.storage.UserStorageProvider;
import org.springframework.stereotype.Repository;

@Repository
public class UserAdapter implements UserStorageProvider {


    @Override
    public void close() {

    }
}

