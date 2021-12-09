package ua.lomakin.atm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "carts")
public class CartEntity extends BaseEntity {

    @Column(name = "number", nullable = false)
    private long cartNumber;

    @Column(name = "pin", nullable = false)
    private int CartPin;

    @Column(name = "debet")
    private Long debetBalance;

    @Column(name = "credit")
    private Long creditBalance;

}
