package ua.lomakin.atm.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CartResDTO {

    private Long id;
    private Long cartNumber;
    private Long debitBalance;
    private Long creditBalance;
    private String message;
    private String userName;

}
