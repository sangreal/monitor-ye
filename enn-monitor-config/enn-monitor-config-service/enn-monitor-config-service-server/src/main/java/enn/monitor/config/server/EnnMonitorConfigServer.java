package enn.monitor.config.server;

import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beust.jcommander.JCommander;
import com.mongodb.BasicDBObject;

import enn.monitor.config.MongoConfig;
import enn.monitor.config.cluster.impl.EnnMonitorConfigClusterHandler;
import enn.monitor.config.cluster.impl.EnnMonitorConfigClusterImpl;
import enn.monitor.config.cluster.pipe.EnnMonitorConfigClusterPipeServerImpl;
import enn.monitor.config.cluster.tablectl.EnnMonitorConfigClusterTableCtl;
import enn.monitor.config.cluster.tablectl.EnnMonitorConfigClusterTableField;
import enn.monitor.config.data.EnnMonitorConfigDataThread;
import enn.monitor.config.parameters.Parameters;
import enn.monitor.config.service.impl.EnnMonitorConfigServiceHandler;
import enn.monitor.config.service.impl.EnnMonitorConfigServiceImpl;
import enn.monitor.config.service.pipe.EnnMonitorConfigServicePipeServerImpl;
import enn.monitor.config.service.tablectl.EnnMonitorConfigServiceTableCtl;
import enn.monitor.config.service.tablectl.EnnMonitorConfigServiceTableField;
import enn.monitor.config.serviceline.impl.EnnMonitorConfigServiceLineHandler;
import enn.monitor.config.serviceline.impl.EnnMonitorConfigServiceLineImpl;
import enn.monitor.config.serviceline.pipe.EnnMonitorConfigServiceLinePipeServerImpl;
import enn.monitor.config.serviceline.tablectl.EnnMonitorConfigServiceLineTableCtl;
import enn.monitor.config.serviceline.tablectl.EnnMonitorConfigServiceLineTableField;
import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerThread;
import enn.monitor.framework.tracing.EnnTracer;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;
import enn.monitor.security.gateway.trace.TracerBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

