package basic;

public class CustomerService {

    private EmailService emailService;

    public void addCustomer() {
        emailService.sendEmail();
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
