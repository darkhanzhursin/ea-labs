package bank.service.account;

import bank.domain.account.Account;
import bank.domain.account.AccountDTO;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter {
    public static Account getAccountFromAccountDTO(AccountDTO accountDTO) {
        Account account = new Account(accountDTO.getAccountNumber());
        account.setCustomer(accountDTO.getCustomer());
        return account;
    }

    public static AccountDTO getAccountDTOFromAccount(Account account) {
        AccountDTO accountDTO = new AccountDTO(account.getAccountNumber());
        accountDTO.setCustomer(account.getCustomer());
        return accountDTO;
    }

    public static List<AccountDTO> getAccountDTOsFromAccounts(List<Account> accounts) {
        List<AccountDTO> accountDTOs = new ArrayList<>();
        for (Account account : accounts) {
            accountDTOs.add(getAccountDTOFromAccount(account));
        }
        return accountDTOs;
    }
}
