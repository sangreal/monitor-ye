package enn.monitor.alarm.config.email.pipe;

import com.mongodb.BasicDBObject;

import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailTable;
import enn.monitor.alarm.config.email.tablectl.EnnMonitorAlarmConfigEmailTableCtl;
import enn.monitor.alarm.config.email.tablectl.EnnMonitorAlarmConfigEmailTableField;
import enn.monitor.framework.common.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerImplBase;

public class EnnMonitorAlarmConfigEmailPipeServerImpl extends EnnMonitorFrameworkPipeServerImplBase {
	
	private MongoAutoIncTableCtl autoIncCtrl = null;
	private EnnMonitorAlarmConfigEmailTableCtl validTableCtrl = null;
	private EnnMonitorFrameworkPipeDeletedTableCtl deleteTableCtrl = null;
	
	public EnnMonitorAlarmConfigEmailPipeServerImpl(MongoAutoIncTableCtl autoIncCtrl, EnnMonitorAlarmConfigEmailTableCtl validTableCtrl, EnnMonitorFrameworkPipeDeletedTableCtl deleteTableCtrl) {
		this.autoIncCtrl = autoIncCtrl;
		this.validTableCtrl = validTableCtrl;
		this.deleteTableCtrl = deleteTableCtrl;
	}

	@Override
	public long getId(Object object) throws Exception {
		EnnMonitorAlarmConfigEmailTable emailTable = (EnnMonitorAlarmConfigEmailTable) object;
		return emailTable.getId();
	}

	@Override
	public long getSeqNo(Object object) throws Exception {
		EnnMonitorAlarmConfigEmailTable emailTable = (EnnMonitorAlarmConfigEmailTable) object;
		return emailTable.getSeqNo();
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
		EnnMonitorAlarmConfigEmailTable.Builder emailTable = EnnMonitorAlarmConfigEmailTable.newBuilder((EnnMonitorAlarmConfigEmailTable) object);
		
		emailTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().insert(emailTable.build(), false);
	}

	@Override
	public void update(long id, long seqNo, Object object) throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		EnnMonitorAlarmConfigEmailTable.Builder emailTable = EnnMonitorAlarmConfigEmailTable.newBuilder((EnnMonitorAlarmConfigEmailTable) object);
		
		queryDBObject.put(EnnMonitorAlarmConfigEmailTableField.IdFieldName, id);
		emailTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().update(queryDBObject, emailTable.build(), false, false, false);
	}

	@Override
	public void updateAndInsert(long id, long seqNo) throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		EnnMonitorAlarmConfigEmailTable.Builder emailTable = EnnMonitorAlarmConfigEmailTable.newBuilder();
		
		queryDBObject.put(EnnMonitorAlarmConfigEmailTableField.IdFieldName, id);
		emailTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().update(queryDBObject, emailTable.build(), true, false, false);
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
