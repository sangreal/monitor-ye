package enn.monitor.log.config.template.tablectl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import enn.monitor.framework.mongo.MongoDBCtrl;
import enn.monitor.framework.mongo.table.MongoDBTableInstance;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateSetType;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateTable;

public class EnnMonitorLogConfigTemplateTableCtl extends MongoDBTableInstance<EnnMonitorLogConfigTemplateTable> {
	public EnnMonitorLogConfigTemplateTableCtl(String uri, String dbName, String tableName) throws Exception {
		super(uri, dbName, tableName);
	}

	@Override
	public EnnMonitorLogConfigTemplateTable convertorFromDBObject(DBObject dbObject) throws Exception {
		EnnMonitorLogConfigTemplateTable.Builder templateTable = EnnMonitorLogConfigTemplateTable.newBuilder();
		
		templateTable.setId((Long) dbObject.get(EnnMonitorLogConfigTemplateTableField.IdFieldName));
		templateTable.setSeqNo((Long) dbObject.get(EnnMonitorLogConfigTemplateTableField.SeqNoFieldName));
		
		if ((String)dbObject.get(EnnMonitorLogConfigTemplateTableField.TemplateKeyFieldName) != null) {
			templateTable.setTemplateKey((String)dbObject.get(EnnMonitorLogConfigTemplateTableField.TemplateKeyFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTableField.BelongToParentTemplateFieldName) != null) {
			templateTable.setBelongToParentTemplate((String)dbObject.get(EnnMonitorLogConfigTemplateTableField.BelongToParentTemplateFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTableField.BelongToServiceIdFieldName) != null) {
			templateTable.setBelongToServiceId((Long)dbObject.get(EnnMonitorLogConfigTemplateTableField.BelongToServiceIdFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTableField.SetTypeFieldName) != null) {
			templateTable.setSetType(EnnMonitorLogConfigTemplateSetType.valueOf((String)dbObject.get(EnnMonitorLogConfigTemplateTableField.SetTypeFieldName)));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTableField.TagIdFieldName) != null) {
			templateTable.setTagId((Long)dbObject.get(EnnMonitorLogConfigTemplateTableField.TagIdFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTableField.BatchIdFieldName) != null) {
			templateTable.setBatchId((Long)dbObject.get(EnnMonitorLogConfigTemplateTableField.BatchIdFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTableField.CreateTimeFieldName) != null) {
			templateTable.setCreateTime((Long) dbObject.get(EnnMonitorLogConfigTemplateTableField.CreateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTableField.LastUpdateTimeFieldName) != null) {
			templateTable.setLastUpdateTime((Long) dbObject.get(EnnMonitorLogConfigTemplateTableField.LastUpdateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTableField.CreateUserFieldName) != null) {
			templateTable.setCreateUser((String)dbObject.get(EnnMonitorLogConfigTemplateTableField.CreateUserFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTableField.LastUpdateUserFieldName) != null) {
			templateTable.setLastUpdateUser((String)dbObject.get(EnnMonitorLogConfigTemplateTableField.LastUpdateUserFieldName));
		}
		
		return templateTable.build();
	}
	
	@Override
	public DBObject convertorFromClassType(EnnMonitorLogConfigTemplateTable instance) throws Exception {
		
		DBObject dbObject = new BasicDBObject();
		
		dbObject.put(EnnMonitorLogConfigTemplateTableField.IdFieldName, instance.getId());
		
		if (instance.getSeqNo() >= 0) {
			dbObject.put(EnnMonitorLogConfigTemplateTableField.SeqNoFieldName, instance.getSeqNo());
		}
		
		if (instance.getTemplateKey() != null && instance.getTemplateKey().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigTemplateTableField.TemplateKeyFieldName, instance.getTemplateKey());
		}
		
		if (instance.getBelongToParentTemplate() != null && instance.getBelongToParentTemplate().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigTemplateTableField.BelongToParentTemplateFieldName, instance.getBelongToParentTemplate());
		}
		
		if (instance.getBelongToServiceId() > 0) {
			dbObject.put(EnnMonitorLogConfigTemplateTableField.BelongToServiceIdFieldName, instance.getBelongToServiceId());
		}
		
		if (instance.getSetType() != null) {
			dbObject.put(EnnMonitorLogConfigTemplateTableField.SetTypeFieldName, instance.getSetType().name());
		}
		
		dbObject.put(EnnMonitorLogConfigTemplateTableField.TagIdFieldName, instance.getTagId());
		
		if (instance.getBatchId() > 0) {
			dbObject.put(EnnMonitorLogConfigTemplateTableField.BatchIdFieldName, instance.getBatchId());
		}
		
		if (instance.getCreateUser() != null && instance.getCreateUser().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigTemplateTableField.CreateUserFieldName, instance.getCreateUser());
		}
		
		if (instance.getLastUpdateUser() != null && instance.getLastUpdateUser().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigTemplateTableField.LastUpdateUserFieldName, instance.getLastUpdateUser());
		}
		
		if (instance.getCreateTime() > 0) {
			dbObject.put(EnnMonitorLogConfigTemplateTableField.CreateTimeFieldName, instance.getCreateTime());
		}
		
		if (instance.getLastUpdateTime() > 0) {
			dbObject.put(EnnMonitorLogConfigTemplateTableField.LastUpdateTimeFieldName, instance.getLastUpdateTime());
		}
		
		return dbObject;
	}
	
	public String getTableName() {
		return EnnMonitorLogConfigTemplateDBTable.getTemplateTable();
	}
	
	public MongoDBCtrl<EnnMonitorLogConfigTemplateTable> getMongoDBCtrl() {
		return mongoDBCtl;
	}

}
