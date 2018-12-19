package enn.monitor.config.cluster.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import com.mongodb.BasicDBObject;

import enn.monitor.config.cache.EnnMonitorConfigBlackListCacheUtil;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedData;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterStatus;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterTable;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateRequest;
import enn.monitor.config.cluster.tablectl.EnnMonitorConfigClusterTableCtl;
import enn.monitor.config.cluster.tablectl.EnnMonitorConfigClusterTableField;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerChannelWriteOpType;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerThread;

public class EnnMonitorConfigClusterHandler {
	
private static final Logger logger = LogManager.getLogger();
	
	private CounterMetric getClusterMetrics = MetricManager.getCounterMetric(EnnMonitorConfigClusterHandler.class, "getCluster");
	private CounterMetric createClusterMetrics = MetricManager.getCounterMetric(EnnMonitorConfigClusterHandler.class, "createCluster");
	private CounterMetric updateClusterMetrics = MetricManager.getCounterMetric(EnnMonitorConfigClusterHandler.class, "updateCluster");
	
	private EnnMonitorConfigClusterTableCtl clusterTable = null;
	private EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl = null;
	private EnnMonitorFrameworkPipeServerThread pipeServer = null;
	
	public EnnMonitorConfigClusterHandler(EnnMonitorConfigClusterTableCtl clusterTable,
			EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl,
			EnnMonitorFrameworkPipeServerThread pipeServer) {
		this.clusterTable = clusterTable;
		this.deletedTableCtl = deletedTableCtl;
		this.pipeServer = pipeServer;
	}
	
	public List<EnnMonitorConfigClusterTable> getEnnMonitorConfigCluster(EnnMonitorConfigClusterGetRequest request) throws Exception {
		List<EnnMonitorConfigClusterTable> clusterTableList = null;
		
		BasicDBObject basicDBObject = new BasicDBObject();
		
		if (request.getId() > 0) {
			basicDBObject.put(EnnMonitorConfigClusterTableField.IdFieldName, request.getId());
		}
		
		if (request.getClusterName() != null && request.getClusterName().equals("") == false) {
			basicDBObject.put(EnnMonitorConfigClusterTableField.ClusterNameFieldName, request.getClusterName());
		}
		
		if (request.getStatus().equals(EnnMonitorConfigClusterStatus.ClusterDefault) == false) {
			basicDBObject.put(EnnMonitorConfigClusterTableField.StatusFieldName, request.getStatus().name());
		}
		
		if (request.getCreateUser() != null && request.getCreateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorConfigClusterTableField.CreateUserFieldName, request.getCreateUser());
		}
		
