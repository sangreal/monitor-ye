package enn.monitor.alarm.ticket.tablectl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import enn.monitor.alarm.ticket.config.MongoConfig;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketLevel;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketStatus;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTable;
import enn.monitor.framework.common.mongo.MongoDBCtrl;
import enn.monitor.framework.common.mongo.table.MongoDBTableInstance;

public class EnnMonitorAlarmTicketTableCtl extends MongoDBTableInstance<EnnMonitorAlarmTicketTable> {
	public EnnMonitorAlarmTicketTableCtl(String uri, String dbName, String tableName) throws Exception {
		super(uri, dbName, tableName);
	}

	@Override
	public EnnMonitorAlarmTicketTable convertorFromDBObject(DBObject dbObject) throws Exception {
		EnnMonitorAlarmTicketTable.Builder ticketTable = EnnMonitorAlarmTicketTable.newBuilder();
		
		ticketTable.setId((Long) dbObject.get(EnnMonitorAlarmTicketTableField.IdFieldName));
		ticketTable.setSeqNo((Long) dbObject.get(EnnMonitorAlarmTicketTableField.SeqNoFieldName));
		
		if ((String)dbObject.get(EnnMonitorAlarmTicketTableField.TicketKeyFieldName) != null) {
			ticketTable.setTicketKey((String)dbObject.get(EnnMonitorAlarmTicketTableField.TicketKeyFieldName));
		}
		
		if ((Long)dbObject.get(EnnMonitorAlarmTicketTableField.CreateTimeFieldName) != null) {
			ticketTable.setCreateTime((Long)dbObject.get(EnnMonitorAlarmTicketTableField.CreateTimeFieldName));
		}
		
		if ((String)dbObject.get(EnnMonitorAlarmTicketTableField.CreateUserFieldName) != null) {
			ticketTable.setCreateUser((String)dbObject.get(EnnMonitorAlarmTicketTableField.CreateUserFieldName));
		}
		
		if ((Long)dbObject.get(EnnMonitorAlarmTicketTableField.LastUpdateTimeFieldName) != null) {
			ticketTable.setLastUpdateTime((Long)dbObject.get(EnnMonitorAlarmTicketTableField.LastUpdateTimeFieldName));
		}
		
		if ((String)dbObject.get(EnnMonitorAlarmTicketTableField.LastUpdateUserFieldName) != null) {
			ticketTable.setLastUpdateUser((String)dbObject.get(EnnMonitorAlarmTicketTableField.LastUpdateUserFieldName));
		}
		
		if ((String)dbObject.get(EnnMonitorAlarmTicketTableField.EnnMonitorAlarmTicketStatusFieldName) != null) {
			ticketTable.setEnnMonitorAlarmTicketStatus(EnnMonitorAlarmTicketStatus.valueOf((String)dbObject.get(EnnMonitorAlarmTicketTableField.EnnMonitorAlarmTicketStatusFieldName)));
		}
		
		if ((String)dbObject.get(EnnMonitorAlarmTicketTableField.EnnMonitorAlarmTicketLevelFieldName) != null) {
			ticketTable.setEnnMonitorAlarmTicketLevel(EnnMonitorAlarmTicketLevel.valueOf((String)dbObject.get(EnnMonitorAlarmTicketTableField.EnnMonitorAlarmTicketLevelFieldName)));
		}
		
		if ((Boolean)dbObject.get(EnnMonitorAlarmTicketTableField.AutomationFieldName) != null) {
			ticketTable.setAutomation((Boolean)dbObject.get(EnnMonitorAlarmTicketTableField.AutomationFieldName));
		}
		
		if ((String)dbObject.get(EnnMonitorAlarmTicketTableField.GroupNameFieldName) != null) {
			ticketTable.setGrourName((String)dbObject.get(EnnMonitorAlarmTicketTableField.GroupNameFieldName));
		}
		
		if ((String)dbObject.get(EnnMonitorAlarmTicketTableField.ClusterNameFieldName) != null) {
			ticketTable.setClusterName((String)dbObject.get(EnnMonitorAlarmTicketTableField.ClusterNameFieldName));
		}
		
		if ((String)dbObject.get(EnnMonitorAlarmTicketTableField.IPFieldName) != null) {
			ticketTable.setIp((String)dbObject.get(EnnMonitorAlarmTicketTableField.IPFieldName));
		}
		
		if ((String)dbObject.get(EnnMonitorAlarmTicketTableField.NameSpaceFieldName) != null) {
			ticketTable.setNameSpace((String)dbObject.get(EnnMonitorAlarmTicketTableField.NameSpaceFieldName));
		}

		if ((String)dbObject.get(EnnMonitorAlarmTicketTableField.PodNameFieldName) != null) {
			ticketTable.setPodName((String)dbObject.get(EnnMonitorAlarmTicketTableField.PodNameFieldName));
		}

		if ((String)dbObject.get(EnnMonitorAlarmTicketTableField.AppNameFieldName) != null) {
			ticketTable.setAppName((String)dbObject.get(EnnMonitorAlarmTicketTableField.AppNameFieldName));
		}

		if ((String)dbObject.get(EnnMonitorAlarmTicketTableField.ErrorFieldName) != null) {
			ticketTable.setError((String)dbObject.get(EnnMonitorAlarmTicketTableField.ErrorFieldName));
		}
		
		if ((String)dbObject.get(EnnMonitorAlarmTicketTableField.ErrorReasonFieldName) != null) {
			ticketTable.setErrorReason((String)dbObject.get(EnnMonitorAlarmTicketTableField.ErrorReasonFieldName));
		}
		
		if ((String)dbObject.get(EnnMonitorAlarmTicketTableField.RemarkFieldName) != null) {
			ticketTable.setRemark((String)dbObject.get(EnnMonitorAlarmTicketTableField.RemarkFieldName));
		}
		
		return ticketTable.build();
	}
	
