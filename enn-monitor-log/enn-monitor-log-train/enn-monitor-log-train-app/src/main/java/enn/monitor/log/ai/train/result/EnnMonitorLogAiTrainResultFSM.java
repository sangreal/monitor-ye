package enn.monitor.log.ai.train.result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.framework.ai.nn.NNFramework;
import enn.monitor.framework.ai.nn.activation.NNActivationEnum;
import enn.monitor.framework.ai.nn.weights.NNWeightEnum;
import enn.monitor.log.ai.common.CommonEventInterface;
import enn.monitor.log.ai.common.CommonFSMInterface;
import enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheRequest;
import enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdRequest;
import enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateSetType;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateTable;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateResponse;
import enn.monitor.log.train.util.CoreContextUtil;
import enn.monitor.log.train.util.EnnMonitorConfigServiceClientUtil;
import enn.monitor.log.train.util.EnnMonitorLogConfigCacheClientUtil;
import enn.monitor.log.train.util.EnnMonitorLogConfigTemplateClientUtil;

public class EnnMonitorLogAiTrainResultFSM extends CommonFSMInterface {
	
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorLogAiTrainResultJPanel resultJPanel = null;

	public EnnMonitorLogAiTrainResultFSM(BlockingQueue<CommonEventInterface> eventQueue) {
		super("TrainResultFSM");
		this.eventQueue = eventQueue;
	}

	@Override
	public void runEvent(Object data) throws Exception {
		long tokenId = -1l;
		
		List<EnnMonitorLogAiTrainResultParameter> parameters = null;
		
		EnnMonitorLogAiTrainResultEventEnum ennMonitorLogAiTrainResultEventEnum = null;
		EnnMonitorLogAiTrainResultParameter parameter = null;
		EnnMonitorLogAiTrainResultEvent event = (EnnMonitorLogAiTrainResultEvent) data;
		
		EnnMonitorLogConfigTemplateUpdateRequest.Builder updateBuilder = null;
		EnnMonitorLogConfigTemplateUpdateResponse updateResponse = null;
		
		parameter = (EnnMonitorLogAiTrainResultParameter) event.getData();
		
		ennMonitorLogAiTrainResultEventEnum = event.getEnnMonitorLogAiTrainResultEventEnum();
		
		if (parameter == null) {
			return;
		}
		
		switch (ennMonitorLogAiTrainResultEventEnum) {
		case Compute:
			if (parameter.getToken() == null || parameter.getToken().equals("") == true) {
				throw new Exception("the token is null");
			}
			
			tokenId = EnnMonitorConfigServiceClientUtil.getTokenId(parameter.getToken());
			parameters = templateAnalyse(tokenId);
			resultJPanel.setEnnMonitorLogAiTrainResultParameters(parameters);
			break;
		case Update:
			if (parameter.getId() <= 0) {
				throw new Exception("in update template, the id is required");
			}
			
			if (parameter.getTagId() <= 0) {
				throw new Exception("in update template, unexpected tagId, it is " + parameter.getTagId());
			}
			
			updateBuilder = EnnMonitorLogConfigTemplateUpdateRequest.newBuilder();
			updateBuilder.setId(parameter.getId());
			
			if (parameter.getBelongToServiceId() > 0) {
				updateBuilder.setBelongToServiceId(parameter.getBelongToServiceId());
			}
			
			updateBuilder.setSetType(EnnMonitorLogConfigTemplateSetType.Auto);
			updateBuilder.setTagId(parameter.getTagId());
			
			updateBuilder.setLastUpdateUser("machine_tableUI");
			
			updateResponse = EnnMonitorLogConfigTemplateClientUtil.getEnnMonitorLogConfigTemplateClient().updateTemplate(updateBuilder.build());
			if (updateResponse.getIsSuccess() == false) {
				throw new Exception(updateResponse.getError());
			}
			break;
		case UpdateAll:
			logger.info("ToDo");
			break;
		case Display:
			resultJPanel.displayEnnMonitorLogConfigTemplateTable(parameter);
			break;
		default:
			logger.error("unexpected dataTemplateEventEnum, it is " + ennMonitorLogAiTrainResultEventEnum.name());
			break;
		}
	}
	
