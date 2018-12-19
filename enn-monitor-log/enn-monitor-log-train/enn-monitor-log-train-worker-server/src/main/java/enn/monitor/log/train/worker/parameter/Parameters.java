package enn.monitor.log.train.worker.parameter;

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
    	names = "--master_url",
        description = "Listen port",
        required = false
    )
//    public String masterUrl = "127.0.0.1";
    public String masterUrl = "10.19.248.200";
    
    @Parameter(
    	names = "--master_port",
        description = "Listen port",
        required = false
    )
//    public int masterPort = 10000;
    public int masterPort = 29302;

}
