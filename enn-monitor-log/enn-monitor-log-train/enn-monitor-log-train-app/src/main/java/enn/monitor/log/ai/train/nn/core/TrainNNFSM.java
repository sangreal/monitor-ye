package enn.monitor.log.ai.train.nn.core;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.framework.ai.nn.NNObject;
import enn.monitor.log.ai.common.CommonEventInterface;
import enn.monitor.log.ai.common.CommonFSMInterface;
import enn.monitor.log.ai.train.nn.panel.TrainNNResultJPanel;
import enn.monitor.log.ai.train.nn.parameter.TrainNNParameter;
import enn.monitor.log.ai.train.nn.parameter.TrainNNParameter.NNItem;
import enn.monitor.log.ai.train.nn.parameter.TrainNNResultParameter;
import enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheRequest;
import enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateSetType;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateTable;
import enn.monitor.log.train.common.nn.data.TrainNNData;
import enn.monitor.log.train.util.CoreContextUtil;
import enn.monitor.log.train.util.EnnMonitorLogConfigCacheClientUtil;
import enn.monitor.log.train.util.EnnMonitorLogConfigTemplateClientUtil;

public class TrainNNFSM extends CommonFSMInterface {
	
	private static final Logger logger = LogManager.getLogger();
	
	private TrainNNModeEnum currentModeEnum = TrainNNModeEnum.Ready;
	
	private NNObject nnObject = null;
	
	private TrainNNResultJPanel trainNNResultJPanel = null;
	
	private TrainNNData trainNNData = new TrainNNData();
	private TrainNNCore nnCore = null;
	
	public TrainNNFSM(BlockingQueue<CommonEventInterface> eventQueue) {
		super("TrainNN");
		this.eventQueue = eventQueue;
		nnCore = new TrainNNCore(this);
	}
	
	@Override
	public void runEvent(Object data) throws Exception {
		TrainNNEventEnum trainNNEventEnum = null;
		TrainNNEvent trainNNEvent = (TrainNNEvent) data;
		
		TrainNNParameter trainNNParameter = null;
		TrainNNResultParameter trainNNResult = null;
		
		trainNNEventEnum = trainNNEvent.getEventEnum();
		switch (currentModeEnum) {
		case Ready:
			switch (trainNNEventEnum) {
			case Train:
				currentModeEnum = TrainNNModeEnum.Training;
				trainNNResult = new TrainNNResultParameter();
				trainNNResult.setNnModeEnum(currentModeEnum);
				trainNNResultJPanel.setTrainNNResult(trainNNResult, false);
				trainNNParameter = (TrainNNParameter) trainNNEvent.getData();
				train(trainNNParameter);
				break;
			default:
				logger.error("the current mode is " + currentModeEnum.name() + ", the event is " + trainNNEventEnum.name());
				break;
			}
			break;
		case Training:
			switch (trainNNEventEnum) {
			case Reset:
				currentModeEnum = TrainNNModeEnum.Ready;
				trainNNResult = new TrainNNResultParameter();
				trainNNResult.setNnModeEnum(currentModeEnum);
				trainNNResultJPanel.setTrainNNResult(trainNNResult, true);
				nnCore.stop();
				break;
			case UpdateResult:
				trainNNResult = (TrainNNResultParameter) trainNNEvent.getData();
				trainNNResultJPanel.setTrainNNResult(trainNNResult, false);
				break;
			case Replace:
				trainNNResult = (TrainNNResultParameter) trainNNEvent.getData();
				nnCore.replace(trainNNResult);
				break;
			default:
				logger.error("the current mode is " + currentModeEnum.name() + ", the event is " + trainNNEventEnum.name());
				break;
			}
			break;
		default:
			logger.error("unexpected TrainNNModeEnum, it is " + currentModeEnum.name());
			break;
		}
	}
	
	private void train(TrainNNParameter trainNNParameter) {
		nnObject = new NNObject();
		
		trainNNData.clear();
		loadData(CoreContextUtil.isTemplate(), CoreContextUtil.getTokenId());
		
		nnObject.addNeuronsLayer(trainNNData.getInputSize(), trainNNParameter.getBias());
		for (NNItem item : trainNNParameter.getNNLayer()) {
			nnObject.addNeuronsLayer(item.numberOfNeuron, trainNNParameter.getBias());
		}
		nnObject.setLearningRate(trainNNParameter.getLearningRate());
		nnObject.setMomentum(trainNNParameter.getMomentum());
		nnObject.setMaxNoise(trainNNParameter.getMaxNoise());
		nnObject.addNeuronsLayer(trainNNData.getOutputSize(), trainNNParameter.getBias());
		
		nnCore.setTrainNNData(trainNNData);
		nnCore.setNnObject(nnObject);
		nnCore.setInputListList(trainNNData.getInputListList());
		nnCore.setTargetOutputList(trainNNData.getOutputList());
		nnCore.setTrainNNParameter(trainNNParameter);
		
		nnCore.train();
	}
	
