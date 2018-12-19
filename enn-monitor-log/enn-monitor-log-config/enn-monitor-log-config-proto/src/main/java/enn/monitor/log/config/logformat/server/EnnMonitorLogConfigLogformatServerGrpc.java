package enn.monitor.log.config.logformat.server;

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
    comments = "Source: protobuf/logformatServer.proto")
public class EnnMonitorLogConfigLogformatServerGrpc {

  private EnnMonitorLogConfigLogformatServerGrpc() {}

  public static final String SERVICE_NAME = "EnnMonitorLogConfigLogformatServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetRequest,
      enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetResponse> METHOD_GET_LOGFORMAT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigLogformatServer", "GetLogformat"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateRequest,
      enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateResponse> METHOD_CREATE_LOGFORMAT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigLogformatServer", "CreateLogformat"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateRequest,
      enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateResponse> METHOD_UPDATE_LOGFORMAT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigLogformatServer", "UpdateLogformat"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteRequest,
      enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteResponse> METHOD_DELETE_LOGFORMAT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigLogformatServer", "DeleteLogformat"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedRequest,
      enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedResponse> METHOD_GET_MAX_DELETED_SEQ_NO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigLogformatServer", "getMaxDeletedSeqNo"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListRequest,
      enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListResponse> METHOD_GET_VALID_DATA_LIST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigLogformatServer", "getValidDataList"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListRequest,
      enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListResponse> METHOD_GET_DELETED_DATA_LIST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigLogformatServer", "getDeletedDataList"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnnMonitorLogConfigLogformatServerStub newStub(io.grpc.Channel channel) {
    return new EnnMonitorLogConfigLogformatServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogConfigLogformatServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogConfigLogformatServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogConfigLogformatServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogConfigLogformatServerFutureStub(channel);
  }

  /**
   */
  public static abstract class EnnMonitorLogConfigLogformatServerImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * get logformat
     * </pre>
     */
    public void getLogformat(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_LOGFORMAT, responseObserver);
    }

    /**
     * <pre>
     * create logformat
     * </pre>
     */
    public void createLogformat(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_LOGFORMAT, responseObserver);
    }

    /**
     * <pre>
     * update logformat
     * </pre>
     */
    public void updateLogformat(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE_LOGFORMAT, responseObserver);
    }

    /**
     * <pre>
     * delete logformat
     * </pre>
     */
    public void deleteLogformat(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE_LOGFORMAT, responseObserver);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public void getMaxDeletedSeqNo(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_MAX_DELETED_SEQ_NO, responseObserver);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public void getValidDataList(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_VALID_DATA_LIST, responseObserver);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public void getDeletedDataList(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_DELETED_DATA_LIST, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_LOGFORMAT,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetRequest,
                enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetResponse>(
                  this, METHODID_GET_LOGFORMAT)))
          .addMethod(
            METHOD_CREATE_LOGFORMAT,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateRequest,
                enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateResponse>(
                  this, METHODID_CREATE_LOGFORMAT)))
          .addMethod(
            METHOD_UPDATE_LOGFORMAT,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateRequest,
                enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateResponse>(
                  this, METHODID_UPDATE_LOGFORMAT)))
          .addMethod(
            METHOD_DELETE_LOGFORMAT,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteRequest,
                enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteResponse>(
                  this, METHODID_DELETE_LOGFORMAT)))
          .addMethod(
            METHOD_GET_MAX_DELETED_SEQ_NO,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedRequest,
                enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedResponse>(
                  this, METHODID_GET_MAX_DELETED_SEQ_NO)))
          .addMethod(
            METHOD_GET_VALID_DATA_LIST,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListRequest,
                enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListResponse>(
                  this, METHODID_GET_VALID_DATA_LIST)))
          .addMethod(
            METHOD_GET_DELETED_DATA_LIST,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListRequest,
                enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListResponse>(
                  this, METHODID_GET_DELETED_DATA_LIST)))
          .build();
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigLogformatServerStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigLogformatServerStub> {
    private EnnMonitorLogConfigLogformatServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigLogformatServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigLogformatServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigLogformatServerStub(channel, callOptions);
    }

    /**
     * <pre>
     * get logformat
     * </pre>
     */
    public void getLogformat(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_LOGFORMAT, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * create logformat
     * </pre>
     */
    public void createLogformat(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_LOGFORMAT, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * update logformat
     * </pre>
     */
    public void updateLogformat(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_LOGFORMAT, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * delete logformat
     * </pre>
     */
    public void deleteLogformat(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_LOGFORMAT, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public void getMaxDeletedSeqNo(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public void getValidDataList(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_VALID_DATA_LIST, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public void getDeletedDataList(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_DELETED_DATA_LIST, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigLogformatServerBlockingStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigLogformatServerBlockingStub> {
    private EnnMonitorLogConfigLogformatServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigLogformatServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigLogformatServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigLogformatServerBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * get logformat
     * </pre>
     */
    public enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetResponse getLogformat(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_LOGFORMAT, getCallOptions(), request);
    }

    /**
     * <pre>
     * create logformat
     * </pre>
     */
    public enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateResponse createLogformat(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_LOGFORMAT, getCallOptions(), request);
    }

    /**
     * <pre>
     * update logformat
     * </pre>
     */
    public enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateResponse updateLogformat(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE_LOGFORMAT, getCallOptions(), request);
    }

    /**
     * <pre>
     * delete logformat
     * </pre>
     */
    public enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteResponse deleteLogformat(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELETE_LOGFORMAT, getCallOptions(), request);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedResponse getMaxDeletedSeqNo(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions(), request);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListResponse getValidDataList(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_VALID_DATA_LIST, getCallOptions(), request);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListResponse getDeletedDataList(enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_DELETED_DATA_LIST, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigLogformatServerFutureStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigLogformatServerFutureStub> {
    private EnnMonitorLogConfigLogformatServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigLogformatServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigLogformatServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigLogformatServerFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * get logformat
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetResponse> getLogformat(
        enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_LOGFORMAT, getCallOptions()), request);
    }

    /**
     * <pre>
     * create logformat
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateResponse> createLogformat(
        enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_LOGFORMAT, getCallOptions()), request);
    }

    /**
     * <pre>
     * update logformat
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateResponse> updateLogformat(
        enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_LOGFORMAT, getCallOptions()), request);
    }

    /**
     * <pre>
     * delete logformat
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteResponse> deleteLogformat(
        enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_LOGFORMAT, getCallOptions()), request);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedResponse> getMaxDeletedSeqNo(
        enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions()), request);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListResponse> getValidDataList(
        enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_VALID_DATA_LIST, getCallOptions()), request);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListResponse> getDeletedDataList(
        enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_DELETED_DATA_LIST, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_LOGFORMAT = 0;
  private static final int METHODID_CREATE_LOGFORMAT = 1;
  private static final int METHODID_UPDATE_LOGFORMAT = 2;
  private static final int METHODID_DELETE_LOGFORMAT = 3;
  private static final int METHODID_GET_MAX_DELETED_SEQ_NO = 4;
  private static final int METHODID_GET_VALID_DATA_LIST = 5;
  private static final int METHODID_GET_DELETED_DATA_LIST = 6;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnnMonitorLogConfigLogformatServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(EnnMonitorLogConfigLogformatServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_LOGFORMAT:
          serviceImpl.getLogformat((enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetResponse>) responseObserver);
          break;
        case METHODID_CREATE_LOGFORMAT:
          serviceImpl.createLogformat((enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateResponse>) responseObserver);
          break;
        case METHODID_UPDATE_LOGFORMAT:
          serviceImpl.updateLogformat((enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateResponse>) responseObserver);
          break;
        case METHODID_DELETE_LOGFORMAT:
          serviceImpl.deleteLogformat((enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteResponse>) responseObserver);
          break;
        case METHODID_GET_MAX_DELETED_SEQ_NO:
          serviceImpl.getMaxDeletedSeqNo((enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedResponse>) responseObserver);
          break;
        case METHODID_GET_VALID_DATA_LIST:
          serviceImpl.getValidDataList((enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListResponse>) responseObserver);
          break;
        case METHODID_GET_DELETED_DATA_LIST:
          serviceImpl.getDeletedDataList((enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListResponse>) responseObserver);
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
        METHOD_GET_LOGFORMAT,
        METHOD_CREATE_LOGFORMAT,
        METHOD_UPDATE_LOGFORMAT,
        METHOD_DELETE_LOGFORMAT,
        METHOD_GET_MAX_DELETED_SEQ_NO,
        METHOD_GET_VALID_DATA_LIST,
        METHOD_GET_DELETED_DATA_LIST);
  }

}
