package ua.lomakin.atm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<UserRolesEntity> userRoles;

    public UserEntity() {
    }

    public UserEntity(Long id,
                      LocalDateTime created,
                      LocalDateTime update,
                      String name,
                      String password,
                      List<UserRolesEntity> userRoles) {
        super(id, created, update);
        this.name = name;
        this.password = password;
        this.userRoles = userRoles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRolesEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRolesEntity> userRoles) {
        this.userRoles = userRoles;
    }
}
