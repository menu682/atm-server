package ua.lomakin.atm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lomakin.atm.dto.MoneyReqResDTO;
import ua.lomakin.atm.services.MoneyService;

@RestController()
@RequestMapping("/addmoney")
public class AddMoneyController {

    Logger LOGGER = LoggerFactory.getLogger(AddMoneyController.class);

    MoneyService moneyService;

    public AddMoneyController(MoneyService moneyService) {
        this.moneyService = moneyService;
    }


    @PostMapping("/getbills")
    public MoneyReqResDTO getBills(){
        LOGGER.info("get all money from DB");
        return moneyService.getAllMoneyRes();
    }

    @PostMapping("/add")
    public MoneyReqResDTO addMoney(@RequestBody MoneyReqResDTO moneyReqResDTO) {
        MoneyReqResDTO sendMoneyReqResDTO = moneyService.addMoney(moneyReqResDTO);
        LOGGER.info("money added");
        return sendMoneyReqResDTO;
    }


}
