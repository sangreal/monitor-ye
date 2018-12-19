package enn.monitor.log.config.gateway.parameters;

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
    public int listenPort = 10000;
    
    @Parameter(
    	names = "--config_host",
        description = "Listen host of config",
        required = false
    )
    public String configHost = "10.19.140.200";
//    public String configHost = "127.0.0.1";
    
    @Parameter(
    	names = "--config_port",
        description = "Listen port of config",
        required = false
    )
    public int configPort = 29315;
    
}
