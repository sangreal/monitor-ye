// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/trainParameters.proto

package enn.monitor.log.train.proto;

public interface EnnMonitorLogTrainJobCtlOrBuilder extends
    // @@protoc_insertion_point(interface_extends:EnnMonitorLogTrainJobCtl)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional uint64 jobId = 1;</code>
   */
  long getJobId();

  /**
   * <code>optional .EnnMonitorLogTrainJobCtlEnum ctl = 3;</code>
   */
  int getCtlValue();
  /**
   * <code>optional .EnnMonitorLogTrainJobCtlEnum ctl = 3;</code>
   */
  enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlEnum getCtl();

  /**
   * <code>repeated string bestJob = 4;</code>
   */
  java.util.List<java.lang.String>
      getBestJobList();
  /**
   * <code>repeated string bestJob = 4;</code>
   */
  int getBestJobCount();
  /**
   * <code>repeated string bestJob = 4;</code>
   */
  java.lang.String getBestJob(int index);
  /**
   * <code>repeated string bestJob = 4;</code>
   */
  com.google.protobuf.ByteString
      getBestJobBytes(int index);
}
