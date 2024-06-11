package jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageListener {

    @JmsListener(destination = "testQueue")
    public void receiveMessage(String message) {
        System.out.println("JMS receiver received message: " + message);
    }
}
