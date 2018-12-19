package enn.monitor.log.analyse.storage.server;

import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beust.jcommander.JCommander;
import com.mongodb.BasicDBObject;

import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerThread;
import enn.monitor.log.analyse.storage.config.MongoConfig;
import enn.monitor.log.analyse.storage.impl.EnnMonitorLogAnalyseStorageImpl;
import enn.monitor.log.analyse.storage.parameters.Parameters;
import enn.monitor.log.analyse.storage.pipe.EnnMonitorLogAnalyseStoragePipeServerImpl;
import enn.monitor.log.analyse.storage.tablectl.EnnMonitorLogAnalyseStorageTableCtl;
import enn.monitor.log.analyse.storage.tablectl.EnnMonitorLogAnalyseStorageTableField;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

public class EnnMonitorLogAnalyseStorageServer {
	
	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws Exception {
		MongoAutoIncTableCtl autoIncTableCtl = null;
		
		EnnMonitorLogAnalyseStorageTableCtl analyseStorageTableCtl = null;
		EnnMonitorFrameworkPipeDeletedTableCtl analyseStorageDeleteTableCtl = null;
		EnnMonitorLogAnalyseStoragePipeServerImpl analyseStorageServerImpl = null;
		EnnMonitorFrameworkPipeServerThread analyseStoragePipeServer = null;
		
		try {
			Parameters parameters = new Parameters();
		    JCommander jc = new JCommander(parameters, args);
		    if (parameters.help) {
		    	jc.usage();
		    	return;
		    }
		    
		    MongoConfig.setParameters(parameters);
		    
		    autoIncTableCtl = new MongoAutoIncTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getAutoIncTable());
		    
		    analyseStorageTableCtl = new EnnMonitorLogAnalyseStorageTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getLogAnalyseStorageTable());
		    analyseStorageDeleteTableCtl = new EnnMonitorFrameworkPipeDeletedTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getLogAnalyseStorageDeletedTable());
		    analyseStorageServerImpl = new EnnMonitorLogAnalyseStoragePipeServerImpl(autoIncTableCtl, analyseStorageTableCtl, analyseStorageDeleteTableCtl);
		    analyseStoragePipeServer = new EnnMonitorFrameworkPipeServerThread(analyseStorageServerImpl);
		    analyseStoragePipeServer.start();
		    
		    MongoConfig.setParameters(parameters);
		    initAnalyseStorageMongo(analyseStorageTableCtl, analyseStorageDeleteTableCtl);
		    
		    Server server = ServerBuilder.forPort(parameters.listenPort)
		            .addService(ServerInterceptors.intercept(new EnnMonitorLogAnalyseStorageImpl(analyseStoragePipeServer, analyseStorageTableCtl)))
		            .executor(Executors.newFixedThreadPool(parameters.workThreadNum))
		            .build();
		    logger.info("server start");
		    server.start();
		    server.awaitTermination();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}
	
	private static void initAnalyseStorageMongo(
			EnnMonitorLogAnalyseStorageTableCtl tableCtl, EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl) throws Exception {
		BasicDBObject keys = null;
		BasicDBObject options = null;
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogAnalyseStorageTableField.IdFieldName, 1);
		options.put("name", "id_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogAnalyseStorageTableField.SeqNoFieldName, 1);
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
