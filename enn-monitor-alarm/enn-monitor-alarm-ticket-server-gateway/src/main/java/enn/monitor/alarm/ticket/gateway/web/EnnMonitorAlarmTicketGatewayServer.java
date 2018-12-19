package enn.monitor.alarm.ticket.gateway.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.beust.jcommander.JCommander;

import enn.monitor.alarm.ticket.gateway.handler.EnnMonitorAlarmTicketGatewayHandler;
import enn.monitor.alarm.ticket.gateway.parameters.Parameters;
import enn.monitor.security.gateway.metrics.EnnMonitorGatewayParameter;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;

public class EnnMonitorAlarmTicketGatewayServer {
	
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
	    metricsGrpc.startMetricsCollector(
        		parameters.enableMetrics, new EnnMonitorGatewayParameter(parameters.gatewayServer, parameters.gatewayPort), 1, 
        		parameters.token);
	    
		Server server = new Server(parameters.listenPort);
        ServletContextHandler context = new ServletContextHandler();  
        context.setContextPath("/ticket");   //这里是请求的上下文，比如http://localhost:8090/MyServer  
        server.setHandler(context); 
        context.addServlet(new ServletHolder(new EnnMonitorAlarmTicketGatewayHandler(parameters.ticketServer, parameters.ticketPort)), "/alerts");   //添加servlet，第一是具体的servlet，后面是请求的别名，在http请求中的路径  
        
        logger.info("server start");
        server.start();  
        server.join(); 
	}

}
