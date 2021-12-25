package ua.lomakin.atm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "money")
public class MoneyEntity extends BaseEntity {

    @Column(name = "bill", nullable = false)
    private int bill;

    @Column(name = "amount")
    private int amount;

    public MoneyEntity() {
    }

    public MoneyEntity(Long id, LocalDateTime created, LocalDateTime update, int bill, int amount) {
        super(id, created, update);
        this.bill = bill;
        this.amount = amount;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
