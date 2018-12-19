package enn.monitor.framework.mongo.autoinc;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import enn.monitor.framework.mongo.table.MongoDBTableInstance;

public class MongoAutoIncTableCtl extends MongoDBTableInstance<MongoAutoIncTable> {
	
	public MongoAutoIncTableCtl(String uri, String dbName, String tableName) throws Exception {
		super(uri, dbName, tableName);
	}

	@Override
	public MongoAutoIncTable convertorFromDBObject(DBObject dbOjbect) throws Exception {
		MongoAutoIncTable autoInc = new MongoAutoIncTable();
		
		autoInc.setId((Long)dbOjbect.get(MongoAutoIncTable.IdFieldName));
		autoInc.setTableName((String)dbOjbect.get(MongoAutoIncTable.TableNameFieldName));
		
		return autoInc;
	}

	@Override
	public DBObject convertorFromClassType(MongoAutoIncTable instance) throws Exception {
		DBObject dbOjbect = new BasicDBObject();
		
		if (instance.getId() > 0) {
			dbOjbect.put(MongoAutoIncTable.IdFieldName, instance.getId());
		}
		
		if (instance.getTableName() != null && instance.getTableName().equals("") == false) {
			dbOjbect.put(MongoAutoIncTable.TableNameFieldName, instance.getTableName());
		}
		
		return dbOjbect;
	}
	
	public long autoInc(String table) throws Exception {
		BasicDBObject query = new BasicDBObject();
		BasicDBObject update = new BasicDBObject();
		BasicDBObject result = null;
		
		query.put(MongoAutoIncTable.TableNameFieldName, table);
		update.put("$inc", new BasicDBObject(MongoAutoIncTable.IdFieldName, 1l));
		
		result = (BasicDBObject) mongoDBCtl.findAndModify(query, update);
		
		return convertorFromDBObject(result).getId();
	}
	
}
