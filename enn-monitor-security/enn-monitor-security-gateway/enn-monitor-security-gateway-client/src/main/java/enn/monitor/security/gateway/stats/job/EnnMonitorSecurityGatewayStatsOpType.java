package enn.monitor.security.gateway.stats.job;

import enn.monitor.framework.log.channel.ChannelWriteOpType;

public enum EnnMonitorSecurityGatewayStatsOpType implements ChannelWriteOpType {
	Register, Count, Set;
}
