package accounts.service;

import accounts.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accounts.repo.AccountRepository;

import java.util.Optional;


@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public AccountResponse getAccount(String accountNumber) {
        Optional<Account> accountOptional = accountRepository.findById(accountNumber);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            AccountResponse accountResponse = AccountAdapter.getAccountResponse(account);
            return accountResponse;
        }
        return null;
    }

    public void createAccount(String accountNumber, double amount, String accountHolder) {
        accountRepository.save(new Account(accountNumber, amount, accountHolder));
    }
}