public class EnnMonitorConfigServer {
	
	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws Exception {
		EnnMonitorMetricsGatewayGrpc metricsGrpc = null;
		
		MongoAutoIncTableCtl autoIncTableCtl = null;
		
		EnnMonitorConfigServiceTableCtl serviceTableCtl = null;
		EnnMonitorFrameworkPipeDeletedTableCtl serviceDeleteTableCtl = null;
		EnnMonitorConfigServicePipeServerImpl serviceServerImpl = null;
		EnnMonitorFrameworkPipeServerThread servicePipeServer = null;
		EnnMonitorConfigServiceHandler serviceHandler = null;
		
		EnnMonitorConfigServiceLineTableCtl serviceLineTableCtl = null;
		EnnMonitorFrameworkPipeDeletedTableCtl serviceLineDeleteTableCtl = null;
		EnnMonitorConfigServiceLinePipeServerImpl serviceLineServerImpl = null;
		EnnMonitorFrameworkPipeServerThread serviceLinePipeServer = null;
		EnnMonitorConfigServiceLineHandler serviceLineHandler = null;

		EnnMonitorConfigClusterTableCtl        clusterTableCtl       = null;
		EnnMonitorFrameworkPipeDeletedTableCtl clusterDeleteTableCtl = null;
		EnnMonitorConfigClusterPipeServerImpl  clusterServerImpl     = null;
		EnnMonitorFrameworkPipeServerThread    clusterPipeServer     = null;
		EnnMonitorConfigClusterHandler         clusterHandler        = null;
		
		try {
			Parameters parameters = new Parameters();
		    JCommander jc = new JCommander(parameters, args);
		    if (parameters.help) {
		    	jc.usage();
		    	return;
		    }
		    
		    MongoConfig.setParameters(parameters);

		    EnnTracer tracer = TracerBuilder.get();
		    metricsGrpc = new EnnMonitorMetricsGatewayGrpc(tracer);
			logger.info("Tracer config: " + tracer.getConfig());
		    metricsGrpc.startMetricsCollector();
		    
		    autoIncTableCtl = new MongoAutoIncTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getAutoIncTable());
		    
		    serviceTableCtl = new EnnMonitorConfigServiceTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getServiceTable());
		    serviceDeleteTableCtl = new EnnMonitorFrameworkPipeDeletedTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getServiceDeletedTable());
		    serviceServerImpl = new EnnMonitorConfigServicePipeServerImpl(autoIncTableCtl, serviceTableCtl, serviceDeleteTableCtl);
		    servicePipeServer = new EnnMonitorFrameworkPipeServerThread(serviceServerImpl);
		    servicePipeServer.start();
		    serviceHandler = new EnnMonitorConfigServiceHandler(serviceTableCtl, serviceDeleteTableCtl, servicePipeServer);
		    
		    serviceLineTableCtl = new EnnMonitorConfigServiceLineTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getServiceLineTable());
		    serviceLineDeleteTableCtl = new EnnMonitorFrameworkPipeDeletedTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getServiceLineDeletedTable());
		    serviceLineServerImpl = new EnnMonitorConfigServiceLinePipeServerImpl(autoIncTableCtl, serviceLineTableCtl, serviceLineDeleteTableCtl);
		    serviceLinePipeServer = new EnnMonitorFrameworkPipeServerThread(serviceLineServerImpl);
		    serviceLinePipeServer.start();
		    serviceLineHandler = new EnnMonitorConfigServiceLineHandler(serviceLineTableCtl, serviceLineDeleteTableCtl, serviceLinePipeServer);

		    clusterTableCtl = new EnnMonitorConfigClusterTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getClusterTable());
		    clusterDeleteTableCtl = new EnnMonitorFrameworkPipeDeletedTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getClusterDeletedTable());
		    clusterServerImpl = new EnnMonitorConfigClusterPipeServerImpl(autoIncTableCtl, clusterTableCtl, clusterDeleteTableCtl);
		    clusterPipeServer = new EnnMonitorFrameworkPipeServerThread(clusterServerImpl);
		    clusterPipeServer.start();
		    clusterHandler = new EnnMonitorConfigClusterHandler(clusterTableCtl, clusterDeleteTableCtl, clusterPipeServer);

		    MongoConfig.setParameters(parameters);
		    initServiceMongo(serviceTableCtl, serviceDeleteTableCtl);
		    initServiceLineMongo(serviceLineTableCtl, serviceLineDeleteTableCtl);
			initClusterMongo(clusterTableCtl, clusterDeleteTableCtl);
			
			new Thread(new EnnMonitorConfigDataThread(serviceTableCtl, serviceLineTableCtl, clusterTableCtl,
					serviceHandler, serviceLineHandler, clusterHandler)).start();
		    
		    Server server = ServerBuilder.forPort(parameters.listenPort)
		            .addService(ServerInterceptors.intercept(new EnnMonitorConfigServiceImpl(serviceHandler)))
		            .addService(ServerInterceptors.intercept(new EnnMonitorConfigServiceLineImpl(serviceLineHandler)))
		            .addService(ServerInterceptors.intercept(new EnnMonitorConfigClusterImpl(clusterHandler)))
					.executor(Executors.newFixedThreadPool(parameters.workThreadNum))
		            .build();
		    logger.info("server start");
		    server.start();
		    server.awaitTermination();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}
	
	private static void initServiceMongo(
			EnnMonitorConfigServiceTableCtl serviceTableCtl, EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl) throws Exception {
		BasicDBObject keys = null;
		BasicDBObject options = null;
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorConfigServiceTableField.IdFieldName, 1);
		options.put("name", "id_");
		options.put("unique", true);
		serviceTableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorConfigServiceTableField.SeqNoFieldName, 1);
		options.put("name", "seqNo_");
		options.put("unique", true);
		serviceTableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorConfigServiceTableField.TokenFieldName, 1);
		options.put("name", "token_");
		options.put("unique", true);
		serviceTableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorConfigServiceTableField.ServiceNameFieldName, 1);
		options.put("name", "serviceName_");
		serviceTableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorConfigServiceTableField.BelongToServiceLineFieldName, 1);
		options.put("name", "belongToServiceLine_");
		serviceTableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, 1);
		options.put("name", "seqNo_");
		options.put("unique", true);
		deletedTableCtl.getMongoDBCtrl().createIndex(keys, options);
	}
	
	private static void initServiceLineMongo(
			EnnMonitorConfigServiceLineTableCtl serviceLineTableCtl, EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl) throws Exception {
		BasicDBObject keys = null;
		BasicDBObject options = null;
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorConfigServiceLineTableField.IdFieldName, 1);
		options.put("name", "id_");
		options.put("unique", true);
		serviceLineTableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorConfigServiceLineTableField.SeqNoFieldName, 1);
		options.put("name", "seqNo_");
		options.put("unique", true);
		serviceLineTableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorConfigServiceLineTableField.ServiceLineNameFieldName, 1);
		options.put("name", "serviceLineName_");
		serviceLineTableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorConfigServiceLineTableField.BelongToClusterFieldName, 1);
		options.put("name", "belongToCluster_");
		serviceLineTableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, 1);
		options.put("name", "seqNo_");
		options.put("unique", true);
		deletedTableCtl.getMongoDBCtrl().createIndex(keys, options);
	}

	private static void initClusterMongo(
			EnnMonitorConfigClusterTableCtl clusterTableCtl, EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl) throws Exception {
		BasicDBObject keys = null;
		BasicDBObject options = null;

		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorConfigClusterTableField.IdFieldName, 1);
		options.put("name", "id_");
		options.put("unique", true);
		clusterTableCtl.getMongoDBCtrl().createIndex(keys, options);

		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorConfigClusterTableField.SeqNoFieldName, 1);
		options.put("name", "seqNo_");
		options.put("unique", true);
		clusterTableCtl.getMongoDBCtrl().createIndex(keys, options);

		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorConfigClusterTableField.ClusterNameFieldName, 1);
		options.put("name", "clusterName_");
		options.put("unique", true);
		clusterTableCtl.getMongoDBCtrl().createIndex(keys, options);

		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, 1);
		options.put("name", "seqNo_");
		options.put("unique", true);
		deletedTableCtl.getMongoDBCtrl().createIndex(keys, options);
	}
}
