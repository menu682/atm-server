package ua.lomakin.atm.dto;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class MoneyReqResDTO {

    private List<MoneyDTO> moneyDTOList;
    private String message;

}
