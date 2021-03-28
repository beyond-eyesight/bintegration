package beyondeyesight.user.infra.adapter.keycloak;

import beyondeyesight.user.infra.persistence.jpa.keycloak.User;
import beyondeyesight.user.infra.persistence.jpa.keycloak.UserRepository;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputUpdater;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;

public class UserAdapter implements UserStorageProvider, UserLookupProvider,
    CredentialInputValidator, CredentialInputUpdater {

    private KeycloakSession session;
    private UserRepository userRepository;
    private ComponentModel model;

    public UserAdapter(KeycloakSession session, UserRepository userRepository, ComponentModel model) {
        this.session = session;
        this.userRepository = userRepository;
        this.model = model;
    }


    @Override
    public void close() {

    }

    @Override
    public UserModel getUserById(String id, RealmModel realm) {
        User user = userRepository.findById(UUID.fromString(id)).orElse(null);
        if (Objects.isNull(user)) {
            return null;
        }
        // todo: refac
        return User.of(UUID.fromString(id), user.getUsername(), user.isEnabled(), user.getPassword(), user.getFirstName(), user.getLastName());
    }

    @Override
    public UserModel getUserByUsername(String username, RealmModel realm) {
        return null;
    }

    @Override
    public UserModel getUserByEmail(String email, RealmModel realm) {
        return null;
    }

    @Override
    public boolean supportsCredentialType(String credentialType) {
        return false;
    }

    @Override
    public boolean updateCredential(RealmModel realm, UserModel user, CredentialInput input) {
        return false;
    }

    @Override
    public void disableCredentialType(RealmModel realm, UserModel user, String credentialType) {

    }

    @Override
    public Set<String> getDisableableCredentialTypes(RealmModel realm, UserModel user) {
        return null;
    }

    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
        return false;
    }

    @Override
    public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
        return true;
    }
}

