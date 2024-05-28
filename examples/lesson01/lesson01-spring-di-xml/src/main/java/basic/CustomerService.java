package basic;

import org.springframework.beans.factory.annotation.Autowired;

public class CustomerService {

    private EmailService emailService;

    public void addCustomer() {
        emailService.sendEmail();
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
