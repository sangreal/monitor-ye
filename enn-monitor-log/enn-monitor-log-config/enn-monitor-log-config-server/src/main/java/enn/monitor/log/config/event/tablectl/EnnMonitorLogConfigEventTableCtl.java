package enn.monitor.log.config.event.tablectl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import enn.monitor.framework.mongo.MongoDBCtrl;
import enn.monitor.framework.mongo.table.MongoDBTableInstance;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventTable;

public class EnnMonitorLogConfigEventTableCtl extends MongoDBTableInstance<EnnMonitorLogConfigEventTable> {
	public EnnMonitorLogConfigEventTableCtl(String uri, String dbName, String tableName) throws Exception {
		super(uri, dbName, tableName);
	}

	@Override
	public EnnMonitorLogConfigEventTable convertorFromDBObject(DBObject dbObject) throws Exception {
		EnnMonitorLogConfigEventTable.Builder eventTable = EnnMonitorLogConfigEventTable.newBuilder();
		
		eventTable.setId((Long) dbObject.get(EnnMonitorLogConfigEventTableField.IdFieldName));
		eventTable.setSeqNo((Long) dbObject.get(EnnMonitorLogConfigEventTableField.SeqNoFieldName));
		
		if ((String)dbObject.get(EnnMonitorLogConfigEventTableField.EventKeyFieldName) != null) {
			eventTable.setEventKey((String) dbObject.get(EnnMonitorLogConfigEventTableField.EventKeyFieldName));
		}
		
		if ((String)dbObject.get(EnnMonitorLogConfigEventTableField.EventNameFieldName) != null) {
			eventTable.setEventName((String) dbObject.get(EnnMonitorLogConfigEventTableField.EventNameFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigEventTableField.BelongToServiceIdFieldName) != null) {
			eventTable.setBelongToServiceId((Long) dbObject.get(EnnMonitorLogConfigEventTableField.BelongToServiceIdFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigEventTableField.CreateTimeFieldName) != null) {
			eventTable.setCreateTime((Long) dbObject.get(EnnMonitorLogConfigEventTableField.CreateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigEventTableField.LastUpdateTimeFieldName) != null) {
			eventTable.setLastUpdateTime((Long) dbObject.get(EnnMonitorLogConfigEventTableField.LastUpdateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigEventTableField.CreateUserFieldName) != null) {
			eventTable.setCreateUser((String)dbObject.get(EnnMonitorLogConfigEventTableField.CreateUserFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigEventTableField.LastUpdateUserFieldName) != null) {
			eventTable.setLastUpdateUser((String)dbObject.get(EnnMonitorLogConfigEventTableField.LastUpdateUserFieldName));
		}
		
		return eventTable.build();
	}
	
	@Override
	public DBObject convertorFromClassType(EnnMonitorLogConfigEventTable instance) throws Exception {
		
		DBObject dbObject = new BasicDBObject();
		
		dbObject.put(EnnMonitorLogConfigEventTableField.IdFieldName, instance.getId());
		
		if (instance.getSeqNo() >= 0) {
			dbObject.put(EnnMonitorLogConfigEventTableField.SeqNoFieldName, instance.getSeqNo());
		}
		
		if (instance.getEventKey() != null && instance.getEventKey().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigEventTableField.EventKeyFieldName, instance.getEventKey());
		}
		
		if (instance.getEventName() != null && instance.getEventName().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigEventTableField.EventNameFieldName, instance.getEventName());
		}
		
		dbObject.put(EnnMonitorLogConfigEventTableField.BelongToServiceIdFieldName, instance.getBelongToServiceId());
		
		if (instance.getCreateUser() != null && instance.getCreateUser().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigEventTableField.CreateUserFieldName, instance.getCreateUser());
		}
		
		if (instance.getLastUpdateUser() != null && instance.getLastUpdateUser().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigEventTableField.LastUpdateUserFieldName, instance.getLastUpdateUser());
		}
		
		if (instance.getCreateTime() > 0) {
			dbObject.put(EnnMonitorLogConfigEventTableField.CreateTimeFieldName, instance.getCreateTime());
		}
		
		if (instance.getLastUpdateTime() > 0) {
			dbObject.put(EnnMonitorLogConfigEventTableField.LastUpdateTimeFieldName, instance.getLastUpdateTime());
		}
		
		return dbObject;
	}
	
	public String getTableName() {
		return EnnMonitorLogConfigEventDBTable.getEventTable();
	}
	
	public MongoDBCtrl<EnnMonitorLogConfigEventTable> getMongoDBCtrl() {
		return mongoDBCtl;
	}

}
