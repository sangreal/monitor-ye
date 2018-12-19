package enn.monitor.log.config.analyse.term.tablectl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import enn.monitor.framework.mongo.MongoDBCtrl;
import enn.monitor.framework.mongo.table.MongoDBTableInstance;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermTable;

public class EnnMonitorLogConfigAnalyseTermTableCtl extends MongoDBTableInstance<EnnMonitorLogConfigAnalyseTermTable> {
	public EnnMonitorLogConfigAnalyseTermTableCtl(String uri, String dbName, String tableName) throws Exception {
		super(uri, dbName, tableName);
	}

	@Override
	public EnnMonitorLogConfigAnalyseTermTable convertorFromDBObject(DBObject dbObject) throws Exception {
		EnnMonitorLogConfigAnalyseTermTable.Builder analyseTermTable = EnnMonitorLogConfigAnalyseTermTable.newBuilder();
		
		analyseTermTable.setId((Long) dbObject.get(EnnMonitorLogConfigAnalyseTermTableField.IdFieldName));
		
		if ((String)dbObject.get(EnnMonitorLogConfigAnalyseTermTableField.AnalyseTermKeyFieldName) != null) {
			analyseTermTable.setAnalyseTermKey((String)dbObject.get(EnnMonitorLogConfigAnalyseTermTableField.AnalyseTermKeyFieldName));
		}
		
		if ((String)dbObject.get(EnnMonitorLogConfigAnalyseTermTableField.AddTermFieldName) != null) {
			analyseTermTable.setAddTerm((String)dbObject.get(EnnMonitorLogConfigAnalyseTermTableField.AddTermFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigAnalyseTermTableField.FilterTermFieldName) != null) {
			analyseTermTable.setFilterTerm((String)dbObject.get(EnnMonitorLogConfigAnalyseTermTableField.FilterTermFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigAnalyseTermTableField.BelongToServiceIdFieldName) != null) {
			analyseTermTable.setBelongToServiceId((Long)dbObject.get(EnnMonitorLogConfigAnalyseTermTableField.BelongToServiceIdFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigAnalyseTermTableField.CreateTimeFieldName) != null) {
			analyseTermTable.setCreateTime((Long) dbObject.get(EnnMonitorLogConfigAnalyseTermTableField.CreateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigAnalyseTermTableField.LastUpdateTimeFieldName) != null) {
			analyseTermTable.setLastUpdateTime((Long) dbObject.get(EnnMonitorLogConfigAnalyseTermTableField.LastUpdateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigAnalyseTermTableField.CreateUserFieldName) != null) {
			analyseTermTable.setCreateUser((String)dbObject.get(EnnMonitorLogConfigAnalyseTermTableField.CreateUserFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigAnalyseTermTableField.LastUpdateUserFieldName) != null) {
			analyseTermTable.setLastUpdateUser((String)dbObject.get(EnnMonitorLogConfigAnalyseTermTableField.LastUpdateUserFieldName));
		}
		
		return analyseTermTable.build();
	}
	
	@Override
	public DBObject convertorFromClassType(EnnMonitorLogConfigAnalyseTermTable instance) throws Exception {
		
		DBObject dbObject = new BasicDBObject();
		
		dbObject.put(EnnMonitorLogConfigAnalyseTermTableField.IdFieldName, instance.getId());
		
		if (instance.getAnalyseTermKey() != null && instance.getAnalyseTermKey().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigAnalyseTermTableField.AnalyseTermKeyFieldName, instance.getAnalyseTermKey());
		}
		
		if (instance.getAddTerm() != null) {
			dbObject.put(EnnMonitorLogConfigAnalyseTermTableField.AddTermFieldName, instance.getAddTerm());
		}
		
		if (instance.getFilterTerm() != null) {
			dbObject.put(EnnMonitorLogConfigAnalyseTermTableField.FilterTermFieldName, instance.getFilterTerm());
		}
		
		dbObject.put(EnnMonitorLogConfigAnalyseTermTableField.BelongToServiceIdFieldName, instance.getBelongToServiceId());
		
		if (instance.getCreateUser() != null && instance.getCreateUser().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigAnalyseTermTableField.CreateUserFieldName, instance.getCreateUser());
		}
		
		if (instance.getLastUpdateUser() != null && instance.getLastUpdateUser().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigAnalyseTermTableField.LastUpdateUserFieldName, instance.getLastUpdateUser());
		}
		
		if (instance.getCreateTime() > 0) {
			dbObject.put(EnnMonitorLogConfigAnalyseTermTableField.CreateTimeFieldName, instance.getCreateTime());
		}
		
		if (instance.getLastUpdateTime() > 0) {
			dbObject.put(EnnMonitorLogConfigAnalyseTermTableField.LastUpdateTimeFieldName, instance.getLastUpdateTime());
		}
		
		return dbObject;
	}
	
	public String getTableName() {
		return EnnMonitorLogConfigAnalyseTermDBTable.getAnalyseTermTable();
	}
	
	public MongoDBCtrl<EnnMonitorLogConfigAnalyseTermTable> getMongoDBCtrl() {
		return mongoDBCtl;
	}

}
