package bank.service;

import bank.domain.account.Account;
import bank.domain.account.AccountDTO;

import java.sql.SQLException;
import java.util.Collection;

public interface AccountService {

    public AccountDTO createAccount(long accountNumber, String customerName);

    public AccountDTO getAccount(long accountNumber) throws SQLException;

    public Collection<Account> getAllAccounts();

    public void deposit (long accountNumber, double amount) throws SQLException;

    public void withdraw (long accountNumber, double amount) throws SQLException;

    public void depositEuros (long accountNumber, double amount) throws SQLException;

    public void withdrawEuros (long accountNumber, double amount) throws SQLException;

    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description);

}
