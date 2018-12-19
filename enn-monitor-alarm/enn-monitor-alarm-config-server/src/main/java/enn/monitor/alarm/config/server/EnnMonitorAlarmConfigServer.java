package enn.monitor.alarm.config.server;

import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beust.jcommander.JCommander;
import com.mongodb.BasicDBObject;

import enn.monitor.alarm.config.config.MongoConfig;
import enn.monitor.alarm.config.email.impl.EnnMonitorAlarmConfigEmailImpl;
import enn.monitor.alarm.config.email.pipe.EnnMonitorAlarmConfigEmailPipeServerImpl;
import enn.monitor.alarm.config.email.tablectl.EnnMonitorAlarmConfigEmailTableCtl;
import enn.monitor.alarm.config.email.tablectl.EnnMonitorAlarmConfigEmailTableField;
import enn.monitor.alarm.config.parameters.Parameters;
import enn.monitor.framework.common.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.framework.log.grpc.EnnMonitorGrpcServerInterceptor;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerThread;
import enn.monitor.security.gateway.metrics.EnnMonitorGatewayParameter;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

public class EnnMonitorAlarmConfigServer {
	
	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws Exception {
		EnnMonitorMetricsGatewayGrpc metricsGrpc = null;
		
		EnnMonitorAlarmConfigEmailTableCtl emailTableCtl = null;
		EnnMonitorFrameworkPipeDeletedTableCtl deletedEmailTableCtl = null;
		MongoAutoIncTableCtl autoIncEmailTableCtl = null;
		EnnMonitorAlarmConfigEmailPipeServerImpl emailServerImpl = null;
		EnnMonitorFrameworkPipeServerThread emailPipeServer = null;
		
		try {
			Parameters parameters = new Parameters();
		    JCommander jc = new JCommander(parameters, args);
		    if (parameters.help) {
		    	jc.usage();
		    	return;
		    }
		    
		    MongoConfig.setParameters(parameters);
		    
		    metricsGrpc = new EnnMonitorMetricsGatewayGrpc();
		    metricsGrpc.startMetricsCollector(
	        		parameters.enableMetrics, new EnnMonitorGatewayParameter(parameters.gatewayServer, parameters.gatewayPort), 1, 
	        		parameters.token);
		    
		    emailTableCtl = new EnnMonitorAlarmConfigEmailTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getEnnMonitorAlarmEmailTable());
		    deletedEmailTableCtl = new EnnMonitorFrameworkPipeDeletedTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getEnnMonitorAlarmEmailDeletedTable());
		    autoIncEmailTableCtl = new MongoAutoIncTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getEnnMonitorAlarmEmailAutoIncTable());
		    emailServerImpl = new EnnMonitorAlarmConfigEmailPipeServerImpl(autoIncEmailTableCtl, emailTableCtl, deletedEmailTableCtl);
		    emailPipeServer = new EnnMonitorFrameworkPipeServerThread(emailServerImpl);
		    emailPipeServer.start();
		    
		    MongoConfig.setParameters(parameters);
		    initEmailMongo(emailTableCtl, deletedEmailTableCtl);
		    
		    Server server = ServerBuilder.forPort(parameters.listenPort)
		            .addService(ServerInterceptors.intercept(new EnnMonitorAlarmConfigEmailImpl(emailTableCtl, deletedEmailTableCtl, emailPipeServer), EnnMonitorGrpcServerInterceptor.create()))
		            .executor(Executors.newFixedThreadPool(parameters.workThreadNum))
		            .build();
		    logger.info("server start");
		    server.start();
		    server.awaitTermination();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}
	
	private static void initEmailMongo(EnnMonitorAlarmConfigEmailTableCtl tableCtl, EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl)
			throws Exception {
		BasicDBObject keys = null;
		BasicDBObject options = null;
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorAlarmConfigEmailTableField.IdFieldName, 1);
		options.put("name", "id_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorAlarmConfigEmailTableField.SeqNoFieldName, 1);
		options.put("name", "seqNo_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorAlarmConfigEmailTableField.GroupNameFieldName, 1);
		options.put("name", "groupName_");
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
