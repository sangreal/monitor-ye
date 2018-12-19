package enn.monitor.security.gateway.trace;

import java.lang.reflect.Field;
import java.util.Map;

import brave.propagation.TraceContextOrSamplingFlags;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import enn.monitor.framework.tracing.exceptionhandler.CatchTraceException;
import enn.monitor.framework.tracing.model.TracerConfig;
import enn.monitor.security.gateway.trace.aop.CheckSwitch;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import brave.Span;
import enn.monitor.framework.tracing.BaseEnnTracer;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Reporter;
import zipkin2.reporter.Sender;

/**
 * Created by weize on 17-12-15.
 */
@Singleton
public class EnnGatewayTracer extends BaseEnnTracer {
    private static AsyncReporter spanReporter;
    @Inject
    private TracerConfig config;

    public EnnGatewayTracer() {
        super();
    }

    public void init(Sender sender, Reporter reporter, String name) {
        super.init(sender, reporter, name);
        this.spanReporter = (AsyncReporter) reporter;
    }

    @Override
    public TracerConfig getConfig() {
        return config;
    }

    @Override
    @CatchTraceException
    @CheckSwitch
    public void flush() {
        spanReporter.flush();
    }

    @Override
    @CatchTraceException
    @CheckSwitch
    public void injectL4J() {
    	injectL4J(tracing.tracer().currentSpan());
    }

    @Override
    @CatchTraceException
    @CheckSwitch
    public void injectL4J(Span span) {
        if (!"on".equals(config.getSwitchOn())) {
            return;
        }
        super.injectL4J(span);
        MDC.put("zInstance", config.getInstance());
        MDC.put("zService", config.getServiceName());
        MDC.put("zBizLine", config.getBizLine());
        try {
            // TODO: UGLY implementation! Zipkin not supporting getName() of Span
            Field recorderField = span.getClass().getDeclaredField("recorder");
            recorderField.setAccessible(true);
            Object recorder = recorderField.get(span);

            Field spanMapField = recorder.getClass().getDeclaredField("spanMap");
            spanMapField.setAccessible(true);
            Object spanMap = spanMapField.get(recorder);

            Field delegateField = spanMap.getClass().getDeclaredField("delegate");
            delegateField.setAccessible(true);
            Object delegate = delegateField.get(spanMap);
            Map map = (Map) delegate;

            for (Object mSpan : map.values()) {
                Field spanField = mSpan.getClass().getDeclaredField("span");
                spanField.setAccessible(true);
                zipkin2.Span.Builder spanBuilder = (zipkin2.Span.Builder) spanField.get(mSpan);
                zipkin2.Span rawSpan = spanBuilder.build();
                if ((rawSpan.traceId() + "/" + rawSpan.id()).equals(span.context().toString())) {
                    MDC.put("zResource", rawSpan.name());
                    break;
                }
            }
        } catch (Exception e) {
            Logger.getRootLogger().error("Read name of span failed: " + e.getMessage());
        }
    }

    @Override
    @CatchTraceException
    @CheckSwitch
    public void injectL4J(TraceContextOrSamplingFlags extracted, String spanName) {
        super.injectL4J(extracted, spanName);
        MDC.put("zInstance", config.getInstance());
        MDC.put("zService", config.getServiceName());
        MDC.put("zBizLine", config.getBizLine());
        MDC.put("zResource", spanName.toLowerCase());

    }

    @Override
    @CatchTraceException
    @CheckSwitch
    public void clearL4J() {
        super.clearL4J();
        MDC.remove("zInstance");
        MDC.remove("zService");
        MDC.remove("zBizLine");
        MDC.remove("zResource");
    }


    @Override
    @CatchTraceException
    public ServerInterceptor getGrpcServerInterceptor() {
        return getGrpcServerInterceptor(false);
    }

    /**
     * override to mute cyclical traces. trace--gateway--trace--……
     * @return
     */
    @Override
    @CatchTraceException
    public ServerInterceptor getGrpcServerInterceptor(boolean createNewTrace) {
        EnnGatewayTraceServerInterceptor interceptor = TracerBuilder.guiceInjector.getInstance(EnnGatewayTraceServerInterceptor.class);
        interceptor.init(super.getGrpcServerInterceptor(createNewTrace));
        return interceptor;
    }

    public static class EnnGatewayTraceServerInterceptor implements ServerInterceptor {
        private ServerInterceptor serverInterceptor;
        @Inject
        private TracerConfig config;

        public EnnGatewayTraceServerInterceptor() {}
        public void init(ServerInterceptor serverInterceptor) {
            this.serverInterceptor = serverInterceptor;
        }

        @Override
        @CatchTraceException
        public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
            String mute = headers.get(Metadata.Key.of("mute", Metadata.ASCII_STRING_MARSHALLER));
            if ("true".equals(mute) || !"on".equals(config.getSwitchOn())) {
                return next.startCall(serverCall, headers);
            }
            return serverInterceptor.interceptCall(serverCall, headers, next);
        }
    }
}
