// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/serviceParameters.proto

package enn.monitor.config.service.parameters;

/**
 * Protobuf type {@code EnnMonitorConfigServiceGetValidDataListResponse}
 */
public  final class EnnMonitorConfigServiceGetValidDataListResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:EnnMonitorConfigServiceGetValidDataListResponse)
    EnnMonitorConfigServiceGetValidDataListResponseOrBuilder {
  // Use EnnMonitorConfigServiceGetValidDataListResponse.newBuilder() to construct.
  private EnnMonitorConfigServiceGetValidDataListResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EnnMonitorConfigServiceGetValidDataListResponse() {
    isSuccess_ = false;
    error_ = "";
    serviceTableList_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private EnnMonitorConfigServiceGetValidDataListResponse(
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
          case 26: {
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              serviceTableList_ = new java.util.ArrayList<enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable>();
              mutable_bitField0_ |= 0x00000004;
            }
            serviceTableList_.add(
                input.readMessage(enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
        serviceTableList_ = java.util.Collections.unmodifiableList(serviceTableList_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return enn.monitor.config.service.parameters.EnnMonitorConfigServiceParameters.internal_static_EnnMonitorConfigServiceGetValidDataListResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return enn.monitor.config.service.parameters.EnnMonitorConfigServiceParameters.internal_static_EnnMonitorConfigServiceGetValidDataListResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse.class, enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse.Builder.class);
  }

  private int bitField0_;
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

  public static final int SERVICETABLELIST_FIELD_NUMBER = 3;
  private java.util.List<enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable> serviceTableList_;
  /**
   * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
   */
  public java.util.List<enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable> getServiceTableListList() {
    return serviceTableList_;
  }
  /**
   * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
   */
  public java.util.List<? extends enn.monitor.config.service.parameters.EnnMonitorConfigServiceTableOrBuilder> 
      getServiceTableListOrBuilderList() {
    return serviceTableList_;
  }
  /**
   * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
   */
  public int getServiceTableListCount() {
    return serviceTableList_.size();
  }
  /**
   * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
   */
  public enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable getServiceTableList(int index) {
    return serviceTableList_.get(index);
  }
  /**
   * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
   */
  public enn.monitor.config.service.parameters.EnnMonitorConfigServiceTableOrBuilder getServiceTableListOrBuilder(
      int index) {
    return serviceTableList_.get(index);
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
    for (int i = 0; i < serviceTableList_.size(); i++) {
      output.writeMessage(3, serviceTableList_.get(i));
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
    for (int i = 0; i < serviceTableList_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, serviceTableList_.get(i));
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
    if (!(obj instanceof enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse)) {
      return super.equals(obj);
    }
    enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse other = (enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse) obj;

    boolean result = true;
    result = result && (getIsSuccess()
        == other.getIsSuccess());
    result = result && getError()
        .equals(other.getError());
    result = result && getServiceTableListList()
        .equals(other.getServiceTableListList());
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
    if (getServiceTableListCount() > 0) {
      hash = (37 * hash) + SERVICETABLELIST_FIELD_NUMBER;
      hash = (53 * hash) + getServiceTableListList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse parseFrom(
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
  public static Builder newBuilder(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse prototype) {
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
   * Protobuf type {@code EnnMonitorConfigServiceGetValidDataListResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:EnnMonitorConfigServiceGetValidDataListResponse)
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return enn.monitor.config.service.parameters.EnnMonitorConfigServiceParameters.internal_static_EnnMonitorConfigServiceGetValidDataListResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return enn.monitor.config.service.parameters.EnnMonitorConfigServiceParameters.internal_static_EnnMonitorConfigServiceGetValidDataListResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse.class, enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse.Builder.class);
    }

    // Construct using enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse.newBuilder()
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
        getServiceTableListFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      isSuccess_ = false;

      error_ = "";

      if (serviceTableListBuilder_ == null) {
        serviceTableList_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
      } else {
        serviceTableListBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return enn.monitor.config.service.parameters.EnnMonitorConfigServiceParameters.internal_static_EnnMonitorConfigServiceGetValidDataListResponse_descriptor;
    }

    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse getDefaultInstanceForType() {
      return enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse.getDefaultInstance();
    }

    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse build() {
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse buildPartial() {
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse result = new enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.isSuccess_ = isSuccess_;
      result.error_ = error_;
      if (serviceTableListBuilder_ == null) {
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          serviceTableList_ = java.util.Collections.unmodifiableList(serviceTableList_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.serviceTableList_ = serviceTableList_;
      } else {
        result.serviceTableList_ = serviceTableListBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
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
      if (other instanceof enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse) {
        return mergeFrom((enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse other) {
      if (other == enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse.getDefaultInstance()) return this;
      if (other.getIsSuccess() != false) {
        setIsSuccess(other.getIsSuccess());
      }
      if (!other.getError().isEmpty()) {
        error_ = other.error_;
        onChanged();
      }
      if (serviceTableListBuilder_ == null) {
        if (!other.serviceTableList_.isEmpty()) {
          if (serviceTableList_.isEmpty()) {
            serviceTableList_ = other.serviceTableList_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureServiceTableListIsMutable();
            serviceTableList_.addAll(other.serviceTableList_);
          }
          onChanged();
        }
      } else {
        if (!other.serviceTableList_.isEmpty()) {
          if (serviceTableListBuilder_.isEmpty()) {
            serviceTableListBuilder_.dispose();
            serviceTableListBuilder_ = null;
            serviceTableList_ = other.serviceTableList_;
            bitField0_ = (bitField0_ & ~0x00000004);
            serviceTableListBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getServiceTableListFieldBuilder() : null;
          } else {
            serviceTableListBuilder_.addAllMessages(other.serviceTableList_);
          }
        }
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
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

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

    private java.util.List<enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable> serviceTableList_ =
      java.util.Collections.emptyList();
    private void ensureServiceTableListIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        serviceTableList_ = new java.util.ArrayList<enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable>(serviceTableList_);
        bitField0_ |= 0x00000004;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable, enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable.Builder, enn.monitor.config.service.parameters.EnnMonitorConfigServiceTableOrBuilder> serviceTableListBuilder_;

    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public java.util.List<enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable> getServiceTableListList() {
      if (serviceTableListBuilder_ == null) {
        return java.util.Collections.unmodifiableList(serviceTableList_);
      } else {
        return serviceTableListBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public int getServiceTableListCount() {
      if (serviceTableListBuilder_ == null) {
        return serviceTableList_.size();
      } else {
        return serviceTableListBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable getServiceTableList(int index) {
      if (serviceTableListBuilder_ == null) {
        return serviceTableList_.get(index);
      } else {
        return serviceTableListBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public Builder setServiceTableList(
        int index, enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable value) {
      if (serviceTableListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureServiceTableListIsMutable();
        serviceTableList_.set(index, value);
        onChanged();
      } else {
        serviceTableListBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public Builder setServiceTableList(
        int index, enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable.Builder builderForValue) {
      if (serviceTableListBuilder_ == null) {
        ensureServiceTableListIsMutable();
        serviceTableList_.set(index, builderForValue.build());
        onChanged();
      } else {
        serviceTableListBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public Builder addServiceTableList(enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable value) {
      if (serviceTableListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureServiceTableListIsMutable();
        serviceTableList_.add(value);
        onChanged();
      } else {
        serviceTableListBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public Builder addServiceTableList(
        int index, enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable value) {
      if (serviceTableListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureServiceTableListIsMutable();
        serviceTableList_.add(index, value);
        onChanged();
      } else {
        serviceTableListBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public Builder addServiceTableList(
        enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable.Builder builderForValue) {
      if (serviceTableListBuilder_ == null) {
        ensureServiceTableListIsMutable();
        serviceTableList_.add(builderForValue.build());
        onChanged();
      } else {
        serviceTableListBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public Builder addServiceTableList(
        int index, enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable.Builder builderForValue) {
      if (serviceTableListBuilder_ == null) {
        ensureServiceTableListIsMutable();
        serviceTableList_.add(index, builderForValue.build());
        onChanged();
      } else {
        serviceTableListBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public Builder addAllServiceTableList(
        java.lang.Iterable<? extends enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable> values) {
      if (serviceTableListBuilder_ == null) {
        ensureServiceTableListIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, serviceTableList_);
        onChanged();
      } else {
        serviceTableListBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public Builder clearServiceTableList() {
      if (serviceTableListBuilder_ == null) {
        serviceTableList_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
      } else {
        serviceTableListBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public Builder removeServiceTableList(int index) {
      if (serviceTableListBuilder_ == null) {
        ensureServiceTableListIsMutable();
        serviceTableList_.remove(index);
        onChanged();
      } else {
        serviceTableListBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable.Builder getServiceTableListBuilder(
        int index) {
      return getServiceTableListFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceTableOrBuilder getServiceTableListOrBuilder(
        int index) {
      if (serviceTableListBuilder_ == null) {
        return serviceTableList_.get(index);  } else {
        return serviceTableListBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public java.util.List<? extends enn.monitor.config.service.parameters.EnnMonitorConfigServiceTableOrBuilder> 
         getServiceTableListOrBuilderList() {
      if (serviceTableListBuilder_ != null) {
        return serviceTableListBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(serviceTableList_);
      }
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable.Builder addServiceTableListBuilder() {
      return getServiceTableListFieldBuilder().addBuilder(
          enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable.getDefaultInstance());
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable.Builder addServiceTableListBuilder(
        int index) {
      return getServiceTableListFieldBuilder().addBuilder(
          index, enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable.getDefaultInstance());
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceTable serviceTableList = 3;</code>
     */
    public java.util.List<enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable.Builder> 
         getServiceTableListBuilderList() {
      return getServiceTableListFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable, enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable.Builder, enn.monitor.config.service.parameters.EnnMonitorConfigServiceTableOrBuilder> 
        getServiceTableListFieldBuilder() {
      if (serviceTableListBuilder_ == null) {
        serviceTableListBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable, enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable.Builder, enn.monitor.config.service.parameters.EnnMonitorConfigServiceTableOrBuilder>(
                serviceTableList_,
                ((bitField0_ & 0x00000004) == 0x00000004),
                getParentForChildren(),
                isClean());
        serviceTableList_ = null;
      }
      return serviceTableListBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:EnnMonitorConfigServiceGetValidDataListResponse)
  }

  // @@protoc_insertion_point(class_scope:EnnMonitorConfigServiceGetValidDataListResponse)
  private static final enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse();
  }

  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EnnMonitorConfigServiceGetValidDataListResponse>
      PARSER = new com.google.protobuf.AbstractParser<EnnMonitorConfigServiceGetValidDataListResponse>() {
    public EnnMonitorConfigServiceGetValidDataListResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new EnnMonitorConfigServiceGetValidDataListResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EnnMonitorConfigServiceGetValidDataListResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EnnMonitorConfigServiceGetValidDataListResponse> getParserForType() {
    return PARSER;
  }

  public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
