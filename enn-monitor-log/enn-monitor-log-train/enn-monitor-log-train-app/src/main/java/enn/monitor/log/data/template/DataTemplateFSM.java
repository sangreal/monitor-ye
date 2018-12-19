package enn.monitor.log.data.template;

import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.log.ai.common.CommonEventInterface;
import enn.monitor.log.ai.common.CommonFSMInterface;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateSetType;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateResponse;
import enn.monitor.log.train.util.EnnMonitorLogConfigTemplateClientUtil;

public class DataTemplateFSM extends CommonFSMInterface {
	
	private static final Logger logger = LogManager.getLogger();
	
	private DataTemplateJPanel dataTemplateJPanel = null;

	public DataTemplateFSM(BlockingQueue<CommonEventInterface> eventQueue) {
		super("DataLabel");
		this.eventQueue = eventQueue;
	}

	@Override
	public void runEvent(Object data) throws Exception {
		DataTemplateEventEnum dataTemplateEventEnum = null;
		DataTemplateParameter dataTemplateEventParameter = null;
		DataTemplateEvent dataTemplateEvent = (DataTemplateEvent) data;
		
		dataTemplateEventParameter = (DataTemplateParameter) dataTemplateEvent.getData();
		
		EnnMonitorLogConfigTemplateGetRequest.Builder getBuilder = null;
		EnnMonitorLogConfigTemplateGetResponse getResponse = null;
		
		EnnMonitorLogConfigTemplateCreateRequest.Builder createRequest = null;
		EnnMonitorLogConfigTemplateCreateResponse createResponse = null;
		
		EnnMonitorLogConfigTemplateUpdateRequest.Builder updateBuilder = null;
		EnnMonitorLogConfigTemplateUpdateResponse updateResponse = null;
		
		EnnMonitorLogConfigTemplateDeleteResponse deleteResponse = null;
		
		dataTemplateEventEnum = dataTemplateEvent.getDataTemplateEventEnum();
		
		if (dataTemplateEventParameter == null) {
			return;
		}
		
		switch (dataTemplateEventEnum) {
		case Search:
			getBuilder = EnnMonitorLogConfigTemplateGetRequest.newBuilder();
			if (dataTemplateEventParameter.getId() > 0) {
				getBuilder.setId(dataTemplateEventParameter.getId());
			}
			if (dataTemplateEventParameter.getBelongToServiceId() > 0) {
				getBuilder.setBelongToServiceId(dataTemplateEventParameter.getBelongToServiceId());
			}
			
			if (dataTemplateEventParameter.getTagId() > 0) {
				getBuilder.setTagId(dataTemplateEventParameter.getTagId());
			}
			
			if (dataTemplateEventParameter.isRoot() == true) {
				getBuilder.setIsRoot(true);
			} else {
				getBuilder.setIsRoot(false);
				if (dataTemplateEventParameter.getBelongToParentTemplate() != null && dataTemplateEventParameter.getBelongToParentTemplate().equals("") == false) {
					getBuilder.setBelongToParentTemplate(dataTemplateEventParameter.getBelongToParentTemplate());
				}
			}
			
			getResponse = EnnMonitorLogConfigTemplateClientUtil.getEnnMonitorLogConfigTemplateClient().getTemplate(getBuilder.build());
			if (getResponse.getIsSuccess() == false) {
				throw new Exception(getResponse.getError());
			} else {
				dataTemplateJPanel.setEnnMonitorLogConfigTemplateTables(getResponse.getTemplateTableList());
			}
			break;
		case Insert:
			if (dataTemplateEventParameter.getBelongToServiceId() <= 0) {
				throw new Exception("in insert template, the belongToServiceId is required");
			}
			
			if (dataTemplateEventParameter.getTemplateKey() == null || dataTemplateEventParameter.getTemplateKey().equals("") == true) {
				throw new Exception("the templateKey is null");
			}
			
			createRequest = EnnMonitorLogConfigTemplateCreateRequest.newBuilder();
			
			createRequest.setBelongToServiceId(dataTemplateEventParameter.getBelongToServiceId());
			if (dataTemplateEventParameter.getTagId() > 0) {
				createRequest.setTagId(dataTemplateEventParameter.getTagId());
			}
			
			createRequest.setSetType(EnnMonitorLogConfigTemplateSetType.None);
			
			createRequest.setTemplateKey(dataTemplateEventParameter.getTemplateKey());
			if (dataTemplateEventParameter.getBelongToParentTemplate() != null && dataTemplateEventParameter.getBelongToParentTemplate().equals("") == false) {
				createRequest.setBelongToParentTemplate(dataTemplateEventParameter.getBelongToParentTemplate());
			}
			
			createRequest.setCreateUser("micklongen_tableUI");
			
			createResponse = EnnMonitorLogConfigTemplateClientUtil.getEnnMonitorLogConfigTemplateClient().createTemplate(createRequest.build());
			
			if (createResponse.getIsSuccess() == false) {
				throw new Exception(createResponse.getError());
			}
			refresh(dataTemplateEventParameter);
			break;
		case Update:
			if (dataTemplateEventParameter.getId() <= 0) {
				throw new Exception("in update template, the id is required");
			}
			
			updateBuilder = EnnMonitorLogConfigTemplateUpdateRequest.newBuilder();
			updateBuilder.setId(dataTemplateEventParameter.getId());
			
			if (dataTemplateEventParameter.getBelongToServiceId() > 0) {
				updateBuilder.setBelongToServiceId(dataTemplateEventParameter.getBelongToServiceId());
			}
			
			updateBuilder.setSetType(EnnMonitorLogConfigTemplateSetType.Manual);
			
			updateBuilder.setTagId(dataTemplateEventParameter.getTagId());
			
			if (dataTemplateEventParameter.getTemplateKey() != null && dataTemplateEventParameter.getTemplateKey().equals("") == false) {
				updateBuilder.setTemplateKey(dataTemplateEventParameter.getTemplateKey());
			}
			
			if (dataTemplateEventParameter.getBelongToParentTemplate() != null && dataTemplateEventParameter.getBelongToParentTemplate().equals("") == false) {
				updateBuilder.setBelongToParentTemplate(dataTemplateEventParameter.getBelongToParentTemplate());
			}
			
			updateBuilder.setLastUpdateUser("micklongen_tableUI");
			
			updateResponse = EnnMonitorLogConfigTemplateClientUtil.getEnnMonitorLogConfigTemplateClient().updateTemplate(updateBuilder.build());
			if (updateResponse.getIsSuccess() == false) {
				throw new Exception(updateResponse.getError());
			}
			refresh(dataTemplateEventParameter);
			break;
		case Delete:
			if (dataTemplateEventParameter.getId() <= 0) {
				throw new Exception("in update tag, the id is required");
			}
			deleteResponse = EnnMonitorLogConfigTemplateClientUtil.getEnnMonitorLogConfigTemplateClient().deleteTemplate(dataTemplateEventParameter.getId());
			if (deleteResponse.getIsSuccess() == false) {
				throw new Exception(deleteResponse.getError());
			}
			refresh(dataTemplateEventParameter);
			break;
		case Display:
			dataTemplateJPanel.displayEnnMonitorLogConfigTemplateTable(dataTemplateEventParameter);
			break;
		default:
			logger.error("unexpected dataTemplateEventEnum, it is " + dataTemplateEventEnum.name());
			break;
		}
	}
	
	public void refresh(DataTemplateParameter dataTemplateParameter) throws Exception {
		addEvent(new DataTemplateEvent(DataTemplateEventEnum.Search, dataTemplateParameter));
	}
	
	public void setDataTemplateJPanel(DataTemplateJPanel dataTemplateJPanel) {
		this.dataTemplateJPanel = dataTemplateJPanel;
	}

}
