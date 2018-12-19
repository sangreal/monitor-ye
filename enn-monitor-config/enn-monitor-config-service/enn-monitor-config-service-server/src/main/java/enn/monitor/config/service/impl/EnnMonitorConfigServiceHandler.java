package enn.monitor.config.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import com.mongodb.BasicDBObject;

import enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceStatus;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateRequest;
import enn.monitor.config.service.tablectl.EnnMonitorConfigServiceTableCtl;
import enn.monitor.config.service.tablectl.EnnMonitorConfigServiceTableField;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerChannelWriteOpType;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerThread;

public class EnnMonitorConfigServiceHandler {

	private static final Logger logger = LogManager.getLogger();
	
	private CounterMetric getServiceMetrics = MetricManager.getCounterMetric(EnnMonitorConfigServiceHandler.class, "getService");
	private CounterMetric countServiceMetrics = MetricManager.getCounterMetric(EnnMonitorConfigServiceHandler.class, "countService");
	private CounterMetric createServiceMetrics = MetricManager.getCounterMetric(EnnMonitorConfigServiceHandler.class, "createService");
	private CounterMetric updateServiceMetrics = MetricManager.getCounterMetric(EnnMonitorConfigServiceHandler.class, "updateService");
	private CounterMetric deleteServiceMetrics = MetricManager.getCounterMetric(EnnMonitorConfigServiceHandler.class, "deleteService");
	
	private EnnMonitorConfigServiceTableCtl serviceTable = null;
	private EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl = null;
	private EnnMonitorFrameworkPipeServerThread pipeServer = null;
	
	public EnnMonitorConfigServiceHandler(EnnMonitorConfigServiceTableCtl serviceTable, 
			EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl,
			EnnMonitorFrameworkPipeServerThread pipeServer) {
		this.serviceTable = serviceTable;
		this.deletedTableCtl = deletedTableCtl;
		this.pipeServer = pipeServer;
	}
	
	private BasicDBObject getBasicDBObject(EnnMonitorConfigServiceGetRequest request) {
		BasicDBObject basicDBObject = new BasicDBObject();
		
		if (request.getId() > 0) {
			basicDBObject.put(EnnMonitorConfigServiceTableField.IdFieldName, request.getId());
		}
		
		if (request.getServiceName() != null && request.getServiceName().equals("") == false) {
			basicDBObject.put(EnnMonitorConfigServiceTableField.ServiceNameFieldName, request.getServiceName());
		}
		
		if (request.getBelongToServiceLine() > 0) {
			basicDBObject.put(EnnMonitorConfigServiceTableField.BelongToServiceLineFieldName, request.getBelongToServiceLine());
		}
		
		if (request.getToken() != null && request.getToken().equals("") == false) {
			basicDBObject.put(EnnMonitorConfigServiceTableField.TokenFieldName, request.getToken());
		}
		
		if (request.getStatus().equals(EnnMonitorConfigServiceStatus.ServiceDefault) == false) {
			basicDBObject.put(EnnMonitorConfigServiceTableField.StatusFieldName, request.getStatus().name());
		}
		
		if (request.getOwner() != null && request.getOwner().equals("") == false) {
			basicDBObject.put(EnnMonitorConfigServiceTableField.OwnerFieldName, request.getOwner());
		}
		
		if (request.getGuest() != null && request.getGuest().equals("") == false) {
			basicDBObject.put(EnnMonitorConfigServiceTableField.GuestFieldName, request.getGuest());
		}
		
		return basicDBObject;
	}
	
	public List<EnnMonitorConfigServiceTable> getEnnMonitorConfigService(EnnMonitorConfigServiceGetRequest request) throws Exception {
		List<EnnMonitorConfigServiceTable> serviceTableList = null;
		
		BasicDBObject basicDBObject = getBasicDBObject(request);

		serviceTableList = serviceTable.getMongoDBCtrl().searchAllData(basicDBObject, null);
		
		getServiceMetrics.markEvent();
		
		return serviceTableList;
	}
	
	public long countEnnMonitorConfigService(EnnMonitorConfigServiceGetRequest request) throws Exception {
		long count = 0l;
		
		BasicDBObject basicDBObject = getBasicDBObject(request);
		
		count = serviceTable.getMongoDBCtrl().getCount(basicDBObject);
		
		countServiceMetrics.markEvent();
		
		return count;
	}
	
