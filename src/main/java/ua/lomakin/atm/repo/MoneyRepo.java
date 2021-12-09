package ua.lomakin.atm.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.lomakin.atm.entity.MoneyEntity;


public interface MoneyRepo extends JpaRepository<MoneyEntity, Long> {
}
