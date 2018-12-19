package enn.monitor.config.business.topic.pipe;

import com.mongodb.BasicDBObject;

import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicTable;
import enn.monitor.config.business.topic.tablectl.EnnMonitorConfigBusinessTopicTableCtl;
import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeTableFieldCommon;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerImplBase;

public class EnnMonitorConfigBusinessTopicPipeServerImpl extends EnnMonitorFrameworkPipeServerImplBase {
	
	private MongoAutoIncTableCtl autoIncCtrl = null;
	private EnnMonitorConfigBusinessTopicTableCtl validTableCtrl = null;
	private EnnMonitorFrameworkPipeDeletedTableCtl deleteTableCtrl = null;
	
	public EnnMonitorConfigBusinessTopicPipeServerImpl(MongoAutoIncTableCtl autoIncCtrl, EnnMonitorConfigBusinessTopicTableCtl validTableCtrl, EnnMonitorFrameworkPipeDeletedTableCtl deleteTableCtrl) {
		this.autoIncCtrl = autoIncCtrl;
		this.validTableCtrl = validTableCtrl;
		this.deleteTableCtrl = deleteTableCtrl;
	}

	@Override
	public long getId(Object object) throws Exception {
		EnnMonitorConfigBusinessTopicTable topicTable = (EnnMonitorConfigBusinessTopicTable) object;
		return topicTable.getId();
	}

	@Override
	public long getSeqNo(Object object) throws Exception {
		EnnMonitorConfigBusinessTopicTable topicTable = (EnnMonitorConfigBusinessTopicTable) object;
		return topicTable.getSeqNo();
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
		EnnMonitorConfigBusinessTopicTable.Builder topicTable = EnnMonitorConfigBusinessTopicTable.newBuilder((EnnMonitorConfigBusinessTopicTable) object);
		
		topicTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().insert(topicTable.build(), false);
	}

	@Override
	public void update(long id, long seqNo, Object object) throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		EnnMonitorConfigBusinessTopicTable.Builder topicTable = EnnMonitorConfigBusinessTopicTable.newBuilder((EnnMonitorConfigBusinessTopicTable) object);
		
		queryDBObject.put(EnnMonitorFrameworkPipeTableFieldCommon.IdFieldName, id);
		topicTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().update(queryDBObject, topicTable.build(), false, false, false);
	}

	@Override
	public void updateAndInsert(long id, long seqNo) throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		EnnMonitorConfigBusinessTopicTable.Builder topicTable = EnnMonitorConfigBusinessTopicTable.newBuilder();
		
		queryDBObject.put(EnnMonitorFrameworkPipeTableFieldCommon.IdFieldName, id);
		topicTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().update(queryDBObject, topicTable.build(), true, false, false);
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
