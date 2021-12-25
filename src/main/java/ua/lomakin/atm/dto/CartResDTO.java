package ua.lomakin.atm.dto;

public class CartResDTO {

    private Long id;
    private Long cartNumber;
    private String cartPin;
    private Long debitBalance;
    private Long creditBalance;
    private String message;
    private String userName;

    public CartResDTO() {
    }

    public CartResDTO(Long id,
                      Long cartNumber,
                      String cartPin,
                      Long debitBalance,
                      Long creditBalance,
                      String message,
                      String userName) {
        this.id = id;
        this.cartNumber = cartNumber;
        this.cartPin = cartPin;
        this.debitBalance = debitBalance;
        this.creditBalance = creditBalance;
        this.message = message;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
