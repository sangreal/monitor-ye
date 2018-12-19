package enn.monitor.config;

import enn.monitor.config.parameters.Parameters;

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
	
	public static String getServiceTable() {
		return "EnnMonitorConfigServiceTable";
	}
	
	public static String getServiceDeletedTable() {
		return "EnnMonitorConfigServiceDeletedTable";
	}

	public static String getClusterTable() {
		return "EnnMonitorConfigClusterTable";
	}

	public static String getClusterDeletedTable() {
		return "EnnMonitorConfigClusterDeletedTable";
	}

	public static String getServiceLineTable() {
		return "EnnMonitorConfigServiceLineTable";
	}
	
	public static String getServiceLineDeletedTable() {
		return "EnnMonitorConfigServiceLineDeletedTable";
	}
	
	public static String getAutoIncTable() {
		return "EnnMonitorConfigServiceAutoIncTable";
	}

}
