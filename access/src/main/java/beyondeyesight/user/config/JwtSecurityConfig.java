package beyondeyesight.user.config;

//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
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
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//}
