package ua.lomakin.atm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lomakin.atm.dto.CartReqDTO;
import ua.lomakin.atm.dto.CartResDTO;
import ua.lomakin.atm.dto.GiveOutSumDTO;
import ua.lomakin.atm.dto.MoneyReqResDTO;
import ua.lomakin.atm.dto.MultipleResDTO;
import ua.lomakin.atm.services.CartService;
import ua.lomakin.atm.services.MoneyService;

import java.util.List;

@RestController
@RequestMapping("/getmoney")
public class GiveOutMoneyConrtoller {

    Logger LOGGER = LoggerFactory.getLogger(AddMoneyController.class);

    private CartService cartService;
    private MoneyService moneyService;

    public GiveOutMoneyConrtoller(CartService cartService, MoneyService moneyService) {
        this.cartService = cartService;
        this.moneyService = moneyService;
    }

    @PostMapping("/getallcarts")
    public List<CartResDTO> getAllCarts() {
        LOGGER.debug("Все карты получены");
        return cartService.getAllCarts();
    }

    @PostMapping("/getcart")
    public CartResDTO getCart(@RequestBody CartReqDTO cartReqDTO) {
        return cartService.getCart(cartReqDTO);
    }

    @PostMapping("/getmultiple")
    public MultipleResDTO getMultiple() {
        return moneyService.getMultiple();
    }

    @PostMapping("/givemoney")
    public MoneyReqResDTO getMoney(@RequestBody GiveOutSumDTO giveOutSumDTO) {
        return moneyService.getMoney(giveOutSumDTO);
    }


}
