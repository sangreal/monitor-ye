package enn.monitor.alarm.ticket.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import com.mongodb.BasicDBObject;

import enn.monitor.alarm.ticket.cache.channel.EnnMonitorAlarmTicketCacheChannel;
import enn.monitor.alarm.ticket.cache.channel.EnnMonitorAlarmTicketCacheOpEnum;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeleted;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketLevel;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketStatus;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTable;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateRequest;
import enn.monitor.alarm.ticket.tablectl.EnnMonitorAlarmTicketTableCtl;
import enn.monitor.alarm.ticket.tablectl.EnnMonitorAlarmTicketTableField;
import enn.monitor.framework.common.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;

public class EnnMonitorAlarmTicketHandler {
	
	private static final Logger logger = LogManager.getLogger();
	
	private CounterMetric getTicketMetrics = MetricManager.getCounterMetric(EnnMonitorAlarmTicketHandler.class, "getTicket");
	private CounterMetric createTicketMetrics = MetricManager.getCounterMetric(EnnMonitorAlarmTicketHandler.class, "createTicket");
	private CounterMetric updateTicketMetrics = MetricManager.getCounterMetric(EnnMonitorAlarmTicketHandler.class, "updateTicket");
	private CounterMetric deleteTicketMetrics = MetricManager.getCounterMetric(EnnMonitorAlarmTicketHandler.class, "deleteTicket");
	private CounterMetric updateStateMetrics = MetricManager.getCounterMetric(EnnMonitorAlarmTicketHandler.class, "updateStateMetrics");
	
	private MongoAutoIncTableCtl autoIncTableCtl = null;
	
	private EnnMonitorAlarmTicketTableCtl ticketTable = null;
	private EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl = null;
	
	private EnnMonitorAlarmTicketCacheChannel cacheChannel = null;
	
	public EnnMonitorAlarmTicketHandler(MongoAutoIncTableCtl autoIncTableCtl,
			EnnMonitorAlarmTicketTableCtl ticketTable, EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl) {
		this.autoIncTableCtl = autoIncTableCtl;
		this.ticketTable = ticketTable;
		this.deletedTableCtl = deletedTableCtl;
	}
	
	public void setEnnMonitorAlarmTicketCacheChannel(EnnMonitorAlarmTicketCacheChannel cacheChannel) {
		this.cacheChannel = cacheChannel;
	}
	
	public List<EnnMonitorAlarmTicketTable> getEnnMonitorAlarmTicket(EnnMonitorAlarmTicketGetRequest request) throws Exception {
		List<EnnMonitorAlarmTicketTable> ticketTableList = null;
		
		BasicDBObject basicDBObject = new BasicDBObject();
		
		if (request.getId() > 0) {
			basicDBObject.put(EnnMonitorAlarmTicketTableField.IdFieldName, request.getId());
		}
		
		if (request.getTicketKey() != null && request.getTicketKey().equals("") == false) {
			basicDBObject.put(EnnMonitorAlarmTicketTableField.TicketKeyFieldName, request.getTicketKey());
		}
		
		if (request.getEnnMonitorAlarmTicketStatus().equals(EnnMonitorAlarmTicketStatus.EnnMonitorAlarmTicketStatusDefault) == false) {
			basicDBObject.put(EnnMonitorAlarmTicketTableField.EnnMonitorAlarmTicketStatusFieldName, request.getEnnMonitorAlarmTicketStatus().name());
		}
		
		if (request.getEnnMonitorAlarmTicketLevel().equals(EnnMonitorAlarmTicketLevel.EnnMonitorAlarmTicketLevelDefault) == false) {
			basicDBObject.put(EnnMonitorAlarmTicketTableField.EnnMonitorAlarmTicketLevelFieldName, request.getEnnMonitorAlarmTicketLevel().name());
		}
		
		if (request.getGrourName() != null && request.getGrourName().equals("") == false) {
			basicDBObject.put(EnnMonitorAlarmTicketTableField.GroupNameFieldName, request.getGrourName());
		}
		
		if (request.getClusterName() != null && request.getClusterName().equals("") == false) {
			basicDBObject.put(EnnMonitorAlarmTicketTableField.ClusterNameFieldName, request.getClusterName());
		}
		
		if (request.getIp() != null && request.getIp().equals("") == false) {
			basicDBObject.put(EnnMonitorAlarmTicketTableField.IPFieldName, request.getIp());
		}
		
		if (request.getNameSpace() != null && request.getNameSpace().equals("") == false) {
			basicDBObject.put(EnnMonitorAlarmTicketTableField.NameSpaceFieldName, request.getNameSpace());
		}
		
		if (request.getPodName() != null && request.getPodName().equals("") == false) {
			basicDBObject.put(EnnMonitorAlarmTicketTableField.PodNameFieldName, request.getPodName());
		}
		
		if (request.getAppName() != null && request.getAppName().equals("") == false) {
			basicDBObject.put(EnnMonitorAlarmTicketTableField.AppNameFieldName, request.getAppName());
		}
		
		if (request.getCreateUser() != null && request.getCreateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorAlarmTicketTableField.CreateUserFieldName, request.getCreateUser());
		}
		
