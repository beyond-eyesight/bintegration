package beyondeyesight.user.domain.service;

import beyondeyesight.user.domain.model.role.Role;
import beyondeyesight.user.domain.model.role.Roles;
import beyondeyesight.user.domain.model.role.UserRole;
import beyondeyesight.user.domain.model.user.User;
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

    User create(String email, String name, String password) {
        User user = new User(email, name, password);
        return addRole(user, Role.initialize());
    }

    User addRole(User user, Role role) {
        // todo: 만약 persist하지 않은 user가 들어오면 어떻게 되는지 테스트!
        role = roleRepository.save(role);
        UserRole userRole = userRoleRepository.save(new UserRole(user, role));
        user = user.addRoles(Roles.of(userRole));
        // todo: 이거 없어도 업데이트 되는지 보기
        return userRepository.save(user);
    }

}
