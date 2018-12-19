package enn.monitor.alarm.ticket.cache.channel;

import java.util.List;

import com.mongodb.BasicDBObject;

import enn.monitor.alarm.ticket.autocover.channel.EnnMonitorAlarmTicketAutoRecoverChannel;
import enn.monitor.alarm.ticket.cache.data.EnnMonitorAlarmCache;
import enn.monitor.alarm.ticket.email.channel.EnnMonitorAlarmTicketEmailChannel;
import enn.monitor.alarm.ticket.impl.EnnMonitorAlarmTicketHandler;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketStatus;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTable;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState;
import enn.monitor.alarm.ticket.tablectl.EnnMonitorAlarmTicketTableField;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.log.channel.ChannelWriteTask;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerChannelWriteOpType;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerThread;

public class EnnMonitorAlarmTicketCacheChannel extends ChannelWriteTask {
	
	private EnnMonitorAlarmTicketHandler ticketHandler = null;
	
	private EnnMonitorAlarmCache alarmCache = null;
	
	private EnnMonitorFrameworkPipeServerThread pipeServer = null;
	
	private EnnMonitorAlarmTicketAutoRecoverChannel autoRecoverChannel = null;
	private EnnMonitorAlarmTicketEmailChannel emailChannel = null;
	
	public EnnMonitorAlarmTicketCacheChannel(EnnMonitorAlarmTicketHandler ticketHandler, EnnMonitorFrameworkPipeServerThread pipeServer,
			EnnMonitorAlarmTicketAutoRecoverChannel autoRecoverChannel, EnnMonitorAlarmTicketEmailChannel emailChannel) {
		this.ticketHandler = ticketHandler;
		this.pipeServer = pipeServer;
		this.autoRecoverChannel = autoRecoverChannel;
		this.emailChannel = emailChannel;
		
		alarmCache = new EnnMonitorAlarmCache(this.autoRecoverChannel, this.emailChannel);
	}
	
	protected void init() throws Exception {
		int i;
		
		BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	List<EnnMonitorAlarmTicketTable> ticketTableList = null;
    	
    	query.put(EnnMonitorAlarmTicketTableField.SeqNoFieldName, new BasicDBObject("$gt", 0));
    	query.put(EnnMonitorAlarmTicketTableField.EnnMonitorAlarmTicketStatusFieldName, 
    			new BasicDBObject("$ne", EnnMonitorAlarmTicketStatus.Resolved.name()));
    	
    	order.put(EnnMonitorAlarmTicketTableField.SeqNoFieldName, 1);
    	
		ticketTableList = ticketHandler.getEnnMonitorAlarmTicketTableCtl().getMongoDBCtrl().searchData(query, order, 0, 100);
		while (ticketTableList.size() > 0) {
			for (i = 0; i < ticketTableList.size(); ++i) {
				if (ticketTableList.get(i).getId() == -1) {
					continue;
				}
				alarmCache.pipeUpdateAndInsert(ticketTableList.get(i));
			}
			
			query.clear();
			query.put(EnnMonitorAlarmTicketTableField.SeqNoFieldName, 
					new BasicDBObject("$gt", ticketTableList.get(ticketTableList.size() - 1).getSeqNo()));
			
			ticketTableList = ticketHandler.getEnnMonitorAlarmTicketTableCtl().getMongoDBCtrl().searchData(query, order, 0, 100);
		}
	}

	@Override
	protected void operator(ChannelWriteData stockWriteData) throws Exception {
		EnnMonitorAlarmTicketCacheOpEnum cacheOpEnum = (EnnMonitorAlarmTicketCacheOpEnum) stockWriteData.getOpEnum();
		
		switch (cacheOpEnum) {
		case Insert:
			insert((EnnMonitorAlarmTicketTable) stockWriteData.getObject());
			break;
		case Update:
			update((EnnMonitorAlarmTicketTable) stockWriteData.getObject());
			break;
		case Delete:
			delete((EnnMonitorAlarmTicketTable) stockWriteData.getObject());
			break;
		case UpdateState:
			updateState((EnnMonitorAlarmTicketTransformState) stockWriteData.getObject());
			break;
		default:
			break;
		}
	}
	
	private void insert(EnnMonitorAlarmTicketTable ticketTable) throws Exception {
		ChannelWriteData pipeWriteData = null;
		
		ticketTable = alarmCache.add(ticketTable);
		if (ticketTable == null) {
			return;
		}
		
		pipeWriteData = new ChannelWriteData(EnnMonitorFrameworkPipeServerChannelWriteOpType.Insert, ticketTable);
		pipeServer.write(pipeWriteData);
	}
	
	private void update(EnnMonitorAlarmTicketTable ticketTable) throws Exception {
		ChannelWriteData channelWriteData = null;
		
		alarmCache.update(ticketTable);
		
		channelWriteData = new ChannelWriteData(EnnMonitorFrameworkPipeServerChannelWriteOpType.Update, ticketTable);
		pipeServer.write(channelWriteData);
	}
	
	private void delete(EnnMonitorAlarmTicketTable ticketTable) throws Exception {
		ChannelWriteData channelWriteData = new ChannelWriteData(EnnMonitorFrameworkPipeServerChannelWriteOpType.Delete, ticketTable);
		
		alarmCache.delete(ticketTable);
		
		pipeServer.write(channelWriteData);
	}
	
	private void updateState(EnnMonitorAlarmTicketTransformState ticketState) throws Exception {
		EnnMonitorAlarmTicketTable ticketTable = null;
		ChannelWriteData channelWriteData = null;
		
		ticketTable = alarmCache.updateState(ticketState);
		if (ticketTable == null) {
			return;
		}
		
		channelWriteData = new ChannelWriteData(EnnMonitorFrameworkPipeServerChannelWriteOpType.Update, ticketTable);
		pipeServer.write(channelWriteData);
	}

}
