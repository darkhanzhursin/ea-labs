package bank.service.aop;

import bank.integration.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DAOLogAdvice {
    private final Logger logger;

    public DAOLogAdvice(Logger logger) {
        this.logger = logger;
    }

    @Pointcut("execution(* bank.repository.*.*(..))")
    private void repositoryCalls() {}

    @Before("repositoryCalls()")
    public void logRepositoryCall(JoinPoint joinPoint) {
        String logMsg = String.format(
                "[%s] Repository method called: %s",
                DAOLogAdvice.class.getSimpleName(),
                joinPoint.getSignature().getName()
        );
        logger.log(logMsg);
    }
}

