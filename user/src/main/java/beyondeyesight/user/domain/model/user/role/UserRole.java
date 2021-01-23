package beyondeyesight.user.domain.model.user.role;

import beyondeyesight.user.domain.model.BaseEntity;
import beyondeyesight.user.domain.model.user.User;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "user_role")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRole extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    // default
    public UserRole(User user, Role role) {
        // todo: check! nullable로 이미 db 레이어에서 검증이 있기에 중복...? 없는 게 나을지 확인
        validate(user, role);
        this.user = user;
        this.role = role;
    }

    private void validate(User user, Role role) {
        if (Objects.isNull(user) || Objects.isNull(role)) {
            throw new IllegalArgumentException(String.format("%s 혹은 %s가 null 입니다.", user, role));
        }
    }

    @Override
    public String toString() {
        return "UserRole{" +
            ", role=" + role +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        UserRole userRole = (UserRole) o;
        return Objects.equals(user, userRole.user) &&
            Objects.equals(role, userRole.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, role);
    }
}
