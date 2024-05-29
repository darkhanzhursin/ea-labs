package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    @Autowired
    private Greeting greeting;

    public String getTheGreeting() {
        return greeting.getGreeting();
    }
}
