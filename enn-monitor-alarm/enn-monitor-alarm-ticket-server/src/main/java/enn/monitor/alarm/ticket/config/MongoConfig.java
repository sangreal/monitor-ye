package enn.monitor.alarm.ticket.config;

import enn.monitor.alarm.ticket.parameters.Parameters;

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
	
	public static String getTicketTable() {
		return "EnnMonitorAlarmTicketTable";
	}
	
	public static String getAutoIncTable() {
		return "EnnMonitorAlarmTicketAutoIncTable";
	}
	
	public static String getTicketDeletedTable() {
		return "EnnMonitorAlarmTicketDeletedTable";
	}

}
