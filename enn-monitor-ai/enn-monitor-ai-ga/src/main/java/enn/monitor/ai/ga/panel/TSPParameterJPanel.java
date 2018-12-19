package enn.monitor.ai.ga.panel;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import enn.monitor.ai.ga.common.TSPParameter;
import enn.monitor.framework.ai.ga.crossover.GACrossoverEnum;
import enn.monitor.framework.ai.ga.mutation.GAMutationEnum;
import enn.monitor.framework.ai.ga.scaleratio.GAScaleRatioEnum;
import enn.monitor.framework.ai.ga.selection.GASelectionEnum;

public class TSPParameterJPanel extends JPanel {

	private static final long serialVersionUID = -8183053612692838592L;
	
	// parameter component
	JLabel totalSizeLabel = new JLabel();
	JTextField totalSizeText = new JTextField();
	
	JLabel citiesLabel = new JLabel();
	JTextField citiesText = new JTextField();
	
	JLabel crossoverLabel = new JLabel();
	JTextField crossoverText = new JTextField();
	
	JLabel mutationLabel = new JLabel();
	JTextField mutationText = new JTextField();
	
	JLabel scaleTypeAlgLabel = new JLabel();
	JComboBox scaleTypeComboBox = null;
	JLabel boltzDtLabel = new JLabel();
	JTextField boltzDtText = new JTextField();
	JLabel boltzMinLabel = new JLabel();
	JTextField boltzMinText = new JTextField();
	
	JLabel selectionAlgLabel = new JLabel();
	JComboBox selectionComboBox = null;
	JLabel nBestLabel = new JLabel();
	JTextField nBestText = new JTextField();
	JLabel nPerLabel = new JLabel();
	JTextField nPerText = new JTextField();
	JLabel nWorstLabel = new JLabel();
	JTextField nWorstText = new JTextField();
	JLabel batchLabel = new JLabel();
	JTextField batchText = new JTextField();
	
	JLabel crossoverAlg = new JLabel();
	JComboBox crossoverComboBox = null;
	
	JLabel mutationAlg = new JLabel();
	JComboBox mutationComboBox = null;
	
	public TSPParameterJPanel(int labelWidth, int textWidth) {
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		// 规模
		totalSizeLabel.setText("规模");
		add(totalSizeLabel);
		
		totalSizeText.setText("256");
		add(totalSizeText);
		
		// 城市
		citiesLabel.setText("城市数");
		add(citiesLabel);
		
		citiesText.setText("256");
		add(citiesText);
		
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
		
		// 遗传算子
		crossoverAlg.setText("遗传算子");
		add(crossoverAlg);
		
		List<String> crossoverStr = new ArrayList<String>();
		for (GACrossoverEnum entry : GACrossoverEnum.values()) {
			crossoverStr.add(entry.value());
		}
		crossoverComboBox = new JComboBox(crossoverStr.toArray());
		add(crossoverComboBox);

		// 变异算子
		mutationAlg.setText("变异算子");
		add(mutationAlg);
		
		List<String> mutationStr = new ArrayList<String>();
		for (GAMutationEnum entry : GAMutationEnum.values()) {
			mutationStr.add(entry.value());
		}
		mutationComboBox = new JComboBox(mutationStr.toArray());
		add(mutationComboBox);

		displayComponent(tspLayout, labelWidth, textWidth);
	}
	
	public TSPParameter getTSPParameter() {
		TSPParameter parameter = new TSPParameter();
		
		parameter.setTotalSize(Integer.parseInt(totalSizeText.getText()));
		parameter.setCities(Integer.parseInt(citiesText.getText()));
		parameter.setCrossover(Double.parseDouble(crossoverText.getText()));
		parameter.setMutation(Double.parseDouble(mutationText.getText()));
		
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
		
		if (crossoverComboBox.getSelectedItem().toString().equals(GACrossoverEnum.PMX.value()) == true) {
			parameter.setCrossoverEnum(GACrossoverEnum.PMX);
		} else if (crossoverComboBox.getSelectedItem().toString().equals(GACrossoverEnum.OBX.value()) == true) {
			parameter.setCrossoverEnum(GACrossoverEnum.OBX);
		} else if (crossoverComboBox.getSelectedItem().toString().equals(GACrossoverEnum.PBX.value()) == true) {
			parameter.setCrossoverEnum(GACrossoverEnum.PBX);
		}
		
		if (mutationComboBox.getSelectedItem().toString().equals(GAMutationEnum.EM.value()) == true) {
			parameter.setMutationEnum(GAMutationEnum.EM);
		} else if (mutationComboBox.getSelectedItem().toString().equals(GAMutationEnum.SM.value()) == true) {
			parameter.setMutationEnum(GAMutationEnum.SM);
		} else if (mutationComboBox.getSelectedItem().toString().equals(GAMutationEnum.IM.value()) == true) {
			parameter.setMutationEnum(GAMutationEnum.IM);
		} else if (mutationComboBox.getSelectedItem().toString().equals(GAMutationEnum.DM.value()) == true) {
			parameter.setMutationEnum(GAMutationEnum.DM);
		} else if (mutationComboBox.getSelectedItem().toString().equals(GAMutationEnum.RM1.value()) == true) {
			parameter.setMutationEnum(GAMutationEnum.RM1);
		} else if (mutationComboBox.getSelectedItem().toString().equals(GAMutationEnum.RM2.value()) == true) {
			parameter.setMutationEnum(GAMutationEnum.RM2);
		} 
		
		return parameter;
	}
	
