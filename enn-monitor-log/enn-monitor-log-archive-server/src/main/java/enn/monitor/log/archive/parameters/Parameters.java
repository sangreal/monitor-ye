package enn.monitor.log.archive.parameters;

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
    
    // es
    @Parameter(
            names = "--es_url",
            description = "Host of ElasticSearch",
            required = false
    )
    //public String esUrl = "10.19.248.200:31931";
    public String esUrl = "10.19.140.200:29401";
    
    @Parameter(
            names = "--es_cluster_name",
            description = "Host of ElasticSearch",
            required = false
    )
    public String esClusterName = "es-log";
    
    // mongo
    @Parameter(
    	names = "--mongoUrl",
        description = "mongo url",
        required = false
    )
    public String mongoUrl = "mongodb://127.0.0.1:27017/EnnMonitorLogArchive";
    
    @Parameter(
    	names = "--dbName",
        description = "database name",
        required = false
    )
    public String dbName = "EnnMonitorLogArchive";
    
}
