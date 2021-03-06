// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/logConfigCacheParameter.proto

package enn.monitor.log.config.cache.parameters;

/**
 * Protobuf type {@code EnnMonitorLogConfigCacheTagIdResponse}
 */
public  final class EnnMonitorLogConfigCacheTagIdResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:EnnMonitorLogConfigCacheTagIdResponse)
    EnnMonitorLogConfigCacheTagIdResponseOrBuilder {
  // Use EnnMonitorLogConfigCacheTagIdResponse.newBuilder() to construct.
  private EnnMonitorLogConfigCacheTagIdResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EnnMonitorLogConfigCacheTagIdResponse() {
    isSuccess_ = false;
    tagId_ = 0L;
    error_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private EnnMonitorLogConfigCacheTagIdResponse(
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
          case 16: {

            tagId_ = input.readUInt64();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            error_ = s;
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
    return enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheParameters.internal_static_EnnMonitorLogConfigCacheTagIdResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheParameters.internal_static_EnnMonitorLogConfigCacheTagIdResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse.class, enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse.Builder.class);
  }

  public static final int ISSUCCESS_FIELD_NUMBER = 1;
  private boolean isSuccess_;
  /**
   * <code>optional bool isSuccess = 1;</code>
   */
  public boolean getIsSuccess() {
    return isSuccess_;
  }

  public static final int TAGID_FIELD_NUMBER = 2;
  private long tagId_;
  /**
   * <code>optional uint64 tagId = 2;</code>
   */
  public long getTagId() {
    return tagId_;
  }

  public static final int ERROR_FIELD_NUMBER = 3;
  private volatile java.lang.Object error_;
  /**
   * <code>optional string error = 3;</code>
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
   * <code>optional string error = 3;</code>
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
    if (tagId_ != 0L) {
      output.writeUInt64(2, tagId_);
    }
    if (!getErrorBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, error_);
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
    if (tagId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(2, tagId_);
    }
    if (!getErrorBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, error_);
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
    if (!(obj instanceof enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse)) {
      return super.equals(obj);
    }
    enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse other = (enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse) obj;

    boolean result = true;
    result = result && (getIsSuccess()
        == other.getIsSuccess());
    result = result && (getTagId()
        == other.getTagId());
    result = result && getError()
        .equals(other.getError());
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
    hash = (37 * hash) + TAGID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTagId());
    hash = (37 * hash) + ERROR_FIELD_NUMBER;
    hash = (53 * hash) + getError().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse parseFrom(
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
  public static Builder newBuilder(enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse prototype) {
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
   * Protobuf type {@code EnnMonitorLogConfigCacheTagIdResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:EnnMonitorLogConfigCacheTagIdResponse)
      enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheParameters.internal_static_EnnMonitorLogConfigCacheTagIdResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheParameters.internal_static_EnnMonitorLogConfigCacheTagIdResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse.class, enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse.Builder.class);
    }

    // Construct using enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse.newBuilder()
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

      tagId_ = 0L;

      error_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheParameters.internal_static_EnnMonitorLogConfigCacheTagIdResponse_descriptor;
    }

    public enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse getDefaultInstanceForType() {
      return enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse.getDefaultInstance();
    }

    public enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse build() {
      enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse buildPartial() {
      enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse result = new enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse(this);
      result.isSuccess_ = isSuccess_;
      result.tagId_ = tagId_;
      result.error_ = error_;
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
      if (other instanceof enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse) {
        return mergeFrom((enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse other) {
      if (other == enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse.getDefaultInstance()) return this;
      if (other.getIsSuccess() != false) {
        setIsSuccess(other.getIsSuccess());
      }
      if (other.getTagId() != 0L) {
        setTagId(other.getTagId());
      }
      if (!other.getError().isEmpty()) {
        error_ = other.error_;
        onChanged();
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
      enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse) e.getUnfinishedMessage();
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

    private long tagId_ ;
    /**
     * <code>optional uint64 tagId = 2;</code>
     */
    public long getTagId() {
      return tagId_;
    }
    /**
     * <code>optional uint64 tagId = 2;</code>
     */
    public Builder setTagId(long value) {
      
      tagId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional uint64 tagId = 2;</code>
     */
    public Builder clearTagId() {
      
      tagId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object error_ = "";
    /**
     * <code>optional string error = 3;</code>
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
     * <code>optional string error = 3;</code>
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
     * <code>optional string error = 3;</code>
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
     * <code>optional string error = 3;</code>
     */
    public Builder clearError() {
      
      error_ = getDefaultInstance().getError();
      onChanged();
      return this;
    }
    /**
     * <code>optional string error = 3;</code>
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
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:EnnMonitorLogConfigCacheTagIdResponse)
  }

  // @@protoc_insertion_point(class_scope:EnnMonitorLogConfigCacheTagIdResponse)
  private static final enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse();
  }

  public static enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EnnMonitorLogConfigCacheTagIdResponse>
      PARSER = new com.google.protobuf.AbstractParser<EnnMonitorLogConfigCacheTagIdResponse>() {
    public EnnMonitorLogConfigCacheTagIdResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new EnnMonitorLogConfigCacheTagIdResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EnnMonitorLogConfigCacheTagIdResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EnnMonitorLogConfigCacheTagIdResponse> getParserForType() {
    return PARSER;
  }

  public enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

