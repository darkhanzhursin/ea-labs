package basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CustomerService customerService() {
        CustomerService customerService = new CustomerService();
        customerService.setEmailService(emailService());
        return customerService;
    }

    @Bean
    public EmailService emailService() {
        return new EmailService();
    }
}
