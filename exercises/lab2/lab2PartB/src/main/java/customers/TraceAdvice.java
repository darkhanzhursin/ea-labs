package customers;

import customers.integration.ems.EmailSenderImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;

@Aspect
@Configuration
public class TraceAdvice {
    @After("execution(* customers.integration.ems.EmailSenderImpl.sendEmail(..)) && args(email, message)")
    public void traceAfterMethod(JoinPoint joinPoint, String email, String message) {
        EmailSenderImpl emailSender = (EmailSenderImpl) joinPoint.getTarget();
        System.out.println(LocalDateTime.now() + " method=" + joinPoint.getSignature().getName()
            + " address=" + email + " message=" + message + " " + emailSender.getOutgoingMailServer());
    }

    @Around("execution(* customers.integration.ems.EmailSenderImpl.sendEmail(..))")
    public Object invoke(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();

        //long totaltime = sw.getLastTaskTimeMillis();
        System.out.println("totalTime" + sw.prettyPrint());

        return retVal;
    }
}
