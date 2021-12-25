package ua.lomakin.atm.dto;


public class CartReqDTO {

    private Long cartNumber;
    private String cartPin;

    public CartReqDTO() {
    }

    public CartReqDTO(Long cartNumber, String cartPin) {
        this.cartNumber = cartNumber;
        this.cartPin = cartPin;
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




}
