// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/serviceLineParameters.proto

package enn.monitor.config.serviceLine.parameters;

public interface EnnMonitorConfigServiceLineGetDeletedDataListResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:EnnMonitorConfigServiceLineGetDeletedDataListResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional bool isSuccess = 1;</code>
   */
  boolean getIsSuccess();

  /**
   * <code>optional string error = 2;</code>
   */
  java.lang.String getError();
  /**
   * <code>optional string error = 2;</code>
   */
  com.google.protobuf.ByteString
      getErrorBytes();

  /**
   * <code>repeated .EnnMonitorConfigServiceLineGetDeletedData deletedDataList = 3;</code>
   */
  java.util.List<enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedData> 
      getDeletedDataListList();
  /**
   * <code>repeated .EnnMonitorConfigServiceLineGetDeletedData deletedDataList = 3;</code>
   */
  enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedData getDeletedDataList(int index);
  /**
   * <code>repeated .EnnMonitorConfigServiceLineGetDeletedData deletedDataList = 3;</code>
   */
  int getDeletedDataListCount();
  /**
   * <code>repeated .EnnMonitorConfigServiceLineGetDeletedData deletedDataList = 3;</code>
   */
  java.util.List<? extends enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataOrBuilder> 
      getDeletedDataListOrBuilderList();
  /**
   * <code>repeated .EnnMonitorConfigServiceLineGetDeletedData deletedDataList = 3;</code>
   */
  enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataOrBuilder getDeletedDataListOrBuilder(
      int index);
}
