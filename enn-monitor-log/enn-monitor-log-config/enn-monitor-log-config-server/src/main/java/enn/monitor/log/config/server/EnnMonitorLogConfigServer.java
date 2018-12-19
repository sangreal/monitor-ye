package enn.monitor.log.config.server;

import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beust.jcommander.JCommander;

import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.log.config.MongoConfig;
import enn.monitor.log.config.analyse.term.util.EnnMonitorLogConfigAnalyseTermUtil;
import enn.monitor.log.config.common.util.EnnMonitorLogConfigCommonUtil;
import enn.monitor.log.config.event.util.EnnMonitorLogConfigEventUtil;
import enn.monitor.log.config.logformat.util.EnnMonitorLogConfigLogformatUtil;
import enn.monitor.log.config.parameters.Parameters;
import enn.monitor.log.config.template.term.util.EnnMonitorLogConfigTemplateTermUtil;
import enn.monitor.log.config.template.util.EnnMonitorLogConfigTemplateUtil;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class EnnMonitorLogConfigServer {
	
	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws Exception {
		EnnMonitorMetricsGatewayGrpc metricsGrpc = null;
		
		MongoAutoIncTableCtl autoIncTableCtl = null;
		
		try {
			Parameters parameters = new Parameters();
		    JCommander jc = new JCommander(parameters, args);
		    if (parameters.help) {
		    	jc.usage();
		    	return;
		    }
		    
		    MongoConfig.setParameters(parameters);

			metricsGrpc = new EnnMonitorMetricsGatewayGrpc();
		    metricsGrpc.startMetricsCollector();

			autoIncTableCtl = new MongoAutoIncTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getAutoIncTable());
		    
		    MongoConfig.setParameters(parameters);
		    
		    ServerBuilder<?> serverBuilder = ServerBuilder.forPort(parameters.listenPort)
		    		.executor(Executors.newFixedThreadPool(parameters.workThreadNum));
		    
		    EnnMonitorLogConfigAnalyseTermUtil.init(serverBuilder, autoIncTableCtl);
		    EnnMonitorLogConfigEventUtil.init(serverBuilder, autoIncTableCtl);
		    EnnMonitorLogConfigLogformatUtil.init(serverBuilder, autoIncTableCtl);
		    EnnMonitorLogConfigTemplateUtil.init(serverBuilder, autoIncTableCtl);
		    EnnMonitorLogConfigCommonUtil.init(serverBuilder, autoIncTableCtl);
		    EnnMonitorLogConfigTemplateTermUtil.init(serverBuilder, autoIncTableCtl);
		    
		    Server server = serverBuilder.build();
		    
		    logger.info("server start");
		    server.start();
		    server.awaitTermination();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}

}
