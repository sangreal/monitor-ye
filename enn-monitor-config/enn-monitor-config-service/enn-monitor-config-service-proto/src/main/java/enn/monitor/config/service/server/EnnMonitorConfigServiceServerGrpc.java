package enn.monitor.config.service.server;

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
    comments = "Source: protobuf/serviceServer.proto")
public class EnnMonitorConfigServiceServerGrpc {

  private EnnMonitorConfigServiceServerGrpc() {}

  public static final String SERVICE_NAME = "EnnMonitorConfigServiceServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest,
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetResponse> METHOD_GET_SERVICE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceServer", "GetService"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest,
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceCountResponse> METHOD_COUNT_SERVICE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceServer", "CountService"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceCountResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateRequest,
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateResponse> METHOD_CREATE_SERVICE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceServer", "CreateService"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateRequest,
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateResponse> METHOD_UPDATE_SERVICE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceServer", "UpdateService"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteRequest,
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteResponse> METHOD_DELETE_SERVICE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceServer", "DeleteService"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorRequest,
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorResponse> METHOD_GENERATE_SERVICE_TOKEN =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceServer", "GenerateServiceToken"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedRequest,
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedResponse> METHOD_GET_MAX_DELETED_SEQ_NO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceServer", "getMaxDeletedSeqNo"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListRequest,
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse> METHOD_GET_VALID_DATA_LIST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceServer", "getValidDataList"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListRequest,
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse> METHOD_GET_DELETED_DATA_LIST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceServer", "getDeletedDataList"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotRequest,
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotResponse> METHOD_CHECK_SERVICE_IS_EXISTED_OR_NOT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigServiceServer", "checkServiceIsExistedOrNot"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnnMonitorConfigServiceServerStub newStub(io.grpc.Channel channel) {
    return new EnnMonitorConfigServiceServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorConfigServiceServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnnMonitorConfigServiceServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorConfigServiceServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnnMonitorConfigServiceServerFutureStub(channel);
  }

  /**
   */
  public static abstract class EnnMonitorConfigServiceServerImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * get service
     * </pre>
     */
    public void getService(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_SERVICE, responseObserver);
    }

    /**
     * <pre>
     * count service
     * </pre>
     */
    public void countService(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceCountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_COUNT_SERVICE, responseObserver);
    }

    /**
     * <pre>
     * create service
     * </pre>
     */
    public void createService(enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_SERVICE, responseObserver);
    }

    /**
     * <pre>
     * update service
     * </pre>
     */
    public void updateService(enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE_SERVICE, responseObserver);
    }

    /**
     * <pre>
     * delete service
     * </pre>
     */
    public void deleteService(enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE_SERVICE, responseObserver);
    }

    /**
     * <pre>
     * generator token
     * </pre>
     */
    public void generateServiceToken(enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GENERATE_SERVICE_TOKEN, responseObserver);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public void getMaxDeletedSeqNo(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_MAX_DELETED_SEQ_NO, responseObserver);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public void getValidDataList(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_VALID_DATA_LIST, responseObserver);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public void getDeletedDataList(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_DELETED_DATA_LIST, responseObserver);
    }

    /**
     * <pre>
     * check whether or not the serviceName has existed
     * </pre>
     */
    public void checkServiceIsExistedOrNot(enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CHECK_SERVICE_IS_EXISTED_OR_NOT, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_SERVICE,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest,
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetResponse>(
                  this, METHODID_GET_SERVICE)))
          .addMethod(
            METHOD_COUNT_SERVICE,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest,
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceCountResponse>(
                  this, METHODID_COUNT_SERVICE)))
          .addMethod(
            METHOD_CREATE_SERVICE,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateRequest,
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateResponse>(
                  this, METHODID_CREATE_SERVICE)))
          .addMethod(
            METHOD_UPDATE_SERVICE,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateRequest,
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateResponse>(
                  this, METHODID_UPDATE_SERVICE)))
          .addMethod(
            METHOD_DELETE_SERVICE,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteRequest,
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteResponse>(
                  this, METHODID_DELETE_SERVICE)))
          .addMethod(
            METHOD_GENERATE_SERVICE_TOKEN,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorRequest,
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorResponse>(
                  this, METHODID_GENERATE_SERVICE_TOKEN)))
          .addMethod(
            METHOD_GET_MAX_DELETED_SEQ_NO,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedRequest,
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedResponse>(
                  this, METHODID_GET_MAX_DELETED_SEQ_NO)))
          .addMethod(
            METHOD_GET_VALID_DATA_LIST,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListRequest,
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse>(
                  this, METHODID_GET_VALID_DATA_LIST)))
          .addMethod(
            METHOD_GET_DELETED_DATA_LIST,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListRequest,
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse>(
                  this, METHODID_GET_DELETED_DATA_LIST)))
          .addMethod(
            METHOD_CHECK_SERVICE_IS_EXISTED_OR_NOT,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotRequest,
                enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotResponse>(
                  this, METHODID_CHECK_SERVICE_IS_EXISTED_OR_NOT)))
          .build();
    }
  }

  /**
   */
  public static final class EnnMonitorConfigServiceServerStub extends io.grpc.stub.AbstractStub<EnnMonitorConfigServiceServerStub> {
    private EnnMonitorConfigServiceServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorConfigServiceServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorConfigServiceServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorConfigServiceServerStub(channel, callOptions);
    }

    /**
     * <pre>
     * get service
     * </pre>
     */
    public void getService(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_SERVICE, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * count service
     * </pre>
     */
    public void countService(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceCountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_COUNT_SERVICE, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * create service
     * </pre>
     */
    public void createService(enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_SERVICE, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * update service
     * </pre>
     */
    public void updateService(enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_SERVICE, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * delete service
     * </pre>
     */
    public void deleteService(enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_SERVICE, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * generator token
     * </pre>
     */
    public void generateServiceToken(enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GENERATE_SERVICE_TOKEN, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public void getMaxDeletedSeqNo(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public void getValidDataList(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_VALID_DATA_LIST, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public void getDeletedDataList(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_DELETED_DATA_LIST, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * check whether or not the serviceName has existed
     * </pre>
     */
    public void checkServiceIsExistedOrNot(enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CHECK_SERVICE_IS_EXISTED_OR_NOT, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnnMonitorConfigServiceServerBlockingStub extends io.grpc.stub.AbstractStub<EnnMonitorConfigServiceServerBlockingStub> {
    private EnnMonitorConfigServiceServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorConfigServiceServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorConfigServiceServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorConfigServiceServerBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * get service
     * </pre>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetResponse getService(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_SERVICE, getCallOptions(), request);
    }

    /**
     * <pre>
     * count service
     * </pre>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceCountResponse countService(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_COUNT_SERVICE, getCallOptions(), request);
    }

    /**
     * <pre>
     * create service
     * </pre>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateResponse createService(enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_SERVICE, getCallOptions(), request);
    }

    /**
     * <pre>
     * update service
     * </pre>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateResponse updateService(enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE_SERVICE, getCallOptions(), request);
    }

    /**
     * <pre>
     * delete service
     * </pre>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteResponse deleteService(enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELETE_SERVICE, getCallOptions(), request);
    }

    /**
     * <pre>
     * generator token
     * </pre>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorResponse generateServiceToken(enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GENERATE_SERVICE_TOKEN, getCallOptions(), request);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedResponse getMaxDeletedSeqNo(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions(), request);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse getValidDataList(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_VALID_DATA_LIST, getCallOptions(), request);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse getDeletedDataList(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_DELETED_DATA_LIST, getCallOptions(), request);
    }

    /**
     * <pre>
     * check whether or not the serviceName has existed
     * </pre>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotResponse checkServiceIsExistedOrNot(enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CHECK_SERVICE_IS_EXISTED_OR_NOT, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnnMonitorConfigServiceServerFutureStub extends io.grpc.stub.AbstractStub<EnnMonitorConfigServiceServerFutureStub> {
    private EnnMonitorConfigServiceServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorConfigServiceServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorConfigServiceServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorConfigServiceServerFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * get service
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetResponse> getService(
        enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_SERVICE, getCallOptions()), request);
    }

    /**
     * <pre>
     * count service
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.service.parameters.EnnMonitorConfigServiceCountResponse> countService(
        enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_COUNT_SERVICE, getCallOptions()), request);
    }

    /**
     * <pre>
     * create service
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateResponse> createService(
        enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_SERVICE, getCallOptions()), request);
    }

    /**
     * <pre>
     * update service
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateResponse> updateService(
        enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_SERVICE, getCallOptions()), request);
    }

    /**
     * <pre>
     * delete service
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteResponse> deleteService(
        enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_SERVICE, getCallOptions()), request);
    }

    /**
     * <pre>
     * generator token
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorResponse> generateServiceToken(
        enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GENERATE_SERVICE_TOKEN, getCallOptions()), request);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedResponse> getMaxDeletedSeqNo(
        enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions()), request);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse> getValidDataList(
        enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_VALID_DATA_LIST, getCallOptions()), request);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse> getDeletedDataList(
        enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_DELETED_DATA_LIST, getCallOptions()), request);
    }

    /**
     * <pre>
     * check whether or not the serviceName has existed
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotResponse> checkServiceIsExistedOrNot(
        enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CHECK_SERVICE_IS_EXISTED_OR_NOT, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SERVICE = 0;
  private static final int METHODID_COUNT_SERVICE = 1;
  private static final int METHODID_CREATE_SERVICE = 2;
  private static final int METHODID_UPDATE_SERVICE = 3;
  private static final int METHODID_DELETE_SERVICE = 4;
  private static final int METHODID_GENERATE_SERVICE_TOKEN = 5;
  private static final int METHODID_GET_MAX_DELETED_SEQ_NO = 6;
  private static final int METHODID_GET_VALID_DATA_LIST = 7;
  private static final int METHODID_GET_DELETED_DATA_LIST = 8;
  private static final int METHODID_CHECK_SERVICE_IS_EXISTED_OR_NOT = 9;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnnMonitorConfigServiceServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(EnnMonitorConfigServiceServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SERVICE:
          serviceImpl.getService((enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetResponse>) responseObserver);
          break;
        case METHODID_COUNT_SERVICE:
          serviceImpl.countService((enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceCountResponse>) responseObserver);
          break;
        case METHODID_CREATE_SERVICE:
          serviceImpl.createService((enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateResponse>) responseObserver);
          break;
        case METHODID_UPDATE_SERVICE:
          serviceImpl.updateService((enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateResponse>) responseObserver);
          break;
        case METHODID_DELETE_SERVICE:
          serviceImpl.deleteService((enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteResponse>) responseObserver);
          break;
        case METHODID_GENERATE_SERVICE_TOKEN:
          serviceImpl.generateServiceToken((enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorResponse>) responseObserver);
          break;
        case METHODID_GET_MAX_DELETED_SEQ_NO:
          serviceImpl.getMaxDeletedSeqNo((enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedResponse>) responseObserver);
          break;
        case METHODID_GET_VALID_DATA_LIST:
          serviceImpl.getValidDataList((enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse>) responseObserver);
          break;
        case METHODID_GET_DELETED_DATA_LIST:
          serviceImpl.getDeletedDataList((enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse>) responseObserver);
          break;
        case METHODID_CHECK_SERVICE_IS_EXISTED_OR_NOT:
          serviceImpl.checkServiceIsExistedOrNot((enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotResponse>) responseObserver);
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
        METHOD_GET_SERVICE,
        METHOD_COUNT_SERVICE,
        METHOD_CREATE_SERVICE,
        METHOD_UPDATE_SERVICE,
        METHOD_DELETE_SERVICE,
        METHOD_GENERATE_SERVICE_TOKEN,
        METHOD_GET_MAX_DELETED_SEQ_NO,
        METHOD_GET_VALID_DATA_LIST,
        METHOD_GET_DELETED_DATA_LIST,
        METHOD_CHECK_SERVICE_IS_EXISTED_OR_NOT);
  }

}
