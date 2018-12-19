package enn.monitor.config.serviceline.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import com.mongodb.BasicDBObject;

import enn.monitor.config.cache.EnnMonitorConfigBlackListCacheUtil;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateRequest;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteRequest;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedData;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetRequest;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineStatus;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineTable;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateRequest;
import enn.monitor.config.serviceline.tablectl.EnnMonitorConfigServiceLineTableCtl;
import enn.monitor.config.serviceline.tablectl.EnnMonitorConfigServiceLineTableField;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerChannelWriteOpType;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerThread;

public class EnnMonitorConfigServiceLineHandler {
	
private static final Logger logger = LogManager.getLogger();
	
	private CounterMetric getServiceLineMetrics = MetricManager.getCounterMetric(EnnMonitorConfigServiceLineHandler.class, "getServiceLine");
	private CounterMetric createServiceLineMetrics = MetricManager.getCounterMetric(EnnMonitorConfigServiceLineHandler.class, "createServiceLine");
	private CounterMetric updateServiceLineMetrics = MetricManager.getCounterMetric(EnnMonitorConfigServiceLineHandler.class, "updateServiceLine");
	
	private EnnMonitorConfigServiceLineTableCtl serviceLineTable = null;
	private EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl = null;
	private EnnMonitorFrameworkPipeServerThread pipeServer = null;
	
	public EnnMonitorConfigServiceLineHandler(EnnMonitorConfigServiceLineTableCtl serviceLineTable, 
			EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl,
			EnnMonitorFrameworkPipeServerThread pipeServer) {
		this.serviceLineTable = serviceLineTable;
		this.deletedTableCtl = deletedTableCtl;
		this.pipeServer = pipeServer;
	}
	
	public List<EnnMonitorConfigServiceLineTable> getEnnMonitorConfigServiceLine(EnnMonitorConfigServiceLineGetRequest request) throws Exception {
		List<EnnMonitorConfigServiceLineTable> serviceLineTableList = null;
		
		BasicDBObject basicDBObject = new BasicDBObject();
		
		if (request.getId() > 0) {
			basicDBObject.put(EnnMonitorConfigServiceLineTableField.IdFieldName, request.getId());
		}
		
		if (request.getServiceLineName() != null && request.getServiceLineName().equals("") == false) {
			basicDBObject.put(EnnMonitorConfigServiceLineTableField.ServiceLineNameFieldName, request.getServiceLineName());
		}
		
		if (request.getStatus().equals(EnnMonitorConfigServiceLineStatus.ServiceLineDefault) == false) {
			basicDBObject.put(EnnMonitorConfigServiceLineTableField.StatusFieldName, request.getStatus().name());
		}
		
		if (request.getBelongToCluster() > 0) {
			basicDBObject.put(EnnMonitorConfigServiceLineTableField.BelongToClusterFieldName, request.getBelongToCluster());
		}
		
		if (request.getCreateUser() != null && request.getCreateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorConfigServiceLineTableField.CreateUserFieldName, request.getCreateUser());
		}
		
