package enn.monitor.config.service.pipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.BasicDBObject;

import enn.monitor.config.cache.EnnMonitorConfigBlackListCacheUtil;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable;
import enn.monitor.config.service.tablectl.EnnMonitorConfigServiceTableCtl;
import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeTableFieldCommon;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerImplBase;

public class EnnMonitorConfigServicePipeServerImpl extends EnnMonitorFrameworkPipeServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private MongoAutoIncTableCtl autoIncCtrl = null;
	private EnnMonitorConfigServiceTableCtl validTableCtrl = null;
	private EnnMonitorFrameworkPipeDeletedTableCtl deleteTableCtrl = null;
	
	public EnnMonitorConfigServicePipeServerImpl(MongoAutoIncTableCtl autoIncCtrl, 
			EnnMonitorConfigServiceTableCtl validTableCtrl, 
			EnnMonitorFrameworkPipeDeletedTableCtl deleteTableCtrl) {
		this.autoIncCtrl = autoIncCtrl;
		this.validTableCtrl = validTableCtrl;
		this.deleteTableCtrl = deleteTableCtrl;
	}

	@Override
	public long getId(Object object) throws Exception {
		EnnMonitorConfigServiceTable serviceTable = (EnnMonitorConfigServiceTable) object;
		return serviceTable.getId();
	}

	@Override
	public long getSeqNo(Object object) throws Exception {
		EnnMonitorConfigServiceTable serviceTable = (EnnMonitorConfigServiceTable) object;
		return serviceTable.getSeqNo();
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
		EnnMonitorConfigServiceTable.Builder serviceTable = EnnMonitorConfigServiceTable.newBuilder((EnnMonitorConfigServiceTable) object);
		
		if (EnnMonitorConfigBlackListCacheUtil.containServiceLineId(serviceTable.getBelongToServiceLine()) == true) {
			return;
		}
		
		serviceTable.setId(id);
		serviceTable.setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().insert(serviceTable.build(), false);
	}

	@Override
	public void update(long id, long seqNo, Object object) throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		EnnMonitorConfigServiceTable.Builder service = EnnMonitorConfigServiceTable.newBuilder((EnnMonitorConfigServiceTable) object);
		
		queryDBObject.put(EnnMonitorFrameworkPipeTableFieldCommon.IdFieldName, id);
		service.setId(id).setSeqNo(seqNo);
		
		logger.info(service.build().toString());
		
		validTableCtrl.getMongoDBCtrl().update(queryDBObject, service.build(), false, false, false);
	}

	@Override
	public void updateAndInsert(long id, long seqNo) throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		EnnMonitorConfigServiceTable.Builder serviceTable = EnnMonitorConfigServiceTable.newBuilder();
		
		queryDBObject.put(EnnMonitorFrameworkPipeTableFieldCommon.IdFieldName, id);
		serviceTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().update(queryDBObject, serviceTable.build(), true, false, false);
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
