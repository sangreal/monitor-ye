// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/serviceParameters.proto

package enn.monitor.config.service.parameters;

public interface EnnMonitorConfigServiceTableOrBuilder extends
    // @@protoc_insertion_point(interface_extends:EnnMonitorConfigServiceTable)
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
   * <code>optional string serviceName = 3;</code>
   */
  java.lang.String getServiceName();
  /**
   * <code>optional string serviceName = 3;</code>
   */
  com.google.protobuf.ByteString
      getServiceNameBytes();

  /**
   * <code>optional uint64 belongToServiceLine = 4;</code>
   */
  long getBelongToServiceLine();

  /**
   * <code>optional string token = 5;</code>
   */
  java.lang.String getToken();
  /**
   * <code>optional string token = 5;</code>
   */
  com.google.protobuf.ByteString
      getTokenBytes();

  /**
   * <code>optional .EnnMonitorConfigServiceStatus status = 6;</code>
   */
  int getStatusValue();
  /**
   * <code>optional .EnnMonitorConfigServiceStatus status = 6;</code>
   */
  enn.monitor.config.service.parameters.EnnMonitorConfigServiceStatus getStatus();

  /**
   * <code>optional uint64 createTime = 7;</code>
   */
  long getCreateTime();

  /**
   * <code>optional uint64 lastUpdateTime = 8;</code>
   */
  long getLastUpdateTime();

  /**
   * <code>optional string owner = 11;</code>
   */
  java.lang.String getOwner();
  /**
   * <code>optional string owner = 11;</code>
   */
  com.google.protobuf.ByteString
      getOwnerBytes();

  /**
   * <code>repeated string guest = 12;</code>
   */
  java.util.List<java.lang.String>
      getGuestList();
  /**
   * <code>repeated string guest = 12;</code>
   */
  int getGuestCount();
  /**
   * <code>repeated string guest = 12;</code>
   */
  java.lang.String getGuest(int index);
  /**
   * <code>repeated string guest = 12;</code>
   */
  com.google.protobuf.ByteString
      getGuestBytes(int index);
}
