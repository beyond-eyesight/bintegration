package beyondeyesight.user.study.keycloak;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import beyondeyesight.user.ui.UserController;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.keycloak.adapters.springsecurity.filter.QueryParamPresenceRequestMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootTest
public class KeycloakAuthenticationProcessingFilterTest {

    @Autowired
    private AuthenticationManager authenticationManager;


    @MethodSource("getRequestCasesForFilter")
    @ParameterizedTest
    public void filter(HttpServletRequest request) {
        RequestMatcher requestMatcher = new OrRequestMatcher(new AntPathRequestMatcher(
            UserController.SIGN_IN_ENDPOINT), new RequestHeaderRequestMatcher("Authorization"),
            new QueryParamPresenceRequestMatcher(
                "access_token"));
        StubKeycloakAuthenticationProcessingFilter keycloakAuthenticationProcessingFilter = new StubKeycloakAuthenticationProcessingFilter(
            authenticationManager, requestMatcher);

        assertTrue(keycloakAuthenticationProcessingFilter.requiresAuthentication(request, null));
    }

    private static Stream<Arguments> getRequestCasesForFilter() {
        return Stream.of(Arguments.of(stubGetRequest(UserController.SIGN_IN_ENDPOINT)),
            Arguments.of(stubRequestWithAuthHeader()), Arguments.of(stubRequestWithParam()));
    }

    private static HttpServletRequest stubRequestWithParam() {
        HttpServletRequest stubRequest = mock(HttpServletRequest.class);
        when(stubRequest.getParameter("access_token")).thenReturn("");
        return stubRequest;
    }

    private static HttpServletRequest stubRequestWithAuthHeader() {
        HttpServletRequest stubRequest = mock(HttpServletRequest.class);
        when(stubRequest.getHeader("Authorization")).thenReturn("");
        return stubRequest;
    }

    private static HttpServletRequest stubGetRequest(String path) {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getMethod()).thenReturn(RequestMethod.GET.name());
        when(mockRequest.getServletPath()).thenReturn(path);
        return mockRequest;
    }

    private static class StubKeycloakAuthenticationProcessingFilter extends
        KeycloakAuthenticationProcessingFilter {

        public StubKeycloakAuthenticationProcessingFilter(
            AuthenticationManager authenticationManager,
            RequestMatcher requiresAuthenticationRequestMatcher) {
            super(authenticationManager, requiresAuthenticationRequestMatcher);
        }

        @Override
        protected boolean requiresAuthentication(HttpServletRequest request,
            HttpServletResponse response) {
            return super.requiresAuthentication(request, response);
        }
    }
}
