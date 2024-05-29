package demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TraceAdvise {
    @Before("execution(* demo.AccountServiceImpl.*(..))")
    public void traceBeforeMethod(JoinPoint joinPoint) {
        System.out.println("before execution of method " + joinPoint.getSignature().getName());
    }

    @After("execution(* demo.AccountServiceImpl.*(..))")
    public void traceAfterMethod(JoinPoint joinPoint) {
        System.out.println("after execution if method " + joinPoint.getSignature().getName());
    }
}
