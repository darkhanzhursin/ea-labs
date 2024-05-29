package contacts.integration;

import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    public void sendEmail(String emailAddress, String message) {
        System.out.println("Send email message '"+ message+"' to "+emailAddress);
    }
}
