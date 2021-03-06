// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/topicParameters.proto

package enn.monitor.config.business.topic.parameters;

/**
 * Protobuf type {@code EnnMonitorConfigBusinessTopicDeleteRequest}
 */
public  final class EnnMonitorConfigBusinessTopicDeleteRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:EnnMonitorConfigBusinessTopicDeleteRequest)
    EnnMonitorConfigBusinessTopicDeleteRequestOrBuilder {
  // Use EnnMonitorConfigBusinessTopicDeleteRequest.newBuilder() to construct.
  private EnnMonitorConfigBusinessTopicDeleteRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EnnMonitorConfigBusinessTopicDeleteRequest() {
    id_ = 0L;
    source_ = "";
    topic_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private EnnMonitorConfigBusinessTopicDeleteRequest(
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

            id_ = input.readUInt64();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            source_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            topic_ = s;
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
    return enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicParameters.internal_static_EnnMonitorConfigBusinessTopicDeleteRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicParameters.internal_static_EnnMonitorConfigBusinessTopicDeleteRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest.class, enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private long id_;
  /**
   * <code>optional uint64 id = 1;</code>
   */
  public long getId() {
    return id_;
  }

  public static final int SOURCE_FIELD_NUMBER = 2;
  private volatile java.lang.Object source_;
  /**
   * <code>optional string source = 2;</code>
   */
  public java.lang.String getSource() {
    java.lang.Object ref = source_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      source_ = s;
      return s;
    }
  }
  /**
   * <code>optional string source = 2;</code>
   */
  public com.google.protobuf.ByteString
      getSourceBytes() {
    java.lang.Object ref = source_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      source_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TOPIC_FIELD_NUMBER = 3;
  private volatile java.lang.Object topic_;
  /**
   * <code>optional string topic = 3;</code>
   */
  public java.lang.String getTopic() {
    java.lang.Object ref = topic_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      topic_ = s;
      return s;
    }
  }
  /**
   * <code>optional string topic = 3;</code>
   */
  public com.google.protobuf.ByteString
      getTopicBytes() {
    java.lang.Object ref = topic_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      topic_ = b;
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
    if (id_ != 0L) {
      output.writeUInt64(1, id_);
    }
    if (!getSourceBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, source_);
    }
    if (!getTopicBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, topic_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(1, id_);
    }
    if (!getSourceBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, source_);
    }
    if (!getTopicBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, topic_);
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
    if (!(obj instanceof enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest)) {
      return super.equals(obj);
    }
    enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest other = (enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest) obj;

    boolean result = true;
    result = result && (getId()
        == other.getId());
    result = result && getSource()
        .equals(other.getSource());
    result = result && getTopic()
        .equals(other.getTopic());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getId());
    hash = (37 * hash) + SOURCE_FIELD_NUMBER;
    hash = (53 * hash) + getSource().hashCode();
    hash = (37 * hash) + TOPIC_FIELD_NUMBER;
    hash = (53 * hash) + getTopic().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest parseFrom(
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
  public static Builder newBuilder(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest prototype) {
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
   * Protobuf type {@code EnnMonitorConfigBusinessTopicDeleteRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:EnnMonitorConfigBusinessTopicDeleteRequest)
      enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicParameters.internal_static_EnnMonitorConfigBusinessTopicDeleteRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicParameters.internal_static_EnnMonitorConfigBusinessTopicDeleteRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest.class, enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest.Builder.class);
    }

    // Construct using enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest.newBuilder()
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
      id_ = 0L;

      source_ = "";

      topic_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicParameters.internal_static_EnnMonitorConfigBusinessTopicDeleteRequest_descriptor;
    }

    public enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest getDefaultInstanceForType() {
      return enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest.getDefaultInstance();
    }

    public enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest build() {
      enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest buildPartial() {
      enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest result = new enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest(this);
      result.id_ = id_;
      result.source_ = source_;
      result.topic_ = topic_;
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
      if (other instanceof enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest) {
        return mergeFrom((enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest other) {
      if (other == enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest.getDefaultInstance()) return this;
      if (other.getId() != 0L) {
        setId(other.getId());
      }
      if (!other.getSource().isEmpty()) {
        source_ = other.source_;
        onChanged();
      }
      if (!other.getTopic().isEmpty()) {
        topic_ = other.topic_;
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
      enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long id_ ;
    /**
     * <code>optional uint64 id = 1;</code>
     */
    public long getId() {
      return id_;
    }
    /**
     * <code>optional uint64 id = 1;</code>
     */
    public Builder setId(long value) {
      
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional uint64 id = 1;</code>
     */
    public Builder clearId() {
      
      id_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object source_ = "";
    /**
     * <code>optional string source = 2;</code>
     */
    public java.lang.String getSource() {
      java.lang.Object ref = source_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        source_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string source = 2;</code>
     */
    public com.google.protobuf.ByteString
        getSourceBytes() {
      java.lang.Object ref = source_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        source_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string source = 2;</code>
     */
    public Builder setSource(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      source_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string source = 2;</code>
     */
    public Builder clearSource() {
      
      source_ = getDefaultInstance().getSource();
      onChanged();
      return this;
    }
    /**
     * <code>optional string source = 2;</code>
     */
    public Builder setSourceBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      source_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object topic_ = "";
    /**
     * <code>optional string topic = 3;</code>
     */
    public java.lang.String getTopic() {
      java.lang.Object ref = topic_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        topic_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string topic = 3;</code>
     */
    public com.google.protobuf.ByteString
        getTopicBytes() {
      java.lang.Object ref = topic_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        topic_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string topic = 3;</code>
     */
    public Builder setTopic(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      topic_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string topic = 3;</code>
     */
    public Builder clearTopic() {
      
      topic_ = getDefaultInstance().getTopic();
      onChanged();
      return this;
    }
    /**
     * <code>optional string topic = 3;</code>
     */
    public Builder setTopicBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      topic_ = value;
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


    // @@protoc_insertion_point(builder_scope:EnnMonitorConfigBusinessTopicDeleteRequest)
  }

  // @@protoc_insertion_point(class_scope:EnnMonitorConfigBusinessTopicDeleteRequest)
  private static final enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest();
  }

  public static enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EnnMonitorConfigBusinessTopicDeleteRequest>
      PARSER = new com.google.protobuf.AbstractParser<EnnMonitorConfigBusinessTopicDeleteRequest>() {
    public EnnMonitorConfigBusinessTopicDeleteRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new EnnMonitorConfigBusinessTopicDeleteRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EnnMonitorConfigBusinessTopicDeleteRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EnnMonitorConfigBusinessTopicDeleteRequest> getParserForType() {
    return PARSER;
  }

  public enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

