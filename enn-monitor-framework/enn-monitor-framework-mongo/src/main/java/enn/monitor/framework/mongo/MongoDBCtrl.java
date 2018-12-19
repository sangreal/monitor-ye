package enn.monitor.framework.mongo;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;

import enn.monitor.framework.mongo.table.MongoDBTableInstance;

public class MongoDBCtrl<ClassType> {
    private static final Logger logger = LogManager.getLogger();

    private static Object mongoDBCtrlLock = MongoDBCtrl.class;
    private volatile DB mongoDB = null;

    private final String mongoDBUri;
    private final String dbName;
    private final String dbCollName;
    
    private MongoDBTableInstance<ClassType> instance = null;

    private DBCollection dbCollection = null;

    public MongoDBCtrl(String uri, String dbName, String collectionName, MongoDBTableInstance<ClassType> instance) throws Exception {
        if (dbName == null || collectionName == null) {
            throw new Exception("in MongoDBCtrl, the argument is null");
        }

        this.mongoDBUri = uri;
        this.dbName = dbName;
        this.dbCollName = collectionName;
        this.instance = instance;

        if (mongoDB == null) {
            initMongoDB();
        }

        dbCollection = mongoDB.getCollection(dbCollName);
    }

    public long getCount(DBObject query) throws Exception {
        if (dbCollection == null) {
            throw new Exception("in getCount, the dbCollection is null");
        }

        if (query == null) {
            return dbCollection.count();
        } else {
            return dbCollection.count(query);
        }
    }
    
    public List<ClassType> searchData(BasicDBObject basicDBObject, 
            BasicDBObject orderDBObject, int numToSkip, int batchSize) throws Exception {

        List<DBObject> objectsList = null;
        List<ClassType> typeList = null;
        
        DBCursor cursor = null;
        
        if (basicDBObject == null) {
            throw new Exception("in searchData, The mongoDBCtrl or basicDBObject is null");
        }

        cursor = find(basicDBObject, orderDBObject, numToSkip, batchSize);
        if (cursor == null) {
            return null;
        }

        objectsList = cursorGetData(cursor);
        closeCursor(cursor);
        
        typeList = new ArrayList<ClassType>();
        for (int i = 0; i < objectsList.size(); ++i) {
        	typeList.add(instance.convertorFromDBObject(objectsList.get(i)));
        }

        return typeList;
    }
    
    public List<ClassType> searchAllData(BasicDBObject basicDBObject, 
            BasicDBObject orderDBObject) throws Exception {

        List<DBObject> objectsList = null;
        List<ClassType> typeList = null;
        
        DBCursor cursor = null;

        cursor = findAll(basicDBObject, orderDBObject);
        if (cursor == null) {
            return null;
        }

        objectsList = cursorGetData(cursor);
        closeCursor(cursor);
        
        typeList = new ArrayList<ClassType>();
        for (int i = 0; i < objectsList.size(); ++i) {
        	typeList.add(instance.convertorFromDBObject(objectsList.get(i)));
        }

        return typeList;
    }
    
    public void saveData(BasicDBObject basicDBObjectQuery, BasicDBObject basicDBObject, 
    		boolean upsert, boolean multi, boolean isConcern)throws Exception{
    	update(basicDBObjectQuery, basicDBObject, upsert, multi, isConcern);                   
    }

    private DBCursor find(BasicDBObject searchCond, BasicDBObject orderBy, int numToSkip, int batchSize) throws Exception {
        if (dbCollection == null) {
            throw new Exception("in find, the dbCollection is null");
        }

        DBCursor cursor = null;

        if (searchCond != null) {
            cursor = dbCollection.find(searchCond).skip(numToSkip).limit(batchSize);
        } else {
            cursor = dbCollection.find().skip(numToSkip).limit(batchSize);
        }

        if (orderBy != null) {
            cursor.sort(orderBy);
        }

        return cursor;
    }
    
    private DBCursor findAll(BasicDBObject searchCond, BasicDBObject orderBy) throws Exception {
        if (dbCollection == null) {
            throw new Exception("in findAll, the dbCollection is null");
        }

        DBCursor cursor = null;

        if (searchCond != null) {
            cursor = dbCollection.find(searchCond);
        } else {
            cursor = dbCollection.find();
        }

        if (orderBy != null) {
            cursor.sort(orderBy);
        }

        return cursor;
    }
    
    private static List<DBObject> cursorGetData(DBCursor cursor)
            throws Exception {
        List<DBObject> objectList = new ArrayList<DBObject>();

        if (cursor == null) {
            throw new Exception("in getData, the cursor is null");
        }

        while (cursor.hasNext() == true) {
            DBObject dbo = cursor.next();
            objectList.add(dbo);
        }

        return objectList;
    }

    private static void closeCursor(DBCursor cursor) {
        if (cursor == null) {
            return;
        }

        cursor.close();
    }

    public DBObject findAndModify(BasicDBObject query, BasicDBObject update) throws Exception {
        if (query == null || update == null) {
            throw new Exception("in findAndModify, the argument is null");
        }

        if (dbCollection == null) {
            throw new Exception("in findAndModify, the dbCollection is null");
        }

        DBObject dbObject = dbCollection.findAndModify(query, null, null, false, update, true, true);

        return dbObject;
    }
    
    public void insert(DBObject dataObject, boolean isConcern) throws Exception {
    	if (dataObject == null) {
            throw new Exception("in insert, the argument is null");
        }

        if (dbCollection == null) {
            throw new Exception("in insert, the dbCollection is null");
        }

        if (isConcern == true) {
            dbCollection.insert(dataObject, new WriteConcern(true));
        } else {
            dbCollection.insert(dataObject);
        }
    }
    
