import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

import javax.naming.InitialContext;

public class ExampleUserStorageProviderFactory implements UserStorageProviderFactory<ExampleUserStorageProvider> {
    @Override
    public ExampleUserStorageProvider create(KeycloakSession session, ComponentModel model) {
        try {
            InitialContext ctx = new InitialContext();
            ExampleUserStorageProvider provider = (ExampleUserStorageProvider) ctx.lookup("java:global/user-storage-jpa-example/" + ExampleUserStorageProvider.class.getSimpleName());
            provider.setModel(model);
            provider.setSession(session);
            return provider;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getId() {
        return "example-provider";
    }
}
