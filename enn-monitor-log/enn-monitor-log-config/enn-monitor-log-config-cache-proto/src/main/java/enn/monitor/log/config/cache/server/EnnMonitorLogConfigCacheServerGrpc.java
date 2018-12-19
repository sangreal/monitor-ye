package enn.monitor.log.config.cache.server;

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
    comments = "Source: protobuf/logConfigCacheServer.proto")
public class EnnMonitorLogConfigCacheServerGrpc {

  private EnnMonitorLogConfigCacheServerGrpc() {}

  public static final String SERVICE_NAME = "EnnMonitorLogConfigCacheServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheRequest,
      enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheResponse> METHOD_GET_TAG_BY_TEMPLATE_KEY =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigCacheServer", "getTagByTemplateKey"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdRequest,
      enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse> METHOD_GET_TAG_ID_BY_TAG_KEY =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigCacheServer", "getTagIdByTagKey"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnnMonitorLogConfigCacheServerStub newStub(io.grpc.Channel channel) {
    return new EnnMonitorLogConfigCacheServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogConfigCacheServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogConfigCacheServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogConfigCacheServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogConfigCacheServerFutureStub(channel);
  }

  /**
   */
  public static abstract class EnnMonitorLogConfigCacheServerImplBase implements io.grpc.BindableService {

    /**
     */
    public void getTagByTemplateKey(enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_TAG_BY_TEMPLATE_KEY, responseObserver);
    }

    /**
     */
    public void getTagIdByTagKey(enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_TAG_ID_BY_TAG_KEY, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_TAG_BY_TEMPLATE_KEY,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheRequest,
                enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheResponse>(
                  this, METHODID_GET_TAG_BY_TEMPLATE_KEY)))
          .addMethod(
            METHOD_GET_TAG_ID_BY_TAG_KEY,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdRequest,
                enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse>(
                  this, METHODID_GET_TAG_ID_BY_TAG_KEY)))
          .build();
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigCacheServerStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigCacheServerStub> {
    private EnnMonitorLogConfigCacheServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigCacheServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigCacheServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigCacheServerStub(channel, callOptions);
    }

    /**
     */
    public void getTagByTemplateKey(enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_TAG_BY_TEMPLATE_KEY, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTagIdByTagKey(enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_TAG_ID_BY_TAG_KEY, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigCacheServerBlockingStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigCacheServerBlockingStub> {
    private EnnMonitorLogConfigCacheServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigCacheServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigCacheServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigCacheServerBlockingStub(channel, callOptions);
    }

    /**
     */
    public enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheResponse getTagByTemplateKey(enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_TAG_BY_TEMPLATE_KEY, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse getTagIdByTagKey(enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_TAG_ID_BY_TAG_KEY, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigCacheServerFutureStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigCacheServerFutureStub> {
    private EnnMonitorLogConfigCacheServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigCacheServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigCacheServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigCacheServerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheResponse> getTagByTemplateKey(
        enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_TAG_BY_TEMPLATE_KEY, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse> getTagIdByTagKey(
        enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_TAG_ID_BY_TAG_KEY, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_TAG_BY_TEMPLATE_KEY = 0;
  private static final int METHODID_GET_TAG_ID_BY_TAG_KEY = 1;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnnMonitorLogConfigCacheServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(EnnMonitorLogConfigCacheServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_TAG_BY_TEMPLATE_KEY:
          serviceImpl.getTagByTemplateKey((enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheResponse>) responseObserver);
          break;
        case METHODID_GET_TAG_ID_BY_TAG_KEY:
          serviceImpl.getTagIdByTagKey((enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse>) responseObserver);
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
        METHOD_GET_TAG_BY_TEMPLATE_KEY,
        METHOD_GET_TAG_ID_BY_TAG_KEY);
  }

}
