package enn.monitor.alarm.ticket.autocover.channel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.alarm.ticket.cache.channel.EnnMonitorAlarmTicketCacheChannel;
import enn.monitor.alarm.ticket.cache.channel.EnnMonitorAlarmTicketCacheOpEnum;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTable;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformStateEnum;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.log.channel.ChannelWriteTask;

public class EnnMonitorAlarmTicketAutoRecoverChannel extends ChannelWriteTask {
	
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorAlarmTicketCacheChannel ticketCacheChannel = null;
	
	public void setEnnMonitorAlarmTicketCacheChannel(EnnMonitorAlarmTicketCacheChannel ticketCacheChannel) {
		this.ticketCacheChannel = ticketCacheChannel;
	}

	@Override
	protected void operator(ChannelWriteData stockWriteData) throws Exception {
		ChannelWriteData cacheData = null;
		EnnMonitorAlarmTicketTransformState.Builder transformState = null;
		
		EnnMonitorAlarmTicketTable ticketTable = (EnnMonitorAlarmTicketTable) stockWriteData.getObject();
		logger.info("AutoRecover " + ticketTable.getTicketKey());
		
		transformState = EnnMonitorAlarmTicketTransformState.newBuilder();
		transformState.setTicketKey(ticketTable.getTicketKey());
		transformState.setTicketTransformState(EnnMonitorAlarmTicketTransformStateEnum.AutoRecoverFailed);
		cacheData = new ChannelWriteData(EnnMonitorAlarmTicketCacheOpEnum.UpdateState, transformState.build());
		ticketCacheChannel.write(cacheData);
	}

}
