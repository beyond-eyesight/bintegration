package beyondeyesight.user.infra.persistence.jpa.keycloak;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

}
