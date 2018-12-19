package enn.monitor.log.analyse.storage.tablectl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import enn.monitor.framework.mongo.MongoDBCtrl;
import enn.monitor.framework.mongo.table.MongoDBTableInstance;
import enn.monitor.log.analyse.storage.config.MongoConfig;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageTable;

public class EnnMonitorLogAnalyseStorageTableCtl extends MongoDBTableInstance<EnnMonitorLogAnalyseStorageTable> {

	public EnnMonitorLogAnalyseStorageTableCtl(String uri, String dbName, String tableName) throws Exception {
		super(uri, dbName, tableName);
	}

	@Override
	public EnnMonitorLogAnalyseStorageTable convertorFromDBObject(DBObject dbObject) throws Exception {
		
		EnnMonitorLogAnalyseStorageTable.Builder analyseStorageTable = EnnMonitorLogAnalyseStorageTable.newBuilder();
		
		analyseStorageTable.setId((Long) dbObject.get(EnnMonitorLogAnalyseStorageTableField.IdFieldName));
		analyseStorageTable.setSeqNo((Long) dbObject.get(EnnMonitorLogAnalyseStorageTableField.SeqNoFieldName));
		
		if (dbObject.get(EnnMonitorLogAnalyseStorageTableField.TokenIdFieldName) != null) {
			analyseStorageTable.setTokenId((Long)dbObject.get(EnnMonitorLogAnalyseStorageTableField.TokenIdFieldName));
		}
		
		if ((String)dbObject.get(EnnMonitorLogAnalyseStorageTableField.NNDataFieldName) != null) {
			analyseStorageTable.setNndata((String)dbObject.get(EnnMonitorLogAnalyseStorageTableField.NNDataFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogAnalyseStorageTableField.CreateTimeFieldName) != null) {
			analyseStorageTable.setCreateTime((Long) dbObject.get(EnnMonitorLogAnalyseStorageTableField.CreateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogAnalyseStorageTableField.LastUpdateTimeFieldName) != null) {
			analyseStorageTable.setLastUpdateTime((Long) dbObject.get(EnnMonitorLogAnalyseStorageTableField.LastUpdateTimeFieldName));
		}
		
		return analyseStorageTable.build();
	}

	@Override
	public DBObject convertorFromClassType(EnnMonitorLogAnalyseStorageTable instance) throws Exception {
		DBObject dbObject = new BasicDBObject();
		
		dbObject.put(EnnMonitorLogAnalyseStorageTableField.IdFieldName, instance.getId());
		
		if (instance.getTokenId() >= 0) {
			dbObject.put(EnnMonitorLogAnalyseStorageTableField.TokenIdFieldName, instance.getTokenId());
		}
		
		if (instance.getSeqNo() >= 0) {
			dbObject.put(EnnMonitorLogAnalyseStorageTableField.SeqNoFieldName, instance.getSeqNo());
		}
		
		if (instance.getNndata() != null && instance.getNndata().equals("") == false) {
			dbObject.put(EnnMonitorLogAnalyseStorageTableField.NNDataFieldName, instance.getNndata());
		}
		
		if (instance.getCreateTime() > 0) {
			dbObject.put(EnnMonitorLogAnalyseStorageTableField.CreateTimeFieldName, instance.getCreateTime());
		}
		
		if (instance.getLastUpdateTime() > 0) {
			dbObject.put(EnnMonitorLogAnalyseStorageTableField.LastUpdateTimeFieldName, instance.getLastUpdateTime());
		}
		
		return dbObject;
	}
	
	public String getTableName() {
		return MongoConfig.getLogAnalyseStorageTable();
	}
	
	public MongoDBCtrl<EnnMonitorLogAnalyseStorageTable> getMongoDBCtrl() {
		return mongoDBCtl;
	}

}
