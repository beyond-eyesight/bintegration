package beyondeyesight.user.domain.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    Authentication create(String signature, String password);
}
