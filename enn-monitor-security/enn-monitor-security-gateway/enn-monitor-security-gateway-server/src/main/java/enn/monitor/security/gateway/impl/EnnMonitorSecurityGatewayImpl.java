package enn.monitor.security.gateway.impl;

import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import brave.Span;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.tracing.TracingWrapper;
import enn.monitor.security.gateway.channel.GatewayChannelWriteTask;
import enn.monitor.security.gateway.channel.GatewayData;
import enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayResponse;
import enn.monitor.security.gateway.server.EnnMonitorSecurityGatewayServerGrpc;
import enn.monitor.security.gateway.token.cache.EnnMonitorConfigServiceTokenCache;
import enn.monitor.security.gateway.trace.util.TraceManager;

public class EnnMonitorSecurityGatewayImpl extends EnnMonitorSecurityGatewayServerGrpc.EnnMonitorSecurityGatewayServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private BlockingQueue<ChannelWriteData> queue = null;
	private EnnMonitorConfigServiceTokenCache ennMonitorGatewayIndex = null;
	private TracingWrapper<ChannelWriteData> tracingWrapper = null;
	
	private CounterMetric putMetrics = MetricManager.getCounterMetric(EnnMonitorSecurityGatewayImpl.class, "put");
	
	public EnnMonitorSecurityGatewayImpl(BlockingQueue<ChannelWriteData> queue, EnnMonitorConfigServiceTokenCache ennMonitorGatewayIndex) {
		this.queue = queue;
		this.ennMonitorGatewayIndex = ennMonitorGatewayIndex;
		this.tracingWrapper = new TracingWrapper<>();
	}
	
	public void put(enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayRequest request,
	        io.grpc.stub.StreamObserver<enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayResponse> responseObserver) {
		Span parent = TraceManager.getInstance().currentSpan();
		Span writingQueue = null;
		if (parent != null) {
			writingQueue = TraceManager.getInstance().newChild("enqueue");
			logger.info("Data enqueue to be processed later.");
		}
		EnnMonitorSecurityGatewayResponse.Builder responseBuilder = null;
		String tokenId = null;

		if (writingQueue != null) {
			writingQueue.start();
		}

		try {
			if (parent != null) {
				logger.info("receiving data, put in queue to process.");
			}
			GatewayData gateData = null;
			ChannelWriteData channelWriteData = tracingWrapper
					.wrapObject(ChannelWriteData.class);

			responseBuilder = EnnMonitorSecurityGatewayResponse.newBuilder();
			tokenId = ennMonitorGatewayIndex.getTokenId(request.getToken());

			gateData = new GatewayData();
			gateData.setTokenId(tokenId);
			gateData.setJsonList(request.getJsonList());
			gateData.setSource(request.getSource());
			channelWriteData.setObject(gateData);

			if (parent != null) {
				TraceManager.getInstance().inject(channelWriteData);
			}

			GatewayChannelWriteTask.write(queue, channelWriteData);
			if (parent != null) {
				logger.info("finish processing data.");
			}
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		} finally {
			if (writingQueue != null) {
				writingQueue.finish();
			}
		}
		
		putMetrics.markEvent();
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
	}

}
