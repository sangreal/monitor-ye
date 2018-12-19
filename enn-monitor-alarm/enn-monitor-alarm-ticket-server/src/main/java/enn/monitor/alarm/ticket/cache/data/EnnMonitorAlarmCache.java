package enn.monitor.alarm.ticket.cache.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.alarm.ticket.autocover.channel.EnnMonitorAlarmTicketAutoRecoverChannel;
import enn.monitor.alarm.ticket.email.channel.EnnMonitorAlarmTicketEmailChannel;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketStatus;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTable;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState;
import enn.monitor.alarm.ticket.state.EnnMonitorAlarmTicketStateFactory;
import enn.monitor.alarm.ticket.state.EnnMonitorAlarmTicketStateInterface;
import enn.monitor.framework.log.channel.ChannelWriteData;

/*
 1. hashMap有异步问题
 2. delete,pipe传入的是id,但是grpc接口传入的是table
 3. cache的逻辑需要重新理顺
 */
public class EnnMonitorAlarmCache {
	
	private static final Logger logger = LogManager.getLogger();
	
	private Map<String, EnnMonitorAlarmTicketTable> ticketTableMap = new HashMap<String, EnnMonitorAlarmTicketTable>();
	private Map<Long, String> ticketIdTicketKeyMap = new HashMap<Long, String>();
	
	private EnnMonitorAlarmTicketAutoRecoverChannel autoRecoverChannel = null;
	private EnnMonitorAlarmTicketEmailChannel emailChannel = null;
	
	public EnnMonitorAlarmCache(EnnMonitorAlarmTicketAutoRecoverChannel autoRecoverChannel, EnnMonitorAlarmTicketEmailChannel emailChannel) {
		this.autoRecoverChannel = autoRecoverChannel;
		this.emailChannel = emailChannel;
	}

	public void pipeUpdateAndInsert(EnnMonitorAlarmTicketTable ticketTable) throws Exception {
		dealEnnMonitorAlarmTicketState(ticketTable);
	}
	
	public void pipeDelete(EnnMonitorAlarmTicketTable ticketTable) throws Exception {
		ticketTableMap.remove(ticketTable.getTicketKey());
	}

	public EnnMonitorAlarmTicketTable add(EnnMonitorAlarmTicketTable ticketTable) throws Exception {
		if (ticketTableMap.containsKey(ticketTable.getTicketKey()) == true) {
			return null;
		}
		
		EnnMonitorAlarmTicketTable.Builder newTicketTableBuilder = EnnMonitorAlarmTicketTable.newBuilder(ticketTable);
		EnnMonitorAlarmTicketTable newTicketTable = null;
		
		if (ticketTable.getAutomation() == true) {
			newTicketTableBuilder.setEnnMonitorAlarmTicketStatus(EnnMonitorAlarmTicketStatus.WaitingAutoRecover);
		} else {
			newTicketTableBuilder.setEnnMonitorAlarmTicketStatus(EnnMonitorAlarmTicketStatus.WaitingNotify);
		}
		
		newTicketTable = newTicketTableBuilder.build();
		dealEnnMonitorAlarmTicketState(newTicketTable);
		
		return newTicketTable;
	}
	
	public void update(EnnMonitorAlarmTicketTable ticketTable) throws Exception {
		EnnMonitorAlarmTicketTable.Builder newTicketTable = null;
		
		if (ticketIdTicketKeyMap.containsKey(ticketTable.getId()) == false) {
			throw new Exception("the id in cache is missed, the id is " + ticketTable.getId());
		}
		
		newTicketTable = EnnMonitorAlarmTicketTable.newBuilder(ticketTableMap.get(ticketIdTicketKeyMap.get(ticketTable.getId())));
		
		if (ticketTable.getLastUpdateUser() != null && ticketTable.getLastUpdateUser().equals("") == false) {
			newTicketTable.setLastUpdateUser(ticketTable.getLastUpdateUser());
		}
		
		if (ticketTable.getEnnMonitorAlarmTicketStatus() != EnnMonitorAlarmTicketStatus.UNRECOGNIZED) {
			newTicketTable.setEnnMonitorAlarmTicketStatus(ticketTable.getEnnMonitorAlarmTicketStatus());
		}
		
		if (ticketTable.getGrourName() != null && ticketTable.getGrourName().equals("") == false) {
			newTicketTable.setGrourName(ticketTable.getGrourName());
		}
		
		if (ticketTable.getError() != null && ticketTable.getError().equals("") == false) {
			newTicketTable.setError(ticketTable.getError());
		}
		
		if (ticketTable.getErrorReason() != null && ticketTable.getErrorReason().equals("") == false) {
			newTicketTable.setErrorReason(ticketTable.getErrorReason());
		}
		
		if (ticketTable.getRemark() != null && ticketTable.getRemark().equals("") == false) {
			newTicketTable.setRemark(ticketTable.getRemark());
		}
		
		ticketTableMap.put(ticketIdTicketKeyMap.get(ticketTable.getId()), newTicketTable.build());
	}
	
	public EnnMonitorAlarmTicketTable updateState(EnnMonitorAlarmTicketTransformState ticketState) throws Exception {
		EnnMonitorAlarmTicketStateInterface stateInterface = null;
		
		EnnMonitorAlarmTicketTable ticketTable = ticketTableMap.get(ticketState.getTicketKey());
		
		if (ticketTable == null) {
			throw new Exception("this table is null, it is ticketKey is " + ticketState.getTicketKey());
		}
		
		stateInterface = EnnMonitorAlarmTicketStateFactory.getEnnMonitorAlarmTicketState(ticketTable.getEnnMonitorAlarmTicketStatus());
		if (stateInterface == null) {
			throw new Exception("the error state transform, the ticketTable status is " + ticketTable.getEnnMonitorAlarmTicketStatus().name() + 
					", this transform condition is " + ticketState.getTicketTransformState().name());
		}
		
		ticketTable = stateInterface.transform(ticketTable, ticketState);
		dealEnnMonitorAlarmTicketState(ticketTable);
		
		return ticketTable;
	}
	
	public void delete(EnnMonitorAlarmTicketTable ticketTable) throws Exception {
		ticketTableMap.remove(ticketTable.getTicketKey());
	}
	
	private void dealEnnMonitorAlarmTicketState(EnnMonitorAlarmTicketTable ticketTable) throws Exception {
		ChannelWriteData channelWriteData = null;
		
		switch (ticketTable.getEnnMonitorAlarmTicketStatus()) {
		case WaitingAutoRecover:
			channelWriteData = new ChannelWriteData(ticketTable);
			autoRecoverChannel.write(channelWriteData);
			
			ticketTableMap.put(ticketTable.getTicketKey(), ticketTable);
			break;
		case WaitingNotify:
			channelWriteData = new ChannelWriteData(ticketTable);
			emailChannel.write(channelWriteData);
			
			ticketTableMap.put(ticketTable.getTicketKey(), ticketTable);
			break;
		case WaitingReceive:
		case WaitingResolve:
		case Resolved:
			ticketTableMap.put(ticketTable.getTicketKey(), ticketTable);
			break;
		default:
			logger.error("the unexpected ticket status, it is " + ticketTable.getEnnMonitorAlarmTicketStatus().name());
			return;
		}
		
		ticketIdTicketKeyMap.put(ticketTable.getId(), ticketTable.getTicketKey());
	}

}
