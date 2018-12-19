package enn.monitor.framework.mongo.table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.DBObject;

import enn.monitor.framework.mongo.MongoDBCtrl;

public abstract class MongoDBTableInstance<ClassType> {
	private static final Logger logger = LogManager.getLogger();
	
	public abstract ClassType convertorFromDBObject(DBObject dbObject) throws Exception;
	public abstract DBObject convertorFromClassType(ClassType instance) throws Exception;
	
	protected MongoDBCtrl<ClassType> mongoDBCtl = null;
	
	public MongoDBTableInstance(String uri, String dbName, String tableName) throws Exception {
		logger.info("in MongoDBTableInstance url={}, dbName={}, tableName={}",uri,dbName,tableName);
    	
        if (dbName == null || tableName == null)
            return;

        mongoDBCtl = new MongoDBCtrl<ClassType>(uri, dbName, tableName, this);
	}
	
	public MongoDBCtrl<ClassType> getMongoDBCtrl() {
		return mongoDBCtl;
	}

}
