package beyondeyesight.user.config;

import beyondeyesight.user.infra.security.JwtAuthenticationFilter;
import beyondeyesight.user.ui.UserController;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.keycloak.adapters.springsecurity.filter.QueryParamPresenceRequestMatcher;
import org.keycloak.adapters.springsecurity.management.HttpSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    @Override
    protected HttpSessionManager httpSessionManager() {
        return new HttpSessionManager();
    }

    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(
            new SessionRegistryImpl());
    }



    @Override
    protected KeycloakAuthenticationProcessingFilter keycloakAuthenticationProcessingFilter()
        throws Exception {
        RequestMatcher requestMatcher = new OrRequestMatcher(new RequestHeaderRequestMatcher(KeycloakAuthenticationProcessingFilter.AUTHORIZATION_HEADER),
            new QueryParamPresenceRequestMatcher(
                OAuth2Constants.ACCESS_TOKEN));
        KeycloakAuthenticationProcessingFilter filter = new KeycloakAuthenticationProcessingFilter(authenticationManagerBean(), requestMatcher);
        filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        KeycloakAuthenticationProvider keycloakAuthenticationProvider
            = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(
            new SimpleAuthorityMapper());

        http.authenticationProvider(keycloakAuthenticationProvider);
        http.cors().and().csrf().disable()
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .authorizeRequests(
                authorizeRequests -> authorizeRequests.antMatchers(UserController.SIGN_IN_ENDPOINT)
                    .permitAll().anyRequest().authenticated())
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//            .addFilter(
//                new JwtAuthenticationFilter(authenticationManager()))
//            .authorizeRequests(
//                authorizeRequests -> authorizeRequests
//                    .antMatchers(UserController.SIGN_IN_ENDPOINT)
//                    .permitAll()
//                    .anyRequest()
//                    .authenticated())
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
}
