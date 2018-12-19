package enn.monitor.log.train.master.jobmsg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import enn.monitor.framework.ai.nn.NNObject;
import enn.monitor.framework.common.heap.EnnMonitorHeap;
import enn.monitor.framework.common.heap.EnnMonitorHeapData;
import enn.monitor.log.train.master.jobmsg.EnnMonitorLogTrainMasterJobmsg.WorkerInfo.JobInfo;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJob;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtl;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlEnum;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobStatus;

public class EnnMonitorLogTrainMasterJobmsg {
	
	private static int size = 1;
	
	private static Map<Long, EnnMonitorHeap> bestNNObjectHeapMap = new HashMap<Long, EnnMonitorHeap>();
	private static Map<String, WorkerInfo> workerMap = new HashMap<String, WorkerInfo>();
	
	private static Gson gson = new Gson();
	
	private static final Logger logger = LogManager.getLogger();
	
	synchronized public static List<EnnMonitorLogTrainJob> getJob(String identityId) {
		List<EnnMonitorLogTrainJob> tmpWaitJobQueue = new ArrayList<EnnMonitorLogTrainJob>();
		WorkerInfo workerInfo = null;
		
		logger.debug("get Job");
		
		workerInfo = workerMap.get(identityId);
		if (workerInfo == null) {
			logger.info("createWorker, it is " + identityId);
			
			workerInfo = new WorkerInfo(identityId);
			workerMap.put(identityId, workerInfo);
			
			
		}
		
		tmpWaitJobQueue.addAll(workerInfo.getWaitJobQueue());
		
		return tmpWaitJobQueue;
	}
	
	synchronized public static List<String> getBestNNObjectList(long jobId) {
		int i;
		
		NNObject nnObject = null;
		
		EnnMonitorHeap heap = null;
		List<EnnMonitorHeapData> dataList = null;
		List<String> nnObjectList = new ArrayList<String>();
		
		heap = bestNNObjectHeapMap.get(jobId);
		if (heap == null) {
			return nnObjectList;
		}
		
		dataList = heap.copyDataList();
		
		Collections.sort(dataList, new Comparator<EnnMonitorHeapData>() {

			@Override
			public int compare(EnnMonitorHeapData o1, EnnMonitorHeapData o2) {
				return o2.compare(o1);
			}
			
		});
		
		for (i = 0; i < dataList.size(); ++i) {
			nnObject = ((EnnMonitorLogTrainMasterData)dataList.get(i)).getNNObject();
			logger.info("the bestJob, the error is " + nnObject.getError());
			nnObjectList.add(gson.toJson(nnObject));
		}
		
		return nnObjectList;
	}
	
	synchronized public static List<EnnMonitorLogTrainJobCtl> getJobCtl(String identityId) {
		WorkerInfo workerInfo = null;
		
		Map<Long, JobInfo> jobMap = null;
		EnnMonitorLogTrainJobCtl.Builder jobCtl = null;
		List<EnnMonitorLogTrainJobCtl> jobCtlList = new ArrayList<EnnMonitorLogTrainJobCtl>();
		
		workerInfo = workerMap.get(identityId);
		if (workerInfo == null) {
			return jobCtlList;
		}
		
		jobMap = workerInfo.getJobMap();
		for (Entry<Long, JobInfo> entry : jobMap.entrySet()) {
			jobCtl = EnnMonitorLogTrainJobCtl.newBuilder();
			jobCtl.setCtl(entry.getValue().getJobCtlEnum());
			jobCtl.setJobId(entry.getValue().getJobStatus().getJobId());
			if (entry.getValue().getJobCtlEnum().equals(EnnMonitorLogTrainJobCtlEnum.Replace) == true) {
				entry.getValue().setJobCtlEnum(EnnMonitorLogTrainJobCtlEnum.Continue);
				
				logger.info("replace best job");
				jobCtl.addAllBestJob(getBestNNObjectList(entry.getValue().getJobStatus().getJobId()));
			}
			jobCtlList.add(jobCtl.build());
		}
		
		return jobCtlList;
	}
	
