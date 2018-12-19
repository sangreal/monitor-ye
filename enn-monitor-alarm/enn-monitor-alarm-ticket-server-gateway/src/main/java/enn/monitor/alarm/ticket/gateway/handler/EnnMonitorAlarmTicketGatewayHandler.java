package enn.monitor.alarm.ticket.gateway.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import com.google.gson.Gson;

import enn.monitor.alarm.prometheus.common.EnnMonitorAlarmPrometheusAlertsItemSeverity;
import enn.monitor.alarm.prometheus.common.EnnMonitorAlarmPrometheusAlertsItemStatus;
import enn.monitor.alarm.prometheus.parameter.EnnMonitorAlarmPrometheusAlerts;
import enn.monitor.alarm.prometheus.parameter.EnnMonitorAlarmPrometheusAlertsItem;
import enn.monitor.alarm.prometheus.parameter.EnnMonitorAlarmPrometheusAlertsLabels;
import enn.monitor.alarm.prometheus.parameter.EnnMonitorAlarmPrometheusAlertsLabelsTypeEnum;
import enn.monitor.alarm.ticket.gateway.util.EnnMonitorAlarmTicketGatewayUtil;
import enn.monitor.alarm.ticket.grpc.client.EnnMonitorAlarmTicketGrpcClient;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketLevel;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformStateEnum;
import enn.monitor.framework.log.core.EnnMonitorLogConstants;
import enn.monitor.framework.log.core.EnnMonitorLoggerFactory;

public class EnnMonitorAlarmTicketGatewayHandler extends HttpServlet {
    private static final long serialVersionUID = 2271797150647771294L;  
    
    private static final Logger logger = LogManager.getLogger();
    
    private CounterMetric putMetrics = MetricManager.getCounterMetric(EnnMonitorAlarmTicketGatewayHandler.class, "put");
    
    private EnnMonitorAlarmTicketGrpcClient alarmTicketGrpcClient = null;
    
    private Gson gson = new Gson();
      
    public EnnMonitorAlarmTicketGatewayHandler(String server, int port){
    	alarmTicketGrpcClient = new EnnMonitorAlarmTicketGrpcClient(server, port);
    }  
    
    @Override  
    protected void doPost(HttpServletRequest req, HttpServletResponse response)  
            throws ServletException, IOException {  
    	String line = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        
        EnnMonitorAlarmPrometheusAlerts ennMonitorAlarmPrometheusAlerts = null;
        
        EnnMonitorAlarmTicketTransformState ennMonitorAlarmTicketTransformState = null;
        EnnMonitorAlarmTicketCreateRequest ennMonitorAlarmTicketCreateRequest = null;
        
        while((line = bufferedReader.readLine())!=null){
            stringBuilder.append(line);
        }

        // 将资料解码
        String json = stringBuilder.toString();
        
        logger.info("json: " + json);
        
        if (req.getHeader(EnnMonitorLogConstants.TRACE_ID) != null && req.getHeader(EnnMonitorLogConstants.PARENT_POD) != null) {
        	EnnMonitorLoggerFactory.setTrace(EnnMonitorLogConstants.TRACE_ID, EnnMonitorLogConstants.PARENT_POD);
        }
        
        try {
        	ennMonitorAlarmPrometheusAlerts = gson.fromJson(json, EnnMonitorAlarmPrometheusAlerts.class);
        } catch (Exception e) {
        	ennMonitorAlarmPrometheusAlerts = null;
    		logger.error(e.getMessage(), e);
    	}
        
        if (ennMonitorAlarmPrometheusAlerts != null) {
        	for (EnnMonitorAlarmPrometheusAlertsItem alertsItem : ennMonitorAlarmPrometheusAlerts.getAlerts()) {
            	try {
            		if (alertsItem.getStatus().equals(EnnMonitorAlarmPrometheusAlertsItemStatus.Resolved) == true) {
                		ennMonitorAlarmTicketTransformState = 
                				EnnMonitorAlarmTicketTransformState.newBuilder()
                					.setTicketKey(EnnMonitorAlarmTicketGatewayUtil.generateKey(alertsItem))
                					.setTicketTransformState(EnnMonitorAlarmTicketTransformStateEnum.SelfRecover)
                					.build();
                		alarmTicketGrpcClient.updateEnnMonitorAlarmTicketTransformState(ennMonitorAlarmTicketTransformState);
                	} else {   // firing
                		ennMonitorAlarmTicketCreateRequest = transformEnnMonitorAlarmPrometheusAlertsItem(ennMonitorAlarmPrometheusAlerts.getReceiver(), alertsItem);
                		alarmTicketGrpcClient.createEnnMonitorAlarmTicket(ennMonitorAlarmTicketCreateRequest);
                	}
            		
            		putMetrics.markEvent();
            	} catch (Exception e) {
            		logger.error(e.getMessage(), e);
            	}
            }
        }
		
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }  
    
