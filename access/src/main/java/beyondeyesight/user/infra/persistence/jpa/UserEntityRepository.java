package beyondeyesight.user.infra.persistence.jpa;

import beyondeyesight.user.domain.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, String> {

}
