package enn.monitor.log.train.proto;

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
    comments = "Source: protobuf/trainServer.proto")
public class EnnMonitorLogTrainMasterServerGrpc {

  private EnnMonitorLogTrainMasterServerGrpc() {}

  public static final String SERVICE_NAME = "EnnMonitorLogTrainMasterServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobReq,
      enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobRsp> METHOD_GET_JOB =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogTrainMasterServer", "getJob"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobReq.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobRsp.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlReq,
      enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlRsp> METHOD_GET_JOB_CTL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogTrainMasterServer", "getJobCtl"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlReq.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlRsp.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusReq,
      enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusRsp> METHOD_REPORT_JOB_STATUS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogTrainMasterServer", "reportJobStatus"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusReq.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusRsp.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployReq,
      enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployRsp> METHOD_DELOY_JOB =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogTrainMasterServer", "deloyJob"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployReq.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployRsp.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlReq,
      enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlRsp> METHOD_CTL_JOB =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogTrainMasterServer", "ctlJob"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlReq.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlRsp.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusReq,
      enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusRsp> METHOD_GET_JOB_STATUS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogTrainMasterServer", "getJobStatus"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusReq.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusRsp.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobReq,
      enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobRsp> METHOD_GET_BEST_JOB =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogTrainMasterServer", "getBestJob"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobReq.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobRsp.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnnMonitorLogTrainMasterServerStub newStub(io.grpc.Channel channel) {
    return new EnnMonitorLogTrainMasterServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogTrainMasterServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogTrainMasterServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogTrainMasterServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogTrainMasterServerFutureStub(channel);
  }

  /**
   */
  public static abstract class EnnMonitorLogTrainMasterServerImplBase implements io.grpc.BindableService {

    /**
     */
    public void getJob(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobRsp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_JOB, responseObserver);
    }

    /**
     */
    public void getJobCtl(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlRsp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_JOB_CTL, responseObserver);
    }

    /**
     */
    public void reportJobStatus(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusRsp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REPORT_JOB_STATUS, responseObserver);
    }

    /**
     */
    public void deloyJob(enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployRsp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELOY_JOB, responseObserver);
    }

    /**
     */
    public void ctlJob(enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlRsp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CTL_JOB, responseObserver);
    }

    /**
     */
    public void getJobStatus(enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusRsp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_JOB_STATUS, responseObserver);
    }

    /**
     */
    public void getBestJob(enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobRsp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_BEST_JOB, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_JOB,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobReq,
                enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobRsp>(
                  this, METHODID_GET_JOB)))
          .addMethod(
            METHOD_GET_JOB_CTL,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlReq,
                enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlRsp>(
                  this, METHODID_GET_JOB_CTL)))
          .addMethod(
            METHOD_REPORT_JOB_STATUS,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusReq,
                enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusRsp>(
                  this, METHODID_REPORT_JOB_STATUS)))
          .addMethod(
            METHOD_DELOY_JOB,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployReq,
                enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployRsp>(
                  this, METHODID_DELOY_JOB)))
          .addMethod(
            METHOD_CTL_JOB,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlReq,
                enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlRsp>(
                  this, METHODID_CTL_JOB)))
          .addMethod(
            METHOD_GET_JOB_STATUS,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusReq,
                enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusRsp>(
                  this, METHODID_GET_JOB_STATUS)))
          .addMethod(
            METHOD_GET_BEST_JOB,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobReq,
                enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobRsp>(
                  this, METHODID_GET_BEST_JOB)))
          .build();
    }
  }

  /**
   */
  public static final class EnnMonitorLogTrainMasterServerStub extends io.grpc.stub.AbstractStub<EnnMonitorLogTrainMasterServerStub> {
    private EnnMonitorLogTrainMasterServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogTrainMasterServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogTrainMasterServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogTrainMasterServerStub(channel, callOptions);
    }

    /**
     */
    public void getJob(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobRsp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_JOB, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getJobCtl(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlRsp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_JOB_CTL, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reportJobStatus(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusRsp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REPORT_JOB_STATUS, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deloyJob(enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployRsp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELOY_JOB, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void ctlJob(enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlRsp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CTL_JOB, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getJobStatus(enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusRsp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_JOB_STATUS, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBestJob(enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobRsp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_BEST_JOB, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnnMonitorLogTrainMasterServerBlockingStub extends io.grpc.stub.AbstractStub<EnnMonitorLogTrainMasterServerBlockingStub> {
    private EnnMonitorLogTrainMasterServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogTrainMasterServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogTrainMasterServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogTrainMasterServerBlockingStub(channel, callOptions);
    }

    /**
     */
    public enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobRsp getJob(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_JOB, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlRsp getJobCtl(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_JOB_CTL, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusRsp reportJobStatus(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REPORT_JOB_STATUS, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployRsp deloyJob(enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELOY_JOB, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlRsp ctlJob(enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CTL_JOB, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusRsp getJobStatus(enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_JOB_STATUS, getCallOptions(), request);
    }

    /**
     */
    public enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobRsp getBestJob(enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_BEST_JOB, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnnMonitorLogTrainMasterServerFutureStub extends io.grpc.stub.AbstractStub<EnnMonitorLogTrainMasterServerFutureStub> {
    private EnnMonitorLogTrainMasterServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogTrainMasterServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogTrainMasterServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogTrainMasterServerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobRsp> getJob(
        enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_JOB, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlRsp> getJobCtl(
        enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_JOB_CTL, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusRsp> reportJobStatus(
        enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REPORT_JOB_STATUS, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployRsp> deloyJob(
        enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELOY_JOB, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlRsp> ctlJob(
        enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CTL_JOB, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusRsp> getJobStatus(
        enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_JOB_STATUS, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobRsp> getBestJob(
        enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobReq request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_BEST_JOB, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_JOB = 0;
  private static final int METHODID_GET_JOB_CTL = 1;
  private static final int METHODID_REPORT_JOB_STATUS = 2;
  private static final int METHODID_DELOY_JOB = 3;
  private static final int METHODID_CTL_JOB = 4;
  private static final int METHODID_GET_JOB_STATUS = 5;
  private static final int METHODID_GET_BEST_JOB = 6;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnnMonitorLogTrainMasterServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(EnnMonitorLogTrainMasterServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_JOB:
          serviceImpl.getJob((enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobReq) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobRsp>) responseObserver);
          break;
        case METHODID_GET_JOB_CTL:
          serviceImpl.getJobCtl((enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlReq) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlRsp>) responseObserver);
          break;
        case METHODID_REPORT_JOB_STATUS:
          serviceImpl.reportJobStatus((enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusReq) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusRsp>) responseObserver);
          break;
        case METHODID_DELOY_JOB:
          serviceImpl.deloyJob((enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployReq) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployRsp>) responseObserver);
          break;
        case METHODID_CTL_JOB:
          serviceImpl.ctlJob((enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlReq) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlRsp>) responseObserver);
          break;
        case METHODID_GET_JOB_STATUS:
          serviceImpl.getJobStatus((enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusReq) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusRsp>) responseObserver);
          break;
        case METHODID_GET_BEST_JOB:
          serviceImpl.getBestJob((enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobReq) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobRsp>) responseObserver);
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
        METHOD_GET_JOB,
        METHOD_GET_JOB_CTL,
        METHOD_REPORT_JOB_STATUS,
        METHOD_DELOY_JOB,
        METHOD_CTL_JOB,
        METHOD_GET_JOB_STATUS,
        METHOD_GET_BEST_JOB);
  }

}
