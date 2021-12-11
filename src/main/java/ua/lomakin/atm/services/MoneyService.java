package ua.lomakin.atm.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lomakin.atm.dto.CartReqDTO;
import ua.lomakin.atm.dto.CartResDTO;
import ua.lomakin.atm.dto.GiveOutSumDTO;
import ua.lomakin.atm.dto.MoneyDTO;
import ua.lomakin.atm.dto.MoneyReqResDTO;
import ua.lomakin.atm.dto.MultipleResDTO;
import ua.lomakin.atm.entity.CartEntity;
import ua.lomakin.atm.entity.MoneyEntity;
import ua.lomakin.atm.repo.CartRepo;
import ua.lomakin.atm.repo.MoneyRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Transactional
public class MoneyService {

    MoneyRepo moneyRepo;
    CartRepo cartRepo;
    CartService cartService;

    public MoneyService(MoneyRepo moneyRepo, CartService cartService, CartRepo cartRepo) {

        this.moneyRepo = moneyRepo;
        this.cartRepo = cartRepo;
        this.cartService = cartService;
    }


    public List<MoneyEntity> getAllMoneyFromDB() {
        return moneyRepo.findAll();
    }

    public void updateAmountBills(MoneyEntity moneyEntity, int bill, int deltaAmount) {
        if (bill == moneyEntity.getBill()) {
            moneyEntity.setAmount(moneyEntity.getAmount() - deltaAmount);
        }
    }

    public MoneyDTO convertMoneyEntityToMoneyDTO(MoneyEntity moneyEntity) {
        MoneyDTO moneyDTO = new MoneyDTO();
        moneyDTO.setId(moneyEntity.getId());
        moneyDTO.setBill(moneyEntity.getBill());
        moneyDTO.setAmount(moneyEntity.getAmount());
        return moneyDTO;
    }

    public MoneyReqResDTO getMoney(GiveOutSumDTO giveOutSumDTO) {

        int withdrawalAmount = giveOutSumDTO.getSum();

        List<MoneyEntity> allMoneyInATM = getAllMoneyFromDB();

        List<MoneyDTO> moneyDTOList = new ArrayList<>();
        MoneyReqResDTO moneyReqResDTO = new MoneyReqResDTO();//return

        //посчитаем все деньги в банкомате
        List<Integer> billsInAtm = new ArrayList<>();
        AtomicInteger moneyInATM = new AtomicInteger();

        allMoneyInATM.stream().forEach(moneyEntity -> {
            if (moneyEntity.getAmount() > 0) {
                billsInAtm.add(moneyEntity.getBill());
                moneyInATM.addAndGet(moneyEntity.getBill() * moneyEntity.getAmount());
            }
        });

        if (billsInAtm.size() == 0) {
            moneyReqResDTO.setMessage("В банкомате нет денег");
            return moneyReqResDTO;
        }
        int sumMoneyInATM = moneyInATM.intValue();

        //сортируем массив доступных купюр
        Collections.sort(billsInAtm);

        //проверяем кратность
        int multiple = billsInAtm.get(0);
        if (withdrawalAmount % multiple != 0) {
            moneyReqResDTO.setMessage("Ошибка: введите сумму кратную " + multiple);
            return moneyReqResDTO;
        }

        //проверяем сумму
        if (sumMoneyInATM < withdrawalAmount) {
            moneyReqResDTO.setMessage("Невозможно выдать такую сумму.");
            return moneyReqResDTO;
        }

        //Списываем деньги с карты
        CartReqDTO cartReqDTO = new CartReqDTO();
        cartReqDTO.setCartNumber(giveOutSumDTO.getCartNumber());
        cartReqDTO.setCartPin(giveOutSumDTO.getCartPin());
        CartEntity cartEntity = cartService.getCartEntity(cartReqDTO);

        switch (giveOutSumDTO.getBalance()) {
            case "credit":
                if (cartEntity.getCreditBalance() < withdrawalAmount) {
                    moneyReqResDTO.setMessage("Не достаточно денег на этом счёте");
                    return moneyReqResDTO;
                } else {
                    cartEntity.setCreditBalance(cartEntity.getCreditBalance() - withdrawalAmount);
                }
                break;
            case "debit":
                if (cartEntity.getDebitBalance() < withdrawalAmount) {
                    moneyReqResDTO.setMessage("Не достаточно денег на этом счёте");
                    return moneyReqResDTO;
                } else {
                    cartEntity.setDebitBalance(cartEntity.getDebitBalance() - withdrawalAmount);
                }
                break;
            default:
                moneyReqResDTO.setMessage("Не верно выбран счёт");
                return moneyReqResDTO;
        }
        cartRepo.save(cartEntity);

        //выдаём деньги начиная с большей купюры
        Collections.reverse(billsInAtm);
        AtomicInteger calculateSum = new AtomicInteger(withdrawalAmount);
        AtomicInteger allGetBillCounter = new AtomicInteger();
        billsInAtm.forEach(bill -> {
            int billCount;
            if (calculateSum.get() >= bill) {
                billCount = calculateSum.get() / bill;
                allMoneyInATM.forEach(moneyEntity -> {
                    if (moneyEntity.getBill() == bill && moneyEntity.getAmount() >= billCount) {
                        updateAmountBills(moneyEntity, bill, billCount);
                        MoneyDTO moneyDTO = convertMoneyEntityToMoneyDTO(moneyEntity);
                        moneyDTO.setAmount(billCount);
                        moneyDTOList.add(moneyDTO);
                        calculateSum.addAndGet(-bill * billCount);
                        allGetBillCounter.addAndGet(billCount);
                    }
                });
            }
        });

        if (moneyDTOList.size() == 0 || allGetBillCounter.get() > 40) {
            moneyReqResDTO.setMessage("Нельзя выдать такую сумму за один раз");
            return moneyReqResDTO;
        }



        moneyRepo.saveAll(allMoneyInATM);

        moneyReqResDTO.setMoneyDTOList(moneyDTOList);
        moneyReqResDTO.setMessage("Деньги выданы");
        return moneyReqResDTO;
    }