	public void createEnnMonitorConfigService(EnnMonitorConfigServiceCreateRequest request) throws Exception {
		EnnMonitorConfigServiceTable.Builder serviceBuilder = EnnMonitorConfigServiceTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
    	
    	logger.info("createService request: " + request.toString());
    	
		if (request.getServiceName() == null || request.getServiceName().equals("") == true) {
			throw new Exception("serviceName is null");
		}
		serviceBuilder.setServiceName(request.getServiceName());
		
		if (request.getBelongToServiceLine() <= 0) {
			throw new Exception("the belongToServiceLine is null");
		}
		serviceBuilder.setBelongToServiceLine(request.getBelongToServiceLine());
		
		if (request.getToken() == null || request.getToken().equals("") == true) {
			throw new Exception("token is null");
		}
		serviceBuilder.setToken(request.getToken());
		
		serviceBuilder.setStatus(EnnMonitorConfigServiceStatus.ServiceRunning);
		
		if (request.getOwner() == null || request.getOwner().equals("") == true) {
			throw new Exception("the owner is null");
		}
		serviceBuilder.setOwner(request.getOwner());
		
		if (request.getGuestList() != null && request.getGuestList().size() > 0) {
			serviceBuilder.addAllGuest(request.getGuestList());
		}
		
		serviceBuilder.setCreateTime(System.currentTimeMillis());
		serviceBuilder.setLastUpdateTime(serviceBuilder.getCreateTime());
		
		createServiceMetrics.markEvent();
		
		channelWriteData = new ChannelWriteData();
		channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Insert);
		channelWriteData.setObject(serviceBuilder.build());
		pipeServer.write(channelWriteData);
	}
	
	public void updateEnnMonitorConfigService(EnnMonitorConfigServiceUpdateRequest request) throws Exception {
		EnnMonitorConfigServiceTable.Builder serviceBuilder = EnnMonitorConfigServiceTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
		
		logger.info("updateService request: " + request.toString());
		
		if (request.getId() < 0) {
    		throw new Exception("the id is null");
    	} else {
			serviceBuilder.setId(request.getId());
			
			if (request.getServiceName() != null && request.getServiceName().equals("") == false) {
				serviceBuilder.setServiceName(request.getServiceName());
			}
			
			if (request.getBelongToServiceLine() > 0) {
				serviceBuilder.setBelongToServiceLine(request.getBelongToServiceLine());
			}
			
			if (request.getToken() != null && request.getToken().equals("") == false) {
				serviceBuilder.setToken(request.getToken());
			}
			
			if (request.getStatus().equals(EnnMonitorConfigServiceStatus.ServiceDefault) == false) {
				serviceBuilder.setStatus(request.getStatus());
			}
			
			if (request.getOwner() != null && request.getOwner().equals("") == false) {
				serviceBuilder.setOwner(request.getOwner());
			}
			
			if (request.getGuestList() != null && request.getGuestList().size() > 0) {
				serviceBuilder.addAllGuest(request.getGuestList());
			}
			
			serviceBuilder.setLastUpdateTime(System.currentTimeMillis());
			
			updateServiceMetrics.markEvent();
			
			channelWriteData = new ChannelWriteData();
			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Update);
			channelWriteData.setObject(serviceBuilder.build());
			pipeServer.write(channelWriteData);
    	}
	}
	
	public void deleteEnnMonitorConfigService(EnnMonitorConfigServiceDeleteRequest request) throws Exception {
		EnnMonitorConfigServiceTable.Builder serviceBuilder = EnnMonitorConfigServiceTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
		
		logger.info("deleteService request: " + request.toString());
		
		if (request.getId() < 0) {
    		throw new Exception("the id is null");
    	}
		
		serviceBuilder.setId(request.getId());
		
		channelWriteData = new ChannelWriteData();
		channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Delete);
		channelWriteData.setObject(serviceBuilder.build());
		pipeServer.write(channelWriteData);
		
		deleteServiceMetrics.markEvent();
	}
	
	public long getEnnMonitorConfigServiceMaxDeletedSeqNo() throws Exception {
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
	
	public List<EnnMonitorConfigServiceTable> getEnnMonitorConfigServiceValid(long startSeqNo, int batch) throws Exception {
		BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	List<EnnMonitorConfigServiceTable> serviceTableList = null;
    	
    	query.put(EnnMonitorConfigServiceTableField.SeqNoFieldName, new BasicDBObject("$gt", startSeqNo));
    	order.put(EnnMonitorConfigServiceTableField.SeqNoFieldName, 1);
    	
		serviceTableList = serviceTable.getMongoDBCtrl().searchData(query, order, 0, batch);
		
		return serviceTableList;
	}
	
	public List<EnnMonitorConfigServiceGetDeletedData> getEnnMonitorConfigServiceDeleted(long startSeqNo, int batch) throws Exception {
		BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	EnnMonitorConfigServiceGetDeletedData.Builder deletedData = null;
    	
    	List<EnnMonitorFrameworkPipeDeletedTable> deletedTableList = null;
    	List<EnnMonitorConfigServiceGetDeletedData> deletedDataList = new ArrayList<EnnMonitorConfigServiceGetDeletedData>();
    	
    	query.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, new BasicDBObject("$gt", startSeqNo));
    	order.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, 1);
    	
		deletedTableList = deletedTableCtl.getMongoDBCtrl().searchData(query, order, 0, batch);
		if (deletedTableList != null) {
			for (EnnMonitorFrameworkPipeDeletedTable deletedTable : deletedTableList) {
				deletedData = EnnMonitorConfigServiceGetDeletedData.newBuilder();
				deletedData.setId(deletedTable.getId());
				deletedData.setSeqNo(deletedTable.getSeqNo());
				deletedDataList.add(deletedData.build());
    		}
		}
		
		return deletedDataList;
	}

	public boolean checkServiceExistedOrNot(long id, String serviceName) {
		BasicDBObject queryCond = new BasicDBObject();

		queryCond.put(EnnMonitorConfigServiceTableField.BelongToServiceLineFieldName, id);
		queryCond.put(EnnMonitorConfigServiceTableField.ServiceNameFieldName, serviceName);

		return serviceTable.getMongoDBCtrl().checkExistedFieldValue(queryCond);
	}

}
