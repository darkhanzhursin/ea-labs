package demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AccountServiceImpl implements AccountService {

    Collection<Account> accountList = new ArrayList<>();

    @Override
    public void addAccount(String accountNumber, Customer customer) {
        Account account = new Account(accountNumber, customer);
        accountList.add(account);
        System.out.println("in execution of methof addAccount");
    }
}
