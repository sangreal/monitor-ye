package enn.monitor.framework.tracing;

import brave.Span;
import brave.Tracing;
import brave.grpc.GrpcTracing;
import brave.httpclient.TracingHttpClientBuilder;
import brave.propagation.Propagation;
import brave.propagation.TraceContext;
import brave.propagation.TraceContextOrSamplingFlags;
import brave.servlet.TracingFilter;
import brave.kafka.clients.KafkaTracing;
import enn.monitor.framework.tracing.model.TraceAccessor;
import enn.monitor.framework.tracing.model.TracerConfig;
import enn.monitor.framework.tracing.model.ZipkinWrapper;
import enn.monitor.framework.tracing.zipkincopy.AsciiMetadataKeyFactory;
import io.grpc.*;
import org.apache.http.client.HttpClient;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.log4j.MDC;
import zipkin2.reporter.Reporter;
import zipkin2.reporter.Sender;

import javax.annotation.Nullable;
import javax.servlet.*;
import java.io.IOException;
import enn.monitor.framework.tracing.exceptionhandler.*;

/**
 * Created by weize on 17-11-13.
 */
public abstract class BaseEnnTracer implements EnnTracer {
    protected Sender sender;
    protected Reporter reporter;
    protected GrpcTracing grpcTracing;
    protected Tracing tracing;
    protected KafkaTracing kafkaTracing;
    private static final Propagation.Setter<Object, String> SETTER =
            new Propagation.Setter<Object, String>() { // retrolambda no like
                @Override public void put(Object metadata, String key, String value) {
                    if (metadata instanceof TraceAccessor) {
                        ((TraceAccessor) metadata).getEnnMonitorTraceInfo().getInfo().put(key, value);
                    } else if (metadata instanceof ZipkinWrapper) {
                        ((ZipkinWrapper) metadata).set(key, value);
                    }
                }

                @Override public String toString() {
                    return "Metadata::put";
                }
            };

    private static final Propagation.Getter<Object, String> GETTER =
            new Propagation.Getter<Object, String>() {
                @Nullable
                @Override
                public String get(Object data, String key) {
                    if (data instanceof TraceAccessor) {
                        return ((TraceAccessor) data).getEnnMonitorTraceInfo().getInfo().get(key);
                    } else if (data instanceof ZipkinWrapper) {
                        return ((ZipkinWrapper) data).get(key);
                    }
                    return null;
                }
            };
    private static TraceContext.Injector injector;
    private static TraceContext.Extractor extractor;

    public BaseEnnTracer() {}

    public void init(Sender sender, Reporter reporter, String name) {
        this.sender = sender;
        this.reporter = reporter;
        tracing = Tracing.newBuilder()
                .localServiceName(name)
                .spanReporter(reporter)
                .build();
        grpcTracing = GrpcTracing.create(tracing);
        kafkaTracing = KafkaTracing.create(tracing);
        extractor = tracing.propagation().extractor(GETTER);
        injector = tracing.propagation().injector(SETTER);
    }

    @Override
    @CatchTraceException
    public Span newRootSpan(String spanName) {
        Span span = tracing.tracer().newTrace();
        tracing.tracer().withSpanInScope(span);
        span.name(spanName);
        return span;
    }

    @Override
    @CatchTraceException
    public ServerInterceptor getGrpcServerInterceptor() {
        return getGrpcServerInterceptor(true);
    }

    @Override
    @CatchTraceException
    public ServerInterceptor getGrpcServerInterceptor(boolean createNewTrace) {
        return new EnnTraceServerInterceptor(grpcTracing.newServerInterceptor(), createNewTrace);
    }

    public class EnnTraceServerInterceptor implements ServerInterceptor {
        private ServerInterceptor serverInterceptor;
        private boolean createNewTrace;
        final TraceContext.Extractor<Metadata> extractor;

        public EnnTraceServerInterceptor(ServerInterceptor serverInterceptor, boolean createNewTrace) {
            this.serverInterceptor = serverInterceptor;
            this.createNewTrace = createNewTrace;
            extractor = tracing.propagationFactory().create(AsciiMetadataKeyFactory.INSTANCE)
                    .extractor(new Propagation.Getter<Metadata, Metadata.Key<String>>() { // retrolambda no like
                        @Override public String get(Metadata metadata, Metadata.Key<String> key) {
                            return metadata.get(key);
                        }
                    });
        }

