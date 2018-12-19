package enn.monitor.log.train.master.parameter;

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
    	names = "--workthread_num",
        description = "group_id",
        required = false
    )
    public int workThreadNum = 16;
    
    @Parameter(
    	names = "--listen_port",
        description = "Listen port",
        required = false
    )
    public int listenPort = 10000;

}
