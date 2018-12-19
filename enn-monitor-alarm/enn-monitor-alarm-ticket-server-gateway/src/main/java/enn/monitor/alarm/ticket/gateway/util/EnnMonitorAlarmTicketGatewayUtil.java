package enn.monitor.alarm.ticket.gateway.util;

import enn.monitor.alarm.prometheus.parameter.EnnMonitorAlarmPrometheusAlertsItem;
import enn.monitor.alarm.prometheus.parameter.EnnMonitorAlarmPrometheusAlertsLabelsTypeEnum;

public class EnnMonitorAlarmTicketGatewayUtil {
	
	/* 
	type:判断服务是node还是pod
	{
	"receiver": "enn-monitor-admin",
	"status": "firing",   // resolved
	"alerts": [
			{
			    status: "resolved",
				labels: {
					alertname: "memory_usage",
					appname: "mysql-container",
					clustername: "shanghai",
					from: "console",             // "default"
					host: "10.19.140.13",
					instance: "perf-deploy-0-2265522859-fmc6d",
					job: "pod",
					metric: "memory_ws_percent",
					monitor: "enn-monitor",
					namespace: "terminal-perf-3",
					podname: "perf-deploy-0-2265522859-fmc6d",
					severity: "critical",  //warning critical
					type: "pod" // pod node
					automation: "true"
				},
				annotations: {
					description: "memory_usage of pod-perf-deploy-0-2265522859-fmc6d has been abnormal for more than 2 minutes.(current value: 0.218536376953125)",
					summary: "Pod perf-deploy-0-2265522859-fmc6d memory_usage abnormal"
				},
				startsAt: "2017-10-25T04:47:31.716Z",
				endsAt: "0001-01-01T00:00:00Z",
				generatorURL: "http://prometheus-engine-v980p:9090/graph?g0.expr=memory_ws_percent%7Bclustername%3D%22shanghai%22%2Cjob%3D%22pod%22%2Cnamespace%3D%22terminal-perf-3%22%7D+%3E+0.2&g0.tab=0"
			}
	],
	"externalURL": "http://8fdc8fee2883:9093",
	"version": "3",
	"groupKey": 6549095836526728000
	}
	*/
	
	public static String generateKey(EnnMonitorAlarmPrometheusAlertsItem alertsItem) throws Exception {
		String key = alertsItem.getLabels().getType() + "-" + alertsItem.getLabels().getClustername() + "-";

		if (EnnMonitorAlarmPrometheusAlertsLabelsTypeEnum.pod.getValue().equals(alertsItem.getLabels().getType()) == true) {
			key = key + alertsItem.getLabels().getNamespace() + "-" + alertsItem.getLabels().getInstance();
		} else if (EnnMonitorAlarmPrometheusAlertsLabelsTypeEnum.node.getValue().equals(alertsItem.getLabels().getType()) == true) {
			key = key + alertsItem.getLabels().getInstance();
		} else if (EnnMonitorAlarmPrometheusAlertsLabelsTypeEnum.service.getValue().equals(alertsItem.getLabels().getType()) == true) {
			key = key + alertsItem.getLabels().getInstance();
		} else {
			throw new Exception("the type is not match: " + alertsItem.getLabels().getType());
		}

		key = key + "-" + alertsItem.getLabels().getAlertname() + "-" + alertsItem.getLabels().getSeverity() + "-" + alertsItem.getStartsAt();
			
		return key;
	}

}
