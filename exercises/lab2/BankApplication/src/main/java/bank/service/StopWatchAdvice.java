package bank.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class StopWatchAdvice {
    @Around("execution(* bank.service.*.*(..))")
    public Object methodTimer(ProceedingJoinPoint call) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        String methodName = call.getSignature().getName();
        stopWatch.start(methodName);
        Object retVal = call.proceed();
        stopWatch.stop();
        System.out.println(methodName + stopWatch.prettyPrint());
        return retVal;
    }
}
