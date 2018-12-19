package enn.monitor.log.train.worker.jobmsg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import enn.monitor.framework.ai.nn.NNObject;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.log.channel.ChannelWriteTask;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJob;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtl;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobStatus;
import enn.monitor.log.train.worker.core.EnnMonitorLogTrainWorkerCoreBody;
import enn.monitor.log.train.worker.core.EnnMonitorLogTrainWorkerCoreEliteBody;
import enn.monitor.log.train.worker.util.EnnMonitorLogTrainWorkerUtil;

public class EnnMonitorLogTrainWorkerJobmsg extends ChannelWriteTask {
	
	private static final Logger logger = LogManager.getLogger();
	
	private Gson gson = new Gson();
	
	private EnnMonitorLogTrainJobStatus bestJobStatus = null;
	private Map<Long, Map<Long, EnnMonitorLogTrainWorkerCoreBody>> bodyMap = new HashMap<Long, Map<Long, EnnMonitorLogTrainWorkerCoreBody>>();
	private Map<Long, Map<Long, EnnMonitorLogTrainJobStatus>> jobStatusMap = new HashMap<Long, Map<Long, EnnMonitorLogTrainJobStatus>>();
	
	private List<EnnMonitorLogTrainJobStatus> jobStatuses = null;
	
	private void deployJobs(List<EnnMonitorLogTrainJob> jobList) throws Exception {
		int i, j, k;
		EnnMonitorLogTrainJob job = null;
		
		List<Double> inputList = null;
		List<Double> outputList = null;
		List<List<Double>> inputListList = null;
		List<List<Double>> outputListList = null;
		
		EnnMonitorLogTrainWorkerCoreBody workerThread = null;
		Map<Long, EnnMonitorLogTrainWorkerCoreBody> workerBodyMap = null;
		
		if (jobList == null || jobList.size() <= 0) {
			logger.debug("no job");
			return;
		}
		
		logger.info("new job");
		
		for (i = 0; i < jobList.size(); ++i) {
			job = jobList.get(i);
			
			workerBodyMap = bodyMap.get(job.getJobId());
			if (workerBodyMap != null) {
				logger.error("the jobId " + job.getJobId() + " exists");
				continue;
			}
			
			workerBodyMap = new HashMap<Long, EnnMonitorLogTrainWorkerCoreBody>();
			bodyMap.put(job.getJobId(), workerBodyMap);
			
			for (j = 0; j < job.getGenomNum(); ++j) {
				if (job.getInputList() != null && job.getOutputList() != null) {
					inputListList = new ArrayList<List<Double>>();
					outputListList = new ArrayList<List<Double>>();
					
					for (k = 0; k < job.getInputList().size(); ++k) {
						inputList = new ArrayList<Double>();
						outputList = new ArrayList<Double>();
						
						if (job.getInputList().get(k).getInputList() != null && job.getOutputList().get(k).getOutputList() != null) {
							inputList.addAll(job.getInputList().get(k).getInputList());
							inputListList.add(inputList);
							
							outputList.addAll(job.getOutputList().get(k).getOutputList());
							outputListList.add(outputList);
						}
					}
					
					if (j < job.getGenomNum() / 3 && j < 5) {
						workerThread = new EnnMonitorLogTrainWorkerCoreEliteBody(this, job.getEliteGenerator(), j, job.getJobId(), gson.fromJson(job.getJob(), NNObject.class), 
								inputListList, outputListList, job.getChangeParameter());
					} else {
						workerThread = new EnnMonitorLogTrainWorkerCoreBody(this, j, job.getJobId(), gson.fromJson(job.getJob(), NNObject.class), 
								inputListList, outputListList, job.getChangeParameter());
					}
					
					new Thread(workerThread).start();
					workerBodyMap.put((long) j, workerThread);
				}
			}
		}
	}
	
