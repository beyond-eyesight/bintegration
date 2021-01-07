package beyondeyesight.user.domain.service;

import beyondeyesight.user.domain.Role;
import beyondeyesight.user.domain.Roles;
import beyondeyesight.user.domain.User;
import beyondeyesight.user.domain.UserRole;
import beyondeyesight.user.domain.repository.RoleRepository;
import beyondeyesight.user.domain.repository.UserRepository;
import beyondeyesight.user.domain.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public UserService(UserRepository userRepository,
        RoleRepository roleRepository,
        UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    User addRole(User user, Role role) {
        // todo: 만약 persist하지 않은 user가 들어오면 어떻게 되는지 테스트!
        role = roleRepository.save(role);
        UserRole userRole = userRoleRepository.save(new UserRole(user, role));
        user = user.addRoles(Roles.of(userRole));
        return userRepository.save(user);
    }

}
