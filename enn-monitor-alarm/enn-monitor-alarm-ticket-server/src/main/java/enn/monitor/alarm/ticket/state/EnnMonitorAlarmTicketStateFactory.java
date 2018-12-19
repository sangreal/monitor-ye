package enn.monitor.alarm.ticket.state;

import java.util.HashMap;
import java.util.Map;

import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketStatus;

public class EnnMonitorAlarmTicketStateFactory {
	
	private static Map<EnnMonitorAlarmTicketStatus, EnnMonitorAlarmTicketStateInterface> ticketStateMap = new HashMap<EnnMonitorAlarmTicketStatus, EnnMonitorAlarmTicketStateInterface>();
	static {
		ticketStateMap.put(EnnMonitorAlarmTicketStatus.WaitingAutoRecover, new EnnMonitorAlarmTicketStateWaitingAutoRecover());
		ticketStateMap.put(EnnMonitorAlarmTicketStatus.WaitingNotify, new EnnMonitorAlarmTicketStateWaitingNotify());
		ticketStateMap.put(EnnMonitorAlarmTicketStatus.WaitingReceive, new EnnMonitorAlarmTicketStateWaitingReceive());
		ticketStateMap.put(EnnMonitorAlarmTicketStatus.WaitingResolve, new EnnMonitorAlarmTicketStateWaitingResolve());
	}
	
	public static EnnMonitorAlarmTicketStateInterface getEnnMonitorAlarmTicketState(EnnMonitorAlarmTicketStatus ticketStatus) {
		return ticketStateMap.get(ticketStatus);
	}
}
