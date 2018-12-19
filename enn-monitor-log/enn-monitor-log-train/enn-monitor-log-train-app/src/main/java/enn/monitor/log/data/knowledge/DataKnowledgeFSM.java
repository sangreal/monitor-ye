package enn.monitor.log.data.knowledge;

import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.log.ai.common.CommonEventInterface;
import enn.monitor.log.ai.common.CommonFSMInterface;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateRequest;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateResponse;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteResponse;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetRequest;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetResponse;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateRequest;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateResponse;
import enn.monitor.log.train.util.EnnMonitorLogConfigAnalyseTermClientUtil;

public class DataKnowledgeFSM extends CommonFSMInterface {
	
	private static final Logger logger = LogManager.getLogger();
	
	private DataKnowledgeJPanel dataKnowledgeJPanel = null;

	public DataKnowledgeFSM(BlockingQueue<CommonEventInterface> eventQueue) {
		super("DataKnowledge");
		this.eventQueue = eventQueue;
	}

	@Override
	public void runEvent(Object data) throws Exception {
		DataKnowledgeEventEnum dataKnowledgeEventEnum = null;
		DataKnowledgeParameter dataKnowledgeEventParameter = null;
		DataKnowledgeEvent dataKnowledgeEvent = (DataKnowledgeEvent) data;
		
		dataKnowledgeEventParameter = (DataKnowledgeParameter) dataKnowledgeEvent.getData();
		
		EnnMonitorLogConfigAnalyseTermGetRequest.Builder getBuilder = null;
		EnnMonitorLogConfigAnalyseTermGetResponse getResponse = null;
		
		EnnMonitorLogConfigAnalyseTermCreateRequest.Builder createRequest = null;
		EnnMonitorLogConfigAnalyseTermCreateResponse createResponse = null;
		
		EnnMonitorLogConfigAnalyseTermUpdateRequest.Builder updateBuilder = null;
		EnnMonitorLogConfigAnalyseTermUpdateResponse updateResponse = null;
		
		EnnMonitorLogConfigAnalyseTermDeleteResponse deleteResponse = null;
		
		dataKnowledgeEventEnum = dataKnowledgeEvent.getDataKnowledgeEventEnum();
		
		if (dataKnowledgeEventParameter == null) {
			return;
		}
		
		switch (dataKnowledgeEventEnum) {
		case Search:
			getBuilder = EnnMonitorLogConfigAnalyseTermGetRequest.newBuilder();
			if (dataKnowledgeEventParameter.getId() > 0) {
				getBuilder.setId(dataKnowledgeEventParameter.getId());
			}
			getBuilder.setBelongToServiceId(dataKnowledgeEventParameter.getBelongToServiceId());
			getResponse = EnnMonitorLogConfigAnalyseTermClientUtil.getEnnMonitorLogConfigAnalyseTermClient().getAnalyseTerm(getBuilder.build());
			if (getResponse.getIsSuccess() == false) {
				throw new Exception(getResponse.getError());
			} else {
				dataKnowledgeJPanel.setEnnMonitorLogConfigAnalyseTermTables(getResponse.getAnalyseTermTableList());
			}
			break;
		case Insert:
			if (dataKnowledgeEventParameter.getBelongToServiceId() <= 0 && dataKnowledgeEventParameter.getBelongToServiceId() != -1) {
				throw new Exception("in insert knowledge, the belongToServiceId is required");
			}
			
			createRequest = EnnMonitorLogConfigAnalyseTermCreateRequest.newBuilder();
			
			createRequest.setBelongToServiceId(dataKnowledgeEventParameter.getBelongToServiceId());
			if (dataKnowledgeEventParameter.getKey() != null && dataKnowledgeEventParameter.getKey().equals("") == false) {
				createRequest.setAddTerm(dataKnowledgeEventParameter.getKey());
			}
			if (dataKnowledgeEventParameter.getFilter() != null && dataKnowledgeEventParameter.getFilter().equals("") == false) {
				createRequest.setFilterTerm(dataKnowledgeEventParameter.getFilter());
			}
			createRequest.setCreateUser("micklongen_tableUI");
			
			if ((createRequest.getAddTerm() == null || createRequest.getAddTerm().equals("") == true) && 
					(createRequest.getFilterTerm() == null || createRequest.getFilterTerm().equals("") == true)) {
				logger.error("nothing is set, it is " + createRequest.toString());
				break;
			}
			
			createResponse = EnnMonitorLogConfigAnalyseTermClientUtil.getEnnMonitorLogConfigAnalyseTermClient().createAnalyseTerm(createRequest.build());
			
			if (createResponse.getIsSuccess() == false) {
				throw new Exception(createResponse.getError());
			}
			refresh(dataKnowledgeEventParameter);
			break;
		case Update:
			if (dataKnowledgeEventParameter.getId() <= 0) {
				throw new Exception("in update knowledge, the id is required");
			}
			
			if (dataKnowledgeEventParameter.getBelongToServiceId() <= 0 && dataKnowledgeEventParameter.getBelongToServiceId() != -1) {
				throw new Exception("in insert knowledge, the belongToServiceId is required");
			}
			
			updateBuilder = EnnMonitorLogConfigAnalyseTermUpdateRequest.newBuilder();
			updateBuilder.setId(dataKnowledgeEventParameter.getId());
			updateBuilder.setBelongToServiceId(dataKnowledgeEventParameter.getBelongToServiceId());
			
			if (dataKnowledgeEventParameter.getKey() != null && dataKnowledgeEventParameter.getKey().equals("") == false) {
				updateBuilder.setAddTerm(dataKnowledgeEventParameter.getKey());
			}
			
			if (dataKnowledgeEventParameter.getFilter() != null && dataKnowledgeEventParameter.getFilter().equals("") == false) {
				updateBuilder.setFilterTerm(dataKnowledgeEventParameter.getFilter());
			}
			
			updateBuilder.setLastUpdateUser("micklongen_tableUI");
			
			if ((updateBuilder.getAddTerm() == null || updateBuilder.getAddTerm().equals("") == true) && 
					(updateBuilder.getFilterTerm() == null || updateBuilder.getFilterTerm().equals("") == true)) {
				logger.error("nothing is set, it is " + updateBuilder.toString());
				break;
			}
			
			updateResponse = EnnMonitorLogConfigAnalyseTermClientUtil.getEnnMonitorLogConfigAnalyseTermClient().updateAnalyseTerm(updateBuilder.build());
			if (updateResponse.getIsSuccess() == false) {
				throw new Exception(updateResponse.getError());
			}
			refresh(dataKnowledgeEventParameter);
			break;
		case Delete:
			if (dataKnowledgeEventParameter.getId() <= 0) {
				throw new Exception("in update tag, the id is required");
			}
			deleteResponse = EnnMonitorLogConfigAnalyseTermClientUtil.getEnnMonitorLogConfigAnalyseTermClient().deleteAnalyseTerm(dataKnowledgeEventParameter.getId());
			if (deleteResponse.getIsSuccess() == false) {
				throw new Exception(deleteResponse.getError());
			}
			refresh(dataKnowledgeEventParameter);
			break;
		case Display:
			dataKnowledgeJPanel.displayEnnMonitorLogConfigAnalyseTermTable(dataKnowledgeEventParameter);
			break;
		default:
			logger.error("unexpected dataKnowledgeEventEnum, it is " + dataKnowledgeEventEnum.name());
			break;
		}
	}
	
	public void refresh(DataKnowledgeParameter dataKnowledgeEventParameter) throws Exception {
		addEvent(new DataKnowledgeEvent(DataKnowledgeEventEnum.Search, dataKnowledgeEventParameter));
	}
	
	public void setDataKnowledgeJPanel(DataKnowledgeJPanel dataKnowledgeJPanel) {
		this.dataKnowledgeJPanel = dataKnowledgeJPanel;
	}

}
