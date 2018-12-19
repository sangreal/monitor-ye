package enn.monitor.security.gateway.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.beust.jcommander.JCommander;

import enn.monitor.security.gateway.handler.EnnMonitorSecurityHandler;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;
import enn.monitor.security.gateway.parameters.Parameters;

public class EnnMonitorSecurityGatewayServer {
	
	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) throws Exception {
		EnnMonitorMetricsGatewayGrpc metricsGrpc = null;
		
		Parameters parameters = new Parameters();
	    JCommander jc = new JCommander(parameters, args);
	    if (parameters.help) {
	    	jc.usage();
	    	return;
	    }
	    
	    metricsGrpc = new EnnMonitorMetricsGatewayGrpc();
	    metricsGrpc.startMetricsCollector();
	    
		Server server = new Server(parameters.listenPort);
        ServletContextHandler context = new ServletContextHandler();  
        context.setContextPath("/security");   //这里是请求的上下文，比如http://localhost:8090/MyServer  
		context.addFilter(CrossOriginFilter.class, "*", null);
        server.setHandler(context);
		EnnMonitorSecurityHandler h = new EnnMonitorSecurityHandler(parameters.gatewayServer, parameters.gatewayPort);
		ServletHolder sh = new ServletHolder(h);
        context.addServlet(sh, "/gateway");   //添加servlet，第一是具体的servlet，后面是请求的别名，在http请求中的路径

        logger.info("server start");
        server.start();  
        server.join(); 
	}

}
