// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/logTemplateParameters.proto

package enn.monitor.log.config.template.parameters;

public interface EnnMonitorLogConfigTemplateGetValidDataListResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:EnnMonitorLogConfigTemplateGetValidDataListResponse)
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
   * <code>repeated .EnnMonitorLogConfigTemplateTable templateTable = 3;</code>
   */
  java.util.List<enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateTable> 
      getTemplateTableList();
  /**
   * <code>repeated .EnnMonitorLogConfigTemplateTable templateTable = 3;</code>
   */
  enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateTable getTemplateTable(int index);
  /**
   * <code>repeated .EnnMonitorLogConfigTemplateTable templateTable = 3;</code>
   */
  int getTemplateTableCount();
  /**
   * <code>repeated .EnnMonitorLogConfigTemplateTable templateTable = 3;</code>
   */
  java.util.List<? extends enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateTableOrBuilder> 
      getTemplateTableOrBuilderList();
  /**
   * <code>repeated .EnnMonitorLogConfigTemplateTable templateTable = 3;</code>
   */
  enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateTableOrBuilder getTemplateTableOrBuilder(
      int index);
}
