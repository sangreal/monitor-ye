// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/storageServer.proto

package enn.monitor.log.analyse.storage.server;

public final class EnnMonitorLogAnalyseStorageConnection {
  private EnnMonitorLogAnalyseStorageConnection() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\034protobuf/storageServer.proto\032 protobuf" +
      "/storageParameters.proto2\350\002\n!EnnMonitorL" +
      "ogAnalyseStorageServer\022c\n\010searchNN\022).Enn" +
      "MonitorLogAnalyseStorageSearchRequest\032*." +
      "EnnMonitorLogAnalyseStorageSearchRespons" +
      "e\"\000\022c\n\010createNN\022).EnnMonitorLogAnalyseSt" +
      "orageCreateRequest\032*.EnnMonitorLogAnalys" +
      "eStorageCreateResponse\"\000\022y\n\020getLastestNN" +
      "Data\0220.EnnMonitorLogAnalyseStorageLastes" +
      "tNNDataRequest\0321.EnnMonitorLogAnalyseSto",
      "rageLastestNNDataResponse\"\000BQ\n&enn.monit" +
      "or.log.analyse.storage.serverB%EnnMonito" +
      "rLogAnalyseStorageConnectionP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageParameters.getDescriptor(),
        }, assigner);
    enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageParameters.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}