	synchronized public static void reportJobStatus(EnnMonitorLogTrainJobStatus jobStatus) throws Exception {
		NNObject nnObject = null;
		EnnMonitorHeap heap = null;
		EnnMonitorLogTrainMasterData masterData = null;
		WorkerInfo workerInfo = null;
		
		if (jobStatus == null) {
			throw new Exception("the jobStatus is null");
		}
		
		logger.debug("reported jobStatus: " + jobStatus.getBestGeneration() + " VS " + jobStatus.getBestError());
		
		workerInfo = workerMap.get(jobStatus.getIdentityId());
		if (workerInfo == null) {
			throw new Exception("the worker " + jobStatus.getIdentityId() + " is null");
		}
		workerInfo.updateJobStatus(jobStatus);
		
		nnObject = gson.fromJson(jobStatus.getBestJob(), NNObject.class);
		heap = bestNNObjectHeapMap.get(jobStatus.getJobId());
		if (heap == null) {
			return;
		}
		
		if (heap.size() < size) {
			heap.add(new EnnMonitorLogTrainMasterData(nnObject));
		} else {
			masterData = (EnnMonitorLogTrainMasterData) heap.getEnnMonitorHeapData();
			if (nnObject.getError() > masterData.getNNObject().getError()) {
				return;
			}
			
			heap.remove();
			heap.add(new EnnMonitorLogTrainMasterData(nnObject));
		}
	}
	
	// --------------------------------------------------------------------------------------------------------------------------
	
	synchronized public static void deployJob(EnnMonitorLogTrainJob job) {
		long genomNum = (job.getGenomNum() + workerMap.size() - 1) / workerMap.size();
		EnnMonitorLogTrainJob tmpJob = EnnMonitorLogTrainJob.newBuilder(job).setGenomNum(genomNum).build();
		
		if (bestNNObjectHeapMap.containsKey(job.getJobId()) == true) {
			logger.error("the jobId is running");
		}
		
		bestNNObjectHeapMap.put(job.getJobId(), new EnnMonitorHeap());
		
		for (Entry<String, WorkerInfo> entry : workerMap.entrySet()) {
			entry.getValue().addJob(tmpJob);
		}
	}
	
	synchronized public static void stopJob(long jobId) throws Exception {
		logger.info("the jobId is " + jobId);
		
		for (Entry<String, WorkerInfo> entry : workerMap.entrySet()) {
			entry.getValue().stopJob(jobId);
		}
		
		bestNNObjectHeapMap.remove(jobId);
	}
	
	synchronized public static void replaceJob(String identityId, long jobId) throws Exception {
		WorkerInfo workerInfo = null;
		JobInfo jobInfo = null;
		
		if (identityId != null && identityId.equals("") == false) {
			workerInfo = workerMap.get(identityId);
			if (workerInfo == null) {
				return;
			}
			
			jobInfo = workerInfo.getJobInfo(jobId);
			if (jobInfo == null) {
				return;
			}
			
			jobInfo.setJobCtlEnum(EnnMonitorLogTrainJobCtlEnum.Replace);
		} else {
			for (Entry<String, WorkerInfo> entry : workerMap.entrySet()) {
				jobInfo = entry.getValue().getJobInfo(jobId);
				if (jobInfo != null) {
					jobInfo.setJobCtlEnum(EnnMonitorLogTrainJobCtlEnum.Replace);
				}
			}
		}
	}
	
	synchronized public static List<EnnMonitorLogTrainJobStatus> getJobStatus(long jobId) {
		JobInfo jobInfo = null;
		List<EnnMonitorLogTrainJobStatus> jobStatusList = new ArrayList<EnnMonitorLogTrainJobStatus>();
		
		for (Entry<String, WorkerInfo> entry : workerMap.entrySet()) {
			jobInfo = entry.getValue().getJobInfo(jobId);
			if (jobInfo != null) {
				jobStatusList.add(EnnMonitorLogTrainJobStatus.newBuilder(jobInfo.getJobStatus()).clearBestJob().build());
			}
		}
		
		return jobStatusList;
	}
	
	synchronized public static String getBestJob(long jobId) {
		double bestError = 0.0;
		
		JobInfo jobInfo = null;
		String bestJob = null;
		
		for (Entry<String, WorkerInfo> entry : workerMap.entrySet()) {
			jobInfo = entry.getValue().getJobInfo(jobId);
			if (jobInfo == null) {
				continue;
			}
			
			if (bestJob == null) {
				bestJob = jobInfo.getJobStatus().getBestJob();
				bestError = jobInfo.getJobStatus().getBestError();
				continue;
			}
			
			if (bestError > jobInfo.getJobStatus().getBestError()) {
				bestJob = jobInfo.getJobStatus().getBestJob();
				bestError = jobInfo.getJobStatus().getBestError();
			}
		}
		
		return bestJob;
	}
	
	public static class WorkerInfo {
		
		private String identityId = null;
		
		private List<EnnMonitorLogTrainJob> waitJobQueue = new ArrayList<EnnMonitorLogTrainJob>();
		private Map<Long, JobInfo> jobMap = new HashMap<Long, JobInfo>();
		
