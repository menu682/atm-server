package ua.lomakin.atm.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class GiveOutSumDTO {

    Integer sum;
    Long cartNumber;
    String cartPin;
    String balance;

}
