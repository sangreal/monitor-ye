package enn.monitor.log.archive.tablectl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import enn.monitor.framework.mongo.MongoDBCtrl;
import enn.monitor.framework.mongo.table.MongoDBTableInstance;
import enn.monitor.log.archive.config.MongoConfig;
import enn.monitor.log.archive.table.EnnMonitorLogArchiveConfigTable;

public class EnnMonitorLogArchiveConfigCtl extends MongoDBTableInstance<EnnMonitorLogArchiveConfigTable> {

	public EnnMonitorLogArchiveConfigCtl(String uri, String dbName, String tableName) throws Exception {
		super(uri, dbName, tableName);
	}

	@Override
	public EnnMonitorLogArchiveConfigTable convertorFromDBObject(DBObject dbObject) throws Exception {
		EnnMonitorLogArchiveConfigTable archiveTable = new EnnMonitorLogArchiveConfigTable();
		
		archiveTable.setId((Long) dbObject.get(EnnMonitorLogArchiveConfigField.IdFieldName));
		archiveTable.setDays((Long) dbObject.get(EnnMonitorLogArchiveConfigField.DaysFieldName));
		
		if (dbObject.get(EnnMonitorLogArchiveConfigField.LastUpdateTimeFieldName) != null) {
			archiveTable.setLastUpdateTime((Long) dbObject.get(EnnMonitorLogArchiveConfigField.LastUpdateTimeFieldName));
		}
		
		
		if (dbObject.get(EnnMonitorLogArchiveConfigField.LastUpdateUserFieldName) != null) {
			archiveTable.setLastUpdateUser((String)dbObject.get(EnnMonitorLogArchiveConfigField.LastUpdateUserFieldName));
		}

		return archiveTable;	
	}

	@Override
	public DBObject convertorFromClassType(EnnMonitorLogArchiveConfigTable instance) throws Exception {
		DBObject dbObject = new BasicDBObject();
		
		dbObject.put(EnnMonitorLogArchiveConfigField.IdFieldName, instance.getId());
		
		if (instance.getDays() >= 0) {
			dbObject.put(EnnMonitorLogArchiveConfigField.DaysFieldName, instance.getDays());
		}
		
		if (instance.getLastUpdateUser() != null && instance.getLastUpdateUser().equals("") == false) {
			dbObject.put(EnnMonitorLogArchiveConfigField.LastUpdateUserFieldName, instance.getLastUpdateUser());
		}
		
		if (instance.getLastUpdateTime() > 0) {
			dbObject.put(EnnMonitorLogArchiveConfigField.LastUpdateTimeFieldName, instance.getLastUpdateTime());
		}
		
		return dbObject;
	}
	
	public String getTableName() {
		return MongoConfig.getLogArchiveTable();
	}
	
	public MongoDBCtrl<EnnMonitorLogArchiveConfigTable> getMongoDBCtrl() {
		return mongoDBCtl;
	}

}
