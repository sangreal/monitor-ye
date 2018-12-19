package enn.monitor.trace.dataapi;

import com.beust.jcommander.Parameter;

/**
 * Created by weize on 18-1-5.
 */
public class Parameters {

    @Parameter(
            names = {"-h", "--help"},
            description = "print help message",
            required = false
    )
    public boolean help = false;

    @Parameter(
            names = "--port",
            description = "",
            required = false
    )
    public int listenPort = 8888;

//    @Parameter(
//
//            names = "--esUrl",
//            description = "",
//            required = false
//    )
//    public String esUrl = "http://10.19.140.200:29400";

    @Parameter(
            names = "--enable_metrics",
            description = "Whether enable metrics collecting or not.",
            required = false
    )
    public boolean enableMetrics = false;

    @Parameter(
            names = "--gateway_server",
            description = "gateway server",
            required = false
    )
    public String gatewayServer = "127.0.0.1";

    @Parameter(
            names = "--gatewayPort",
            description = "gateway port",
            required = false
    )
    public int gatewayPort = 10100;

    @Parameter(
            names = "--token",
            description = "token",
            required = false
    )
    public String token = "micklongen-gateway-server";

    @Parameter(
            names = "--elasticSearch_host",
            description = "",
            required = false
    )
    public String elasticSearchHost = "10.19.140.200";
//    public String elasticSearchHost = "10.19.138.169";

    @Parameter(
            names = "--elasticSearch_port",
            description = "",
            required = false
    )
    public int elasticSearchPort = 29400;
//    public int elasticSearchPort = 9200;

}

