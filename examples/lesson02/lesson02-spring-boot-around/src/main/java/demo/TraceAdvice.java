package demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TraceAdvice {

    @Around("execution(* demo.Calculator.*(..))")
    public Object aroundAdd(ProceedingJoinPoint joinpoint) throws Throwable {

        Object[] params = joinpoint.getArgs();
        System.out.println("in around advice with values x= "+params[0]+" and y= "+params[1]);

        params[0]=23;
        params[1]=13;
        Object object= joinpoint.proceed(params);

        //Object result = joinpoint.proceed();
        System.out.println("in around advice: result received from the calculator "+(Integer)object);
        return 100;
    }
}
