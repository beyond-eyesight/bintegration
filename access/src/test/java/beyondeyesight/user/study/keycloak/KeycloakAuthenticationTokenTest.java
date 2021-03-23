package beyondeyesight.user.study.keycloak;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.user.domain.model.user.User;
import org.junit.jupiter.api.Test;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

public class KeycloakAuthenticationTokenTest {

    @Test
    void defaultConstruct() {
        User user = User.withoutRole("test@email.com", "name", "password");
        KeycloakAuthenticationToken keycloakAuthenticationToken = new KeycloakAuthenticationToken(user, true);
        assertThat(keycloakAuthenticationToken).isNotNull();
    }

}
