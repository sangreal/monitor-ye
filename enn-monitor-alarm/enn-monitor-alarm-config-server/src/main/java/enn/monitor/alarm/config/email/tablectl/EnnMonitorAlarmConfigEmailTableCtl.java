package enn.monitor.alarm.config.email.tablectl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import enn.monitor.alarm.config.config.MongoConfig;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailTable;
import enn.monitor.framework.common.mongo.MongoDBCtrl;
import enn.monitor.framework.common.mongo.table.MongoDBTableInstance;

public class EnnMonitorAlarmConfigEmailTableCtl extends MongoDBTableInstance<EnnMonitorAlarmConfigEmailTable> {
	public EnnMonitorAlarmConfigEmailTableCtl(String uri, String dbName, String tableName) throws Exception {
		super(uri, dbName, tableName);
	}

	@Override
	public EnnMonitorAlarmConfigEmailTable convertorFromDBObject(DBObject dbObject) throws Exception {
		
		EnnMonitorAlarmConfigEmailTable.Builder emailTable = EnnMonitorAlarmConfigEmailTable.newBuilder();
		
		emailTable.setId((Long) dbObject.get(EnnMonitorAlarmConfigEmailTableField.IdFieldName));
		emailTable.setSeqNo((Long) dbObject.get(EnnMonitorAlarmConfigEmailTableField.SeqNoFieldName));
		
		if (dbObject.get(EnnMonitorAlarmConfigEmailTableField.CreateTimeFieldName) != null) {
			emailTable.setCreateTime((Long) dbObject.get(EnnMonitorAlarmConfigEmailTableField.CreateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorAlarmConfigEmailTableField.LastUpdateTimeFieldName) != null) {
			emailTable.setLastUpdateTime((Long) dbObject.get(EnnMonitorAlarmConfigEmailTableField.LastUpdateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorAlarmConfigEmailTableField.CreateUserFieldName) != null) {
			emailTable.setCreateUser((String)dbObject.get(EnnMonitorAlarmConfigEmailTableField.CreateUserFieldName));
		}
		
		if (dbObject.get(EnnMonitorAlarmConfigEmailTableField.LastUpdateUserFieldName) != null) {
			emailTable.setLastUpdateUser((String)dbObject.get(EnnMonitorAlarmConfigEmailTableField.LastUpdateUserFieldName));
		}
		
		if (dbObject.get(EnnMonitorAlarmConfigEmailTableField.GroupNameFieldName) != null) {
			emailTable.setGroupName((String)dbObject.get(EnnMonitorAlarmConfigEmailTableField.GroupNameFieldName));
		}
		
		if (dbObject.get(EnnMonitorAlarmConfigEmailTableField.MailFieldName) != null) {
			emailTable.setMail((String)dbObject.get(EnnMonitorAlarmConfigEmailTableField.MailFieldName));
		}
		
		if (dbObject.get(EnnMonitorAlarmConfigEmailTableField.NameFieldName) != null) {
			emailTable.setName((String)dbObject.get(EnnMonitorAlarmConfigEmailTableField.NameFieldName));
		}

		return emailTable.build();
	}
	
	@Override
	public DBObject convertorFromClassType(EnnMonitorAlarmConfigEmailTable instance) throws Exception {
		DBObject dbObject = new BasicDBObject();
		
		dbObject.put(EnnMonitorAlarmConfigEmailTableField.IdFieldName, instance.getId());
		
		if (instance.getSeqNo() >= 0) {
			dbObject.put(EnnMonitorAlarmConfigEmailTableField.SeqNoFieldName, instance.getSeqNo());
		}
		
		if (instance.getCreateUser() != null && instance.getCreateUser().equals("") == false) {
			dbObject.put(EnnMonitorAlarmConfigEmailTableField.CreateUserFieldName, instance.getCreateUser());
		}
		
		if (instance.getLastUpdateUser() != null && instance.getLastUpdateUser().equals("") == false) {
			dbObject.put(EnnMonitorAlarmConfigEmailTableField.LastUpdateUserFieldName, instance.getLastUpdateUser());
		}
		
		if (instance.getCreateTime() > 0) {
			dbObject.put(EnnMonitorAlarmConfigEmailTableField.CreateTimeFieldName, instance.getCreateTime());
		}
		
		if (instance.getLastUpdateTime() > 0) {
			dbObject.put(EnnMonitorAlarmConfigEmailTableField.LastUpdateTimeFieldName, instance.getLastUpdateTime());
		}
		
		if (instance.getGroupName() != null && instance.getGroupName().equals("") == false) {
			dbObject.put(EnnMonitorAlarmConfigEmailTableField.GroupNameFieldName, instance.getGroupName());
		}
		
		if (instance.getMail() != null && instance.getMail().equals("") == false) {
			dbObject.put(EnnMonitorAlarmConfigEmailTableField.MailFieldName, instance.getMail());
		}
		
		if (instance.getName() != null && instance.getName().equals("") == false) {
			dbObject.put(EnnMonitorAlarmConfigEmailTableField.NameFieldName, instance.getName());
		}
		
		return dbObject;
	}
	
	public String getTableName() {
		return MongoConfig.getEnnMonitorAlarmEmailTable();
	}
	
	public MongoDBCtrl<EnnMonitorAlarmConfigEmailTable> getMongoDBCtrl() {
		return mongoDBCtl;
	}

}
