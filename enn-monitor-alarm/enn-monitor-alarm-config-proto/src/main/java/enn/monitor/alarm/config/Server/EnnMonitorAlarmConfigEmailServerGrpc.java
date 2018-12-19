package enn.monitor.alarm.config.Server;

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
    comments = "Source: protobuf/configServer.proto")
public class EnnMonitorAlarmConfigEmailServerGrpc {

  private EnnMonitorAlarmConfigEmailServerGrpc() {}

  public static final String SERVICE_NAME = "EnnMonitorAlarmConfigEmailServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetRequest,
      enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetResponse> METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorAlarmConfigEmailServer", "getEnnMonitorAlarmConfigEmail"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateRequest,
      enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateResponse> METHOD_CREATE_ENN_MONITOR_ALARM_CONFIG_EMAIL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorAlarmConfigEmailServer", "createEnnMonitorAlarmConfigEmail"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateRequest,
      enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateResponse> METHOD_UPDATE_ENN_MONITOR_ALARM_CONFIG_EMAIL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorAlarmConfigEmailServer", "updateEnnMonitorAlarmConfigEmail"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteRequest,
      enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteResponse> METHOD_DELETE_ENN_MONITOR_ALARM_CONFIG_EMAIL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorAlarmConfigEmailServer", "deleteEnnMonitorAlarmConfigEmail"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest,
      enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetMaxDeletedResponse> METHOD_GET_MAX_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED_SEQ_NO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorAlarmConfigEmailServer", "getMaxEnnMonitorAlarmConfigEmailDeletedSeqNo"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetMaxDeletedResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidRequest,
      enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidResponse> METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_VALID =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorAlarmConfigEmailServer", "getEnnMonitorAlarmConfigEmailValid"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedRequest,
      enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedResponse> METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorAlarmConfigEmailServer", "getEnnMonitorAlarmConfigEmailDeleted"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnnMonitorAlarmConfigEmailServerStub newStub(io.grpc.Channel channel) {
    return new EnnMonitorAlarmConfigEmailServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorAlarmConfigEmailServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnnMonitorAlarmConfigEmailServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorAlarmConfigEmailServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnnMonitorAlarmConfigEmailServerFutureStub(channel);
  }

  /**
   */
  public static abstract class EnnMonitorAlarmConfigEmailServerImplBase implements io.grpc.BindableService {

    /**
     */
    public void getEnnMonitorAlarmConfigEmail(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL, responseObserver);
    }

    /**
     */
    public void createEnnMonitorAlarmConfigEmail(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_ENN_MONITOR_ALARM_CONFIG_EMAIL, responseObserver);
    }

    /**
     */
    public void updateEnnMonitorAlarmConfigEmail(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE_ENN_MONITOR_ALARM_CONFIG_EMAIL, responseObserver);
    }

    /**
     */
    public void deleteEnnMonitorAlarmConfigEmail(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE_ENN_MONITOR_ALARM_CONFIG_EMAIL, responseObserver);
    }

    /**
     */
    public void getMaxEnnMonitorAlarmConfigEmailDeletedSeqNo(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetMaxDeletedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_MAX_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED_SEQ_NO, responseObserver);
    }

    /**
     */
    public void getEnnMonitorAlarmConfigEmailValid(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_VALID, responseObserver);
    }

    /**
     */
    public void getEnnMonitorAlarmConfigEmailDeleted(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetRequest,
                enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetResponse>(
                  this, METHODID_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL)))
          .addMethod(
            METHOD_CREATE_ENN_MONITOR_ALARM_CONFIG_EMAIL,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateRequest,
                enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateResponse>(
                  this, METHODID_CREATE_ENN_MONITOR_ALARM_CONFIG_EMAIL)))
          .addMethod(
            METHOD_UPDATE_ENN_MONITOR_ALARM_CONFIG_EMAIL,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateRequest,
                enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateResponse>(
                  this, METHODID_UPDATE_ENN_MONITOR_ALARM_CONFIG_EMAIL)))
          .addMethod(
            METHOD_DELETE_ENN_MONITOR_ALARM_CONFIG_EMAIL,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteRequest,
                enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteResponse>(
                  this, METHODID_DELETE_ENN_MONITOR_ALARM_CONFIG_EMAIL)))
          .addMethod(
            METHOD_GET_MAX_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED_SEQ_NO,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest,
                enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetMaxDeletedResponse>(
                  this, METHODID_GET_MAX_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED_SEQ_NO)))
          .addMethod(
            METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_VALID,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidRequest,
                enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidResponse>(
                  this, METHODID_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_VALID)))
          .addMethod(
            METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedRequest,
                enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedResponse>(
                  this, METHODID_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED)))
          .build();
    }
  }

  /**
   */
  public static final class EnnMonitorAlarmConfigEmailServerStub extends io.grpc.stub.AbstractStub<EnnMonitorAlarmConfigEmailServerStub> {
    private EnnMonitorAlarmConfigEmailServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorAlarmConfigEmailServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorAlarmConfigEmailServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorAlarmConfigEmailServerStub(channel, callOptions);
    }

    /**
     */
    public void getEnnMonitorAlarmConfigEmail(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createEnnMonitorAlarmConfigEmail(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_ENN_MONITOR_ALARM_CONFIG_EMAIL, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateEnnMonitorAlarmConfigEmail(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_ENN_MONITOR_ALARM_CONFIG_EMAIL, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteEnnMonitorAlarmConfigEmail(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_ENN_MONITOR_ALARM_CONFIG_EMAIL, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getMaxEnnMonitorAlarmConfigEmailDeletedSeqNo(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetMaxDeletedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_MAX_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED_SEQ_NO, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEnnMonitorAlarmConfigEmailValid(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_VALID, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEnnMonitorAlarmConfigEmailDeleted(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnnMonitorAlarmConfigEmailServerBlockingStub extends io.grpc.stub.AbstractStub<EnnMonitorAlarmConfigEmailServerBlockingStub> {
    private EnnMonitorAlarmConfigEmailServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorAlarmConfigEmailServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorAlarmConfigEmailServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorAlarmConfigEmailServerBlockingStub(channel, callOptions);
    }

    /**
     */
    public enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetResponse getEnnMonitorAlarmConfigEmail(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateResponse createEnnMonitorAlarmConfigEmail(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_ENN_MONITOR_ALARM_CONFIG_EMAIL, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateResponse updateEnnMonitorAlarmConfigEmail(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE_ENN_MONITOR_ALARM_CONFIG_EMAIL, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteResponse deleteEnnMonitorAlarmConfigEmail(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELETE_ENN_MONITOR_ALARM_CONFIG_EMAIL, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetMaxDeletedResponse getMaxEnnMonitorAlarmConfigEmailDeletedSeqNo(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_MAX_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED_SEQ_NO, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidResponse getEnnMonitorAlarmConfigEmailValid(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_VALID, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedResponse getEnnMonitorAlarmConfigEmailDeleted(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnnMonitorAlarmConfigEmailServerFutureStub extends io.grpc.stub.AbstractStub<EnnMonitorAlarmConfigEmailServerFutureStub> {
    private EnnMonitorAlarmConfigEmailServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorAlarmConfigEmailServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorAlarmConfigEmailServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorAlarmConfigEmailServerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetResponse> getEnnMonitorAlarmConfigEmail(
        enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateResponse> createEnnMonitorAlarmConfigEmail(
        enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_ENN_MONITOR_ALARM_CONFIG_EMAIL, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateResponse> updateEnnMonitorAlarmConfigEmail(
        enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_ENN_MONITOR_ALARM_CONFIG_EMAIL, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteResponse> deleteEnnMonitorAlarmConfigEmail(
        enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_ENN_MONITOR_ALARM_CONFIG_EMAIL, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetMaxDeletedResponse> getMaxEnnMonitorAlarmConfigEmailDeletedSeqNo(
        enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_MAX_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED_SEQ_NO, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidResponse> getEnnMonitorAlarmConfigEmailValid(
        enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_VALID, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedResponse> getEnnMonitorAlarmConfigEmailDeleted(
        enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL = 0;
  private static final int METHODID_CREATE_ENN_MONITOR_ALARM_CONFIG_EMAIL = 1;
  private static final int METHODID_UPDATE_ENN_MONITOR_ALARM_CONFIG_EMAIL = 2;
  private static final int METHODID_DELETE_ENN_MONITOR_ALARM_CONFIG_EMAIL = 3;
  private static final int METHODID_GET_MAX_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED_SEQ_NO = 4;
  private static final int METHODID_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_VALID = 5;
  private static final int METHODID_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED = 6;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnnMonitorAlarmConfigEmailServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(EnnMonitorAlarmConfigEmailServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL:
          serviceImpl.getEnnMonitorAlarmConfigEmail((enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetResponse>) responseObserver);
          break;
        case METHODID_CREATE_ENN_MONITOR_ALARM_CONFIG_EMAIL:
          serviceImpl.createEnnMonitorAlarmConfigEmail((enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateResponse>) responseObserver);
          break;
        case METHODID_UPDATE_ENN_MONITOR_ALARM_CONFIG_EMAIL:
          serviceImpl.updateEnnMonitorAlarmConfigEmail((enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateResponse>) responseObserver);
          break;
        case METHODID_DELETE_ENN_MONITOR_ALARM_CONFIG_EMAIL:
          serviceImpl.deleteEnnMonitorAlarmConfigEmail((enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteResponse>) responseObserver);
          break;
        case METHODID_GET_MAX_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED_SEQ_NO:
          serviceImpl.getMaxEnnMonitorAlarmConfigEmailDeletedSeqNo((enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetMaxDeletedResponse>) responseObserver);
          break;
        case METHODID_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_VALID:
          serviceImpl.getEnnMonitorAlarmConfigEmailValid((enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidResponse>) responseObserver);
          break;
        case METHODID_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED:
          serviceImpl.getEnnMonitorAlarmConfigEmailDeleted((enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedResponse>) responseObserver);
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
        METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL,
        METHOD_CREATE_ENN_MONITOR_ALARM_CONFIG_EMAIL,
        METHOD_UPDATE_ENN_MONITOR_ALARM_CONFIG_EMAIL,
        METHOD_DELETE_ENN_MONITOR_ALARM_CONFIG_EMAIL,
        METHOD_GET_MAX_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED_SEQ_NO,
        METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_VALID,
        METHOD_GET_ENN_MONITOR_ALARM_CONFIG_EMAIL_DELETED);
  }

}
