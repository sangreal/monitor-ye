package enn.monitor.framework.pipe.client;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// 启动的时候，假设主从数据库的数据是一致的
public class EnnMonitorFrameworkPipeClientThread implements Runnable {
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorFrameworkPipeClientImplBase implBase = null;
	
	private List<Object> validDataList = new LinkedList<Object>();
	private List<Object> deletedDataList = new LinkedList<Object>();
	
	private long nextValidSeqNo = -1;
	private long nextDeletedSeqNo = -1;
	
	private int batchNum = 1000;
	private long waitMills = 200;
	
	public EnnMonitorFrameworkPipeClientThread(EnnMonitorFrameworkPipeClientImplBase implBase) {
		this.implBase = implBase;
	}
	
	public void setBatchNum(int batchNum) {
		this.batchNum = batchNum;
	}

	public void setWaitMills(long waitMills) {
		this.waitMills = waitMills;
	}

	public void init() throws Exception {
		logger.info("pipe client init start");
		
		nextDeletedSeqNo = implBase.getMaxDeletedSeqNo();
		
		validDataList.clear();
		deletedDataList.clear();
		
		/*while (validDataList.size() <= 0) {
			validDataList.addAll(implBase.getValidDataList(nextValidSeqNo, batchNum));
			
			if (validDataList.size() > 0) {
				nextValidSeqNo = implBase.getValidSeqNo(validDataList.get(validDataList.size() - 1));
			}
			while (validDataList.size() > 0) {
				if (implBase.getValidSeqNo(validDataList.get(0)) > nextDeletedSeqNo) {
					break;
				}
				
				implBase.updateAndInsertData(validDataList.get(0));
				validDataList.remove(0);
			}
			
			if (nextValidSeqNo >= nextDeletedSeqNo) {
				break;
			}
			Thread.sleep(10);
		}*/
		
		logger.info("pipe client init complete");
	}
	
	public void initComplete() { }

	@Override
	public void run() {
		while (true) {
			try {
				init();
				initComplete();
				break;
			} catch (Exception e) {
				try {
					Thread.sleep(10000);
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
				logger.error(e.getMessage(), e);
			}
		}
		
		while (true) {
			try {
				validDataList.addAll(implBase.getValidDataList(nextValidSeqNo, batchNum));
				if (validDataList.size() > 0) {
					nextValidSeqNo = implBase.getValidSeqNo(validDataList.get(validDataList.size() - 1));
				}
				
				deletedDataList.addAll(implBase.getDeletedDataList(nextDeletedSeqNo, batchNum));
				if (deletedDataList.size() > 0) {
					nextDeletedSeqNo = implBase.getDeletedSeqNo(deletedDataList.get(deletedDataList.size() - 1));
				}
				
				while (validDataList.size() > 0) {
					implBase.updateAndInsertData(validDataList.get(0));
					validDataList.remove(0);
				}
				
				while (deletedDataList.size() > 0) {
					if (implBase.getDeletedSeqNo(deletedDataList.get(0)) > nextValidSeqNo) {
						break;
					}
					
					implBase.deleteData(deletedDataList.get(0));
					deletedDataList.remove(0);
				}
				
				Thread.sleep(waitMills);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
}
