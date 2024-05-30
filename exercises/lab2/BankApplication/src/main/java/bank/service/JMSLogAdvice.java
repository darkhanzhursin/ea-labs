package bank.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Aspect
@Configuration
public class JMSLogAdvice {

    @After("execution(* bank.integration.jms.JMSSenderImpl.sendJMSMessage(..)) && args(text)")
    public void logJMSMessage(String text) {
        System.out.println(LocalDateTime.now() + " message: " + text);
    }
}
