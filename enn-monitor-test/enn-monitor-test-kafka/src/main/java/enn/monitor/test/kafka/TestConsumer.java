package enn.monitor.test.kafka;

import com.beust.jcommander.JCommander;
import enn.monitor.framework.kafka.EnnKafkaConsumer;
import enn.monitor.framework.kafka.EnnKafkaMsgProcessor;
import enn.monitor.framework.tracing.EnnTracer;
import enn.monitor.framework.tracing.EnnTracerFactory;
import enn.monitor.security.gateway.metrics.EnnMonitorGatewayParameter;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;
import enn.monitor.security.gateway.trace.TracerBuilder;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weize on 17-11-28.
 */
public class TestConsumer {
    public static void main(String[] args) throws Exception {
        Parameters parameters = new Parameters();
        JCommander jc = new JCommander(parameters, args);
        if (parameters.help) {
            jc.usage();
            return;
        }

        EnnTracer tracer = TracerBuilder.get();
        EnnMonitorMetricsGatewayGrpc metricsGrpc = new EnnMonitorMetricsGatewayGrpc(tracer);
        Logger.getRootLogger().info("Tracer config: " + TracerBuilder.config);
        metricsGrpc.startMetricsCollector();

        EnnKafkaConsumer consumer = new EnnKafkaConsumer.Builder()
                .setUrl(parameters.kafkaUrl)
                .setTopic(parameters.kafkaTopic)
                .setGroupId("demo-consumer")
                .setKeyDeserializer("org.apache.kafka.common.serialization.LongDeserializer")
                .setValueDeserializer("org.apache.kafka.common.serialization.StringDeserializer")
                .build();
        consumer.wrapTracing(tracer);
        consumer.setProcessor(new EnnKafkaMsgProcessor() {
            @Override
            public void process(Object key, Object value) throws Exception {
                Logger.getRootLogger().info("receiving kafkaMsg: " + key + "|" + value);
            }

            @Override
            public void stop() {

            }
        });
        consumer.start();
    }
}


