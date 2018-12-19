package enn.monitor.log.ai.train.nn.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import enn.monitor.framework.ai.nn.activation.NNActivationEnum;
import enn.monitor.log.ai.train.nn.core.TrainNNEvent;
import enn.monitor.log.ai.train.nn.core.TrainNNEventEnum;
import enn.monitor.log.ai.train.nn.core.TrainNNFSM;
import enn.monitor.log.ai.train.nn.parameter.TrainNNParameter;
import enn.monitor.log.analyse.data.EnnMonitorLogAnalyseData;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateRequest;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchRequest;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchResponse;
import enn.monitor.log.train.util.CoreContextUtil;
import enn.monitor.log.train.util.EnnMonitorLogAnalyseStorageClientUtil;

public class TrainNNConfigureJPanel extends JPanel {

	private static final long serialVersionUID = -82236800496408040L;
	
	private static final Logger logger = LogManager.getLogger();
	
	private Gson gson = new Gson();

	private JLabel hiddenLayerLabel = new JLabel();
	private JTextField hiddenLayerText = new JTextField();
	private JButton addButton = new JButton();
	
	private JLabel deleteHiddenLayerLabel = new JLabel();
	private JTextField deleteHiddenLayerText = new JTextField();
	private JButton delButton = new JButton();
	
	private JLabel learningRateLabel = new JLabel();
	private JTextField learningRateText = new JTextField();
	
	private JLabel deviationLabel = new JLabel();
	private JTextField deviationText = new JTextField();
	
	private JLabel biasLabel = new JLabel();
	private JTextField biasText = new JTextField();
	
	private JLabel momentumLabel = new JLabel();
	private JTextField momentumText = new JTextField();
	
	private JLabel maxNoiseLabel = new JLabel();
	private JTextField maxNoiseText = new JTextField();
	
	private JButton runButton = new JButton();
	
	private JLabel loadIdLabel = new JLabel();
	private JTextField loadIdText = new JTextField();
	
	private JButton releaseButton = new JButton();
	private JButton loadButton = new JButton();
	
	private final String columnHeader[] = {"ID", "类型", "神经元数目"};
	private JTable table = new JTable(new DefaultTableModel());
	private JScrollPane scrollPane = new JScrollPane(table);
	
	private JLabel activationLabel = new JLabel();
	private JComboBox activationComboBox = null;
	
	private TrainNNParameter nnConfigureParameter = new TrainNNParameter();
	
	private int labelWidth = 80;
	private int textWidth = 100;
	private int buttonWidth = 80;
	
	private TrainNNFSM trainNNFSM = null;
	
	private boolean isRun = false;
	
