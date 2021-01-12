package beyondeyesight.user.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class SignInRequest {
    private final String signature;
    private final String password;
}
