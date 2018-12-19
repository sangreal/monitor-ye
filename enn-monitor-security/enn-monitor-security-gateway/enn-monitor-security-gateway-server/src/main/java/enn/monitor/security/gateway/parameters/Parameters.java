package enn.monitor.security.gateway.parameters;

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
            names = "--serviceName",
            description = "config server",
            required = false
    )
    public String serviceName = "monitor-gateway-server";

    @Parameter(
    	names = "--enable_token_filter",
        description = "Whether enable metrics collecting or not.",
        required = false
    )
    public boolean enableTokenFilter = false;
    
    @Parameter(
            names = "--create_new_trace",
            description = "Whether create new trace for none-traced requests.",
            required = false
    )
    public boolean createNewTrace = false;
    
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
    public int listenPort = 10100;

    @Parameter(
    	names = "--kafkaUrl",
        description = "kafka url",
        required = false
    )
    public String kafkaUrl = "10.19.137.141:30191,10.19.137.142:30192,10.19.137.143:30193";
//    public String kafkaUrl = "10.19.138.169:9092";
    
    @Parameter(
    	names = "--config_service_ip",
        description = "config server",
        required = false
    )
//    public String configServer = "127.0.0.1";
    public String configServiceIp = "10.19.140.200";

    @Parameter(
    	names = "--config_service_port",
        description = "config port",
        required = false
    )
//    public int configPort = 10000;
    public int configServicePort = 30933;
    
    @Parameter(
    	names = "--config_business_ip",
        description = "config server",
        required = false
    )
//        public String configServer = "127.0.0.1";
    public String configBusinessIp = "10.19.140.200";

    @Parameter(
    	names = "--config_business_port",
        description = "config port",
        required = false
    )
//        public int configPort = 10000;
    public int configBusinessPort = 30933;

}
