package beyondeyesight.user.ui;

import beyondeyesight.user.domain.model.dto.SignInRequest;
import beyondeyesight.user.domain.model.dto.SignInResponse;
import beyondeyesight.user.domain.model.user.User;
import beyondeyesight.user.domain.service.SecurityService;
import beyondeyesight.user.domain.service.UserService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final SecurityService securityService;
    private final UserService userService;

    @RequestMapping(value = "/")
    public String haha() {
        return "hihi";
    }

    // todo: 다른 장소에서 게터를 여러번 써도 되려나..?
    @PostMapping(value = "/signIn")
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
