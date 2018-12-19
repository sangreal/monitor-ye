package enn.monitor.framework.tracing;

import brave.Span;
import enn.monitor.framework.tracing.model.TracerConfig;
import io.grpc.ClientInterceptor;
import io.grpc.ServerInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.producer.Producer;


/**
 * Created by weize on 17-11-13.
 */
public interface EnnTracer {
    public TracerConfig getConfig();
    public void flush();
    public ServerInterceptor getGrpcServerInterceptor();
    public ServerInterceptor getGrpcServerInterceptor(boolean createNewTrace);
    public ClientInterceptor getGrpcClientInterceptor();
    public Span newRootSpan(String spanName);
    public Span newChild(String name);
    public Span newChild(Span span, String name);
    public Span currentSpan();
    public <T> void inject(T data);
    public <T> Span extract(T data);
    public Span joinTrace(Span span);
    void injectL4J();
    void clearL4J();
    public javax.servlet.Filter getServletFilter();
    public javax.servlet.Filter getServletFilter(boolean createNewTrace);

    public HttpClient createHttpClient();
    public <K, V> Producer<K, V> wrapKafkaProducer(Producer<K, V> producer);
    public <K, V> Consumer<K, V> wrapKafkaConsumer(Consumer<K, V> consumer);
}
