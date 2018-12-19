package enn.monitor.test.elasticsearch;

import com.beust.jcommander.Parameter;

/** Command line parameters for Config Pusher. */
public class Parameters {

    @Parameter(
        names = {"-h", "--help"},
        description = "print help message",
        required = false
    )
    public boolean help = false;

    //-------------------------------------------------- monitor ----------------------------------------------
    @Parameter(names = "--app_monitor_kafka", description = "kafka url", required = false)
    public String appMonitorKafka = "10.19.140.4:30191,10.19.140.5:30192,10.19.140.12:30193";

    @Parameter(
        names = "--appTopic",
        description = "get message from kafka",
        required = false
    )
    public String appTopic = "monitor-app";
    
    @Parameter(
        names = "--metrics_freq",
        description = "Frequency in seconds of collecting metrics.",
        required = false
    )
    public int metricsFreq = 1;

    @Parameter(
    	names = "--enable_metrics",
        description = "Whether enable metrics collecting or not.",
        required = false
    )
    public boolean enableMetrics = false;
    
    //-------------------------------------------------- elasticsearch ----------------------------------------------
    
    @Parameter(
    	names = "--es_cluster_name",
        description = "Whether enable metrics collecting or not.",
        required = true
    )
    public String esClusterName = null;
    
    @Parameter(
    	names = "--es_url",
        description = "Whether enable metrics collecting or not.",
        required = true
    )
    public String esUrl = null;
    
    @Parameter(
    	names = "--es_index",
        description = "Whether enable metrics collecting or not.",
        required = false
    )
    public String esIndex = "micklongen1";
    
    //-------------------------------------------------- test size ----------------------------------------------
    @Parameter(
    	names = "--batchNum",
        description = "Whether enable metrics collecting or not.",
        required = false
    )
    public int batchNum = 10000;
    
    @Parameter(
    	names = "--threadNum",
        description = "Whether enable metrics collecting or not.",
        required = false
    )
    public int threadNum = 64;
    
    @Parameter(
    	names = "--loopCount",
        description = "Whether enable metrics collecting or not.",
        required = false
    )
    public long loopCount = 50;
}
