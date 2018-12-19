package enn.monitor.framework.pipe.common;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import enn.monitor.framework.mongo.table.MongoDBTableInstance;

public class EnnMonitorFrameworkPipeDeletedTableCtl extends MongoDBTableInstance<EnnMonitorFrameworkPipeDeletedTable> {
	
	public EnnMonitorFrameworkPipeDeletedTableCtl(String uri, String dbName, String tableName) throws Exception {
		super(uri, dbName, tableName);
	}

	@Override
	public EnnMonitorFrameworkPipeDeletedTable convertorFromDBObject(DBObject dbOjbect) throws Exception {
		EnnMonitorFrameworkPipeDeletedTable deleteInc = new EnnMonitorFrameworkPipeDeletedTable();
		
		deleteInc.setId((Long)dbOjbect.get(EnnMonitorFrameworkPipeDeletedTable.IdFieldName));
		deleteInc.setSeqNo((Long)dbOjbect.get(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName));
		
		return deleteInc;
	}

	@Override
	public DBObject convertorFromClassType(EnnMonitorFrameworkPipeDeletedTable instance) throws Exception {
		DBObject dbOjbect = new BasicDBObject();
		
		if (instance.getId() > 0) {
			dbOjbect.put(EnnMonitorFrameworkPipeDeletedTable.IdFieldName, instance.getId());
		}
		
		if (instance.getSeqNo() > 0) {
			dbOjbect.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, instance.getSeqNo());
		}
		
		return dbOjbect;
	}
	
}
