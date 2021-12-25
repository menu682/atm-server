package ua.lomakin.atm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "carts")
public class CartEntity extends BaseEntity {

    @Column(name = "number", nullable = false)
    private Long cartNumber;

    @Column(name = "pin", nullable = false)
    private String cartPin;

    @Column(name = "debit")
    private Long debitBalance;

    @Column(name = "credit")
    private Long creditBalance;

    @ManyToOne
    private UserEntity user;

    public CartEntity() {
    }

    public CartEntity(Long id,
                      LocalDateTime created,
                      LocalDateTime update,
                      Long cartNumber,
                      String cartPin,
                      Long debitBalance,
                      Long creditBalance,
                      UserEntity user) {
        super(id, created, update);
        this.cartNumber = cartNumber;
        this.cartPin = cartPin;
        this.debitBalance = debitBalance;
        this.creditBalance = creditBalance;
        this.user = user;
    }

    public Long getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(Long cartNumber) {
        this.cartNumber = cartNumber;
    }

    public String getCartPin() {
        return cartPin;
    }

    public void setCartPin(String cartPin) {
        this.cartPin = cartPin;
    }

    public Long getDebitBalance() {
        return debitBalance;
    }

    public void setDebitBalance(Long debitBalance) {
        this.debitBalance = debitBalance;
    }

    public Long getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(Long creditBalance) {
        this.creditBalance = creditBalance;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
