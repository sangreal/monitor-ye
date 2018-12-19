package enn.monitor.ai.ga.nn.panel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import enn.monitor.ai.ga.nn.common.NNResult;

public class NNResultJPanel extends JPanel {

	private static final long serialVersionUID = -1396329941639001597L;
	
	JLabel generationLabel = new JLabel();
	JLabel generationText = new JLabel();
	
	JLabel resultLabel = new JLabel();
	JLabel resultText = new JLabel();
	
	JLabel shortestRouteLable = new JLabel();
	JLabel shortestRouteText = new JLabel();
	
	JLabel longestRouteLabel = new JLabel();
	JLabel longestRouteText = new JLabel();
	
	JLabel totalFeeLabel = new JLabel();
	JLabel totalFeeText = new JLabel();
	
	public NNResultJPanel(int labelWidth, int textWidth) {
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		generationLabel.setText("Generation");
		add(generationLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, generationLabel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, generationLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, generationLabel, labelWidth, SpringLayout.WEST, generationLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, generationLabel, 20, SpringLayout.NORTH, generationLabel);
		
		generationText.setText("0");
		add(generationText);
		tspLayout.putConstraint(SpringLayout.NORTH, generationText, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, generationText, 10, SpringLayout.EAST, generationLabel);
		tspLayout.putConstraint(SpringLayout.EAST, generationText, textWidth, SpringLayout.WEST, generationText);
		tspLayout.putConstraint(SpringLayout.SOUTH, generationText, 20, SpringLayout.NORTH, generationText);
		
		resultLabel.setText("Result");
		add(resultLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, resultLabel, 10, SpringLayout.SOUTH, generationLabel);
		tspLayout.putConstraint(SpringLayout.WEST, resultLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, resultLabel, labelWidth, SpringLayout.WEST, resultLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, resultLabel, 20, SpringLayout.NORTH, resultLabel);
		
		resultText.setText("0");
		add(resultText);
		tspLayout.putConstraint(SpringLayout.NORTH, resultText, 10, SpringLayout.SOUTH, generationText);
		tspLayout.putConstraint(SpringLayout.WEST, resultText, 10, SpringLayout.EAST, resultLabel);
		tspLayout.putConstraint(SpringLayout.EAST, resultText, textWidth, SpringLayout.WEST, resultText);
		tspLayout.putConstraint(SpringLayout.SOUTH, resultText, 20, SpringLayout.NORTH, resultText);
		
		shortestRouteLable.setText("ShortestRoute");
		add(shortestRouteLable);
		tspLayout.putConstraint(SpringLayout.NORTH, shortestRouteLable, 10, SpringLayout.SOUTH, resultLabel);
		tspLayout.putConstraint(SpringLayout.WEST, shortestRouteLable, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, shortestRouteLable, labelWidth, SpringLayout.WEST, shortestRouteLable);
		tspLayout.putConstraint(SpringLayout.SOUTH, shortestRouteLable, 20, SpringLayout.NORTH, shortestRouteLable);
		
		shortestRouteText.setText("0");
		add(shortestRouteText);
		tspLayout.putConstraint(SpringLayout.NORTH, shortestRouteText, 10, SpringLayout.SOUTH, resultText);
		tspLayout.putConstraint(SpringLayout.WEST, shortestRouteText, 10, SpringLayout.EAST, shortestRouteLable);
		tspLayout.putConstraint(SpringLayout.EAST, shortestRouteText, textWidth, SpringLayout.WEST, shortestRouteText);
		tspLayout.putConstraint(SpringLayout.SOUTH, shortestRouteText, 20, SpringLayout.NORTH, shortestRouteText);
		
		longestRouteLabel.setText("LongestRoute");
		add(longestRouteLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, longestRouteLabel, 10, SpringLayout.SOUTH, shortestRouteLable);
		tspLayout.putConstraint(SpringLayout.WEST, longestRouteLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, longestRouteLabel, labelWidth, SpringLayout.WEST, longestRouteLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, longestRouteLabel, 20, SpringLayout.NORTH, longestRouteLabel);
		
		longestRouteText.setText("0");
		add(longestRouteText);
		tspLayout.putConstraint(SpringLayout.NORTH, longestRouteText, 10, SpringLayout.SOUTH, shortestRouteText);
		tspLayout.putConstraint(SpringLayout.WEST, longestRouteText, 10, SpringLayout.EAST, longestRouteLabel);
		tspLayout.putConstraint(SpringLayout.EAST, longestRouteText, textWidth, SpringLayout.WEST, longestRouteText);
		tspLayout.putConstraint(SpringLayout.SOUTH, longestRouteText, 20, SpringLayout.NORTH, longestRouteText);
		
		totalFeeLabel.setText("TotalFee");
		add(totalFeeLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, totalFeeLabel, 10, SpringLayout.SOUTH, longestRouteLabel);
		tspLayout.putConstraint(SpringLayout.WEST, totalFeeLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, totalFeeLabel, labelWidth, SpringLayout.WEST, totalFeeLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, totalFeeLabel, 20, SpringLayout.NORTH, totalFeeLabel);
		
		totalFeeText.setText("0");
		add(totalFeeText);
		tspLayout.putConstraint(SpringLayout.NORTH, totalFeeText, 10, SpringLayout.SOUTH, longestRouteText);
		tspLayout.putConstraint(SpringLayout.WEST, totalFeeText, 10, SpringLayout.EAST, totalFeeLabel);
		tspLayout.putConstraint(SpringLayout.EAST, totalFeeText, textWidth, SpringLayout.WEST, totalFeeText);
		tspLayout.putConstraint(SpringLayout.SOUTH, totalFeeText, 20, SpringLayout.NORTH, totalFeeText);
	}
	
	public void setTSPResult(NNResult tspResult, int radius) {
		generationText.setText("" + tspResult.getGeneration());
		resultText.setText("" + tspResult.getResult());
		shortestRouteText.setText("" + tspResult.getShortestRoute() * radius);
		longestRouteText.setText("" + tspResult.getLongestRoute() * radius);
		totalFeeText.setText("" + tspResult.getTotalFee() * radius);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(248, 250, 241));
	}
}
