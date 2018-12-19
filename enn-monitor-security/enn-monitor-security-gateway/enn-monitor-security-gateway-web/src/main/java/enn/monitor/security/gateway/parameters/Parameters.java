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
    	names = "--listen_port",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public int listenPort = 8090;
    
    @Parameter(
    	names = "--gateway_server",
        description = "gateway server",
        required = false
    )
    public String gatewayServer = "127.0.0.1";
//    public String gatewayServer = "10.19.140.200";

    @Parameter(
    	names = "--gatewayPort",
        description = "gateway port",
        required = false
    )
    public int gatewayPort = 10100;
//    public int gatewayPort = 29014;

}
