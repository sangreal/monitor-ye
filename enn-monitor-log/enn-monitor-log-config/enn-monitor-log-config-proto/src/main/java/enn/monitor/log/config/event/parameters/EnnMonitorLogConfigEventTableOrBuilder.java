// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/logEventParameters.proto

package enn.monitor.log.config.event.parameters;

public interface EnnMonitorLogConfigEventTableOrBuilder extends
    // @@protoc_insertion_point(interface_extends:EnnMonitorLogConfigEventTable)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional uint64 id = 1;</code>
   */
  long getId();

  /**
   * <code>optional uint64 seqNo = 2;</code>
   */
  long getSeqNo();

  /**
   * <code>optional string eventKey = 3;</code>
   */
  java.lang.String getEventKey();
  /**
   * <code>optional string eventKey = 3;</code>
   */
  com.google.protobuf.ByteString
      getEventKeyBytes();

  /**
   * <code>optional string eventName = 4;</code>
   */
  java.lang.String getEventName();
  /**
   * <code>optional string eventName = 4;</code>
   */
  com.google.protobuf.ByteString
      getEventNameBytes();

  /**
   * <code>optional uint64 belongToServiceId = 5;</code>
   */
  long getBelongToServiceId();

  /**
   * <code>optional uint64 createTime = 6;</code>
   */
  long getCreateTime();

  /**
   * <code>optional uint64 lastUpdateTime = 7;</code>
   */
  long getLastUpdateTime();

  /**
   * <code>optional string createUser = 8;</code>
   */
  java.lang.String getCreateUser();
  /**
   * <code>optional string createUser = 8;</code>
   */
  com.google.protobuf.ByteString
      getCreateUserBytes();

  /**
   * <code>optional string lastUpdateUser = 9;</code>
   */
  java.lang.String getLastUpdateUser();
  /**
   * <code>optional string lastUpdateUser = 9;</code>
   */
  com.google.protobuf.ByteString
      getLastUpdateUserBytes();
}