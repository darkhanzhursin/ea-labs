package demo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("Two")
public class GreetingTwo implements Greeting {

    @Override
    public String getGreeting() {
        return "Hello";
    }
}
