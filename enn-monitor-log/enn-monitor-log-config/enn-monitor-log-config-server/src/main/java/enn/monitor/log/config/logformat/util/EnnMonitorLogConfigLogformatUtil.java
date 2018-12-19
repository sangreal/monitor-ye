package enn.monitor.log.config.logformat.util;

import com.mongodb.BasicDBObject;

import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerThread;
import enn.monitor.log.config.MongoConfig;
import enn.monitor.log.config.logformat.impl.EnnMonitorLogConfigLogformatImpl;
import enn.monitor.log.config.logformat.pipe.EnnMonitorLogConfigLogformatPipeServerImpl;
import enn.monitor.log.config.logformat.tablectl.EnnMonitorLogConfigLogformatDBTable;
import enn.monitor.log.config.logformat.tablectl.EnnMonitorLogConfigLogformatTableCtl;
import enn.monitor.log.config.logformat.tablectl.EnnMonitorLogConfigLogformatTableField;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

public class EnnMonitorLogConfigLogformatUtil {
	
	public static void init(ServerBuilder<?> serverBuilder, MongoAutoIncTableCtl autoIncTableCtl) throws Exception {
		EnnMonitorLogConfigLogformatTableCtl logformatTableCtl = null;
		EnnMonitorFrameworkPipeDeletedTableCtl logformatDeleteTableCtl = null;
		EnnMonitorLogConfigLogformatPipeServerImpl logformatServerImpl = null;
		EnnMonitorFrameworkPipeServerThread logformatPipeServer = null;
		
		logformatTableCtl = new EnnMonitorLogConfigLogformatTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), EnnMonitorLogConfigLogformatDBTable.getLogformatTable());
	    logformatDeleteTableCtl = new EnnMonitorFrameworkPipeDeletedTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), EnnMonitorLogConfigLogformatDBTable.getLogformatDeletedTable());
	    logformatServerImpl = new EnnMonitorLogConfigLogformatPipeServerImpl(autoIncTableCtl, logformatTableCtl, logformatDeleteTableCtl);
	    logformatPipeServer = new EnnMonitorFrameworkPipeServerThread(logformatServerImpl);
	    logformatPipeServer.start();
	    
	    EnnMonitorLogConfigLogformatUtil.createIndexes(logformatTableCtl, logformatDeleteTableCtl);
	    
	    serverBuilder.addService(ServerInterceptors.intercept(new EnnMonitorLogConfigLogformatImpl(logformatTableCtl, logformatDeleteTableCtl, logformatPipeServer)));
	}
	
	public static void createIndexes(EnnMonitorLogConfigLogformatTableCtl tableCtl, EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl) throws Exception {
		BasicDBObject keys = null;
		BasicDBObject options = null;
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigLogformatTableField.IdFieldName, 1);
		options.put("name", "id_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigLogformatTableField.SeqNoFieldName, 1);
		options.put("name", "seqNo_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigLogformatTableField.BelongToServiceIdFieldName, 1);
		options.put("name", "belongToServiceIdFieldName_");
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
