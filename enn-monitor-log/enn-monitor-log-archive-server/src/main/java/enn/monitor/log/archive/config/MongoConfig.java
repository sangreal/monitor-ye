package enn.monitor.log.archive.config;

import enn.monitor.log.archive.parameters.Parameters;

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
	
	public static String getLogArchiveTable() {
		return "EnnMonitorLogArchiveTable";
	}
	
}
