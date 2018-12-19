package enn.monitor.log.config.template.term.server;

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
    comments = "Source: protobuf/logTemplateTermServer.proto")
public class EnnMonitorLogConfigTemplateTermServerGrpc {

  private EnnMonitorLogConfigTemplateTermServerGrpc() {}

  public static final String SERVICE_NAME = "EnnMonitorLogConfigTemplateTermServer";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetRequest,
      enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetResponse> METHOD_GET_TEMPLATE_TERM =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigTemplateTermServer", "GetTemplateTerm"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateRequest,
      enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateResponse> METHOD_CREATE_TEMPLATE_TERM =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigTemplateTermServer", "CreateTemplateTerm"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteRequest,
      enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteResponse> METHOD_DELETE_TEMPLATE_TERM =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EnnMonitorLogConfigTemplateTermServer", "DeleteTemplateTerm"),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnnMonitorLogConfigTemplateTermServerStub newStub(io.grpc.Channel channel) {
    return new EnnMonitorLogConfigTemplateTermServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogConfigTemplateTermServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogConfigTemplateTermServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EnnMonitorLogConfigTemplateTermServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnnMonitorLogConfigTemplateTermServerFutureStub(channel);
  }

  /**
   */
  public static abstract class EnnMonitorLogConfigTemplateTermServerImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * get TemplateTerm
     * </pre>
     */
    public void getTemplateTerm(enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_TEMPLATE_TERM, responseObserver);
    }

    /**
     * <pre>
     * create TemplateTerm
     * </pre>
     */
    public void createTemplateTerm(enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_TEMPLATE_TERM, responseObserver);
    }

    /**
     * <pre>
     * delete TemplateTerm
     * </pre>
     */
    public void deleteTemplateTerm(enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE_TEMPLATE_TERM, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_TEMPLATE_TERM,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetRequest,
                enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetResponse>(
                  this, METHODID_GET_TEMPLATE_TERM)))
          .addMethod(
            METHOD_CREATE_TEMPLATE_TERM,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateRequest,
                enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateResponse>(
                  this, METHODID_CREATE_TEMPLATE_TERM)))
          .addMethod(
            METHOD_DELETE_TEMPLATE_TERM,
            asyncUnaryCall(
              new MethodHandlers<
                enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteRequest,
                enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteResponse>(
                  this, METHODID_DELETE_TEMPLATE_TERM)))
          .build();
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigTemplateTermServerStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigTemplateTermServerStub> {
    private EnnMonitorLogConfigTemplateTermServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigTemplateTermServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigTemplateTermServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigTemplateTermServerStub(channel, callOptions);
    }

    /**
     * <pre>
     * get TemplateTerm
     * </pre>
     */
    public void getTemplateTerm(enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_TEMPLATE_TERM, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * create TemplateTerm
     * </pre>
     */
    public void createTemplateTerm(enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_TEMPLATE_TERM, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * delete TemplateTerm
     * </pre>
     */
    public void deleteTemplateTerm(enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_TEMPLATE_TERM, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigTemplateTermServerBlockingStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigTemplateTermServerBlockingStub> {
    private EnnMonitorLogConfigTemplateTermServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigTemplateTermServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigTemplateTermServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigTemplateTermServerBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * get TemplateTerm
     * </pre>
     */
    public enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetResponse getTemplateTerm(enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_TEMPLATE_TERM, getCallOptions(), request);
    }

    /**
     * <pre>
     * create TemplateTerm
     * </pre>
     */
    public enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateResponse createTemplateTerm(enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_TEMPLATE_TERM, getCallOptions(), request);
    }

    /**
     * <pre>
     * delete TemplateTerm
     * </pre>
     */
    public enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteResponse deleteTemplateTerm(enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELETE_TEMPLATE_TERM, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnnMonitorLogConfigTemplateTermServerFutureStub extends io.grpc.stub.AbstractStub<EnnMonitorLogConfigTemplateTermServerFutureStub> {
    private EnnMonitorLogConfigTemplateTermServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnnMonitorLogConfigTemplateTermServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnnMonitorLogConfigTemplateTermServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnnMonitorLogConfigTemplateTermServerFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * get TemplateTerm
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetResponse> getTemplateTerm(
        enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_TEMPLATE_TERM, getCallOptions()), request);
    }

    /**
     * <pre>
     * create TemplateTerm
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateResponse> createTemplateTerm(
        enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_TEMPLATE_TERM, getCallOptions()), request);
    }

    /**
     * <pre>
     * delete TemplateTerm
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteResponse> deleteTemplateTerm(
        enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_TEMPLATE_TERM, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_TEMPLATE_TERM = 0;
  private static final int METHODID_CREATE_TEMPLATE_TERM = 1;
  private static final int METHODID_DELETE_TEMPLATE_TERM = 2;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnnMonitorLogConfigTemplateTermServerImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(EnnMonitorLogConfigTemplateTermServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_TEMPLATE_TERM:
          serviceImpl.getTemplateTerm((enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetResponse>) responseObserver);
          break;
        case METHODID_CREATE_TEMPLATE_TERM:
          serviceImpl.createTemplateTerm((enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateResponse>) responseObserver);
          break;
        case METHODID_DELETE_TEMPLATE_TERM:
          serviceImpl.deleteTemplateTerm((enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteRequest) request,
              (io.grpc.stub.StreamObserver<enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteResponse>) responseObserver);
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
        METHOD_GET_TEMPLATE_TERM,
        METHOD_CREATE_TEMPLATE_TERM,
        METHOD_DELETE_TEMPLATE_TERM);
  }

}
