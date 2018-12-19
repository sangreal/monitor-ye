// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/serviceParameters.proto

package enn.monitor.config.service.parameters;

/**
 * Protobuf type {@code EnnMonitorConfigServiceGetDeletedDataListResponse}
 */
public  final class EnnMonitorConfigServiceGetDeletedDataListResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:EnnMonitorConfigServiceGetDeletedDataListResponse)
    EnnMonitorConfigServiceGetDeletedDataListResponseOrBuilder {
  // Use EnnMonitorConfigServiceGetDeletedDataListResponse.newBuilder() to construct.
  private EnnMonitorConfigServiceGetDeletedDataListResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EnnMonitorConfigServiceGetDeletedDataListResponse() {
    isSuccess_ = false;
    error_ = "";
    deletedDataList_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private EnnMonitorConfigServiceGetDeletedDataListResponse(
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
              deletedDataList_ = new java.util.ArrayList<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData>();
              mutable_bitField0_ |= 0x00000004;
            }
            deletedDataList_.add(
                input.readMessage(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData.parser(), extensionRegistry));
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
        deletedDataList_ = java.util.Collections.unmodifiableList(deletedDataList_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return enn.monitor.config.service.parameters.EnnMonitorConfigServiceParameters.internal_static_EnnMonitorConfigServiceGetDeletedDataListResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return enn.monitor.config.service.parameters.EnnMonitorConfigServiceParameters.internal_static_EnnMonitorConfigServiceGetDeletedDataListResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse.class, enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse.Builder.class);
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

  public static final int DELETEDDATALIST_FIELD_NUMBER = 3;
  private java.util.List<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData> deletedDataList_;
  /**
   * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
   */
  public java.util.List<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData> getDeletedDataListList() {
    return deletedDataList_;
  }
  /**
   * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
   */
  public java.util.List<? extends enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataOrBuilder> 
      getDeletedDataListOrBuilderList() {
    return deletedDataList_;
  }
  /**
   * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
   */
  public int getDeletedDataListCount() {
    return deletedDataList_.size();
  }
  /**
   * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
   */
  public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData getDeletedDataList(int index) {
    return deletedDataList_.get(index);
  }
  /**
   * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
   */
  public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataOrBuilder getDeletedDataListOrBuilder(
      int index) {
    return deletedDataList_.get(index);
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
    for (int i = 0; i < deletedDataList_.size(); i++) {
      output.writeMessage(3, deletedDataList_.get(i));
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
    for (int i = 0; i < deletedDataList_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, deletedDataList_.get(i));
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
    if (!(obj instanceof enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse)) {
      return super.equals(obj);
    }
    enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse other = (enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse) obj;

    boolean result = true;
    result = result && (getIsSuccess()
        == other.getIsSuccess());
    result = result && getError()
        .equals(other.getError());
    result = result && getDeletedDataListList()
        .equals(other.getDeletedDataListList());
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
    if (getDeletedDataListCount() > 0) {
      hash = (37 * hash) + DELETEDDATALIST_FIELD_NUMBER;
      hash = (53 * hash) + getDeletedDataListList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse parseFrom(
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
  public static Builder newBuilder(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse prototype) {
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
   * Protobuf type {@code EnnMonitorConfigServiceGetDeletedDataListResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:EnnMonitorConfigServiceGetDeletedDataListResponse)
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return enn.monitor.config.service.parameters.EnnMonitorConfigServiceParameters.internal_static_EnnMonitorConfigServiceGetDeletedDataListResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return enn.monitor.config.service.parameters.EnnMonitorConfigServiceParameters.internal_static_EnnMonitorConfigServiceGetDeletedDataListResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse.class, enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse.Builder.class);
    }

    // Construct using enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse.newBuilder()
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
        getDeletedDataListFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      isSuccess_ = false;

      error_ = "";

      if (deletedDataListBuilder_ == null) {
        deletedDataList_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
      } else {
        deletedDataListBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return enn.monitor.config.service.parameters.EnnMonitorConfigServiceParameters.internal_static_EnnMonitorConfigServiceGetDeletedDataListResponse_descriptor;
    }

    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse getDefaultInstanceForType() {
      return enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse.getDefaultInstance();
    }

    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse build() {
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse buildPartial() {
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse result = new enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.isSuccess_ = isSuccess_;
      result.error_ = error_;
      if (deletedDataListBuilder_ == null) {
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          deletedDataList_ = java.util.Collections.unmodifiableList(deletedDataList_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.deletedDataList_ = deletedDataList_;
      } else {
        result.deletedDataList_ = deletedDataListBuilder_.build();
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
      if (other instanceof enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse) {
        return mergeFrom((enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse other) {
      if (other == enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse.getDefaultInstance()) return this;
      if (other.getIsSuccess() != false) {
        setIsSuccess(other.getIsSuccess());
      }
      if (!other.getError().isEmpty()) {
        error_ = other.error_;
        onChanged();
      }
      if (deletedDataListBuilder_ == null) {
        if (!other.deletedDataList_.isEmpty()) {
          if (deletedDataList_.isEmpty()) {
            deletedDataList_ = other.deletedDataList_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureDeletedDataListIsMutable();
            deletedDataList_.addAll(other.deletedDataList_);
          }
          onChanged();
        }
      } else {
        if (!other.deletedDataList_.isEmpty()) {
          if (deletedDataListBuilder_.isEmpty()) {
            deletedDataListBuilder_.dispose();
            deletedDataListBuilder_ = null;
            deletedDataList_ = other.deletedDataList_;
            bitField0_ = (bitField0_ & ~0x00000004);
            deletedDataListBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getDeletedDataListFieldBuilder() : null;
          } else {
            deletedDataListBuilder_.addAllMessages(other.deletedDataList_);
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
      enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse) e.getUnfinishedMessage();
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

    private java.util.List<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData> deletedDataList_ =
      java.util.Collections.emptyList();
    private void ensureDeletedDataListIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        deletedDataList_ = new java.util.ArrayList<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData>(deletedDataList_);
        bitField0_ |= 0x00000004;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData, enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData.Builder, enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataOrBuilder> deletedDataListBuilder_;

    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public java.util.List<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData> getDeletedDataListList() {
      if (deletedDataListBuilder_ == null) {
        return java.util.Collections.unmodifiableList(deletedDataList_);
      } else {
        return deletedDataListBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public int getDeletedDataListCount() {
      if (deletedDataListBuilder_ == null) {
        return deletedDataList_.size();
      } else {
        return deletedDataListBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData getDeletedDataList(int index) {
      if (deletedDataListBuilder_ == null) {
        return deletedDataList_.get(index);
      } else {
        return deletedDataListBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public Builder setDeletedDataList(
        int index, enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData value) {
      if (deletedDataListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDeletedDataListIsMutable();
        deletedDataList_.set(index, value);
        onChanged();
      } else {
        deletedDataListBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public Builder setDeletedDataList(
        int index, enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData.Builder builderForValue) {
      if (deletedDataListBuilder_ == null) {
        ensureDeletedDataListIsMutable();
        deletedDataList_.set(index, builderForValue.build());
        onChanged();
      } else {
        deletedDataListBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public Builder addDeletedDataList(enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData value) {
      if (deletedDataListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDeletedDataListIsMutable();
        deletedDataList_.add(value);
        onChanged();
      } else {
        deletedDataListBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public Builder addDeletedDataList(
        int index, enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData value) {
      if (deletedDataListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDeletedDataListIsMutable();
        deletedDataList_.add(index, value);
        onChanged();
      } else {
        deletedDataListBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public Builder addDeletedDataList(
        enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData.Builder builderForValue) {
      if (deletedDataListBuilder_ == null) {
        ensureDeletedDataListIsMutable();
        deletedDataList_.add(builderForValue.build());
        onChanged();
      } else {
        deletedDataListBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public Builder addDeletedDataList(
        int index, enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData.Builder builderForValue) {
      if (deletedDataListBuilder_ == null) {
        ensureDeletedDataListIsMutable();
        deletedDataList_.add(index, builderForValue.build());
        onChanged();
      } else {
        deletedDataListBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public Builder addAllDeletedDataList(
        java.lang.Iterable<? extends enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData> values) {
      if (deletedDataListBuilder_ == null) {
        ensureDeletedDataListIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, deletedDataList_);
        onChanged();
      } else {
        deletedDataListBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public Builder clearDeletedDataList() {
      if (deletedDataListBuilder_ == null) {
        deletedDataList_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
      } else {
        deletedDataListBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public Builder removeDeletedDataList(int index) {
      if (deletedDataListBuilder_ == null) {
        ensureDeletedDataListIsMutable();
        deletedDataList_.remove(index);
        onChanged();
      } else {
        deletedDataListBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData.Builder getDeletedDataListBuilder(
        int index) {
      return getDeletedDataListFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataOrBuilder getDeletedDataListOrBuilder(
        int index) {
      if (deletedDataListBuilder_ == null) {
        return deletedDataList_.get(index);  } else {
        return deletedDataListBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public java.util.List<? extends enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataOrBuilder> 
         getDeletedDataListOrBuilderList() {
      if (deletedDataListBuilder_ != null) {
        return deletedDataListBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(deletedDataList_);
      }
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData.Builder addDeletedDataListBuilder() {
      return getDeletedDataListFieldBuilder().addBuilder(
          enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData.getDefaultInstance());
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData.Builder addDeletedDataListBuilder(
        int index) {
      return getDeletedDataListFieldBuilder().addBuilder(
          index, enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData.getDefaultInstance());
    }
    /**
     * <code>repeated .EnnMonitorConfigServiceGetDeletedData deletedDataList = 3;</code>
     */
    public java.util.List<enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData.Builder> 
         getDeletedDataListBuilderList() {
      return getDeletedDataListFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData, enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData.Builder, enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataOrBuilder> 
        getDeletedDataListFieldBuilder() {
      if (deletedDataListBuilder_ == null) {
        deletedDataListBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData, enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData.Builder, enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataOrBuilder>(
                deletedDataList_,
                ((bitField0_ & 0x00000004) == 0x00000004),
                getParentForChildren(),
                isClean());
        deletedDataList_ = null;
      }
      return deletedDataListBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:EnnMonitorConfigServiceGetDeletedDataListResponse)
  }

  // @@protoc_insertion_point(class_scope:EnnMonitorConfigServiceGetDeletedDataListResponse)
  private static final enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse();
  }

  public static enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EnnMonitorConfigServiceGetDeletedDataListResponse>
      PARSER = new com.google.protobuf.AbstractParser<EnnMonitorConfigServiceGetDeletedDataListResponse>() {
    public EnnMonitorConfigServiceGetDeletedDataListResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new EnnMonitorConfigServiceGetDeletedDataListResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EnnMonitorConfigServiceGetDeletedDataListResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EnnMonitorConfigServiceGetDeletedDataListResponse> getParserForType() {
    return PARSER;
  }

  public enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
