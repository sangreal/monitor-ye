package enn.monitor.log.train.worker.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.log.train.master.client.EnnMonitorLogTrainMasterClient;
import enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlReq;
import enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlRsp;
import enn.monitor.log.train.worker.jobmsg.EnnMonitorLogTrainWorkerJobData;
import enn.monitor.log.train.worker.jobmsg.EnnMonitorLogTrainWorkerJobEnum;
import enn.monitor.log.train.worker.jobmsg.EnnMonitorLogTrainWorkerJobmsg;
import enn.monitor.log.train.worker.util.EnnMonitorLogTrainWorkerUtil;

public class EnnMonitorLogTrainWorkerGetJobCtl implements Runnable {
	
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorLogTrainMasterClient client = null;
	
	private EnnMonitorLogTrainWorkerJobmsg jobmsg = null;
	
	public EnnMonitorLogTrainWorkerGetJobCtl(EnnMonitorLogTrainWorkerJobmsg jobmsg, EnnMonitorLogTrainMasterClient client) throws Exception {
		this.jobmsg = jobmsg;
		this.client = client;
	}

	@Override
	public void run() {
		EnnMonitorLogTrainMasterGetJobCtlRsp getJobCtlRsp = null;
		
		EnnMonitorLogTrainWorkerJobData jobData = null;
		ChannelWriteData channelWriteData = null;
		
		while (true) {
			try {
				logger.debug("get Ctl");
				getJobCtlRsp = client.getJobCtl(EnnMonitorLogTrainMasterGetJobCtlReq.newBuilder().setIdentityId(EnnMonitorLogTrainWorkerUtil.generateIdentityId()).build());
				if (getJobCtlRsp.getIsSuccess() == true) {
					jobData = new EnnMonitorLogTrainWorkerJobData();
					jobData.setJobCtlList(getJobCtlRsp.getJobCtlList());
					channelWriteData = new ChannelWriteData(EnnMonitorLogTrainWorkerJobEnum.CtlJob, jobData);
					jobmsg.write(channelWriteData);
				} else {
					logger.error(getJobCtlRsp.getError());
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}

}