	public void loadData(boolean isTemplate, long tokenId) {
		String tag = null;
		EnnMonitorLogConfigTemplateTable table = null;
		
		EnnMonitorLogConfigCacheResponse response = null;
		
		List<Object> tmpDataList = null;
		
		try {
			if (isTemplate == true) {
				CoreContextUtil.tagClear();
				tmpDataList = EnnMonitorLogConfigTemplateClientUtil.getEnnMonitorLogConfigTemplateClient().getValidDataList(
						EnnMonitorLogConfigTemplateGetValidDataListRequest.newBuilder().setStartSeqNo(-1l).setBatchNum(1000).setSetType(EnnMonitorLogConfigTemplateSetType.Manual).build());
			} else {
				// 添加预处理的数据集
				trainNNData.addTrainData("normal", "");
				tmpDataList = EnnMonitorLogConfigTemplateClientUtil.getEnnMonitorLogConfigTemplateClient().getValidDataList(
						EnnMonitorLogConfigTemplateGetValidDataListRequest.newBuilder().setStartSeqNo(-1l).setBatchNum(1000).setBelongToServiceId(tokenId).build());
			}
			while (tmpDataList != null && tmpDataList.size() > 0) {
				for (Object item : tmpDataList) {
					table = (EnnMonitorLogConfigTemplateTable) item;
					
					if (table.getBelongToParentTemplate() != null && table.getBelongToParentTemplate().equals("") == false) {
						continue;
					}
					
					if (table.getTemplateKey().indexOf('-') < 0) {
						continue;
					}
					
					if (isTemplate == true) {
						response = EnnMonitorLogConfigCacheClientUtil.getEnnMonitorLogConfigCacheClient().getTag(EnnMonitorLogConfigCacheRequest.newBuilder().setTemplateKey(table.getTemplateKey()).build());
						if (response.getIsSuccess() == false) {
							logger.error(response.getError());
							continue;
						}
						if (response.getTag() == null || response.getTag().equals("") == true) {
							logger.error(table.getTemplateKey() + ", the tag is null");
							continue;
						}
						tag = response.getTag();
						trainNNData.addTrainData(tag, table.getTemplateKey().substring(table.getTemplateKey().indexOf('-') + 1).replaceAll("-", " "));
						
						if (table.getTagId() == 0) {
							System.out.println(table.toString());
						}
						CoreContextUtil.addTag(tag, table.getTagId());
					} else {
						trainNNData.addTrainData(table.getTemplateKey(), table.getTemplateKey().substring(table.getTemplateKey().indexOf('-') + 1).replaceAll("-", " "));
					}
				}
				
				table = (EnnMonitorLogConfigTemplateTable) tmpDataList.get(tmpDataList.size() - 1);
				if (isTemplate == true) {
					tmpDataList = EnnMonitorLogConfigTemplateClientUtil.getEnnMonitorLogConfigTemplateClient().getValidDataList(
							EnnMonitorLogConfigTemplateGetValidDataListRequest.newBuilder().setStartSeqNo(table.getSeqNo()).setBatchNum(1000).setSetType(EnnMonitorLogConfigTemplateSetType.Manual).build());
				} else {
					tmpDataList = EnnMonitorLogConfigTemplateClientUtil.getEnnMonitorLogConfigTemplateClient().getValidDataList(
							EnnMonitorLogConfigTemplateGetValidDataListRequest.newBuilder().setStartSeqNo(table.getSeqNo()).setBatchNum(1000).setBelongToServiceId(tokenId).build());
				}
			}
			
			logger.info("the input size: " + trainNNData.getInputSize());
			logger.info("the output size: " + trainNNData.getOutputSize());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}
	
	public void setTrainNNResultJPanel(TrainNNResultJPanel trainNNResultJPanel) {
		this.trainNNResultJPanel = trainNNResultJPanel;
	}
	
}
