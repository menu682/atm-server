package ua.lomakin.atm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_roles")
public class UserRolesEntity extends BaseEntity{

    @Column(name = "user_id")
    private long userId;

    @Column(name = "role_id")
    private long roleId;

    public UserRolesEntity() {
    }

    public UserRolesEntity(Long id,
                           LocalDateTime created,
                           LocalDateTime update,
                           long userId,
                           long roleId) {
        super(id, created, update);
        this.userId = userId;
        this.roleId = roleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
