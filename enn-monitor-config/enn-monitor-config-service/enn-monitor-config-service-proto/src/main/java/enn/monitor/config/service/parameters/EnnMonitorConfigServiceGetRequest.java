// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/serviceParameters.proto

package enn.monitor.config.service.parameters;

/**
 * <pre>
 * get service
 * </pre>
 *
 * Protobuf type {@code EnnMonitorConfigServiceGetRequest}
 */
public  final class EnnMonitorConfigServiceGetRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:EnnMonitorConfigServiceGetRequest)
    EnnMonitorConfigServiceGetRequestOrBuilder {
  // Use EnnMonitorConfigServiceGetRequest.newBuilder() to construct.
  private EnnMonitorConfigServiceGetRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EnnMonitorConfigServiceGetRequest() {
    id_ = 0L;
    serviceName_ = "";
    belongToServiceLine_ = 0L;
    token_ = "";
    status_ = 0;
    owner_ = "";
    guest_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private EnnMonitorConfigServiceGetRequest(
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

            serviceName_ = s;
            break;
          }
          case 24: {

            belongToServiceLine_ = input.readUInt64();
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            token_ = s;
            break;
          }
          case 40: {
            int rawValue = input.readEnum();

            status_ = rawValue;
            break;
          }
          case 50: {
            java.lang.String s = input.readStringRequireUtf8();

            owner_ = s;
            break;
          }
          case 58: {
            java.lang.String s = input.readStringRequireUtf8();

            guest_ = s;
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
    return enn.monitor.config.service.parameters.EnnMonitorConfigServiceParameters.internal_static_EnnMonitorConfigServiceGetRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return enn.monitor.config.service.parameters.EnnMonitorConfigServiceParameters.internal_static_EnnMonitorConfigServiceGetRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest.class, enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private long id_;
  /**
   * <code>optional uint64 id = 1;</code>
   */
  public long getId() {
    return id_;
  }

  public static final int SERVICENAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object serviceName_;
  /**
   * <code>optional string serviceName = 2;</code>
   */
  public java.lang.String getServiceName() {
    java.lang.Object ref = serviceName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      serviceName_ = s;
      return s;
    }
  }
  /**
   * <code>optional string serviceName = 2;</code>
   */
  public com.google.protobuf.ByteString
      getServiceNameBytes() {
    java.lang.Object ref = serviceName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      serviceName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int BELONGTOSERVICELINE_FIELD_NUMBER = 3;
  private long belongToServiceLine_;
  /**
   * <code>optional uint64 belongToServiceLine = 3;</code>
   */
  public long getBelongToServiceLine() {
    return belongToServiceLine_;
  }

  public static final int TOKEN_FIELD_NUMBER = 4;
  private volatile java.lang.Object token_;
  /**
   * <code>optional string token = 4;</code>
   */
  public java.lang.String getToken() {
    java.lang.Object ref = token_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      token_ = s;
      return s;
    }
  }
  /**
   * <code>optional string token = 4;</code>
   */
  public com.google.protobuf.ByteString
      getTokenBytes() {
    java.lang.Object ref = token_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      token_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int STATUS_FIELD_NUMBER = 5;
  private int status_;
  /**
   * <code>optional .EnnMonitorConfigServiceStatus status = 5;</code>
   */
  public int getStatusValue() {
    return status_;
  }
  /**
   * <code>optional .EnnMonitorConfigServiceStatus status = 5;</code>
   */
  public enn.monitor.config.service.parameters.EnnMonitorConfigServiceStatus getStatus() {
    enn.monitor.config.service.parameters.EnnMonitorConfigServiceStatus result = enn.monitor.config.service.parameters.EnnMonitorConfigServiceStatus.valueOf(status_);
    return result == null ? enn.monitor.config.service.parameters.EnnMonitorConfigServiceStatus.UNRECOGNIZED : result;
  }

  public static final int OWNER_FIELD_NUMBER = 6;
  private volatile java.lang.Object owner_;
  /**
   * <code>optional string owner = 6;</code>
   */
  public java.lang.String getOwner() {
    java.lang.Object ref = owner_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      owner_ = s;
      return s;
    }
  }
  /**
   * <code>optional string owner = 6;</code>
   */
  public com.google.protobuf.ByteString
      getOwnerBytes() {
    java.lang.Object ref = owner_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      owner_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int GUEST_FIELD_NUMBER = 7;
  private volatile java.lang.Object guest_;
  /**
   * <code>optional string guest = 7;</code>
   */
  public java.lang.String getGuest() {
    java.lang.Object ref = guest_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      guest_ = s;
      return s;
    }
  }
  /**
   * <code>optional string guest = 7;</code>
   */
  public com.google.protobuf.ByteString
      getGuestBytes() {
    java.lang.Object ref = guest_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      guest_ = b;
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
    if (!getServiceNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, serviceName_);
    }
    if (belongToServiceLine_ != 0L) {
      output.writeUInt64(3, belongToServiceLine_);
    }
    if (!getTokenBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, token_);
    }
    if (status_ != enn.monitor.config.service.parameters.EnnMonitorConfigServiceStatus.ServiceDefault.getNumber()) {
      output.writeEnum(5, status_);
    }
    if (!getOwnerBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, owner_);
    }
    if (!getGuestBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 7, guest_);
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
    if (!getServiceNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, serviceName_);
    }
    if (belongToServiceLine_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(3, belongToServiceLine_);
    }
    if (!getTokenBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, token_);
    }
    if (status_ != enn.monitor.config.service.parameters.EnnMonitorConfigServiceStatus.ServiceDefault.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(5, status_);
    }
    if (!getOwnerBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, owner_);
    }
    if (!getGuestBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(7, guest_);
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
    if (!(obj instanceof enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest)) {
      return super.equals(obj);
    }
    enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest other = (enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest) obj;

    boolean result = true;
    result = result && (getId()
        == other.getId());
    result = result && getServiceName()
        .equals(other.getServiceName());
    result = result && (getBelongToServiceLine()
        == other.getBelongToServiceLine());
    result = result && getToken()
        .equals(other.getToken());
    result = result && status_ == other.status_;
    result = result && getOwner()
        .equals(other.getOwner());
    result = result && getGuest()
        .equals(other.getGuest());
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
    hash = (37 * hash) + SERVICENAME_FIELD_NUMBER;
    hash = (53 * hash) + getServiceName().hashCode();
    hash = (37 * hash) + BELONGTOSERVICELINE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getBelongToServiceLine());
    hash = (37 * hash) + TOKEN_FIELD_NUMBER;
    hash = (53 * hash) + getToken().hashCode();
    hash = (37 * hash) + STATUS_FIELD_NUMBER;
    hash = (53 * hash) + status_;
    hash = (37 * hash) + OWNER_FIELD_NUMBER;
    hash = (53 * hash) + getOwner().hashCode();
    hash = (37 * hash) + GUEST_FIELD_NUMBER;
    hash = (53 * hash) + getGuest().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest parseFrom(
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
  public static Builder newBuilder(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest prototype) {
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
   * get service
   * </pre>
   *
   * Protobuf type {@code EnnMonitorConfigServiceGetRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:EnnMonitorConfigServiceGetRequest)
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return enn.monitor.config.service.parameters.EnnMonitorConfigServiceParameters.internal_static_EnnMonitorConfigServiceGetRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return enn.monitor.config.service.parameters.EnnMonitorConfigServiceParameters.internal_static_EnnMonitorConfigServiceGetRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest.class, enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest.Builder.class);
    }

    // Construct using enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest.newBuilder()
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

      serviceName_ = "";

      belongToServiceLine_ = 0L;

      token_ = "";

      status_ = 0;

      owner_ = "";

      guest_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return enn.monitor.config.service.parameters.EnnMonitorConfigServiceParameters.internal_static_EnnMonitorConfigServiceGetRequest_descriptor;
    }

    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest getDefaultInstanceForType() {
      return enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest.getDefaultInstance();
    }

    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest build() {
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest buildPartial() {
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest result = new enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest(this);
      result.id_ = id_;
      result.serviceName_ = serviceName_;
      result.belongToServiceLine_ = belongToServiceLine_;
      result.token_ = token_;
      result.status_ = status_;
      result.owner_ = owner_;
      result.guest_ = guest_;
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
      if (other instanceof enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest) {
        return mergeFrom((enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest other) {
      if (other == enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest.getDefaultInstance()) return this;
      if (other.getId() != 0L) {
        setId(other.getId());
      }
      if (!other.getServiceName().isEmpty()) {
        serviceName_ = other.serviceName_;
        onChanged();
      }
      if (other.getBelongToServiceLine() != 0L) {
        setBelongToServiceLine(other.getBelongToServiceLine());
      }
      if (!other.getToken().isEmpty()) {
        token_ = other.token_;
        onChanged();
      }
      if (other.status_ != 0) {
        setStatusValue(other.getStatusValue());
      }
      if (!other.getOwner().isEmpty()) {
        owner_ = other.owner_;
        onChanged();
      }
      if (!other.getGuest().isEmpty()) {
        guest_ = other.guest_;
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
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest) e.getUnfinishedMessage();
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

    private java.lang.Object serviceName_ = "";
    /**
     * <code>optional string serviceName = 2;</code>
     */
    public java.lang.String getServiceName() {
      java.lang.Object ref = serviceName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        serviceName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string serviceName = 2;</code>
     */
    public com.google.protobuf.ByteString
        getServiceNameBytes() {
      java.lang.Object ref = serviceName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        serviceName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string serviceName = 2;</code>
     */
    public Builder setServiceName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      serviceName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string serviceName = 2;</code>
     */
    public Builder clearServiceName() {
      
      serviceName_ = getDefaultInstance().getServiceName();
      onChanged();
      return this;
    }
    /**
     * <code>optional string serviceName = 2;</code>
     */
    public Builder setServiceNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      serviceName_ = value;
      onChanged();
      return this;
    }

    private long belongToServiceLine_ ;
    /**
     * <code>optional uint64 belongToServiceLine = 3;</code>
     */
    public long getBelongToServiceLine() {
      return belongToServiceLine_;
    }
    /**
     * <code>optional uint64 belongToServiceLine = 3;</code>
     */
    public Builder setBelongToServiceLine(long value) {
      
      belongToServiceLine_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional uint64 belongToServiceLine = 3;</code>
     */
    public Builder clearBelongToServiceLine() {
      
      belongToServiceLine_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object token_ = "";
    /**
     * <code>optional string token = 4;</code>
     */
    public java.lang.String getToken() {
      java.lang.Object ref = token_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        token_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string token = 4;</code>
     */
    public com.google.protobuf.ByteString
        getTokenBytes() {
      java.lang.Object ref = token_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        token_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string token = 4;</code>
     */
    public Builder setToken(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      token_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string token = 4;</code>
     */
    public Builder clearToken() {
      
      token_ = getDefaultInstance().getToken();
      onChanged();
      return this;
    }
    /**
     * <code>optional string token = 4;</code>
     */
    public Builder setTokenBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      token_ = value;
      onChanged();
      return this;
    }

    private int status_ = 0;
    /**
     * <code>optional .EnnMonitorConfigServiceStatus status = 5;</code>
     */
    public int getStatusValue() {
      return status_;
    }
    /**
     * <code>optional .EnnMonitorConfigServiceStatus status = 5;</code>
     */
    public Builder setStatusValue(int value) {
      status_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional .EnnMonitorConfigServiceStatus status = 5;</code>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceStatus getStatus() {
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceStatus result = enn.monitor.config.service.parameters.EnnMonitorConfigServiceStatus.valueOf(status_);
      return result == null ? enn.monitor.config.service.parameters.EnnMonitorConfigServiceStatus.UNRECOGNIZED : result;
    }
    /**
     * <code>optional .EnnMonitorConfigServiceStatus status = 5;</code>
     */
    public Builder setStatus(enn.monitor.config.service.parameters.EnnMonitorConfigServiceStatus value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      status_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>optional .EnnMonitorConfigServiceStatus status = 5;</code>
     */
    public Builder clearStatus() {
      
      status_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object owner_ = "";
    /**
     * <code>optional string owner = 6;</code>
     */
    public java.lang.String getOwner() {
      java.lang.Object ref = owner_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        owner_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string owner = 6;</code>
     */
    public com.google.protobuf.ByteString
        getOwnerBytes() {
      java.lang.Object ref = owner_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        owner_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string owner = 6;</code>
     */
    public Builder setOwner(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      owner_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string owner = 6;</code>
     */
    public Builder clearOwner() {
      
      owner_ = getDefaultInstance().getOwner();
      onChanged();
      return this;
    }
    /**
     * <code>optional string owner = 6;</code>
     */
    public Builder setOwnerBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      owner_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object guest_ = "";
    /**
     * <code>optional string guest = 7;</code>
     */
    public java.lang.String getGuest() {
      java.lang.Object ref = guest_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        guest_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string guest = 7;</code>
     */
    public com.google.protobuf.ByteString
        getGuestBytes() {
      java.lang.Object ref = guest_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        guest_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string guest = 7;</code>
     */
    public Builder setGuest(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      guest_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string guest = 7;</code>
     */
    public Builder clearGuest() {
      
      guest_ = getDefaultInstance().getGuest();
      onChanged();
      return this;
    }
    /**
     * <code>optional string guest = 7;</code>
     */
    public Builder setGuestBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      guest_ = value;
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


    // @@protoc_insertion_point(builder_scope:EnnMonitorConfigServiceGetRequest)
  }

  // @@protoc_insertion_point(class_scope:EnnMonitorConfigServiceGetRequest)
  private static final enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest();
  }

  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EnnMonitorConfigServiceGetRequest>
      PARSER = new com.google.protobuf.AbstractParser<EnnMonitorConfigServiceGetRequest>() {
    public EnnMonitorConfigServiceGetRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new EnnMonitorConfigServiceGetRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EnnMonitorConfigServiceGetRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EnnMonitorConfigServiceGetRequest> getParserForType() {
    return PARSER;
  }

  public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

