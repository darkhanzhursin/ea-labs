package bank.service.events;

import bank.service.EmailSender;
import bank.service.events.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EmailClientOnAccountChange {

    private final EmailSender emailSender;

    public EmailClientOnAccountChange(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @EventListener
    public void onEvent(DepositEvent event) {
        String message = String.format(
                "Hi %s, Your account has been credited %f dollars",
                event.customerName(),
                event.amount()
        );
        emailSender.sendEmail("user@domain", message);
    }

    @EventListener
    public void onEvent(DepositEurosEvent event) {
        String message = String.format(
                "Hi %s, Your account has been credited %f euros",
                event.customerName(),
                event.amount()
        );
        emailSender.sendEmail("user@domain", message);
    }

    @EventListener
    public void onEvent(WithdrawEvent event) {
        String message = String.format(
                "Hi %s, You have withdrawn %f dollars",
                event.customerName(),
                event.amount()
        );
        emailSender.sendEmail("user@domain", message);
    }

    @EventListener
    public void onEvent(WithdrawEurosEvent event) {
        String message = String.format(
                "Hi %s, You have withdrawn %f euros",
                event.customerName(),
                event.amount()
        );
        emailSender.sendEmail("user@domain", message);
    }

    @EventListener
    public void onEvent(TransferFundsEvent event) {
        String sendingCustomerMessage = String.format(
                "Hi %s, You have successfully sent %f dollars to %s",
                event.fromCustomerName(),
                event.amount(),
                event.toCustomerName()
        );
        emailSender.sendEmail("from@domain", sendingCustomerMessage);

        String receivingCustomerMessage = String.format(
                "Hi %s, You have successfully received %f dollars from %s",
                event.toCustomerName(),
                event.amount(),
                event.fromCustomerName()
        );
        emailSender.sendEmail("to@domain", receivingCustomerMessage);
    }
}
