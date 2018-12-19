package enn.monitor.log.ai.data.main;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import enn.monitor.log.ai.common.CommonEventInterface;
import enn.monitor.log.ai.core.EnnMonitorLogAiCoreFSM;
import enn.monitor.log.data.knowledge.DataKnowledgeFSM;
import enn.monitor.log.data.knowledge.DataKnowledgeJPanel;
import enn.monitor.log.data.tag.DataTagFSM;
import enn.monitor.log.data.tag.DataTagJPanel;
import enn.monitor.log.data.template.DataTemplateFSM;
import enn.monitor.log.data.template.DataTemplateJPanel;
import enn.monitor.log.data.template.extraction.DataTemplateExtractionFSM;
import enn.monitor.log.data.template.extraction.DataTemplateExtractionJPanel;

public class EnnMonitorLogAiDataMainJPanel extends JPanel {
	
	private static final long serialVersionUID = -6402056914273923769L;
	
	protected BlockingQueue<CommonEventInterface> eventQueue = new LinkedBlockingQueue<CommonEventInterface>();
	
	private EnnMonitorLogAiCoreFSM dataCoreFSM = new EnnMonitorLogAiCoreFSM(eventQueue);
	
	private DataKnowledgeFSM dataKnowledgeFSM = new DataKnowledgeFSM(eventQueue);
	private DataKnowledgeJPanel dataKnowledgeJPanel = null;
	
	private DataTagFSM dataTagFSM = new DataTagFSM(eventQueue);
	private DataTagJPanel dataTagJPanel = null;
	
	private DataTemplateExtractionFSM dataTemplateExtractionFSM = new DataTemplateExtractionFSM(eventQueue);
	private DataTemplateExtractionJPanel dataTemplateExtractionJPanel = null;
	
	private DataTemplateFSM dataTemplateFSM = new DataTemplateFSM(eventQueue);
	private DataTemplateJPanel dataTemplateJPanel = null;
	
	public EnnMonitorLogAiDataMainJPanel() throws Exception {
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);

		// knowledge
		dataKnowledgeJPanel = new DataKnowledgeJPanel(dataKnowledgeFSM);
		add(dataKnowledgeJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, dataKnowledgeJPanel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, dataKnowledgeJPanel, 270, SpringLayout.NORTH, dataKnowledgeJPanel);
		tspLayout.putConstraint(SpringLayout.WEST, dataKnowledgeJPanel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, dataKnowledgeJPanel, 800, SpringLayout.WEST, dataKnowledgeJPanel);
		
		dataTagJPanel = new DataTagJPanel(dataTagFSM);
		add(dataTagJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, dataTagJPanel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, dataTagJPanel, 270, SpringLayout.NORTH, dataTagJPanel);
		tspLayout.putConstraint(SpringLayout.WEST, dataTagJPanel, 10, SpringLayout.EAST, dataKnowledgeJPanel);
		tspLayout.putConstraint(SpringLayout.EAST, dataTagJPanel, -10, SpringLayout.EAST, this);
		
		dataTemplateExtractionJPanel = new DataTemplateExtractionJPanel(dataTemplateExtractionFSM);
		add(dataTemplateExtractionJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, dataTemplateExtractionJPanel, 10, SpringLayout.SOUTH, dataKnowledgeJPanel);
		tspLayout.putConstraint(SpringLayout.SOUTH, dataTemplateExtractionJPanel, 150, SpringLayout.NORTH, dataTemplateExtractionJPanel);
		tspLayout.putConstraint(SpringLayout.WEST, dataTemplateExtractionJPanel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, dataTemplateExtractionJPanel, -10, SpringLayout.EAST, this);
		
		// template
		dataTemplateJPanel = new DataTemplateJPanel(dataTemplateFSM);
		add(dataTemplateJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, dataTemplateJPanel, 10, SpringLayout.SOUTH, dataTemplateExtractionJPanel);
		tspLayout.putConstraint(SpringLayout.SOUTH, dataTemplateJPanel, -10, SpringLayout.SOUTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, dataTemplateJPanel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, dataTemplateJPanel, -10, SpringLayout.EAST, this);
		
		dataKnowledgeFSM.setDataKnowledgeJPanel(dataKnowledgeJPanel);
		dataCoreFSM.addCoreModuleInfo(dataKnowledgeFSM.getModuleName(), dataKnowledgeFSM);
		
		dataTagFSM.setDataTagJPanel(dataTagJPanel);
		dataCoreFSM.addCoreModuleInfo(dataTagFSM.getModuleName(), dataTagFSM);
		
		dataTemplateExtractionFSM.setLogTemplateExtractionJPanel(dataTemplateExtractionJPanel);
		dataCoreFSM.addCoreModuleInfo(dataTemplateExtractionFSM.getModuleName(), dataTemplateExtractionFSM);
		
		dataTemplateFSM.setDataTemplateJPanel(dataTemplateJPanel);
		dataCoreFSM.addCoreModuleInfo(dataTemplateFSM.getModuleName(), dataTemplateFSM);
		
		new Thread(dataCoreFSM).start();
	}

}
