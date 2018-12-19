package enn.monitor.alarm.ticket.Server;

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
    comments = "Source: protobuf/ticketServer.proto")
public class EnnMonitorAlarmTicketServerGrpc {

  private EnnMonitorAlarmTicketServerGrpc() {}

  public static final String SERVICE_NAME = "EnnMonitorAlarmTicketServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetRequest,
      enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetResponse> METHOD_GET_ENN_MONITOR_ALARM_TICKET =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorAlarmTicketServer", "getEnnMonitorAlarmTicket"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateRequest,
      enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateResponse> METHOD_CREATE_ENN_MONITOR_ALARM_TICKET =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorAlarmTicketServer", "createEnnMonitorAlarmTicket"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateRequest,
      enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateResponse> METHOD_UPDATE_ENN_MONITOR_ALARM_TICKET =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorAlarmTicketServer", "updateEnnMonitorAlarmTicket"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteRequest,
      enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteResponse> METHOD_DELETE_ENN_MONITOR_ALARM_TICKET =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorAlarmTicketServer", "deleteEnnMonitorAlarmTicket"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedRequest,
      enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedResponse> METHOD_GET_ENN_MONITOR_ALARM_TICKET_MAX_DELETED_SEQ_NO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorAlarmTicketServer", "getEnnMonitorAlarmTicketMaxDeletedSeqNo"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidRequest,
      enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidResponse> METHOD_GET_ENN_MONITOR_ALARM_TICKET_VALID =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorAlarmTicketServer", "getEnnMonitorAlarmTicketValid"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedRequest,
      enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedResponse> METHOD_GET_ENN_MONITOR_ALARM_TICKET_DELETED =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorAlarmTicketServer", "getEnnMonitorAlarmTicketDeleted"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState,
      enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformStateResponse> METHOD_UPDATE_ENN_MONITOR_ALARM_TICKET_TRANSFORM_STATE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorAlarmTicketServer", "updateEnnMonitorAlarmTicketTransformState"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformStateResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnnMonitorAlarmTicketServerStub newStub(io.grpc.Channel channel) {
    return new EnnMonitorAlarmTicketServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorAlarmTicketServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnnMonitorAlarmTicketServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorAlarmTicketServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnnMonitorAlarmTicketServerFutureStub(channel);
  }

  /**
   */
  public static abstract class EnnMonitorAlarmTicketServerImplBase implements io.grpc.BindableService {

    /**
     */
    public void getEnnMonitorAlarmTicket(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_ENN_MONITOR_ALARM_TICKET, responseObserver);
    }

    /**
     */
    public void createEnnMonitorAlarmTicket(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_ENN_MONITOR_ALARM_TICKET, responseObserver);
    }

    /**
     */
    public void updateEnnMonitorAlarmTicket(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE_ENN_MONITOR_ALARM_TICKET, responseObserver);
    }

    /**
     */
    public void deleteEnnMonitorAlarmTicket(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE_ENN_MONITOR_ALARM_TICKET, responseObserver);
    }

    /**
     */
    public void getEnnMonitorAlarmTicketMaxDeletedSeqNo(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_ENN_MONITOR_ALARM_TICKET_MAX_DELETED_SEQ_NO, responseObserver);
    }

    /**
     */
    public void getEnnMonitorAlarmTicketValid(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_ENN_MONITOR_ALARM_TICKET_VALID, responseObserver);
    }

    /**
     */
    public void getEnnMonitorAlarmTicketDeleted(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_ENN_MONITOR_ALARM_TICKET_DELETED, responseObserver);
    }

    /**
     */
    public void updateEnnMonitorAlarmTicketTransformState(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformStateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE_ENN_MONITOR_ALARM_TICKET_TRANSFORM_STATE, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_ENN_MONITOR_ALARM_TICKET,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetRequest,
                enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetResponse>(
                  this, METHODID_GET_ENN_MONITOR_ALARM_TICKET)))
          .addMethod(
            METHOD_CREATE_ENN_MONITOR_ALARM_TICKET,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateRequest,
                enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateResponse>(
                  this, METHODID_CREATE_ENN_MONITOR_ALARM_TICKET)))
          .addMethod(
            METHOD_UPDATE_ENN_MONITOR_ALARM_TICKET,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateRequest,
                enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateResponse>(
                  this, METHODID_UPDATE_ENN_MONITOR_ALARM_TICKET)))
          .addMethod(
            METHOD_DELETE_ENN_MONITOR_ALARM_TICKET,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteRequest,
                enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteResponse>(
                  this, METHODID_DELETE_ENN_MONITOR_ALARM_TICKET)))
          .addMethod(
            METHOD_GET_ENN_MONITOR_ALARM_TICKET_MAX_DELETED_SEQ_NO,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedRequest,
                enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedResponse>(
                  this, METHODID_GET_ENN_MONITOR_ALARM_TICKET_MAX_DELETED_SEQ_NO)))
          .addMethod(
            METHOD_GET_ENN_MONITOR_ALARM_TICKET_VALID,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidRequest,
                enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidResponse>(
                  this, METHODID_GET_ENN_MONITOR_ALARM_TICKET_VALID)))
          .addMethod(
            METHOD_GET_ENN_MONITOR_ALARM_TICKET_DELETED,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedRequest,
                enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedResponse>(
                  this, METHODID_GET_ENN_MONITOR_ALARM_TICKET_DELETED)))
          .addMethod(
            METHOD_UPDATE_ENN_MONITOR_ALARM_TICKET_TRANSFORM_STATE,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState,
                enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformStateResponse>(
                  this, METHODID_UPDATE_ENN_MONITOR_ALARM_TICKET_TRANSFORM_STATE)))
          .build();
    }
  }

  /**
   */
  public static final class EnnMonitorAlarmTicketServerStub extends io.grpc.stub.AbstractStub<EnnMonitorAlarmTicketServerStub> {
    private EnnMonitorAlarmTicketServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorAlarmTicketServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorAlarmTicketServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorAlarmTicketServerStub(channel, callOptions);
    }

    /**
     */
    public void getEnnMonitorAlarmTicket(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_ENN_MONITOR_ALARM_TICKET, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createEnnMonitorAlarmTicket(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_ENN_MONITOR_ALARM_TICKET, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateEnnMonitorAlarmTicket(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_ENN_MONITOR_ALARM_TICKET, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteEnnMonitorAlarmTicket(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_ENN_MONITOR_ALARM_TICKET, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEnnMonitorAlarmTicketMaxDeletedSeqNo(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_ENN_MONITOR_ALARM_TICKET_MAX_DELETED_SEQ_NO, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEnnMonitorAlarmTicketValid(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_ENN_MONITOR_ALARM_TICKET_VALID, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEnnMonitorAlarmTicketDeleted(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_ENN_MONITOR_ALARM_TICKET_DELETED, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateEnnMonitorAlarmTicketTransformState(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformStateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_ENN_MONITOR_ALARM_TICKET_TRANSFORM_STATE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnnMonitorAlarmTicketServerBlockingStub extends io.grpc.stub.AbstractStub<EnnMonitorAlarmTicketServerBlockingStub> {
    private EnnMonitorAlarmTicketServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorAlarmTicketServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorAlarmTicketServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorAlarmTicketServerBlockingStub(channel, callOptions);
    }

    /**
     */
    public enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetResponse getEnnMonitorAlarmTicket(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_ENN_MONITOR_ALARM_TICKET, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateResponse createEnnMonitorAlarmTicket(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_ENN_MONITOR_ALARM_TICKET, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateResponse updateEnnMonitorAlarmTicket(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE_ENN_MONITOR_ALARM_TICKET, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteResponse deleteEnnMonitorAlarmTicket(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELETE_ENN_MONITOR_ALARM_TICKET, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedResponse getEnnMonitorAlarmTicketMaxDeletedSeqNo(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_ENN_MONITOR_ALARM_TICKET_MAX_DELETED_SEQ_NO, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidResponse getEnnMonitorAlarmTicketValid(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_ENN_MONITOR_ALARM_TICKET_VALID, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedResponse getEnnMonitorAlarmTicketDeleted(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_ENN_MONITOR_ALARM_TICKET_DELETED, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformStateResponse updateEnnMonitorAlarmTicketTransformState(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE_ENN_MONITOR_ALARM_TICKET_TRANSFORM_STATE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnnMonitorAlarmTicketServerFutureStub extends io.grpc.stub.AbstractStub<EnnMonitorAlarmTicketServerFutureStub> {
    private EnnMonitorAlarmTicketServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorAlarmTicketServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorAlarmTicketServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorAlarmTicketServerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetResponse> getEnnMonitorAlarmTicket(
        enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_ENN_MONITOR_ALARM_TICKET, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateResponse> createEnnMonitorAlarmTicket(
        enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_ENN_MONITOR_ALARM_TICKET, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateResponse> updateEnnMonitorAlarmTicket(
        enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_ENN_MONITOR_ALARM_TICKET, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteResponse> deleteEnnMonitorAlarmTicket(
        enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_ENN_MONITOR_ALARM_TICKET, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedResponse> getEnnMonitorAlarmTicketMaxDeletedSeqNo(
        enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_ENN_MONITOR_ALARM_TICKET_MAX_DELETED_SEQ_NO, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidResponse> getEnnMonitorAlarmTicketValid(
        enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_ENN_MONITOR_ALARM_TICKET_VALID, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedResponse> getEnnMonitorAlarmTicketDeleted(
        enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_ENN_MONITOR_ALARM_TICKET_DELETED, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformStateResponse> updateEnnMonitorAlarmTicketTransformState(
        enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_ENN_MONITOR_ALARM_TICKET_TRANSFORM_STATE, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ENN_MONITOR_ALARM_TICKET = 0;
  private static final int METHODID_CREATE_ENN_MONITOR_ALARM_TICKET = 1;
  private static final int METHODID_UPDATE_ENN_MONITOR_ALARM_TICKET = 2;
  private static final int METHODID_DELETE_ENN_MONITOR_ALARM_TICKET = 3;
  private static final int METHODID_GET_ENN_MONITOR_ALARM_TICKET_MAX_DELETED_SEQ_NO = 4;
  private static final int METHODID_GET_ENN_MONITOR_ALARM_TICKET_VALID = 5;
  private static final int METHODID_GET_ENN_MONITOR_ALARM_TICKET_DELETED = 6;
  private static final int METHODID_UPDATE_ENN_MONITOR_ALARM_TICKET_TRANSFORM_STATE = 7;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnnMonitorAlarmTicketServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(EnnMonitorAlarmTicketServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ENN_MONITOR_ALARM_TICKET:
          serviceImpl.getEnnMonitorAlarmTicket((enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetResponse>) responseObserver);
          break;
        case METHODID_CREATE_ENN_MONITOR_ALARM_TICKET:
          serviceImpl.createEnnMonitorAlarmTicket((enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateResponse>) responseObserver);
          break;
        case METHODID_UPDATE_ENN_MONITOR_ALARM_TICKET:
          serviceImpl.updateEnnMonitorAlarmTicket((enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateResponse>) responseObserver);
          break;
        case METHODID_DELETE_ENN_MONITOR_ALARM_TICKET:
          serviceImpl.deleteEnnMonitorAlarmTicket((enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteResponse>) responseObserver);
          break;
        case METHODID_GET_ENN_MONITOR_ALARM_TICKET_MAX_DELETED_SEQ_NO:
          serviceImpl.getEnnMonitorAlarmTicketMaxDeletedSeqNo((enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedResponse>) responseObserver);
          break;
        case METHODID_GET_ENN_MONITOR_ALARM_TICKET_VALID:
          serviceImpl.getEnnMonitorAlarmTicketValid((enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidResponse>) responseObserver);
          break;
        case METHODID_GET_ENN_MONITOR_ALARM_TICKET_DELETED:
          serviceImpl.getEnnMonitorAlarmTicketDeleted((enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedResponse>) responseObserver);
          break;
        case METHODID_UPDATE_ENN_MONITOR_ALARM_TICKET_TRANSFORM_STATE:
          serviceImpl.updateEnnMonitorAlarmTicketTransformState((enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState) request,
              (io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformStateResponse>) responseObserver);
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
        METHOD_GET_ENN_MONITOR_ALARM_TICKET,
        METHOD_CREATE_ENN_MONITOR_ALARM_TICKET,
        METHOD_UPDATE_ENN_MONITOR_ALARM_TICKET,
        METHOD_DELETE_ENN_MONITOR_ALARM_TICKET,
        METHOD_GET_ENN_MONITOR_ALARM_TICKET_MAX_DELETED_SEQ_NO,
        METHOD_GET_ENN_MONITOR_ALARM_TICKET_VALID,
        METHOD_GET_ENN_MONITOR_ALARM_TICKET_DELETED,
        METHOD_UPDATE_ENN_MONITOR_ALARM_TICKET_TRANSFORM_STATE);
  }

}
