package enn.monitor.alarm.token.config;

public class EnnMonitorAlarmConfig {
	
	public static String getAlarmConfigHost() {
		return "enn-monitor-alarm-config-server";
		//return "127.0.0.1";
	}
	
	public static int getAlarmConfigPort() {
		return 10000;
		//return 9998;
	}
	
	public static String getAlarmTicketHost() {
		return "enn-monitor-alarm-ticket-server";
		//return "127.0.0.1";
	}
	
	public static int getAlarmTicketPort() {
		return 10000;
		//return 9999;
	}
	
}
