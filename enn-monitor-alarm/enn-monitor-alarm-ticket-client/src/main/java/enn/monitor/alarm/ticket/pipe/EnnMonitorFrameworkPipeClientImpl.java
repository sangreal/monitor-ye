package enn.monitor.alarm.ticket.pipe;

import java.util.List;

import enn.monitor.alarm.ticket.grpc.client.EnnMonitorAlarmTicketGrpcClient;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeleted;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTable;
import enn.monitor.framework.pipe.client.EnnMonitorFrameworkPipeClientImplBase;

public abstract class EnnMonitorFrameworkPipeClientImpl extends EnnMonitorFrameworkPipeClientImplBase {
	
	private EnnMonitorAlarmTicketGrpcClient tokenClient = null;
	
	public EnnMonitorFrameworkPipeClientImpl(EnnMonitorAlarmTicketGrpcClient tokenClient) {
		this.tokenClient = tokenClient;
	}

	@Override
	public long getMaxDeletedSeqNo() throws Exception {
		return tokenClient.getEnnMonitorAlarmTicketMaxDeletedSeqNo();
	}

	@Override
	public long getValidId(Object object) throws Exception {
		EnnMonitorAlarmTicketTable tokenTable = (EnnMonitorAlarmTicketTable) object;
		return tokenTable.getId();
	}

	@Override
	public long getValidSeqNo(Object object) throws Exception {
		EnnMonitorAlarmTicketTable tokenTable = (EnnMonitorAlarmTicketTable) object;
		return tokenTable.getSeqNo();
	}
	
	@Override
	public long getDeletedId(Object object) throws Exception {
		EnnMonitorAlarmTicketGetDeleted ticketTable = (EnnMonitorAlarmTicketGetDeleted) object;
		return ticketTable.getId();
	}
	
	@Override
	public long getDeletedSeqNo(Object object) throws Exception {
		EnnMonitorAlarmTicketGetDeleted ticketTable = (EnnMonitorAlarmTicketGetDeleted) object;
		return ticketTable.getSeqNo();
	}

	@Override
	public List<Object> getValidDataList(long startSeqNo, int batch) throws Exception {
		return tokenClient.getEnnMonitorAlarmTicketTableList(startSeqNo, batch);
	}

	@Override
	public List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception {
		return tokenClient.getEnnMonitorAlarmTicketGetDeletedList(startSeqNo, batch);
	}

	@Override
	protected abstract void updateAndInsert(Object object);

	@Override
	protected abstract void delete(Object object);

}
