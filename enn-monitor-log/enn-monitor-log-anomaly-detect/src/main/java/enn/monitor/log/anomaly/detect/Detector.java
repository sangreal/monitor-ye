package enn.monitor.log.anomaly.detect;

import com.beust.jcommander.JCommander;
import enn.monitor.framework.kafka.EnnKafkaConsumer;
import enn.monitor.framework.kafka.EnnKafkaMsgProcessor;

/**
 * Created by weize on 18-8-10.
 */
public class Detector {
    public static Parameters parameters;
    public static void main(String[] args) {
        parameters = new Parameters();
        JCommander jc = new JCommander(parameters, args);
        if (parameters.help) {
            jc.usage();
            return;
        }
        EnnKafkaConsumer consumer = new EnnKafkaConsumer.Builder()
                .setUrl(parameters.kafka)
                .setTopic(parameters.topic)
                .setGroupId(parameters.groupId)
                .setKeyDeserializer("org.apache.kafka.common.serialization.LongDeserializer")
                .setValueDeserializer("org.apache.kafka.common.serialization.StringDeserializer")
                .build();
        consumer.setProcessor(new EnnKafkaMsgProcessor() {
            @Override
            public void process(Object key, Object value) throws Exception {
                
            }

            @Override
            public void stop() {

            }
        });
        consumer.start();
    }
}
