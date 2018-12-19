package enn.monitor.config.cluster.cluster;

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
    comments = "Source: protobuf/cluster.proto")
public class EnnMonitorConfigClusterServerGrpc {

  private EnnMonitorConfigClusterServerGrpc() {}

  public static final String SERVICE_NAME = "EnnMonitorConfigClusterServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetRequest,
      enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetResponse> METHOD_GET_CLUSTER =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigClusterServer", "GetCluster"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateRequest,
      enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateResponse> METHOD_CREATE_CLUSTER =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigClusterServer", "CreateCluster"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateRequest,
      enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateResponse> METHOD_UPDATE_CLUSTER =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigClusterServer", "UpdateCluster"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteRequest,
      enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteResponse> METHOD_DELETE_CLUSTER =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigClusterServer", "DeleteCluster"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedRequest,
      enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedResponse> METHOD_GET_MAX_DELETED_SEQ_NO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigClusterServer", "getMaxDeletedSeqNo"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListRequest,
      enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListResponse> METHOD_GET_VALID_DATA_LIST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigClusterServer", "getValidDataList"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListRequest,
      enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListResponse> METHOD_GET_DELETED_DATA_LIST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigClusterServer", "getDeletedDataList"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotRequest,
      enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotResponse> METHOD_CHECK_CLUSTER_NAME_IS_EXISTED_OR_NOT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigClusterServer", "checkClusterNameIsExistedOrNot"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnnMonitorConfigClusterServerStub newStub(io.grpc.Channel channel) {
    return new EnnMonitorConfigClusterServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorConfigClusterServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnnMonitorConfigClusterServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorConfigClusterServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnnMonitorConfigClusterServerFutureStub(channel);
  }

  /**
   */
  public static abstract class EnnMonitorConfigClusterServerImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * get cluster
     * </pre>
     */
    public void getCluster(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_CLUSTER, responseObserver);
    }

    /**
     * <pre>
     * create cluster
     * </pre>
     */
    public void createCluster(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_CLUSTER, responseObserver);
    }

    /**
     * <pre>
     * update cluster
     * </pre>
     */
    public void updateCluster(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE_CLUSTER, responseObserver);
    }

    /**
     * <pre>
     * delete cluster
     * </pre>
     */
    public void deleteCluster(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE_CLUSTER, responseObserver);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public void getMaxDeletedSeqNo(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_MAX_DELETED_SEQ_NO, responseObserver);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public void getValidDataList(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_VALID_DATA_LIST, responseObserver);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public void getDeletedDataList(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_DELETED_DATA_LIST, responseObserver);
    }

    /**
     * <pre>
     * check whether or not the clusterName has existed
     * </pre>
     */
    public void checkClusterNameIsExistedOrNot(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CHECK_CLUSTER_NAME_IS_EXISTED_OR_NOT, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_CLUSTER,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetRequest,
                enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetResponse>(
                  this, METHODID_GET_CLUSTER)))
          .addMethod(
            METHOD_CREATE_CLUSTER,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateRequest,
                enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateResponse>(
                  this, METHODID_CREATE_CLUSTER)))
          .addMethod(
            METHOD_UPDATE_CLUSTER,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateRequest,
                enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateResponse>(
                  this, METHODID_UPDATE_CLUSTER)))
          .addMethod(
            METHOD_DELETE_CLUSTER,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteRequest,
                enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteResponse>(
                  this, METHODID_DELETE_CLUSTER)))
          .addMethod(
            METHOD_GET_MAX_DELETED_SEQ_NO,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedRequest,
                enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedResponse>(
                  this, METHODID_GET_MAX_DELETED_SEQ_NO)))
          .addMethod(
            METHOD_GET_VALID_DATA_LIST,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListRequest,
                enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListResponse>(
                  this, METHODID_GET_VALID_DATA_LIST)))
          .addMethod(
            METHOD_GET_DELETED_DATA_LIST,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListRequest,
                enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListResponse>(
                  this, METHODID_GET_DELETED_DATA_LIST)))
          .addMethod(
            METHOD_CHECK_CLUSTER_NAME_IS_EXISTED_OR_NOT,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotRequest,
                enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotResponse>(
                  this, METHODID_CHECK_CLUSTER_NAME_IS_EXISTED_OR_NOT)))
          .build();
    }
  }

  /**
   */
  public static final class EnnMonitorConfigClusterServerStub extends io.grpc.stub.AbstractStub<EnnMonitorConfigClusterServerStub> {
    private EnnMonitorConfigClusterServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorConfigClusterServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorConfigClusterServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorConfigClusterServerStub(channel, callOptions);
    }

    /**
     * <pre>
     * get cluster
     * </pre>
     */
    public void getCluster(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_CLUSTER, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * create cluster
     * </pre>
     */
    public void createCluster(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_CLUSTER, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * update cluster
     * </pre>
     */
    public void updateCluster(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_CLUSTER, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * delete cluster
     * </pre>
     */
    public void deleteCluster(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_CLUSTER, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public void getMaxDeletedSeqNo(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public void getValidDataList(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_VALID_DATA_LIST, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public void getDeletedDataList(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_DELETED_DATA_LIST, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * check whether or not the clusterName has existed
     * </pre>
     */
    public void checkClusterNameIsExistedOrNot(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CHECK_CLUSTER_NAME_IS_EXISTED_OR_NOT, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnnMonitorConfigClusterServerBlockingStub extends io.grpc.stub.AbstractStub<EnnMonitorConfigClusterServerBlockingStub> {
    private EnnMonitorConfigClusterServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorConfigClusterServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorConfigClusterServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorConfigClusterServerBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * get cluster
     * </pre>
     */
    public enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetResponse getCluster(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_CLUSTER, getCallOptions(), request);
    }

    /**
     * <pre>
     * create cluster
     * </pre>
     */
    public enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateResponse createCluster(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_CLUSTER, getCallOptions(), request);
    }

    /**
     * <pre>
     * update cluster
     * </pre>
     */
    public enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateResponse updateCluster(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE_CLUSTER, getCallOptions(), request);
    }

    /**
     * <pre>
     * delete cluster
     * </pre>
     */
    public enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteResponse deleteCluster(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELETE_CLUSTER, getCallOptions(), request);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedResponse getMaxDeletedSeqNo(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions(), request);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListResponse getValidDataList(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_VALID_DATA_LIST, getCallOptions(), request);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListResponse getDeletedDataList(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_DELETED_DATA_LIST, getCallOptions(), request);
    }

    /**
     * <pre>
     * check whether or not the clusterName has existed
     * </pre>
     */
    public enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotResponse checkClusterNameIsExistedOrNot(enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CHECK_CLUSTER_NAME_IS_EXISTED_OR_NOT, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnnMonitorConfigClusterServerFutureStub extends io.grpc.stub.AbstractStub<EnnMonitorConfigClusterServerFutureStub> {
    private EnnMonitorConfigClusterServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorConfigClusterServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorConfigClusterServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorConfigClusterServerFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * get cluster
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetResponse> getCluster(
        enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_CLUSTER, getCallOptions()), request);
    }

    /**
     * <pre>
     * create cluster
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateResponse> createCluster(
        enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_CLUSTER, getCallOptions()), request);
    }

    /**
     * <pre>
     * update cluster
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateResponse> updateCluster(
        enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_CLUSTER, getCallOptions()), request);
    }

    /**
     * <pre>
     * delete cluster
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteResponse> deleteCluster(
        enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_CLUSTER, getCallOptions()), request);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedResponse> getMaxDeletedSeqNo(
        enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions()), request);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListResponse> getValidDataList(
        enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_VALID_DATA_LIST, getCallOptions()), request);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListResponse> getDeletedDataList(
        enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_DELETED_DATA_LIST, getCallOptions()), request);
    }

    /**
     * <pre>
     * check whether or not the clusterName has existed
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotResponse> checkClusterNameIsExistedOrNot(
        enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CHECK_CLUSTER_NAME_IS_EXISTED_OR_NOT, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CLUSTER = 0;
  private static final int METHODID_CREATE_CLUSTER = 1;
  private static final int METHODID_UPDATE_CLUSTER = 2;
  private static final int METHODID_DELETE_CLUSTER = 3;
  private static final int METHODID_GET_MAX_DELETED_SEQ_NO = 4;
  private static final int METHODID_GET_VALID_DATA_LIST = 5;
  private static final int METHODID_GET_DELETED_DATA_LIST = 6;
  private static final int METHODID_CHECK_CLUSTER_NAME_IS_EXISTED_OR_NOT = 7;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnnMonitorConfigClusterServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(EnnMonitorConfigClusterServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CLUSTER:
          serviceImpl.getCluster((enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetResponse>) responseObserver);
          break;
        case METHODID_CREATE_CLUSTER:
          serviceImpl.createCluster((enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateResponse>) responseObserver);
          break;
        case METHODID_UPDATE_CLUSTER:
          serviceImpl.updateCluster((enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateResponse>) responseObserver);
          break;
        case METHODID_DELETE_CLUSTER:
          serviceImpl.deleteCluster((enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteResponse>) responseObserver);
          break;
        case METHODID_GET_MAX_DELETED_SEQ_NO:
          serviceImpl.getMaxDeletedSeqNo((enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedResponse>) responseObserver);
          break;
        case METHODID_GET_VALID_DATA_LIST:
          serviceImpl.getValidDataList((enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListResponse>) responseObserver);
          break;
        case METHODID_GET_DELETED_DATA_LIST:
          serviceImpl.getDeletedDataList((enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListResponse>) responseObserver);
          break;
        case METHODID_CHECK_CLUSTER_NAME_IS_EXISTED_OR_NOT:
          serviceImpl.checkClusterNameIsExistedOrNot((enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotResponse>) responseObserver);
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
        METHOD_GET_CLUSTER,
        METHOD_CREATE_CLUSTER,
        METHOD_UPDATE_CLUSTER,
        METHOD_DELETE_CLUSTER,
        METHOD_GET_MAX_DELETED_SEQ_NO,
        METHOD_GET_VALID_DATA_LIST,
        METHOD_GET_DELETED_DATA_LIST,
        METHOD_CHECK_CLUSTER_NAME_IS_EXISTED_OR_NOT);
  }

}
