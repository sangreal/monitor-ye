package enn.monitor.log.storage.origin.es.server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.GaugeLong;
import org.avaje.metric.MetricManager;

import com.beust.jcommander.JCommander;

import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.log.storage.es.data.EnnMonitorLogStorageESDataCollector;
import enn.monitor.log.storage.origin.es.job.EnnMonitorLogStorageOriginESJob;
import enn.monitor.log.storage.origin.es.parameters.Parameters;
import enn.monitor.security.gateway.metrics.EnnMonitorGatewayParameter;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;
import enn.monitor.security.gateway.stats.util.EnnMonitorSecurityGatewayStatsUtil;

public class EnnMonitorLogStorageOriginESServer {
	
	private static final Logger logger = LogManager.getLogger();
	
    public static void main(String[] args) throws Exception {
        Parameters parameters = new Parameters();
        JCommander jc = new JCommander(parameters, args);
        if (parameters.help) {
            jc.usage();
            return;
        }
        
        EnnMonitorLogStorageESDataCollector collector = null;
        BlockingQueue<ChannelWriteData> dataQueue = new LinkedBlockingQueue<ChannelWriteData>();
        
        initServer(parameters);
        
	    collector = new EnnMonitorLogStorageESDataCollector(parameters.kafkaUrl, parameters.topicOrigin, parameters.groupId, dataQueue, parameters.queueSize);
	    collector.startJob();
	    
	    EnnMonitorLogStorageOriginESJob dataProcessor = null;
	    for (int i = 0; i < parameters.workThreadNum; ++i) {
        	dataProcessor = new EnnMonitorLogStorageOriginESJob(parameters.esHost, parameters.esCluster);
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
        MetricManager.register(MetricManager.name(EnnMonitorLogStorageOriginESServer.class, "queueSize"), dataSizeGuageLong);
        
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
