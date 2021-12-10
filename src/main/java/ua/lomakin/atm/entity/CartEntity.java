package ua.lomakin.atm.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carts")
@Data
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

}
