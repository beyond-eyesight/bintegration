package beyondeyesight.user.domain.service;

import beyondeyesight.user.domain.model.user.DeprecateUser;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    Authentication create(DeprecateUser user);
}