	public void setEnnMonitorLogAiTrainResultJPanel(EnnMonitorLogAiTrainResultJPanel resultJPanel) {
		this.resultJPanel = resultJPanel;
	}
	
	public List<EnnMonitorLogAiTrainResultParameter> templateAnalyse(long tokenId) {
		int i;
		double maxMatch = Double.MIN_VALUE;
		int pos = -1;
		
		Long tagId = null;
		
		List<Double> outputList = null;
		
		String tag = null;
		EnnMonitorLogConfigTemplateTable table = null;
		
		EnnMonitorLogConfigCacheTagIdResponse tagIdResponse = null;
		
		EnnMonitorLogAiTrainResultParameter parameter = null;
		List<EnnMonitorLogAiTrainResultParameter> parameters = new ArrayList<EnnMonitorLogAiTrainResultParameter>();
		
		List<Object> tmpDataList = null;
		
		// 添加预处理的数据集
		
		try {
			tmpDataList = EnnMonitorLogConfigTemplateClientUtil.getEnnMonitorLogConfigTemplateClient().getValidDataList(
					EnnMonitorLogConfigTemplateGetValidDataListRequest.newBuilder().setStartSeqNo(-1l).setBatchNum(1000).setBelongToServiceId(tokenId).build());
			while (tmpDataList != null && tmpDataList.size() > 0) {
				for (Object item : tmpDataList) {
					table = (EnnMonitorLogConfigTemplateTable) item;
					
					if (table.getTemplateKey().indexOf('-') < 0) {
						continue;
					}
					
					if (table.getTagId() > 0) {
						continue;
					}
					
					parameter = new EnnMonitorLogAiTrainResultParameter();
					
					parameter.setId(table.getId());
					parameter.setBelongToServiceId(tokenId);
					parameter.setTemplateKey(table.getTemplateKey());
					
					maxMatch = Double.MIN_VALUE;
					pos = -1;
					outputList = NNFramework.compute(
							CoreContextUtil.getNnObject(), CoreContextUtil.getTrainNNData().formatInputList(parameter.getTemplateKey()), 
							null, NNActivationEnum.Sigmoid, NNWeightEnum.Momentum, 0, 0);
					for (i = 0; i < outputList.size(); ++i) {
						if (maxMatch < outputList.get(i)) {
							maxMatch = outputList.get(i);
							pos = i;
						}
					}
					
					tag = CoreContextUtil.getTrainNNData().getResults(pos);
					if (tag == null) {
						continue;
					}
					
					parameter.setTag(tag);
					tagId = CoreContextUtil.getTagId(tag);
					if (tagId == null) {
						tagIdResponse = EnnMonitorLogConfigCacheClientUtil.getEnnMonitorLogConfigCacheClient().getTagId(EnnMonitorLogConfigCacheTagIdRequest.newBuilder().setTag(tag).build());
						if (tagIdResponse.getIsSuccess() == true) {
							parameter.setTagId(tagIdResponse.getTagId());
							CoreContextUtil.addTag(tag, tagIdResponse.getTagId());
						}
					} else {
						parameter.setTagId(CoreContextUtil.getTagId(tag));
					}
					
					parameter.setMatch(maxMatch);
					
					parameters.add(parameter);
				}
				
				table = (EnnMonitorLogConfigTemplateTable) tmpDataList.get(tmpDataList.size() - 1);
				tmpDataList = EnnMonitorLogConfigTemplateClientUtil.getEnnMonitorLogConfigTemplateClient().getValidDataList(
						EnnMonitorLogConfigTemplateGetValidDataListRequest.newBuilder().setStartSeqNo(table.getSeqNo()).setBatchNum(1000).setBelongToServiceId(tokenId).build());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return parameters;
	}
	
}
