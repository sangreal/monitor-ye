// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/serviceLineParameters.proto

package enn.monitor.config.serviceLine.parameters;

public interface EnnMonitorConfigServiceLineGetRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:EnnMonitorConfigServiceLineGetRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional uint64 id = 1;</code>
   */
  long getId();

  /**
   * <code>optional .EnnMonitorConfigServiceLineStatus status = 2;</code>
   */
  int getStatusValue();
  /**
   * <code>optional .EnnMonitorConfigServiceLineStatus status = 2;</code>
   */
  enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineStatus getStatus();

  /**
   * <code>optional string serviceLineName = 3;</code>
   */
  java.lang.String getServiceLineName();
  /**
   * <code>optional string serviceLineName = 3;</code>
   */
  com.google.protobuf.ByteString
      getServiceLineNameBytes();

  /**
   * <code>optional uint64 belongToCluster = 4;</code>
   */
  long getBelongToCluster();

  /**
   * <code>optional string createUser = 5;</code>
   */
  java.lang.String getCreateUser();
  /**
   * <code>optional string createUser = 5;</code>
   */
  com.google.protobuf.ByteString
      getCreateUserBytes();

  /**
   * <code>optional string lastUpdateUser = 6;</code>
   */
  java.lang.String getLastUpdateUser();
  /**
   * <code>optional string lastUpdateUser = 6;</code>
   */
  com.google.protobuf.ByteString
      getLastUpdateUserBytes();
}