package enn.monitor.log.config.template.pipe;

import com.mongodb.BasicDBObject;

import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeTableFieldCommon;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerImplBase;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateTable;
import enn.monitor.log.config.template.tablectl.EnnMonitorLogConfigTemplateTableCtl;

public class EnnMonitorLogConfigTemplatePipeServerImpl extends EnnMonitorFrameworkPipeServerImplBase {
	
	private MongoAutoIncTableCtl autoIncCtrl = null;
	private EnnMonitorLogConfigTemplateTableCtl validTableCtrl = null;
	private EnnMonitorFrameworkPipeDeletedTableCtl deleteTableCtrl = null;
	
	public EnnMonitorLogConfigTemplatePipeServerImpl(MongoAutoIncTableCtl autoIncCtrl, EnnMonitorLogConfigTemplateTableCtl validTableCtrl, EnnMonitorFrameworkPipeDeletedTableCtl deleteTableCtrl) {
		this.autoIncCtrl = autoIncCtrl;
		this.validTableCtrl = validTableCtrl;
		this.deleteTableCtrl = deleteTableCtrl;
	}

	@Override
	public long getId(Object object) throws Exception {
		EnnMonitorLogConfigTemplateTable templateTable = (EnnMonitorLogConfigTemplateTable) object;
		return templateTable.getId();
	}

	@Override
	public long getSeqNo(Object object) throws Exception {
		EnnMonitorLogConfigTemplateTable templateTable = (EnnMonitorLogConfigTemplateTable) object;
		return templateTable.getSeqNo();
	}

	@Override
	public long incDataId() throws Exception {
		return autoIncCtrl.autoInc(validTableCtrl.getTableName() + "_ID");
	}

	@Override
	public long incDataSeqNo() throws Exception {
		return autoIncCtrl.autoInc(validTableCtrl.getTableName() + "_SEQNO");
	}

	@Override
	public void insert(long id, long seqNo, Object object) throws Exception {
		EnnMonitorLogConfigTemplateTable.Builder templateTable = EnnMonitorLogConfigTemplateTable.newBuilder((EnnMonitorLogConfigTemplateTable) object);
		
		templateTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().insert(templateTable.build(), false);
	}

	@Override
	public void update(long id, long seqNo, Object object) throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		EnnMonitorLogConfigTemplateTable.Builder templateTable = EnnMonitorLogConfigTemplateTable.newBuilder((EnnMonitorLogConfigTemplateTable) object);
		
		queryDBObject.put(EnnMonitorFrameworkPipeTableFieldCommon.IdFieldName, id);
		templateTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().update(queryDBObject, templateTable.build(), false, false, false);
	}

	@Override
	public void updateAndInsert(long id, long seqNo) throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		EnnMonitorLogConfigTemplateTable.Builder templateTable = EnnMonitorLogConfigTemplateTable.newBuilder();
		
		queryDBObject.put(EnnMonitorFrameworkPipeTableFieldCommon.IdFieldName, id);
		templateTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().update(queryDBObject, templateTable.build(), true, false, false);
	}

	@Override
	public void delete(long id, long seqNo, Object object) throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		EnnMonitorFrameworkPipeDeletedTable deletedTable = new EnnMonitorFrameworkPipeDeletedTable();
		
		deletedTable.setId(id);
		deletedTable.setSeqNo(seqNo);
		
		queryDBObject.put(EnnMonitorFrameworkPipeDeletedTable.IdFieldName, id);
		
		validTableCtrl.getMongoDBCtrl().remove(queryDBObject, false);
		deleteTableCtrl.getMongoDBCtrl().insert(deletedTable, false);
	}

}
