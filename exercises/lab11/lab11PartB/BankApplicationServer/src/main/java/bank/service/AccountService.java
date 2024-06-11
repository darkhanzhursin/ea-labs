package bank.service;

import bank.dto.request.DepositRequest;
import bank.dto.response.AccountDTO;

import java.util.Collection;

public interface AccountService {

    public AccountDTO createAccount(long accountNumber, String customerName);

    public AccountDTO getAccount(long accountNumber);

    public Collection<AccountDTO> getAllAccounts();

    public void deposit (long accountNumber, DepositRequest depositRequest);

    public void withdraw (long accountNumber, double amount);

    public void depositEuros (long accountNumber, DepositRequest depositRequest);

    public void withdrawEuros (long accountNumber, double amount);

    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description);

}
