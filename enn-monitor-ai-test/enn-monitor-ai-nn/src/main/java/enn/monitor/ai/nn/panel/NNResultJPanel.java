package enn.monitor.ai.nn.panel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import enn.monitor.ai.nn.common.NNResult;

public class NNResultJPanel extends JPanel {

	private static final long serialVersionUID = -1396329941639001597L;
	
	JLabel nnModeLable = new JLabel();
	JLabel nnModeText = new JLabel();
	
	JLabel generationLabel = new JLabel();
	JLabel generationText = new JLabel();
	
	JLabel errorLabel = new JLabel();
	JLabel errorText = new JLabel();
	
	JLabel recognizeLabel = new JLabel();
	JLabel recognizeText = new JLabel();
	
	JLabel matchToleranceLabel = new JLabel();
	JLabel matchToleranceText = new JLabel();

	
	public NNResultJPanel(int labelWidth, int textWidth) {
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		nnModeLable.setText("Current Mode");
		add(nnModeLable);
		tspLayout.putConstraint(SpringLayout.NORTH, nnModeLable, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, nnModeLable, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, nnModeLable, labelWidth, SpringLayout.WEST, nnModeLable);
		tspLayout.putConstraint(SpringLayout.SOUTH, nnModeLable, 20, SpringLayout.NORTH, nnModeLable);
		
		nnModeText.setText("");
		add(nnModeText);
		tspLayout.putConstraint(SpringLayout.NORTH, nnModeText, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, nnModeText, 10, SpringLayout.EAST, nnModeLable);
		tspLayout.putConstraint(SpringLayout.EAST, nnModeText, textWidth, SpringLayout.WEST, nnModeText);
		tspLayout.putConstraint(SpringLayout.SOUTH, nnModeText, 20, SpringLayout.NORTH, nnModeText);
		
		generationLabel.setText("Generation");
		add(generationLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, generationLabel, 10, SpringLayout.SOUTH, nnModeLable);
		tspLayout.putConstraint(SpringLayout.WEST, generationLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, generationLabel, labelWidth, SpringLayout.WEST, generationLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, generationLabel, 20, SpringLayout.NORTH, generationLabel);
		
		generationText.setText("0");
		add(generationText);
		tspLayout.putConstraint(SpringLayout.NORTH, generationText, 10, SpringLayout.SOUTH, nnModeText);
		tspLayout.putConstraint(SpringLayout.WEST, generationText, 10, SpringLayout.EAST, generationLabel);
		tspLayout.putConstraint(SpringLayout.EAST, generationText, textWidth, SpringLayout.WEST, generationText);
		tspLayout.putConstraint(SpringLayout.SOUTH, generationText, 20, SpringLayout.NORTH, generationText);
		
		errorLabel.setText("Error");
		add(errorLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, errorLabel, 10, SpringLayout.SOUTH, generationLabel);
		tspLayout.putConstraint(SpringLayout.WEST, errorLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, errorLabel, labelWidth, SpringLayout.WEST, errorLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, errorLabel, 20, SpringLayout.NORTH, errorLabel);
		
		errorText.setText("0");
		add(errorText);
		tspLayout.putConstraint(SpringLayout.NORTH, errorText, 10, SpringLayout.SOUTH, generationText);
		tspLayout.putConstraint(SpringLayout.WEST, errorText, 10, SpringLayout.EAST, errorLabel);
		tspLayout.putConstraint(SpringLayout.EAST, errorText, textWidth, SpringLayout.WEST, errorText);
		tspLayout.putConstraint(SpringLayout.SOUTH, errorText, 20, SpringLayout.NORTH, errorText);
		
		recognizeLabel.setText("Result");
		add(recognizeLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, recognizeLabel, 10, SpringLayout.SOUTH, errorLabel);
		tspLayout.putConstraint(SpringLayout.WEST, recognizeLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, recognizeLabel, labelWidth, SpringLayout.WEST, recognizeLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, recognizeLabel, 20, SpringLayout.NORTH, recognizeLabel);
		
		recognizeText.setText("");
		add(recognizeText);
		tspLayout.putConstraint(SpringLayout.NORTH, recognizeText, 10, SpringLayout.SOUTH, errorText);
		tspLayout.putConstraint(SpringLayout.WEST, recognizeText, 10, SpringLayout.EAST, recognizeLabel);
		tspLayout.putConstraint(SpringLayout.EAST, recognizeText, textWidth, SpringLayout.WEST, recognizeText);
		tspLayout.putConstraint(SpringLayout.SOUTH, recognizeText, 20, SpringLayout.NORTH, recognizeText);
		
		matchToleranceLabel.setText("Match Tolerance");
		add(matchToleranceLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, matchToleranceLabel, 10, SpringLayout.SOUTH, recognizeLabel);
		tspLayout.putConstraint(SpringLayout.WEST, matchToleranceLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, matchToleranceLabel, labelWidth, SpringLayout.WEST, matchToleranceLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, matchToleranceLabel, 20, SpringLayout.NORTH, matchToleranceLabel);
		
		matchToleranceText.setText("");
		add(matchToleranceText);
		tspLayout.putConstraint(SpringLayout.NORTH, matchToleranceText, 10, SpringLayout.SOUTH, recognizeText);
		tspLayout.putConstraint(SpringLayout.WEST, matchToleranceText, 10, SpringLayout.EAST, matchToleranceLabel);
		tspLayout.putConstraint(SpringLayout.EAST, matchToleranceText, textWidth, SpringLayout.WEST, matchToleranceText);
		tspLayout.putConstraint(SpringLayout.SOUTH, matchToleranceText, 20, SpringLayout.NORTH, matchToleranceText);

	}
	
	public void setResult(NNResult nnResult) {
		if (nnResult.getNnModeEnum() != null) {
			nnModeText.setText("" + nnResult.getNnModeEnum().name());
		}
		
		if (nnResult.getGeneration() >= 0) {
			generationText.setText("" + nnResult.getGeneration());
		}
		
		if (nnResult.getError() >= 0.0) {
			errorText.setText("" + nnResult.getError());
		}
		
		if (nnResult.getRecognize() != null) {
			recognizeText.setText("" + nnResult.getRecognize());
		}
		
		if (nnResult.getMatchTolerance() >= 0) {
			matchToleranceText.setText("" + nnResult.getMatchTolerance());
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(248, 250, 241));
	}
}
