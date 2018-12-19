package enn.monitor.log.storage.origin.es.job;

import java.net.InetAddress;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.bytes.BytesArray;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.google.gson.Gson;

import enn.monitor.framework.common.time.EnnDatetimeUtil;
import enn.monitor.framework.common.time.EnnTimezoneUtil;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.log.channel.ChannelWriteTask;
import enn.monitor.framework.log.proto.ESLog;
import enn.monitor.log.storage.es.util.EnnMonitorLogStorageESLogStatsUtil;
import enn.monitor.security.gateway.stats.util.EnnMonitorSecurityGatewayStatsUtil;

public class EnnMonitorLogStorageOriginESJob extends ChannelWriteTask {
	
private static final Logger logger = LogManager.getLogger();
	
	private Gson gson = new Gson();
	
	private CounterMetric logMetrics = MetricManager.getCounterMetric(EnnMonitorLogStorageOriginESJob.class, "logCount");
	
    private BulkRequestBuilder bulkRequest = null;
    private static TransportClient esTransportClient = null;
    
    private Set<String> tagKeySet = ConcurrentHashMap.<String> newKeySet();
    
    public EnnMonitorLogStorageOriginESJob(String esHostList, String clusterName) throws Exception {
        Settings settings = Settings.builder()
                .put("cluster.name", clusterName)
                .put("client.transport.sniff", false)
                .build();

        esTransportClient = new PreBuiltTransportClient(settings);

        String[] hostList = esHostList.split(",");
        String[] ipPort = null;
        for (int i = 0; i < hostList.length; ++i) {
                ipPort = hostList[i].split(":");
                esTransportClient.addTransportAddress(new InetSocketTransportAddress(
                                InetAddress.getByName(ipPort[0]), Integer.parseInt(ipPort[1])));
        }
        
    }
    
	@Override
	protected void operator(ChannelWriteData stockWriteData) throws Exception {
		String date = null;
		String json = null;
		ESLog esLog = null;
		
		json = (String) stockWriteData.getObject();
		
		esLog = gson.fromJson(json, ESLog.class);

        if (esTransportClient == null) {
            throw new Exception("the esTransportClient is null");
        }

        if (esLog.getDateTime() != null) {
        	esLog.setDateTime(esLog.getDateTime().replaceAll(" ", "T"));
        	esLog.setDateTime(esLog.getDateTime() + "+0800");
        }

        if (bulkRequest == null) {
            bulkRequest = esTransportClient.prepareBulk();
        }
        
        computeStats(esLog);

        if (esLog.getCreateTime() != 0l) {
            date = EnnDatetimeUtil.convertLongToStringWithDate(esLog.getCreateTime(), "-", EnnTimezoneUtil.getChinaTimeZone());
        } else {
            date = "";
        }

        String index = null;
        if (esLog.getNameSpace() != null && esLog.getNameSpace().equals("") == false) {
        	index = esLog.getClusterName() + "-" + esLog.getNameSpace() + "-" + date;
        } else {
        	index = esLog.getClusterName() + "-" + esLog.getAppName() + "-" + date;
        }
        json = gson.toJson(esLog);
        bulkRequest.add(esTransportClient.prepareIndex(index, "log", null).setSource(new BytesArray(json), XContentFactory.xContentType(json)));

        if (bulkRequest.numberOfActions() >= 2000) {
            insertItem();
        }
	}
	
    protected void operatorNull() throws Exception {
        insertItem();
    }
	
    private void insertItem() throws Exception {
        if (bulkRequest == null) {
                return;
        }

        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            logger.error(bulkResponse.buildFailureMessage());
        }
        bulkRequest = null;
    }
    
    private void computeStats(ESLog esLog) throws Exception {
    	String key = null;
    	
    	logMetrics.markEvent();
        key = EnnMonitorLogStorageESLogStatsUtil.generatorKey(esLog);
		if (tagKeySet.contains(key) == false) {
			EnnMonitorSecurityGatewayStatsUtil.register(key, EnnMonitorLogStorageESLogStatsUtil.getMetricsName(), EnnMonitorLogStorageESLogStatsUtil.generatorTagsMap(esLog));
			tagKeySet.add(key);
		}
		EnnMonitorSecurityGatewayStatsUtil.count(key);
    }
    
}
