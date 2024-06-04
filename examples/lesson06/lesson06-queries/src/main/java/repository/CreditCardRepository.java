package repository;

import domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    CreditCard findByNumber(String number);

    CreditCard findByNumberAndName(String number, String name);

    List<CreditCard> findByName(String name);
}
