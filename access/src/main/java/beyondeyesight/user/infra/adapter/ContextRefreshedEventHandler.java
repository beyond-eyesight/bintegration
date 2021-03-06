package beyondeyesight.user.infra.adapter;

import beyondeyesight.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Component
@RequiredArgsConstructor
public class ContextRefreshedEventHandler implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        User user = User.withoutRole("wom2277@naver.com", "geunwon", passwordEncoder.encode("12345678*at"));
//        userRepository.save(user);
    }
}
