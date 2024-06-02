package bank.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Aspect
@Configuration
public class DAOLogAdvice {
    @After("execution(* bank.repository.*(..))")
    public void logDAOCall(JoinPoint joinPoint) {
        //AccountRepositoryImpl accountRepository = (AccountRepositoryImpl) joinPoint.getTarget();
        System.out.println(LocalDateTime.now() + " method=" + joinPoint.getSignature().getName());
    }
}
