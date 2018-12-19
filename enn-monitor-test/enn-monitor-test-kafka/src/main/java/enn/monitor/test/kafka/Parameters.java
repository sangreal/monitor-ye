package enn.monitor.test.kafka;

import com.beust.jcommander.Parameter;

/**
 * Created by weize on 18-1-31.
 */
public class Parameters {
    @Parameter(
            names = {"-h", "--help"},
            description = "print help message",
            required = false
    )
    public boolean help = false;

    @Parameter(
            names = "--kafkaUrl",
            description = "kafka url",
            required = false
    )
//    public String kafkaUrl = "127.0.0.1:9092";
    public String kafkaUrl = "10.19.140.200:30191,10.19.140.200:30192,10.19.140.200:30193";

    @Parameter(
            names = "--kafkaTopic",
            description = "kafka topic",
            required = false
    )
    public String kafkaTopic = "rdkafka";
//    public String kafkaTopic = "monitor-app";
//    public String kafkaTopic = "log-k8s-system";

    @Parameter(
            names = "--gateway_server",
            description = "gateway server",
            required = false
    )
    public String gatewayServer = "10.19.140.200";

    @Parameter(
            names = "--gatewayPort",
            description = "gateway port",
            required = false
    )
    public int gatewayPort = 30111;

    @Parameter(
            names = "token",
            description = "token for gateway server",
            required = false
    )
    public String token = "micklongen-token-server";

    @Parameter(
            names = "--send_interval",
            description = "send_interval in ms.",
            required = false
    )
    public int sendInterval = 1000;

    @Parameter(
            names = "--fuzzy_interval",
            description = "mul by random num if set.",
            required = false
    )
    public boolean fuzzyInterval = false;
}
