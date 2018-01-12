package edu.sample.common.logging;

/**
 * ThreadLocal storage to store HTTP request parameters
 * in the thread, so that it can be accessed
 * by the Spring MVC Interceptor class(BusinessLogEndPointInterceptor).
 * @author 
 *
 */
public class BusinessLogThreadLocal {
    public static final ThreadLocal<BusinessLogHttpInterceptorMessage> userThreadLocal = 
            new ThreadLocal<BusinessLogHttpInterceptorMessage>();

    public static void set(BusinessLogHttpInterceptorMessage businessLog) {
        userThreadLocal.set(businessLog);
    }

    public static void unset() {
        userThreadLocal.remove();
    }

    public static BusinessLogHttpInterceptorMessage get() {
        return  (BusinessLogHttpInterceptorMessage) userThreadLocal.get();
    }
}
