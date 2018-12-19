package enn.monitor.framework.tracing.exceptionhandler;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

/**
 * Created by weize on 18-5-24.
 */
public class TraceGuiceModule extends AbstractModule {
    protected void configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(CatchTraceException.class),
                new ExceptionHandler());
    }
}
