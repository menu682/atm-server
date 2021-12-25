package ua.lomakin.atm.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "role_id")
    private List<UserRolesEntity> userRoles;

    public RoleEntity() {
    }

    public RoleEntity(Long id,
                      LocalDateTime created,
                      LocalDateTime update,
                      String name,
                      List<UserRolesEntity> userRoles) {
        super(id, created, update);
        this.name = name;
        this.userRoles = userRoles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserRolesEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRolesEntity> userRoles) {
        this.userRoles = userRoles;
    }
}