	private void displayComponent(SpringLayout tspLayout, int labelWidth, int textWidth) {
		tspLayout.putConstraint(SpringLayout.NORTH, totalSizeLabel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, totalSizeLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, totalSizeLabel, labelWidth, SpringLayout.WEST, totalSizeLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, totalSizeLabel, 20, SpringLayout.NORTH, totalSizeLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, totalSizeText, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, totalSizeText, 10, SpringLayout.EAST, totalSizeLabel);
		tspLayout.putConstraint(SpringLayout.EAST, totalSizeText, textWidth, SpringLayout.WEST, totalSizeText);
		tspLayout.putConstraint(SpringLayout.SOUTH, totalSizeText, 20, SpringLayout.NORTH, totalSizeText);
		
		tspLayout.putConstraint(SpringLayout.NORTH, citiesLabel, 10, SpringLayout.SOUTH, totalSizeLabel);
		tspLayout.putConstraint(SpringLayout.WEST, citiesLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, citiesLabel, labelWidth, SpringLayout.WEST, citiesLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, citiesLabel, 20, SpringLayout.NORTH, citiesLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, citiesText, 10, SpringLayout.SOUTH, totalSizeText);
		tspLayout.putConstraint(SpringLayout.WEST, citiesText, 10, SpringLayout.EAST, citiesLabel);
		tspLayout.putConstraint(SpringLayout.EAST, citiesText, textWidth, SpringLayout.WEST, citiesText);
		tspLayout.putConstraint(SpringLayout.SOUTH, citiesText, 20, SpringLayout.NORTH, citiesText);
		
		tspLayout.putConstraint(SpringLayout.NORTH, crossoverLabel, 10, SpringLayout.SOUTH, citiesLabel);
		tspLayout.putConstraint(SpringLayout.WEST, crossoverLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, crossoverLabel, labelWidth, SpringLayout.WEST, crossoverLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, crossoverLabel, 20, SpringLayout.NORTH, crossoverLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, crossoverText, 10, SpringLayout.SOUTH, citiesText);
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
		
		tspLayout.putConstraint(SpringLayout.NORTH, scaleTypeAlgLabel, 10, SpringLayout.SOUTH, mutationLabel);
		tspLayout.putConstraint(SpringLayout.WEST, scaleTypeAlgLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, scaleTypeAlgLabel, labelWidth, SpringLayout.WEST, scaleTypeAlgLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, scaleTypeAlgLabel, 20, SpringLayout.NORTH, scaleTypeAlgLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, scaleTypeComboBox, 10, SpringLayout.SOUTH, mutationText);
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
			
			tspLayout.putConstraint(SpringLayout.NORTH, crossoverAlg, 10, SpringLayout.SOUTH, batchLabel);
			tspLayout.putConstraint(SpringLayout.WEST, crossoverAlg, 10, SpringLayout.WEST, this);
			tspLayout.putConstraint(SpringLayout.EAST, crossoverAlg, labelWidth, SpringLayout.WEST, crossoverAlg);
			tspLayout.putConstraint(SpringLayout.SOUTH, crossoverAlg, 20, SpringLayout.NORTH, crossoverAlg);
			
			tspLayout.putConstraint(SpringLayout.NORTH, crossoverComboBox, 10, SpringLayout.SOUTH, batchText);
			tspLayout.putConstraint(SpringLayout.WEST, crossoverComboBox, 10, SpringLayout.EAST, crossoverAlg);
			tspLayout.putConstraint(SpringLayout.EAST, crossoverComboBox, textWidth, SpringLayout.WEST, crossoverComboBox);
			tspLayout.putConstraint(SpringLayout.SOUTH, crossoverComboBox, 20, SpringLayout.NORTH, crossoverComboBox);
		} else {
			batchLabel.setVisible(false);
			batchText.setVisible(false);
			
			tspLayout.putConstraint(SpringLayout.NORTH, crossoverAlg, 10, SpringLayout.SOUTH, nWorstLabel);
			tspLayout.putConstraint(SpringLayout.WEST, crossoverAlg, 10, SpringLayout.WEST, this);
			tspLayout.putConstraint(SpringLayout.EAST, crossoverAlg, labelWidth, SpringLayout.WEST, crossoverAlg);
			tspLayout.putConstraint(SpringLayout.SOUTH, crossoverAlg, 20, SpringLayout.NORTH, crossoverAlg);
			
			tspLayout.putConstraint(SpringLayout.NORTH, crossoverComboBox, 10, SpringLayout.SOUTH, nWorstText);
			tspLayout.putConstraint(SpringLayout.WEST, crossoverComboBox, 10, SpringLayout.EAST, crossoverAlg);
			tspLayout.putConstraint(SpringLayout.EAST, crossoverComboBox, textWidth, SpringLayout.WEST, crossoverComboBox);
			tspLayout.putConstraint(SpringLayout.SOUTH, crossoverComboBox, 20, SpringLayout.NORTH, crossoverComboBox);
		}
		
		tspLayout.putConstraint(SpringLayout.NORTH, mutationAlg, 10, SpringLayout.SOUTH, crossoverAlg);
		tspLayout.putConstraint(SpringLayout.WEST, mutationAlg, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, mutationAlg, labelWidth, SpringLayout.WEST, mutationAlg);
		tspLayout.putConstraint(SpringLayout.SOUTH, mutationAlg, 20, SpringLayout.NORTH, mutationAlg);
		
		tspLayout.putConstraint(SpringLayout.NORTH, mutationComboBox, 10, SpringLayout.SOUTH, crossoverComboBox);
		tspLayout.putConstraint(SpringLayout.WEST, mutationComboBox, 10, SpringLayout.EAST, mutationAlg);
		tspLayout.putConstraint(SpringLayout.EAST, mutationComboBox, textWidth, SpringLayout.WEST, mutationComboBox);
		tspLayout.putConstraint(SpringLayout.SOUTH, mutationComboBox, 20, SpringLayout.NORTH, mutationComboBox);
	}

}
