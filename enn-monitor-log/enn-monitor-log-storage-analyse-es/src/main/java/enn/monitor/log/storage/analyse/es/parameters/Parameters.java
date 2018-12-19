package enn.monitor.log.storage.analyse.es.parameters;

import java.io.Serializable;

import com.beust.jcommander.Parameter;

/** Command line parameters for Config Pusher. */
public class Parameters implements Serializable {

	private static final long serialVersionUID = 6003725313905819281L;

	@Parameter(
        names = {"-h", "--help"},
        description = "print help message",
        required = false
    )
    public boolean help = false;
	
	// threadnum
    @Parameter(
    	names = "--workthread_num",
        description = "group_id",
        required = false
    )
    public int workThreadNum = 16;
    
    @Parameter(
    	names = "--queue_size",
        description = "queue_size",
        required = false
    )
    public int queueSize = 50000;
    
    // kafka topic
    @Parameter(
    	names = "--kafkaUrl",
        description = "kafka url",
        required = false
    )
    public String kafkaUrl = "10.19.248.26:29420,10.19.248.27:29421,10.19.248.28:29422,10.19.248.29:29423,10.19.248.30:29424";
    
    @Parameter(
    	names = "--topic_normal",
        description = "topic",
        required = false
    )
    public String topicNormal  = "monitor-log-normal";

    @Parameter(
    	names = "--group_id",
        description = "group_id",
        required = false
    )
    public String groupId = "log-analyse-miak";
    
    // -------------------------------------------------------------------------
    @Parameter(
        names = "--analyseStorageHost",
        description = "",
        required = false
    )
    public String analyseStorageHost = "10.19.248.200";

    @Parameter(
        names = "--analyseStoragePort",
        description = "clustername of ES",
        required = false
    )
    public int analyseStoragePort = 29304;
    
 // -----------------------------------------------
    @Parameter(
    	names = "--token",
        description = "token",
        required = false
    )
    public String token = "01C561B348479ECC66EB6D6C85C026C0";
    
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
    public int gatewayPort = 30111;
    
    // -----------------------------------------------
    @Parameter(
        names = {"--es_host"},
        description = "is test",
        required = false
    )
    public String esHost = "10.19.248.200:31931";
    
    @Parameter(
        names = {"--es_cluster"},
        description = "is test",
        required = false
    )
    public String esCluster = "es-log";
    
    // -------------------------------------------------------------------------
    @Parameter(
        names = "--logConfigCacheHost",
        description = "",
        required = false
    )
    public String logConfigCacheHost = "10.19.248.200";

    @Parameter(
        names = "--logConfigCachePort",
        description = "",
        required = false
    )
    public int logConfigCachePort = 29307;
}
