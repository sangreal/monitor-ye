package enn.monitor.log.analyse.storage.server;

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
    comments = "Source: protobuf/storageServer.proto")
public class EnnMonitorLogAnalyseStorageServerGrpc {

  private EnnMonitorLogAnalyseStorageServerGrpc() {}

  public static final String SERVICE_NAME = "EnnMonitorLogAnalyseStorageServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchRequest,
      enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchResponse> METHOD_SEARCH_NN =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogAnalyseStorageServer", "searchNN"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateRequest,
      enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateResponse> METHOD_CREATE_NN =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogAnalyseStorageServer", "createNN"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataRequest,
      enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataResponse> METHOD_GET_LASTEST_NNDATA =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogAnalyseStorageServer", "getLastestNNData"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnnMonitorLogAnalyseStorageServerStub newStub(io.grpc.Channel channel) {
    return new EnnMonitorLogAnalyseStorageServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogAnalyseStorageServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogAnalyseStorageServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogAnalyseStorageServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogAnalyseStorageServerFutureStub(channel);
  }

  /**
   */
  public static abstract class EnnMonitorLogAnalyseStorageServerImplBase implements io.grpc.BindableService {

    /**
     */
    public void searchNN(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SEARCH_NN, responseObserver);
    }

    /**
     */
    public void createNN(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_NN, responseObserver);
    }

    /**
     */
    public void getLastestNNData(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_LASTEST_NNDATA, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_SEARCH_NN,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchRequest,
                enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchResponse>(
                  this, METHODID_SEARCH_NN)))
          .addMethod(
            METHOD_CREATE_NN,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateRequest,
                enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateResponse>(
                  this, METHODID_CREATE_NN)))
          .addMethod(
            METHOD_GET_LASTEST_NNDATA,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataRequest,
                enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataResponse>(
                  this, METHODID_GET_LASTEST_NNDATA)))
          .build();
    }
  }

  /**
   */
  public static final class EnnMonitorLogAnalyseStorageServerStub extends io.grpc.stub.AbstractStub<EnnMonitorLogAnalyseStorageServerStub> {
    private EnnMonitorLogAnalyseStorageServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogAnalyseStorageServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogAnalyseStorageServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogAnalyseStorageServerStub(channel, callOptions);
    }

    /**
     */
    public void searchNN(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SEARCH_NN, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createNN(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_NN, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getLastestNNData(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_LASTEST_NNDATA, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnnMonitorLogAnalyseStorageServerBlockingStub extends io.grpc.stub.AbstractStub<EnnMonitorLogAnalyseStorageServerBlockingStub> {
    private EnnMonitorLogAnalyseStorageServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogAnalyseStorageServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogAnalyseStorageServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogAnalyseStorageServerBlockingStub(channel, callOptions);
    }

    /**
     */
    public enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchResponse searchNN(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SEARCH_NN, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateResponse createNN(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_NN, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataResponse getLastestNNData(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_LASTEST_NNDATA, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnnMonitorLogAnalyseStorageServerFutureStub extends io.grpc.stub.AbstractStub<EnnMonitorLogAnalyseStorageServerFutureStub> {
    private EnnMonitorLogAnalyseStorageServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogAnalyseStorageServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogAnalyseStorageServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogAnalyseStorageServerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchResponse> searchNN(
        enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SEARCH_NN, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateResponse> createNN(
        enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_NN, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataResponse> getLastestNNData(
        enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_LASTEST_NNDATA, getCallOptions()), request);
    }
  }

  private static final int METHODID_SEARCH_NN = 0;
  private static final int METHODID_CREATE_NN = 1;
  private static final int METHODID_GET_LASTEST_NNDATA = 2;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnnMonitorLogAnalyseStorageServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(EnnMonitorLogAnalyseStorageServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEARCH_NN:
          serviceImpl.searchNN((enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchResponse>) responseObserver);
          break;
        case METHODID_CREATE_NN:
          serviceImpl.createNN((enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateResponse>) responseObserver);
          break;
        case METHODID_GET_LASTEST_NNDATA:
          serviceImpl.getLastestNNData((enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataResponse>) responseObserver);
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
        METHOD_SEARCH_NN,
        METHOD_CREATE_NN,
        METHOD_GET_LASTEST_NNDATA);
  }

}
