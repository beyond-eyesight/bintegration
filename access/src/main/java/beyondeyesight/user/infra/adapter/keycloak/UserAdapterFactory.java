package beyondeyesight.user.infra.adapter.keycloak;

import beyondeyesight.user.infra.persistence.jpa.keycloak.UserRepository;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.provider.ServerInfoAwareProviderFactory;
import org.keycloak.storage.UserStorageProviderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAdapterFactory implements UserStorageProviderFactory<UserAdapter>,
    ServerInfoAwareProviderFactory {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserAdapter create(KeycloakSession session, ComponentModel model) {
        return new UserAdapter(session, this.userRepository, model);

    }

    @Override
    public String getId() {
        return "user-adapter";
    }

    @Override
    public Map<String, String> getOperationalInfo() {
        Map<String, String> ret = new LinkedHashMap<>();
        ret.put("theme-name", "my-theme");
        return ret;
    }
}
