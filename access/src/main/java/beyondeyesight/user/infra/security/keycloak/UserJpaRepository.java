package beyondeyesight.user.infra.security.keycloak;

import javax.persistence.EntityManager;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.jpa.JpaUserProvider;
import org.keycloak.storage.UserStorageProvider;

// todo: 굳이 Jpa를 안넣어도 될 수도 있겠다. 그냥 Keycloak Repository로
public class UserJpaRepository extends JpaUserProvider implements UserStorageProvider {

    public UserJpaRepository(KeycloakSession session, EntityManager em) {
        super(session, em);
    }
}
