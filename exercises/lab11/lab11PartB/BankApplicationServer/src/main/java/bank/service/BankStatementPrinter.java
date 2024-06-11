package bank.service;

import bank.dto.response.AccountDTO;
import bank.events.ChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class BankStatementPrinter {

    @Autowired
    private AccountService accountService;

    @Scheduled(fixedRate = 20000)
    public void print() {
        Collection<AccountDTO> list = accountService.getAllAccounts();
        for (AccountDTO account : list) {
            System.out.println(account.getAccountNumber());
            System.out.println(account.getCustomerName());
        }
        System.out.println("Bank statement printer");
    }
}
