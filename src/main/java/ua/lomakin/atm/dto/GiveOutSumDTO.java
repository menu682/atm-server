package ua.lomakin.atm.dto;

public class GiveOutSumDTO {

    Integer sum;
    Long cartNumber;
    String cartPin;
    String balance;

    public GiveOutSumDTO() {
    }

    public GiveOutSumDTO(Integer sum, Long cartNumber, String cartPin, String balance) {
        this.sum = sum;
        this.cartNumber = cartNumber;
        this.cartPin = cartPin;
        this.balance = balance;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
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

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
