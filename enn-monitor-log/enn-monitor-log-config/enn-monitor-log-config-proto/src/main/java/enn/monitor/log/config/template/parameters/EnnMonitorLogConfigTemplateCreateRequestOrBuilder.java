// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/logTemplateParameters.proto

package enn.monitor.log.config.template.parameters;

public interface EnnMonitorLogConfigTemplateCreateRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:EnnMonitorLogConfigTemplateCreateRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string templateKey = 1;</code>
   */
  java.lang.String getTemplateKey();
  /**
   * <code>optional string templateKey = 1;</code>
   */
  com.google.protobuf.ByteString
      getTemplateKeyBytes();

  /**
   * <code>optional string belongToParentTemplate = 2;</code>
   */
  java.lang.String getBelongToParentTemplate();
  /**
   * <code>optional string belongToParentTemplate = 2;</code>
   */
  com.google.protobuf.ByteString
      getBelongToParentTemplateBytes();

  /**
   * <code>optional uint64 belongToServiceId = 3;</code>
   */
  long getBelongToServiceId();

  /**
   * <code>optional .EnnMonitorLogConfigTemplateSetType setType = 4;</code>
   */
  int getSetTypeValue();
  /**
   * <code>optional .EnnMonitorLogConfigTemplateSetType setType = 4;</code>
   */
  enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateSetType getSetType();

  /**
   * <code>optional uint64 tagId = 5;</code>
   */
  long getTagId();

  /**
   * <code>optional uint64 batchId = 6;</code>
   */
  long getBatchId();

  /**
   * <code>optional string createUser = 7;</code>
   */
  java.lang.String getCreateUser();
  /**
   * <code>optional string createUser = 7;</code>
   */
  com.google.protobuf.ByteString
      getCreateUserBytes();
}
