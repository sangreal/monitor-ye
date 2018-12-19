package enn.monitor.log.config.common.server;

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
    comments = "Source: protobuf/logCommonServer.proto")
public class EnnMonitorLogConfigCommonServerGrpc {

  private EnnMonitorLogConfigCommonServerGrpc() {}

  public static final String SERVICE_NAME = "EnnMonitorLogConfigCommonServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.common.parameters.EnnMonitorLogConfigCommonBatchIdGetRequest,
      enn.monitor.log.config.common.parameters.EnnMonitorLogConfigBatchIdGetResponse> METHOD_GET_BATCH_ID =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigCommonServer", "GetBatchId"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.common.parameters.EnnMonitorLogConfigCommonBatchIdGetRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.common.parameters.EnnMonitorLogConfigBatchIdGetResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnnMonitorLogConfigCommonServerStub newStub(io.grpc.Channel channel) {
    return new EnnMonitorLogConfigCommonServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogConfigCommonServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogConfigCommonServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogConfigCommonServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogConfigCommonServerFutureStub(channel);
  }

  /**
   */
  public static abstract class EnnMonitorLogConfigCommonServerImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * get batchId
     * </pre>
     */
    public void getBatchId(enn.monitor.log.config.common.parameters.EnnMonitorLogConfigCommonBatchIdGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.common.parameters.EnnMonitorLogConfigBatchIdGetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_BATCH_ID, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_BATCH_ID,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.common.parameters.EnnMonitorLogConfigCommonBatchIdGetRequest,
                enn.monitor.log.config.common.parameters.EnnMonitorLogConfigBatchIdGetResponse>(
                  this, METHODID_GET_BATCH_ID)))
          .build();
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigCommonServerStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigCommonServerStub> {
    private EnnMonitorLogConfigCommonServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigCommonServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigCommonServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigCommonServerStub(channel, callOptions);
    }

    /**
     * <pre>
     * get batchId
     * </pre>
     */
    public void getBatchId(enn.monitor.log.config.common.parameters.EnnMonitorLogConfigCommonBatchIdGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.common.parameters.EnnMonitorLogConfigBatchIdGetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_BATCH_ID, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigCommonServerBlockingStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigCommonServerBlockingStub> {
    private EnnMonitorLogConfigCommonServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigCommonServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigCommonServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigCommonServerBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * get batchId
     * </pre>
     */
    public enn.monitor.log.config.common.parameters.EnnMonitorLogConfigBatchIdGetResponse getBatchId(enn.monitor.log.config.common.parameters.EnnMonitorLogConfigCommonBatchIdGetRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_BATCH_ID, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigCommonServerFutureStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigCommonServerFutureStub> {
    private EnnMonitorLogConfigCommonServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigCommonServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigCommonServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigCommonServerFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * get batchId
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.common.parameters.EnnMonitorLogConfigBatchIdGetResponse> getBatchId(
        enn.monitor.log.config.common.parameters.EnnMonitorLogConfigCommonBatchIdGetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_BATCH_ID, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BATCH_ID = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnnMonitorLogConfigCommonServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(EnnMonitorLogConfigCommonServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BATCH_ID:
          serviceImpl.getBatchId((enn.monitor.log.config.common.parameters.EnnMonitorLogConfigCommonBatchIdGetRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.common.parameters.EnnMonitorLogConfigBatchIdGetResponse>) responseObserver);
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
        METHOD_GET_BATCH_ID);
  }

}
