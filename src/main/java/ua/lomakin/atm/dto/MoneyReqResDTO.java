package ua.lomakin.atm.dto;


import java.util.List;

public class MoneyReqResDTO {

    private List<MoneyDTO> moneyDTOList;
    private String message;

    public MoneyReqResDTO() {
    }

    public MoneyReqResDTO(List<MoneyDTO> moneyDTOList, String message) {
        this.moneyDTOList = moneyDTOList;
        this.message = message;
    }

    public List<MoneyDTO> getMoneyDTOList() {
        return moneyDTOList;
    }

    public void setMoneyDTOList(List<MoneyDTO> moneyDTOList) {
        this.moneyDTOList = moneyDTOList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
