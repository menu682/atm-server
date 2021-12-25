package ua.lomakin.atm.dto;

public class MoneyDTO {

    private Long id;
    private int bill;
    private int amount;

    public MoneyDTO() {
    }

    public MoneyDTO(Long id, int bill, int amount) {
        this.id = id;
        this.bill = bill;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
