package ua.lomakin.atm.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lomakin.atm.dto.CartReqDTO;
import ua.lomakin.atm.dto.CartResDTO;
import ua.lomakin.atm.entity.CartEntity;
import ua.lomakin.atm.repo.CartRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartService {

    CartRepo cartRepo;

    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    public List<CartResDTO> getAllCarts(){

        List<CartEntity> cartEntityList = cartRepo.findAll();

        List<CartResDTO> cartResDTOList = cartEntityList.stream().map(cartEntity -> {
            CartResDTO cartResDTO = new CartResDTO();
            cartResDTO.setId(cartEntity.getId());
            cartResDTO.setCartNumber(cartEntity.getCartNumber());
            cartResDTO.setMessage("Подсказка: пин код карты - " + cartEntity.getCartPin());
            cartResDTO.setUserName(cartEntity.getUser().getName());
            return cartResDTO;
        }).collect(Collectors.toList());

        return cartResDTOList;
    }

    public CartResDTO getCart(CartReqDTO cartReqDTO){

        CartResDTO cartResDTO = new CartResDTO();

        Optional<CartEntity> optionalCartEntity = cartRepo.findByCartNumber(cartReqDTO.getCartNumber());
        try{
            CartEntity cartEntity = optionalCartEntity.get();

            if(!cartEntity.getCartPin().equals(cartReqDTO.getCartPin())){
                cartResDTO.setMessage("Не верный pin код!");
                return cartResDTO;
            }

            cartResDTO.setId(cartEntity.getId());
            cartResDTO.setCartNumber(cartEntity.getCartNumber());
            cartResDTO.setCartPin(cartEntity.getCartPin());
            cartResDTO.setCreditBalance(cartEntity.getCreditBalance());
            cartResDTO.setDebitBalance(cartEntity.getDebitBalance());
            cartResDTO.setUserName(cartEntity.getUser().getName());
            cartResDTO.setMessage("Карта найдена");
            return cartResDTO;

        }catch (NoSuchElementException e){
            cartResDTO.setMessage("Такой карты не существует!");
            return cartResDTO;
        }
    }

    public CartEntity getCartEntity(CartReqDTO cartReqDTO){

        Optional<CartEntity> optionalCartEntity = cartRepo.findByCartNumber(cartReqDTO.getCartNumber());
        try{
            CartEntity cartEntity = optionalCartEntity.get();

            if(!cartEntity.getCartPin().equals(cartReqDTO.getCartPin())){
                throw new NoSuchElementException();
            }

            return cartEntity;

        }catch (NoSuchElementException e){
            throw new NoSuchElementException("Не верный пин код или такой карты не существует!");
        }
    }

}
