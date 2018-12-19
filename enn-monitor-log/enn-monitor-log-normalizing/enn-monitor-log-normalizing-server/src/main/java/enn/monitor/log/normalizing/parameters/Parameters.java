package enn.monitor.log.normalizing.parameters;

import com.beust.jcommander.Parameter;

/** Command line parameters for Config Pusher. */
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

    @Parameter(
    	names = "--listen_port",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public int listenPort = 10000;
    
    @Parameter(
    	names = "--kafka_url",
        description = "kafka",
        required = false
    )
    public String kafkaUrl = "10.19.137.141:30191,10.19.137.142:30192,10.19.137.143:30193";
    
    @Parameter(
    	names = "--group_id",
        description = "kafka",
        required = false
    )
    public String groupId = "micklongen";
    
    @Parameter(
    	names = "--from_topic",
        description = "from topic",
        required = false
    )
    public String fromTopic = "monitor-log";
    
    @Parameter(
    	names = "--to_topic_normal",
        description = "to topic",
        required = false
    )
    public String toTopicNormal = "monitor-log-normal";
    
    @Parameter(
    	names = "--to_topic_origin",
        description = "to topic",
        required = false
    )
    public String toTopicOrigin = "monitor-log-origin";
    
    @Parameter(
    	names = "--log_config_ip",
        description = "config server",
        required = false
    )
    public String logConfigIp = "10.19.140.200";

    @Parameter(
    	names = "--log_config_port",
        description = "config port",
        required = false
    )
    public int logConfigPort = 29315;

}