    public void insert(ClassType dataObject, boolean isConcern) throws Exception {
    	if (dataObject == null) {
            throw new Exception("in insert, the argument is null");
        }

        if (dbCollection == null) {
            throw new Exception("in insert, the dbCollection is null");
        }

        if (isConcern == true) {
            dbCollection.insert(instance.convertorFromClassType(dataObject), new WriteConcern(true));
        } else {
            dbCollection.insert(instance.convertorFromClassType(dataObject));
        }
    }

    public void insertBatch(List<DBObject> dbObjectList, boolean isConcern) throws Exception {
    	if (dbObjectList == null) {
            throw new Exception("in insertBatch, the argument is null");
        }

    	if (isConcern == true) {
    		dbCollection.insert(dbObjectList, new WriteConcern(true));
        } else {
        	dbCollection.insert(dbObjectList);
        }
    }
    
    public void insertBatchWithClassType(List<ClassType> classTypeList, boolean isConcern) throws Exception {
    	if (classTypeList == null) {
            throw new Exception("in insertBatch, the argument is null");
        }
    	
    	List<DBObject> dbObjectList = new ArrayList<DBObject>();
    	for (int i = 0; i < classTypeList.size(); ++i) {
    		dbObjectList.add(instance.convertorFromClassType(classTypeList.get(i)));
    	}
    	
    	if (isConcern == true) {
    		dbCollection.insert(dbObjectList, new WriteConcern(true));
        } else {
        	dbCollection.insert(dbObjectList);
        }
    }

    /*
     * upsert: true-如果没有找到相应的文档，就插入 multi: true-所有匹配的文档都更新 false-只匹配找到的第一个文档
     */
    public void update(DBObject query, DBObject dbObject, boolean upsert, boolean multi, boolean isConcern) throws Exception {
        if (query == null || dbObject == null) {
            throw new Exception("in update, the argument is null");
        }

        if (dbCollection == null) {
            throw new Exception("in update, the dbCollection is null");
        }

        BasicDBObject dataDBObject = new BasicDBObject("$set", dbObject);
        
        if (isConcern == true) {
            dbCollection.update(query, dataDBObject, upsert, multi, new WriteConcern(true));
        } else {
        	dbCollection.update(query, dataDBObject, upsert, multi);
        }
    }
    
    public void update(DBObject query, ClassType dbObject, boolean upsert, boolean multi, boolean isConcern) throws Exception {
        if (query == null || dbObject == null) {
            throw new Exception("in update, the argument is null");
        }

        if (dbCollection == null) {
            throw new Exception("in update, the dbCollection is null");
        }

        BasicDBObject dataDBObject = new BasicDBObject("$set", instance.convertorFromClassType(dbObject));
        
        if (isConcern == true) {
            dbCollection.update(query, dataDBObject, upsert, multi, new WriteConcern(true));
        } else {
        	dbCollection.update(query, dataDBObject, upsert, multi);
        }
    }

    public void remove(BasicDBObject query, boolean isConcern) throws Exception {
        if (query == null) {
            query = new BasicDBObject();
        }

        if (dbCollection == null) {
            throw new Exception("in remove, the dbCollection is null");
        }

        if (isConcern == true) {
            dbCollection.remove(query, new WriteConcern(true));
        } else {
            dbCollection.remove(query);
        }
    }

    public void purge() throws Exception {
        if (dbCollection == null) {
            throw new Exception("in purge, the dbCollection is null");
        }

        dbCollection.drop();
    }

    /*
     * 索引相关
     */
    public void createIndex(DBObject keys, DBObject options) throws Exception {
        if (dbCollection == null) {
            throw new Exception("in createIndex, the dbCollection is null");
        }

        if (options == null) {
            dbCollection.createIndex(keys);
        } else {
            dbCollection.createIndex(keys, options);
        }
    }

    public void dropIndexes() throws Exception {
        if (dbCollection == null) {
            throw new Exception("in dropIndexes, the dbCollection is null");
        }

        dbCollection.dropIndexes();
    }

    public String getDbName() {
        return dbName;
    }

    public String getCollName() {
        return dbCollName;
    }

    public void closeMongoDB() {
        if (mongoDB != null) {
            synchronized (mongoDBCtrlLock) {
                if (mongoDB != null) {
                    mongoDB = null;
                }
            }
        }
    }

    public CommandResult lock() {
        return mongoDB.getMongo().fsyncAndLock();
    }

    public void unLock() {
        mongoDB.getMongo().unlock();
    }

    public void dropDB() {
    	mongoDB.dropDatabase();
    }
    
    public void dropDataTable() {
    	dbCollection.drop();
    }

    public boolean checkExistedFieldValue(BasicDBObject searchCond) {

        DBObject dbObject = dbCollection.findOne(searchCond);

        if (dbObject == null)
            return false;

        return true;
    }

    private void initMongoDB() throws Exception {
        if (mongoDB == null) {
            synchronized (mongoDBCtrlLock) {
                if (mongoDB == null) {
                    logger.info("mongoUri:" + mongoDBUri);

                    MongoClientOptions.Builder builder = new MongoClientOptions.Builder();;

                    MongoClient mongo = new MongoClient(new MongoClientURI(mongoDBUri, builder));

                    builder.connectTimeout(1000 * 5);
                    builder.connectionsPerHost(1000 * 1000);
                    builder.threadsAllowedToBlockForConnectionMultiplier(100);

                    mongoDB = mongo.getDB(dbName);
                }
            }
        }
    }
}
