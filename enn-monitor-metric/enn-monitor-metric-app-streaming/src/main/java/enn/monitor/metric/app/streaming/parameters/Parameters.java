package enn.monitor.metric.app.streaming.parameters;

import com.beust.jcommander.Parameter;

import java.io.Serializable;

/**
 * Created by weize on 17-8-31.
 */
public class Parameters implements Serializable {

	private static final long serialVersionUID = 7848819921779868226L;

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

    @Parameter(names = "--kafkaBootstrap", description = "kafka url", required = false)
    public String kafkaBootstrap = "10.19.140.200:30191,10.19.140.200:30192,10.19.140.200:30193";

    @Parameter(
            names = "--appTopic",
            description = "which topic to put self metrics to kafka",
            required = false
    )
    public String appTopic = "monitor-app";
//    public String appTopic = "k8s-kubelet";

    @Parameter(
            names = "--groupid",
            description = "kafka group id",
            required = false
    )
    public String kafkaGroupId = "test2";

    @Parameter(
            names = "--opentsdbUrl",
            description = "url of opentsdb",
            required = false
    )
//    public String opentsdbUrl = "10.19.138.169:4242";
    public String opentsdbUrl = "10.19.140.200:29430";
    
    //---------------------------------------monitor----------------------------------------------
    @Parameter(
    	names = "--token",
        description = "token",
        required = false
    )
    public String token = "CA4B7757AEEAA7EA547776653906EA42";
    
    @Parameter(
    	names = "--enable_metrics",
        description = "Whether enable metrics collecting or not.",
        required = false
    )
    public boolean enableMetrics = false;
    
    @Parameter(
        names = "--gatewayHost",
        description = "",
        required = false
    )
    public String gatewayHost = "10.19.248.200";

    @Parameter(
        names = "--gatewayPort",
        description = "clustername of ES",
        required = false
    )
    public int gatewayPort = 30112;
}