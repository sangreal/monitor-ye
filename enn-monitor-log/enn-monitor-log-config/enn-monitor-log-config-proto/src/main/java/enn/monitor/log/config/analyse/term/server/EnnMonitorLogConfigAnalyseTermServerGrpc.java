package enn.monitor.log.config.analyse.term.server;

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
    comments = "Source: protobuf/logAnalyseTermServer.proto")
public class EnnMonitorLogConfigAnalyseTermServerGrpc {

  private EnnMonitorLogConfigAnalyseTermServerGrpc() {}

  public static final String SERVICE_NAME = "EnnMonitorLogConfigAnalyseTermServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetRequest,
      enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetResponse> METHOD_GET_ANALYSE_TERM =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigAnalyseTermServer", "GetAnalyseTerm"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateRequest,
      enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateResponse> METHOD_CREATE_ANALYSE_TERM =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigAnalyseTermServer", "CreateAnalyseTerm"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateRequest,
      enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateResponse> METHOD_UPDATE_ANALYSE_TERM =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigAnalyseTermServer", "UpdateAnalyseTerm"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteRequest,
      enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteResponse> METHOD_DELETE_ANALYSE_TERM =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigAnalyseTermServer", "DeleteAnalyseTerm"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnnMonitorLogConfigAnalyseTermServerStub newStub(io.grpc.Channel channel) {
    return new EnnMonitorLogConfigAnalyseTermServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogConfigAnalyseTermServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogConfigAnalyseTermServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogConfigAnalyseTermServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogConfigAnalyseTermServerFutureStub(channel);
  }

  /**
   */
  public static abstract class EnnMonitorLogConfigAnalyseTermServerImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * get AnalyseTerm
     * </pre>
     */
    public void getAnalyseTerm(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_ANALYSE_TERM, responseObserver);
    }

    /**
     * <pre>
     * create AnalyseTerm
     * </pre>
     */
    public void createAnalyseTerm(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_ANALYSE_TERM, responseObserver);
    }

    /**
     * <pre>
     * update AnalyseTerm
     * </pre>
     */
    public void updateAnalyseTerm(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE_ANALYSE_TERM, responseObserver);
    }

    /**
     * <pre>
     * delete AnalyseTerm
     * </pre>
     */
    public void deleteAnalyseTerm(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE_ANALYSE_TERM, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_ANALYSE_TERM,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetRequest,
                enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetResponse>(
                  this, METHODID_GET_ANALYSE_TERM)))
          .addMethod(
            METHOD_CREATE_ANALYSE_TERM,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateRequest,
                enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateResponse>(
                  this, METHODID_CREATE_ANALYSE_TERM)))
          .addMethod(
            METHOD_UPDATE_ANALYSE_TERM,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateRequest,
                enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateResponse>(
                  this, METHODID_UPDATE_ANALYSE_TERM)))
          .addMethod(
            METHOD_DELETE_ANALYSE_TERM,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteRequest,
                enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteResponse>(
                  this, METHODID_DELETE_ANALYSE_TERM)))
          .build();
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigAnalyseTermServerStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigAnalyseTermServerStub> {
    private EnnMonitorLogConfigAnalyseTermServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigAnalyseTermServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigAnalyseTermServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigAnalyseTermServerStub(channel, callOptions);
    }

    /**
     * <pre>
     * get AnalyseTerm
     * </pre>
     */
    public void getAnalyseTerm(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_ANALYSE_TERM, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * create AnalyseTerm
     * </pre>
     */
    public void createAnalyseTerm(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_ANALYSE_TERM, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * update AnalyseTerm
     * </pre>
     */
    public void updateAnalyseTerm(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_ANALYSE_TERM, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * delete AnalyseTerm
     * </pre>
     */
    public void deleteAnalyseTerm(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_ANALYSE_TERM, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigAnalyseTermServerBlockingStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigAnalyseTermServerBlockingStub> {
    private EnnMonitorLogConfigAnalyseTermServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigAnalyseTermServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigAnalyseTermServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigAnalyseTermServerBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * get AnalyseTerm
     * </pre>
     */
    public enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetResponse getAnalyseTerm(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_ANALYSE_TERM, getCallOptions(), request);
    }

    /**
     * <pre>
     * create AnalyseTerm
     * </pre>
     */
    public enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateResponse createAnalyseTerm(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_ANALYSE_TERM, getCallOptions(), request);
    }

    /**
     * <pre>
     * update AnalyseTerm
     * </pre>
     */
    public enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateResponse updateAnalyseTerm(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE_ANALYSE_TERM, getCallOptions(), request);
    }

    /**
     * <pre>
     * delete AnalyseTerm
     * </pre>
     */
    public enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteResponse deleteAnalyseTerm(enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELETE_ANALYSE_TERM, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigAnalyseTermServerFutureStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigAnalyseTermServerFutureStub> {
    private EnnMonitorLogConfigAnalyseTermServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigAnalyseTermServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigAnalyseTermServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigAnalyseTermServerFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * get AnalyseTerm
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetResponse> getAnalyseTerm(
        enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_ANALYSE_TERM, getCallOptions()), request);
    }

    /**
     * <pre>
     * create AnalyseTerm
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateResponse> createAnalyseTerm(
        enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_ANALYSE_TERM, getCallOptions()), request);
    }

    /**
     * <pre>
     * update AnalyseTerm
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateResponse> updateAnalyseTerm(
        enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_ANALYSE_TERM, getCallOptions()), request);
    }

    /**
     * <pre>
     * delete AnalyseTerm
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteResponse> deleteAnalyseTerm(
        enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_ANALYSE_TERM, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ANALYSE_TERM = 0;
  private static final int METHODID_CREATE_ANALYSE_TERM = 1;
  private static final int METHODID_UPDATE_ANALYSE_TERM = 2;
  private static final int METHODID_DELETE_ANALYSE_TERM = 3;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnnMonitorLogConfigAnalyseTermServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(EnnMonitorLogConfigAnalyseTermServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ANALYSE_TERM:
          serviceImpl.getAnalyseTerm((enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetResponse>) responseObserver);
          break;
        case METHODID_CREATE_ANALYSE_TERM:
          serviceImpl.createAnalyseTerm((enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateResponse>) responseObserver);
          break;
        case METHODID_UPDATE_ANALYSE_TERM:
          serviceImpl.updateAnalyseTerm((enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateResponse>) responseObserver);
          break;
        case METHODID_DELETE_ANALYSE_TERM:
          serviceImpl.deleteAnalyseTerm((enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteResponse>) responseObserver);
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
        METHOD_GET_ANALYSE_TERM,
        METHOD_CREATE_ANALYSE_TERM,
        METHOD_UPDATE_ANALYSE_TERM,
        METHOD_DELETE_ANALYSE_TERM);
  }

}
