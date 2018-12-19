package enn.monitor.alarm.ticket.gateway.main;

import com.google.gson.Gson;

import enn.monitor.alarm.prometheus.parameter.EnnMonitorAlarmPrometheusAlerts;
import enn.monitor.alarm.prometheus.parameter.EnnMonitorAlarmPrometheusAlertsAnnotations;
import enn.monitor.alarm.prometheus.parameter.EnnMonitorAlarmPrometheusAlertsItem;
import enn.monitor.alarm.prometheus.parameter.EnnMonitorAlarmPrometheusAlertsLabels;
import enn.monitor.alarm.ticket.http.client.EnnMonitorAlarmTicketHttpClient;

public class EnnMonitorAlarmTicketMain {
	
	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();
		
		EnnMonitorAlarmPrometheusAlerts ennMonitorAlarmPrometheusAlerts = null;
		
		EnnMonitorAlarmTicketHttpClient httpClient = new EnnMonitorAlarmTicketHttpClient("127.0.0.1", 8090);
		
		ennMonitorAlarmPrometheusAlerts = getEnnMonitorAlarmPrometheusAlerts0();
		httpClient.put(gson.toJson(ennMonitorAlarmPrometheusAlerts));
		
		ennMonitorAlarmPrometheusAlerts = getEnnMonitorAlarmPrometheusAlerts1();
		httpClient.put(gson.toJson(ennMonitorAlarmPrometheusAlerts));
		
		ennMonitorAlarmPrometheusAlerts = getEnnMonitorAlarmPrometheusAlerts2();
		httpClient.put(gson.toJson(ennMonitorAlarmPrometheusAlerts));
		
		ennMonitorAlarmPrometheusAlerts = getEnnMonitorAlarmPrometheusAlerts3();
		httpClient.put(gson.toJson(ennMonitorAlarmPrometheusAlerts));
		
		ennMonitorAlarmPrometheusAlerts = getEnnMonitorAlarmPrometheusAlerts4();
		httpClient.put(gson.toJson(ennMonitorAlarmPrometheusAlerts));
		
		ennMonitorAlarmPrometheusAlerts = getEnnMonitorAlarmPrometheusAlerts5();
		httpClient.put(gson.toJson(ennMonitorAlarmPrometheusAlerts));
		
		ennMonitorAlarmPrometheusAlerts = getEnnMonitorAlarmPrometheusAlerts6();
		httpClient.put(gson.toJson(ennMonitorAlarmPrometheusAlerts));
		
		ennMonitorAlarmPrometheusAlerts = getEnnMonitorAlarmPrometheusAlerts7();
		httpClient.put(gson.toJson(ennMonitorAlarmPrometheusAlerts));
		
