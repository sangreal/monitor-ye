// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/ticketParameter.proto

package enn.monitor.alarm.ticket.parameter;

public interface EnnMonitorAlarmTicketGetDeletedResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:EnnMonitorAlarmTicketGetDeletedResponse)
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
   * <code>repeated .EnnMonitorAlarmTicketGetDeleted ennMonitorAlarmTicketGetDeletedList = 3;</code>
   */
  java.util.List<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeleted> 
      getEnnMonitorAlarmTicketGetDeletedListList();
  /**
   * <code>repeated .EnnMonitorAlarmTicketGetDeleted ennMonitorAlarmTicketGetDeletedList = 3;</code>
   */
  enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeleted getEnnMonitorAlarmTicketGetDeletedList(int index);
  /**
   * <code>repeated .EnnMonitorAlarmTicketGetDeleted ennMonitorAlarmTicketGetDeletedList = 3;</code>
   */
  int getEnnMonitorAlarmTicketGetDeletedListCount();
  /**
   * <code>repeated .EnnMonitorAlarmTicketGetDeleted ennMonitorAlarmTicketGetDeletedList = 3;</code>
   */
  java.util.List<? extends enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedOrBuilder> 
      getEnnMonitorAlarmTicketGetDeletedListOrBuilderList();
  /**
   * <code>repeated .EnnMonitorAlarmTicketGetDeleted ennMonitorAlarmTicketGetDeletedList = 3;</code>
   */
  enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedOrBuilder getEnnMonitorAlarmTicketGetDeletedListOrBuilder(
      int index);
}
