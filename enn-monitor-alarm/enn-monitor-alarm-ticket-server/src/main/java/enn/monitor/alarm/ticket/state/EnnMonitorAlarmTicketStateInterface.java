package enn.monitor.alarm.ticket.state;

import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTable;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState;

public interface EnnMonitorAlarmTicketStateInterface {
	
	public EnnMonitorAlarmTicketTable transform(EnnMonitorAlarmTicketTable ticketTable, 
			EnnMonitorAlarmTicketTransformState ticketState) throws Exception;
	
}
