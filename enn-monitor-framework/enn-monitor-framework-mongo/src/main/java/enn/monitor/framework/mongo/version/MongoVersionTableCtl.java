package enn.monitor.framework.mongo.version;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import enn.monitor.framework.mongo.MongoDBCtrl;
import enn.monitor.framework.mongo.table.MongoDBTableInstance;

public class MongoVersionTableCtl extends MongoDBTableInstance<MongoVersionTable> {
	
	public MongoVersionTableCtl(String uri, String dbName, String tableName) throws Exception {
		super(uri, dbName, tableName);
	}

	@Override
	public MongoVersionTable convertorFromDBObject(DBObject dbObject) throws Exception {
		MongoVersionTable versionTable = new MongoVersionTable();
		
		versionTable.setTableName((String) dbObject.get(MongoVersionTable.TableNameFieldName));
		versionTable.setVersionNo((long) dbObject.get(MongoVersionTable.VersionNoFieldName));
		
		return versionTable;
	}
	
	@Override
	public DBObject convertorFromClassType(MongoVersionTable instance) throws Exception {
		DBObject dbOjbect = new BasicDBObject();
		
		if (instance.getTableName() == null) {
			throw new Exception("in MongoVersionTable, the tableName is null");
		}
		dbOjbect.put(MongoVersionTable.TableNameFieldName, instance.getTableName());
		
		if (instance.getVersionNo() > 0) {
			dbOjbect.put(MongoVersionTable.VersionNoFieldName, instance.getVersionNo());
		}
		
		return dbOjbect;
	}
	
	public MongoDBCtrl<MongoVersionTable> getMongoDBCtrl() {
		return mongoDBCtl;
	}

}
