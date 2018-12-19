package enn.monitor.log.normalizing.data;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import com.google.gson.Gson;

import enn.monitor.framework.kafka.EnnKafkaProducer;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.log.channel.ChannelWriteTask;
import enn.monitor.framework.log.proto.ESLog;
import enn.monitor.log.normalizing.logformat.cache.EnnMonitorLogNormalizingLogformatCache;
import enn.monitor.log.normalizing.logformat.cache.EnnMonitorLogNormalizingLogformatCache.EnnMonitorLogNormalizingLogFormat;

public class EnnMonitorLogNormalizingDataProcessor extends ChannelWriteTask {
	
	private static final Logger logger = LogManager.getLogger(EnnMonitorLogNormalizingDataProcessor.class);
	
	private Gson gson = new Gson();
	
	private EnnKafkaProducer<Long, String> normalLogProducer = null;
	private EnnKafkaProducer<Long, String> originLogProducer = null;
	
	private CounterMetric log_origin = MetricManager.getCounterMetric(EnnMonitorLogNormalizingDataProcessor.class, "etl_log_origin");
	private CounterMetric log_normal = MetricManager.getCounterMetric(EnnMonitorLogNormalizingDataProcessor.class, "etl_log_normal");
	
	public EnnMonitorLogNormalizingDataProcessor(EnnKafkaProducer<Long, String> normalLogProducer, EnnKafkaProducer<Long, String> originLogProducer) {
		this.normalLogProducer = normalLogProducer;
		this.originLogProducer = originLogProducer;
	}

	@Override
	protected void operator(ChannelWriteData stockWriteData) throws Exception {
		String log = (String) stockWriteData.getObject();
		ESLog esLog = null;
		
		String preLog = null;
		
		EnnMonitorLogNormalizingLogFormat logformat = null;
		
		Map<String, String> resultMap = null;
		
		int splitIndex = log.indexOf("|");
        String token = "";
        long tokenId = 0l;
        
        if (splitIndex == -1) {
        	return;
        }
        
        preLog = log;
        
        token = log.substring(0, splitIndex);
        log = log.substring(splitIndex + 1, log.length());
        
        if (token == null || token.equals("") == true) {
        	logger.error(preLog);
        	throw new Exception("tokenId is null");
        }
        
        tokenId = Long.parseLong(token);
        logformat = EnnMonitorLogNormalizingLogformatCache.getEnnMonitorLogNormalizingLogFormat(tokenId);
        esLog = gson.fromJson(log, ESLog.class);
        esLog.setToken("" + tokenId);
        
        if (logformat == null) {
        	log_origin.markEvent();
        	originLogProducer.send(null, gson.toJson(esLog), false);
        } else {
        	log_normal.markEvent();
        	if (logformat != null && esLog.getLog() != null) {
        		resultMap = logformat.regexFSM.match(esLog.getLog());
        		if (resultMap != null) {
        			resultMap = logformat.aggregatorFSM.aggregator(resultMap);
        			esLog.parse(resultMap);
        		}
            } 
            
            normalLogProducer.send(null, gson.toJson(esLog), false);
        }
        
        
	}

}
