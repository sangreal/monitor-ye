package enn.monitor.alarm.ticket.email.pipe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeleted;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailTable;

public class EnnMonitorAlarmTicketEmailCache {
	
	private static final Logger logger = LogManager.getLogger();
	
	private Map<String, EnnMonitorAlarmConfigEmailTable> emailMap = new ConcurrentHashMap<String, EnnMonitorAlarmConfigEmailTable>();
	private Map<Long, String> emailIdGroupNameMap = new ConcurrentHashMap<Long, String>();
	
	public void updateAndInsert(EnnMonitorAlarmConfigEmailTable emailTable) {
		emailIdGroupNameMap.put(emailTable.getId(), emailTable.getGroupName());
		emailMap.put(emailTable.getGroupName(), emailTable);
	}
	
	public void remove(EnnMonitorAlarmConfigEmailGetDeleted emailTable) {
		emailMap.remove(emailIdGroupNameMap.get(emailTable.getId()));
		emailIdGroupNameMap.remove(emailTable.getId());
	}
	
	public String getEmail(String groupName) throws Exception {
		EnnMonitorAlarmConfigEmailTable emailTable = null;
		
		emailTable = emailMap.get(groupName);
		
		if (emailTable == null) {
			logger.info("the email is null, the groupname is" + groupName);
			return null;
		}
		
		return emailTable.getMail();
	}
	
}
