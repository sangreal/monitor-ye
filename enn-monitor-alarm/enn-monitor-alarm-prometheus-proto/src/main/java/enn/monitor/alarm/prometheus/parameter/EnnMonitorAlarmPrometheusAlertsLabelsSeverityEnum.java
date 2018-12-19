package enn.monitor.alarm.prometheus.parameter;

public enum EnnMonitorAlarmPrometheusAlertsLabelsSeverityEnum {
	warning("warning"), critical("critical");
	
	private String value = null;
	
	EnnMonitorAlarmPrometheusAlertsLabelsSeverityEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
