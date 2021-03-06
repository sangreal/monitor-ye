// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/configEmailParameter.proto

package enn.monitor.alarm.config.email.parameter;

/**
 * <pre>
 * get max deleted seqno
 * </pre>
 *
 * Protobuf type {@code EnnMonitorAlarmConfigEmailMaxDeletedRequest}
 */
public  final class EnnMonitorAlarmConfigEmailMaxDeletedRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:EnnMonitorAlarmConfigEmailMaxDeletedRequest)
    EnnMonitorAlarmConfigEmailMaxDeletedRequestOrBuilder {
  // Use EnnMonitorAlarmConfigEmailMaxDeletedRequest.newBuilder() to construct.
  private EnnMonitorAlarmConfigEmailMaxDeletedRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EnnMonitorAlarmConfigEmailMaxDeletedRequest() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private EnnMonitorAlarmConfigEmailMaxDeletedRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailParameter.internal_static_EnnMonitorAlarmConfigEmailMaxDeletedRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailParameter.internal_static_EnnMonitorAlarmConfigEmailMaxDeletedRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest.class, enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest.Builder.class);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest)) {
      return super.equals(obj);
    }
    enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest other = (enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest) obj;

    boolean result = true;
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * get max deleted seqno
   * </pre>
   *
   * Protobuf type {@code EnnMonitorAlarmConfigEmailMaxDeletedRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:EnnMonitorAlarmConfigEmailMaxDeletedRequest)
      enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailParameter.internal_static_EnnMonitorAlarmConfigEmailMaxDeletedRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailParameter.internal_static_EnnMonitorAlarmConfigEmailMaxDeletedRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest.class, enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest.Builder.class);
    }

    // Construct using enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailParameter.internal_static_EnnMonitorAlarmConfigEmailMaxDeletedRequest_descriptor;
    }

    public enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest getDefaultInstanceForType() {
      return enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest.getDefaultInstance();
    }

    public enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest build() {
      enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest buildPartial() {
      enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest result = new enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest(this);
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest) {
        return mergeFrom((enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest other) {
      if (other == enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest.getDefaultInstance()) return this;
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:EnnMonitorAlarmConfigEmailMaxDeletedRequest)
  }

  // @@protoc_insertion_point(class_scope:EnnMonitorAlarmConfigEmailMaxDeletedRequest)
  private static final enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest();
  }

  public static enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EnnMonitorAlarmConfigEmailMaxDeletedRequest>
      PARSER = new com.google.protobuf.AbstractParser<EnnMonitorAlarmConfigEmailMaxDeletedRequest>() {
    public EnnMonitorAlarmConfigEmailMaxDeletedRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new EnnMonitorAlarmConfigEmailMaxDeletedRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EnnMonitorAlarmConfigEmailMaxDeletedRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EnnMonitorAlarmConfigEmailMaxDeletedRequest> getParserForType() {
    return PARSER;
  }

  public enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

