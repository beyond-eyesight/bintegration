package beyondeyesight.user.infra.persistence.jpa;

import beyondeyesight.user.domain.model.user.DeprecateUser;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<DeprecateUser, UUID> {

    DeprecateUser findByEmail(String email);

}
