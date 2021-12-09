package ua.lomakin.atm.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MoneyDTO {

    private Long id;
    private int bill;
    private int amount;

}
