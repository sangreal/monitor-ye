// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/topicParameters.proto

package enn.monitor.config.business.topic.parameters;

/**
 * Protobuf type {@code EnnMonitorConfigBusinessTopicGetMaxDeletedResponse}
 */
public  final class EnnMonitorConfigBusinessTopicGetMaxDeletedResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:EnnMonitorConfigBusinessTopicGetMaxDeletedResponse)
    EnnMonitorConfigBusinessTopicGetMaxDeletedResponseOrBuilder {
  // Use EnnMonitorConfigBusinessTopicGetMaxDeletedResponse.newBuilder() to construct.
  private EnnMonitorConfigBusinessTopicGetMaxDeletedResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EnnMonitorConfigBusinessTopicGetMaxDeletedResponse() {
    isSuccess_ = false;
    error_ = "";
    seqNo_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private EnnMonitorConfigBusinessTopicGetMaxDeletedResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
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
          case 8: {

            isSuccess_ = input.readBool();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            error_ = s;
            break;
          }
          case 24: {

            seqNo_ = input.readUInt64();
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
    return enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicParameters.internal_static_EnnMonitorConfigBusinessTopicGetMaxDeletedResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicParameters.internal_static_EnnMonitorConfigBusinessTopicGetMaxDeletedResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse.class, enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse.Builder.class);
  }

  public static final int ISSUCCESS_FIELD_NUMBER = 1;
  private boolean isSuccess_;
  /**
   * <code>optional bool isSuccess = 1;</code>
   */
  public boolean getIsSuccess() {
    return isSuccess_;
  }

  public static final int ERROR_FIELD_NUMBER = 2;
  private volatile java.lang.Object error_;
  /**
   * <code>optional string error = 2;</code>
   */
  public java.lang.String getError() {
    java.lang.Object ref = error_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      error_ = s;
      return s;
    }
  }
  /**
   * <code>optional string error = 2;</code>
   */
  public com.google.protobuf.ByteString
      getErrorBytes() {
    java.lang.Object ref = error_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      error_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SEQNO_FIELD_NUMBER = 3;
  private long seqNo_;
  /**
   * <code>optional uint64 seqNo = 3;</code>
   */
  public long getSeqNo() {
    return seqNo_;
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
    if (isSuccess_ != false) {
      output.writeBool(1, isSuccess_);
    }
    if (!getErrorBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, error_);
    }
    if (seqNo_ != 0L) {
      output.writeUInt64(3, seqNo_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (isSuccess_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, isSuccess_);
    }
    if (!getErrorBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, error_);
    }
    if (seqNo_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(3, seqNo_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse)) {
      return super.equals(obj);
    }
    enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse other = (enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse) obj;

    boolean result = true;
    result = result && (getIsSuccess()
        == other.getIsSuccess());
    result = result && getError()
        .equals(other.getError());
    result = result && (getSeqNo()
        == other.getSeqNo());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + ISSUCCESS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getIsSuccess());
    hash = (37 * hash) + ERROR_FIELD_NUMBER;
    hash = (53 * hash) + getError().hashCode();
    hash = (37 * hash) + SEQNO_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getSeqNo());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse parseFrom(
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
  public static Builder newBuilder(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse prototype) {
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
   * Protobuf type {@code EnnMonitorConfigBusinessTopicGetMaxDeletedResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:EnnMonitorConfigBusinessTopicGetMaxDeletedResponse)
      enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicParameters.internal_static_EnnMonitorConfigBusinessTopicGetMaxDeletedResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicParameters.internal_static_EnnMonitorConfigBusinessTopicGetMaxDeletedResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse.class, enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse.Builder.class);
    }

    // Construct using enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse.newBuilder()
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
      isSuccess_ = false;

      error_ = "";

      seqNo_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicParameters.internal_static_EnnMonitorConfigBusinessTopicGetMaxDeletedResponse_descriptor;
    }

    public enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse getDefaultInstanceForType() {
      return enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse.getDefaultInstance();
    }

    public enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse build() {
      enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse buildPartial() {
      enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse result = new enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse(this);
      result.isSuccess_ = isSuccess_;
      result.error_ = error_;
      result.seqNo_ = seqNo_;
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
      if (other instanceof enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse) {
        return mergeFrom((enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse other) {
      if (other == enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse.getDefaultInstance()) return this;
      if (other.getIsSuccess() != false) {
        setIsSuccess(other.getIsSuccess());
      }
      if (!other.getError().isEmpty()) {
        error_ = other.error_;
        onChanged();
      }
      if (other.getSeqNo() != 0L) {
        setSeqNo(other.getSeqNo());
      }
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
      enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private boolean isSuccess_ ;
    /**
     * <code>optional bool isSuccess = 1;</code>
     */
    public boolean getIsSuccess() {
      return isSuccess_;
    }
    /**
     * <code>optional bool isSuccess = 1;</code>
     */
    public Builder setIsSuccess(boolean value) {
      
      isSuccess_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional bool isSuccess = 1;</code>
     */
    public Builder clearIsSuccess() {
      
      isSuccess_ = false;
      onChanged();
      return this;
    }

    private java.lang.Object error_ = "";
    /**
     * <code>optional string error = 2;</code>
     */
    public java.lang.String getError() {
      java.lang.Object ref = error_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        error_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string error = 2;</code>
     */
    public com.google.protobuf.ByteString
        getErrorBytes() {
      java.lang.Object ref = error_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        error_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string error = 2;</code>
     */
    public Builder setError(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      error_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string error = 2;</code>
     */
    public Builder clearError() {
      
      error_ = getDefaultInstance().getError();
      onChanged();
      return this;
    }
    /**
     * <code>optional string error = 2;</code>
     */
    public Builder setErrorBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      error_ = value;
      onChanged();
      return this;
    }

    private long seqNo_ ;
    /**
     * <code>optional uint64 seqNo = 3;</code>
     */
    public long getSeqNo() {
      return seqNo_;
    }
    /**
     * <code>optional uint64 seqNo = 3;</code>
     */
    public Builder setSeqNo(long value) {
      
      seqNo_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional uint64 seqNo = 3;</code>
     */
    public Builder clearSeqNo() {
      
      seqNo_ = 0L;
      onChanged();
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


    // @@protoc_insertion_point(builder_scope:EnnMonitorConfigBusinessTopicGetMaxDeletedResponse)
  }

  // @@protoc_insertion_point(class_scope:EnnMonitorConfigBusinessTopicGetMaxDeletedResponse)
  private static final enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse();
  }

  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EnnMonitorConfigBusinessTopicGetMaxDeletedResponse>
      PARSER = new com.google.protobuf.AbstractParser<EnnMonitorConfigBusinessTopicGetMaxDeletedResponse>() {
    public EnnMonitorConfigBusinessTopicGetMaxDeletedResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new EnnMonitorConfigBusinessTopicGetMaxDeletedResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EnnMonitorConfigBusinessTopicGetMaxDeletedResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EnnMonitorConfigBusinessTopicGetMaxDeletedResponse> getParserForType() {
    return PARSER;
  }

  public enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

