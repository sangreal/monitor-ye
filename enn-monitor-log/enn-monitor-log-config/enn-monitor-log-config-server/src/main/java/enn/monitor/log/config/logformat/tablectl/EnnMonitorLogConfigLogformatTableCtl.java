package enn.monitor.log.config.logformat.tablectl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import enn.monitor.framework.mongo.MongoDBCtrl;
import enn.monitor.framework.mongo.table.MongoDBTableInstance;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatTable;

public class EnnMonitorLogConfigLogformatTableCtl extends MongoDBTableInstance<EnnMonitorLogConfigLogformatTable> {
	public EnnMonitorLogConfigLogformatTableCtl(String uri, String dbName, String tableName) throws Exception {
		super(uri, dbName, tableName);
	}

	@Override
	public EnnMonitorLogConfigLogformatTable convertorFromDBObject(DBObject dbObject) throws Exception {
		EnnMonitorLogConfigLogformatTable.Builder logformatTable = EnnMonitorLogConfigLogformatTable.newBuilder();
		
		logformatTable.setId((Long) dbObject.get(EnnMonitorLogConfigLogformatTableField.IdFieldName));
		logformatTable.setSeqNo((Long) dbObject.get(EnnMonitorLogConfigLogformatTableField.SeqNoFieldName));
		
		if ((String)dbObject.get(EnnMonitorLogConfigLogformatTableField.RegexFieldName) != null) {
			logformatTable.setRegex((String)dbObject.get(EnnMonitorLogConfigLogformatTableField.RegexFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigLogformatTableField.LogformatFieldName) != null) {
			logformatTable.setLogformat((String)dbObject.get(EnnMonitorLogConfigLogformatTableField.LogformatFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigLogformatTableField.BelongToServiceIdFieldName) != null) {
			logformatTable.setBelongToServiceId((Long)dbObject.get(EnnMonitorLogConfigLogformatTableField.BelongToServiceIdFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigLogformatTableField.CreateTimeFieldName) != null) {
			logformatTable.setCreateTime((Long) dbObject.get(EnnMonitorLogConfigLogformatTableField.CreateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigLogformatTableField.LastUpdateTimeFieldName) != null) {
			logformatTable.setLastUpdateTime((Long) dbObject.get(EnnMonitorLogConfigLogformatTableField.LastUpdateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigLogformatTableField.CreateUserFieldName) != null) {
			logformatTable.setCreateUser((String)dbObject.get(EnnMonitorLogConfigLogformatTableField.CreateUserFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigLogformatTableField.LastUpdateUserFieldName) != null) {
			logformatTable.setLastUpdateUser((String)dbObject.get(EnnMonitorLogConfigLogformatTableField.LastUpdateUserFieldName));
		}
		
		return logformatTable.build();
	}
	
	@Override
	public DBObject convertorFromClassType(EnnMonitorLogConfigLogformatTable instance) throws Exception {
		
		DBObject dbObject = new BasicDBObject();
		
		dbObject.put(EnnMonitorLogConfigLogformatTableField.IdFieldName, instance.getId());
		
		if (instance.getSeqNo() >= 0) {
			dbObject.put(EnnMonitorLogConfigLogformatTableField.SeqNoFieldName, instance.getSeqNo());
		}
		
		if (instance.getRegex() != null && instance.getRegex().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigLogformatTableField.RegexFieldName, instance.getRegex());
		}
		
		if (instance.getLogformat() != null && instance.getLogformat().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigLogformatTableField.LogformatFieldName, instance.getLogformat());
		}
		
		if (instance.getBelongToServiceId() > 0) {
			dbObject.put(EnnMonitorLogConfigLogformatTableField.BelongToServiceIdFieldName, instance.getBelongToServiceId());
		}
		
		if (instance.getCreateUser() != null && instance.getCreateUser().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigLogformatTableField.CreateUserFieldName, instance.getCreateUser());
		}
		
		if (instance.getLastUpdateUser() != null && instance.getLastUpdateUser().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigLogformatTableField.LastUpdateUserFieldName, instance.getLastUpdateUser());
		}
		
		if (instance.getCreateTime() > 0) {
			dbObject.put(EnnMonitorLogConfigLogformatTableField.CreateTimeFieldName, instance.getCreateTime());
		}
		
		if (instance.getLastUpdateTime() > 0) {
			dbObject.put(EnnMonitorLogConfigLogformatTableField.LastUpdateTimeFieldName, instance.getLastUpdateTime());
		}
		
		return dbObject;
	}
	
	public String getTableName() {
		return EnnMonitorLogConfigLogformatDBTable.getLogformatTable();
	}
	
	public MongoDBCtrl<EnnMonitorLogConfigLogformatTable> getMongoDBCtrl() {
		return mongoDBCtl;
	}

}
