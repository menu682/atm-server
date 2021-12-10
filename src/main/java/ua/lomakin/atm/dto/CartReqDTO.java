package ua.lomakin.atm.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CartReqDTO {

    private Long cartNumber;
    private String cartPin;

}
