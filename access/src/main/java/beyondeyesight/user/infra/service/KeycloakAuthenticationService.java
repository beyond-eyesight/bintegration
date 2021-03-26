package beyondeyesight.user.infra.service;

import beyondeyesight.user.domain.model.user.DeprecateUser;
import beyondeyesight.user.domain.service.AuthenticationService;
import java.security.Principal;
import java.util.Set;
import org.keycloak.adapters.spi.KeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

// todo: 나중에 db 스키마까지 커스텀하고나면, 일단 디비부터 찾아보고, 그다음 keycloack 호출하는 식으로.
@Service
public class KeycloakAuthenticationService implements AuthenticationService {

    @Override
    public Authentication create(DeprecateUser user) {
        return new KeycloakAuthenticationToken(new KeycloakUser(user), true);
    }

    private static class KeycloakUser implements KeycloakAccount {
        private final Principal principal;
        private final Set<String> roles;

        public KeycloakUser(DeprecateUser user) {
            this.principal = user;
            this.roles = user.getRoles().get();
        }

        @Override
        public Principal getPrincipal() {
            return principal;
        }

        @Override
        public Set<String> getRoles() {
            return roles;
        }
    }
}