    public MultipleResDTO getMultiple() {

        MultipleResDTO multipleResDTO = new MultipleResDTO();
        multipleResDTO.setMultiple(moneyRepo.getMultiple());
        return multipleResDTO;
    }


    public MoneyReqResDTO getAllMoneyRes() {

        List<MoneyEntity> moneyEntityList = getAllMoneyFromDB();

        List<MoneyDTO> moneyDTOList = moneyEntityList.stream().map(moneyEntity -> {
            MoneyDTO moneyDTO = convertMoneyEntityToMoneyDTO(moneyEntity);
            return moneyDTO;
        }).collect(Collectors.toList());

        String message = "";
        MoneyReqResDTO moneyReqResDTO = new MoneyReqResDTO();
        moneyReqResDTO.setMoneyDTOList(moneyDTOList);
        moneyReqResDTO.setMessage(message);

        return moneyReqResDTO;
    }

    public MoneyReqResDTO addMoney(MoneyReqResDTO moneyReqResDTO) {

        List<MoneyDTO> moneyDTOList = moneyReqResDTO.getMoneyDTOList();
        List<MoneyEntity> moneyEntityListInDB = getAllMoneyFromDB();

        moneyDTOList.forEach(moneyDTO -> moneyEntityListInDB.forEach(moneyEntity -> {
            if (moneyDTO.getBill() == moneyEntity.getBill()) {
                moneyEntity.setAmount(moneyEntity.getAmount() + moneyDTO.getAmount());
            }
        }));

        moneyRepo.saveAll(moneyEntityListInDB);

        List<MoneyDTO> resMoneyDTOList = new ArrayList<>();
        moneyEntityListInDB.forEach(moneyEntity -> moneyDTOList.forEach(moneyDTO -> {
            if (moneyDTO.getBill() == moneyEntity.getBill()) {
                moneyDTO.setAmount(moneyEntity.getAmount());
                resMoneyDTOList.add(moneyDTO);
            }
        }));

        MoneyReqResDTO sendMoneyReqResDTO = new MoneyReqResDTO();
        sendMoneyReqResDTO.setMoneyDTOList(resMoneyDTOList);
        sendMoneyReqResDTO.setMessage("Ура! Деньги внесены!");
        return sendMoneyReqResDTO;
    }

}
