package ua.lomakin.atm.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class UserEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<UserRolesEntity> userRoles;


}
