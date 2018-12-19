package enn.monitor.log.config.template.term.tablectl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import enn.monitor.framework.mongo.MongoDBCtrl;
import enn.monitor.framework.mongo.table.MongoDBTableInstance;
import enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermTable;

public class EnnMonitorLogConfigTemplateTermTableCtl extends MongoDBTableInstance<EnnMonitorLogConfigTemplateTermTable> {
	public EnnMonitorLogConfigTemplateTermTableCtl(String uri, String dbName, String tableName) throws Exception {
		super(uri, dbName, tableName);
	}

	@Override
	public EnnMonitorLogConfigTemplateTermTable convertorFromDBObject(DBObject dbObject) throws Exception {
		EnnMonitorLogConfigTemplateTermTable.Builder templateTermTable = EnnMonitorLogConfigTemplateTermTable.newBuilder();
		
		templateTermTable.setId((Long) dbObject.get(EnnMonitorLogConfigTemplateTermTableField.IdFieldName));
		
		if ((String)dbObject.get(EnnMonitorLogConfigTemplateTermTableField.TemplateTermFieldName) != null) {
			templateTermTable.setTemplateTerm((String)dbObject.get(EnnMonitorLogConfigTemplateTermTableField.TemplateTermFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTermTableField.BelongToServiceIdFieldName) != null) {
			templateTermTable.setBelongToServiceId((Long)dbObject.get(EnnMonitorLogConfigTemplateTermTableField.BelongToServiceIdFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTermTableField.TemplateTermValueFieldName) != null) {
			templateTermTable.setTemplateTermValue((Double)dbObject.get(EnnMonitorLogConfigTemplateTermTableField.TemplateTermValueFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTermTableField.IsSelectedFieldName) != null) {
			templateTermTable.setIsSelected((Boolean)dbObject.get(EnnMonitorLogConfigTemplateTermTableField.IsSelectedFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTermTableField.BatchIdFieldName) != null) {
			templateTermTable.setBatchId((Long)dbObject.get(EnnMonitorLogConfigTemplateTermTableField.BatchIdFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTermTableField.CreateTimeFieldName) != null) {
			templateTermTable.setCreateTime((Long) dbObject.get(EnnMonitorLogConfigTemplateTermTableField.CreateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTermTableField.LastUpdateTimeFieldName) != null) {
			templateTermTable.setLastUpdateTime((Long) dbObject.get(EnnMonitorLogConfigTemplateTermTableField.LastUpdateTimeFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTermTableField.CreateUserFieldName) != null) {
			templateTermTable.setCreateUser((String)dbObject.get(EnnMonitorLogConfigTemplateTermTableField.CreateUserFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTermTableField.LastUpdateUserFieldName) != null) {
			templateTermTable.setLastUpdateUser((String)dbObject.get(EnnMonitorLogConfigTemplateTermTableField.LastUpdateUserFieldName));
		}
		
		if (dbObject.get(EnnMonitorLogConfigTemplateTermTableField.TemplateTermKeyFieldName) != null) {
			templateTermTable.setTemplateTermKey((String)dbObject.get(EnnMonitorLogConfigTemplateTermTableField.TemplateTermKeyFieldName));
		}
		
		return templateTermTable.build();
	}
	
	@Override
	public DBObject convertorFromClassType(EnnMonitorLogConfigTemplateTermTable instance) throws Exception {
		
		DBObject dbObject = new BasicDBObject();
		
		dbObject.put(EnnMonitorLogConfigTemplateTermTableField.IdFieldName, instance.getId());
		
		if (instance.getTemplateTerm() != null && instance.getTemplateTerm().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigTemplateTermTableField.TemplateTermFieldName, instance.getTemplateTerm());
		}
		
		dbObject.put(EnnMonitorLogConfigTemplateTermTableField.BelongToServiceIdFieldName, instance.getBelongToServiceId());
		
		if (instance.getTemplateTermValue() >= 0.0) {
			dbObject.put(EnnMonitorLogConfigTemplateTermTableField.TemplateTermValueFieldName, instance.getTemplateTermValue());
		}
		
		dbObject.put(EnnMonitorLogConfigTemplateTermTableField.IsSelectedFieldName, instance.getIsSelected());
		
		if (instance.getBatchId() > 0) {
			dbObject.put(EnnMonitorLogConfigTemplateTermTableField.BatchIdFieldName, instance.getBatchId());
		}
		
		if (instance.getCreateUser() != null && instance.getCreateUser().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigTemplateTermTableField.CreateUserFieldName, instance.getCreateUser());
		}
		
		if (instance.getLastUpdateUser() != null && instance.getLastUpdateUser().equals("") == false) {
			dbObject.put(EnnMonitorLogConfigTemplateTermTableField.LastUpdateUserFieldName, instance.getLastUpdateUser());
		}
		
		if (instance.getCreateTime() > 0) {
			dbObject.put(EnnMonitorLogConfigTemplateTermTableField.CreateTimeFieldName, instance.getCreateTime());
		}
		
		if (instance.getLastUpdateTime() > 0) {
			dbObject.put(EnnMonitorLogConfigTemplateTermTableField.LastUpdateTimeFieldName, instance.getLastUpdateTime());
		}
		
		dbObject.put(EnnMonitorLogConfigTemplateTermTableField.TemplateTermKeyFieldName, instance.getTemplateTermKey());
		
		return dbObject;
	}
	
	public String getTableName() {
		return EnnMonitorLogConfigTemplateTermDBTable.getTemplateTermTable();
	}
	
	public MongoDBCtrl<EnnMonitorLogConfigTemplateTermTable> getMongoDBCtrl() {
		return mongoDBCtl;
	}

}
