package ua.lomakin.atm.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class RoleEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "role_id")
    private List<UserRolesEntity> userRoles;

}