		if (request.getLastUpdateUser() != null && request.getLastUpdateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorConfigClusterTableField.LastUpdateUserFieldName, request.getLastUpdateUser());
		}
		
		clusterTableList = clusterTable.getMongoDBCtrl().searchAllData(basicDBObject, null);
		
		getClusterMetrics.markEvent();
		
		return clusterTableList;
	}
	
	public void createEnnMonitorConfigCluster(EnnMonitorConfigClusterCreateRequest request) throws Exception {
		EnnMonitorConfigClusterTable.Builder clusterBuilder = EnnMonitorConfigClusterTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
    	
    	logger.info("createCluster request: " + request.toString());
    	
		if (request.getCreateUser() == null || request.getCreateUser().equals("") == true) {
			throw new Exception("createUser is null");
		}
		clusterBuilder.setCreateUser(request.getCreateUser());
		clusterBuilder.setLastUpdateUser(request.getCreateUser());
		clusterBuilder.setCreateTime(System.currentTimeMillis());
		clusterBuilder.setLastUpdateTime(clusterBuilder.getCreateTime());
		
		if (request.getClusterName() == null || request.getClusterName().equals("") == true) {
			throw new Exception("clusterName is null");
		}
		clusterBuilder.setClusterName(request.getClusterName());
		
		clusterBuilder.setStatus(EnnMonitorConfigClusterStatus.ClusterRunning);
		
		createClusterMetrics.markEvent();
		
		channelWriteData = new ChannelWriteData();
		channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Insert);
		channelWriteData.setObject(clusterBuilder.build());
		pipeServer.write(channelWriteData);
	}
	
	public void updateEnnMonitorConfigCluster(EnnMonitorConfigClusterUpdateRequest request) throws Exception {
		EnnMonitorConfigClusterTable.Builder clusterBuilder = EnnMonitorConfigClusterTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
		
		logger.info("updateCluster request: " + request.toString());
		
		if (request.getId() < 0) {
    		throw new Exception("the id is null");
    	} else {
			clusterBuilder.setId(request.getId());
			
			if (request.getLastUpdateUser() == null || request.getLastUpdateUser().equals("") == true) {
				throw new Exception("lastUpdateUser is null");
			}
			clusterBuilder.setLastUpdateUser(request.getLastUpdateUser());
			clusterBuilder.setLastUpdateTime(System.currentTimeMillis());
			
			if (request.getClusterName() != null && request.getClusterName().equals("") == false) {
				clusterBuilder.setClusterName(request.getClusterName());
			}
			
			if (request.getStatus().equals(EnnMonitorConfigClusterStatus.ClusterDefault) == false && 
					request.getStatus().equals(EnnMonitorConfigClusterStatus.ClusterRunning) == false) {
				clusterBuilder.setStatus(request.getStatus());
			}
			
			updateClusterMetrics.markEvent();
			
			channelWriteData = new ChannelWriteData();
			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Update);
			channelWriteData.setObject(clusterBuilder.build());
			pipeServer.write(channelWriteData);
    	}
	}
	
	public void deleteEnnMonitorConfigCluster(EnnMonitorConfigClusterDeleteRequest request) throws Exception {
		EnnMonitorConfigClusterUpdateRequest.Builder requestBuidler = EnnMonitorConfigClusterUpdateRequest.newBuilder();
		
		logger.info("deleteCluster request: " + request.toString());
		
		if (request.getId() > 0 && request.getLastUpdateUser() != null && request.getLastUpdateUser().trim().equals("") == false) {
			EnnMonitorConfigBlackListCacheUtil.addClusterId(request.getId());
			
			requestBuidler.setId(request.getId());
			requestBuidler.setStatus(EnnMonitorConfigClusterStatus.ClusterDeleting);
			requestBuidler.setLastUpdateUser(request.getLastUpdateUser());
			updateEnnMonitorConfigCluster(requestBuidler.build());
		}
	}
	
	public void purgeEnnMonitorConfigCluster(EnnMonitorConfigClusterDeleteRequest request) throws Exception {
		EnnMonitorConfigClusterTable.Builder clusterBuilder = EnnMonitorConfigClusterTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
		
		logger.info("purgeCluster request: " + request.toString());
		
		if (request.getId() < 0) {
    		throw new Exception("the id is null");
    	}
		
		clusterBuilder.setId(request.getId());
		
		channelWriteData = new ChannelWriteData();
		channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Delete);
		channelWriteData.setObject(clusterBuilder.build());
		pipeServer.write(channelWriteData);
	}

	public long getEnnMonitorConfigClusterMaxDeletedSeqNo() throws Exception {
		BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	long seqNo = -1;
    	
    	List<EnnMonitorFrameworkPipeDeletedTable> deletedTableList = null; 
    	
    	order.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, -1);
    	deletedTableList = deletedTableCtl.getMongoDBCtrl().searchData(query, order, 0, 1);
    	if (deletedTableList == null || deletedTableList.size() <= 0) {
			seqNo = -1;
		} else {
			seqNo = deletedTableList.get(0).getSeqNo();
		}
		
		return seqNo;
	}
	
	public List<EnnMonitorConfigClusterTable> getEnnMonitorConfigClusterValid(long startSeqNo, int batch) throws Exception {
		BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	List<EnnMonitorConfigClusterTable> clusterTableList = null;
    	
    	query.put(EnnMonitorConfigClusterTableField.SeqNoFieldName, new BasicDBObject("$gt", startSeqNo));
    	order.put(EnnMonitorConfigClusterTableField.SeqNoFieldName, 1);
    	
		clusterTableList = clusterTable.getMongoDBCtrl().searchData(query, order, 0, batch);
		
		return clusterTableList;
	}
	
	public List<EnnMonitorConfigClusterGetDeletedData> getEnnMonitorConfigClusterDeleted(long startSeqNo, int batch) throws Exception {
		BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	EnnMonitorConfigClusterGetDeletedData.Builder deletedData = null;
    	
    	List<EnnMonitorFrameworkPipeDeletedTable> deletedTableList = null;
    	List<EnnMonitorConfigClusterGetDeletedData> deletedDataList = new ArrayList<>();
    	
    	query.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, new BasicDBObject("$gt", startSeqNo));
    	order.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, 1);
    	
		deletedTableList = deletedTableCtl.getMongoDBCtrl().searchData(query, order, 0, batch);
		if (deletedTableList != null) {
			for (EnnMonitorFrameworkPipeDeletedTable deletedTable : deletedTableList) {
				deletedData = EnnMonitorConfigClusterGetDeletedData.newBuilder();
				deletedData.setId(deletedTable.getId());
				deletedData.setSeqNo(deletedTable.getSeqNo());
				deletedDataList.add(deletedData.build());
    		}
		}
		
		return deletedDataList;
	}

	public boolean checkClusterExistedOrNot(String clusterName) {
		BasicDBObject queryCond = new BasicDBObject();

		queryCond.put(EnnMonitorConfigClusterTableField.ClusterNameFieldName, clusterName);

		return clusterTable.getMongoDBCtrl().checkExistedFieldValue(queryCond);
	}

}
