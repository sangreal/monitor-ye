package enn.monitor.log.config.template.server;

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
    comments = "Source: protobuf/logTemplateServer.proto")
public class EnnMonitorLogConfigTemplateServerGrpc {

  private EnnMonitorLogConfigTemplateServerGrpc() {}

  public static final String SERVICE_NAME = "EnnMonitorLogConfigTemplateServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetRequest,
      enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetResponse> METHOD_GET_TEMPLATE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigTemplateServer", "GetTemplate"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateRequest,
      enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateResponse> METHOD_CREATE_TEMPLATE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigTemplateServer", "CreateTemplate"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateRequest,
      enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateResponse> METHOD_UPDATE_TEMPLATE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigTemplateServer", "UpdateTemplate"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteRequest,
      enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteResponse> METHOD_DELETE_TEMPLATE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigTemplateServer", "DeleteTemplate"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedRequest,
      enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedResponse> METHOD_GET_MAX_DELETED_SEQ_NO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigTemplateServer", "getMaxDeletedSeqNo"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListRequest,
      enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListResponse> METHOD_GET_VALID_DATA_LIST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigTemplateServer", "getValidDataList"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListRequest,
      enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListResponse> METHOD_GET_DELETED_DATA_LIST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigTemplateServer", "getDeletedDataList"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnnMonitorLogConfigTemplateServerStub newStub(io.grpc.Channel channel) {
    return new EnnMonitorLogConfigTemplateServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogConfigTemplateServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogConfigTemplateServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogConfigTemplateServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogConfigTemplateServerFutureStub(channel);
  }

  /**
   */
  public static abstract class EnnMonitorLogConfigTemplateServerImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * get Template
     * </pre>
     */
    public void getTemplate(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_TEMPLATE, responseObserver);
    }

    /**
     * <pre>
     * create Template
     * </pre>
     */
    public void createTemplate(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_TEMPLATE, responseObserver);
    }

    /**
     * <pre>
     * update Template
     * </pre>
     */
    public void updateTemplate(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE_TEMPLATE, responseObserver);
    }

    /**
     * <pre>
     * delete Template
     * </pre>
     */
    public void deleteTemplate(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE_TEMPLATE, responseObserver);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public void getMaxDeletedSeqNo(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_MAX_DELETED_SEQ_NO, responseObserver);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public void getValidDataList(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_VALID_DATA_LIST, responseObserver);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public void getDeletedDataList(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_DELETED_DATA_LIST, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_TEMPLATE,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetRequest,
                enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetResponse>(
                  this, METHODID_GET_TEMPLATE)))
          .addMethod(
            METHOD_CREATE_TEMPLATE,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateRequest,
                enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateResponse>(
                  this, METHODID_CREATE_TEMPLATE)))
          .addMethod(
            METHOD_UPDATE_TEMPLATE,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateRequest,
                enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateResponse>(
                  this, METHODID_UPDATE_TEMPLATE)))
          .addMethod(
            METHOD_DELETE_TEMPLATE,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteRequest,
                enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteResponse>(
                  this, METHODID_DELETE_TEMPLATE)))
          .addMethod(
            METHOD_GET_MAX_DELETED_SEQ_NO,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedRequest,
                enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedResponse>(
                  this, METHODID_GET_MAX_DELETED_SEQ_NO)))
          .addMethod(
            METHOD_GET_VALID_DATA_LIST,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListRequest,
                enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListResponse>(
                  this, METHODID_GET_VALID_DATA_LIST)))
          .addMethod(
            METHOD_GET_DELETED_DATA_LIST,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListRequest,
                enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListResponse>(
                  this, METHODID_GET_DELETED_DATA_LIST)))
          .build();
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigTemplateServerStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigTemplateServerStub> {
    private EnnMonitorLogConfigTemplateServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigTemplateServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigTemplateServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigTemplateServerStub(channel, callOptions);
    }

    /**
     * <pre>
     * get Template
     * </pre>
     */
    public void getTemplate(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_TEMPLATE, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * create Template
     * </pre>
     */
    public void createTemplate(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_TEMPLATE, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * update Template
     * </pre>
     */
    public void updateTemplate(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_TEMPLATE, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * delete Template
     * </pre>
     */
    public void deleteTemplate(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_TEMPLATE, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public void getMaxDeletedSeqNo(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public void getValidDataList(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_VALID_DATA_LIST, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public void getDeletedDataList(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_DELETED_DATA_LIST, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigTemplateServerBlockingStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigTemplateServerBlockingStub> {
    private EnnMonitorLogConfigTemplateServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigTemplateServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigTemplateServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigTemplateServerBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * get Template
     * </pre>
     */
    public enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetResponse getTemplate(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_TEMPLATE, getCallOptions(), request);
    }

    /**
     * <pre>
     * create Template
     * </pre>
     */
    public enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateResponse createTemplate(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_TEMPLATE, getCallOptions(), request);
    }

    /**
     * <pre>
     * update Template
     * </pre>
     */
    public enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateResponse updateTemplate(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE_TEMPLATE, getCallOptions(), request);
    }

    /**
     * <pre>
     * delete Template
     * </pre>
     */
    public enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteResponse deleteTemplate(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELETE_TEMPLATE, getCallOptions(), request);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedResponse getMaxDeletedSeqNo(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions(), request);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListResponse getValidDataList(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_VALID_DATA_LIST, getCallOptions(), request);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListResponse getDeletedDataList(enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_DELETED_DATA_LIST, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigTemplateServerFutureStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigTemplateServerFutureStub> {
    private EnnMonitorLogConfigTemplateServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigTemplateServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigTemplateServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigTemplateServerFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * get Template
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetResponse> getTemplate(
        enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_TEMPLATE, getCallOptions()), request);
    }

    /**
     * <pre>
     * create Template
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateResponse> createTemplate(
        enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_TEMPLATE, getCallOptions()), request);
    }

    /**
     * <pre>
     * update Template
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateResponse> updateTemplate(
        enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_TEMPLATE, getCallOptions()), request);
    }

    /**
     * <pre>
     * delete Template
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteResponse> deleteTemplate(
        enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_TEMPLATE, getCallOptions()), request);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedResponse> getMaxDeletedSeqNo(
        enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions()), request);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListResponse> getValidDataList(
        enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_VALID_DATA_LIST, getCallOptions()), request);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListResponse> getDeletedDataList(
        enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_DELETED_DATA_LIST, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_TEMPLATE = 0;
  private static final int METHODID_CREATE_TEMPLATE = 1;
  private static final int METHODID_UPDATE_TEMPLATE = 2;
  private static final int METHODID_DELETE_TEMPLATE = 3;
  private static final int METHODID_GET_MAX_DELETED_SEQ_NO = 4;
  private static final int METHODID_GET_VALID_DATA_LIST = 5;
  private static final int METHODID_GET_DELETED_DATA_LIST = 6;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnnMonitorLogConfigTemplateServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(EnnMonitorLogConfigTemplateServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_TEMPLATE:
          serviceImpl.getTemplate((enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetResponse>) responseObserver);
          break;
        case METHODID_CREATE_TEMPLATE:
          serviceImpl.createTemplate((enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateResponse>) responseObserver);
          break;
        case METHODID_UPDATE_TEMPLATE:
          serviceImpl.updateTemplate((enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateResponse>) responseObserver);
          break;
        case METHODID_DELETE_TEMPLATE:
          serviceImpl.deleteTemplate((enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteResponse>) responseObserver);
          break;
        case METHODID_GET_MAX_DELETED_SEQ_NO:
          serviceImpl.getMaxDeletedSeqNo((enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedResponse>) responseObserver);
          break;
        case METHODID_GET_VALID_DATA_LIST:
          serviceImpl.getValidDataList((enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListResponse>) responseObserver);
          break;
        case METHODID_GET_DELETED_DATA_LIST:
          serviceImpl.getDeletedDataList((enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListResponse>) responseObserver);
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
        METHOD_GET_TEMPLATE,
        METHOD_CREATE_TEMPLATE,
        METHOD_UPDATE_TEMPLATE,
        METHOD_DELETE_TEMPLATE,
        METHOD_GET_MAX_DELETED_SEQ_NO,
        METHOD_GET_VALID_DATA_LIST,
        METHOD_GET_DELETED_DATA_LIST);
  }

}
