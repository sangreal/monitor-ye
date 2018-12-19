package enn.monitor.security.gateway.server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.GaugeLong;
import org.avaje.metric.MetricManager;

import com.beust.jcommander.JCommander;

import enn.monitor.config.business.topic.client.EnnMonitorConfigBusinessTopicClient;
import enn.monitor.config.service.client.EnnMonitorConfigServiceClient;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.pipe.client.EnnMonitorFrameworkPipeClientThread;
import enn.monitor.framework.tracing.EnnTracer;
import enn.monitor.security.gateway.channel.GatewayChannelWriteTask;
import enn.monitor.security.gateway.impl.EnnMonitorSecurityGatewayImpl;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;
import enn.monitor.security.gateway.parameters.Parameters;
import enn.monitor.security.gateway.token.cache.EnnMonitorConfigServiceTokenCache;
import enn.monitor.security.gateway.token.pipe.EnnMonitorSecurityGatewayConfigTokenPipeClientImpl;
import enn.monitor.security.gateway.topic.cache.EnnMonitorConfigBussinessTopicCache;
import enn.monitor.security.gateway.topic.pipe.EnnMonitorSecurityGatewayConfigTopicPipeClientImpl;
import enn.monitor.security.gateway.trace.util.TraceManager;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

public class EnnMonitorSecurityGatewayServer {
	
	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) throws Exception {
		EnnMonitorMetricsGatewayGrpc metricsGrpc = null;
		GatewayChannelWriteTask writeTask = null;
		
		// token
		EnnMonitorSecurityGatewayConfigTokenPipeClientImpl tokenClientImpl = null;
		EnnMonitorFrameworkPipeClientThread tokenClientThread = null;
		EnnMonitorConfigServiceTokenCache tokenCache = null;
		
		// topic
		EnnMonitorSecurityGatewayConfigTopicPipeClientImpl topicClientImpl = null;
		EnnMonitorFrameworkPipeClientThread topicClientThread = null;
		EnnMonitorConfigBussinessTopicCache topicCache = null;
		
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
	    
	    // topic
	    topicCache = new EnnMonitorConfigBussinessTopicCache(parameters.kafkaUrl, null);
	    topicClientImpl = new EnnMonitorSecurityGatewayConfigTopicPipeClientImpl(new EnnMonitorConfigBusinessTopicClient(parameters.configBusinessIp, parameters.configBusinessPort), topicCache);
	    topicClientThread = new EnnMonitorFrameworkPipeClientThread(topicClientImpl);
	    new Thread(topicClientThread).start();
	    
	    // token
	    tokenCache = new EnnMonitorConfigServiceTokenCache();
        tokenClientImpl = new EnnMonitorSecurityGatewayConfigTokenPipeClientImpl(new EnnMonitorConfigServiceClient(parameters.configServiceIp, parameters.configServicePort), tokenCache);
        tokenClientThread = new EnnMonitorFrameworkPipeClientThread(tokenClientImpl);
        new Thread(tokenClientThread).start();
	    
	    // 创建线程池
        final BlockingQueue<ChannelWriteData> stockQueue = new LinkedBlockingQueue<ChannelWriteData>();
        for (int i = 0; i < parameters.workThreadNum; ++i) {
        	writeTask = new GatewayChannelWriteTask(topicCache, parameters.enableTokenFilter);
        	writeTask.setTaskQueue(stockQueue);
        	writeTask.start();
        }
	    
        // 监控队列
        GaugeLong dataSizeGuageLong = new GaugeLong() {
            @Override
            public long getValue() {
                return stockQueue.size();
            }
        };
        MetricManager.register(MetricManager.name(EnnMonitorSecurityGatewayServer.class, "queueSize"), dataSizeGuageLong);
        
	    // 启动服务
		Server server = ServerBuilder.forPort(parameters.listenPort)
	            .addService(ServerInterceptors.intercept(
	            		new EnnMonitorSecurityGatewayImpl(stockQueue, tokenCache),
						TraceManager.getInstance().getGrpcServerInterceptor(parameters.createNewTrace))
				)
	            .executor(Executors.newFixedThreadPool(parameters.workThreadNum))
	            .build();
	    logger.info("server start");
	    server.start();
	    server.awaitTermination();
	}

}
