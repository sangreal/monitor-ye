package enn.monitor.test.kafka;

import enn.monitor.framework.kafka.EnnKafkaConsumer;
import enn.monitor.framework.kafka.EnnKafkaMsgProcessor;
import enn.monitor.framework.tracing.EnnTracer;
import enn.monitor.framework.tracing.EnnTracerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weize on 17-12-18.
 */
public class ZipkinGwdataConsumer {
    public static void main(String[] args) {
        EnnKafkaConsumer consumer = new EnnKafkaConsumer.Builder()
                .setUrl("127.0.0.1:9092")
                .setTopic("zipkin_gw")
                .setKeyDeserializer("org.apache.kafka.common.serialization.LongDeserializer")
                .setValueDeserializer("org.apache.kafka.common.serialization.StringDeserializer")
                .build();
        consumer.setProcessor(new EnnKafkaMsgProcessor() {
            @Override
            public void process(Object key, Object value) throws Exception {
                System.out.println("zipkin data from gateway: " + key + "|" + value);
            }

            @Override
            public void stop() {

            }
        });
        consumer.start();
    }
}
