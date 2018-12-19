package enn.monitor.config.serviceLine.server;

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
    comments = "Source: protobuf/serviceLineServer.proto")
public class EnnMonitorConfigServiceLineServerGrpc {

  private EnnMonitorConfigServiceLineServerGrpc() {}

  public static final String SERVICE_NAME = "EnnMonitorConfigServiceLineServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetRequest,
      enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetResponse> METHOD_GET_SERVICE_LINE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceLineServer", "GetServiceLine"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateRequest,
      enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateResponse> METHOD_CREATE_SERVICE_LINE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceLineServer", "CreateServiceLine"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateRequest,
      enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateResponse> METHOD_UPDATE_SERVICE_LINE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceLineServer", "UpdateServiceLine"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteRequest,
      enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteResponse> METHOD_DELETE_SERVICE_LINE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceLineServer", "DeleteServiceLine"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedRequest,
      enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedResponse> METHOD_GET_MAX_DELETED_SEQ_NO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceLineServer", "getMaxDeletedSeqNo"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListRequest,
      enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListResponse> METHOD_GET_VALID_DATA_LIST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceLineServer", "getValidDataList"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListRequest,
      enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListResponse> METHOD_GET_DELETED_DATA_LIST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceLineServer", "getDeletedDataList"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotRequest,
      enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotResponse> METHOD_CHECK_SERVICE_LINE_IS_EXISTED_OR_NOT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceLineServer", "checkServiceLineIsExistedOrNot"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnnMonitorConfigServiceLineServerStub newStub(io.grpc.Channel channel) {
    return new EnnMonitorConfigServiceLineServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorConfigServiceLineServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnnMonitorConfigServiceLineServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorConfigServiceLineServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnnMonitorConfigServiceLineServerFutureStub(channel);
  }

  /**
   */
  public static abstract class EnnMonitorConfigServiceLineServerImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * get service
     * </pre>
     */
    public void getServiceLine(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_SERVICE_LINE, responseObserver);
    }

    /**
     * <pre>
     * create service
     * </pre>
     */
    public void createServiceLine(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_SERVICE_LINE, responseObserver);
    }

    /**
     * <pre>
     * update service
     * </pre>
     */
    public void updateServiceLine(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE_SERVICE_LINE, responseObserver);
    }

    /**
     * <pre>
     * delete serviceLine
     * </pre>
     */
    public void deleteServiceLine(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE_SERVICE_LINE, responseObserver);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public void getMaxDeletedSeqNo(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_MAX_DELETED_SEQ_NO, responseObserver);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public void getValidDataList(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_VALID_DATA_LIST, responseObserver);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public void getDeletedDataList(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_DELETED_DATA_LIST, responseObserver);
    }

    /**
     * <pre>
     * check whether or not the serviceLine has existed
     * </pre>
     */
    public void checkServiceLineIsExistedOrNot(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CHECK_SERVICE_LINE_IS_EXISTED_OR_NOT, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_SERVICE_LINE,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetRequest,
                enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetResponse>(
                  this, METHODID_GET_SERVICE_LINE)))
          .addMethod(
            METHOD_CREATE_SERVICE_LINE,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateRequest,
                enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateResponse>(
                  this, METHODID_CREATE_SERVICE_LINE)))
          .addMethod(
            METHOD_UPDATE_SERVICE_LINE,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateRequest,
                enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateResponse>(
                  this, METHODID_UPDATE_SERVICE_LINE)))
          .addMethod(
            METHOD_DELETE_SERVICE_LINE,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteRequest,
                enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteResponse>(
                  this, METHODID_DELETE_SERVICE_LINE)))
          .addMethod(
            METHOD_GET_MAX_DELETED_SEQ_NO,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedRequest,
                enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedResponse>(
                  this, METHODID_GET_MAX_DELETED_SEQ_NO)))
          .addMethod(
            METHOD_GET_VALID_DATA_LIST,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListRequest,
                enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListResponse>(
                  this, METHODID_GET_VALID_DATA_LIST)))
          .addMethod(
            METHOD_GET_DELETED_DATA_LIST,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListRequest,
                enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListResponse>(
                  this, METHODID_GET_DELETED_DATA_LIST)))
          .addMethod(
            METHOD_CHECK_SERVICE_LINE_IS_EXISTED_OR_NOT,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotRequest,
                enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotResponse>(
                  this, METHODID_CHECK_SERVICE_LINE_IS_EXISTED_OR_NOT)))
          .build();
    }
  }

  /**
   */
  public static final class EnnMonitorConfigServiceLineServerStub extends io.grpc.stub.AbstractStub<EnnMonitorConfigServiceLineServerStub> {
    private EnnMonitorConfigServiceLineServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorConfigServiceLineServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorConfigServiceLineServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorConfigServiceLineServerStub(channel, callOptions);
    }

    /**
     * <pre>
     * get service
     * </pre>
     */
    public void getServiceLine(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_SERVICE_LINE, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * create service
     * </pre>
     */
    public void createServiceLine(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_SERVICE_LINE, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * update service
     * </pre>
     */
    public void updateServiceLine(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_SERVICE_LINE, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * delete serviceLine
     * </pre>
     */
    public void deleteServiceLine(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_SERVICE_LINE, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public void getMaxDeletedSeqNo(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public void getValidDataList(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_VALID_DATA_LIST, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public void getDeletedDataList(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_DELETED_DATA_LIST, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * check whether or not the serviceLine has existed
     * </pre>
     */
    public void checkServiceLineIsExistedOrNot(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CHECK_SERVICE_LINE_IS_EXISTED_OR_NOT, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnnMonitorConfigServiceLineServerBlockingStub extends io.grpc.stub.AbstractStub<EnnMonitorConfigServiceLineServerBlockingStub> {
    private EnnMonitorConfigServiceLineServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorConfigServiceLineServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorConfigServiceLineServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorConfigServiceLineServerBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * get service
     * </pre>
     */
    public enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetResponse getServiceLine(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_SERVICE_LINE, getCallOptions(), request);
    }

    /**
     * <pre>
     * create service
     * </pre>
     */
    public enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateResponse createServiceLine(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_SERVICE_LINE, getCallOptions(), request);
    }

    /**
     * <pre>
     * update service
     * </pre>
     */
    public enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateResponse updateServiceLine(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE_SERVICE_LINE, getCallOptions(), request);
    }

    /**
     * <pre>
     * delete serviceLine
     * </pre>
     */
    public enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteResponse deleteServiceLine(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELETE_SERVICE_LINE, getCallOptions(), request);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedResponse getMaxDeletedSeqNo(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions(), request);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListResponse getValidDataList(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_VALID_DATA_LIST, getCallOptions(), request);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListResponse getDeletedDataList(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_DELETED_DATA_LIST, getCallOptions(), request);
    }

    /**
     * <pre>
     * check whether or not the serviceLine has existed
     * </pre>
     */
    public enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotResponse checkServiceLineIsExistedOrNot(enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CHECK_SERVICE_LINE_IS_EXISTED_OR_NOT, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnnMonitorConfigServiceLineServerFutureStub extends io.grpc.stub.AbstractStub<EnnMonitorConfigServiceLineServerFutureStub> {
    private EnnMonitorConfigServiceLineServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorConfigServiceLineServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorConfigServiceLineServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorConfigServiceLineServerFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * get service
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetResponse> getServiceLine(
        enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_SERVICE_LINE, getCallOptions()), request);
    }

    /**
     * <pre>
     * create service
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateResponse> createServiceLine(
        enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_SERVICE_LINE, getCallOptions()), request);
    }

    /**
     * <pre>
     * update service
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateResponse> updateServiceLine(
        enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_SERVICE_LINE, getCallOptions()), request);
    }

    /**
     * <pre>
     * delete serviceLine
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteResponse> deleteServiceLine(
        enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_SERVICE_LINE, getCallOptions()), request);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedResponse> getMaxDeletedSeqNo(
        enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions()), request);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListResponse> getValidDataList(
        enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_VALID_DATA_LIST, getCallOptions()), request);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListResponse> getDeletedDataList(
        enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_DELETED_DATA_LIST, getCallOptions()), request);
    }

    /**
     * <pre>
     * check whether or not the serviceLine has existed
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotResponse> checkServiceLineIsExistedOrNot(
        enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CHECK_SERVICE_LINE_IS_EXISTED_OR_NOT, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SERVICE_LINE = 0;
  private static final int METHODID_CREATE_SERVICE_LINE = 1;
  private static final int METHODID_UPDATE_SERVICE_LINE = 2;
  private static final int METHODID_DELETE_SERVICE_LINE = 3;
  private static final int METHODID_GET_MAX_DELETED_SEQ_NO = 4;
  private static final int METHODID_GET_VALID_DATA_LIST = 5;
  private static final int METHODID_GET_DELETED_DATA_LIST = 6;
  private static final int METHODID_CHECK_SERVICE_LINE_IS_EXISTED_OR_NOT = 7;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnnMonitorConfigServiceLineServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(EnnMonitorConfigServiceLineServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SERVICE_LINE:
          serviceImpl.getServiceLine((enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetResponse>) responseObserver);
          break;
        case METHODID_CREATE_SERVICE_LINE:
          serviceImpl.createServiceLine((enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateResponse>) responseObserver);
          break;
        case METHODID_UPDATE_SERVICE_LINE:
          serviceImpl.updateServiceLine((enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateResponse>) responseObserver);
          break;
        case METHODID_DELETE_SERVICE_LINE:
          serviceImpl.deleteServiceLine((enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteResponse>) responseObserver);
          break;
        case METHODID_GET_MAX_DELETED_SEQ_NO:
          serviceImpl.getMaxDeletedSeqNo((enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedResponse>) responseObserver);
          break;
        case METHODID_GET_VALID_DATA_LIST:
          serviceImpl.getValidDataList((enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListResponse>) responseObserver);
          break;
        case METHODID_GET_DELETED_DATA_LIST:
          serviceImpl.getDeletedDataList((enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListResponse>) responseObserver);
          break;
        case METHODID_CHECK_SERVICE_LINE_IS_EXISTED_OR_NOT:
          serviceImpl.checkServiceLineIsExistedOrNot((enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotResponse>) responseObserver);
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
        METHOD_GET_SERVICE_LINE,
        METHOD_CREATE_SERVICE_LINE,
        METHOD_UPDATE_SERVICE_LINE,
        METHOD_DELETE_SERVICE_LINE,
        METHOD_GET_MAX_DELETED_SEQ_NO,
        METHOD_GET_VALID_DATA_LIST,
        METHOD_GET_DELETED_DATA_LIST,
        METHOD_CHECK_SERVICE_LINE_IS_EXISTED_OR_NOT);
  }

}
