package enn.monitor.security.gateway.server;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.1.0-SNAPSHOT)",
    comments = "Source: protobuf/gatewayServer.proto")
public class EnnMonitorSecurityGatewayServerGrpc {

  private EnnMonitorSecurityGatewayServerGrpc() {}

  public static final String SERVICE_NAME = "EnnMonitorSecurityGatewayServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayRequest,
      enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayResponse> METHOD_PUT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorSecurityGatewayServer", "put"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnnMonitorSecurityGatewayServerStub newStub(io.grpc.Channel channel) {
    return new EnnMonitorSecurityGatewayServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorSecurityGatewayServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnnMonitorSecurityGatewayServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorSecurityGatewayServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnnMonitorSecurityGatewayServerFutureStub(channel);
  }

  /**
   */
  public static abstract class EnnMonitorSecurityGatewayServerImplBase implements io.grpc.BindableService {

    /**
     */
    public void put(enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_PUT, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_PUT,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayRequest,
                enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayResponse>(
                  this, METHODID_PUT)))
          .build();
    }
  }

  /**
   */
  public static final class EnnMonitorSecurityGatewayServerStub extends io.grpc.stub.AbstractStub<EnnMonitorSecurityGatewayServerStub> {
    private EnnMonitorSecurityGatewayServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorSecurityGatewayServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorSecurityGatewayServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorSecurityGatewayServerStub(channel, callOptions);
    }

    /**
     */
    public void put(enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_PUT, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnnMonitorSecurityGatewayServerBlockingStub extends io.grpc.stub.AbstractStub<EnnMonitorSecurityGatewayServerBlockingStub> {
    private EnnMonitorSecurityGatewayServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorSecurityGatewayServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorSecurityGatewayServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorSecurityGatewayServerBlockingStub(channel, callOptions);
    }

    /**
     */
    public enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayResponse put(enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_PUT, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnnMonitorSecurityGatewayServerFutureStub extends io.grpc.stub.AbstractStub<EnnMonitorSecurityGatewayServerFutureStub> {
    private EnnMonitorSecurityGatewayServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorSecurityGatewayServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorSecurityGatewayServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorSecurityGatewayServerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayResponse> put(
        enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_PUT, getCallOptions()), request);
    }
  }

  private static final int METHODID_PUT = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnnMonitorSecurityGatewayServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(EnnMonitorSecurityGatewayServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PUT:
          serviceImpl.put((enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_PUT);
  }

}
