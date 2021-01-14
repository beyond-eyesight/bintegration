package beyondeyesight.user.domain.service;

import beyondeyesight.user.domain.model.user.User;
import beyondeyesight.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsLoader implements UserDetailsService {

    private final UserRepository userRepository;

    // todo: username이기에, userId 파라미터가 맘에 들지 않음. loadUserByUserId같은걸 만들어서 해결할수 있을지 보기!
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}
