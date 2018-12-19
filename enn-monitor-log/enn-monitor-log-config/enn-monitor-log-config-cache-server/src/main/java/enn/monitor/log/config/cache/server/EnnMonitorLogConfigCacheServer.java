package enn.monitor.log.config.cache.server;

import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beust.jcommander.JCommander;

import enn.monitor.framework.pipe.client.EnnMonitorFrameworkPipeClientThread;
import enn.monitor.log.config.cache.event.EnnMonitorLogConfigCacheEventPipeClientImpl;
import enn.monitor.log.config.cache.impl.EnnMonitorLogConfigCacheImpl;
import enn.monitor.log.config.cache.parameters.Parameters;
import enn.monitor.log.config.cache.template.EnnMonitorLogConfigCacheTemplatePipeClientImpl;
import enn.monitor.log.config.event.client.EnnMonitorLogConfigEventClient;
import enn.monitor.log.config.template.client.EnnMonitorLogConfigTemplateClient;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

public class EnnMonitorLogConfigCacheServer {
	
	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) throws Exception {
		EnnMonitorMetricsGatewayGrpc metricsGrpc = null;
		
		EnnMonitorLogConfigCacheEventPipeClientImpl logEventClientImpl = null;
		EnnMonitorFrameworkPipeClientThread logEventClientThread = null;
		
		EnnMonitorLogConfigCacheTemplatePipeClientImpl logTemplateClientImpl = null;
		EnnMonitorFrameworkPipeClientThread logTemplateClientThread = null;
		
		Parameters parameters = new Parameters();
	    JCommander jc = new JCommander(parameters, args);
	    if (parameters.help) {
	    	jc.usage();
	    	return;
	    }
		
		metricsGrpc = new EnnMonitorMetricsGatewayGrpc();
	    metricsGrpc.startMetricsCollector();
	    
	    logEventClientImpl = 
	    		new EnnMonitorLogConfigCacheEventPipeClientImpl(
	    				new EnnMonitorLogConfigEventClient(parameters.logConfigIp, parameters.logConfigPort));
	    logEventClientThread = new EnnMonitorFrameworkPipeClientThread(logEventClientImpl);
	    new Thread(logEventClientThread).start();
	    
	    logTemplateClientImpl = 
	    		new EnnMonitorLogConfigCacheTemplatePipeClientImpl(
	    				new EnnMonitorLogConfigTemplateClient(parameters.logConfigIp, parameters.logConfigPort));
	    logTemplateClientThread = new EnnMonitorFrameworkPipeClientThread(logTemplateClientImpl);
	    new Thread(logTemplateClientThread).start();
	    
	    Server server = ServerBuilder.forPort(parameters.listenPort)
	            .addService(ServerInterceptors.intercept(new EnnMonitorLogConfigCacheImpl()))
	            .executor(Executors.newFixedThreadPool(parameters.workThreadNum))
	            .build();
	    logger.info("server start");
	    server.start();
	    server.awaitTermination();
	}

}