		if (request.getLastUpdateUser() != null && request.getLastUpdateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorConfigServiceLineTableField.LastUpdateUserFieldName, request.getLastUpdateUser());
		}
		
		serviceLineTableList = serviceLineTable.getMongoDBCtrl().searchAllData(basicDBObject, null);
		
		getServiceLineMetrics.markEvent();
		
		return serviceLineTableList;
	}
	
	public void createEnnMonitorConfigServiceLine(EnnMonitorConfigServiceLineCreateRequest request) throws Exception {
		EnnMonitorConfigServiceLineTable.Builder serviceLineBuilder = EnnMonitorConfigServiceLineTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
    	
    	logger.info("createServiceLine request: " + request.toString());
    	
		if (request.getCreateUser() == null || request.getCreateUser().equals("") == true) {
			throw new Exception("createUser is null");
		}
		serviceLineBuilder.setCreateUser(request.getCreateUser());
		serviceLineBuilder.setLastUpdateUser(request.getCreateUser());
		serviceLineBuilder.setCreateTime(System.currentTimeMillis());
		serviceLineBuilder.setLastUpdateTime(serviceLineBuilder.getCreateTime());
		
		if (request.getServiceLineName() == null || request.getServiceLineName().equals("") == true) {
			throw new Exception("serviceLineName is null");
		}
		serviceLineBuilder.setServiceLineName(request.getServiceLineName());
		
		if (request.getBelongToCluster() <= 0) {
			throw new Exception("the belongToCluster is 0");
		}
		serviceLineBuilder.setBelongToCluster(request.getBelongToCluster());
		
		serviceLineBuilder.setStatus(EnnMonitorConfigServiceLineStatus.ServiceLineRunning);
		
		createServiceLineMetrics.markEvent();
		
		channelWriteData = new ChannelWriteData();
		channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Insert);
		channelWriteData.setObject(serviceLineBuilder.build());
		pipeServer.write(channelWriteData);
	}
	
	public void updateEnnMonitorConfigServiceLine(EnnMonitorConfigServiceLineUpdateRequest request) throws Exception {
		EnnMonitorConfigServiceLineTable.Builder serviceLineBuilder = EnnMonitorConfigServiceLineTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
		
		logger.info("updateServiceLine request: " + request.toString());
		
		if (request.getId() < 0) {
    		throw new Exception("the id is null");
    	} else {
			serviceLineBuilder.setId(request.getId());
			
			if (request.getLastUpdateUser() == null || request.getLastUpdateUser().equals("") == true) {
				throw new Exception("lastUpdateUser is null");
			}
			serviceLineBuilder.setLastUpdateUser(request.getLastUpdateUser());
			serviceLineBuilder.setLastUpdateTime(System.currentTimeMillis());
			
			if (request.getServiceLineName() != null && request.getServiceLineName().equals("") == false) {
				serviceLineBuilder.setServiceLineName(request.getServiceLineName());
			}
			
			if (request.getStatus().equals(EnnMonitorConfigServiceLineStatus.ServiceLineDefault) == false &&
					request.getStatus().equals(EnnMonitorConfigServiceLineStatus.ServiceLineRunning) == false) {
				serviceLineBuilder.setStatus(request.getStatus());
			}
			
			if (request.getBelongToCluster() > 0) {
				serviceLineBuilder.setBelongToCluster(request.getBelongToCluster());
			}
			
			updateServiceLineMetrics.markEvent();
			
			channelWriteData = new ChannelWriteData();
			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Update);
			channelWriteData.setObject(serviceLineBuilder.build());
			pipeServer.write(channelWriteData);
    	}
	}
	
	public void deleteEnnMonitorConfigServiceLine(EnnMonitorConfigServiceLineDeleteRequest request) throws Exception {
		EnnMonitorConfigServiceLineUpdateRequest.Builder requestBuidler = EnnMonitorConfigServiceLineUpdateRequest.newBuilder();
		
		logger.info("deleteServiceLine request: " + request.toString());
		
		if (request.getId() > 0 && request.getLastUpdateUser() != null && request.getLastUpdateUser().trim().equals("") == false) {
			EnnMonitorConfigBlackListCacheUtil.addServicLineId(request.getId());
			
			requestBuidler.setId(request.getId());
			requestBuidler.setStatus(EnnMonitorConfigServiceLineStatus.ServiceLineDeleting);
			requestBuidler.setLastUpdateUser(request.getLastUpdateUser());
			updateEnnMonitorConfigServiceLine(requestBuidler.build());
		}
    }
	
	public void purgeEnnMonitorConfigServiceLine(EnnMonitorConfigServiceLineDeleteRequest request) throws Exception {
		EnnMonitorConfigServiceLineTable.Builder serviceLineBuilder = EnnMonitorConfigServiceLineTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
		
		logger.info("purgeService request: " + request.toString());
		
		if (request.getId() < 0) {
    		throw new Exception("the id is null");
    	}
		
		serviceLineBuilder.setId(request.getId());
		
		channelWriteData = new ChannelWriteData();
		channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Delete);
		channelWriteData.setObject(serviceLineBuilder.build());
		pipeServer.write(channelWriteData);
	}

	public long getEnnMonitorConfigServiceLineMaxDeletedSeqNo() throws Exception {
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
	
	public List<EnnMonitorConfigServiceLineTable> getEnnMonitorConfigServiceLineValid(long startSeqNo, int batch) throws Exception {
		BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	List<EnnMonitorConfigServiceLineTable> serviceLineTableList = null;
    	
    	query.put(EnnMonitorConfigServiceLineTableField.SeqNoFieldName, new BasicDBObject("$gt", startSeqNo));
    	order.put(EnnMonitorConfigServiceLineTableField.SeqNoFieldName, 1);
    	
		serviceLineTableList = serviceLineTable.getMongoDBCtrl().searchData(query, order, 0, batch);
		
		return serviceLineTableList;
	}
	
	public List<EnnMonitorConfigServiceLineGetDeletedData> getEnnMonitorConfigServiceLineDeleted(long startSeqNo, int batch) throws Exception {
		BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	EnnMonitorConfigServiceLineGetDeletedData.Builder deletedData = null;
    	
    	List<EnnMonitorFrameworkPipeDeletedTable> deletedTableList = null;
    	List<EnnMonitorConfigServiceLineGetDeletedData> deletedDataList = new ArrayList<EnnMonitorConfigServiceLineGetDeletedData>();
    	
    	query.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, new BasicDBObject("$gt", startSeqNo));
    	order.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, 1);
    	
		deletedTableList = deletedTableCtl.getMongoDBCtrl().searchData(query, order, 0, batch);
		if (deletedTableList != null) {
			for (EnnMonitorFrameworkPipeDeletedTable deletedTable : deletedTableList) {
				deletedData = EnnMonitorConfigServiceLineGetDeletedData.newBuilder();
				deletedData.setId(deletedTable.getId());
				deletedData.setSeqNo(deletedTable.getSeqNo());
				deletedDataList.add(deletedData.build());
    		}
		}
		
		return deletedDataList;
	}

	public boolean checkServiceLineExistedOrNot(String serviceLineName) {
		BasicDBObject queryCond = new BasicDBObject();

		queryCond.put(EnnMonitorConfigServiceLineTableField.ServiceLineNameFieldName, serviceLineName);

		return serviceLineTable.getMongoDBCtrl().checkExistedFieldValue(queryCond);
	}

}
