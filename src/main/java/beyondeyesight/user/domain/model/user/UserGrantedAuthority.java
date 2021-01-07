package beyondeyesight.user.domain.model.user;

import org.springframework.security.core.GrantedAuthority;

class UserGrantedAuthority implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return null;
    }
}
