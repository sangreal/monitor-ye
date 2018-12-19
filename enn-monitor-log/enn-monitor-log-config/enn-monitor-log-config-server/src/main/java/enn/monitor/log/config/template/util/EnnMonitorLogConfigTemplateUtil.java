package enn.monitor.log.config.template.util;

import com.mongodb.BasicDBObject;

import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerThread;
import enn.monitor.log.config.MongoConfig;
import enn.monitor.log.config.template.impl.EnnMonitorLogConfigTemplateImpl;
import enn.monitor.log.config.template.pipe.EnnMonitorLogConfigTemplatePipeServerImpl;
import enn.monitor.log.config.template.tablectl.EnnMonitorLogConfigTemplateDBTable;
import enn.monitor.log.config.template.tablectl.EnnMonitorLogConfigTemplateTableCtl;
import enn.monitor.log.config.template.tablectl.EnnMonitorLogConfigTemplateTableField;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

public class EnnMonitorLogConfigTemplateUtil {
	
	public static void init(ServerBuilder<?> serverBuilder, MongoAutoIncTableCtl autoIncTableCtl) throws Exception {
		EnnMonitorLogConfigTemplateTableCtl templateTableCtl = null;
		EnnMonitorFrameworkPipeDeletedTableCtl templateDeleteTableCtl = null;
		EnnMonitorLogConfigTemplatePipeServerImpl templateServerImpl = null;
		EnnMonitorFrameworkPipeServerThread templatePipeServer = null;
		
		templateTableCtl = new EnnMonitorLogConfigTemplateTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), EnnMonitorLogConfigTemplateDBTable.getTemplateTable());
	    templateDeleteTableCtl = new EnnMonitorFrameworkPipeDeletedTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), EnnMonitorLogConfigTemplateDBTable.getTemplateDeletedTable());
	    templateServerImpl = new EnnMonitorLogConfigTemplatePipeServerImpl(autoIncTableCtl, templateTableCtl, templateDeleteTableCtl);
	    templatePipeServer = new EnnMonitorFrameworkPipeServerThread(templateServerImpl);
	    templatePipeServer.start();
	    
	    EnnMonitorLogConfigTemplateUtil.createIndexes(templateTableCtl, templateDeleteTableCtl);
	    
	    serverBuilder.addService(ServerInterceptors.intercept(new EnnMonitorLogConfigTemplateImpl(templateTableCtl, templateDeleteTableCtl, templatePipeServer)));
	}
	
	public static void createIndexes(EnnMonitorLogConfigTemplateTableCtl tableCtl, EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl) throws Exception {
		BasicDBObject keys = null;
		BasicDBObject options = null;
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigTemplateTableField.IdFieldName, 1);
		options.put("name", "id_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigTemplateTableField.SeqNoFieldName, 1);
		options.put("name", "seqNo_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigTemplateTableField.BelongToServiceIdFieldName, 1);
		options.put("name", "belongToServiceIdFieldName_");
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigTemplateTableField.TemplateKeyFieldName, 1);
		options.put("name", "templateKeyFieldName_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigTemplateTableField.BelongToParentTemplateFieldName, 1);
		options.put("name", "belongToRootTemplateIdFieldName_");
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigTemplateTableField.BatchIdFieldName, 1);
		options.put("name", "batchIdFieldName_");
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, 1);
		options.put("name", "seqNo_");
		options.put("unique", true);
		deletedTableCtl.getMongoDBCtrl().createIndex(keys, options);
	}

}
