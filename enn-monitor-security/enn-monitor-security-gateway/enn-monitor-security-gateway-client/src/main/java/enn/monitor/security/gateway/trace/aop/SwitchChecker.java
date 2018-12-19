package enn.monitor.security.gateway.trace.aop;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import enn.monitor.framework.tracing.model.TracerConfig;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by weize on 18-5-25.
 */
public class SwitchChecker implements MethodInterceptor {
    @Inject
    TracerConfig config;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (config == null || !"on".equals(config.getSwitchOn())) {
            return null;
        }
        return invocation.proceed();
    }
}