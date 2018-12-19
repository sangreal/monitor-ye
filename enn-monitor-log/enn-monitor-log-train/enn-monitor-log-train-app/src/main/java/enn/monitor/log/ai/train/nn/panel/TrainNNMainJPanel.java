package enn.monitor.log.ai.train.nn.panel;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import enn.monitor.log.ai.train.nn.core.TrainNNFSM;

public class TrainNNMainJPanel extends JPanel {

	private static final long serialVersionUID = -670249879201809409L;
	
	private static final int labelWidth = 120;
	private static final int textWidth = 200;
	
	private TrainNNResultJPanel trainNNResultJPanel = null;
	private TrainNNConfigureJPanel trainNNConfigureJPanel = null;
	
	public TrainNNMainJPanel(TrainNNFSM trainNNFSM) {
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		trainNNConfigureJPanel = new TrainNNConfigureJPanel(trainNNFSM);
		add(trainNNConfigureJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, trainNNConfigureJPanel, 0, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, trainNNConfigureJPanel, 0, SpringLayout.SOUTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, trainNNConfigureJPanel, 0, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, trainNNConfigureJPanel, 600, SpringLayout.WEST, trainNNConfigureJPanel);
		
		trainNNResultJPanel = new TrainNNResultJPanel(trainNNFSM, labelWidth, textWidth);
		add(trainNNResultJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, trainNNResultJPanel, 0, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, trainNNResultJPanel, 0, SpringLayout.SOUTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, trainNNResultJPanel, 3, SpringLayout.EAST, trainNNConfigureJPanel);
		tspLayout.putConstraint(SpringLayout.EAST, trainNNResultJPanel, 0, SpringLayout.EAST, this);
		
		trainNNFSM.setTrainNNResultJPanel(trainNNResultJPanel);
	}

}
