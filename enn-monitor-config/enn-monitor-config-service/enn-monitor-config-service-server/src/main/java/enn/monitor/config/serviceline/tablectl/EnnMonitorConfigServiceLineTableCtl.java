package enn.monitor.config.serviceline.tablectl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import enn.monitor.config.MongoConfig;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineStatus;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineTable;
import enn.monitor.framework.mongo.MongoDBCtrl;
import enn.monitor.framework.mongo.table.MongoDBTableInstance;

public class EnnMonitorConfigServiceLineTableCtl extends MongoDBTableInstance<EnnMonitorConfigServiceLineTable> {

	public EnnMonitorConfigServiceLineTableCtl(String uri, String dbName, String tableName) throws Exception {
		super(uri, dbName, tableName);
	}

	@Override
	public EnnMonitorConfigServiceLineTable convertorFromDBObject(DBObject dbObject) throws Exception {
		EnnMonitorConfigServiceLineTable.Builder serviceNameTable = EnnMonitorConfigServiceLineTable.newBuilder();
		
		serviceNameTable.setId((Long) dbObject.get(EnnMonitorConfigServiceLineTableField.IdFieldName));
		serviceNameTable.setSeqNo((Long) dbObject.get(EnnMonitorConfigServiceLineTableField.SeqNoFieldName));
		
		if ((String)dbObject.get(EnnMonitorConfigServiceLineTableField.StatusFieldName) != null) {
			serviceNameTable.setStatus(EnnMonitorConfigServiceLineStatus.valueOf((String)dbObject.get(EnnMonitorConfigServiceLineTableField.StatusFieldName)));
		}
		
		if ((String)dbObject.get(EnnMonitorConfigServiceLineTableField.ServiceLineNameFieldName) != null) {
			serviceNameTable.setServiceLineName((String)dbObject.get(EnnMonitorConfigServiceLineTableField.ServiceLineNameFieldName));
		}
		
		if ((Long)dbObject.get(EnnMonitorConfigServiceLineTableField.BelongToClusterFieldName) != null) {
			serviceNameTable.setBelongToCluster((Long)dbObject.get(EnnMonitorConfigServiceLineTableField.BelongToClusterFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigServiceLineTableField.CreateTimeFieldName) != null) {
			serviceNameTable.setCreateTime((Long) dbObject.get(EnnMonitorConfigServiceLineTableField.CreateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigServiceLineTableField.LastUpdateTimeFieldName) != null) {
			serviceNameTable.setLastUpdateTime((Long) dbObject.get(EnnMonitorConfigServiceLineTableField.LastUpdateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigServiceLineTableField.CreateUserFieldName) != null) {
			serviceNameTable.setCreateUser((String)dbObject.get(EnnMonitorConfigServiceLineTableField.CreateUserFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigServiceLineTableField.LastUpdateUserFieldName) != null) {
			serviceNameTable.setLastUpdateUser((String)dbObject.get(EnnMonitorConfigServiceLineTableField.LastUpdateUserFieldName));
		}
		
		return serviceNameTable.build();
	}

	@Override
	public DBObject convertorFromClassType(EnnMonitorConfigServiceLineTable instance) throws Exception {
		DBObject dbObject = new BasicDBObject();
		
		dbObject.put(EnnMonitorConfigServiceLineTableField.IdFieldName, instance.getId());
		
		if (instance.getSeqNo() > 0) {
			dbObject.put(EnnMonitorConfigServiceLineTableField.SeqNoFieldName, instance.getSeqNo());
		}
		
		if (instance.getStatus().equals(EnnMonitorConfigServiceLineStatus.ServiceLineDefault) == false) {
			dbObject.put(EnnMonitorConfigServiceLineTableField.StatusFieldName, instance.getStatus().name());
		}
		
		if (instance.getServiceLineName() != null && instance.getServiceLineName().equals("") == false) {
			dbObject.put(EnnMonitorConfigServiceLineTableField.ServiceLineNameFieldName, instance.getServiceLineName());
		}
		
		if (instance.getBelongToCluster() > 0) {
			dbObject.put(EnnMonitorConfigServiceLineTableField.BelongToClusterFieldName, instance.getBelongToCluster());
		}
		
		if (instance.getCreateUser() != null && instance.getCreateUser().equals("") == false) {
			dbObject.put(EnnMonitorConfigServiceLineTableField.CreateUserFieldName, instance.getCreateUser());
		}
		
		if (instance.getLastUpdateUser() != null && instance.getLastUpdateUser().equals("") == false) {
			dbObject.put(EnnMonitorConfigServiceLineTableField.LastUpdateUserFieldName, instance.getLastUpdateUser());
		}
		
		if (instance.getCreateTime() > 0) {
			dbObject.put(EnnMonitorConfigServiceLineTableField.CreateTimeFieldName, instance.getCreateTime());
		}
		
		if (instance.getLastUpdateTime() > 0) {
			dbObject.put(EnnMonitorConfigServiceLineTableField.LastUpdateTimeFieldName, instance.getLastUpdateTime());
		}
		
		return dbObject;
	}
	
	public String getTableName() {
		return MongoConfig.getServiceLineTable();
	}
	
	public MongoDBCtrl<EnnMonitorConfigServiceLineTable> getMongoDBCtrl() {
		return mongoDBCtl;
	}

}
