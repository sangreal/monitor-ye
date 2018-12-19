package enn.monitor.alarm.prometheus.parameter;

public enum EnnMonitorAlarmPrometheusAlertsLabelsTypeEnum {
	pod("pod"), node("node"), service("service");
	
	private String value = null;
	
	EnnMonitorAlarmPrometheusAlertsLabelsTypeEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
