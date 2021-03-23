package beyondeyesight.user.study.keycloak;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

public class KeycloakAuthenticationProviderTest {
    private static final KeycloakAuthenticationProvider keycloakAuthenticationProvider = new KeycloakAuthenticationProvider();

    @Test
    void supports() {
        assertTrue(keycloakAuthenticationProvider.supports(KeycloakAuthenticationToken.class));
    }

}
