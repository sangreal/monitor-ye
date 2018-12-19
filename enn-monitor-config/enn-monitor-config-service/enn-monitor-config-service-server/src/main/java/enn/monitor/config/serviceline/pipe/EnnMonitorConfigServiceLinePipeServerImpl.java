package enn.monitor.config.serviceline.pipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.BasicDBObject;

import enn.monitor.config.cache.EnnMonitorConfigBlackListCacheUtil;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineTable;
import enn.monitor.config.serviceline.tablectl.EnnMonitorConfigServiceLineTableCtl;
import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeTableFieldCommon;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerImplBase;

public class EnnMonitorConfigServiceLinePipeServerImpl extends EnnMonitorFrameworkPipeServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private MongoAutoIncTableCtl autoIncCtrl = null;
	private EnnMonitorConfigServiceLineTableCtl validTableCtrl = null;
	private EnnMonitorFrameworkPipeDeletedTableCtl deleteTableCtrl = null;
	
	public EnnMonitorConfigServiceLinePipeServerImpl(MongoAutoIncTableCtl autoIncCtrl, 
			EnnMonitorConfigServiceLineTableCtl validTableCtrl, 
			EnnMonitorFrameworkPipeDeletedTableCtl deleteTableCtrl) {
		this.autoIncCtrl = autoIncCtrl;
		this.validTableCtrl = validTableCtrl;
		this.deleteTableCtrl = deleteTableCtrl;
	}

	@Override
	public long getId(Object object) throws Exception {
		EnnMonitorConfigServiceLineTable serviceLineTable = (EnnMonitorConfigServiceLineTable) object;
		return serviceLineTable.getId();
	}

	@Override
	public long getSeqNo(Object object) throws Exception {
		EnnMonitorConfigServiceLineTable serviceLineTable = (EnnMonitorConfigServiceLineTable) object;
		return serviceLineTable.getSeqNo();
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
		EnnMonitorConfigServiceLineTable.Builder serviceLineTable = EnnMonitorConfigServiceLineTable.newBuilder((EnnMonitorConfigServiceLineTable) object);
		
		if (EnnMonitorConfigBlackListCacheUtil.containClusterId(serviceLineTable.getBelongToCluster()) == true) {
			return;
		}
		
		serviceLineTable.setId(id);
		serviceLineTable.setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().insert(serviceLineTable.build(), false);
	}

	@Override
	public void update(long id, long seqNo, Object object) throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		EnnMonitorConfigServiceLineTable.Builder serviceLine = EnnMonitorConfigServiceLineTable.newBuilder((EnnMonitorConfigServiceLineTable) object);
		
		queryDBObject.put(EnnMonitorFrameworkPipeTableFieldCommon.IdFieldName, id);
		serviceLine.setId(id).setSeqNo(seqNo);
		
		logger.info(serviceLine.build().toString());
		
		validTableCtrl.getMongoDBCtrl().update(queryDBObject, serviceLine.build(), false, false, false);
	}

	@Override
	public void updateAndInsert(long id, long seqNo) throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		EnnMonitorConfigServiceLineTable.Builder serviceLineTable = EnnMonitorConfigServiceLineTable.newBuilder();
		
		queryDBObject.put(EnnMonitorFrameworkPipeTableFieldCommon.IdFieldName, id);
		serviceLineTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().update(queryDBObject, serviceLineTable.build(), true, false, false);
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
