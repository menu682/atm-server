package ua.lomakin.atm.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lomakin.atm.dto.MessageDTO;

@RestController("/")
public class MainController {

    MessageDTO messageDTO;

    public MainController(MessageDTO messageDTO) {
        this.messageDTO = messageDTO;
    }

    @PostMapping()
    public MessageDTO main(){
        messageDTO.setMessage("Соединение установлено!");
        return messageDTO;
    }
}
