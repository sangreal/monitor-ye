package enn.monitor.log.archive.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.beust.jcommander.JCommander;

import enn.monitor.log.archive.config.MongoConfig;
import enn.monitor.log.archive.handler.EnnMonitorLogArchiveConfigHandler;
import enn.monitor.log.archive.handler.EnnMonitorLogArchiveDiskHandler;
import enn.monitor.log.archive.parameters.Parameters;
import enn.monitor.log.archive.tablectl.EnnMonitorLogArchiveConfigCtl;
import enn.monitor.log.archive.task.EnnMonitorLogArchiveTask;
import enn.monitor.log.archive.util.EnnMonitorLogElasticsearchUtil;
import enn.monitor.log.archive.util.EnnMonitorLogStorageUtil;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;

public class EnnMonitorLogArchiveServer {
	
	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) throws Exception {
		EnnMonitorMetricsGatewayGrpc metricsGrpc = null;
		
		EnnMonitorLogArchiveConfigCtl archiveConfigCtl = null;
		
		EnnMonitorLogArchiveTask ennMonitorLogArchiveTask = null;
		
		Parameters parameters = new Parameters();
	    JCommander jc = new JCommander(parameters, args);
	    if (parameters.help) {
	    	jc.usage();
	    	return;
	    }
	    
	    MongoConfig.setParameters(parameters);
	    
	    metricsGrpc = new EnnMonitorMetricsGatewayGrpc();
	    metricsGrpc.startMetricsCollector();
	    
	    EnnMonitorLogElasticsearchUtil.init(parameters.esUrl, parameters.esClusterName);
	    EnnMonitorLogStorageUtil.init(parameters.esClusterName);
	    archiveConfigCtl = new EnnMonitorLogArchiveConfigCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getLogArchiveTable());
	    
	    ennMonitorLogArchiveTask = new EnnMonitorLogArchiveTask(archiveConfigCtl);
	    new Thread(ennMonitorLogArchiveTask).start();
		Server server = new Server(parameters.listenPort);
		
		EnnMonitorLogArchiveConfigHandler.init(archiveConfigCtl);
        ServletContextHandler context = new ServletContextHandler();  
        context.setContextPath("/log/archive");   //这里是请求的上下文，比如http://localhost:8090/MyServer  
        server.setHandler(context); 
        context.addServlet(new ServletHolder(new EnnMonitorLogArchiveConfigHandler.EnnMonitorLogArchiveConfigPutHandler(ennMonitorLogArchiveTask)), "/config/put");
        context.addServlet(new ServletHolder(new EnnMonitorLogArchiveConfigHandler.EnnMonitorLogArchiveConfigGetHandler()), "/config/get");
        context.addServlet(new ServletHolder(new EnnMonitorLogArchiveDiskHandler()), "/disk/usage");
        
        logger.info("server start");
        server.start();  
        server.join(); 
	}

}
