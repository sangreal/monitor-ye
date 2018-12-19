package enn.monitor.alarm.ticket.email.pipe;

import enn.monitor.alarm.config.email.client.EnnMonitorAlarmConfigEmailClient;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeleted;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailTable;
import enn.monitor.alarm.config.email.pipe.EnnMonitorAlarmConfigEmailPipeClientImpl;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeleted;

public class EnnMonitorAlarmTicketEmailPipeClientImpl extends EnnMonitorAlarmConfigEmailPipeClientImpl {
	
	private EnnMonitorAlarmTicketEmailCache ennMonitorAlarmTicketEmailCache = null;

	public EnnMonitorAlarmTicketEmailPipeClientImpl(EnnMonitorAlarmConfigEmailClient emailClient,
			EnnMonitorAlarmTicketEmailCache ennMonitorAlarmTicketEmailCache) {
		super(emailClient);
		this.ennMonitorAlarmTicketEmailCache = ennMonitorAlarmTicketEmailCache;
	}

	@Override
	protected void updateAndInsert(Object object) {
		ennMonitorAlarmTicketEmailCache.updateAndInsert((EnnMonitorAlarmConfigEmailTable) object);
	}

	@Override
	protected void delete(Object object) {
		ennMonitorAlarmTicketEmailCache.remove((EnnMonitorAlarmConfigEmailGetDeleted) object);
	}

}
