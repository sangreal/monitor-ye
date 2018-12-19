package enn.monitor.log.config.gateway.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.beust.jcommander.JCommander;

import enn.monitor.log.config.gateway.analyseterm.EnnMonitorLogConfigGatewayAnalyseTermHandler;
import enn.monitor.log.config.gateway.batchId.EnnMonitorLogConfigGatewayBatchIdHandler;
import enn.monitor.log.config.gateway.parameters.Parameters;
import enn.monitor.log.config.gateway.template.EnnMonitorLogConfigGatewayTemplateHandler;
import enn.monitor.log.config.gateway.template.term.EnnMonitorLogConfigGatewayTemplateTermHandler;

public class EnnMonitorLogConfigGatewayServer {
	
	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) throws Exception {
		Parameters parameters = new Parameters();
	    JCommander jc = new JCommander(parameters, args);
	    if (parameters.help) {
	    	jc.usage();
	    	return;
	    }
	    
		Server server = new Server(parameters.listenPort);
		
        ServletContextHandler context = new ServletContextHandler();  
        context.setContextPath("/template");   //这里是请求的上下文，比如http://localhost:8090/MyServer  
        server.setHandler(context); 
        context.addServlet(new ServletHolder(new EnnMonitorLogConfigGatewayBatchIdHandler(parameters.configHost, parameters.configPort)), "/batchId");
        context.addServlet(new ServletHolder(new EnnMonitorLogConfigGatewayTemplateTermHandler(parameters.configHost, parameters.configPort)), "/term");
        context.addServlet(new ServletHolder(new EnnMonitorLogConfigGatewayTemplateHandler(parameters.configHost, parameters.configPort)), "/content");
        context.addServlet(new ServletHolder(new EnnMonitorLogConfigGatewayAnalyseTermHandler(parameters.configHost, parameters.configPort)), "/analyseterm");
        
        logger.info("server start");
        server.start();  
        server.join(); 
	}

}
