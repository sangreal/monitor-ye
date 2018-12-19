package enn.monitor.log.ai.parameters;

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
    	names = "--config_server",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public String configServer = "10.19.248.200";
    
    @Parameter(
    	names = "--config_port",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public int configPort = 30309;

    @Parameter(
    	names = "--log_config_server",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public String logConfigServer = "10.19.248.200";
    
    @Parameter(
    	names = "--log_config_port",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public int logConfigPort = 29415;
    
    @Parameter(
    	names = "--log_config_cache_server",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public String logConfigCacheServer = "10.19.248.200";
    
    @Parameter(
    	names = "--log_config_cache_port",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public int logConfigCachePort = 29307;
    
    @Parameter(
    	names = "--analyse_template_server",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public String analyseTemplateServer = "10.19.248.200";
    
    @Parameter(
    	names = "--analyse_template_port",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public int analyseTemplatePort = 29320;
    
    @Parameter(
    	names = "--analyse_server",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public String analyseServer = "10.19.248.200";
    
    @Parameter(
    	names = "--analyse_port",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public int analysePort = 30116;
    
    @Parameter(
    	names = "--analyse_storage_server",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public String analyseStorageServer = "10.19.248.200";
    
    @Parameter(
    	names = "--analyse_storage_port",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public int analyseStoragePort = 29304;
    
    @Parameter(
    	names = "--log_ga_server",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public String logGaServer = "10.19.248.200";
//    public String logGaServer = "127.0.0.1";
    
    @Parameter(
    	names = "--log_ga_port",
        description = "Listen port of OpenTSDB",
        required = false
    )
//    public int logGaPort = 10000;
    public int logGaPort = 29302;
    
    @Parameter(
    	names = "--gateway_server",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public String gatewayServer = "10.19.248.200";
    
    @Parameter(
    	names = "--gateway_port",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public int gatewayPort = 30111;
    
    @Parameter(
    	names = "--token",
        description = "Listen port of OpenTSDB",
        required = false
    )
    public String token = "DC3977242C767B9382D5B57EC4E55207";
        
}
