package enn.monitor.log.config.cache.parameters;

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
    	names = "--log_config_ip",
        description = "config server",
        required = false
    )
    public String logConfigIp = "10.19.248.200";

    @Parameter(
    	names = "--log_config_port",
        description = "config port",
        required = false
    )
    public int logConfigPort = 29415;

}
