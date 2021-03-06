package beyondeyesight.user.ui;

import beyondeyesight.user.domain.model.user.User;
import beyondeyesight.user.domain.model.user.dto.SignInRequest;
import beyondeyesight.user.domain.model.user.dto.SignInResponse;
import beyondeyesight.user.domain.service.SecurityService;
import beyondeyesight.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserController {

    public static final String SIGN_IN_ENDPOINT = "/signIn";

    private final SecurityService securityService;
    private final UserService userService;

    @PostMapping(value = SIGN_IN_ENDPOINT)
    public ResponseEntity<SignInResponse> signIn(@RequestBody final SignInRequest signInRequest) {
        String token = securityService
            .authenticate(signInRequest.getSignature(), signInRequest.getPassword());

        return ResponseEntity.created(getLocation(signInRequest.getSignature()))
            .body(SignInResponse.of(signInRequest.getSignature(), token));
    }

    private URI getLocation(final String signature) {
        User foundBySignature = userService.findBySignature(signature);
        return WebMvcLinkBuilder.linkTo(UserController.class).slash(foundBySignature.getId())
            .toUri();
    }
}
