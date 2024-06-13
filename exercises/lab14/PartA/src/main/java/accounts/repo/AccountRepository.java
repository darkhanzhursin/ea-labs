package accounts.repo;

import accounts.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

    Account findByAccountHolder(String accountHolder);

    List<Account> findByBalanceGreaterThan(Double amount);
    List<Account> findByBalanceEquals(Double amount);
}
