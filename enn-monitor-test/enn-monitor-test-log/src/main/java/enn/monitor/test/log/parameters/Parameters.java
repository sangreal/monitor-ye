package enn.monitor.test.log.parameters;

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
    	names = "--batchNum",
        description = "Whether enable metrics collecting or not.",
        required = false
    )
    public int batchNum = 1;
    
    @Parameter(
    	names = "--waitMills",
        description = "Whether enable metrics collecting or not.",
        required = false
    )
    public long waitMills = 1;
    
}
