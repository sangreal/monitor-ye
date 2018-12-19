package enn.monitor.alarm.prometheus.parameter;

public class EnnMonitorAlarmPrometheusAlertsItem {
	private String status = null;
	
	private EnnMonitorAlarmPrometheusAlertsLabels labels = null;
    private EnnMonitorAlarmPrometheusAlertsAnnotations annotations = null;
    
    private String startsAt = null;
    private String endsAt = null;
    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public EnnMonitorAlarmPrometheusAlertsLabels getLabels() {
		return labels;
	}
	
    public void setLabels(EnnMonitorAlarmPrometheusAlertsLabels labels) {
		this.labels = labels;
	}
	
    public EnnMonitorAlarmPrometheusAlertsAnnotations getAnnotations() {
		return annotations;
	}
	
    public void setAnnotations(EnnMonitorAlarmPrometheusAlertsAnnotations annotations) {
		this.annotations = annotations;
	}

	public String getStartsAt() {
		return startsAt;
	}

	public void setStartsAt(String startsAt) {
		this.startsAt = startsAt;
	}

	public String getEndsAt() {
		return endsAt;
	}

	public void setEndsAt(String endsAt) {
		this.endsAt = endsAt;
	}
    
}
