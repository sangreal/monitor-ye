package enn.monitor.test.kafka;

import com.beust.jcommander.JCommander;
import enn.monitor.framework.kafka.EnnKafkaProducer;
import enn.monitor.framework.tracing.EnnTracer;
import enn.monitor.security.gateway.metrics.EnnMonitorGatewayParameter;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;
import enn.monitor.security.gateway.trace.TracerBuilder;
import org.apache.log4j.Logger;

/**
 * Created by weize on 18-2-7.
 */
public class TestProducer {
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

        EnnKafkaProducer producer = new EnnKafkaProducer.Builder()
                .setUrl(parameters.kafkaUrl)
                .setTopic(parameters.kafkaTopic)
                .setKeySerializer("org.apache.kafka.common.serialization.LongSerializer")
                .setValueSerializer("org.apache.kafka.common.serialization.StringSerializer")
                .build();
        producer.wrapTracing(tracer);
        while (true) {
            producer.send(10L, "test", true);
            try {
                int interval = parameters.fuzzyInterval ? (int)(parameters.sendInterval * (Math.random() * 3 + 1))
                        : parameters.sendInterval;
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
