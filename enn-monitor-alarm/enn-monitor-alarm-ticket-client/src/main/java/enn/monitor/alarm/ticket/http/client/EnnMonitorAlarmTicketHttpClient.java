package enn.monitor.alarm.ticket.http.client;

import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.framework.common.constval.EnnMonitorHttpConstants;
import enn.monitor.framework.common.env.EnnMonitorEnvAppUtil;
import enn.monitor.framework.log.core.EnnMonitorLogConstants;
import enn.monitor.framework.log.core.EnnMonitorLoggerFactory;

public class EnnMonitorAlarmTicketHttpClient {
	private static final Logger logger = LogManager.getLogger();
	
	private HttpClient client = new DefaultHttpClient();  
	
	private String host = null;
	private int port = 0;
	
	public EnnMonitorAlarmTicketHttpClient(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void put(String json) throws Exception {
		HttpPost request = new HttpPost("http://" + host + ":" + port + "/ticket/alerts");  
		
		if (EnnMonitorLoggerFactory.get() != null && EnnMonitorLoggerFactory.get().getTraceId() != null) {
			request.setHeader(EnnMonitorLogConstants.TRACE_ID, EnnMonitorLoggerFactory.get().getTraceId());
	        request.setHeader(EnnMonitorLogConstants.PARENT_POD, EnnMonitorEnvAppUtil.getPodName());
		}
		request.addHeader(HTTP.CONTENT_TYPE, EnnMonitorHttpConstants.APPLICATION_JSON);
		
		String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
		StringEntity se = new StringEntity(encoderJson);
        se.setContentType(EnnMonitorHttpConstants.CONTENT_TYPE_TEXT_JSON);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, EnnMonitorHttpConstants.APPLICATION_JSON));
        request.setEntity(se);
        
        HttpResponse response = client.execute(request);  
        try {
       	 	if (response.getStatusLine().getStatusCode() != 200) {
           	 	logger.error("Http Status: " + response.getStatusLine().getStatusCode() + " Phrase " + response.getStatusLine().getReasonPhrase());
            }
        } finally {
       	 	request.releaseConnection();
        }
        
	}
	
}
