package enn.monitor.alarm.ticket.cache.channel;

import java.util.ArrayList;
import java.util.List;

import enn.monitor.alarm.ticket.cache.data.EnnMonitorAlarmCache;
import enn.monitor.alarm.ticket.impl.EnnMonitorAlarmTicketHandler;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeleted;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTable;
import enn.monitor.framework.pipe.client.EnnMonitorFrameworkPipeClientImplBase;

public class EnnMonitorAlarmTicketCachePipeClientImpl extends EnnMonitorFrameworkPipeClientImplBase {
	
	private EnnMonitorAlarmTicketHandler ticketHandler = null;
	private EnnMonitorAlarmCache alarmCache = null;
	
	public EnnMonitorAlarmTicketCachePipeClientImpl(EnnMonitorAlarmTicketHandler ticketHandler, EnnMonitorAlarmCache alarmCache) {
		this.ticketHandler = ticketHandler;
		this.alarmCache = alarmCache;
	}

	@Override
	public long getMaxDeletedSeqNo() throws Exception {
		return ticketHandler.getEnnMonitorAlarmTicketMaxDeletedSeqNo();
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
		List<EnnMonitorAlarmTicketTable> ticketTableList = null;
		
		ticketTableList = ticketHandler.getEnnMonitorAlarmTicketValid(startSeqNo, batch);
		
		return new ArrayList<Object>(ticketTableList);
	}

	@Override
	public List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception {
		List<EnnMonitorAlarmTicketGetDeleted> ticketTableList = null;
		
		ticketTableList = ticketHandler.getEnnMonitorAlarmTicketDeleted(startSeqNo, batch);
		
		return new ArrayList<Object>(ticketTableList);
	}

	@Override
	protected void updateAndInsert(Object object) throws Exception {
		alarmCache.pipeUpdateAndInsert((EnnMonitorAlarmTicketTable) object);
	}

	@Override
	protected void delete(Object object) throws Exception {
		alarmCache.pipeDelete((EnnMonitorAlarmTicketTable) object);
	}

}
