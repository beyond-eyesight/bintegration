package beyondeyesight.user.domain.service;

import beyondeyesight.user.domain.model.user.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    Authentication create(User user);
}
