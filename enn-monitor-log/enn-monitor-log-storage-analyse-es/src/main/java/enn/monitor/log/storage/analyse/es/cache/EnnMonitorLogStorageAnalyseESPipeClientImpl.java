package enn.monitor.log.storage.analyse.es.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import enn.monitor.log.analyse.data.EnnMonitorLogAnalyseData;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataRequest;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataResponse;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageTable;
import enn.monitor.log.analyse.storge.client.EnnMonitorLogAnalyseStorageClient;

public class EnnMonitorLogStorageAnalyseESPipeClientImpl implements Runnable {
	
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorLogAnalyseStorageClient analyseStorageClient = null;
	
	private long startSeqNo = -1l;
	
	private Gson gson = new Gson();
	
	public EnnMonitorLogStorageAnalyseESPipeClientImpl(EnnMonitorLogAnalyseStorageClient analyseStorageClient) {
		this.analyseStorageClient = analyseStorageClient;
	}
	
	@Override
	public void run() {
		int i;
		
		EnnMonitorLogAnalyseStorageLastestNNDataResponse response = null;
		
		EnnMonitorLogAnalyseStorageTable table = null;
		
		EnnMonitorLogAnalyseData analyseData = null;
		
		while (true) {
			try {
				response = analyseStorageClient.getLastestNNData(EnnMonitorLogAnalyseStorageLastestNNDataRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(1).build());
				if (response.getIsSuccess() == true) {
					if (response.getAnalyseStorageTableList() != null) {
						for (i = 0; i < response.getAnalyseStorageTableList().size(); ++i) {
							table = response.getAnalyseStorageTableList().get(i);
							
							if (table.getNndata() == null || table.getNndata().equals("") == true) {
								startSeqNo = table.getSeqNo();
								continue;
							}
							analyseData = gson.fromJson(table.getNndata(), EnnMonitorLogAnalyseData.class);
							EnnMonitorLogStorageAnalyseESPipeCache.add(table.getTokenId(), analyseData);
							
							startSeqNo = table.getSeqNo();
						}
					}
				} else {
					logger.error(response.getError());
				}
				
				Thread.sleep(5000);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

}
