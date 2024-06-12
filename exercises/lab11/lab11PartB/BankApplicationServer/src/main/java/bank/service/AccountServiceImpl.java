package bank.service;

import bank.domain.Account;
import bank.domain.Customer;
import bank.dto.request.DepositRequest;
import bank.dto.response.AccountDTO;
import bank.integration.jms.JMSSender;
import bank.integration.logging.Logger;
import bank.repository.AccountRepository;
import bank.service.events.event.*;
import bank.service.mapper.AccountAdapter;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private CurrencyConverter currencyConverter;
    private JMSSender jmsSender;
    private Logger logger;
    private ApplicationEventPublisher eventPublisher;

    public AccountServiceImpl(AccountRepository accountRepository, CurrencyConverter currencyConverter, JMSSender jmsSender,
                              Logger logger, ApplicationEventPublisher eventPublisher) {
        this.accountRepository = accountRepository;
        this.currencyConverter = currencyConverter;
        this.jmsSender = jmsSender;
        this.logger = logger;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public AccountDTO createAccount(long accountNumber, String customerName) {
        Customer customer = new Customer(customerName);
        Account account = new Account(accountNumber);
        account.setCustomer(customer);
        accountRepository.save(account);
        logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
        eventPublisher.publishEvent(new ChangeEvent("Created account"));
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
        eventPublisher.publishEvent(new ChangeEvent("get all accounts"));
        return AccountAdapter.toDTOs(accounts);
    }

    @Override
    public void deposit(long accountNumber, DepositRequest depositRequest) {
        Double amount = depositRequest.getAmount();
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow();
        account.deposit(amount);
        accountRepository.save(account);
        eventPublisher.publishEvent(new ChangeEvent("deposit account"));
        logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
        if (amount > 10000){
            jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
        }

        DepositEvent event = new DepositEvent(account.getCustomer().getName(), accountNumber, amount);
        eventPublisher.publishEvent(event);
    }

    @Override
    public void withdraw(long accountNumber, double amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow();
        account.withdraw(amount);
        accountRepository.save(account);
        logger.log("withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount);
        eventPublisher.publishEvent(new WithdrawEvent(account.getCustomer().getName(), accountNumber, amount));
    }

    @Override
    public void depositEuros(long accountNumber, DepositRequest depositRequest) {
        double amount = depositRequest.getAmount();
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow();
        double amountDollars = currencyConverter.euroToDollars(amount);
        account.deposit(amountDollars);
        accountRepository.save(account);
        eventPublisher.publishEvent(new ChangeEvent("deposit account"));
        logger.log("depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
        if (amountDollars > 10000){
            jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
        }

        DepositEurosEvent event = new DepositEurosEvent(account.getCustomer().getName(), accountNumber, amount);
        eventPublisher.publishEvent(event);
    }

    @Override
    public void withdrawEuros(long accountNumber, double amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow();
        double amountDollars = currencyConverter.euroToDollars(amount);
        account.withdraw(amountDollars);
        accountRepository.save(account);
        logger.log("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);

        WithdrawEurosEvent event = new WithdrawEurosEvent(account.getCustomer().getName(), accountNumber, amount);
        eventPublisher.publishEvent(event);
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

        TransferFundsEvent transferFundsEvent = new TransferFundsEvent(
                fromAccountNumber,
                fromAccount.getCustomer().getName(),
                toAccountNumber,
                toAccount.getCustomer().getName(),
                amount
        );
        eventPublisher.publishEvent(transferFundsEvent);
    }
}
