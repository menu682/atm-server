package ua.lomakin.atm.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MessageDTO {

    private String message;

    public MessageDTO() {
    }

    public MessageDTO(String message) {
        this.message = message;
    }
}