        @Override
        @CatchTraceException
        public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
            TraceContextOrSamplingFlags extracted = extractor.extract(headers);
            if (extracted.context() == null && !createNewTrace) {
                clearL4J();
                return next.startCall(serverCall, headers);
            }
            ServerCall.Listener<ReqT> reqTListener = serverInterceptor.interceptCall(serverCall, headers, next);
            injectL4J(extracted, serverCall.getMethodDescriptor().getFullMethodName());
            return reqTListener;
        }
    }

    @Override
    @CatchTraceException
    public ClientInterceptor getGrpcClientInterceptor() {
        return grpcTracing.newClientInterceptor();
    }

    @Override
    @CatchTraceException
    public <T> void inject(T wrappedData) {
        if (tracing.tracer().currentSpan() == null) {
            injector.inject(tracing.tracer().nextSpan().context(), wrappedData);
        } else {
            injector.inject(tracing.tracer().currentSpan().context(), wrappedData);
        }
    }

    @Override
    @CatchTraceException
    public <T> Span extract(T data) {
        TraceContextOrSamplingFlags contextOrFlags = extractor.extract(data);
        if (contextOrFlags == null || contextOrFlags.context() == null) {
            clearL4J();
            return null;
        }
        Span span = tracing.tracer().toSpan(contextOrFlags.context());
//        tracing.tracer().withSpanInScope(span);
        injectL4J(span);
        return span;
    }

    @Override
    @CatchTraceException
    public Span joinTrace(Span span) {
        Span joinSpan = tracing.tracer().joinSpan(span.context());
        injectL4J(joinSpan);
        return joinSpan;
    }

    @Override
    @CatchTraceException
    public Span newChild(Span span, String name) {
        return tracing.tracer().newChild(span.context()).name(name);
    }

    @Override
    @CatchTraceException
    public Span newChild(String name) {
        if (tracing.tracer().currentSpan() != null) {
            Span child = tracing.tracer().newChild(tracing.tracer().currentSpan().context()).name(name);
            return child;
        } else {
            Span child = tracing.tracer().nextSpan().name(name);
            return child;
        }
    }

    @Override
    @CatchTraceException
    public Span currentSpan() {
        return tracing.tracer().currentSpan();
    }

    @CatchTraceException
    public void injectL4J() {
        injectL4J(currentSpan());
    }

    @CatchTraceException
    public void injectL4J(Span span) {
        clearL4J();
        MDC.put("zTraceId", span.context().traceIdString());
    }

    @CatchTraceException
    public void injectL4J(TraceContextOrSamplingFlags extracted, String spanName) {
        clearL4J();
        MDC.put("zTraceId", extracted.context().traceIdString());
    }

    @CatchTraceException
    public void clearL4J() {
        MDC.remove("zTraceId");
    }

    @Override
    @CatchTraceException
    public javax.servlet.Filter getServletFilter() {
        return getServletFilter(false);
    }

    @Override
    @CatchTraceException
    public javax.servlet.Filter getServletFilter(boolean createNewTrace) {
        return new EnnTraceServletFilter(TracingFilter.create(tracing), createNewTrace);
    }

    public class EnnTraceServletFilter implements javax.servlet.Filter {
        private Filter delegate;
        private boolean createNewTrace;

        public EnnTraceServletFilter(Filter filter, boolean createNewTrace) {
            delegate = filter;
            extractor = tracing.propagation().extractor(GETTER);
            this.createNewTrace = createNewTrace;
        }

        @Override
        @CatchTraceException
        public void init(FilterConfig filterConfig) throws ServletException {
            delegate.init(filterConfig);
        }

        @Override
        @CatchTraceException
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            // Prevent duplicate spans for the same request
            if (servletRequest.getAttribute("TracingFilter") != null) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

            if (extractor.extract(servletRequest) == null && !createNewTrace) {
                clearL4J();
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                delegate.doFilter(servletRequest, servletResponse, filterChain);
                if (tracing.tracer().currentSpan() != null) {
                    injectL4J();
                }
            }
        }

        @Override
        @CatchTraceException
        public void destroy() {
            delegate.destroy();
        }
    }

    @Override
    @CatchTraceException
    public HttpClient createHttpClient() {
        HttpClient httpclient = TracingHttpClientBuilder.create(tracing).build();
        return httpclient;
    }

    @Override
    @CatchTraceException
    public Producer wrapKafkaProducer(Producer producer) {
        return kafkaTracing.producer(producer);
    }

    @Override
    @CatchTraceException
    public  Consumer wrapKafkaConsumer(Consumer consumer) {
        return kafkaTracing.consumer(consumer);
    }
}
