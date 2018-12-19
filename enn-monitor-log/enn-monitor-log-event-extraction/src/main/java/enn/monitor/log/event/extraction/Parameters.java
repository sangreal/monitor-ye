package enn.monitor.log.event.extraction;

import java.io.Serializable;

import com.beust.jcommander.Parameter;

/**
 * Created by weize on 18-6-21.
 */
public class Parameters implements Serializable {

    @Parameter(
            names = {"-h", "--help"},
            description = "print help message",
            required = false
    )
    public boolean help = false;

    @Parameter(
            names = "--env",
            description = "which topic to put self metrics to kafka",
            required = false
    )
    public String env = "test";

    @Parameter(
            names = "--kafka",
            description = "kafka url",
            required = false
    )
    public String kafka = "10.19.140.200:30191,10.19.140.200:30192,10.19.140.200:30193";

    @Parameter(
            names = "--batchInterval",
            description = "which topic to put self metrics to kafka",
            required = false
    )
    public int batchInterval = 5;

    @Parameter(
            names = "--logTopic",
            description = "get log from kafka",
            required = false
    )
    public String logTopic = "monitor-log";

    @Parameter(
            names = "--groupid",
            description = "kafka group id",
            required = false
    )
    public String kafkaGroupId = "test-event-extract";

    @Parameter(
            names = "--opentsdbUrl",
            description = "url of opentsdb",
            required = false
    )
//    public String opentsdbUrl = "10.19.140.200:29430";
    public String opentsdbUrl = "10.19.138.169:4242";
}
