package enn.monitor.config.business.topic.server;

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
    comments = "Source: protobuf/topicServer.proto")
public class EnnMonitorConfigBusinessTopicServerGrpc {

  private EnnMonitorConfigBusinessTopicServerGrpc() {}

  public static final String SERVICE_NAME = "EnnMonitorConfigBusinessTopicServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetRequest,
      enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetResponse> METHOD_GET_TOPIC =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigBusinessTopicServer", "GetTopic"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateRequest,
      enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateResponse> METHOD_CREATE_TOPIC =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigBusinessTopicServer", "CreateTopic"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateRequest,
      enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateResponse> METHOD_UPDATE_TOPIC =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigBusinessTopicServer", "UpdateTopic"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest,
      enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteResponse> METHOD_DELETE_TOPIC =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigBusinessTopicServer", "DeleteTopic"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedRequest,
      enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse> METHOD_GET_MAX_DELETED_SEQ_NO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigBusinessTopicServer", "getMaxDeletedSeqNo"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListRequest,
      enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListResponse> METHOD_GET_VALID_DATA_LIST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigBusinessTopicServer", "getValidDataList"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListRequest,
      enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListResponse> METHOD_GET_DELETED_DATA_LIST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorConfigBusinessTopicServer", "getDeletedDataList"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnnMonitorConfigBusinessTopicServerStub newStub(io.grpc.Channel channel) {
    return new EnnMonitorConfigBusinessTopicServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorConfigBusinessTopicServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnnMonitorConfigBusinessTopicServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorConfigBusinessTopicServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnnMonitorConfigBusinessTopicServerFutureStub(channel);
  }

  /**
   */
  public static abstract class EnnMonitorConfigBusinessTopicServerImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * get topic
     * </pre>
     */
    public void getTopic(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_TOPIC, responseObserver);
    }

    /**
     * <pre>
     * create topic
     * </pre>
     */
    public void createTopic(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_TOPIC, responseObserver);
    }

    /**
     * <pre>
     * update topic
     * </pre>
     */
    public void updateTopic(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE_TOPIC, responseObserver);
    }

    /**
     * <pre>
     * delete topic
     * </pre>
     */
    public void deleteTopic(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE_TOPIC, responseObserver);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public void getMaxDeletedSeqNo(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_MAX_DELETED_SEQ_NO, responseObserver);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public void getValidDataList(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_VALID_DATA_LIST, responseObserver);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public void getDeletedDataList(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_DELETED_DATA_LIST, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_TOPIC,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetRequest,
                enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetResponse>(
                  this, METHODID_GET_TOPIC)))
          .addMethod(
            METHOD_CREATE_TOPIC,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateRequest,
                enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateResponse>(
                  this, METHODID_CREATE_TOPIC)))
          .addMethod(
            METHOD_UPDATE_TOPIC,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateRequest,
                enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateResponse>(
                  this, METHODID_UPDATE_TOPIC)))
          .addMethod(
            METHOD_DELETE_TOPIC,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest,
                enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteResponse>(
                  this, METHODID_DELETE_TOPIC)))
          .addMethod(
            METHOD_GET_MAX_DELETED_SEQ_NO,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedRequest,
                enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse>(
                  this, METHODID_GET_MAX_DELETED_SEQ_NO)))
          .addMethod(
            METHOD_GET_VALID_DATA_LIST,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListRequest,
                enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListResponse>(
                  this, METHODID_GET_VALID_DATA_LIST)))
          .addMethod(
            METHOD_GET_DELETED_DATA_LIST,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListRequest,
                enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListResponse>(
                  this, METHODID_GET_DELETED_DATA_LIST)))
          .build();
    }
  }

  /**
   */
  public static final class EnnMonitorConfigBusinessTopicServerStub extends io.grpc.stub.AbstractStub<EnnMonitorConfigBusinessTopicServerStub> {
    private EnnMonitorConfigBusinessTopicServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorConfigBusinessTopicServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorConfigBusinessTopicServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorConfigBusinessTopicServerStub(channel, callOptions);
    }

    /**
     * <pre>
     * get topic
     * </pre>
     */
    public void getTopic(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_TOPIC, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * create topic
     * </pre>
     */
    public void createTopic(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_TOPIC, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * update topic
     * </pre>
     */
    public void updateTopic(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_TOPIC, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * delete topic
     * </pre>
     */
    public void deleteTopic(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_TOPIC, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public void getMaxDeletedSeqNo(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public void getValidDataList(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_VALID_DATA_LIST, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public void getDeletedDataList(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_DELETED_DATA_LIST, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnnMonitorConfigBusinessTopicServerBlockingStub extends io.grpc.stub.AbstractStub<EnnMonitorConfigBusinessTopicServerBlockingStub> {
    private EnnMonitorConfigBusinessTopicServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorConfigBusinessTopicServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorConfigBusinessTopicServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorConfigBusinessTopicServerBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * get topic
     * </pre>
     */
    public enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetResponse getTopic(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_TOPIC, getCallOptions(), request);
    }

    /**
     * <pre>
     * create topic
     * </pre>
     */
    public enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateResponse createTopic(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_TOPIC, getCallOptions(), request);
    }

    /**
     * <pre>
     * update topic
     * </pre>
     */
    public enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateResponse updateTopic(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE_TOPIC, getCallOptions(), request);
    }

    /**
     * <pre>
     * delete topic
     * </pre>
     */
    public enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteResponse deleteTopic(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELETE_TOPIC, getCallOptions(), request);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse getMaxDeletedSeqNo(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions(), request);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListResponse getValidDataList(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_VALID_DATA_LIST, getCallOptions(), request);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListResponse getDeletedDataList(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_DELETED_DATA_LIST, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnnMonitorConfigBusinessTopicServerFutureStub extends io.grpc.stub.AbstractStub<EnnMonitorConfigBusinessTopicServerFutureStub> {
    private EnnMonitorConfigBusinessTopicServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorConfigBusinessTopicServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorConfigBusinessTopicServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorConfigBusinessTopicServerFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * get topic
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetResponse> getTopic(
        enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_TOPIC, getCallOptions()), request);
    }

    /**
     * <pre>
     * create topic
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateResponse> createTopic(
        enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_TOPIC, getCallOptions()), request);
    }

    /**
     * <pre>
     * update topic
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateResponse> updateTopic(
        enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_TOPIC, getCallOptions()), request);
    }

    /**
     * <pre>
     * delete topic
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteResponse> deleteTopic(
        enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_TOPIC, getCallOptions()), request);
    }

    /**
     * <pre>
     * get max deleted seqNo
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse> getMaxDeletedSeqNo(
        enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_MAX_DELETED_SEQ_NO, getCallOptions()), request);
    }

    /**
     * <pre>
     * get valid data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListResponse> getValidDataList(
        enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_VALID_DATA_LIST, getCallOptions()), request);
    }

    /**
     * <pre>
     * get deleted data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListResponse> getDeletedDataList(
        enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_DELETED_DATA_LIST, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_TOPIC = 0;
  private static final int METHODID_CREATE_TOPIC = 1;
  private static final int METHODID_UPDATE_TOPIC = 2;
  private static final int METHODID_DELETE_TOPIC = 3;
  private static final int METHODID_GET_MAX_DELETED_SEQ_NO = 4;
  private static final int METHODID_GET_VALID_DATA_LIST = 5;
  private static final int METHODID_GET_DELETED_DATA_LIST = 6;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnnMonitorConfigBusinessTopicServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(EnnMonitorConfigBusinessTopicServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_TOPIC:
          serviceImpl.getTopic((enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetResponse>) responseObserver);
          break;
        case METHODID_CREATE_TOPIC:
          serviceImpl.createTopic((enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateResponse>) responseObserver);
          break;
        case METHODID_UPDATE_TOPIC:
          serviceImpl.updateTopic((enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateResponse>) responseObserver);
          break;
        case METHODID_DELETE_TOPIC:
          serviceImpl.deleteTopic((enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteResponse>) responseObserver);
          break;
        case METHODID_GET_MAX_DELETED_SEQ_NO:
          serviceImpl.getMaxDeletedSeqNo((enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse>) responseObserver);
          break;
        case METHODID_GET_VALID_DATA_LIST:
          serviceImpl.getValidDataList((enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListResponse>) responseObserver);
          break;
        case METHODID_GET_DELETED_DATA_LIST:
          serviceImpl.getDeletedDataList((enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListResponse>) responseObserver);
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
        METHOD_GET_TOPIC,
        METHOD_CREATE_TOPIC,
        METHOD_UPDATE_TOPIC,
        METHOD_DELETE_TOPIC,
        METHOD_GET_MAX_DELETED_SEQ_NO,
        METHOD_GET_VALID_DATA_LIST,
        METHOD_GET_DELETED_DATA_LIST);
  }

}
