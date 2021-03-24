package beyondeyesight.user.infra.service;

import beyondeyesight.user.domain.service.AuthenticationService;
import beyondeyesight.user.infra.model.KeycloakUser;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

// todo: 나중에 db 스키마까지 커스텀하고나면, 일단 디비부터 찾아보고, 그다음 keycloack 호출하는 식으로.
@Service
public class KeycloakAuthenticationService implements AuthenticationService {

    @Override
    public Authentication create(String signature, String password) {
        return new KeycloakAuthenticationToken(new KeycloakUser(), true);
    }
}
