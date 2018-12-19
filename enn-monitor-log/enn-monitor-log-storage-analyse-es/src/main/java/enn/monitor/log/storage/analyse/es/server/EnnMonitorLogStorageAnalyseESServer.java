package enn.monitor.log.storage.analyse.es.server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.GaugeLong;
import org.avaje.metric.MetricManager;

import com.beust.jcommander.JCommander;

import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.log.analyse.storge.client.EnnMonitorLogAnalyseStorageClient;
import enn.monitor.log.config.cache.client.EnnMonitorLogConfigCacheClient;
import enn.monitor.log.storage.analyse.es.cache.EnnMonitorLogStorageAnalyseESPipeClientImpl;
import enn.monitor.log.storage.es.data.EnnMonitorLogStorageESDataCollector;
import enn.monitor.log.storage.analyse.es.job.EnnMonitorLogStorageAnalyseESJob;
import enn.monitor.log.storage.analyse.es.parameters.Parameters;
import enn.monitor.security.gateway.metrics.EnnMonitorGatewayParameter;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;
import enn.monitor.security.gateway.stats.util.EnnMonitorSecurityGatewayStatsUtil;

public class EnnMonitorLogStorageAnalyseESServer {
	
	private static final Logger logger = LogManager.getLogger();
	
    public static void main(String[] args) throws Exception {
        Parameters parameters = new Parameters();
        JCommander jc = new JCommander(parameters, args);
        if (parameters.help) {
            jc.usage();
            return;
        }
        
        EnnMonitorLogAnalyseStorageClient analyseStorageClient = null;
        EnnMonitorLogConfigCacheClient logConfigCacheClient = null;
        
        EnnMonitorLogStorageESDataCollector collector = null;
        BlockingQueue<ChannelWriteData> dataQueue = new LinkedBlockingQueue<ChannelWriteData>();
        
        initServer(parameters);
        
        collector = new EnnMonitorLogStorageESDataCollector(parameters.kafkaUrl, parameters.topicNormal, parameters.groupId, dataQueue, parameters.queueSize);
	    collector.startJob();
	    
	    logConfigCacheClient = new EnnMonitorLogConfigCacheClient(parameters.logConfigCacheHost, parameters.logConfigCachePort);
	    EnnMonitorLogStorageAnalyseESJob dataProcessor = null;
	    for (int i = 0; i < parameters.workThreadNum; ++i) {
        	dataProcessor = new EnnMonitorLogStorageAnalyseESJob(parameters.esHost, parameters.esCluster, logConfigCacheClient);
        	dataProcessor.setTaskQueue(dataQueue);
        	dataProcessor.start();
        }
	    
	 // 监控队列
        GaugeLong dataSizeGuageLong = new GaugeLong() {
            @Override
            public long getValue() {
                return dataQueue.size();
            }
        };
        MetricManager.register(MetricManager.name(EnnMonitorLogStorageAnalyseESServer.class, "queueSize"), dataSizeGuageLong);
        
        analyseStorageClient = new EnnMonitorLogAnalyseStorageClient(parameters.analyseStorageHost, parameters.analyseStoragePort);
        new Thread(new EnnMonitorLogStorageAnalyseESPipeClientImpl(analyseStorageClient)).start();
        
        logger.info("server start");
        
        while (true) {
        	Thread.sleep(100000000);
        }

    }
    
    private static void initServer(Parameters parameters) throws Exception {
    	EnnMonitorMetricsGatewayGrpc metricsGrpc = null;
        
        metricsGrpc = new EnnMonitorMetricsGatewayGrpc();
	    metricsGrpc.startMetricsCollector(
        		parameters.enableMetrics, new EnnMonitorGatewayParameter(parameters.gatewayHost, parameters.gatewayPort), 1,
        		parameters.token);
	    
	    EnnMonitorSecurityGatewayStatsUtil.init(parameters.gatewayHost, parameters.gatewayPort, parameters.token, 1, false);
	    
	    logger.info("enableMetrics is " + parameters.enableMetrics);
	    logger.info("the monitor host is {}, the monitor port is {}, the monitor token is {}", 
	    		parameters.gatewayHost, parameters.gatewayPort, parameters.token);
    }

}