		ennMonitorAlarmPrometheusAlerts = getEnnMonitorAlarmPrometheusAlerts8();
		httpClient.put(gson.toJson(ennMonitorAlarmPrometheusAlerts));
	}
		
	private static EnnMonitorAlarmPrometheusAlerts getEnnMonitorAlarmPrometheusAlerts0() {
		EnnMonitorAlarmPrometheusAlertsItem ennMonitorAlarmPrometheusAlertsItem = null;
		EnnMonitorAlarmPrometheusAlerts ennMonitorAlarmPrometheusAlerts = new EnnMonitorAlarmPrometheusAlerts();
		
		ennMonitorAlarmPrometheusAlerts.setReceiver("enn-monitor-admin");
		ennMonitorAlarmPrometheusAlerts.setStatus("firing");
		
		ennMonitorAlarmPrometheusAlertsItem = getEnnMonitorAlarmPrometheusAlertsItem();
		ennMonitorAlarmPrometheusAlertsItem.setStatus("firing");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setSeverity("critical");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setAutomation("true");
		ennMonitorAlarmPrometheusAlertsItem.setStartsAt("2017-10-30T08:48:52.804Z");
		ennMonitorAlarmPrometheusAlertsItem.setEndsAt("0001-01-01T00:00:00Z");
		ennMonitorAlarmPrometheusAlerts.addEnnMonitorAlarmPrometheusAlertsItem(ennMonitorAlarmPrometheusAlertsItem);
		
		return ennMonitorAlarmPrometheusAlerts;
	}
	
	private static EnnMonitorAlarmPrometheusAlerts getEnnMonitorAlarmPrometheusAlerts1() {
		EnnMonitorAlarmPrometheusAlertsItem ennMonitorAlarmPrometheusAlertsItem = null;
		EnnMonitorAlarmPrometheusAlerts ennMonitorAlarmPrometheusAlerts = new EnnMonitorAlarmPrometheusAlerts();
		
		ennMonitorAlarmPrometheusAlerts.setReceiver("enn-monitor-admin");
		ennMonitorAlarmPrometheusAlerts.setStatus("firing");
		
		ennMonitorAlarmPrometheusAlertsItem = getEnnMonitorAlarmPrometheusAlertsItem();
		ennMonitorAlarmPrometheusAlertsItem.setStatus("firing");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setSeverity("critical");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setAutomation("true");
		ennMonitorAlarmPrometheusAlertsItem.setStartsAt("2017-10-30T08:48:52.804Z");
		ennMonitorAlarmPrometheusAlertsItem.setEndsAt("0001-01-01T00:00:00Z");
		ennMonitorAlarmPrometheusAlerts.addEnnMonitorAlarmPrometheusAlertsItem(ennMonitorAlarmPrometheusAlertsItem);
		
		return ennMonitorAlarmPrometheusAlerts;
	}

	private static EnnMonitorAlarmPrometheusAlerts getEnnMonitorAlarmPrometheusAlerts2() {
		EnnMonitorAlarmPrometheusAlertsItem ennMonitorAlarmPrometheusAlertsItem = null;
		EnnMonitorAlarmPrometheusAlerts ennMonitorAlarmPrometheusAlerts = new EnnMonitorAlarmPrometheusAlerts();
		
		ennMonitorAlarmPrometheusAlerts.setReceiver("enn-monitor-admin");
		ennMonitorAlarmPrometheusAlerts.setStatus("resolved");
		
		ennMonitorAlarmPrometheusAlertsItem = getEnnMonitorAlarmPrometheusAlertsItem();
		ennMonitorAlarmPrometheusAlertsItem.setStatus("resolved");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setSeverity("critical");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setAutomation("true");
		ennMonitorAlarmPrometheusAlertsItem.setStartsAt("2017-10-30T08:48:52.804Z");
		ennMonitorAlarmPrometheusAlertsItem.setEndsAt("2017-10-30T09:04:07.804Z");
		ennMonitorAlarmPrometheusAlerts.addEnnMonitorAlarmPrometheusAlertsItem(ennMonitorAlarmPrometheusAlertsItem);
		
		return ennMonitorAlarmPrometheusAlerts;
	}
		
	private static EnnMonitorAlarmPrometheusAlerts getEnnMonitorAlarmPrometheusAlerts3() {
		EnnMonitorAlarmPrometheusAlertsItem ennMonitorAlarmPrometheusAlertsItem = null;
		EnnMonitorAlarmPrometheusAlerts ennMonitorAlarmPrometheusAlerts = new EnnMonitorAlarmPrometheusAlerts();
		
		ennMonitorAlarmPrometheusAlerts.setReceiver("enn-monitor-admin");
		ennMonitorAlarmPrometheusAlerts.setStatus("resolved");
		
		ennMonitorAlarmPrometheusAlertsItem = getEnnMonitorAlarmPrometheusAlertsItem();
		ennMonitorAlarmPrometheusAlertsItem.setStatus("resolved");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setSeverity("critical");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setAutomation("true");
		ennMonitorAlarmPrometheusAlertsItem.setStartsAt("2017-10-30T08:48:52.804Z");
		ennMonitorAlarmPrometheusAlertsItem.setEndsAt("2017-10-30T09:04:07.804Z");
		ennMonitorAlarmPrometheusAlerts.addEnnMonitorAlarmPrometheusAlertsItem(ennMonitorAlarmPrometheusAlertsItem);
		
		return ennMonitorAlarmPrometheusAlerts;
	}

	private static EnnMonitorAlarmPrometheusAlerts getEnnMonitorAlarmPrometheusAlerts4() {
		EnnMonitorAlarmPrometheusAlertsItem ennMonitorAlarmPrometheusAlertsItem = null;
		EnnMonitorAlarmPrometheusAlerts ennMonitorAlarmPrometheusAlerts = new EnnMonitorAlarmPrometheusAlerts();
		
		ennMonitorAlarmPrometheusAlerts.setReceiver("enn-monitor-admin");
		ennMonitorAlarmPrometheusAlerts.setStatus("firing");
		
		ennMonitorAlarmPrometheusAlertsItem = getEnnMonitorAlarmPrometheusAlertsItem();
		ennMonitorAlarmPrometheusAlertsItem.setStatus("firing");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setSeverity("critical");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setAutomation("true");
		ennMonitorAlarmPrometheusAlertsItem.setStartsAt("2017-10-30T09:09:22.804Z");
		ennMonitorAlarmPrometheusAlertsItem.setEndsAt("0001-01-01T00:00:00Z");
		ennMonitorAlarmPrometheusAlerts.addEnnMonitorAlarmPrometheusAlertsItem(ennMonitorAlarmPrometheusAlertsItem);
		
		return ennMonitorAlarmPrometheusAlerts;
	}
		
	private static EnnMonitorAlarmPrometheusAlerts getEnnMonitorAlarmPrometheusAlerts5() {
		EnnMonitorAlarmPrometheusAlertsItem ennMonitorAlarmPrometheusAlertsItem = null;
		EnnMonitorAlarmPrometheusAlerts ennMonitorAlarmPrometheusAlerts = new EnnMonitorAlarmPrometheusAlerts();
		
		ennMonitorAlarmPrometheusAlerts.setReceiver("enn-monitor-admin");
		ennMonitorAlarmPrometheusAlerts.setStatus("firing");
		
		ennMonitorAlarmPrometheusAlertsItem = getEnnMonitorAlarmPrometheusAlertsItem();
		ennMonitorAlarmPrometheusAlertsItem.setStatus("firing");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setSeverity("critical");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setAutomation("true");
		ennMonitorAlarmPrometheusAlertsItem.setStartsAt("2017-10-30T09:09:22.804Z");
		ennMonitorAlarmPrometheusAlertsItem.setEndsAt("0001-01-01T00:00:00Z");
		ennMonitorAlarmPrometheusAlerts.addEnnMonitorAlarmPrometheusAlertsItem(ennMonitorAlarmPrometheusAlertsItem);

		return ennMonitorAlarmPrometheusAlerts;
	}
		
	private static EnnMonitorAlarmPrometheusAlerts getEnnMonitorAlarmPrometheusAlerts6() {
		EnnMonitorAlarmPrometheusAlertsItem ennMonitorAlarmPrometheusAlertsItem = null;
		EnnMonitorAlarmPrometheusAlerts ennMonitorAlarmPrometheusAlerts = new EnnMonitorAlarmPrometheusAlerts();
		
		ennMonitorAlarmPrometheusAlerts.setReceiver("enn-monitor-admin");
		ennMonitorAlarmPrometheusAlerts.setStatus("firing");
		
		ennMonitorAlarmPrometheusAlertsItem = getEnnMonitorAlarmPrometheusAlertsItem();
		ennMonitorAlarmPrometheusAlertsItem.setStatus("firing");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setSeverity("critical");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setAutomation("true");
		ennMonitorAlarmPrometheusAlertsItem.setStartsAt("2017-10-30T09:09:22.804Z");
		ennMonitorAlarmPrometheusAlertsItem.setEndsAt("0001-01-01T00:00:00Z");
		ennMonitorAlarmPrometheusAlerts.addEnnMonitorAlarmPrometheusAlertsItem(ennMonitorAlarmPrometheusAlertsItem);
		
		return ennMonitorAlarmPrometheusAlerts;
	}

	private static EnnMonitorAlarmPrometheusAlerts getEnnMonitorAlarmPrometheusAlerts7() {
		EnnMonitorAlarmPrometheusAlertsItem ennMonitorAlarmPrometheusAlertsItem = null;
		EnnMonitorAlarmPrometheusAlerts ennMonitorAlarmPrometheusAlerts = new EnnMonitorAlarmPrometheusAlerts();
		
		ennMonitorAlarmPrometheusAlerts.setReceiver("enn-monitor-admin");
		ennMonitorAlarmPrometheusAlerts.setStatus("firing");
		
		ennMonitorAlarmPrometheusAlertsItem = getEnnMonitorAlarmPrometheusAlertsItem();
		ennMonitorAlarmPrometheusAlertsItem.setStatus("resolved");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setSeverity("critical");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setAutomation("true");
		ennMonitorAlarmPrometheusAlertsItem.setStartsAt("2017-10-30T09:09:22.804Z");
		ennMonitorAlarmPrometheusAlertsItem.setEndsAt("2017-10-30T09:16:37.807653311Z");
		ennMonitorAlarmPrometheusAlerts.addEnnMonitorAlarmPrometheusAlertsItem(ennMonitorAlarmPrometheusAlertsItem);
		
		ennMonitorAlarmPrometheusAlertsItem = getEnnMonitorAlarmPrometheusAlertsItem();
		ennMonitorAlarmPrometheusAlertsItem.setStatus("firing");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setSeverity("warning");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setAutomation("true");
		ennMonitorAlarmPrometheusAlertsItem.setStartsAt("2017-10-30T09:12:52.803Z");
		ennMonitorAlarmPrometheusAlertsItem.setEndsAt("0001-01-01T00:00:00Z");
		ennMonitorAlarmPrometheusAlerts.addEnnMonitorAlarmPrometheusAlertsItem(ennMonitorAlarmPrometheusAlertsItem);
		
		return ennMonitorAlarmPrometheusAlerts;
	}

	private static EnnMonitorAlarmPrometheusAlerts getEnnMonitorAlarmPrometheusAlerts8() {
		EnnMonitorAlarmPrometheusAlertsItem ennMonitorAlarmPrometheusAlertsItem = null;
		EnnMonitorAlarmPrometheusAlerts ennMonitorAlarmPrometheusAlerts = new EnnMonitorAlarmPrometheusAlerts();
		
		ennMonitorAlarmPrometheusAlerts.setReceiver("enn-monitor-admin");
		ennMonitorAlarmPrometheusAlerts.setStatus("firing");
		
		ennMonitorAlarmPrometheusAlertsItem = getEnnMonitorAlarmPrometheusAlertsItem();
		ennMonitorAlarmPrometheusAlertsItem.setStatus("firing");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setSeverity("warning");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setAutomation("true");
		ennMonitorAlarmPrometheusAlertsItem.setStartsAt("2017-10-30T09:12:52.803Z");
		ennMonitorAlarmPrometheusAlertsItem.setEndsAt("0001-01-01T00:00:00Z");
		ennMonitorAlarmPrometheusAlerts.addEnnMonitorAlarmPrometheusAlertsItem(ennMonitorAlarmPrometheusAlertsItem);
		
		
		return ennMonitorAlarmPrometheusAlerts;
	}
	
	private static EnnMonitorAlarmPrometheusAlertsItem getEnnMonitorAlarmPrometheusAlertsItem() {
		EnnMonitorAlarmPrometheusAlertsItem ennMonitorAlarmPrometheusAlertsItem = new EnnMonitorAlarmPrometheusAlertsItem();
		
		ennMonitorAlarmPrometheusAlertsItem.setLabels(new EnnMonitorAlarmPrometheusAlertsLabels());
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setAlertname("memory_usage");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setType("node");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setClustername("shanghai");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setHost("10.19.137.142");
		ennMonitorAlarmPrometheusAlertsItem.getLabels().setAppname("mongo");
		ennMonitorAlarmPrometheusAlertsItem.setAnnotations(new EnnMonitorAlarmPrometheusAlertsAnnotations());
		ennMonitorAlarmPrometheusAlertsItem.getAnnotations().setDescription("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		ennMonitorAlarmPrometheusAlertsItem.getAnnotations().setSummary("aaaaa");
		
		return ennMonitorAlarmPrometheusAlertsItem;
	}
}
