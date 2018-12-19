package enn.monitor.log.normalizing.server;

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
    comments = "Source: protobuf/normalizingServer.proto")
public class EnnMonitorLogNormalizingServerGrpc {

  private EnnMonitorLogNormalizingServerGrpc() {}

  public static final String SERVICE_NAME = "EnnMonitorLogNormalizingServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingRequest,
      enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingResponse> METHOD_NORMALIZING =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogNormalizingServer", "Normalizing"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnnMonitorLogNormalizingServerStub newStub(io.grpc.Channel channel) {
    return new EnnMonitorLogNormalizingServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogNormalizingServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogNormalizingServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogNormalizingServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogNormalizingServerFutureStub(channel);
  }

  /**
   */
  public static abstract class EnnMonitorLogNormalizingServerImplBase implements io.grpc.BindableService {

    /**
     */
    public void normalizing(enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_NORMALIZING, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_NORMALIZING,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingRequest,
                enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingResponse>(
                  this, METHODID_NORMALIZING)))
          .build();
    }
  }

  /**
   */
  public static final class EnnMonitorLogNormalizingServerStub extends io.grpc.stub.AbstractStub<EnnMonitorLogNormalizingServerStub> {
    private EnnMonitorLogNormalizingServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogNormalizingServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogNormalizingServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogNormalizingServerStub(channel, callOptions);
    }

    /**
     */
    public void normalizing(enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_NORMALIZING, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnnMonitorLogNormalizingServerBlockingStub extends io.grpc.stub.AbstractStub<EnnMonitorLogNormalizingServerBlockingStub> {
    private EnnMonitorLogNormalizingServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogNormalizingServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogNormalizingServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogNormalizingServerBlockingStub(channel, callOptions);
    }

    /**
     */
    public enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingResponse normalizing(enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_NORMALIZING, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnnMonitorLogNormalizingServerFutureStub extends io.grpc.stub.AbstractStub<EnnMonitorLogNormalizingServerFutureStub> {
    private EnnMonitorLogNormalizingServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogNormalizingServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogNormalizingServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogNormalizingServerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingResponse> normalizing(
        enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_NORMALIZING, getCallOptions()), request);
    }
  }

  private static final int METHODID_NORMALIZING = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnnMonitorLogNormalizingServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(EnnMonitorLogNormalizingServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_NORMALIZING:
          serviceImpl.normalizing((enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingResponse>) responseObserver);
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
        METHOD_NORMALIZING);
  }

}
