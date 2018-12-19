package enn.monitor.log.normalizing.server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.GaugeLong;
import org.avaje.metric.MetricManager;

import com.beust.jcommander.JCommander;

import enn.monitor.framework.kafka.EnnKafkaProducer;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.pipe.client.EnnMonitorFrameworkPipeClientThread;
import enn.monitor.framework.tracing.EnnTracer;
import enn.monitor.log.config.logformat.client.EnnMonitorLogConfigLogformatClient;
import enn.monitor.log.normalizing.data.EnnMonitorLogNormalizingCollector;
import enn.monitor.log.normalizing.data.EnnMonitorLogNormalizingDataProcessor;
import enn.monitor.log.normalizing.impl.EnnMonitorLogNormalizingImpl;
import enn.monitor.log.normalizing.logformat.pipe.EnnMonitorLogNormalizingLogformatPipeClientImpl;
import enn.monitor.log.normalizing.parameters.Parameters;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;
import enn.monitor.security.gateway.trace.util.TraceManager;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

public class EnnMonitorLogNormalizingServer {
	
	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws Exception {
		try {
			EnnMonitorMetricsGatewayGrpc metricsGrpc = null;
			
			EnnMonitorLogNormalizingDataProcessor dataProcessor = null;
			
			EnnMonitorLogNormalizingLogformatPipeClientImpl logformatClientImpl = null;
			EnnMonitorFrameworkPipeClientThread logformatClientThread = null;
			
			EnnKafkaProducer<Long, String> logNormalProducer = null;
			EnnKafkaProducer<Long, String> logOriginProducer = null;
			
			Parameters parameters = new Parameters();
		    JCommander jc = new JCommander(parameters, args);
		    if (parameters.help) {
		    	jc.usage();
		    	return;
		    }
		    
		    EnnTracer ennTracer = TraceManager.init();
			logger.info("Tracer config: " + ennTracer.getConfig());
		    
			metricsGrpc = new EnnMonitorMetricsGatewayGrpc();
		    metricsGrpc.startMetricsCollector();
		    
		    logformatClientImpl = 
		    		new EnnMonitorLogNormalizingLogformatPipeClientImpl(
		    				new EnnMonitorLogConfigLogformatClient(parameters.logConfigIp, parameters.logConfigPort));
		    logformatClientThread = new EnnMonitorFrameworkPipeClientThread(logformatClientImpl);
		    new Thread(logformatClientThread).start();
		    
		    BlockingQueue<ChannelWriteData> dataQueue = new LinkedBlockingQueue<ChannelWriteData>();
		    
		    // start kafka
		    EnnMonitorLogNormalizingCollector collector = new EnnMonitorLogNormalizingCollector(parameters.kafkaUrl, parameters.fromTopic, parameters.groupId, dataQueue);
		    collector.startJob();
		    
		    logNormalProducer = genKafkaProducer(parameters.kafkaUrl, parameters.toTopicNormal);
		    logOriginProducer = genKafkaProducer(parameters.kafkaUrl, parameters.toTopicOrigin);
		    
		    // start data process thread
	        for (int i = 0; i < parameters.workThreadNum; ++i) {
	        	dataProcessor = new EnnMonitorLogNormalizingDataProcessor(logNormalProducer, logOriginProducer);
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
	        MetricManager.register(MetricManager.name(EnnMonitorLogNormalizingServer.class, "queueSize"), dataSizeGuageLong);
		    
		    Server server = ServerBuilder.forPort(parameters.listenPort)
		            .addService(ServerInterceptors.intercept(new EnnMonitorLogNormalizingImpl()))
//		            .executor(Executors.newFixedThreadPool(parameters.workThreadNum))
		            .build();
		    logger.info("server start");
		    server.start();
		    server.awaitTermination();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}
	
	private static EnnKafkaProducer<Long, String> genKafkaProducer(String kafkaUrl, String topic) {
		EnnKafkaProducer<Long, String> ennKafkaProducer = null;
		
		ennKafkaProducer = (EnnKafkaProducer<Long, String>) new EnnKafkaProducer.Builder()
                .setUrl(kafkaUrl)
                .setTopic(topic)
                .setKeySerializer("org.apache.kafka.common.serialization.LongSerializer")
                .setValueSerializer("org.apache.kafka.common.serialization.StringSerializer")
                .build();
		ennKafkaProducer.wrapTracing(TraceManager.getInstance());
		
		return ennKafkaProducer;
	}
	
}
