package enn.monitor.log.config.event.server;

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
    comments = "Source: protobuf/logEventServer.proto")
public class EnnMonitorLogConfigEventServerGrpc {

  private EnnMonitorLogConfigEventServerGrpc() {}

  public static final String SERVICE_NAME = "EnnMonitorLogConfigEventServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetRequest,
      enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetResponse> METHOD_GET_EVENT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigEventServer", "GetEvent"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateRequest,
      enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateResponse> METHOD_CREATE_EVENT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigEventServer", "CreateEvent"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateRequest,
      enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateResponse> METHOD_UPDATE_EVENT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigEventServer", "UpdateEvent"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteRequest,
      enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteResponse> METHOD_DELETE_EVENT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigEventServer", "DeleteEvent"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedRequest,
      enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedResponse> METHOD_GET_MAX_DELETED_SEQ_NO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigEventServer", "getMaxDeletedSeqNo"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListRequest,
      enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListResponse> METHOD_GET_VALID_DATA_LIST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigEventServer", "getValidDataList"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListRequest,
      enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListResponse> METHOD_GET_DELETED_DATA_LIST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigEventServer", "getDeletedDataList"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnnMonitorLogConfigEventServerStub newStub(io.grpc.Channel channel) {
    return new EnnMonitorLogConfigEventServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogConfigEventServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogConfigEventServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogConfigEventServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogConfigEventServerFutureStub(channel);
  }

  /**
   */
  public static abstract class EnnMonitorLogConfigEventServerImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * get Event
     * </pre>
     */
    public void getEvent(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_EVENT, responseObserver);
    }

    /**
     * <pre>
     * create Event
     * </pre>
     */
    public void createEvent(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_EVENT, responseObserver);
    }

    /**
     * <pre>
     * update Event
     * </pre>
     */
    public void updateEvent(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE_EVENT, responseObserver);
    }

    /**
     * <pre>
     * delete Event
     * </pre>
     */
    public void deleteEvent(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE_EVENT, responseObserver);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public void getMaxDeletedSeqNo(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_MAX_DELETED_SEQ_NO, responseObserver);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public void getValidDataList(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_VALID_DATA_LIST, responseObserver);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public void getDeletedDataList(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_DELETED_DATA_LIST, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_EVENT,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetRequest,
                enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetResponse>(
                  this, METHODID_GET_EVENT)))
          .addMethod(
            METHOD_CREATE_EVENT,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateRequest,
                enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateResponse>(
                  this, METHODID_CREATE_EVENT)))
          .addMethod(
            METHOD_UPDATE_EVENT,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateRequest,
                enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateResponse>(
                  this, METHODID_UPDATE_EVENT)))
          .addMethod(
            METHOD_DELETE_EVENT,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteRequest,
                enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteResponse>(
                  this, METHODID_DELETE_EVENT)))
          .addMethod(
            METHOD_GET_MAX_DELETED_SEQ_NO,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedRequest,
                enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedResponse>(
                  this, METHODID_GET_MAX_DELETED_SEQ_NO)))
          .addMethod(
            METHOD_GET_VALID_DATA_LIST,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListRequest,
                enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListResponse>(
                  this, METHODID_GET_VALID_DATA_LIST)))
          .addMethod(
            METHOD_GET_DELETED_DATA_LIST,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListRequest,
                enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListResponse>(
                  this, METHODID_GET_DELETED_DATA_LIST)))
          .build();
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigEventServerStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigEventServerStub> {
    private EnnMonitorLogConfigEventServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigEventServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigEventServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigEventServerStub(channel, callOptions);
    }

    /**
     * <pre>
     * get Event
     * </pre>
     */
    public void getEvent(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_EVENT, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * create Event
     * </pre>
     */
    public void createEvent(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_EVENT, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * update Event
     * </pre>
     */
    public void updateEvent(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_EVENT, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * delete Event
     * </pre>
     */
    public void deleteEvent(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_EVENT, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public void getMaxDeletedSeqNo(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public void getValidDataList(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_VALID_DATA_LIST, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public void getDeletedDataList(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_DELETED_DATA_LIST, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigEventServerBlockingStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigEventServerBlockingStub> {
    private EnnMonitorLogConfigEventServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigEventServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigEventServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigEventServerBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * get Event
     * </pre>
     */
    public enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetResponse getEvent(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_EVENT, getCallOptions(), request);
    }

    /**
     * <pre>
     * create Event
     * </pre>
     */
    public enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateResponse createEvent(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_EVENT, getCallOptions(), request);
    }

    /**
     * <pre>
     * update Event
     * </pre>
     */
    public enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateResponse updateEvent(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE_EVENT, getCallOptions(), request);
    }

    /**
     * <pre>
     * delete Event
     * </pre>
     */
    public enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteResponse deleteEvent(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELETE_EVENT, getCallOptions(), request);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedResponse getMaxDeletedSeqNo(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions(), request);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListResponse getValidDataList(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_VALID_DATA_LIST, getCallOptions(), request);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListResponse getDeletedDataList(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_DELETED_DATA_LIST, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigEventServerFutureStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigEventServerFutureStub> {
    private EnnMonitorLogConfigEventServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigEventServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigEventServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigEventServerFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * get Event
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetResponse> getEvent(
        enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_EVENT, getCallOptions()), request);
    }

    /**
     * <pre>
     * create Event
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateResponse> createEvent(
        enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_EVENT, getCallOptions()), request);
    }

    /**
     * <pre>
     * update Event
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateResponse> updateEvent(
        enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_EVENT, getCallOptions()), request);
    }

    /**
     * <pre>
     * delete Event
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteResponse> deleteEvent(
        enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_EVENT, getCallOptions()), request);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedResponse> getMaxDeletedSeqNo(
        enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions()), request);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListResponse> getValidDataList(
        enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_VALID_DATA_LIST, getCallOptions()), request);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListResponse> getDeletedDataList(
        enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_DELETED_DATA_LIST, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_EVENT = 0;
  private static final int METHODID_CREATE_EVENT = 1;
  private static final int METHODID_UPDATE_EVENT = 2;
  private static final int METHODID_DELETE_EVENT = 3;
  private static final int METHODID_GET_MAX_DELETED_SEQ_NO = 4;
  private static final int METHODID_GET_VALID_DATA_LIST = 5;
  private static final int METHODID_GET_DELETED_DATA_LIST = 6;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnnMonitorLogConfigEventServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(EnnMonitorLogConfigEventServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_EVENT:
          serviceImpl.getEvent((enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetResponse>) responseObserver);
          break;
        case METHODID_CREATE_EVENT:
          serviceImpl.createEvent((enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateResponse>) responseObserver);
          break;
        case METHODID_UPDATE_EVENT:
          serviceImpl.updateEvent((enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateResponse>) responseObserver);
          break;
        case METHODID_DELETE_EVENT:
          serviceImpl.deleteEvent((enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteResponse>) responseObserver);
          break;
        case METHODID_GET_MAX_DELETED_SEQ_NO:
          serviceImpl.getMaxDeletedSeqNo((enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedResponse>) responseObserver);
          break;
        case METHODID_GET_VALID_DATA_LIST:
          serviceImpl.getValidDataList((enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListResponse>) responseObserver);
          break;
        case METHODID_GET_DELETED_DATA_LIST:
          serviceImpl.getDeletedDataList((enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListResponse>) responseObserver);
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
        METHOD_GET_EVENT,
        METHOD_CREATE_EVENT,
        METHOD_UPDATE_EVENT,
        METHOD_DELETE_EVENT,
        METHOD_GET_MAX_DELETED_SEQ_NO,
        METHOD_GET_VALID_DATA_LIST,
        METHOD_GET_DELETED_DATA_LIST);
  }

}
