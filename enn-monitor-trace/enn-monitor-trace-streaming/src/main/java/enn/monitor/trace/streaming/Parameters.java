package enn.monitor.trace.streaming;

import com.beust.jcommander.Parameter;

/**
 * Created by weize on 18-1-2.
 */
public class Parameters {

    @Parameter(
            names = {"-h", "--help"},
            description = "print help message",
            required = false
    )
    public boolean help = false;

    @Parameter(
            names = "--workThreadNum",
            description = "work thread num",
            required = false
    )
    public int workThreadNum = 16;

    @Parameter(names = "--kafkaZk", description = "kafka url", required = false)
//    public String kafkaZk = "127.0.0.1:2181";
    public String kafkaZk = "10.19.140.200:30281,10.19.140.200:30282,10.19.140.200:30283";

    @Parameter(names = "--kafkaBootstrap", description = "kafka bootstrap url", required = false)
    public String kafkaBootstrap = "10.19.140.200:30191,10.19.140.200:30192,10.19.140.200:30193";

    @Parameter(
            names = "--topic",
            description = "which topic to put self metrics to kafka",
            required = false
    )
    public String topic = "zipkin_gw";

    @Parameter(
            names = "--esRest",
            description = "rest host and port of elasticsearch",
            required = false
    )
    public String esRest = "10.19.140.200:31921";

    @Parameter(
            names = "--esTcp",
            description = "tcp host and port of elasticsearch",
            required = false
    )
//    public String esTcp = "10.19.138.169:9300";
//    public String esTcp = "10.19.140.200:31931";
    public String esTcp = "10.38.240.28:31582";

    @Parameter(
            names = "--esClusterName",
            description = "cluster name of elasticsearch",
            required = false
    )
    public String esClusterName = "es-log";

    @Parameter(
            names = "--groupid",
            description = "kafka group id",
            required = false
    )
    public String kafkaGroupId = "test4";

    @Parameter(
            names = "--kafkaReceiverPerTopic",
            description = "kafka receiver per topic",
            required = false
    )
    public int kafkaReceiverPerTopic = 3;

    @Parameter(
            names = "--batchInterval",
            description = "batchInterval, in seconds.",
            required = false
    )
    public int batchInterval = 20;

    @Parameter(
            names = "--env",
            description = "running environment",
            required = false
    )
    public String env = "test";
}
