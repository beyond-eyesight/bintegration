package beyondeyesight.user.domain;

import org.springframework.security.core.GrantedAuthority;

class UserGrantedAuthority implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return null;
    }
}
