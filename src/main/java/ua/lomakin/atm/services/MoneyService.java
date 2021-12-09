package ua.lomakin.atm.services;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.lomakin.atm.dto.MoneyDTO;
import ua.lomakin.atm.dto.MoneyReqResDTO;
import ua.lomakin.atm.entity.MoneyEntity;
import ua.lomakin.atm.repo.MoneyRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class MoneyService {

    MoneyRepo moneyRepo;

    public MoneyService(MoneyRepo moneyRepo) {
        this.moneyRepo = moneyRepo;
    }


    public List<MoneyEntity> getAllMoneyFromDB(){
        return moneyRepo.findAll();
    }


    public MoneyReqResDTO getAllMoneyRes(){

        List<MoneyEntity> moneyEntityList = getAllMoneyFromDB();

        List<MoneyDTO> moneyDTOList = moneyEntityList.stream().map(moneyEntity -> {
            MoneyDTO moneyDTO = new MoneyDTO();
            moneyDTO.setId(moneyEntity.getId());
            moneyDTO.setBill(moneyEntity.getBill());
            moneyDTO.setAmount(moneyDTO.getAmount());
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
            if(moneyDTO.getBill() == moneyEntity.getBill()){
                moneyEntity.setAmount(moneyEntity.getAmount() + moneyDTO.getAmount());
            }
        }));

        moneyRepo.saveAll(moneyEntityListInDB);

        List<MoneyDTO> resMoneyDTOList = new ArrayList<>();
        moneyEntityListInDB.forEach(moneyEntity -> moneyDTOList.forEach(moneyDTO -> {
            if(moneyDTO.getBill() == moneyEntity.getBill()){
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
