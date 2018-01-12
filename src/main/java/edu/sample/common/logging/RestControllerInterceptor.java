package edu.sample.common.logging;

 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Class to intercept Spring HTTP requests for logging purposes
 * @author
 *
 */
public class RestControllerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(RestControllerInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        BusinessLogHttpInterceptorMessage businessLog = BusinessLogThreadLocal.get();
        BusinessLogThreadLocal.unset();

        if (businessLog != null) {
            businessLog.setRequestURI(request.getRequestURI());
            businessLog.setHttpMethod(request.getMethod());

            String json;
            try{
                json = LogUtil.toJSON(businessLog);
            }catch (Exception e){
                BusinessLogHttpInterceptorMessage  genericMessage = new BusinessLogHttpInterceptorMessage();
                genericMessage.setRequestURI(request.getRequestURI());
                genericMessage.setHttpMethod(request.getMethod());
                genericMessage.setExceptionMessage("Log was not able to serialize request/resposnse object to JSON");
                json=LogUtil.toJSON(genericMessage);
            }

            String businessLogString = new StringBuilder().append("NEXT-ME").append(" ").append(json).toString();
            LOG.info(businessLogString);
        }

    }
}