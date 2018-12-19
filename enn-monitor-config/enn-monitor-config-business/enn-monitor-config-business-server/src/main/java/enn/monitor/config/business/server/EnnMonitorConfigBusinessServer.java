package enn.monitor.config.business.server;

import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beust.jcommander.JCommander;
import com.mongodb.BasicDBObject;

import enn.monitor.config.business.MongoConfig;
import enn.monitor.config.business.parameters.Parameters;
import enn.monitor.config.business.topic.impl.EnnMonitorConfigBusinessTopicImpl;
import enn.monitor.config.business.topic.pipe.EnnMonitorConfigBusinessTopicPipeServerImpl;
import enn.monitor.config.business.topic.tablectl.EnnMonitorConfigBusinessTopicTableCtl;
import enn.monitor.config.business.topic.tablectl.EnnMonitorConfigBusinessTopicTableField;
import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerThread;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

public class EnnMonitorConfigBusinessServer {
	
	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws Exception {
		EnnMonitorMetricsGatewayGrpc metricsGrpc = null;
		
		MongoAutoIncTableCtl autoIncTableCtl = null;
		
		EnnMonitorConfigBusinessTopicTableCtl topicTableCtl = null;
		EnnMonitorFrameworkPipeDeletedTableCtl topicDeleteTableCtl = null;
		EnnMonitorConfigBusinessTopicPipeServerImpl topicServerImpl = null;
		EnnMonitorFrameworkPipeServerThread topicPipeServer = null;
		
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
		    
		    topicTableCtl = new EnnMonitorConfigBusinessTopicTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getTopicTable());
		    topicDeleteTableCtl = new EnnMonitorFrameworkPipeDeletedTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getTopicDeletedTable());
		    topicServerImpl = new EnnMonitorConfigBusinessTopicPipeServerImpl(autoIncTableCtl, topicTableCtl, topicDeleteTableCtl);
		    topicPipeServer = new EnnMonitorFrameworkPipeServerThread(topicServerImpl);
		    topicPipeServer.start();
		    
		    MongoConfig.setParameters(parameters);
		    initTopicMongo(topicTableCtl, topicDeleteTableCtl);
		    
		    Server server = ServerBuilder.forPort(parameters.listenPort)
		            .addService(ServerInterceptors.intercept(new EnnMonitorConfigBusinessTopicImpl(topicTableCtl, topicDeleteTableCtl, topicPipeServer)))
		            .executor(Executors.newFixedThreadPool(parameters.workThreadNum))
		            .build();
		    logger.info("server start");
		    server.start();
		    server.awaitTermination();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}
	
	private static void initTopicMongo(
			EnnMonitorConfigBusinessTopicTableCtl tableCtl, EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl) throws Exception {
		BasicDBObject keys = null;
		BasicDBObject options = null;
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorConfigBusinessTopicTableField.IdFieldName, 1);
		options.put("name", "id_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorConfigBusinessTopicTableField.SeqNoFieldName, 1);
		options.put("name", "seqNo_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);

		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, 1);
		options.put("name", "seqNo_");
		options.put("unique", true);
		deletedTableCtl.getMongoDBCtrl().createIndex(keys, options);
	}
	
}
