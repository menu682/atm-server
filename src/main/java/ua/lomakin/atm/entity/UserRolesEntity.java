package ua.lomakin.atm.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users_roles")
@Data
public class UserRolesEntity extends BaseEntity{

    @Column(name = "user_id")
    private long userId;

    @Column(name = "role_id")
    private long roleId;
}
