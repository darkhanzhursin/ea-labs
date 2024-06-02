package bank.repository;

import bank.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyAccountRepository extends JpaRepository<Account, Long> {
}
