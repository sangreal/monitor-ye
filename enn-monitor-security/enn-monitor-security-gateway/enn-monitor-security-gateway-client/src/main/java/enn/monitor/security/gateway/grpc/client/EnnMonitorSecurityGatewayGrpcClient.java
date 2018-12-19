package enn.monitor.security.gateway.grpc.client;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

import java.util.Map;

import org.apache.log4j.Logger;

import com.google.common.util.concurrent.ListenableFuture;

import brave.Span;
import enn.monitor.framework.tracing.EnnTracer;
import enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayRequest;
import enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayResponse;
import enn.monitor.security.gateway.server.EnnMonitorSecurityGatewayServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;

public class EnnMonitorSecurityGatewayGrpcClient {
	
	private ManagedChannel channel;
	private EnnMonitorSecurityGatewayServerGrpc.EnnMonitorSecurityGatewayServerBlockingStub futureBlockingStub = null;
	private EnnMonitorSecurityGatewayServerGrpc.EnnMonitorSecurityGatewayServerFutureStub futureStub = null;
	private EnnTracer tracer;

	public EnnMonitorSecurityGatewayGrpcClient(String host, int port) {
		this(host, port, null);
	}

	public EnnMonitorSecurityGatewayGrpcClient(String host, int port, EnnTracer tracer) {
		this.tracer = tracer;
		ManagedChannelBuilder builder = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext(true);
		if (tracer != null) {
			builder = builder.intercept(tracer.getGrpcClientInterceptor());
		}
		channel = builder
				.build();
		futureBlockingStub = EnnMonitorSecurityGatewayServerGrpc.newBlockingStub(channel);
		futureStub = EnnMonitorSecurityGatewayServerGrpc.newFutureStub(channel);
	}

	public void attachHeader(String key, String value) {
		Metadata extraHeaders = new Metadata();
		extraHeaders.put(Metadata.Key.of(key, ASCII_STRING_MARSHALLER), value);
		futureBlockingStub = MetadataUtils.attachHeaders(futureBlockingStub, extraHeaders);
		futureStub = MetadataUtils.attachHeaders(futureStub, extraHeaders);
	}

	public EnnMonitorSecurityGatewayResponse put(EnnMonitorSecurityGatewayRequest request) {
		return put(request, null);
	}

	public EnnMonitorSecurityGatewayResponse put(EnnMonitorSecurityGatewayRequest request, Map<String, String> customHeaders) {
		Span span = null;
		if (tracer != null) {
			span = sendData(null);
		}

		if (customHeaders != null) {
			Metadata extraHeaders = new Metadata();
			customHeaders.forEach((key, value) -> {
				extraHeaders.put(Metadata.Key.of(key, ASCII_STRING_MARSHALLER), value);
			});
			futureBlockingStub = MetadataUtils.attachHeaders(futureBlockingStub, extraHeaders);
		}
		EnnMonitorSecurityGatewayResponse response = futureBlockingStub.put(request);

		if (tracer != null) {
			span.finish();
			tracer.flush();
		}
		return response;
	}

	public ListenableFuture<EnnMonitorSecurityGatewayResponse> putAsync(EnnMonitorSecurityGatewayRequest request) {
		ListenableFuture<EnnMonitorSecurityGatewayResponse> listenableFuture =
				futureStub.put(request);
		return listenableFuture;
	}

	private Span sendData(Span span) {
		if (span == null) {
			span = tracer.newRootSpan("client-send");
		} else {
			tracer.joinTrace(span);
		}
//		System.out.println("start put tidstr: " + span.context().traceIdString());
//		System.out.println("start put tid: " + span.context().traceId());
//		System.out.println("start put sid: " + span.context().spanId());
		span.start();
//		tracer.injectL4J();

		return span;
	}

	public static void main(String[] args) {
//		EnnTracer tracer = TracerManager.getInstance();
//		System.out.println(TracerBuilder.config.getReportServer());
//		EnnMonitorSecurityGatewayGrpcClient gatewayClient1 =
//				new EnnMonitorSecurityGatewayGrpcClient("10.19.140.200", 30111, tracer);
		EnnMonitorSecurityGatewayGrpcClient gatewayClient1 = GrpcClientManager.getInstance();
//				new EnnMonitorSecurityGatewayGrpcClient("127.0.0.1", 10100, tracer);
//		EnnMonitorSecurityGatewayGrpcClient gatewayClient2 =
//				new EnnMonitorSecurityGatewayGrpcClient("127.0.0.1", 10101, TracerBuilder.get());
		while (true) {
			EnnMonitorSecurityGatewayRequest.Builder requestBuilder = EnnMonitorSecurityGatewayRequest.newBuilder();
			requestBuilder.setToken(GrpcClientManager.getConfig().getToken());
			requestBuilder.setSource("test_source");
			requestBuilder.setJsonList("{\"metric\": \"cpu\", \"timestamp\" : 1231241, \"value\": 123.42}");
			EnnMonitorSecurityGatewayRequest request = requestBuilder.build();
			Logger.getRootLogger().info("start");
			gatewayClient1.put(request);
			Logger.getRootLogger().info("end");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
