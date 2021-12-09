package ua.lomakin.atm.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "money")
@Data
public class MoneyEntity extends BaseEntity {

    @Column(name = "bill", nullable = false)
    private int bill;

    @Column(name = "amount")
    private int amount;

}
