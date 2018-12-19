package enn.monitor.ai.ga.nn.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import enn.monitor.ai.ga.nn.common.GAAdaptabilityEnum;
import enn.monitor.ai.ga.nn.common.GAConfigureParameter;
import enn.monitor.ai.ga.scaleratio.GAScaleRatioEnum;
import enn.monitor.ai.ga.selection.GASelectionEnum;

public class GAConfigureJPanel extends JPanel {

	private static final long serialVersionUID = -8183053612692838592L;
	
	// parameter component
	private JLabel geneSizeLabel = new JLabel();
	private JTextField geneSizeText = new JTextField();
	
	private JLabel dataSizeLabel = new JLabel();
	private JTextField dataSizeText = new JTextField();
	
	private JLabel crossoverLabel = new JLabel();
	private JTextField crossoverText = new JTextField();
	
	private JLabel mutationLabel = new JLabel();
	private JTextField mutationText = new JTextField();
	
	private JLabel mutationOffLabel = new JLabel();
	private JTextField mutationOffText = new JTextField();
	
	private JLabel scaleTypeAlgLabel = new JLabel();
	private JComboBox scaleTypeComboBox = null;
	private JLabel boltzDtLabel = new JLabel();
	private JTextField boltzDtText = new JTextField();
	private JLabel boltzMinLabel = new JLabel();
	private JTextField boltzMinText = new JTextField();
	
	private JLabel selectionAlgLabel = new JLabel();
	private JComboBox selectionComboBox = null;
	private JLabel nBestLabel = new JLabel();
	private JTextField nBestText = new JTextField();
	private JLabel nPerLabel = new JLabel();
	private JTextField nPerText = new JTextField();
	private JLabel nWorstLabel = new JLabel();
	private JTextField nWorstText = new JTextField();
	private JLabel batchLabel = new JLabel();
	private JTextField batchText = new JTextField();
	
	private JLabel adaptabilityLabel = new JLabel();
	private JComboBox adaptabilityComboBox = null;
	
