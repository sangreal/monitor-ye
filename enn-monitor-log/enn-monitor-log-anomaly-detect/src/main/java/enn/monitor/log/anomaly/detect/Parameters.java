package enn.monitor.log.anomaly.detect;

import com.beust.jcommander.Parameter;
import java.io.Serializable;

/**
 * Created by weize on 18-8-15.
 */
public class Parameters implements Serializable {

    @Parameter(
            names = {"-h", "--help"},
            description = "print help message",
            required = false
    )
    public boolean help = false;

    @Parameter(
            names = "--kafka",
            description = "kafka bootstrap server url",
            required = false
    )
    public String kafka = "10.19.140.200:30191,10.19.140.200:30192,10.19.140.200:30193";

    @Parameter(
            names = "--topic",
            description = "kafka bootstrap server url",
            required = false
    )
    public String topic = "log-events";

    @Parameter(
            names = "--groupId",
            description = "kafka group id",
            required = false
    )
    public String groupId = "log-events";

    @Parameter(
            names = "--opentsdbUrl",
            description = "url of opentsdb",
            required = false
    )
    public String opentsdbUrl = "10.19.138.169:4242";
}
