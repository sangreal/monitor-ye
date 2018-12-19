package enn.monitor.alarm.prometheus.parameter;

public enum EnnMonitorAlarmPrometheusAlertsStatusEnum {
	firing("firing"), resolved("resolved");

	private String value = null;
	
	EnnMonitorAlarmPrometheusAlertsStatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
