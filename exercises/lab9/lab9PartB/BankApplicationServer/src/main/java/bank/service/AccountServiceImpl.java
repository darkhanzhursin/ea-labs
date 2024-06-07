package bank.service;

import bank.domain.Account;
import bank.domain.Customer;
import bank.dto.request.DepositRequest;
import bank.dto.response.AccountDTO;
import bank.integration.jms.JMSSender;
import bank.integration.logging.Logger;
import bank.repository.AccountRepository;
import bank.service.mapper.AccountAdapter;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
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
        account.setCustomer(customer);
        accountRepository.save(account);
        logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
        return AccountAdapter.toDTO(account);
    }

    @Override
    public AccountDTO getAccount(long accountNumber) {
        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow();
        return AccountAdapter.toDTO(account);
    }

    @Override
    public Collection<AccountDTO> getAllAccounts() {
        Collection<Account> accounts = accountRepository.findAll();
        return AccountAdapter.toDTOs(accounts);
    }

    @Override
    public void deposit(long accountNumber, DepositRequest depositRequest) {
        Double amount = depositRequest.getAmount();
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow();
        account.deposit(amount);
        accountRepository.save(account);
        logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
        if (amount > 10000){
            jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
        }
    }

    @Override
    public void withdraw(long accountNumber, double amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow();
        account.withdraw(amount);
        accountRepository.save(account);
        logger.log("withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount);
    }

    @Override
    public void depositEuros(long accountNumber, DepositRequest depositRequest) {
        double amount = depositRequest.getAmount();
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow();
        double amountDollars = currencyConverter.euroToDollars(amount);
        account.deposit(amountDollars);
        accountRepository.save(account);
        logger.log("depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
        if (amountDollars > 10000){
            jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
        }
    }

    @Override
    public void withdrawEuros(long accountNumber, double amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow();
        double amountDollars = currencyConverter.euroToDollars(amount);
        account.withdraw(amountDollars);
        accountRepository.save(account);
        logger.log("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);

    }

    @Override
    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber).orElseThrow();
        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber).orElseThrow();
        fromAccount.transferFunds(toAccount, amount, description);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        logger.log("transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description);
        if (amount > 10000){
            jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
        }
    }
}
