package enn.monitor.ai.ga.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import enn.monitor.ai.ga.common.TSPParameter;
import enn.monitor.ai.ga.crossover.GACrossoverEnum;
import enn.monitor.ai.ga.mutation.GAMutationEnum;
import enn.monitor.ai.ga.scaleratio.GAScaleRatioEnum;
import enn.monitor.ai.ga.selection.GASelectionEnum;

public class TSPDiaplayJPanel extends JPanel {

	private static final long serialVersionUID = 8826623476955727203L;
	
	private SpringLayout tspLayout = null;
	private int labelWidth = 0;
	private int textWidth = 0;
	
	// parameter component
	JLabel totalSizeLabel = new JLabel();
	JLabel totalSizeText = new JLabel();
	
	JLabel citiesLabel = new JLabel();
	JLabel citiesText = new JLabel();
	
	JLabel crossoverLabel = new JLabel();
	JLabel crossoverText = new JLabel();
	
	JLabel mutationLabel = new JLabel();
	JLabel mutationText = new JLabel();
	
	JLabel scaleTypeAlgLabel = new JLabel();
	JLabel scaleTypeAlgText = new JLabel();
	JLabel boltzDtLabel = new JLabel();
	JLabel boltzDtText = new JLabel();
	JLabel boltzMinLabel = new JLabel();
	JLabel boltzMinText = new JLabel();
	
	JLabel selectionAlgLabel = new JLabel();
	JLabel selectionAlgText = new JLabel();
	JLabel nBestLabel = new JLabel();
	JLabel nBestText = new JLabel();
	JLabel nPerLabel = new JLabel();
	JLabel nPerText = new JLabel();
	JLabel nWorstLabel = new JLabel();
	JLabel nWorstText = new JLabel();
	JLabel batchLabel = new JLabel();
	JLabel batchText = new JLabel();
	
	JLabel crossoverAlg = new JLabel();
	JLabel crossoverAlgText = new JLabel();
	
	JLabel mutationAlg = new JLabel();
	JLabel mutationAlgText = new JLabel();

	public TSPDiaplayJPanel(int labelWidth, int textWidth) {
		tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		this.labelWidth = labelWidth;
		this.textWidth = textWidth;
		
		totalSizeLabel.setText("规模");
		add(totalSizeLabel);
		totalSizeText.setText("");
		add(totalSizeText);
		
		citiesLabel.setText("城市数");
		add(citiesLabel);
		citiesText.setText("");
		add(citiesText);
		
		crossoverLabel.setText("杂交率");
		add(crossoverLabel);
		crossoverText.setText("");
		add(crossoverText);
		
		mutationLabel.setText("变异率");
		add(mutationLabel);
		mutationText.setText("");
		add(mutationText);
		
		scaleTypeAlgLabel.setText("变比技术");
		add(scaleTypeAlgLabel);
		scaleTypeAlgText.setText("");
		add(scaleTypeAlgText);
		
		boltzDtLabel.setText("Boltzmann DT");
		add(boltzDtLabel);
		boltzDtText.setText("");
		add(boltzDtText);
		boltzMinLabel.setText("Boltzmann MIN");
		add(boltzMinLabel);
		boltzMinText.setText("");
		add(boltzMinText);
		
		selectionAlgLabel.setText("选择算子");
		add(selectionAlgLabel);
		selectionAlgText.setText("");
		add(selectionAlgText);
		
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
		
		crossoverAlg.setText("遗传算子");
		add(crossoverAlg);
		crossoverAlgText.setText("");
		add(crossoverAlgText);
		
		mutationAlg.setText("变异算子");
		add(mutationAlg);
		mutationAlgText.setText("");
		add(mutationAlgText);
	}
	
