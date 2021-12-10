package ua.lomakin.atm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lomakin.atm.entity.CartEntity;

import java.util.Optional;

public interface CartRepo extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByCartNumber(Long cartNumber);

}
