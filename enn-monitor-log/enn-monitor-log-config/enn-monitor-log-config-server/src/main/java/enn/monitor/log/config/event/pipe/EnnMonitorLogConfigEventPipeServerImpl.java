package enn.monitor.log.config.event.pipe;

import com.mongodb.BasicDBObject;

import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeTableFieldCommon;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerImplBase;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventTable;
import enn.monitor.log.config.event.tablectl.EnnMonitorLogConfigEventTableCtl;

public class EnnMonitorLogConfigEventPipeServerImpl extends EnnMonitorFrameworkPipeServerImplBase {
	
	private MongoAutoIncTableCtl autoIncCtrl = null;
	private EnnMonitorLogConfigEventTableCtl validTableCtrl = null;
	private EnnMonitorFrameworkPipeDeletedTableCtl deleteTableCtrl = null;
	
	public EnnMonitorLogConfigEventPipeServerImpl(MongoAutoIncTableCtl autoIncCtrl, EnnMonitorLogConfigEventTableCtl validTableCtrl, EnnMonitorFrameworkPipeDeletedTableCtl deleteTableCtrl) {
		this.autoIncCtrl = autoIncCtrl;
		this.validTableCtrl = validTableCtrl;
		this.deleteTableCtrl = deleteTableCtrl;
	}

	@Override
	public long getId(Object object) throws Exception {
		EnnMonitorLogConfigEventTable eventTable = (EnnMonitorLogConfigEventTable) object;
		return eventTable.getId();
	}

	@Override
	public long getSeqNo(Object object) throws Exception {
		EnnMonitorLogConfigEventTable eventTable = (EnnMonitorLogConfigEventTable) object;
		return eventTable.getSeqNo();
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
		EnnMonitorLogConfigEventTable.Builder eventTable = EnnMonitorLogConfigEventTable.newBuilder((EnnMonitorLogConfigEventTable) object);
		
		eventTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().insert(eventTable.build(), false);
	}

	@Override
	public void update(long id, long seqNo, Object object) throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		EnnMonitorLogConfigEventTable.Builder eventTable = EnnMonitorLogConfigEventTable.newBuilder((EnnMonitorLogConfigEventTable) object);
		
		queryDBObject.put(EnnMonitorFrameworkPipeTableFieldCommon.IdFieldName, id);
		eventTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().update(queryDBObject, eventTable.build(), false, false, false);
	}

	@Override
	public void updateAndInsert(long id, long seqNo) throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		EnnMonitorLogConfigEventTable.Builder eventTable = EnnMonitorLogConfigEventTable.newBuilder();
		
		queryDBObject.put(EnnMonitorFrameworkPipeTableFieldCommon.IdFieldName, id);
		eventTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().update(queryDBObject, eventTable.build(), true, false, false);
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
