package beyondeyesight.user.domain.service;

import org.springframework.security.core.Authentication;

public interface TokenProvider {
    String generate(Authentication authentication);
}
