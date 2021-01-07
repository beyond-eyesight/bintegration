package beyondeyesight.user.infra.persistence;

import beyondeyesight.user.domain.model.user.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, UUID> {}
