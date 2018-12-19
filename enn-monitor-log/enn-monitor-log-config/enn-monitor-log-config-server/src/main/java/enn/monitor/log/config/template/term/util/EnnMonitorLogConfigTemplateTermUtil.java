package enn.monitor.log.config.template.term.util;

import com.mongodb.BasicDBObject;

import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.log.config.MongoConfig;
import enn.monitor.log.config.template.term.impl.EnnMonitorLogConfigTemplateTermImpl;
import enn.monitor.log.config.template.term.tablectl.EnnMonitorLogConfigTemplateTermDBTable;
import enn.monitor.log.config.template.term.tablectl.EnnMonitorLogConfigTemplateTermTableCtl;
import enn.monitor.log.config.template.term.tablectl.EnnMonitorLogConfigTemplateTermTableField;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

public class EnnMonitorLogConfigTemplateTermUtil {
	
	public static void init(ServerBuilder<?> serverBuilder, MongoAutoIncTableCtl autoIncTableCtl) throws Exception {
		EnnMonitorLogConfigTemplateTermTableCtl ennMonitorLogConfigTemplateTermTableCtl = null;
		
		ennMonitorLogConfigTemplateTermTableCtl = new EnnMonitorLogConfigTemplateTermTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), 
				EnnMonitorLogConfigTemplateTermDBTable.getTemplateTermTable());
		EnnMonitorLogConfigTemplateTermUtil.createIndexes(ennMonitorLogConfigTemplateTermTableCtl);
		
		serverBuilder.addService(ServerInterceptors.intercept(new EnnMonitorLogConfigTemplateTermImpl(autoIncTableCtl, ennMonitorLogConfigTemplateTermTableCtl)));
	}
	
	public static void createIndexes(EnnMonitorLogConfigTemplateTermTableCtl tableCtl) throws Exception {
		BasicDBObject keys = null;
		BasicDBObject options = null;
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigTemplateTermTableField.IdFieldName, 1);
		options.put("name", "id_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigTemplateTermTableField.BelongToServiceIdFieldName, 1);
		options.put("name", "belongToServiceIdFieldName_");
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigTemplateTermTableField.BatchIdFieldName, 1);
		options.put("name", "batchIdFieldName_");
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigTemplateTermTableField.IsSelectedFieldName, 1);
		options.put("name", "isSelectedFieldName_");
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigTemplateTermTableField.TemplateTermKeyFieldName, 1);
		options.put("name", "templateTermKey_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
	}

}
