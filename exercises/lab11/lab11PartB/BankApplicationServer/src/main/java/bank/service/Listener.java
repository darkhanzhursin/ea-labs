package bank.service;

import bank.events.ChangeEvent;
import bank.repository.EventTraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class Listener {

    @Autowired
    private EventTraceRepository eventTraceRepository;

    @EventListener
    public void onEvent(ChangeEvent event) {
        System.out.println("received event: " + event.getMessage());

    }
}
