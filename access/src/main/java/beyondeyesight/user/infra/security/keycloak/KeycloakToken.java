package beyondeyesight.user.infra.security.keycloak;

import beyondeyesight.user.domain.model.Token;
import org.keycloak.adapters.spi.KeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

public class KeycloakToken extends KeycloakAuthenticationToken implements Token {
    public KeycloakToken(KeycloakAccount account,
        boolean interactive) {
        super(account, interactive);
    }
}
