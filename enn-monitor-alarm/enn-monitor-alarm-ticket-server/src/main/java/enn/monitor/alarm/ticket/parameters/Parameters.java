package enn.monitor.alarm.ticket.parameters;

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
        description = "Listen port of Ticker Server",
        required = false
    )
    public int listenPort = 9999;

    @Parameter(
    	names = "--mongoUrl",
        description = "mongo url",
        required = false
    )
    public String mongoUrl = "mongodb://127.0.0.1:27017/EnnMonitorAlarmTicket";
    
    @Parameter(
    	names = "--dbName",
        description = "database name",
        required = false
    )
    public String dbName = "EnnMonitorAlarmTicket";
    
    @Parameter(
    	names = "--token",
        description = "token",
        required = false
    )
    public String token = "micklongen-alarm-ticket-server";
    
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
    	names = "--config_server",
        description = "config server",
        required = false
    )
    public String configServer = "127.0.0.1";
    
    @Parameter(
    	names = "--configPort",
        description = "config port",
        required = false
    )
    public int configPort = 9998;
        
}
