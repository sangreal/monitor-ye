package enn.monitor.config.business.topic.tablectl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import enn.monitor.config.business.MongoConfig;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicTable;
import enn.monitor.framework.mongo.MongoDBCtrl;
import enn.monitor.framework.mongo.table.MongoDBTableInstance;

public class EnnMonitorConfigBusinessTopicTableCtl extends MongoDBTableInstance<EnnMonitorConfigBusinessTopicTable> {

	public EnnMonitorConfigBusinessTopicTableCtl(String uri, String dbName, String tableName) throws Exception {
		super(uri, dbName, tableName);
	}

	@Override
	public EnnMonitorConfigBusinessTopicTable convertorFromDBObject(DBObject dbObject) throws Exception {
		
		EnnMonitorConfigBusinessTopicTable.Builder topicTable = EnnMonitorConfigBusinessTopicTable.newBuilder();
		
		topicTable.setId((Long) dbObject.get(EnnMonitorConfigBusinessTopicTableField.IdFieldName));
		topicTable.setSeqNo((Long) dbObject.get(EnnMonitorConfigBusinessTopicTableField.SeqNoFieldName));
		
		if ((String)dbObject.get(EnnMonitorConfigBusinessTopicTableField.SourceFieldName) != null) {
			topicTable.setSource((String)dbObject.get(EnnMonitorConfigBusinessTopicTableField.SourceFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigBusinessTopicTableField.TopicFieldName) != null) {
			topicTable.setTopic((String)dbObject.get(EnnMonitorConfigBusinessTopicTableField.TopicFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigBusinessTopicTableField.CreateTimeFieldName) != null) {
			topicTable.setCreateTime((Long) dbObject.get(EnnMonitorConfigBusinessTopicTableField.CreateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigBusinessTopicTableField.LastUpdateTimeFieldName) != null) {
			topicTable.setLastUpdateTime((Long) dbObject.get(EnnMonitorConfigBusinessTopicTableField.LastUpdateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigBusinessTopicTableField.CreateUserFieldName) != null) {
			topicTable.setCreateUser((String)dbObject.get(EnnMonitorConfigBusinessTopicTableField.CreateUserFieldName));
		}
		
		if (dbObject.get(EnnMonitorConfigBusinessTopicTableField.LastUpdateUserFieldName) != null) {
			topicTable.setLastUpdateUser((String)dbObject.get(EnnMonitorConfigBusinessTopicTableField.LastUpdateUserFieldName));
		}

		return topicTable.build();
	}

	@Override
	public DBObject convertorFromClassType(EnnMonitorConfigBusinessTopicTable instance) throws Exception {
		DBObject dbObject = new BasicDBObject();
		
		dbObject.put(EnnMonitorConfigBusinessTopicTableField.IdFieldName, instance.getId());
		
		if (instance.getSeqNo() >= 0) {
			dbObject.put(EnnMonitorConfigBusinessTopicTableField.SeqNoFieldName, instance.getSeqNo());
		}
		
		if (instance.getSource() != null && instance.getSource().equals("") == false) {
			dbObject.put(EnnMonitorConfigBusinessTopicTableField.SourceFieldName, instance.getSource());
		}
		
		if (instance.getTopic() != null && instance.getTopic().equals("") == false) {
			dbObject.put(EnnMonitorConfigBusinessTopicTableField.TopicFieldName, instance.getTopic());
		}
		
		if (instance.getCreateUser() != null && instance.getCreateUser().equals("") == false) {
			dbObject.put(EnnMonitorConfigBusinessTopicTableField.CreateUserFieldName, instance.getCreateUser());
		}
		
		if (instance.getLastUpdateUser() != null && instance.getLastUpdateUser().equals("") == false) {
			dbObject.put(EnnMonitorConfigBusinessTopicTableField.LastUpdateUserFieldName, instance.getLastUpdateUser());
		}
		
		if (instance.getCreateTime() > 0) {
			dbObject.put(EnnMonitorConfigBusinessTopicTableField.CreateTimeFieldName, instance.getCreateTime());
		}
		
		if (instance.getLastUpdateTime() > 0) {
			dbObject.put(EnnMonitorConfigBusinessTopicTableField.LastUpdateTimeFieldName, instance.getLastUpdateTime());
		}
		
		return dbObject;
	}
	
	public String getTableName() {
		return MongoConfig.getTopicTable();
	}
	
	public MongoDBCtrl<EnnMonitorConfigBusinessTopicTable> getMongoDBCtrl() {
		return mongoDBCtl;
	}

}
