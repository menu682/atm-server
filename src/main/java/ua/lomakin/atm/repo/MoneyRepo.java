package ua.lomakin.atm.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.lomakin.atm.entity.MoneyEntity;


public interface MoneyRepo extends JpaRepository<MoneyEntity, Long> {

    @Query(value = "select bill from money where amount > 0 order by bill asc limit 1", nativeQuery = true)
    Integer getMultiple();

}
