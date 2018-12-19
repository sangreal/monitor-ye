package enn.monitor.log.config;

public class EnnMonitorLogConfig {
	
	public static String getHost() {
		return "enn-monitor-log-config-server.monitor-system-log";
//		return "127.0.0.1";
	}
	
	public static int getPort() {
		return 10000;
	}
	
	public static String getLogformatHost() {
		return "enn-monitor-log-normalizing-server.monitor-system-log";
		//return "127.0.0.1";
	}
	
	public static int getLogformatPort() {
		return 10000;
	}
	
}