		if (request.getLastUpdateUser() != null && request.getLastUpdateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorAlarmTicketTableField.LastUpdateUserFieldName, request.getLastUpdateUser());
		}
		
		ticketTableList = ticketTable.getMongoDBCtrl().searchAllData(basicDBObject, null);
		
		getTicketMetrics.markEvent();
		
		return ticketTableList;
	}
	
	public void createEnnMonitorAlarmTicket(EnnMonitorAlarmTicketCreateRequest request) throws Exception {
		EnnMonitorAlarmTicketTable.Builder ticketBuilder = EnnMonitorAlarmTicketTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
    	
    	ticketBuilder.setId(autoIncTableCtl.autoInc(ticketTable.getTableName() + "_ID"));
		
		if (request.getCreateUser() == null || request.getCreateUser().equals("") == true) {
			throw new Exception("createUser is null");
		}
		ticketBuilder.setCreateUser(request.getCreateUser());
		ticketBuilder.setLastUpdateUser(request.getCreateUser());
		ticketBuilder.setCreateTime(System.currentTimeMillis());
		ticketBuilder.setLastUpdateTime(ticketBuilder.getCreateTime());
		
		if (request.getTicketKey() == null || request.getTicketKey().equals("") == true) {
			throw new Exception("ticketKey is null");
		}
		ticketBuilder.setTicketKey(request.getTicketKey());
		
		ticketBuilder.setEnnMonitorAlarmTicketStatus(request.getEnnMonitorAlarmTicketStatus());
		
		if (request.getEnnMonitorAlarmTicketLevel().equals(EnnMonitorAlarmTicketLevel.EnnMonitorAlarmTicketLevelDefault) == true) {
			throw new Exception("the ennMonitorAlarmTicketLevel is null");
		}
		ticketBuilder.setEnnMonitorAlarmTicketLevel(request.getEnnMonitorAlarmTicketLevel());
		
		ticketBuilder.setAutomation(request.getAutomation());
		
		if (request.getGrourName() == null && request.getGrourName().equals("") == true) {
			throw new Exception("groupName is null");
		}
		ticketBuilder.setGrourName(request.getGrourName());
		
		if (request.getClusterName() == null || request.getClusterName().equals("") == true) {
			throw new Exception("clusterName is null");
		}
		ticketBuilder.setClusterName(request.getClusterName());
		
		if (request.getIp() != null && request.getIp().equals("") == false) {
			ticketBuilder.setIp(request.getIp());
		}
		
		if (request.getNameSpace() != null && request.getNameSpace().equals("") == false) {
			ticketBuilder.setNameSpace(request.getNameSpace());
		}
		
		if (request.getPodName() != null && request.getPodName().equals("") == false) {
			ticketBuilder.setPodName(request.getPodName());
		}
		
		if (request.getAppName() != null && request.getAppName().equals("") == false) {
			ticketBuilder.setAppName(request.getAppName());
		}
		
		if (request.getError() != null && request.getError().equals("") == false) {
			ticketBuilder.setError(request.getError());
		}
		
		if (request.getErrorReason() != null && request.getErrorReason().equals("") == false) {
			ticketBuilder.setErrorReason(request.getErrorReason());
		}
		
		if (request.getRemark() != null && request.getRemark().equals("") == false) {
			ticketBuilder.setRemark(request.getRemark());
		}
		
		createTicketMetrics.markEvent();
		
		channelWriteData = new ChannelWriteData();
		channelWriteData.setOpEnum(EnnMonitorAlarmTicketCacheOpEnum.Insert);
		channelWriteData.setObject(ticketBuilder.build());
		cacheChannel.write(channelWriteData);
	}
	
	public void updateEnnMonitorAlarmTicket(EnnMonitorAlarmTicketUpdateRequest request) throws Exception {
		EnnMonitorAlarmTicketTable.Builder ticketBuilder = EnnMonitorAlarmTicketTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
		
		logger.info("updateTicket request: " + request.toString());
		if (request.getId() < 0) {
    		throw new Exception("the id is null");
    	} else {
			ticketBuilder.setId(request.getId());
			
			if (request.getLastUpdateUser() == null || request.getLastUpdateUser().equals("") == true) {
				throw new Exception("lastUpdateUser is null");
			}
			ticketBuilder.setLastUpdateUser(request.getLastUpdateUser());
			ticketBuilder.setLastUpdateTime(System.currentTimeMillis());
			
			if (request.getEnnMonitorAlarmTicketStatus().equals(EnnMonitorAlarmTicketStatus.EnnMonitorAlarmTicketStatusDefault) == false) {
				ticketBuilder.setEnnMonitorAlarmTicketStatus(request.getEnnMonitorAlarmTicketStatus());
			}
			
			if (request.getGrourName() != null && request.getGrourName().equals("") == false) {
				ticketBuilder.setGrourName(request.getGrourName());
			}
			
			if (request.getError() != null && request.getError().equals("") == false) {
				ticketBuilder.setError(request.getError());
			}
			
			if (request.getErrorReason() != null && request.getErrorReason().equals("") == false) {
				ticketBuilder.setErrorReason(request.getErrorReason());
			}
			
			if (request.getRemark() != null && request.getRemark().equals("") == false) {
				ticketBuilder.setRemark(request.getRemark());
			}
			
			updateTicketMetrics.markEvent();
			
			channelWriteData = new ChannelWriteData();
			channelWriteData.setOpEnum(EnnMonitorAlarmTicketCacheOpEnum.Update);
			channelWriteData.setObject(ticketBuilder.build());
			cacheChannel.write(channelWriteData);
    	}
	}
	
	public void deleteEnnMonitorAlarmTicket(EnnMonitorAlarmTicketDeleteRequest request) throws Exception {
		EnnMonitorAlarmTicketTable.Builder ticketBuilder = EnnMonitorAlarmTicketTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
		
		logger.info("deleteTicket request: " + request.toString());
		if (request.getId() < 0) {
    		throw new Exception("the id is null");
    	} else {
    		ticketBuilder.setId(request.getId());
    		
    		channelWriteData = new ChannelWriteData();
			channelWriteData.setOpEnum(EnnMonitorAlarmTicketCacheOpEnum.Delete);
			channelWriteData.setObject(ticketBuilder.build());
			cacheChannel.write(channelWriteData);
    	}
		
		deleteTicketMetrics.markEvent();
	}
	
	public long getEnnMonitorAlarmTicketMaxDeletedSeqNo() throws Exception {
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
	
	public List<EnnMonitorAlarmTicketTable> getEnnMonitorAlarmTicketValid(long startSeqNo, int batch) throws Exception {
		BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	List<EnnMonitorAlarmTicketTable> ticketTableList = null;
    	
    	query.put(EnnMonitorAlarmTicketTableField.SeqNoFieldName, new BasicDBObject("$gt", startSeqNo));
    	order.put(EnnMonitorAlarmTicketTableField.SeqNoFieldName, 1);
    	
		ticketTableList = ticketTable.getMongoDBCtrl().searchData(query, order, 0, batch);
		
		return ticketTableList;
	}
	
	public List<EnnMonitorAlarmTicketGetDeleted> getEnnMonitorAlarmTicketDeleted(long startSeqNo, int batch) throws Exception {
		BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	EnnMonitorAlarmTicketGetDeleted.Builder deletedData = null;
    	
    	List<EnnMonitorFrameworkPipeDeletedTable> deletedTableList = null;
    	List<EnnMonitorAlarmTicketGetDeleted> deletedDataList = new ArrayList<EnnMonitorAlarmTicketGetDeleted>();
    	
    	query.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, new BasicDBObject("$gt", startSeqNo));
    	order.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, 1);
    	
		deletedTableList = deletedTableCtl.getMongoDBCtrl().searchData(query, order, 0, batch);
		if (deletedTableList != null) {
			for (EnnMonitorFrameworkPipeDeletedTable deletedTable : deletedTableList) {
				deletedData = EnnMonitorAlarmTicketGetDeleted.newBuilder();
				deletedData.setId(deletedTable.getId());
				deletedData.setSeqNo(deletedTable.getSeqNo());
				deletedDataList.add(deletedData.build());
    		}
		}
		
		return deletedDataList;
	}
	
	public void updateEnnMonitorAlarmTicketTransformState(EnnMonitorAlarmTicketTransformState transformState) throws Exception {
		ChannelWriteData channelWriteData = null;
		
		channelWriteData = new ChannelWriteData();
		channelWriteData.setOpEnum(EnnMonitorAlarmTicketCacheOpEnum.UpdateState);
		channelWriteData.setObject(transformState);
		cacheChannel.write(channelWriteData);
		
		updateStateMetrics.markEvent();
	}
	
	public EnnMonitorAlarmTicketTableCtl getEnnMonitorAlarmTicketTableCtl() {
		return ticketTable;
	}

}
