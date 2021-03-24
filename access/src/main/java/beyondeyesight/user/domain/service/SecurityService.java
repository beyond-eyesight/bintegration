package beyondeyesight.user.domain.service;

import beyondeyesight.user.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public String authenticate(User user) {
        Authentication authentication = authenticationManager.authenticate(
            authenticationService.create(user)
        );

        // todo: 컨텍스트를 여기에서 가져와도 되나?
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.generate(authentication);
    }

}
