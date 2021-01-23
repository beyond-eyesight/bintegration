package beyondeyesight.user.infra.security;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    public JwtAuthenticationFilter(
        AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
}
