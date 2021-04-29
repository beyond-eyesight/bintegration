package beyondeyesight.user.domain.model.user.role;

import beyondeyesight.user.domain.model.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "role_privilege")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RolePrivilege extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "privilege_id", nullable = false)
    private Privilege privilege;

    public static RolePrivilege of(Role role, Privilege privilege) {
        return new RolePrivilege(role, privilege);
    }

    @Override
    public String toString() {
        return "RolePrivilege{" +
            ", privilege=" + privilege +
            '}';
    }
}