    private EnnMonitorAlarmTicketCreateRequest transformEnnMonitorAlarmPrometheusAlertsItem(String groupName, EnnMonitorAlarmPrometheusAlertsItem alertsItem) throws Exception {
    	EnnMonitorAlarmPrometheusAlertsLabels ennMonitorAlarmPrometheusAlertsLabels = null;
    	
    	EnnMonitorAlarmTicketCreateRequest.Builder requestBuilder = EnnMonitorAlarmTicketCreateRequest.newBuilder();
    	
    	ennMonitorAlarmPrometheusAlertsLabels = alertsItem.getLabels();
    	
    	requestBuilder.setGrourName(groupName);
    	
    	requestBuilder.setTicketKey(EnnMonitorAlarmTicketGatewayUtil.generateKey(alertsItem));
    	
    	if (ennMonitorAlarmPrometheusAlertsLabels.getSeverity().equals(EnnMonitorAlarmPrometheusAlertsItemSeverity.Warning) == true) {
    		requestBuilder.setEnnMonitorAlarmTicketLevel(EnnMonitorAlarmTicketLevel.Warning);
    	} else {
    		requestBuilder.setEnnMonitorAlarmTicketLevel(EnnMonitorAlarmTicketLevel.Critical);
    	}
    	
    	requestBuilder.setAutomation(false);
    	if (ennMonitorAlarmPrometheusAlertsLabels.getAutomation() != null && ennMonitorAlarmPrometheusAlertsLabels.getAutomation().equals("true") == true) {
    		requestBuilder.setAutomation(true);
    	}
    	
    	if (ennMonitorAlarmPrometheusAlertsLabels.getClustername() != null && ennMonitorAlarmPrometheusAlertsLabels.getClustername().equals("") == false) {
    		requestBuilder.setClusterName(ennMonitorAlarmPrometheusAlertsLabels.getClustername());
    	}
    	
    	if (EnnMonitorAlarmPrometheusAlertsLabelsTypeEnum.pod.getValue().equals(ennMonitorAlarmPrometheusAlertsLabels.getType()) == true) {
    		requestBuilder.setNameSpace(ennMonitorAlarmPrometheusAlertsLabels.getNamespace());
    		requestBuilder.setPodName(ennMonitorAlarmPrometheusAlertsLabels.getInstance());
		} else if (EnnMonitorAlarmPrometheusAlertsLabelsTypeEnum.node.getValue().equals(alertsItem.getLabels().getType()) == true) {
			requestBuilder.setIp(ennMonitorAlarmPrometheusAlertsLabels.getInstance());
		} else if (EnnMonitorAlarmPrometheusAlertsLabelsTypeEnum.service.getValue().equals(alertsItem.getLabels().getType()) == true) {
			requestBuilder.setAppName(ennMonitorAlarmPrometheusAlertsLabels.getInstance());
		} else {
			throw new Exception("the type is not match: " + alertsItem.getLabels().getType());
		}
    	
    	if (alertsItem.getAnnotations() != null && alertsItem.getAnnotations().getDescription() != null &&
    			alertsItem.getAnnotations().getDescription().equals("") == false) {
    		requestBuilder.setError(alertsItem.getAnnotations().getDescription());
    	}
    	
    	requestBuilder.setCreateUser("Prometheus");
    	
    	return requestBuilder.build();
    }
    
    public static void main(String[] args) throws Exception {
    	EnnMonitorAlarmPrometheusAlerts ennMonitorAlarmPrometheusAlerts = null;
    	Gson gson = new Gson();
    	String json = "{\"receiver\":\"enn-monitor-oncall\",\"status\":\"firing\",\"alerts\":[{\"status\":\"firing\",\"labels\":{\"alertname\":\"Service_SH\",\"instance\":\"shared_HDFS\",\"job\":\"shanghai_services\",\"monitor\":\"enn-monitor\",\"script\":\"shanghai_shared_HDFS\",\"severity\":\"critical\"},\"annotations\":{\"description\":\"shanghai_services-shared_HDFS is in trouble for more than 2 minutes\",\"summary\":\"shanghai_services-shared_HDFS unavailable\"},\"startsAt\":\"2017-11-07T04:46:13.768Z\",\"endsAt\":\"0001-01-01T00:00:00Z\",\"generatorURL\":\"http://prometheus-engine-88s4m:9090/graph?g0.expr=script_success%7Bjob%3D%22shanghai_services%22%7D+%3D%3D+2+or+script_success%7Bjob%3D%22shanghai_services%22%7D+%3D%3D+-1\u0026g0.tab=0\"}],\"groupLabels\":{\"alertname\":\"Service_SH\",\"instance\":\"shared_HDFS\",\"job\":\"shanghai_services\"},\"commonLabels\":{\"alertname\":\"Service_SH\",\"instance\":\"shared_HDFS\",\"job\":\"shanghai_services\",\"monitor\":\"enn-monitor\",\"script\":\"shanghai_shared_HDFS\",\"severity\":\"critical\"},\"commonAnnotations\":{\"description\":\"shanghai_services-shared_HDFS is in trouble for more than 2 minutes\",\"summary\":\"shanghai_services-shared_HDFS unavailable\"},\"externalURL\":\"http://prometheus-alertmanager-42g8x:9093\",\"version\":\"3\",\"groupKey\":12278082540831277698}";
    	ennMonitorAlarmPrometheusAlerts = gson.fromJson(json, EnnMonitorAlarmPrometheusAlerts.class);
    }
}
