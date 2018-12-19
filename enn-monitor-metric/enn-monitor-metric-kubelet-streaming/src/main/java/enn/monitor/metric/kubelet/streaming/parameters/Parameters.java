package enn.monitor.metric.kubelet.streaming.parameters;

import com.beust.jcommander.Parameter;

import java.io.Serializable;

/**
 * Created by weize on 17-8-31.
 */
public class Parameters implements Serializable {

	private static final long serialVersionUID = 5992600283799081019L;

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
            names = "--batchInterval",
            description = "which topic to put self metrics to kafka",
            required = false
    )
    public int batchInterval = 3;

    @Parameter(names = "--kafkaBootstrap", description = "kafka bootstrap url", required = false)
    public String kafkaBootstrap = "10.19.140.200:30191,10.19.140.200:30192,10.19.140.200:30193";

    @Parameter(
            names = "--metricsTopic",
            description = "which topic to put self metrics to kafka",
            required = false
    )
    public String metricsTopic = "k8s-kubelet";

    @Parameter(
            names = "--groupid",
            description = "kafka group id",
            required = false
    )
    public String kafkaGroupId = "etl";

    @Parameter(
            names = "--opentsdbUrl",
            description = "url of opentsdb",
            required = false
    )
//    public String opentsdbUrl = "10.19.140.200:29430";
    public String opentsdbUrl = "10.19.138.169:4242";

    @Parameter(
            names = "--prometheusPushUrl",
            description = "url of prometheus Push gateway",
            required = false
    )
    public String prometheusPushUrl = "off";

    @Parameter(
            names = "--apiserver",
            description = "url of apiserver",
            required = false
    )
    public String apiserver = "10.19.140.200:6443";

    @Parameter(
            names = "--apiuser",
            description = "username of apiserver",
            required = false
    )
    public String apiuser = "zhtsC1002";

    @Parameter(
            names = "--apipass",
            description = "password of apiserver",
            required = false
    )
    public String apipass = "zhtsC1002";

    @Parameter(
            names = "--async",
            description = "whether async between batches",
            required = false
    )
    public String async = "on";
    
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
