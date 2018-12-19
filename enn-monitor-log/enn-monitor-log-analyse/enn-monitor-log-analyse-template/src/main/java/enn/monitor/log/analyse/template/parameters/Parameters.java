package enn.monitor.log.analyse.template.parameters;

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
	
    @Parameter(
    	names = "--listen_port",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public int listenPort = 10000;
	
 // -----------------------------------------------
    @Parameter(
    	names = "--token",
        description = "token",
        required = false
    )
    public String token = "CD13213D0AFC1B3DB59705F2F51583D1";
    
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
    
    // -----------------------------------------------
    @Parameter(
        names = {"--enable_test"},
        description = "is test",
        required = false
    )
    public boolean enableTest = false;
    
    // -----------------------------------------------
    @Parameter(
        names = {"--es_host"},
        description = "is test",
        required = false
    )
    public String esHost = "10.19.248.200";
    
    @Parameter(
        names = {"--es_port"},
        description = "is test",
        required = false
    )
    public String esPort = "31921";
    
//    @Parameter(
//        names = {"--es_index"},
//        description = "is test",
//        required = false
//    )
//    public String esIndex = "shanghai-monitor-system-config-2018-08-14/log";
    
    @Parameter(
        names = {"--enable_client"},
        description = "is test",
        required = false
    )
    public boolean enableClient = false;
    
    // -------------------------------------------------------------------------------
    @Parameter(
    	names = "--config_gateway_host",
        description = "Listen host of config",
        required = false
    )
    public String configGatewayHost = "10.19.248.200";
    
    @Parameter(
    	names = "--config_gateway_port",
        description = "Listen port of config",
        required = false
    )
    public int configGatewayPort = 29319;

}
