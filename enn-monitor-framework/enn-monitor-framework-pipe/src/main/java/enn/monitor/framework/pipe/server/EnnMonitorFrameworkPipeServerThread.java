package enn.monitor.framework.pipe.server;

import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.log.channel.ChannelWriteTask;

public class EnnMonitorFrameworkPipeServerThread extends ChannelWriteTask {
	
	private boolean isDeleteTag = false;
	
	private EnnMonitorFrameworkPipeServerImplBase implBase = null;
	
	public EnnMonitorFrameworkPipeServerThread(EnnMonitorFrameworkPipeServerImplBase implBase) {
		this.implBase = implBase;
	}

	@Override
	protected void operator(ChannelWriteData stockWriteData) throws Exception {
		EnnMonitorFrameworkPipeServerChannelWriteOpType writeOpType = (EnnMonitorFrameworkPipeServerChannelWriteOpType) stockWriteData.getOpEnum();
		
		switch (writeOpType) {
		case Insert:
			insert(stockWriteData.getObject());
			break;
		case Update:
			update(stockWriteData.getObject());
			break;
		case Delete:
			delete(stockWriteData.getObject());
			break;
		}
		
		isDeleteTag = false;
	}
	
	private void insert(Object object) throws Exception {
		long id;
		long seqNo;
		
		id = implBase.incDataId();
		seqNo = implBase.incDataSeqNo();
		
		implBase.insert(id, seqNo, object);
	}
	
	private void update(Object object) throws Exception {
		long id;
		long seqNo;
		
		id = implBase.getId(object);
		seqNo = implBase.incDataSeqNo();
		
		implBase.update(id, seqNo, object);
	}
	
	private void delete(Object object) throws Exception {
		long id;
		long seqNo;
		
		id = implBase.getId(object);
		seqNo = implBase.incDataSeqNo();
		
		implBase.delete(id, seqNo, object);
	}
	
	protected void operatorNull() throws Exception { 
		long id;
		long seqNo;
		
		if (isDeleteTag == true) {
			return;
		}
		
		id = implBase.getTagId();
		seqNo = implBase.incDataSeqNo();
		
		implBase.updateAndInsert(id, seqNo);
		
		isDeleteTag = true;
	}
	
}
