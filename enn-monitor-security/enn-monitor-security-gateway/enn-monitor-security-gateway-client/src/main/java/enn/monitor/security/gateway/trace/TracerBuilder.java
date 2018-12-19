package enn.monitor.security.gateway.trace;


import com.google.inject.Guice;
import com.google.inject.Injector;
import enn.monitor.framework.tracing.EnnAsyncReporter;
import enn.monitor.framework.tracing.EnnTracer;
import enn.monitor.security.gateway.trace.aop.GatewayTraceGuiceModule;
import zipkin2.codec.Encoding;
import zipkin2.reporter.AsyncReporter;

/**
 * Created by weize on 17-12-15.
 */
public class TracerBuilder {
    public static Injector guiceInjector;

    public static EnnTracer get() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final EnnTracer INSTANCE = init();
    }

    private static EnnTracer init() {
        guiceInjector = Guice.createInjector(new GatewayTraceGuiceModule());
        EnnGatewaySender sender = new EnnGatewaySender.Builder()
                .maxBytes(10000000)
                .encoding(Encoding.JSON)
                .build();
        GrpcCallDispatcher dispatcher
                = TracerBuilder.guiceInjector.getInstance(GrpcCallDispatcher.class);


        EnnGatewayTracer tracer = guiceInjector.getInstance(EnnGatewayTracer.class);
        AsyncReporter reporter = AsyncReporter.create(sender);
        EnnAsyncReporter ennAsyncReporter = new EnnAsyncReporter(reporter, tracer.getConfig().getBizLine(), tracer.getConfig().getInstance());

        tracer.init(sender, ennAsyncReporter, tracer.getConfig().getServiceName());

        return tracer;
    }
}
