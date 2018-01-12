package edu.sample.common.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Spring AOP based aspect class to intercept RestController HTTP methods for
 * logging purposes.
 * 
 * @author
 *
 */
@Aspect
@Component
public class RestControllerAspect {

    @Around("execution(* br.com.bradesco.next.sync.*.server.*Controller.*(..))")
    public Object pointCut(ProceedingJoinPoint pjp) throws Throwable {

        BusinessLogHttpInterceptorMessage businessLog = new BusinessLogHttpInterceptorMessage();
        BusinessLogThreadLocal.set(businessLog);

        long start = System.currentTimeMillis();

        try {

            Object returnValue = pjp.proceed();

            businessLog.setProcessingTime(System.currentTimeMillis() - start);

            businessLog.setResponseParam(returnValue);

            return returnValue;

        } catch (Throwable e) {
            businessLog.setProcessingTime(System.currentTimeMillis() - start);
            businessLog.setExceptionMessage(e.getMessage());
            throw e;
        }

    }
}
