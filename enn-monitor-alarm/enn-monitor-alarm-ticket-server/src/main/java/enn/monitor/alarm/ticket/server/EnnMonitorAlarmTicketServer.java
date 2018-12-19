package enn.monitor.alarm.ticket.server;

import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beust.jcommander.JCommander;
import com.mongodb.BasicDBObject;

import enn.monitor.alarm.config.email.client.EnnMonitorAlarmConfigEmailClient;
import enn.monitor.alarm.ticket.autocover.channel.EnnMonitorAlarmTicketAutoRecoverChannel;
import enn.monitor.alarm.ticket.cache.channel.EnnMonitorAlarmTicketCacheChannel;
import enn.monitor.alarm.ticket.config.MongoConfig;
import enn.monitor.alarm.ticket.email.channel.EnnMonitorAlarmTicketEmailChannel;
import enn.monitor.alarm.ticket.email.pipe.EnnMonitorAlarmTicketEmailCache;
import enn.monitor.alarm.ticket.email.pipe.EnnMonitorAlarmTicketEmailPipeClientImpl;
import enn.monitor.alarm.ticket.impl.EnnMonitorAlarmTicketHandler;
import enn.monitor.alarm.ticket.impl.EnnMonitorAlarmTicketImpl;
import enn.monitor.alarm.ticket.parameters.Parameters;
import enn.monitor.alarm.ticket.pipe.EnnMonitorAlarmTicketPipeServerImpl;
import enn.monitor.alarm.ticket.tablectl.EnnMonitorAlarmTicketTableCtl;
import enn.monitor.alarm.ticket.tablectl.EnnMonitorAlarmTicketTableField;
import enn.monitor.framework.common.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.framework.log.grpc.EnnMonitorGrpcServerInterceptor;
import enn.monitor.framework.pipe.client.EnnMonitorFrameworkPipeClientThread;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerThread;
import enn.monitor.security.gateway.metrics.EnnMonitorGatewayParameter;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

public class EnnMonitorAlarmTicketServer {
	
	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws Exception {
		EnnMonitorMetricsGatewayGrpc metricsGrpc = null;
		
		EnnMonitorAlarmTicketTableCtl tableCtl = null;
		EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl = null;
		MongoAutoIncTableCtl autoIncTableCtl = null;
		
		EnnMonitorAlarmTicketPipeServerImpl serverImpl = null;
		EnnMonitorFrameworkPipeServerThread pipeServer = null;
		
		EnnMonitorAlarmTicketCacheChannel ticketCacheChannel = null;
		EnnMonitorAlarmTicketAutoRecoverChannel autoRecoverChannel = null;
		EnnMonitorAlarmTicketEmailChannel emailChannel = null;
		
		EnnMonitorAlarmTicketHandler ticketHandler = null;
		
		EnnMonitorAlarmTicketEmailCache emailCache = new EnnMonitorAlarmTicketEmailCache();
		
		EnnMonitorAlarmTicketEmailPipeClientImpl clientImpl = null;
		EnnMonitorFrameworkPipeClientThread clientThread = null;
		
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
		    
		    clientImpl = new EnnMonitorAlarmTicketEmailPipeClientImpl(new EnnMonitorAlarmConfigEmailClient(parameters.configServer, parameters.configPort), emailCache);
	        clientThread = new EnnMonitorFrameworkPipeClientThread(clientImpl);
	        new Thread(clientThread).start();
		    
		    tableCtl = new EnnMonitorAlarmTicketTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getTicketTable());
		    deletedTableCtl = new EnnMonitorFrameworkPipeDeletedTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getTicketDeletedTable());
		    autoIncTableCtl = new MongoAutoIncTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getAutoIncTable());
		    
		    serverImpl = new EnnMonitorAlarmTicketPipeServerImpl(autoIncTableCtl, tableCtl, deletedTableCtl);
		    pipeServer = new EnnMonitorFrameworkPipeServerThread(serverImpl);
		    pipeServer.start();
		    
		    ticketHandler = new EnnMonitorAlarmTicketHandler(autoIncTableCtl, tableCtl, deletedTableCtl);
		    
		    autoRecoverChannel = new EnnMonitorAlarmTicketAutoRecoverChannel();
		    autoRecoverChannel.start();
		    
		    emailChannel = new EnnMonitorAlarmTicketEmailChannel(emailCache);
		    emailChannel.start();
		    
		    ticketCacheChannel = new EnnMonitorAlarmTicketCacheChannel(ticketHandler, pipeServer, autoRecoverChannel, emailChannel);
		    ticketHandler.setEnnMonitorAlarmTicketCacheChannel(ticketCacheChannel);
		    autoRecoverChannel.setEnnMonitorAlarmTicketCacheChannel(ticketCacheChannel);
		    emailChannel.setEnnMonitorAlarmTicketCacheChannel(ticketCacheChannel);
		    ticketCacheChannel.start();
		    
		    MongoConfig.setParameters(parameters);
		    initMongo(tableCtl, deletedTableCtl);
		    
		    Server server = ServerBuilder.forPort(parameters.listenPort)
		            .addService(ServerInterceptors.intercept(new EnnMonitorAlarmTicketImpl(ticketHandler), EnnMonitorGrpcServerInterceptor.create()))
		            .executor(Executors.newFixedThreadPool(parameters.workThreadNum))
		            .build();
		    logger.info("server start");
		    server.start();
		    server.awaitTermination();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}
	
	private static void initMongo(
			EnnMonitorAlarmTicketTableCtl tableCtl, EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl) throws Exception {
		BasicDBObject keys = null;
		BasicDBObject options = null;
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorAlarmTicketTableField.IdFieldName, 1);
		options.put("name", "id_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorAlarmTicketTableField.SeqNoFieldName, 1);
		options.put("name", "seqNo_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorAlarmTicketTableField.TicketKeyFieldName, 1);
		options.put("name", "ticketKey_");
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