	public TrainNNConfigureJPanel(TrainNNFSM trainNNFSM) {
		DefaultTableModel defaultTableModel = null;
		
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		this.trainNNFSM = trainNNFSM;
		
		// 添加隐藏层
		hiddenLayerLabel.setText("添加隐藏层");
		add(hiddenLayerLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, hiddenLayerLabel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, hiddenLayerLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, hiddenLayerLabel, labelWidth, SpringLayout.WEST, hiddenLayerLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, hiddenLayerLabel, 20, SpringLayout.NORTH, hiddenLayerLabel);
		
		add(hiddenLayerText);
		tspLayout.putConstraint(SpringLayout.NORTH, hiddenLayerText, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, hiddenLayerText, 10, SpringLayout.EAST, hiddenLayerLabel);
		tspLayout.putConstraint(SpringLayout.EAST, hiddenLayerText, textWidth, SpringLayout.WEST, hiddenLayerText);
		tspLayout.putConstraint(SpringLayout.SOUTH, hiddenLayerText, 20, SpringLayout.NORTH, hiddenLayerText);
		
		addButton.setText("添加");
		add(addButton);
		tspLayout.putConstraint(SpringLayout.NORTH, addButton, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, addButton, 10, SpringLayout.EAST, hiddenLayerText);
		tspLayout.putConstraint(SpringLayout.EAST, addButton, buttonWidth, SpringLayout.WEST, addButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, addButton, 20, SpringLayout.NORTH, addButton);
		
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nnConfigureParameter.addHiddenLayer(Integer.parseInt(hiddenLayerText.getText()));
				updateTable();
			}
			
		});
		
		//删除隐藏层
		deleteHiddenLayerLabel.setText("删除隐藏层");
		add(deleteHiddenLayerLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, deleteHiddenLayerLabel, 10, SpringLayout.SOUTH, hiddenLayerLabel);
		tspLayout.putConstraint(SpringLayout.WEST, deleteHiddenLayerLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, deleteHiddenLayerLabel, labelWidth, SpringLayout.WEST, deleteHiddenLayerLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, deleteHiddenLayerLabel, 20, SpringLayout.NORTH, deleteHiddenLayerLabel);
		
		add(deleteHiddenLayerText);
		tspLayout.putConstraint(SpringLayout.NORTH, deleteHiddenLayerText, 10, SpringLayout.SOUTH, hiddenLayerText);
		tspLayout.putConstraint(SpringLayout.WEST, deleteHiddenLayerText, 10, SpringLayout.EAST, deleteHiddenLayerLabel);
		tspLayout.putConstraint(SpringLayout.EAST, deleteHiddenLayerText, textWidth, SpringLayout.WEST, deleteHiddenLayerText);
		tspLayout.putConstraint(SpringLayout.SOUTH, deleteHiddenLayerText, 20, SpringLayout.NORTH, deleteHiddenLayerText);
		
		delButton.setText("删除");
		add(delButton);
		tspLayout.putConstraint(SpringLayout.NORTH, delButton, 10, SpringLayout.SOUTH, hiddenLayerText);
		tspLayout.putConstraint(SpringLayout.WEST, delButton, 10, SpringLayout.EAST, deleteHiddenLayerText);
		tspLayout.putConstraint(SpringLayout.EAST, delButton, buttonWidth, SpringLayout.WEST, delButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, delButton, 20, SpringLayout.NORTH, delButton);
		
		delButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nnConfigureParameter.deleteHiddenLayer(Integer.parseInt(deleteHiddenLayerText.getText()));
				updateTable();
			}
			
		});
		
		// 学习率
		learningRateLabel.setText("学习率");
		add(learningRateLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, learningRateLabel, 10, SpringLayout.SOUTH, deleteHiddenLayerLabel);
		tspLayout.putConstraint(SpringLayout.WEST, learningRateLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, learningRateLabel, labelWidth, SpringLayout.WEST, learningRateLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, learningRateLabel, 20, SpringLayout.NORTH, learningRateLabel);
		
		learningRateText.setText("1");
		add(learningRateText);
		tspLayout.putConstraint(SpringLayout.NORTH, learningRateText, 10, SpringLayout.SOUTH, deleteHiddenLayerText);
		tspLayout.putConstraint(SpringLayout.WEST, learningRateText, 10, SpringLayout.EAST, learningRateLabel);
		tspLayout.putConstraint(SpringLayout.EAST, learningRateText, textWidth, SpringLayout.WEST, learningRateText);
		tspLayout.putConstraint(SpringLayout.SOUTH, learningRateText, 20, SpringLayout.NORTH, learningRateText);
		
		// 误差值
		deviationLabel.setText("误差值");
		add(deviationLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, deviationLabel, 10, SpringLayout.SOUTH, learningRateLabel);
		tspLayout.putConstraint(SpringLayout.WEST, deviationLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, deviationLabel, labelWidth, SpringLayout.WEST, deviationLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, deviationLabel, 20, SpringLayout.NORTH, deviationLabel);
		
		deviationText.setText("0.03");
		add(deviationText);
		tspLayout.putConstraint(SpringLayout.NORTH, deviationText, 10, SpringLayout.SOUTH, learningRateText);
		tspLayout.putConstraint(SpringLayout.WEST, deviationText, 10, SpringLayout.EAST, deviationLabel);
		tspLayout.putConstraint(SpringLayout.EAST, deviationText, textWidth, SpringLayout.WEST, deviationText);
		tspLayout.putConstraint(SpringLayout.SOUTH, deviationText, 20, SpringLayout.NORTH, deviationText);
		
		// 权重偏移量
		biasLabel.setText("Bias");
		add(biasLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, biasLabel, 10, SpringLayout.SOUTH, deviationLabel);
		tspLayout.putConstraint(SpringLayout.WEST, biasLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, biasLabel, labelWidth, SpringLayout.WEST, biasLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, biasLabel, 20, SpringLayout.NORTH, biasLabel);
		
		biasText.setText("1");
		add(biasText);
		tspLayout.putConstraint(SpringLayout.NORTH, biasText, 10, SpringLayout.SOUTH, deviationText);
		tspLayout.putConstraint(SpringLayout.WEST, biasText, 10, SpringLayout.EAST, biasLabel);
		tspLayout.putConstraint(SpringLayout.EAST, biasText, textWidth, SpringLayout.WEST, biasText);
		tspLayout.putConstraint(SpringLayout.SOUTH, biasText, 20, SpringLayout.NORTH, biasText);
		
		// 动量
		momentumLabel.setText("Momentum");
		add(momentumLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, momentumLabel, 10, SpringLayout.SOUTH, biasLabel);
		tspLayout.putConstraint(SpringLayout.WEST, momentumLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, momentumLabel, labelWidth, SpringLayout.WEST, momentumLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, momentumLabel, 20, SpringLayout.NORTH, momentumLabel);
		
		momentumText.setText("1");
		add(momentumText);
		tspLayout.putConstraint(SpringLayout.NORTH, momentumText, 10, SpringLayout.SOUTH, biasText);
		tspLayout.putConstraint(SpringLayout.WEST, momentumText, 10, SpringLayout.EAST, momentumLabel);
		tspLayout.putConstraint(SpringLayout.EAST, momentumText, textWidth, SpringLayout.WEST, momentumText);
		tspLayout.putConstraint(SpringLayout.SOUTH, momentumText, 20, SpringLayout.NORTH, momentumText);
		
		// 噪声
		maxNoiseLabel.setText("最大噪声");
		add(maxNoiseLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, maxNoiseLabel, 10, SpringLayout.SOUTH, momentumLabel);
		tspLayout.putConstraint(SpringLayout.WEST, maxNoiseLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, maxNoiseLabel, labelWidth, SpringLayout.WEST, maxNoiseLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, maxNoiseLabel, 20, SpringLayout.NORTH, maxNoiseLabel);
		
		maxNoiseText.setText("0.1");
		add(maxNoiseText);
		tspLayout.putConstraint(SpringLayout.NORTH, maxNoiseText, 10, SpringLayout.SOUTH, momentumText);
		tspLayout.putConstraint(SpringLayout.WEST, maxNoiseText, 10, SpringLayout.EAST, maxNoiseLabel);
		tspLayout.putConstraint(SpringLayout.EAST, maxNoiseText, textWidth, SpringLayout.WEST, maxNoiseText);
		tspLayout.putConstraint(SpringLayout.SOUTH, maxNoiseText, 20, SpringLayout.NORTH, maxNoiseText);
		
		// 激活函数
		activationLabel.setText("激励函数");
		add(activationLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, activationLabel, 10, SpringLayout.SOUTH, maxNoiseLabel);
		tspLayout.putConstraint(SpringLayout.WEST, activationLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, activationLabel, labelWidth, SpringLayout.WEST, activationLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, activationLabel, 20, SpringLayout.NORTH, activationLabel);
		
		List<String> activationList = new ArrayList<String>();
		activationList.add(NNActivationEnum.Sigmoid.value());
		
		activationComboBox = new JComboBox(activationList.toArray());
		add(activationComboBox);
		tspLayout.putConstraint(SpringLayout.NORTH, activationComboBox, 10, SpringLayout.SOUTH, maxNoiseText);
		tspLayout.putConstraint(SpringLayout.WEST, activationComboBox, 10, SpringLayout.EAST, activationLabel);
		tspLayout.putConstraint(SpringLayout.EAST, activationComboBox, textWidth, SpringLayout.WEST, activationComboBox);
		tspLayout.putConstraint(SpringLayout.SOUTH, activationComboBox, 20, SpringLayout.NORTH, activationComboBox);
		
		add(runButton);
		runButton.setText("Run");
		tspLayout.putConstraint(SpringLayout.NORTH, runButton, 10, SpringLayout.SOUTH, maxNoiseText);
		tspLayout.putConstraint(SpringLayout.WEST, runButton, 10, SpringLayout.EAST, activationComboBox);
		tspLayout.putConstraint(SpringLayout.EAST, runButton, buttonWidth, SpringLayout.WEST, runButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, runButton, 20, SpringLayout.NORTH, runButton);
		runButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isRun == false) {
					startTask();
				} else {
					stopTask();
				}
			}
			
		});
		
		add(releaseButton);
		releaseButton.setText("Release");
		tspLayout.putConstraint(SpringLayout.NORTH, releaseButton, 10, SpringLayout.SOUTH, activationLabel);
		tspLayout.putConstraint(SpringLayout.WEST, releaseButton, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, releaseButton, buttonWidth + 40, SpringLayout.WEST, releaseButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, releaseButton, 20, SpringLayout.NORTH, releaseButton);
		releaseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String json = null;
					EnnMonitorLogAnalyseData ennMonitorAnalyseData = null;
					
					ennMonitorAnalyseData = new EnnMonitorLogAnalyseData();
					ennMonitorAnalyseData.setNnObject(CoreContextUtil.getNnObject());
					ennMonitorAnalyseData.setTrainNNData(CoreContextUtil.getTrainNNData());
					
					json = gson.toJson(ennMonitorAnalyseData);
					EnnMonitorLogAnalyseStorageClientUtil.getEnnMonitorLogAnalyseStorageClient()
						.createNN(EnnMonitorLogAnalyseStorageCreateRequest.newBuilder().setTokenId(CoreContextUtil.getTokenId()).setNndata(json).build());
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
			
		});
		
		loadIdLabel.setText("LoadId");
		add(loadIdLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, loadIdLabel, 10, SpringLayout.SOUTH, releaseButton);
		tspLayout.putConstraint(SpringLayout.WEST, loadIdLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, loadIdLabel, labelWidth, SpringLayout.WEST, loadIdLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, loadIdLabel, 20, SpringLayout.NORTH, loadIdLabel);
		
		add(loadIdText);
		tspLayout.putConstraint(SpringLayout.NORTH, loadIdText, 10, SpringLayout.SOUTH, releaseButton);
		tspLayout.putConstraint(SpringLayout.WEST, loadIdText, 10, SpringLayout.EAST, loadIdLabel);
		tspLayout.putConstraint(SpringLayout.EAST, loadIdText, textWidth, SpringLayout.WEST, loadIdText);
		tspLayout.putConstraint(SpringLayout.SOUTH, loadIdText, 20, SpringLayout.NORTH, loadIdText);
		
		add(loadButton);
		loadButton.setText("Load");
		tspLayout.putConstraint(SpringLayout.NORTH, loadButton, 10, SpringLayout.SOUTH, releaseButton);
		tspLayout.putConstraint(SpringLayout.WEST, loadButton, 10, SpringLayout.EAST, loadIdText);
		tspLayout.putConstraint(SpringLayout.EAST, loadButton, buttonWidth, SpringLayout.WEST, loadButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, loadButton, 20, SpringLayout.NORTH, loadButton);
		loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String json = null;
				EnnMonitorLogAnalyseData ennMonitorAnalyseData = null;
				
				if (loadIdText.getText() == null || loadIdText.getText().equals("") == true) {
					return;
				}
				
				try {
					long loadId = Long.parseLong(loadIdText.getText());
					
					EnnMonitorLogAnalyseStorageSearchResponse response = EnnMonitorLogAnalyseStorageClientUtil.getEnnMonitorLogAnalyseStorageClient()
						.searchNN(EnnMonitorLogAnalyseStorageSearchRequest.newBuilder().setId(loadId).build());
					
					if (response.getIsSuccess() == true && response.getAnalyseStorageTableList() != null && response.getAnalyseStorageTableList().size() > 0) {
						json = response.getAnalyseStorageTableList().get(0).getNndata();
						if (json != null && json.equals("") == false) {
							json = response.getAnalyseStorageTableList().get(0).getNndata();
							ennMonitorAnalyseData = gson.fromJson(json, EnnMonitorLogAnalyseData.class);
							
							CoreContextUtil.setNnObject(ennMonitorAnalyseData.getNnObject());
							CoreContextUtil.setTrainNNData(ennMonitorAnalyseData.getTrainNNData());
						}
					} else if (response.getIsSuccess() == false) {
						logger.error(response.getError());
					}
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
			
		});
		
		// 表格
		add(scrollPane);
		tspLayout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.EAST, addButton);
		tspLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, this);
		
		defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setColumnIdentifiers(columnHeader);
		
		updateTable();
	}
	
	public void startTask() {
		try {
			trainNNFSM.addEvent(new TrainNNEvent(TrainNNEventEnum.Train, getTrainNNConfigureParameter()));
			runButton.setText("Stop");
			isRun = true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void stopTask() {
		try {
			trainNNFSM.addEvent(new TrainNNEvent(TrainNNEventEnum.Reset, null));
			runButton.setText("Run");
			isRun = false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public TrainNNParameter getTrainNNConfigureParameter() {
		NNActivationEnum nnActivationEnum = null;
		
		nnConfigureParameter.setBias(Double.parseDouble(biasText.getText()));
		nnActivationEnum = NNActivationEnum.valueOf(activationComboBox.getSelectedItem().toString());
		nnConfigureParameter.setNnActivationEnum(nnActivationEnum);
		nnConfigureParameter.setLearningRate(Double.parseDouble(learningRateText.getText()));
		nnConfigureParameter.setDeviation(Double.parseDouble(deviationText.getText()));
		nnConfigureParameter.setMomentum(Double.parseDouble(momentumText.getText()));
		nnConfigureParameter.setMaxNoise(Double.parseDouble(maxNoiseText.getText()));
		
		return nnConfigureParameter;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(248, 250, 241));
	}
	
	private void updateTable() {
		int i;
		
		Vector<String> rowData = null;
		DefaultTableModel defaultTableModel = null;
		
		defaultTableModel = (DefaultTableModel) table.getModel();
		
		defaultTableModel.getDataVector().clear();
		
		for (i = 0; i < nnConfigureParameter.getNNLayer().size(); ++i) {
			rowData = new Vector<String>();
			
			rowData.add("" + nnConfigureParameter.getNNLayer().get(i).id);
			rowData.add(nnConfigureParameter.getNNLayer().get(i).type);
			rowData.add("" + nnConfigureParameter.getNNLayer().get(i).numberOfNeuron);
			
			defaultTableModel.getDataVector().add(rowData);
		}
		
		table.updateUI();
	}
}
