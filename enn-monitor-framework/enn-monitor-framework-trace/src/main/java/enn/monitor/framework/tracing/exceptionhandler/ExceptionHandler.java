package enn.monitor.framework.tracing.exceptionhandler;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

/**
 * Created by weize on 18-5-24.
 */
public class ExceptionHandler implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        } catch (Exception e) {
            Logger.getRootLogger().error("Tracer has Exception!");
            Logger.getRootLogger().error("Exception info: ", e);
            return null;
        }
    }
}
