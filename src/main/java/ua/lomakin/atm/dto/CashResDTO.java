package ua.lomakin.atm.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CashResDTO {

    private MoneyDTO moneyDTO;
    private Long cash;
    private String message;

}
