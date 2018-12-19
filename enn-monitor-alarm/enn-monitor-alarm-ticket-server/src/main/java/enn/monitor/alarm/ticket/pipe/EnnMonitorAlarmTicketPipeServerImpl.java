package enn.monitor.alarm.ticket.pipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.BasicDBObject;

import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTable;
import enn.monitor.alarm.ticket.tablectl.EnnMonitorAlarmTicketTableCtl;
import enn.monitor.alarm.ticket.tablectl.EnnMonitorAlarmTicketTableField;
import enn.monitor.framework.common.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerImplBase;

public class EnnMonitorAlarmTicketPipeServerImpl extends EnnMonitorFrameworkPipeServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private MongoAutoIncTableCtl autoIncCtrl = null;
	private EnnMonitorAlarmTicketTableCtl validTableCtrl = null;
	private EnnMonitorFrameworkPipeDeletedTableCtl deleteTableCtrl = null;
	
	public EnnMonitorAlarmTicketPipeServerImpl(MongoAutoIncTableCtl autoIncCtrl, EnnMonitorAlarmTicketTableCtl validTableCtrl, EnnMonitorFrameworkPipeDeletedTableCtl deleteTableCtrl) {
		this.autoIncCtrl = autoIncCtrl;
		this.validTableCtrl = validTableCtrl;
		this.deleteTableCtrl = deleteTableCtrl;
	}

	@Override
	public long getId(Object object) throws Exception {
		EnnMonitorAlarmTicketTable ticketTable = (EnnMonitorAlarmTicketTable) object;
		return ticketTable.getId();
	}

	@Override
	public long getSeqNo(Object object) throws Exception {
		EnnMonitorAlarmTicketTable ticketTable = (EnnMonitorAlarmTicketTable) object;
		return ticketTable.getSeqNo();
	}

	@Override
	public long incDataId() throws Exception {
		return -1l;
	}

	@Override
	public long incDataSeqNo() throws Exception {
		return autoIncCtrl.autoInc(validTableCtrl.getTableName() + "_SEQNO");
	}

	@Override
	public void insert(long id, long seqNo, Object object) throws Exception {
		EnnMonitorAlarmTicketTable.Builder ticketTable = EnnMonitorAlarmTicketTable.newBuilder((EnnMonitorAlarmTicketTable) object);
		
		ticketTable.setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().insert(ticketTable.build(), false);
	}

	@Override
	public void update(long id, long seqNo, Object object) throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		EnnMonitorAlarmTicketTable.Builder ticketTable = EnnMonitorAlarmTicketTable.newBuilder((EnnMonitorAlarmTicketTable) object);
		
		queryDBObject.put(EnnMonitorAlarmTicketTableField.IdFieldName, id);
		ticketTable.setId(id).setSeqNo(seqNo);
		
		logger.info(ticketTable.build().toString());
		
		validTableCtrl.getMongoDBCtrl().update(queryDBObject, ticketTable.build(), false, false, false);
	}

	@Override
	public void updateAndInsert(long id, long seqNo) throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		EnnMonitorAlarmTicketTable.Builder ticketTable = EnnMonitorAlarmTicketTable.newBuilder();
		
		queryDBObject.put(EnnMonitorAlarmTicketTableField.IdFieldName, id);
		ticketTable.setId(id).setSeqNo(seqNo);
		
		validTableCtrl.getMongoDBCtrl().update(queryDBObject, ticketTable.build(), true, false, false);
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
