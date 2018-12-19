package enn.monitor.log.config.analyse.term.util;

import com.mongodb.BasicDBObject;

import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.log.config.MongoConfig;
import enn.monitor.log.config.analyse.term.impl.EnnMonitorLogConfigAnalyseTermImpl;
import enn.monitor.log.config.analyse.term.tablectl.EnnMonitorLogConfigAnalyseTermDBTable;
import enn.monitor.log.config.analyse.term.tablectl.EnnMonitorLogConfigAnalyseTermTableCtl;
import enn.monitor.log.config.analyse.term.tablectl.EnnMonitorLogConfigAnalyseTermTableField;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

public class EnnMonitorLogConfigAnalyseTermUtil {
	
	public static void init(ServerBuilder<?> serverBuilder, MongoAutoIncTableCtl autoIncTableCtl) throws Exception {
		EnnMonitorLogConfigAnalyseTermTableCtl ennMonitorLogConfigAnalyseTermTableCtl = null;
		
		ennMonitorLogConfigAnalyseTermTableCtl = new EnnMonitorLogConfigAnalyseTermTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), EnnMonitorLogConfigAnalyseTermDBTable.getAnalyseTermTable());
		EnnMonitorLogConfigAnalyseTermUtil.createIndexes(ennMonitorLogConfigAnalyseTermTableCtl);
		
		serverBuilder.addService(ServerInterceptors.intercept(new EnnMonitorLogConfigAnalyseTermImpl(autoIncTableCtl, ennMonitorLogConfigAnalyseTermTableCtl)));
	}
	
	public static void createIndexes(EnnMonitorLogConfigAnalyseTermTableCtl tableCtl) throws Exception {
		BasicDBObject keys = null;
		BasicDBObject options = null;
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigAnalyseTermTableField.IdFieldName, 1);
		options.put("name", "id_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigAnalyseTermTableField.AnalyseTermKeyFieldName, 1);
		options.put("name", "analyseTermKey_");
		options.put("unique", true);
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
		
		keys = new BasicDBObject();
		options = new BasicDBObject();
		keys.put(EnnMonitorLogConfigAnalyseTermTableField.BelongToServiceIdFieldName, 1);
		options.put("name", "belongToServiceIdFieldName_");
		tableCtl.getMongoDBCtrl().createIndex(keys, options);
	}

}
