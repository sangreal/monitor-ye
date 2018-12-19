package enn.monitor.log.config.event.util;

import com.mongodb.BasicDBObject;

import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerThread;
import enn.monitor.log.config.MongoConfig;
import enn.monitor.log.config.event.impl.EnnMonitorLogConfigEventImpl;
import enn.monitor.log.config.event.pipe.EnnMonitorLogConfigEventPipeServerImpl;
import enn.monitor.log.config.event.tablectl.EnnMonitorLogConfigEventDBTable;
import enn.monitor.log.config.event.tablectl.EnnMonitorLogConfigEventTableCtl;
import enn.monitor.log.config.event.tablectl.EnnMonitorLogConfigEventTableField;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

public class EnnMonitorLogConfigEventUtil {
	
	public static void init(ServerBuilder<?> serverBuilder, MongoAutoIncTableCtl autoIncTableCtl) throws Exception {
		EnnMonitorLogConfigEventTableCtl eventTableCtl = null;
		EnnMonitorFrameworkPipeDeletedTableCtl eventDeleteTableCtl = null;
		EnnMonitorLogConfigEventPipeServerImpl eventServerImpl = null;
		EnnMonitorFrameworkPipeServerThread eventPipeServer = null;
		
		eventTableCtl = new EnnMonitorLogConfigEventTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), EnnMonitorLogConfigEventDBTable.getEventTable());
	    eventDeleteTableCtl = new EnnMonitorFrameworkPipeDeletedTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), EnnMonitorLogConfigEventDBTable.getEventDeletedTable());
	    eventServerImpl = new EnnMonitorLogConfigEventPipeServerImpl(autoIncTableCtl, eventTableCtl, eventDeleteTableCtl);
	    eventPipeServer = new EnnMonitorFrameworkPipeServerThread(eventServerImpl);
	    eventPipeServer.start();
	    
		eventTableCtl = new EnnMonitorLogConfigEventTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), EnnMonitorLogConfigEventDBTable.getEventTable());
		EnnMonitorLogConfigEventUtil.createIndexes(eventTableCtl, eventDeleteTableCtl);
		
		serverBuilder.addService(ServerInterceptors.intercept(new EnnMonitorLogConfigEventImpl(eventTableCtl, eventDeleteTableCtl, eventPipeServer)));
	}
	
	public static void createIndexes(EnnMonitorLogConfigEventTableCtl tableCtl, EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl) throws Exception {
		BasicDBObject keys = null;
		BasicDBObject options = null;
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigEventTableField.IdFieldName, 1);
		options.put("name", "id_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigEventTableField.SeqNoFieldName, 1);
		options.put("name", "seqNo_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigEventTableField.EventKeyFieldName, 1);
		options.put("name", "eventKey_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigEventTableField.BelongToServiceIdFieldName, 1);
		options.put("name", "belongToServiceId_");
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, 1);
		options.put("name", "seqNo_");
		options.put("unique", true);
		deletedTableCtl.getMongoDBCtrl().createIndex(keys, options);
	}

}
