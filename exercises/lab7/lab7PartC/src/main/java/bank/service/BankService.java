package bank.service;

import bank.domain.Account;
import bank.domain.Customer;
import bank.domain.TraceRecord;
import bank.integration.EmailSender;
import bank.repository.AccountRepository;
import bank.repository.CustomerRepository;
import bank.repository.TraceRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BankService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TraceRecordRepository traceRecordRepository;

	@Autowired
	private EmailSender emailSender;
	
	@Transactional
	public void createCustomerAndAccount(int customerId, String customerName, String emailAddress,
										 String accountNumber){
		Account account = new Account(accountNumber);

		Customer customer = new Customer(customerId, customerName);
        customer.setAccount(account);
		try {
			accountRepository.save(account);
			customerRepository.saveCustomer(customer);

			TraceRecord traceRecord = new TraceRecord();
			traceRecord.setDateTime(LocalDateTime.now());
			traceRecord.setMessage("Customer " + customerName + " created with account " + accountNumber);
			traceRecordRepository.save(traceRecord);
		} catch (DataIntegrityViolationException e) {
			TraceRecord traceRecord = new TraceRecord();
			traceRecord.setDateTime(LocalDateTime.now());
			traceRecord.setMessage("Could not create customer " + customerName + " with account " + accountNumber);
			traceRecordRepository.save(traceRecord);
			throw e;
		}
		emailSender.sendEmail(emailAddress, "Welcome "+customerName);
	}

}
