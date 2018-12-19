package enn.monitor.log.analyse.storage.config;

import enn.monitor.log.analyse.storage.parameters.Parameters;

public class MongoConfig {
	private static Parameters instance = null;
	
	public static void setParameters(Parameters parameters) {
		instance = parameters;
	}
	
	public static String getMongoUrl() {
		return instance.mongoUrl;
	}
	
	public static String getDBName() {
		return instance.dbName;
	}
	
	public static String getLogAnalyseStorageTable() {
		return "EnnMonitorLogAnalyseStorageTable";
	}
	
	public static String getLogAnalyseStorageDeletedTable() {
		return "EnnMonitorLogAnalyseStorageDeletedTable";
	}

	public static String getAutoIncTable() {
		return "EnnMonitorLogAnalyseStorageAutoIncTable";
	}

}
