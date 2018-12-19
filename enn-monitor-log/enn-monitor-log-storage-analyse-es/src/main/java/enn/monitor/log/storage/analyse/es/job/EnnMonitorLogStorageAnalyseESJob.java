package enn.monitor.log.storage.analyse.es.job;

import java.net.InetAddress;
import java.util.List;
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

import enn.monitor.framework.ai.nn.NNFramework;
import enn.monitor.framework.ai.nn.activation.NNActivationEnum;
import enn.monitor.framework.ai.nn.weights.NNWeightEnum;
import enn.monitor.framework.common.time.EnnDatetimeUtil;
import enn.monitor.framework.common.time.EnnTimezoneUtil;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.log.channel.ChannelWriteTask;
import enn.monitor.framework.log.proto.ESLog;
import enn.monitor.log.analyse.data.EnnMonitorLogAnalyseData;
import enn.monitor.log.config.cache.client.EnnMonitorLogConfigCacheClient;
import enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheRequest;
import enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheResponse;
import enn.monitor.log.storage.analyse.es.cache.EnnMonitorLogStorageAnalyseESPipeCache;
import enn.monitor.log.storage.es.util.EnnMonitorLogStorageESLogAnalyseUtil;
import enn.monitor.log.storage.es.util.EnnMonitorLogStorageESLogStatsUtil;
import enn.monitor.security.gateway.stats.util.EnnMonitorSecurityGatewayStatsUtil;

public class EnnMonitorLogStorageAnalyseESJob extends ChannelWriteTask {
	
private static final Logger logger = LogManager.getLogger();
	
	private Gson gson = new Gson();
	
	private CounterMetric logMetrics = MetricManager.getCounterMetric(EnnMonitorLogStorageAnalyseESJob.class, "logCount");
	private CounterMetric logAnalyseMetrics = MetricManager.getCounterMetric(EnnMonitorLogStorageAnalyseESJob.class, "logAnalyse");
	
	private EnnMonitorLogConfigCacheClient logConfigCacheClient = null;
	
    private BulkRequestBuilder bulkRequest = null;
    private static TransportClient esTransportClient = null;
    
    private Set<String> tagKeySet = ConcurrentHashMap.<String> newKeySet();
    
    public EnnMonitorLogStorageAnalyseESJob(String esHostList, String clusterName, EnnMonitorLogConfigCacheClient logConfigCacheClient) throws Exception {
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
        
        this.logConfigCacheClient = logConfigCacheClient;
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
        computeNN(esLog);

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
    
    private void computeNN(ESLog esLog) throws Exception {
    	int i;
    	double maxMatch = Double.MIN_VALUE;
		int pos = -1;
		
		String template = null;
		
		String key = null;
    	
    	EnnMonitorLogAnalyseData analyseData = null;
    	List<Double> outputList = null;
    	
    	EnnMonitorLogConfigCacheResponse response = null;
    	
    	if (esLog.getToken() == null || esLog.getToken().equals("") == true || 
    			esLog.getLog() == null || esLog.getLog().equals("") == true) {
    		return;
    	}
    	
    	analyseData = EnnMonitorLogStorageAnalyseESPipeCache.get(Long.parseLong(esLog.getToken()));
    	if (analyseData == null) {
    		return;
    	}
    	
    	logAnalyseMetrics.markEvent();
    	
    	outputList = NNFramework.compute(
    			analyseData.deployNNObject(), analyseData.getTrainNNData().formatInputList(esLog.getLog()), 
				null, NNActivationEnum.Sigmoid, NNWeightEnum.Momentum, 0, 0);
    	
		for (i = 0; i < outputList.size(); ++i) {
			if (maxMatch < outputList.get(i)) {
				maxMatch = outputList.get(i);
				pos = i;
			}
		}
		
		template = analyseData.getTrainNNData().getResults(pos);
		if (template == null) {
			return;
		}
		esLog.setTemplateKey(template);
		
		try {
			response = logConfigCacheClient.getTag(EnnMonitorLogConfigCacheRequest.newBuilder().setTemplateKey(template).build());
			if (response.getIsSuccess() == true) {
				if (response.getTag() != null && response.getTag().equals("") == false) {
					esLog.setTag(response.getTag());
					
					key = EnnMonitorLogStorageESLogAnalyseUtil.generatorKey(esLog, response.getTag());
					if (tagKeySet.contains(key) == false) {
						EnnMonitorSecurityGatewayStatsUtil.register(key, EnnMonitorLogStorageESLogAnalyseUtil.getMetricsName(), EnnMonitorLogStorageESLogAnalyseUtil.generatorTagsMap(esLog, response.getTag()));
						tagKeySet.add(key);
					}
					EnnMonitorSecurityGatewayStatsUtil.count(key);
				}
			} else {
				logger.error(response.getError());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		esLog.setMatch(maxMatch);
    }
    
}