	@Override
	public DBObject convertorFromClassType(EnnMonitorAlarmTicketTable instance) throws Exception {
		
		DBObject dbObject = new BasicDBObject();
		
		dbObject.put(EnnMonitorAlarmTicketTableField.IdFieldName, instance.getId());
		
		if (instance.getSeqNo() >= 0) {
			dbObject.put(EnnMonitorAlarmTicketTableField.SeqNoFieldName, instance.getSeqNo());
		}
		
		if (instance.getTicketKey() != null && instance.getTicketKey().equals("") == false) {
			dbObject.put(EnnMonitorAlarmTicketTableField.TicketKeyFieldName, instance.getTicketKey());
		}
		
		if (instance.getCreateUser() != null && instance.getCreateUser().equals("") == false) {
			dbObject.put(EnnMonitorAlarmTicketTableField.CreateUserFieldName, instance.getCreateUser());
		}
		
		if (instance.getLastUpdateUser() != null && instance.getLastUpdateUser().equals("") == false) {
			dbObject.put(EnnMonitorAlarmTicketTableField.LastUpdateUserFieldName, instance.getLastUpdateUser());
		}
		
		if (instance.getCreateTime() > 0) {
			dbObject.put(EnnMonitorAlarmTicketTableField.CreateTimeFieldName, instance.getCreateTime());
		}
		
		if (instance.getLastUpdateTime() > 0) {
			dbObject.put(EnnMonitorAlarmTicketTableField.LastUpdateTimeFieldName, instance.getLastUpdateTime());
		}
		
		if (instance.getEnnMonitorAlarmTicketStatus().equals(EnnMonitorAlarmTicketStatus.EnnMonitorAlarmTicketStatusDefault) == false) {
			dbObject.put(EnnMonitorAlarmTicketTableField.EnnMonitorAlarmTicketStatusFieldName, instance.getEnnMonitorAlarmTicketStatus().name());
		}
		
		if (instance.getEnnMonitorAlarmTicketLevel().equals(EnnMonitorAlarmTicketLevel.EnnMonitorAlarmTicketLevelDefault) == false) {
			dbObject.put(EnnMonitorAlarmTicketTableField.EnnMonitorAlarmTicketLevelFieldName, instance.getEnnMonitorAlarmTicketLevel().name());
		}
		
		dbObject.put(EnnMonitorAlarmTicketTableField.AutomationFieldName, instance.getAutomation());
		
		if (instance.getGrourName() != null && instance.getGrourName().equals("") == false) {
			dbObject.put(EnnMonitorAlarmTicketTableField.GroupNameFieldName, instance.getGrourName());
		}
		
		if (instance.getClusterName() != null && instance.getClusterName().equals("") == false) {
			dbObject.put(EnnMonitorAlarmTicketTableField.ClusterNameFieldName, instance.getClusterName());
		}
		
		if (instance.getIp() != null && instance.getIp().equals("") == false) {
			dbObject.put(EnnMonitorAlarmTicketTableField.IPFieldName, instance.getIp());
		}
		
		if (instance.getNameSpace() != null && instance.getNameSpace().equals("") == false) {
			dbObject.put(EnnMonitorAlarmTicketTableField.NameSpaceFieldName, instance.getNameSpace());
		}
		
		if (instance.getPodName() != null && instance.getPodName().equals("") == false) {
			dbObject.put(EnnMonitorAlarmTicketTableField.PodNameFieldName, instance.getPodName());
		}
		
		if (instance.getAppName() != null && instance.getAppName().equals("") == false) {
			dbObject.put(EnnMonitorAlarmTicketTableField.AppNameFieldName, instance.getAppName());
		}
		
		if (instance.getError() != null && instance.getError().equals("") == false) {
			dbObject.put(EnnMonitorAlarmTicketTableField.ErrorFieldName, instance.getError());
		}
		
		if (instance.getErrorReason() != null && instance.getErrorReason().equals("") == false) {
			dbObject.put(EnnMonitorAlarmTicketTableField.ErrorReasonFieldName, instance.getErrorReason());
		}
		
		if (instance.getRemark() != null && instance.getRemark().equals("") == false) {
			dbObject.put(EnnMonitorAlarmTicketTableField.RemarkFieldName, instance.getRemark());
		}
		

		return dbObject;
	}
	
	public String getTableName() {
		return MongoConfig.getTicketTable();
	}

	public MongoDBCtrl<EnnMonitorAlarmTicketTable> getMongoDBCtrl() {
		return mongoDBCtl;
	}

}
