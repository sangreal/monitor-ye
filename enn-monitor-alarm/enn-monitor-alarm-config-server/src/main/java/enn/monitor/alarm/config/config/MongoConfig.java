package enn.monitor.alarm.config.config;

import enn.monitor.alarm.config.parameters.Parameters;

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
	
	public static String getEnnMonitorAlarmEmailTable() {
		return "EnnMonitorAlarmEmailTable";
	}
	
	public static String getEnnMonitorAlarmEmailDeletedTable() {
		return "EnnMonitorAlarmEmailDeletedTable";
	}
	
	public static String getEnnMonitorAlarmEmailAutoIncTable() {
		return "EnnMonitorAlarmEmailAutoIncTable";
	}
	
	public static String getEnnMonitorAlarmRouterStormTable() {
		return "EnnMonitorAlarmRouterStormTable";
	}
	
	public static String getEnnMonitorAlarmRouterStormDeletedTable() {
		return "EnnMonitorAlarmRouterStormDeletedTable";
	}
	
	public static String getEnnMonitorAlarmRouterStormAutoIncTable() {
		return "EnnMonitorAlarmRouterStormAutoIncTable";
	}
	
	public static String getEnnMonitorAlarmRouterTopicTable() {
		return "EnnMonitorAlarmRouterTopicTable";
	}
	
	public static String getEnnMonitorAlarmRouterTopicDeletedTable() {
		return "EnnMonitorAlarmRouterTopicDeletedTable";
	}
	
	public static String getEnnMonitorAlarmRouterTopicAutoIncTable() {
		return "EnnMonitorAlarmRouterTopicAutoIncTable";
	}

}
