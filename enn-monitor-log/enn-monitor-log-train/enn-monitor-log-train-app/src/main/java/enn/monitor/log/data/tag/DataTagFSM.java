package enn.monitor.log.data.tag;

import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.log.ai.common.CommonEventInterface;
import enn.monitor.log.ai.common.CommonFSMInterface;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateResponse;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteResponse;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetResponse;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateResponse;
import enn.monitor.log.train.util.EnnMonitorLogConfigEventClientUtil;

public class DataTagFSM extends CommonFSMInterface {
	
	private static final Logger logger = LogManager.getLogger();
	
	private DataTagJPanel dataTagJPanel = null;

	public DataTagFSM(BlockingQueue<CommonEventInterface> eventQueue) {
		super("DataTag");
		this.eventQueue = eventQueue;
	}

	@Override
	public void runEvent(Object data) throws Exception {
		DataTagEventEnum dataTagEventEnum = null;
		DataTagParameter dataTagEventParameter = null;
		DataTagEvent dataTagEvent = (DataTagEvent) data;
		
		dataTagEventParameter = (DataTagParameter) dataTagEvent.getData();
		
		EnnMonitorLogConfigEventGetRequest.Builder getBuilder = null;
		EnnMonitorLogConfigEventGetResponse getResponse = null;
		
		EnnMonitorLogConfigEventCreateRequest.Builder createRequest = null;
		EnnMonitorLogConfigEventCreateResponse createResponse = null;
		
		EnnMonitorLogConfigEventUpdateRequest.Builder updateBuilder = null;
		EnnMonitorLogConfigEventUpdateResponse updateResponse = null;
		
		EnnMonitorLogConfigEventDeleteResponse deleteResponse = null;
		
		dataTagEventEnum = dataTagEvent.getDataTagEventEnum();
		
		if (dataTagEventParameter == null) {
			return;
		}
		
		switch (dataTagEventEnum) {
		case Search:
			getBuilder = EnnMonitorLogConfigEventGetRequest.newBuilder();
			if (dataTagEventParameter.getId() > 0) {
				getBuilder.setId(dataTagEventParameter.getId());
			}
			if (dataTagEventParameter.getBelongToServiceId() > 0 || dataTagEventParameter.getBelongToServiceId() == -1) {
				getBuilder.setBelongToServiceId(dataTagEventParameter.getBelongToServiceId());
			}
			getResponse = EnnMonitorLogConfigEventClientUtil.getEnnMonitorLogConfigEventClient().getEvent(getBuilder.build());
			if (getResponse.getIsSuccess() == false) {
				throw new Exception(getResponse.getError());
			} else {
				dataTagJPanel.setEnnMonitorLogConfigEventTables(getResponse.getEventTableList());
			}
			break;
		case Insert:
			if (dataTagEventParameter.getBelongToServiceId() <= 0 && dataTagEventParameter.getBelongToServiceId() != -1) {
				throw new Exception("in insert knowledge, the belongToId is required");
			}
			
			createRequest = EnnMonitorLogConfigEventCreateRequest.newBuilder();
			
			createRequest.setBelongToServiceId(dataTagEventParameter.getBelongToServiceId());
			if (dataTagEventParameter.getTag() != null && dataTagEventParameter.getTag().equals("") == false) {
				createRequest.setEventName(dataTagEventParameter.getTag());
			}
			createRequest.setCreateUser("micklongen_tableUI");
			
			if ((createRequest.getEventName() == null || createRequest.getEventName().equals("") == true)) {
				logger.error("nothing is set, it is " + createRequest.toString());
				break;
			}
			
			createResponse = EnnMonitorLogConfigEventClientUtil.getEnnMonitorLogConfigEventClient().createEvent(createRequest.build());
			
			if (createResponse.getIsSuccess() == false) {
				throw new Exception(createResponse.getError());
			}
			refresh(dataTagEventParameter);
			break;
		case Update:
			if (dataTagEventParameter.getId() <= 0) {
				throw new Exception("in update knowledge, the id is required");
			}
			
			updateBuilder = EnnMonitorLogConfigEventUpdateRequest.newBuilder();
			updateBuilder.setId(dataTagEventParameter.getId());
			if (dataTagEventParameter.getBelongToServiceId() > 0 || dataTagEventParameter.getBelongToServiceId() == -1) {
				updateBuilder.setBelongToServiceId(dataTagEventParameter.getBelongToServiceId());
			}
			
			if (dataTagEventParameter.getTag() != null && dataTagEventParameter.getTag().equals("") == false) {
				updateBuilder.setEventName(dataTagEventParameter.getTag());
			}
			
			updateBuilder.setLastUpdateUser("micklongen_tableUI");
			
			if ((updateBuilder.getEventName() == null || updateBuilder.getEventName().equals("") == true)) {
				logger.error("nothing is set, it is " + updateBuilder.toString());
				break;
			}
			
			updateResponse = EnnMonitorLogConfigEventClientUtil.getEnnMonitorLogConfigEventClient().updateEvent(updateBuilder.build());
			if (updateResponse.getIsSuccess() == false) {
				throw new Exception(updateResponse.getError());
			}
			refresh(dataTagEventParameter);
			break;
		case Delete:
			if (dataTagEventParameter.getId() <= 0) {
				throw new Exception("in update tag, the id is required");
			}
			deleteResponse = EnnMonitorLogConfigEventClientUtil.getEnnMonitorLogConfigEventClient().deleteEvent(dataTagEventParameter.getId());
			if (deleteResponse.getIsSuccess() == false) {
				throw new Exception(deleteResponse.getError());
			}
			refresh(dataTagEventParameter);
			break;
		case Display:
			dataTagJPanel.displayEnnMonitorLogConfigEventTable(dataTagEventParameter);
			break;
		default:
			logger.error("unexpected dataTagEventEnum, it is " + dataTagEventEnum.name());
			break;
		}
	}
	
	public void refresh(DataTagParameter dataTagParameter) throws Exception {
		addEvent(new DataTagEvent(DataTagEventEnum.Search, dataTagParameter));
	}
	
	public void setDataTagJPanel(DataTagJPanel dataTagJPanel) {
		this.dataTagJPanel = dataTagJPanel;
	}

}
