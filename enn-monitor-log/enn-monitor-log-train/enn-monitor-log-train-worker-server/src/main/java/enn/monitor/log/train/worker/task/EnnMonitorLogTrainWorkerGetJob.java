package enn.monitor.log.train.worker.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.log.train.master.client.EnnMonitorLogTrainMasterClient;
import enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobReq;
import enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobRsp;
import enn.monitor.log.train.worker.jobmsg.EnnMonitorLogTrainWorkerJobData;
import enn.monitor.log.train.worker.jobmsg.EnnMonitorLogTrainWorkerJobEnum;
import enn.monitor.log.train.worker.jobmsg.EnnMonitorLogTrainWorkerJobmsg;
import enn.monitor.log.train.worker.util.EnnMonitorLogTrainWorkerUtil;

public class EnnMonitorLogTrainWorkerGetJob implements Runnable {
	
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorLogTrainMasterClient client = null;
	
	private EnnMonitorLogTrainWorkerJobmsg jobmsg = null;
	
	public EnnMonitorLogTrainWorkerGetJob(EnnMonitorLogTrainWorkerJobmsg jobmsg, EnnMonitorLogTrainMasterClient client) throws Exception {
		this.jobmsg = jobmsg;
		this.client = client;
	}

	@Override
	public void run() {
		EnnMonitorLogTrainMasterGetJobRsp getJobRsp = null;
		
		EnnMonitorLogTrainWorkerJobData jobData = null;
		ChannelWriteData channelWriteData = null;
		
		while (true) {
			try {
				getJobRsp = client.getJob(EnnMonitorLogTrainMasterGetJobReq.newBuilder().setIdentityId(EnnMonitorLogTrainWorkerUtil.generateIdentityId()).build());
				if (getJobRsp.getIsSuccess() == true) {
					jobData = new EnnMonitorLogTrainWorkerJobData();
					jobData.setJobList(getJobRsp.getTrainJobList());
					channelWriteData = new ChannelWriteData(EnnMonitorLogTrainWorkerJobEnum.DeploymentJob, jobData);
					jobmsg.write(channelWriteData);
				} else {
					logger.error(getJobRsp.getError());
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
