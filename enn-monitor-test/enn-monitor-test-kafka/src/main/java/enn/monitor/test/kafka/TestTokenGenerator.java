package enn.monitor.test.kafka;

import enn.monitor.security.config.topic.client.EnnMonitorSecurityConfigTopicClient;
import enn.monitor.security.config.topic.parameters.EnnMonitorSecurityConfigTopicGetRequest;
import enn.monitor.security.config.topic.parameters.EnnMonitorSecurityConfigTopicGetResponse;

/**
 * Created by weize on 17-12-18.
 */
public class TestTokenGenerator {
    private static EnnMonitorSecurityConfigTopicClient client = new EnnMonitorSecurityConfigTopicClient(
            "127.0.0.1", 10000);

    public static void main(String[] args) {
        client.createTopic("test_user", "zipkin_gw", "zipkin_gw");
        EnnMonitorSecurityConfigTopicGetResponse topicResponse = client.getTopic(EnnMonitorSecurityConfigTopicGetRequest.newBuilder().setCreateUser("test_user").build());
        System.out.println("test");
    }
}