		public WorkerInfo(String identityId) {
			this.identityId = identityId;
		}
		
		public String getIdentityId() {
			return identityId;
		}

		public void setIdentityId(String identityId) {
			this.identityId = identityId;
		}
		
		public List<EnnMonitorLogTrainJob> getWorkingJobQueue() {
			List<EnnMonitorLogTrainJob> tmpJobList = new ArrayList<EnnMonitorLogTrainJob>(waitJobQueue);
			
			for (Entry<Long, JobInfo> entry : jobMap.entrySet()) {
				tmpJobList.add(entry.getValue().getTrainJob());
			}
			
			return tmpJobList;
		}

		public List<EnnMonitorLogTrainJob> getWaitJobQueue() {
			List<EnnMonitorLogTrainJob> tmpJobList = new ArrayList<EnnMonitorLogTrainJob>(waitJobQueue);
			
			for (EnnMonitorLogTrainJob job : waitJobQueue) {
				jobMap.put(job.getJobId(), new JobInfo(job, EnnMonitorLogTrainJobStatus.newBuilder().setIdentityId(identityId).setJobId(job.getJobId()).build()));
			}
			waitJobQueue.clear();
			
			return tmpJobList;
		}
		
		public Map<Long, JobInfo> getJobMap() {
			return jobMap;
		}
		
		public void addJob(EnnMonitorLogTrainJob job) {
			waitJobQueue.add(job);
		}
		
		public void stopJob(long jobId) {
			EnnMonitorLogTrainJob job = null;
			
			jobMap.remove(jobId);
			
			Iterator<EnnMonitorLogTrainJob> iterator = waitJobQueue.iterator();
			while (iterator.hasNext()) {
				job = iterator.next();
				if (job.getJobId() == jobId) {
					iterator.remove();
				}
			}
		}

		public void updateJobStatus(EnnMonitorLogTrainJobStatus jobStatus) throws Exception {
			JobInfo jobInfo = null;
			
			jobInfo = jobMap.get(jobStatus.getJobId());
			if (jobInfo == null) {
				logger.error("the job is not exists");
				return;
			}
			
			if (jobStatus.getWorstError() < jobStatus.getBestError()) {
				logger.error("the worstError is " + jobStatus.getWorstError() + " the bestError is " + jobStatus.getBestError() + ", the worstError is less than bestError, it is " + jobStatus.getIdentityId());
			}
			
			if (jobInfo.getJobStatus() != null && jobInfo.getJobStatus().getBestGeneration() > 0) {
				if (jobInfo.getJobStatus().getBestError() < jobStatus.getBestError()) {
					logger.error("the old bestError is " + jobInfo.getJobStatus().getBestError() + " the new is " + jobStatus.getBestError() + ", the old bestError is less than new, it is " + jobStatus.getIdentityId());
				}
				
				if (jobInfo.getJobStatus().getWorstError() < jobStatus.getWorstError()) {
					logger.error("the old worstError is " + jobInfo.getJobStatus().getWorstError() + " the new is " + jobStatus.getWorstError() + ", the old worstError is less than new, it is " + jobStatus.getIdentityId());
				}
				
			}
			jobInfo.setJobStatus(jobStatus);
		}

		public JobInfo getJobInfo(long jobId) {
			return jobMap.get(jobId);
		}
		
		public static class JobInfo {
			
			private EnnMonitorLogTrainJobCtlEnum jobCtlEnum = EnnMonitorLogTrainJobCtlEnum.Continue;
			
			private EnnMonitorLogTrainJob trainJob = null;
			private EnnMonitorLogTrainJobStatus jobStatus = null;
			
			public JobInfo(EnnMonitorLogTrainJob trainJob, EnnMonitorLogTrainJobStatus jobStatus) {
				this.trainJob = trainJob;
				this.jobStatus = jobStatus;
			}
			
			public EnnMonitorLogTrainJobCtlEnum getJobCtlEnum() {
				return jobCtlEnum;
			}
			
			public void setJobCtlEnum(EnnMonitorLogTrainJobCtlEnum jobCtlEnum) {
				this.jobCtlEnum = jobCtlEnum;
			}
			
			public EnnMonitorLogTrainJob getTrainJob() {
				return trainJob;
			}

			public void setTrainJob(EnnMonitorLogTrainJob trainJob) {
				this.trainJob = trainJob;
			}

			public EnnMonitorLogTrainJobStatus getJobStatus() {
				return jobStatus;
			}
			
			public void setJobStatus(EnnMonitorLogTrainJobStatus jobStatus) {
				this.jobStatus = jobStatus;
			}
			
		}
		
	}

}
