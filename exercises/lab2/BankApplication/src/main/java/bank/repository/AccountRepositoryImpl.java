package bank.repository;

import bank.domain.Account;
import bank.integration.logging.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    Collection<Account> accountlist = new ArrayList<>();

    private Logger logger;

    public AccountRepositoryImpl(Logger logger) {
        this.logger = logger;
    }

    public void saveAccount(Account account) {
        logger.log("AccountDAO: saving account with accountnr =" + account.getAccountNumber());
        accountlist.add(account); // add the new
    }

    public void updateAccount(Account account) {
        logger.log("AccountDAO: update account with accountnr =" + account.getAccountNumber());
        Account accountexist = loadAccount(account.getAccountNumber());
        if (accountexist != null) {
            accountlist.remove(accountexist); // remove the old
            accountlist.add(account); // add the new
        }

    }

    public Account loadAccount(long accountNumber) {
        logger.log("AccountDAO: loading account with accountnr =" + accountNumber);
        for (Account account : accountlist) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public Collection<Account> getAccounts() {
        logger.log("AccountDAT: get all accounts");
        return accountlist;
    }

}
