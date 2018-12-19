package enn.monitor.log.data.template.extraction;

import java.util.concurrent.BlockingQueue;

import enn.monitor.log.ai.common.CommonEventInterface;
import enn.monitor.log.ai.common.CommonFSMInterface;
import enn.monitor.log.train.util.EnnMonitorLogAnalyseTemplateClientUtil;

public class DataTemplateExtractionFSM extends CommonFSMInterface {
	
	private DataTemplateExtractionJPanel dataTemplateExtractionJPanel = null;
	
	public DataTemplateExtractionFSM(BlockingQueue<CommonEventInterface> eventQueue) {
		super("LogDataTemplateExtraction");
		this.eventQueue = eventQueue;
	}

	@Override
	public void runEvent(Object data) throws Exception {
		String result = null;
		
		DataTemplateExtractionParameter dataTemplateExtractionParameter = null;
		DataTemplateExtractionEvent dataTemplateExtractionEvent = (DataTemplateExtractionEvent) data;
		
		dataTemplateExtractionParameter = (DataTemplateExtractionParameter) dataTemplateExtractionEvent.getData();
		
		if (dataTemplateExtractionParameter.getIndex() == null || dataTemplateExtractionParameter.getIndex().equals("") == true) {
			throw new Exception("the index is null");
		}
		
		result = EnnMonitorLogAnalyseTemplateClientUtil.getEnnMonitorLogAnalyseTemplateClient().analyseTemplate(
				dataTemplateExtractionParameter.getIndex(), dataTemplateExtractionParameter.getTfRatio(), dataTemplateExtractionParameter.getSimilarRatio());

		dataTemplateExtractionJPanel.displayAnalyseTemplateStatus(result);
		
		dataTemplateExtractionJPanel.stopTask();
	}

	public void setLogTemplateExtractionJPanel(DataTemplateExtractionJPanel dataTemplateExtractionJPanel) {
		this.dataTemplateExtractionJPanel = dataTemplateExtractionJPanel;
	}
	
}