	private void ctlJobs(List<EnnMonitorLogTrainJobCtl> jobCtlList) throws Exception {
		int i = 0;
		
		List<Long> jobIdList = new ArrayList<Long>();
		
		// 清除已经不存在的任务
		Map<Long, EnnMonitorLogTrainJobCtl> jobCtlMap = new HashMap<Long, EnnMonitorLogTrainJobCtl>();
		for (EnnMonitorLogTrainJobCtl jobCtl : jobCtlList) {
			jobCtlMap.put(jobCtl.getJobId(), jobCtl);
		}
		
		for (Entry<Long, Map<Long, EnnMonitorLogTrainWorkerCoreBody>> entry : bodyMap.entrySet()) {
			if (jobCtlMap.containsKey(entry.getKey()) == false) {
				jobIdList.add(entry.getKey());
			}
		}
		
		for (i = 0; i < jobIdList.size(); ++i) {
			stopJob(jobIdList.get(i));
		}
		
		// 执行命令
		for (EnnMonitorLogTrainJobCtl jobCtl : jobCtlList) {
			if (jobCtl.getCtl() == null) {
				logger.error("the jobCtl is null");
				continue;
			}
			
			switch (jobCtl.getCtl()) {
			case Continue:
				break;
			case Replace:
				logger.info("replace best job");
				if (jobCtl.getBestJobList() != null && jobCtl.getBestJobList().size() > 0) {
					replaceJob(jobCtl.getJobId(), -1, jobCtl.getBestJobList().get(0));
				}
				break;
			case Stop:
				stopJob(jobCtl.getJobId());
				break;
			default:
				logger.error("the unexpected jobCtl, it is " + jobCtl.getCtl().name());
			}
		}
	}
	
	private void replaceJob(long jobId, long bodyId, String bestJob) throws Exception {
		NNObject nnObject = null;
		
		Entry<Long, EnnMonitorLogTrainJobStatus> tmpEntry = null;
		Map<Long, EnnMonitorLogTrainJobStatus> jobStatuses = null;
		
		Map<Long, EnnMonitorLogTrainWorkerCoreBody> bodyItemMap = null;
		
		EnnMonitorLogTrainWorkerCoreBody body = null;
		
		if (bestJob == null || bestJob.equals("") == true) {
			return;
		}
		
		jobStatuses = jobStatusMap.get(jobId);
		bodyItemMap = bodyMap.get(jobId);
		
		if (jobStatuses != null && jobStatuses.size() > 0) {
			if (bodyId < 0) {
				logger.info("get the worst job");
				for (Entry<Long, EnnMonitorLogTrainJobStatus> entry : jobStatuses.entrySet()) {
					if (tmpEntry == null || tmpEntry.getValue().getBestError() < entry.getValue().getBestError()) {
						tmpEntry = entry;
					}
				}
				logger.info("it is error is: " + tmpEntry.getValue().getBestError());
			} else {
				for (Entry<Long, EnnMonitorLogTrainJobStatus> entry : jobStatuses.entrySet()) {
					if (entry.getKey() == bodyId) {
						tmpEntry = entry;
						break;
					}
				}
			}
			
			if (tmpEntry == null || bodyItemMap.containsKey(tmpEntry.getKey()) == false) {
				return;
			}
			body = bodyItemMap.get(tmpEntry.getKey());
			
			logger.info("replace job, it is " + tmpEntry.getKey());
			
			body.stop();
			
			nnObject = gson.fromJson(bestJob, NNObject.class);
//			bodyItemMap.remove(tmpEntry.getKey());
//			jobStatuses.remove(tmpEntry.getKey());
			
			if (bestJobStatus == null || bestJobStatus.getBestError() > nnObject.getError()) {
				bestJobStatus = EnnMonitorLogTrainJobStatus.newBuilder().setBestError(nnObject.getError()).setBestJob(gson.toJson(nnObject)).build();
			}
			
			logger.info("the NNObject error is " + nnObject.getError());
			
			body.setNNObject(nnObject);
			new Thread(body).start();
			
			bodyItemMap.put(tmpEntry.getKey(), body);
		} else {
			logger.error("the job is not exists, it is " + jobId);
		}
	}
	
	private void stopJob(long jobId) throws Exception {
		Map<Long, EnnMonitorLogTrainWorkerCoreBody> workerBodyMap = null;
		
		workerBodyMap = bodyMap.get(jobId);
		if (workerBodyMap != null) {
			for (Entry<Long, EnnMonitorLogTrainWorkerCoreBody> entry : workerBodyMap.entrySet()) {
				logger.info("threadId: " + entry.getKey() + " stop");
				entry.getValue().stop();
			}
			
			bodyMap.remove(jobId);
			jobStatusMap.remove(jobId);
		} else {
			logger.info("the jobCtl is null");
		}
		
		bestJobStatus = null;
		jobStatuses = null;
	}
	
