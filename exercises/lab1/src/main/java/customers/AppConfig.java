package customers;

import customers.integration.ems.EmailSender;
import customers.integration.ems.EmailSenderImpl;
import customers.integration.logging.Logger;
import customers.integration.logging.LoggerImpl;
import customers.repository.CustomerRepository;
import customers.repository.CustomerRepositoryImpl;
import customers.service.CustomerService;
import customers.service.CustomerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CustomerService customerService() {
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        customerService.setEmailSender(emailService());
        customerService.setCustomerRepository(customerRepository());
        return customerService;
    }

    @Bean
    public EmailSender emailService() {
        return new EmailSenderImpl(logger());
    }

    @Bean
    public CustomerRepository customerRepository() {
        return new CustomerRepositoryImpl(logger());
    }

    @Bean
    public Logger logger() {
        return new LoggerImpl();
    }
}
