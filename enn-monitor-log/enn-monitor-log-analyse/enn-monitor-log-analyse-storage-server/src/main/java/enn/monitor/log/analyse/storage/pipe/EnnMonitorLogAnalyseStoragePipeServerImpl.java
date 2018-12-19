package enn.monitor.log.analyse.storage.pipe;

import com.mongodb.BasicDBObject;

import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerImplBase;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageTable;
import enn.monitor.log.analyse.storage.tablectl.EnnMonitorLogAnalyseStorageTableCtl;
import enn.monitor.log.analyse.storage.tablectl.EnnMonitorLogAnalyseStorageTableField;

public class EnnMonitorLogAnalyseStoragePipeServerImpl extends EnnMonitorFrameworkPipeServerImplBase {
	
	private MongoAutoIncTableCtl autoIncCtrl = null;
	private EnnMonitorLogAnalyseStorageTableCtl validTableCtrl = null;
	private EnnMonitorFrameworkPipeDeletedTableCtl deleteTableCtrl = null;
	
	public EnnMonitorLogAnalyseStoragePipeServerImpl(MongoAutoIncTableCtl autoIncCtrl, EnnMonitorLogAnalyseStorageTableCtl validTableCtrl, EnnMonitorFrameworkPipeDeletedTableCtl deleteTableCtrl) {
		this.autoIncCtrl = autoIncCtrl;
		this.validTableCtrl = validTableCtrl;
		this.deleteTableCtrl = deleteTableCtrl;
	}

	@Override
	public long getId(Object object) throws Exception {
		EnnMonitorLogAnalyseStorageTable collectorDataTable = (EnnMonitorLogAnalyseStorageTable) object;
		return collectorDataTable.getId();
	}

	@Override
	public long getSeqNo(Object object) throws Exception {
		EnnMonitorLogAnalyseStorageTable collectorDataTable = (EnnMonitorLogAnalyseStorageTable) object;
		return collectorDataTable.getSeqNo();
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
		EnnMonitorLogAnalyseStorageTable.Builder collectorDataTable = EnnMonitorLogAnalyseStorageTable.newBuilder((EnnMonitorLogAnalyseStorageTable) object);
		
		collectorDataTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().insert(collectorDataTable.build(), false);
	}

	@Override
	public void update(long id, long seqNo, Object object) throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		EnnMonitorLogAnalyseStorageTable.Builder collectorDataTable = EnnMonitorLogAnalyseStorageTable.newBuilder((EnnMonitorLogAnalyseStorageTable) object);
		
		queryDBObject.put(EnnMonitorLogAnalyseStorageTableField.IdFieldName, id);
		collectorDataTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().update(queryDBObject, collectorDataTable.build(), false, false, false);
	}

	@Override
	public void updateAndInsert(long id, long seqNo) throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		EnnMonitorLogAnalyseStorageTable.Builder collectorDataTable = EnnMonitorLogAnalyseStorageTable.newBuilder();
		
		queryDBObject.put(EnnMonitorLogAnalyseStorageTableField.IdFieldName, id);
		collectorDataTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().update(queryDBObject, collectorDataTable.build(), true, false, false);
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
