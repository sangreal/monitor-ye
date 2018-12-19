package enn.monitor.log.train.worker.util;

import enn.monitor.framework.common.env.EnnMonitorEnvAppUtil;

public class EnnMonitorLogTrainWorkerUtil {
	
	public static String generateIdentityId() throws Exception {
		return EnnMonitorEnvAppUtil.getNameSpace() + "_" + EnnMonitorEnvAppUtil.getPodName();
	}

}