	public GAConfigureJPanel(int labelWidth, int textWidth) {
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		// 规模
		geneSizeLabel.setText("基因规模");
		add(geneSizeLabel);
		
		geneSizeText.setText("200");
		add(geneSizeText);
		
		// 数据规模
		dataSizeLabel.setText("数据规模");
		add(dataSizeLabel);
		
		dataSizeText.setText("10");
		add(dataSizeText);
		
		// 交换率
		crossoverLabel.setText("杂交率");
		add(crossoverLabel);
		
		crossoverText.setText("0.7");
		add(crossoverText);
		
		// 变换率
		mutationLabel.setText("变异率");
		add(mutationLabel);
		
		mutationText.setText("0.001");
		add(mutationText);
		
		// 变异因子
		mutationOffLabel.setText("变异偏移量");
		add(mutationOffLabel);
		
		mutationOffText.setText("0.1");
		add(mutationOffText);
		
		// 变比技术
		scaleTypeAlgLabel.setText("变比技术");
		add(scaleTypeAlgLabel);
		
		List<String> scaleTypeStr = new ArrayList<String>();
		for (GAScaleRatioEnum entry : GAScaleRatioEnum.values()) {
			scaleTypeStr.add(entry.value());
		}
		scaleTypeComboBox = new JComboBox(scaleTypeStr.toArray());
		add(scaleTypeComboBox);
		scaleTypeComboBox.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				if (ItemEvent.SELECTED == evt.getStateChange()) {
					displayComponent(tspLayout, labelWidth, textWidth);
				}
			}
		});
		
		boltzDtLabel.setText("Boltzmann DT");
		add(boltzDtLabel);
		boltzDtText.setText("0.05");
		add(boltzDtText);
		boltzMinLabel.setText("Boltzmann MIN");
		add(boltzMinLabel);
		boltzMinText.setText("1");
		add(boltzMinText);
		
		// 选择算子
		selectionAlgLabel.setText("选择算子");
		add(selectionAlgLabel);
		
		List<String> selectionStr = new ArrayList<String>();
		for (GASelectionEnum entry : GASelectionEnum.values()) {
			selectionStr.add(entry.value());
		}
		selectionComboBox = new JComboBox(selectionStr.toArray());
		add(selectionComboBox);
		selectionComboBox.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				if (ItemEvent.SELECTED == evt.getStateChange()) {
					displayComponent(tspLayout, labelWidth, textWidth);
				}
			}
		});
		
		nBestLabel.setText("最优种子数");
		add(nBestLabel);
		nBestText.setText("0");
		add(nBestText);
		nPerLabel.setText("每个种子的个数");
		add(nPerLabel);
		nPerText.setText("0");
		add(nPerText);
		nWorstLabel.setText("最差种子数");
		add(nWorstLabel);
		nWorstText.setText("0");
		add(nWorstText);
		batchLabel.setText("批次");
		add(batchLabel);
		batchText.setText("0");
		add(batchText);
		
		adaptabilityLabel.setText("适应性函数");
		add(adaptabilityLabel);
		
		List<String> adaptabilityList = new ArrayList<String>();
		for (GAAdaptabilityEnum entry : GAAdaptabilityEnum.values()) {
			adaptabilityList.add(entry.value());
		}
		adaptabilityComboBox = new JComboBox(adaptabilityList.toArray());
		add(adaptabilityComboBox);
		
		displayComponent(tspLayout, labelWidth, textWidth);
	}
	
	public GAConfigureParameter getTSPParameter() {
		GAConfigureParameter parameter = new GAConfigureParameter();
		
		parameter.setTotalSize(Integer.parseInt(geneSizeText.getText()));
		parameter.setPointNum(Integer.parseInt(dataSizeText.getText()));
		parameter.setCrossover(Double.parseDouble(crossoverText.getText()));
		parameter.setMutation(Double.parseDouble(mutationText.getText()));
		parameter.setMutationOff(Double.parseDouble(mutationOffText.getText()));
		
		if (scaleTypeComboBox.getSelectedItem().toString().equals(GAScaleRatioEnum.None.value()) == true) {
			parameter.setScaleRatioEnum(GAScaleRatioEnum.None);
		} else if (scaleTypeComboBox.getSelectedItem().toString().equals(GAScaleRatioEnum.Rank.value()) == true) {
			parameter.setScaleRatioEnum(GAScaleRatioEnum.Rank);
		} else if (scaleTypeComboBox.getSelectedItem().toString().equals(GAScaleRatioEnum.Sigma.value()) == true) {
			parameter.setScaleRatioEnum(GAScaleRatioEnum.Sigma);
		} else if (scaleTypeComboBox.getSelectedItem().toString().equals(GAScaleRatioEnum.Boltzmann.value()) == true) {
			parameter.setScaleRatioEnum(GAScaleRatioEnum.Boltzmann);
			parameter.setBoltzmanndt(Double.parseDouble(boltzDtText.getText()));
			parameter.setBoltzmannmin(Double.parseDouble(boltzMinText.getText()));
		}
		
		if (selectionComboBox.getSelectedItem().toString().equals(GASelectionEnum.RouletteWheel.value()) == true) {
			parameter.setSelectionEnum(GASelectionEnum.RouletteWheel);
		} else if (selectionComboBox.getSelectedItem().toString().equals(GASelectionEnum.SUS.value()) == true) {
			parameter.setSelectionEnum(GASelectionEnum.SUS);
		} else if (selectionComboBox.getSelectedItem().toString().equals(GASelectionEnum.Tournament.value()) == true) {
			parameter.setSelectionEnum(GASelectionEnum.Tournament);
			parameter.setBatchNum(Integer.parseInt(batchText.getText()));
		}
		parameter.setnBest(Integer.parseInt(nBestText.getText()));
		parameter.setnPer(Integer.parseInt(nPerText.getText()));
		parameter.setnWorst(Integer.parseInt(nWorstText.getText()));
		
		if (adaptabilityComboBox.getSelectedItem().toString().equals(GAAdaptabilityEnum.LongestRoute.value()) == true) {
			parameter.setGaAdaptabilityEnum(GAAdaptabilityEnum.LongestRoute);
		} else {
			parameter.setGaAdaptabilityEnum(GAAdaptabilityEnum.Total);
		}
		
		return parameter;
	}
	
	private void displayComponent(SpringLayout tspLayout, int labelWidth, int textWidth) {
		tspLayout.putConstraint(SpringLayout.NORTH, geneSizeLabel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, geneSizeLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, geneSizeLabel, labelWidth, SpringLayout.WEST, geneSizeLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, geneSizeLabel, 20, SpringLayout.NORTH, geneSizeLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, geneSizeText, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, geneSizeText, 10, SpringLayout.EAST, geneSizeLabel);
		tspLayout.putConstraint(SpringLayout.EAST, geneSizeText, textWidth, SpringLayout.WEST, geneSizeText);
		tspLayout.putConstraint(SpringLayout.SOUTH, geneSizeText, 20, SpringLayout.NORTH, geneSizeText);
		
		tspLayout.putConstraint(SpringLayout.NORTH, dataSizeLabel, 10, SpringLayout.SOUTH, geneSizeLabel);
		tspLayout.putConstraint(SpringLayout.WEST, dataSizeLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, dataSizeLabel, labelWidth, SpringLayout.WEST, dataSizeLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, dataSizeLabel, 20, SpringLayout.NORTH, dataSizeLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, dataSizeText, 10, SpringLayout.SOUTH, geneSizeText);
		tspLayout.putConstraint(SpringLayout.WEST, dataSizeText, 10, SpringLayout.EAST, dataSizeLabel);
		tspLayout.putConstraint(SpringLayout.EAST, dataSizeText, textWidth, SpringLayout.WEST, dataSizeText);
		tspLayout.putConstraint(SpringLayout.SOUTH, dataSizeText, 20, SpringLayout.NORTH, dataSizeText);
		
		tspLayout.putConstraint(SpringLayout.NORTH, crossoverLabel, 10, SpringLayout.SOUTH, dataSizeLabel);
		tspLayout.putConstraint(SpringLayout.WEST, crossoverLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, crossoverLabel, labelWidth, SpringLayout.WEST, crossoverLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, crossoverLabel, 20, SpringLayout.NORTH, crossoverLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, crossoverText, 10, SpringLayout.SOUTH, dataSizeText);
		tspLayout.putConstraint(SpringLayout.WEST, crossoverText, 10, SpringLayout.EAST, crossoverLabel);
		tspLayout.putConstraint(SpringLayout.EAST, crossoverText, textWidth, SpringLayout.WEST, crossoverText);
		tspLayout.putConstraint(SpringLayout.SOUTH, crossoverText, 20, SpringLayout.NORTH, crossoverText);
		
		tspLayout.putConstraint(SpringLayout.NORTH, mutationLabel, 10, SpringLayout.SOUTH, crossoverLabel);
		tspLayout.putConstraint(SpringLayout.WEST, mutationLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, mutationLabel, labelWidth, SpringLayout.WEST, mutationLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, mutationLabel, 20, SpringLayout.NORTH, mutationLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, mutationText, 10, SpringLayout.SOUTH, crossoverText);
		tspLayout.putConstraint(SpringLayout.WEST, mutationText, 10, SpringLayout.EAST, mutationLabel);
		tspLayout.putConstraint(SpringLayout.EAST, mutationText, textWidth, SpringLayout.WEST, mutationText);
		tspLayout.putConstraint(SpringLayout.SOUTH, mutationText, 20, SpringLayout.NORTH, mutationText);
		
		tspLayout.putConstraint(SpringLayout.NORTH, mutationOffLabel, 10, SpringLayout.SOUTH, mutationLabel);
		tspLayout.putConstraint(SpringLayout.WEST, mutationOffLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, mutationOffLabel, labelWidth, SpringLayout.WEST, mutationOffLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, mutationOffLabel, 20, SpringLayout.NORTH, mutationOffLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, mutationOffText, 10, SpringLayout.SOUTH, mutationText);
		tspLayout.putConstraint(SpringLayout.WEST, mutationOffText, 10, SpringLayout.EAST, mutationOffLabel);
		tspLayout.putConstraint(SpringLayout.EAST, mutationOffText, textWidth, SpringLayout.WEST, mutationOffText);
		tspLayout.putConstraint(SpringLayout.SOUTH, mutationOffText, 20, SpringLayout.NORTH, mutationOffText);
		
		tspLayout.putConstraint(SpringLayout.NORTH, scaleTypeAlgLabel, 10, SpringLayout.SOUTH, mutationOffLabel);
		tspLayout.putConstraint(SpringLayout.WEST, scaleTypeAlgLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, scaleTypeAlgLabel, labelWidth, SpringLayout.WEST, scaleTypeAlgLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, scaleTypeAlgLabel, 20, SpringLayout.NORTH, scaleTypeAlgLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, scaleTypeComboBox, 10, SpringLayout.SOUTH, mutationOffText);
		tspLayout.putConstraint(SpringLayout.WEST, scaleTypeComboBox, 10, SpringLayout.EAST, scaleTypeAlgLabel);
		tspLayout.putConstraint(SpringLayout.EAST, scaleTypeComboBox, textWidth, SpringLayout.WEST, scaleTypeComboBox);
		tspLayout.putConstraint(SpringLayout.SOUTH, scaleTypeComboBox, 20, SpringLayout.NORTH, scaleTypeComboBox);
		
		if (scaleTypeComboBox.getSelectedItem().toString().equals(GAScaleRatioEnum.Boltzmann.value()) == true) {
			boltzDtLabel.setVisible(true);
			boltzDtText.setVisible(true);
			boltzMinLabel.setVisible(true);
			boltzMinText.setVisible(true);
			
			tspLayout.putConstraint(SpringLayout.NORTH, boltzDtLabel, 10, SpringLayout.SOUTH, scaleTypeAlgLabel);
			tspLayout.putConstraint(SpringLayout.WEST, boltzDtLabel, 30, SpringLayout.WEST, this);
			tspLayout.putConstraint(SpringLayout.EAST, boltzDtLabel, labelWidth, SpringLayout.WEST, boltzDtLabel);
			tspLayout.putConstraint(SpringLayout.SOUTH, boltzDtLabel, 20, SpringLayout.NORTH, boltzDtLabel);
			
			tspLayout.putConstraint(SpringLayout.NORTH, boltzDtText, 10, SpringLayout.SOUTH, scaleTypeComboBox);
			tspLayout.putConstraint(SpringLayout.WEST, boltzDtText, 10, SpringLayout.EAST, boltzDtLabel);
			tspLayout.putConstraint(SpringLayout.EAST, boltzDtText, textWidth - 20, SpringLayout.WEST, boltzDtText);
			tspLayout.putConstraint(SpringLayout.SOUTH, boltzDtText, 20, SpringLayout.NORTH, boltzDtText);
			
			tspLayout.putConstraint(SpringLayout.NORTH, boltzMinLabel, 10, SpringLayout.SOUTH, boltzDtLabel);
			tspLayout.putConstraint(SpringLayout.WEST, boltzMinLabel, 30, SpringLayout.WEST, this);
			tspLayout.putConstraint(SpringLayout.EAST, boltzMinLabel, labelWidth, SpringLayout.WEST, boltzMinLabel);
			tspLayout.putConstraint(SpringLayout.SOUTH, boltzMinLabel, 20, SpringLayout.NORTH, boltzMinLabel);
			
			tspLayout.putConstraint(SpringLayout.NORTH, boltzMinText, 10, SpringLayout.SOUTH, boltzDtText);
			tspLayout.putConstraint(SpringLayout.WEST, boltzMinText, 10, SpringLayout.EAST, boltzMinLabel);
			tspLayout.putConstraint(SpringLayout.EAST, boltzMinText, textWidth - 20, SpringLayout.WEST, boltzMinText);
			tspLayout.putConstraint(SpringLayout.SOUTH, boltzMinText, 20, SpringLayout.NORTH, boltzMinText);
			
			tspLayout.putConstraint(SpringLayout.NORTH, selectionAlgLabel, 10, SpringLayout.SOUTH, boltzMinLabel);
			tspLayout.putConstraint(SpringLayout.WEST, selectionAlgLabel, 10, SpringLayout.WEST, this);
			tspLayout.putConstraint(SpringLayout.EAST, selectionAlgLabel, labelWidth, SpringLayout.WEST, selectionAlgLabel);
			tspLayout.putConstraint(SpringLayout.SOUTH, selectionAlgLabel, 20, SpringLayout.NORTH, selectionAlgLabel);
			
			tspLayout.putConstraint(SpringLayout.NORTH, selectionComboBox, 10, SpringLayout.SOUTH, boltzMinText);
			tspLayout.putConstraint(SpringLayout.WEST, selectionComboBox, 10, SpringLayout.EAST, selectionAlgLabel);
			tspLayout.putConstraint(SpringLayout.EAST, selectionComboBox, textWidth, SpringLayout.WEST, selectionComboBox);
			tspLayout.putConstraint(SpringLayout.SOUTH, selectionComboBox, 20, SpringLayout.NORTH, selectionComboBox);
		} else {
			boltzDtLabel.setVisible(false);
			boltzDtText.setVisible(false);
			boltzMinLabel.setVisible(false);
			boltzMinText.setVisible(false);
			
			tspLayout.putConstraint(SpringLayout.NORTH, selectionAlgLabel, 10, SpringLayout.SOUTH, scaleTypeAlgLabel);
			tspLayout.putConstraint(SpringLayout.WEST, selectionAlgLabel, 10, SpringLayout.WEST, this);
			tspLayout.putConstraint(SpringLayout.EAST, selectionAlgLabel, labelWidth, SpringLayout.WEST, selectionAlgLabel);
			tspLayout.putConstraint(SpringLayout.SOUTH, selectionAlgLabel, 20, SpringLayout.NORTH, selectionAlgLabel);
			
			tspLayout.putConstraint(SpringLayout.NORTH, selectionComboBox, 10, SpringLayout.SOUTH, scaleTypeComboBox);
			tspLayout.putConstraint(SpringLayout.WEST, selectionComboBox, 10, SpringLayout.EAST, selectionAlgLabel);
			tspLayout.putConstraint(SpringLayout.EAST, selectionComboBox, textWidth, SpringLayout.WEST, selectionComboBox);
			tspLayout.putConstraint(SpringLayout.SOUTH, selectionComboBox, 20, SpringLayout.NORTH, selectionComboBox);
		}
		
		tspLayout.putConstraint(SpringLayout.NORTH, nBestLabel, 10, SpringLayout.SOUTH, selectionAlgLabel);
		tspLayout.putConstraint(SpringLayout.WEST, nBestLabel, 30, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, nBestLabel, labelWidth, SpringLayout.WEST, nBestLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, nBestLabel, 20, SpringLayout.NORTH, nBestLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, nBestText, 10, SpringLayout.SOUTH, selectionComboBox);
		tspLayout.putConstraint(SpringLayout.WEST, nBestText, 10, SpringLayout.EAST, nBestLabel);
		tspLayout.putConstraint(SpringLayout.EAST, nBestText, textWidth - 20, SpringLayout.WEST, nBestText);
		tspLayout.putConstraint(SpringLayout.SOUTH, nBestText, 20, SpringLayout.NORTH, nBestText);
		
		tspLayout.putConstraint(SpringLayout.NORTH, nPerLabel, 10, SpringLayout.SOUTH, nBestLabel);
		tspLayout.putConstraint(SpringLayout.WEST, nPerLabel, 30, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, nPerLabel, labelWidth, SpringLayout.WEST, nPerLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, nPerLabel, 20, SpringLayout.NORTH, nPerLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, nPerText, 10, SpringLayout.SOUTH, nBestText);
		tspLayout.putConstraint(SpringLayout.WEST, nPerText, 10, SpringLayout.EAST, nPerLabel);
		tspLayout.putConstraint(SpringLayout.EAST, nPerText, textWidth - 20, SpringLayout.WEST, nPerText);
		tspLayout.putConstraint(SpringLayout.SOUTH, nPerText, 20, SpringLayout.NORTH, nPerText);
		
		tspLayout.putConstraint(SpringLayout.NORTH, nWorstLabel, 10, SpringLayout.SOUTH, nPerLabel);
		tspLayout.putConstraint(SpringLayout.WEST, nWorstLabel, 30, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, nWorstLabel, labelWidth, SpringLayout.WEST, nWorstLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, nWorstLabel, 20, SpringLayout.NORTH, nWorstLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, nWorstText, 10, SpringLayout.SOUTH, nPerText);
		tspLayout.putConstraint(SpringLayout.WEST, nWorstText, 10, SpringLayout.EAST, nWorstLabel);
		tspLayout.putConstraint(SpringLayout.EAST, nWorstText, textWidth - 20, SpringLayout.WEST, nWorstText);
		tspLayout.putConstraint(SpringLayout.SOUTH, nWorstText, 20, SpringLayout.NORTH, nWorstText);
		
		if (selectionComboBox.getSelectedItem().toString().equals(GASelectionEnum.Tournament.value()) == true) {
			batchLabel.setVisible(true);
			batchText.setVisible(true);
			
			tspLayout.putConstraint(SpringLayout.NORTH, batchLabel, 10, SpringLayout.SOUTH, nWorstLabel);
			tspLayout.putConstraint(SpringLayout.WEST, batchLabel, 30, SpringLayout.WEST, this);
			tspLayout.putConstraint(SpringLayout.EAST, batchLabel, labelWidth, SpringLayout.WEST, batchLabel);
			tspLayout.putConstraint(SpringLayout.SOUTH, batchLabel, 20, SpringLayout.NORTH, batchLabel);
			
			tspLayout.putConstraint(SpringLayout.NORTH, batchText, 10, SpringLayout.SOUTH, nWorstText);
			tspLayout.putConstraint(SpringLayout.WEST, batchText, 10, SpringLayout.EAST, batchLabel);
			tspLayout.putConstraint(SpringLayout.EAST, batchText, textWidth - 20, SpringLayout.WEST, batchText);
			tspLayout.putConstraint(SpringLayout.SOUTH, batchText, 20, SpringLayout.NORTH, batchText);
			
			tspLayout.putConstraint(SpringLayout.NORTH, adaptabilityLabel, 10, SpringLayout.SOUTH, batchLabel);
			tspLayout.putConstraint(SpringLayout.WEST, adaptabilityLabel, 30, SpringLayout.WEST, this);
			tspLayout.putConstraint(SpringLayout.EAST, adaptabilityLabel, labelWidth, SpringLayout.WEST, adaptabilityLabel);
			tspLayout.putConstraint(SpringLayout.SOUTH, adaptabilityLabel, 20, SpringLayout.NORTH, adaptabilityLabel);
			
			tspLayout.putConstraint(SpringLayout.NORTH, adaptabilityComboBox, 10, SpringLayout.SOUTH, batchText);
			tspLayout.putConstraint(SpringLayout.WEST, adaptabilityComboBox, 10, SpringLayout.EAST, adaptabilityLabel);
			tspLayout.putConstraint(SpringLayout.EAST, adaptabilityComboBox, textWidth - 20, SpringLayout.WEST, adaptabilityComboBox);
			tspLayout.putConstraint(SpringLayout.SOUTH, adaptabilityComboBox, 20, SpringLayout.NORTH, adaptabilityComboBox);
		} else {
			batchLabel.setVisible(false);
			batchText.setVisible(false);
			
			tspLayout.putConstraint(SpringLayout.NORTH, adaptabilityLabel, 10, SpringLayout.SOUTH, nWorstLabel);
			tspLayout.putConstraint(SpringLayout.WEST, adaptabilityLabel, 30, SpringLayout.WEST, this);
			tspLayout.putConstraint(SpringLayout.EAST, adaptabilityLabel, labelWidth, SpringLayout.WEST, adaptabilityLabel);
			tspLayout.putConstraint(SpringLayout.SOUTH, adaptabilityLabel, 20, SpringLayout.NORTH, adaptabilityLabel);
			
			tspLayout.putConstraint(SpringLayout.NORTH, adaptabilityComboBox, 10, SpringLayout.SOUTH, nWorstText);
			tspLayout.putConstraint(SpringLayout.WEST, adaptabilityComboBox, 10, SpringLayout.EAST, adaptabilityLabel);
			tspLayout.putConstraint(SpringLayout.EAST, adaptabilityComboBox, textWidth - 20, SpringLayout.WEST, adaptabilityComboBox);
			tspLayout.putConstraint(SpringLayout.SOUTH, adaptabilityComboBox, 20, SpringLayout.NORTH, adaptabilityComboBox);
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(248, 250, 241));
	}
}
