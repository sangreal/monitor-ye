package enn.monitor.log.analyse.storage.tablectl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import enn.monitor.framework.mongo.MongoDBCtrl;
import enn.monitor.framework.mongo.table.MongoDBTableInstance;
import enn.monitor.log.analyse.storage.data.EnnMonitorLogAnalyseStorageData;

public class EnnMonitorLogAnalyseStorageTableCtl extends MongoDBTableInstance<EnnMonitorLogAnalyseStorageData> {

	public EnnMonitorLogAnalyseStorageTableCtl(String uri, String dbName, String tableName) throws Exception {
		super(uri, dbName, tableName);
	}

	@Override
	public EnnMonitorLogAnalyseStorageData convertorFromDBObject(DBObject dbObject) throws Exception {
		
		EnnMonitorLogAnalyseStorageData analyseStorageTable = new EnnMonitorLogAnalyseStorageData();
		
		analyseStorageTable.setId((Long) dbObject.get(EnnMonitorLogAnalyseStorageTableField.IdFieldName));
		analyseStorageTable.setSeqNo((Long) dbObject.get(EnnMonitorLogAnalyseStorageTableField.SeqNoFieldName));
		
		if ((String)dbObject.get(EnnMonitorLogAnalyseStorageTableField.NNDataFieldName) != null) {
			analyseStorageTable.setNndata((String)dbObject.get(EnnMonitorLogAnalyseStorageTableField.NNDataFieldName));
		}
		
		return analyseStorageTable;
	}

	@Override
	public DBObject convertorFromClassType(EnnMonitorLogAnalyseStorageData instance) throws Exception {
		DBObject dbObject = new BasicDBObject();
		
		dbObject.put(EnnMonitorLogAnalyseStorageTableField.IdFieldName, instance.getId());
		
		if (instance.getSeqNo() >= 0) {
			dbObject.put(EnnMonitorLogAnalyseStorageTableField.SeqNoFieldName, instance.getSeqNo());
		}
		
		if (instance.getNndata() != null && instance.getNndata().equals("") == false) {
			dbObject.put(EnnMonitorLogAnalyseStorageTableField.NNDataFieldName, instance.getNndata());
		}
		
		return dbObject;
	}
	
	public MongoDBCtrl<EnnMonitorLogAnalyseStorageData> getMongoDBCtrl() {
		return mongoDBCtl;
	}

}