	private void reportJobStatus() throws Exception {
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		
		String job = null;
		long bestGeneration = -1l;
		long worstGeneration = -1l;
		
		EnnMonitorLogTrainJobStatus.Builder jobStatus = null;
		
		List<EnnMonitorLogTrainJobStatus> jobStatusList = new ArrayList<EnnMonitorLogTrainJobStatus>();
		
		logger.debug("get jobStatus");
		
		for (Entry<Long, Map<Long, EnnMonitorLogTrainJobStatus>> entry : jobStatusMap.entrySet()) {
			jobStatus = EnnMonitorLogTrainJobStatus.newBuilder();
			
			max = Double.MIN_VALUE;
			min = Double.MAX_VALUE;
			for (Entry<Long, EnnMonitorLogTrainJobStatus> item : entry.getValue().entrySet()) {
				if (max < item.getValue().getBestError()) {
					max = item.getValue().getBestError();
					worstGeneration = item.getValue().getBestGeneration();
				}
				if (min > item.getValue().getBestError()) {
					min = item.getValue().getBestError();
					bestGeneration = item.getValue().getBestGeneration();
					
					job = item.getValue().getBestJob();
				}
				
				logger.info("jobStatus: bodyId " + item.getKey() + ", the error is " + item.getValue().getBestError());
			}
			
			if (job != null) {
				jobStatus.setJobId(entry.getKey());
				jobStatus.setIdentityId(EnnMonitorLogTrainWorkerUtil.generateIdentityId());
				jobStatus.setBestJob(job);
				jobStatus.setBestError(min);
				jobStatus.setBestGeneration(bestGeneration);
				jobStatus.setWorstError(max);
				jobStatus.setWorstGeneration(worstGeneration);
				
				logger.info("jobStatus: the bestError is " + jobStatus.getBestError() + ", the worstError is " + jobStatus.getWorstError());
				if (jobStatus.getWorstError() < jobStatus.getBestError()) {
					logger.error("the worstError is less than bestError, it is " + jobStatus.getIdentityId());
				}
				
				jobStatusList.add(jobStatus.build());
			}
			
		}
		
		jobStatuses = jobStatusList;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void reportEnnMonitorLogTrainJobStatus(long bodyId, EnnMonitorLogTrainJobStatus jobStatus) throws Exception {
		Map<Long, EnnMonitorLogTrainJobStatus> jobStatusGroup = null;
		EnnMonitorLogTrainJobStatus jobStatusEntry = null;
		
		logger.debug("report job status");
		
		if (bodyMap.containsKey(jobStatus.getJobId()) == false) {
			return;
		}
		
		jobStatusGroup = jobStatusMap.get(jobStatus.getJobId());
		if (jobStatusGroup == null) {
			jobStatusGroup = new HashMap<Long, EnnMonitorLogTrainJobStatus>();
			jobStatusMap.put(jobStatus.getJobId(), jobStatusGroup);
		}
		
		jobStatusEntry = jobStatusGroup.get(bodyId);
		if (jobStatusEntry == null || jobStatusEntry.getBestError() > jobStatus.getBestError()) {
//			logger.info("bodyId is " + bodyId + ", the error is " + jobStatus.getBestError());
			jobStatusGroup.put(bodyId, jobStatus);
		}
		
		if (bestJobStatus == null) {
			bestJobStatus = jobStatus;
		} else if (bestJobStatus.getBestError() > jobStatus.getBestError()) {
			bestJobStatus = jobStatus;
		}
	}
	
	@Override
	protected void operator(ChannelWriteData stockWriteData) throws Exception {
		EnnMonitorLogTrainWorkerJobEnum jobEnum = (EnnMonitorLogTrainWorkerJobEnum) stockWriteData.getOpEnum();
		EnnMonitorLogTrainWorkerJobData jobData = (EnnMonitorLogTrainWorkerJobData) stockWriteData.getObject();
		
		switch (jobEnum) {
		case ReportJobStatus:
			reportEnnMonitorLogTrainJobStatus(jobData.getBodyId(), jobData.getJobStatus());
			break;
		case ReplaceBestJob:
			if (bestJobStatus == null) {
				break;
			}
			replaceJob(jobData.getJobId(), jobData.getBodyId(), bestJobStatus.getBestJob());
			break;
		case DeploymentJob:
			deployJobs(jobData.getJobList());
			break;
		case CtlJob:
			ctlJobs(jobData.getJobCtlList());
			break;
		case ReportToMaster:
			reportJobStatus();
			break;
		default:
			logger.error("unexpected JobEnum, it is " + jobEnum.name());
			break;
		}
	}
	
	public List<EnnMonitorLogTrainJobStatus> getEnnMonitorLogTrainJobStatuses() {
		return jobStatuses;
	}
	
}
