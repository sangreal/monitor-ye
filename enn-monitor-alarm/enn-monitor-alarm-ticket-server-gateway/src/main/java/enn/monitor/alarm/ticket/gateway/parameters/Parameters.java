package enn.monitor.alarm.ticket.gateway.parameters;

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
        description = "Listen port of gateway",
        required = false
    )
    public int listenPort = 8090;
    
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
    public String token = "micklongen-alarm-ticket-server-http";
    
    @Parameter(
    	names = "--ticket_server",
        description = "ticket_server",
        required = false
    )
    public String ticketServer = "127.0.0.1";
    
    @Parameter(
    	names = "--ticket_port",
        description = "ticket_port",
        required = false
    )
    public int ticketPort = 9999;
    
}
