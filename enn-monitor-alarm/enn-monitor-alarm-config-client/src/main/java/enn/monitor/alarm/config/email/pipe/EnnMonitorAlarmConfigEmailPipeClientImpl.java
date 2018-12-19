package enn.monitor.alarm.config.email.pipe;

import java.util.List;

import enn.monitor.alarm.config.email.client.EnnMonitorAlarmConfigEmailClient;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeleted;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailTable;
import enn.monitor.framework.pipe.client.EnnMonitorFrameworkPipeClientImplBase;

public abstract class EnnMonitorAlarmConfigEmailPipeClientImpl extends EnnMonitorFrameworkPipeClientImplBase {
	
	private EnnMonitorAlarmConfigEmailClient emailClient = null;
	
	public EnnMonitorAlarmConfigEmailPipeClientImpl(EnnMonitorAlarmConfigEmailClient emailClient) {
		this.emailClient = emailClient;
	}

	@Override
	public long getMaxDeletedSeqNo() throws Exception {
		return emailClient.getMaxEnnMonitorAlarmConfigEmailDeletedSeqNo();
	}

	@Override
	public long getValidId(Object object) throws Exception {
		EnnMonitorAlarmConfigEmailTable emailTable = (EnnMonitorAlarmConfigEmailTable) object;
		return emailTable.getId();
	}

	@Override
	public long getValidSeqNo(Object object) throws Exception {
		EnnMonitorAlarmConfigEmailTable emailTable = (EnnMonitorAlarmConfigEmailTable) object;
		return emailTable.getSeqNo();
	}
	
	@Override
	public long getDeletedId(Object object) throws Exception {
		EnnMonitorAlarmConfigEmailGetDeleted deletedTable = (EnnMonitorAlarmConfigEmailGetDeleted) object;
		return deletedTable.getId();
	}
	
	@Override
	public long getDeletedSeqNo(Object object) throws Exception {
		EnnMonitorAlarmConfigEmailGetDeleted deletedTable = (EnnMonitorAlarmConfigEmailGetDeleted) object;
		return deletedTable.getSeqNo();
	}

	@Override
	public List<Object> getValidDataList(long startSeqNo, int batch) throws Exception {
		return emailClient.getEnnMonitorAlarmConfigEmailTableList(startSeqNo, batch);
	}

	@Override
	public List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception {
		return emailClient.getEnnMonitorAlarmConfigEmailGetDeletedList(startSeqNo, batch);
	}

}
