package beyondeyesight.user.infra.persistence;

import beyondeyesight.user.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);

}
