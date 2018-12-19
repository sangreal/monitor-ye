package enn.monitor.alarm.ticket.email.channel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.alarm.ticket.cache.channel.EnnMonitorAlarmTicketCacheChannel;
import enn.monitor.alarm.ticket.cache.channel.EnnMonitorAlarmTicketCacheOpEnum;
import enn.monitor.alarm.ticket.email.pipe.EnnMonitorAlarmTicketEmailCache;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTable;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformStateEnum;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.log.channel.ChannelWriteTask;

public class EnnMonitorAlarmTicketEmailChannel extends ChannelWriteTask {
	
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorAlarmTicketCacheChannel ticketCacheChannel = null;
	
	private EnnMonitorAlarmTicketEmailCache emailCache = null;
	
	public EnnMonitorAlarmTicketEmailChannel(EnnMonitorAlarmTicketEmailCache emailCache) {
		this.emailCache = emailCache;
	}
	
	public void setEnnMonitorAlarmTicketCacheChannel(EnnMonitorAlarmTicketCacheChannel ticketCacheChannel) {
		this.ticketCacheChannel = ticketCacheChannel;
	}

	@Override
	protected void operator(ChannelWriteData stockWriteData) throws Exception {
		String email = null;
		
		ChannelWriteData cacheData = null;
		EnnMonitorAlarmTicketTransformState.Builder transformState = null;
		
		EnnMonitorAlarmTicketTable ticketTable = (EnnMonitorAlarmTicketTable) stockWriteData.getObject();
		logger.info("Email " + ticketTable.getTicketKey());
		
		email = emailCache.getEmail(ticketTable.getGrourName());
		if (email == null) {
			logger.error("the email is null, the groupName is " + ticketTable.getGrourName());
		} else {
			// todo: send mail
		}
		
		transformState = EnnMonitorAlarmTicketTransformState.newBuilder();
		transformState.setTicketKey(ticketTable.getTicketKey());
		transformState.setTicketTransformState(EnnMonitorAlarmTicketTransformStateEnum.Notified);
		cacheData = new ChannelWriteData(EnnMonitorAlarmTicketCacheOpEnum.UpdateState, transformState.build());
		ticketCacheChannel.write(cacheData);
	}

}
