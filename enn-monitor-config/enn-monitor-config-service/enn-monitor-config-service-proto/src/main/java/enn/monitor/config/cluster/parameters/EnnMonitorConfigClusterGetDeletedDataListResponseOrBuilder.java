// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/clusterParameters.proto

package enn.monitor.config.cluster.parameters;

public interface EnnMonitorConfigClusterGetDeletedDataListResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:EnnMonitorConfigClusterGetDeletedDataListResponse)
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
   * <code>repeated .EnnMonitorConfigClusterGetDeletedData deletedDataList = 3;</code>
   */
  java.util.List<enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedData> 
      getDeletedDataListList();
  /**
   * <code>repeated .EnnMonitorConfigClusterGetDeletedData deletedDataList = 3;</code>
   */
  enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedData getDeletedDataList(int index);
  /**
   * <code>repeated .EnnMonitorConfigClusterGetDeletedData deletedDataList = 3;</code>
   */
  int getDeletedDataListCount();
  /**
   * <code>repeated .EnnMonitorConfigClusterGetDeletedData deletedDataList = 3;</code>
   */
  java.util.List<? extends enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataOrBuilder> 
      getDeletedDataListOrBuilderList();
  /**
   * <code>repeated .EnnMonitorConfigClusterGetDeletedData deletedDataList = 3;</code>
   */
  enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataOrBuilder getDeletedDataListOrBuilder(
      int index);
}