	public void setTSPParameter(TSPParameter parameter) {
		// 规模
		tspLayout.putConstraint(SpringLayout.NORTH, totalSizeLabel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, totalSizeLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, totalSizeLabel, labelWidth, SpringLayout.WEST, totalSizeLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, totalSizeLabel, 20, SpringLayout.NORTH, totalSizeLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, totalSizeText, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, totalSizeText, 10, SpringLayout.EAST, totalSizeLabel);
		tspLayout.putConstraint(SpringLayout.EAST, totalSizeText, textWidth, SpringLayout.WEST, totalSizeText);
		tspLayout.putConstraint(SpringLayout.SOUTH, totalSizeText, 20, SpringLayout.NORTH, totalSizeText);
		
		totalSizeText.setText("" + parameter.getTotalSize());
		
		// 城市数
		tspLayout.putConstraint(SpringLayout.NORTH, citiesLabel, 10, SpringLayout.SOUTH, totalSizeLabel);
		tspLayout.putConstraint(SpringLayout.WEST, citiesLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, citiesLabel, labelWidth, SpringLayout.WEST, citiesLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, citiesLabel, 20, SpringLayout.NORTH, citiesLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, citiesText, 10, SpringLayout.SOUTH, totalSizeText);
		tspLayout.putConstraint(SpringLayout.WEST, citiesText, 10, SpringLayout.EAST, citiesLabel);
		tspLayout.putConstraint(SpringLayout.EAST, citiesText, textWidth, SpringLayout.WEST, citiesText);
		tspLayout.putConstraint(SpringLayout.SOUTH, citiesText, 20, SpringLayout.NORTH, citiesText);
		
		citiesText.setText("" + parameter.getCities());
		
		// 交换率
		tspLayout.putConstraint(SpringLayout.NORTH, crossoverLabel, 10, SpringLayout.SOUTH, citiesLabel);
		tspLayout.putConstraint(SpringLayout.WEST, crossoverLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, crossoverLabel, labelWidth, SpringLayout.WEST, crossoverLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, crossoverLabel, 20, SpringLayout.NORTH, crossoverLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, crossoverText, 10, SpringLayout.SOUTH, citiesText);
		tspLayout.putConstraint(SpringLayout.WEST, crossoverText, 10, SpringLayout.EAST, crossoverLabel);
		tspLayout.putConstraint(SpringLayout.EAST, crossoverText, textWidth, SpringLayout.WEST, crossoverText);
		tspLayout.putConstraint(SpringLayout.SOUTH, crossoverText, 20, SpringLayout.NORTH, crossoverText);
		
		crossoverText.setText("" + parameter.getCrossover());
		
		// 变换率
		tspLayout.putConstraint(SpringLayout.NORTH, mutationLabel, 10, SpringLayout.SOUTH, crossoverLabel);
		tspLayout.putConstraint(SpringLayout.WEST, mutationLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, mutationLabel, labelWidth, SpringLayout.WEST, mutationLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, mutationLabel, 20, SpringLayout.NORTH, mutationLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, mutationText, 10, SpringLayout.SOUTH, crossoverText);
		tspLayout.putConstraint(SpringLayout.WEST, mutationText, 10, SpringLayout.EAST, mutationLabel);
		tspLayout.putConstraint(SpringLayout.EAST, mutationText, textWidth, SpringLayout.WEST, mutationText);
		tspLayout.putConstraint(SpringLayout.SOUTH, mutationText, 20, SpringLayout.NORTH, mutationText);
		
		mutationText.setText("" + parameter.getMutation());
		
		// 变比技术
		tspLayout.putConstraint(SpringLayout.NORTH, scaleTypeAlgLabel, 10, SpringLayout.SOUTH, mutationLabel);
		tspLayout.putConstraint(SpringLayout.WEST, scaleTypeAlgLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, scaleTypeAlgLabel, labelWidth, SpringLayout.WEST, scaleTypeAlgLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, scaleTypeAlgLabel, 20, SpringLayout.NORTH, scaleTypeAlgLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, scaleTypeAlgText, 10, SpringLayout.SOUTH, mutationText);
		tspLayout.putConstraint(SpringLayout.WEST, scaleTypeAlgText, 10, SpringLayout.EAST, scaleTypeAlgLabel);
		tspLayout.putConstraint(SpringLayout.EAST, scaleTypeAlgText, textWidth, SpringLayout.WEST, scaleTypeAlgText);
		tspLayout.putConstraint(SpringLayout.SOUTH, scaleTypeAlgText, 20, SpringLayout.NORTH, scaleTypeAlgText);
		
		scaleTypeAlgText.setText(parameter.getScaleRatioEnum().value());
		
		if (parameter.getScaleRatioEnum().equals(GAScaleRatioEnum.Boltzmann) == true) {
			boltzDtLabel.setVisible(true);
			boltzDtText.setVisible(true);
			boltzMinLabel.setVisible(true);
			boltzMinText.setVisible(true);
			
			tspLayout.putConstraint(SpringLayout.NORTH, boltzDtLabel, 10, SpringLayout.SOUTH, scaleTypeAlgLabel);
			tspLayout.putConstraint(SpringLayout.WEST, boltzDtLabel, 30, SpringLayout.WEST, this);
			tspLayout.putConstraint(SpringLayout.EAST, boltzDtLabel, labelWidth, SpringLayout.WEST, boltzDtLabel);
			tspLayout.putConstraint(SpringLayout.SOUTH, boltzDtLabel, 20, SpringLayout.NORTH, boltzDtLabel);
			
			tspLayout.putConstraint(SpringLayout.NORTH, boltzDtText, 10, SpringLayout.SOUTH, scaleTypeAlgText);
			tspLayout.putConstraint(SpringLayout.WEST, boltzDtText, 10, SpringLayout.EAST, boltzDtLabel);
			tspLayout.putConstraint(SpringLayout.EAST, boltzDtText, textWidth - 20, SpringLayout.WEST, boltzDtText);
			tspLayout.putConstraint(SpringLayout.SOUTH, boltzDtText, 20, SpringLayout.NORTH, boltzDtText);
			
			boltzDtText.setText("" + parameter.getBoltzmanndt());
			
			tspLayout.putConstraint(SpringLayout.NORTH, boltzMinLabel, 10, SpringLayout.SOUTH, boltzDtLabel);
			tspLayout.putConstraint(SpringLayout.WEST, boltzMinLabel, 30, SpringLayout.WEST, this);
			tspLayout.putConstraint(SpringLayout.EAST, boltzMinLabel, labelWidth, SpringLayout.WEST, boltzMinLabel);
			tspLayout.putConstraint(SpringLayout.SOUTH, boltzMinLabel, 20, SpringLayout.NORTH, boltzMinLabel);
			
			tspLayout.putConstraint(SpringLayout.NORTH, boltzMinText, 10, SpringLayout.SOUTH, boltzDtText);
			tspLayout.putConstraint(SpringLayout.WEST, boltzMinText, 10, SpringLayout.EAST, boltzMinLabel);
			tspLayout.putConstraint(SpringLayout.EAST, boltzMinText, textWidth - 20, SpringLayout.WEST, boltzMinText);
			tspLayout.putConstraint(SpringLayout.SOUTH, boltzMinText, 20, SpringLayout.NORTH, boltzMinText);
			
			boltzMinText.setText("" + parameter.getBoltzmannmin());
			
			tspLayout.putConstraint(SpringLayout.NORTH, selectionAlgLabel, 10, SpringLayout.SOUTH, boltzMinLabel);
			tspLayout.putConstraint(SpringLayout.WEST, selectionAlgLabel, 10, SpringLayout.WEST, this);
			tspLayout.putConstraint(SpringLayout.EAST, selectionAlgLabel, labelWidth, SpringLayout.WEST, selectionAlgLabel);
			tspLayout.putConstraint(SpringLayout.SOUTH, selectionAlgLabel, 20, SpringLayout.NORTH, selectionAlgLabel);
			
			tspLayout.putConstraint(SpringLayout.NORTH, selectionAlgText, 10, SpringLayout.SOUTH, boltzMinText);
			tspLayout.putConstraint(SpringLayout.WEST, selectionAlgText, 10, SpringLayout.EAST, selectionAlgLabel);
			tspLayout.putConstraint(SpringLayout.EAST, selectionAlgText, textWidth, SpringLayout.WEST, selectionAlgText);
			tspLayout.putConstraint(SpringLayout.SOUTH, selectionAlgText, 20, SpringLayout.NORTH, selectionAlgText);
		} else {
			boltzDtLabel.setVisible(false);
			boltzDtText.setVisible(false);
			boltzMinLabel.setVisible(false);
			boltzMinText.setVisible(false);
			
			tspLayout.putConstraint(SpringLayout.NORTH, selectionAlgLabel, 10, SpringLayout.SOUTH, scaleTypeAlgLabel);
			tspLayout.putConstraint(SpringLayout.WEST, selectionAlgLabel, 10, SpringLayout.WEST, this);
			tspLayout.putConstraint(SpringLayout.EAST, selectionAlgLabel, labelWidth, SpringLayout.WEST, selectionAlgLabel);
			tspLayout.putConstraint(SpringLayout.SOUTH, selectionAlgLabel, 20, SpringLayout.NORTH, selectionAlgLabel);
			
			tspLayout.putConstraint(SpringLayout.NORTH, selectionAlgText, 10, SpringLayout.SOUTH, scaleTypeAlgText);
			tspLayout.putConstraint(SpringLayout.WEST, selectionAlgText, 10, SpringLayout.EAST, selectionAlgLabel);
			tspLayout.putConstraint(SpringLayout.EAST, selectionAlgText, textWidth, SpringLayout.WEST, selectionAlgText);
			tspLayout.putConstraint(SpringLayout.SOUTH, selectionAlgText, 20, SpringLayout.NORTH, selectionAlgText);
		}
		
		selectionAlgText.setText(parameter.getSelectionEnum().value());
		
		tspLayout.putConstraint(SpringLayout.NORTH, nBestLabel, 10, SpringLayout.SOUTH, selectionAlgLabel);
		tspLayout.putConstraint(SpringLayout.WEST, nBestLabel, 30, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, nBestLabel, labelWidth, SpringLayout.WEST, nBestLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, nBestLabel, 20, SpringLayout.NORTH, nBestLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, nBestText, 10, SpringLayout.SOUTH, selectionAlgText);
		tspLayout.putConstraint(SpringLayout.WEST, nBestText, 10, SpringLayout.EAST, nBestLabel);
		tspLayout.putConstraint(SpringLayout.EAST, nBestText, textWidth - 20, SpringLayout.WEST, nBestText);
		tspLayout.putConstraint(SpringLayout.SOUTH, nBestText, 20, SpringLayout.NORTH, nBestText);
		
		nBestText.setText("" + parameter.getnBest());
		
		tspLayout.putConstraint(SpringLayout.NORTH, nPerLabel, 10, SpringLayout.SOUTH, nBestLabel);
		tspLayout.putConstraint(SpringLayout.WEST, nPerLabel, 30, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, nPerLabel, labelWidth, SpringLayout.WEST, nPerLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, nPerLabel, 20, SpringLayout.NORTH, nPerLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, nPerText, 10, SpringLayout.SOUTH, nBestText);
		tspLayout.putConstraint(SpringLayout.WEST, nPerText, 10, SpringLayout.EAST, nPerLabel);
		tspLayout.putConstraint(SpringLayout.EAST, nPerText, textWidth - 20, SpringLayout.WEST, nPerText);
		tspLayout.putConstraint(SpringLayout.SOUTH, nPerText, 20, SpringLayout.NORTH, nPerText);
		
		nPerText.setText("" + parameter.getnPer());
		
		tspLayout.putConstraint(SpringLayout.NORTH, nWorstLabel, 10, SpringLayout.SOUTH, nPerLabel);
		tspLayout.putConstraint(SpringLayout.WEST, nWorstLabel, 30, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, nWorstLabel, labelWidth, SpringLayout.WEST, nWorstLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, nWorstLabel, 20, SpringLayout.NORTH, nWorstLabel);
		
		tspLayout.putConstraint(SpringLayout.NORTH, nWorstText, 10, SpringLayout.SOUTH, nPerText);
		tspLayout.putConstraint(SpringLayout.WEST, nWorstText, 10, SpringLayout.EAST, nWorstLabel);
		tspLayout.putConstraint(SpringLayout.EAST, nWorstText, textWidth - 20, SpringLayout.WEST, nWorstText);
		tspLayout.putConstraint(SpringLayout.SOUTH, nWorstText, 20, SpringLayout.NORTH, nWorstText);
		
		nWorstText.setText("" + parameter.getnWorst());
		
		if (parameter.getSelectionEnum().equals(GASelectionEnum.Tournament) == true) {
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
			
			batchText.setText("" + parameter.getBatchNum());
			
			tspLayout.putConstraint(SpringLayout.NORTH, crossoverAlg, 10, SpringLayout.SOUTH, batchLabel);
			tspLayout.putConstraint(SpringLayout.WEST, crossoverAlg, 10, SpringLayout.WEST, this);
			tspLayout.putConstraint(SpringLayout.EAST, crossoverAlg, labelWidth, SpringLayout.WEST, crossoverAlg);
			tspLayout.putConstraint(SpringLayout.SOUTH, crossoverAlg, 20, SpringLayout.NORTH, crossoverAlg);
			
			tspLayout.putConstraint(SpringLayout.NORTH, crossoverAlgText, 10, SpringLayout.SOUTH, batchText);
			tspLayout.putConstraint(SpringLayout.WEST, crossoverAlgText, 10, SpringLayout.EAST, crossoverAlg);
			tspLayout.putConstraint(SpringLayout.EAST, crossoverAlgText, textWidth, SpringLayout.WEST, crossoverAlgText);
			tspLayout.putConstraint(SpringLayout.SOUTH, crossoverAlgText, 20, SpringLayout.NORTH, crossoverAlgText);
		} else {
			batchLabel.setVisible(false);
			batchText.setVisible(false);
			
			tspLayout.putConstraint(SpringLayout.NORTH, crossoverAlg, 10, SpringLayout.SOUTH, nWorstLabel);
			tspLayout.putConstraint(SpringLayout.WEST, crossoverAlg, 10, SpringLayout.WEST, this);
			tspLayout.putConstraint(SpringLayout.EAST, crossoverAlg, labelWidth, SpringLayout.WEST, crossoverAlg);
			tspLayout.putConstraint(SpringLayout.SOUTH, crossoverAlg, 20, SpringLayout.NORTH, crossoverAlg);
			
			tspLayout.putConstraint(SpringLayout.NORTH, crossoverAlgText, 10, SpringLayout.SOUTH, nWorstText);
			tspLayout.putConstraint(SpringLayout.WEST, crossoverAlgText, 10, SpringLayout.EAST, crossoverAlg);
			tspLayout.putConstraint(SpringLayout.EAST, crossoverAlgText, textWidth, SpringLayout.WEST, crossoverAlgText);
			tspLayout.putConstraint(SpringLayout.SOUTH, crossoverAlgText, 20, SpringLayout.NORTH, crossoverAlgText);
		}
		
		crossoverAlgText.setText("" + parameter.getCrossover());
		
		// 变异算子
		tspLayout.putConstraint(SpringLayout.NORTH, mutationAlg, 10, SpringLayout.SOUTH, crossoverAlg);
		tspLayout.putConstraint(SpringLayout.WEST, mutationAlg, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, mutationAlg, labelWidth, SpringLayout.WEST, mutationAlg);
		tspLayout.putConstraint(SpringLayout.SOUTH, mutationAlg, 20, SpringLayout.NORTH, mutationAlg);
		
		tspLayout.putConstraint(SpringLayout.NORTH, mutationAlgText, 10, SpringLayout.SOUTH, crossoverAlgText);
		tspLayout.putConstraint(SpringLayout.WEST, mutationAlgText, 10, SpringLayout.EAST, mutationAlg);
		tspLayout.putConstraint(SpringLayout.EAST, mutationAlgText, textWidth, SpringLayout.WEST, mutationAlgText);
		tspLayout.putConstraint(SpringLayout.SOUTH, mutationAlgText, 20, SpringLayout.NORTH, mutationAlgText);
		
		mutationAlgText.setText("" + parameter.getMutation());
	}
}
