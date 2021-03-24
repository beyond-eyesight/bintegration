package beyondeyesight.user.infra.model;

import java.security.Principal;
import java.util.Set;
import org.keycloak.adapters.spi.KeycloakAccount;

public class KeycloakUser implements KeycloakAccount {

    @Override
    public Principal getPrincipal() {
        return null;
    }

    @Override
    public Set<String> getRoles() {
        return null;
    }
}
