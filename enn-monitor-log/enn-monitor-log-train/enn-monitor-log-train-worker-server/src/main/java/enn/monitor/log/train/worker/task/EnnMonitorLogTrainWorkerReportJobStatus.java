package enn.monitor.log.train.worker.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.log.train.master.client.EnnMonitorLogTrainMasterClient;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobStatus;
import enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusReq;
import enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusRsp;
import enn.monitor.log.train.worker.jobmsg.EnnMonitorLogTrainWorkerJobEnum;
import enn.monitor.log.train.worker.jobmsg.EnnMonitorLogTrainWorkerJobmsg;

public class EnnMonitorLogTrainWorkerReportJobStatus implements Runnable {
	
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorLogTrainMasterClient client = null;
	private EnnMonitorLogTrainWorkerJobmsg jobmsg = null;
	
	public EnnMonitorLogTrainWorkerReportJobStatus(EnnMonitorLogTrainWorkerJobmsg jobmsg, EnnMonitorLogTrainMasterClient client) throws Exception {
		this.jobmsg = jobmsg;
		this.client = client;
	}

	@Override
	public void run() {
		List<EnnMonitorLogTrainJobStatus> jobStatuses = null;
		
		while (true) {
			try {
				logger.debug("thread: report job status");
				
				jobmsg.write(new ChannelWriteData(EnnMonitorLogTrainWorkerJobEnum.ReportToMaster, null));
				
				jobStatuses = jobmsg.getEnnMonitorLogTrainJobStatuses();
				if (jobStatuses == null) {
					jobStatuses = new ArrayList<EnnMonitorLogTrainJobStatus>();
				} else {
					for (EnnMonitorLogTrainJobStatus jobStatus : jobStatuses) {
						logger.debug("jobStatus: the bestError is " + jobStatus.getBestError() + ", the worstError is " + jobStatus.getWorstError());
					}
				}
				
				EnnMonitorLogTrainMasterReportJobStatusRsp jobStatusRsp = 
						client.reportJobStatus(EnnMonitorLogTrainMasterReportJobStatusReq.newBuilder().addAllJobStatus(jobStatuses).build());
				if (jobStatusRsp.getIsSuccess() == false) {
					logger.error(jobStatusRsp.getError());
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}

}
