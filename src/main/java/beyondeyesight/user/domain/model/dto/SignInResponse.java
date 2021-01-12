package beyondeyesight.user.domain.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInResponse {
    private final String signature;
    private final String token;

    public static SignInResponse of(String signature, String token) {
        return new SignInResponse(signature, token);
    }
}
