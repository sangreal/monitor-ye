package enn.monitor.config.business;

import enn.monitor.config.business.parameters.Parameters;

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
	
	public static String getLogformatTable() {
		return "EnnMonitorConfigBusinessLogformatTable";
	}
	
	public static String getLogformatDeletedTable() {
		return "EnnMonitorConfigBusinessLogformatDeletedTable";
	}
	
	public static String getTopicTable() {
		return "EnnMonitorConfigBusinessTopicTable";
	}
	
	public static String getTopicDeletedTable() {
		return "EnnMonitorConfigBusinessTopicDeletedTable";
	}
	
	public static String getAutoIncTable() {
		return "EnnMonitorConfigBusinessAutoIncTable";
	}

}
