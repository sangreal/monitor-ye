package enn.monitor.config.cluster.tablectl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import enn.monitor.config.MongoConfig;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterStatus;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterTable;
import enn.monitor.framework.mongo.MongoDBCtrl;
import enn.monitor.framework.mongo.table.MongoDBTableInstance;

public class EnnMonitorConfigClusterTableCtl extends MongoDBTableInstance<EnnMonitorConfigClusterTable> {

	public EnnMonitorConfigClusterTableCtl(String uri, String dbName, String tableName) throws Exception {
		super(uri, dbName, tableName);
	}

	@Override
	public EnnMonitorConfigClusterTable convertorFromDBObject(DBObject dbObject) throws Exception {
		EnnMonitorConfigClusterTable.Builder serviceNameTable = EnnMonitorConfigClusterTable.newBuilder();
		
		serviceNameTable.setId((Long) dbObject.get(EnnMonitorConfigClusterTableField.IdFieldName));
		serviceNameTable.setSeqNo((Long) dbObject.get(EnnMonitorConfigClusterTableField.SeqNoFieldName));
		
		if ((String)dbObject.get(EnnMonitorConfigClusterTableField.StatusFieldName) != null) {
			serviceNameTable.setStatus(EnnMonitorConfigClusterStatus.valueOf((String)dbObject.get(EnnMonitorConfigClusterTableField.StatusFieldName)));
		}
		
		if ((String)dbObject.get(EnnMonitorConfigClusterTableField.ClusterNameFieldName) != null) {
			serviceNameTable.setClusterName((String)dbObject.get(EnnMonitorConfigClusterTableField.ClusterNameFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigClusterTableField.CreateTimeFieldName) != null) {
			serviceNameTable.setCreateTime((Long) dbObject.get(EnnMonitorConfigClusterTableField.CreateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigClusterTableField.LastUpdateTimeFieldName) != null) {
			serviceNameTable.setLastUpdateTime((Long) dbObject.get(EnnMonitorConfigClusterTableField.LastUpdateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigClusterTableField.CreateUserFieldName) != null) {
			serviceNameTable.setCreateUser((String)dbObject.get(EnnMonitorConfigClusterTableField.CreateUserFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigClusterTableField.LastUpdateUserFieldName) != null) {
			serviceNameTable.setLastUpdateUser((String)dbObject.get(EnnMonitorConfigClusterTableField.LastUpdateUserFieldName));
		}
		
		return serviceNameTable.build();
	}

	@Override
	public DBObject convertorFromClassType(EnnMonitorConfigClusterTable instance) throws Exception {
		DBObject dbObject = new BasicDBObject();
		
		dbObject.put(EnnMonitorConfigClusterTableField.IdFieldName, instance.getId());
		
		if (instance.getSeqNo() > 0) {
			dbObject.put(EnnMonitorConfigClusterTableField.SeqNoFieldName, instance.getSeqNo());
		}
		
		if (instance.getStatus().equals(EnnMonitorConfigClusterStatus.ClusterDefault) == false) {
			dbObject.put(EnnMonitorConfigClusterTableField.StatusFieldName, instance.getStatus().name());
		}
		
		if (instance.getClusterName() != null && instance.getClusterName().equals("") == false) {
			dbObject.put(EnnMonitorConfigClusterTableField.ClusterNameFieldName, instance.getClusterName());
		}
		
		if (instance.getCreateUser() != null && instance.getCreateUser().equals("") == false) {
			dbObject.put(EnnMonitorConfigClusterTableField.CreateUserFieldName, instance.getCreateUser());
		}
		
		if (instance.getLastUpdateUser() != null && instance.getLastUpdateUser().equals("") == false) {
			dbObject.put(EnnMonitorConfigClusterTableField.LastUpdateUserFieldName, instance.getLastUpdateUser());
		}
		
		if (instance.getCreateTime() > 0) {
			dbObject.put(EnnMonitorConfigClusterTableField.CreateTimeFieldName, instance.getCreateTime());
		}
		
		if (instance.getLastUpdateTime() > 0) {
			dbObject.put(EnnMonitorConfigClusterTableField.LastUpdateTimeFieldName, instance.getLastUpdateTime());
		}
		
		return dbObject;
	}
	
	public String getTableName() {
		return MongoConfig.getClusterTable();
	}
	
	public MongoDBCtrl<EnnMonitorConfigClusterTable> getMongoDBCtrl() {
		return mongoDBCtl;
	}

}
