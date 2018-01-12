package edu.sample.common.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect class to log all CoreBanking API public functions. The Aspect logs
 * 
 * @author
 *
 */
@Aspect
@Component
public class CoreBankingAspect {

    private static final Logger LOG = LoggerFactory.getLogger(CoreBankingAspect.class);

    @Around("execution(* edu.sample.service.JiraIntegrationService.*(..))")
    public Object pointCut(ProceedingJoinPoint pjp) throws Throwable {

        
        Object[] requestParams = pjp.getArgs();
        BusinessLogCoreBankInterceptorMessage businessLogMessage = new BusinessLogCoreBankInterceptorMessage();
        businessLogMessage.setClassName(pjp.getTarget().getClass().getName());
        businessLogMessage.setMethodName(pjp.getSignature().getName());
        businessLogMessage.setArguments(requestParams);
        Object returnParam = null;

        Throwable throwable = null;
        long startTime = System.currentTimeMillis();
        try {
            returnParam = pjp.proceed();
            businessLogMessage.setReturnValue(returnParam);
        } catch (Throwable e) {
            throwable = e;
            businessLogMessage.setExceptionMessage(e.toString());
        }

        businessLogMessage.setProcessingTime(System.currentTimeMillis() - startTime);

        String json = LogUtil.toJSON(businessLogMessage);
        String businessLogString = new StringBuilder().append("JIRA").append(" ").append(json).toString();

        LOG.info(businessLogString);

        if (throwable != null) {
            throw throwable;
        } else {
            return returnParam;
        }
    }
}
