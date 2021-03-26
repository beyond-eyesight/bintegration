package beyondeyesight.user.infra.security.keycloak;

import beyondeyesight.user.domain.repository.UserRepository;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserJpaRepositoryFactory implements UserStorageProviderFactory<UserJpaRepository>{

    private final EntityManager entityManager;

    @Override
    public UserJpaRepository create(KeycloakSession session, ComponentModel model) {
        return new UserJpaRepository(session, entityManager);

    }

    @Override
    public String getId() {
        return "user-jpa-repository";
    }
}
