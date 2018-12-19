package enn.monitor.log.config.common.util;

import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.log.config.common.impl.EnnMonitorLogConfigCommonImpl;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

public class EnnMonitorLogConfigCommonUtil {
	
	public static void init(ServerBuilder<?> serverBuilder, MongoAutoIncTableCtl autoIncTableCtl) throws Exception {
		serverBuilder.addService(ServerInterceptors.intercept(new EnnMonitorLogConfigCommonImpl(autoIncTableCtl)));
	}
	
}
