package ua.lomakin.atm.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class MainController {


    @PostMapping()
    public Object main() throws JsonProcessingException {
        String message = "{\"message\" : \"Соединение установлено!\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        Object json = objectMapper.readValue(message, Object.class);

        return json;
    }
}
