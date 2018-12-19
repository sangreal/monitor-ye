package enn.monitor.alarm.ticket.cache.channel;

import enn.monitor.framework.log.channel.ChannelWriteOpType;

public enum EnnMonitorAlarmTicketCacheOpEnum implements ChannelWriteOpType {
	Insert, Update, Delete, UpdateState;
}
