package enn.monitor.alarm.ticket.tablectl;

/*
uint64 id = 1;
uint64 seqNo = 2;

uint64 createTime = 3;
uint64 lastUpdateTime = 4;
string createUser = 5;
string lastUpdateUser = 6;

EnnMonitorAlarmTicketStatus ennMonitorAlarmTicketStatus = 7;

EnnMonitorAlarmTicketLevel ennMonitorAlarmTicketLevel = 8;

string clusterName = 9;
string ip = 10;
string nameSpace = 11;
string podName = 12;
string appName = 13;

string error = 14;
string errorReason = 15;

string remark = 16;
*/

public class EnnMonitorAlarmTicketTableField {

	public static final String IdFieldName = "id";
	public static final String SeqNoFieldName = "seqNo";
	
	public static final String TicketKeyFieldName = "ticketKey";
	
	public static final String CreateTimeFieldName = "createtime";
	public static final String LastUpdateTimeFieldName = "lastUpdateTime";
	
	public static final String CreateUserFieldName = "createUser";
	public static final String LastUpdateUserFieldName = "lastUpdateUser";
	
	public static final String EnnMonitorAlarmTicketStatusFieldName = "ennMonitorAlarmTicketStatus";
	public static final String EnnMonitorAlarmTicketLevelFieldName = "ennMonitorAlarmTicketLevel";
	
	public static final String AutomationFieldName = "automation";
	
	public static final String GroupNameFieldName = "groupName";

	public static final String ClusterNameFieldName = "clusterName";
	public static final String IPFieldName = "ip";
	public static final String NameSpaceFieldName = "nameSpace";
	public static final String PodNameFieldName = "podName";
	public static final String AppNameFieldName = "appName";
	
	public static final String ErrorFieldName = "error";
	public static final String ErrorReasonFieldName = "errorReason";
	public static final String RemarkFieldName = "remark";
			
}
