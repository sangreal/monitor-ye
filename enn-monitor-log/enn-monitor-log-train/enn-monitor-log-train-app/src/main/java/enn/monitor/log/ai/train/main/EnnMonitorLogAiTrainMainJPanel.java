package enn.monitor.log.ai.train.main;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import enn.monitor.log.ai.common.CommonEventInterface;
import enn.monitor.log.ai.core.EnnMonitorLogAiCoreFSM;
import enn.monitor.log.ai.train.config.EnnMonitorLogAiTrainConfigJPanel;
import enn.monitor.log.ai.train.nn.core.TrainNNFSM;
import enn.monitor.log.ai.train.nn.panel.TrainNNMainJPanel;
import enn.monitor.log.ai.train.result.EnnMonitorLogAiTrainResultFSM;
import enn.monitor.log.ai.train.result.EnnMonitorLogAiTrainResultJPanel;
import enn.monitor.log.ai.train.test.core.TestFSM;
import enn.monitor.log.ai.train.test.panel.TestMainJPanel;

public class EnnMonitorLogAiTrainMainJPanel extends JPanel {
	
	private static final long serialVersionUID = 2002012308498717087L;
	
	protected BlockingQueue<CommonEventInterface> eventQueue = new LinkedBlockingQueue<CommonEventInterface>();
	
	private EnnMonitorLogAiCoreFSM coreFSM = new EnnMonitorLogAiCoreFSM(eventQueue);
	
	private EnnMonitorLogAiTrainConfigJPanel trainConfigJPanel = null;
	
	private TrainNNFSM trainNNFSM = new TrainNNFSM(eventQueue);
	private TrainNNMainJPanel trainNNMainJPanel = null;
	
	private TestFSM testFSM = new TestFSM(eventQueue);
	private TestMainJPanel testMainJPanel = null;
	
	private EnnMonitorLogAiTrainResultFSM ennMonitorLogAiTrainResultFSM = new EnnMonitorLogAiTrainResultFSM(eventQueue);
	private EnnMonitorLogAiTrainResultJPanel ennMonitorLogAiTrainResultJPanel = null;
	
	private final static int FirstLayerHeight = 450;
	
	public EnnMonitorLogAiTrainMainJPanel() throws Exception {
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		trainConfigJPanel = new EnnMonitorLogAiTrainConfigJPanel();
		add(trainConfigJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, trainConfigJPanel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, trainConfigJPanel, FirstLayerHeight, SpringLayout.NORTH, trainConfigJPanel);
		tspLayout.putConstraint(SpringLayout.WEST, trainConfigJPanel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, trainConfigJPanel, 350, SpringLayout.WEST, trainConfigJPanel);
		
		trainNNMainJPanel = new TrainNNMainJPanel(trainNNFSM);
		add(trainNNMainJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, trainNNMainJPanel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, trainNNMainJPanel, FirstLayerHeight, SpringLayout.NORTH, trainNNMainJPanel);
		tspLayout.putConstraint(SpringLayout.WEST, trainNNMainJPanel, 10, SpringLayout.EAST, trainConfigJPanel);
		tspLayout.putConstraint(SpringLayout.EAST, trainNNMainJPanel, 1500, SpringLayout.WEST, trainNNMainJPanel);
		
		testMainJPanel = new TestMainJPanel(testFSM);
		add(testMainJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, testMainJPanel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, testMainJPanel, FirstLayerHeight, SpringLayout.NORTH, testMainJPanel);
		tspLayout.putConstraint(SpringLayout.WEST, testMainJPanel, 10, SpringLayout.EAST, trainNNMainJPanel);
		tspLayout.putConstraint(SpringLayout.EAST, testMainJPanel, -10, SpringLayout.EAST, this);
		
		ennMonitorLogAiTrainResultJPanel = new EnnMonitorLogAiTrainResultJPanel(ennMonitorLogAiTrainResultFSM);
		add(ennMonitorLogAiTrainResultJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, ennMonitorLogAiTrainResultJPanel, 10, SpringLayout.SOUTH, trainNNMainJPanel);
		tspLayout.putConstraint(SpringLayout.SOUTH, ennMonitorLogAiTrainResultJPanel, -10, SpringLayout.SOUTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, ennMonitorLogAiTrainResultJPanel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, ennMonitorLogAiTrainResultJPanel, -10, SpringLayout.EAST, this);
		
		
		coreFSM.addCoreModuleInfo(trainNNFSM.getModuleName(), trainNNFSM);
		coreFSM.addCoreModuleInfo(testFSM.getModuleName(), testFSM);
		
		ennMonitorLogAiTrainResultFSM.setEnnMonitorLogAiTrainResultJPanel(ennMonitorLogAiTrainResultJPanel);
		coreFSM.addCoreModuleInfo(ennMonitorLogAiTrainResultFSM.getModuleName(), ennMonitorLogAiTrainResultFSM);
		
		new Thread(coreFSM).start();
	}

}
