package bank.service;

import bank.domain.Customer;
import bank.domain.account.Account;
import bank.domain.account.AccountDTO;
import bank.integration.jms.JMSSender;
import bank.integration.logging.Logger;
import bank.repository.AccountRepository;
import bank.service.account.AccountAdapter;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private CurrencyConverter currencyConverter;
    private JMSSender jmsSender;
    private Logger logger;

    public AccountServiceImpl(AccountRepository accountRepository, CurrencyConverter currencyConverter, JMSSender jmsSender, Logger logger) {
        this.accountRepository = accountRepository;
        this.currencyConverter = currencyConverter;
        this.jmsSender = jmsSender;
        this.logger = logger;
    }

    @Override
    public AccountDTO createAccount(long accountNumber, String customerName) {
        Customer customer = new Customer(customerName);
        Account account = new Account(accountNumber);
        accountRepository.save(account);
        return AccountAdapter.getAccountDTOFromAccount(account);
    }

    @Override
    public AccountDTO getAccount(long accountNumber) throws SQLException {
        Optional<Account> account = accountRepository.findById(accountNumber);
        if (!account.isPresent()) throw new SQLException("Account not found!");
        return AccountAdapter.getAccountDTOFromAccount(account.get());
    }

    @Override
    public Collection<Account> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    @Override
    public void deposit(long accountNumber, double amount) throws SQLException {
        Optional<Account> accountOpt = accountRepository.findById(accountNumber);
        if (!accountOpt.isPresent()) throw new SQLException("Account not found!");
        Account account = accountOpt.get();
        account.deposit(amount);
        accountRepository.save(account);
        logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
        if (amount > 10000){
            jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
        }
    }

    @Override
    public void withdraw(long accountNumber, double amount) throws SQLException {
        Optional<Account> accountOpt = accountRepository.findById(accountNumber);
        if (!accountOpt.isPresent()) throw new SQLException("Account not found!");
        Account account = accountOpt.get();
        account.withdraw(amount);
        accountRepository.save(account);
        logger.log("withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount);
    }

    @Override
    public void depositEuros(long accountNumber, double amount) throws SQLException {
        Optional<Account> accountOpt = accountRepository.findById(accountNumber);
        if (!accountOpt.isPresent()) throw new SQLException("Account not found!");
        double amountDollars = currencyConverter.euroToDollars(amount);
        Account account = accountOpt.get();
        account.deposit(amountDollars);
        accountRepository.save(account);
        logger.log("depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
        if (amountDollars > 10000){
            jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
        }
    }

    @Override
    public void withdrawEuros(long accountNumber, double amount) throws SQLException {
        Optional<Account> accountOpt = accountRepository.findById(accountNumber);
        if (!accountOpt.isPresent()) throw new SQLException("Account not found!");
        double amountDollars = currencyConverter.euroToDollars(amount);
        Account account = accountOpt.get();
        account.withdraw(amountDollars);
        accountRepository.save(account);
        logger.log("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);

    }

    @Override
    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
        Optional<Account> fromAccountOpt = accountRepository.findById(fromAccountNumber);
        Optional<Account> toAccountOpt = accountRepository.findById(toAccountNumber);
        Account fromAccount = fromAccountOpt.get();
        Account toAccount = toAccountOpt.get();
        fromAccount.transferFunds(toAccount, amount, description);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        logger.log("transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description);
        if (amount > 10000){
            jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
        }
    }
}
