package enn.monitor.log.data.template.extraction;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataTemplateExtractionJPanel extends JPanel {

	private static final long serialVersionUID = 5228664000952068693L;
	
	private static final Logger logger = LogManager.getLogger();
	
	private JLabel moduleLabel = new JLabel();
	
	// index
	private JLabel indexLabel = new JLabel();
	private JTextField indexText = new JTextField();
	
	private JLabel tfratioLabel = new JLabel();
	private JTextField tfratioText = new JTextField();
	
	private JLabel similarLabel = new JLabel();
	private JTextField similarText = new JTextField();
	
	private JButton runButton = new JButton();
	
	private JLabel resultLabel = new JLabel();
	private JLabel resultText = new JLabel();
	
	private int labelWidth = 120;
	private int textWidth = 100;
	private int buttonWidth = 80;
	
	private boolean isRun = false;
	
	public DataTemplateExtractionJPanel(DataTemplateExtractionFSM dataTemplateExtractionFSM) {
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		moduleLabel.setText("模板提取");
		add(moduleLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, moduleLabel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, moduleLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, moduleLabel, labelWidth * 3, SpringLayout.WEST, moduleLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, moduleLabel, 20, SpringLayout.NORTH, moduleLabel);
		
		// index
		indexLabel.setText("Index");
		add(indexLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, indexLabel, 10, SpringLayout.SOUTH, moduleLabel);
		tspLayout.putConstraint(SpringLayout.WEST, indexLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, indexLabel, labelWidth, SpringLayout.WEST, indexLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, indexLabel, 20, SpringLayout.NORTH, indexLabel);
		
		add(indexText);
		tspLayout.putConstraint(SpringLayout.NORTH, indexText, 10, SpringLayout.SOUTH, moduleLabel);
		tspLayout.putConstraint(SpringLayout.WEST, indexText, 10, SpringLayout.EAST, indexLabel);
		tspLayout.putConstraint(SpringLayout.EAST, indexText, -10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, indexText, 20, SpringLayout.NORTH, indexText);
		
		// tfratio
		tfratioLabel.setText("tfRatio");
		add(tfratioLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, tfratioLabel, 10, SpringLayout.SOUTH, indexLabel);
		tspLayout.putConstraint(SpringLayout.WEST, tfratioLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, tfratioLabel, labelWidth, SpringLayout.WEST, tfratioLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, tfratioLabel, 20, SpringLayout.NORTH, tfratioLabel);
		
		add(tfratioText);
		tspLayout.putConstraint(SpringLayout.NORTH, tfratioText, 10, SpringLayout.SOUTH, indexLabel);
		tspLayout.putConstraint(SpringLayout.WEST, tfratioText, 10, SpringLayout.EAST, tfratioLabel);
		tspLayout.putConstraint(SpringLayout.EAST, tfratioText, textWidth, SpringLayout.WEST, tfratioText);
		tspLayout.putConstraint(SpringLayout.SOUTH, tfratioText, 20, SpringLayout.NORTH, tfratioText);
		
		// similarratio
		similarLabel.setText("similarRatio");
		add(similarLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, similarLabel, 10, SpringLayout.SOUTH, indexLabel);
		tspLayout.putConstraint(SpringLayout.WEST, similarLabel, 10, SpringLayout.EAST, tfratioText);
		tspLayout.putConstraint(SpringLayout.EAST, similarLabel, labelWidth, SpringLayout.WEST, similarLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, similarLabel, 20, SpringLayout.NORTH, similarLabel);
		
		add(similarText);
		tspLayout.putConstraint(SpringLayout.NORTH, similarText, 10, SpringLayout.SOUTH, indexLabel);
		tspLayout.putConstraint(SpringLayout.WEST, similarText, 10, SpringLayout.EAST, similarLabel);
		tspLayout.putConstraint(SpringLayout.EAST, similarText, textWidth, SpringLayout.WEST, similarText);
		tspLayout.putConstraint(SpringLayout.SOUTH, similarText, 20, SpringLayout.NORTH, similarText);
		
		runButton.setText("Run");
		add(runButton);
		tspLayout.putConstraint(SpringLayout.NORTH, runButton, 10, SpringLayout.SOUTH, indexLabel);
		tspLayout.putConstraint(SpringLayout.WEST, runButton, 10, SpringLayout.EAST, similarText);
		tspLayout.putConstraint(SpringLayout.EAST, runButton, buttonWidth, SpringLayout.WEST, runButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, runButton, 20, SpringLayout.NORTH, runButton);
		runButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (isRun == false) {
						dataTemplateExtractionFSM.addEvent(new DataTemplateExtractionEvent(getDataTemplateExtractionParameter()));
						runButton.setText("Stop");
						isRun = true;
					}
				} catch (Exception a) {
					logger.error(a.getMessage(), a);
				}
			}
			
		});
		
		resultLabel.setText("Result");
		add(resultLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, resultLabel, 10, SpringLayout.SOUTH, runButton);
		tspLayout.putConstraint(SpringLayout.WEST, resultLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, resultLabel, labelWidth * 2, SpringLayout.WEST, resultLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, resultLabel, 20, SpringLayout.NORTH, resultLabel);
		
		add(resultText);
		tspLayout.putConstraint(SpringLayout.NORTH, resultText, 10, SpringLayout.SOUTH, runButton);
		tspLayout.putConstraint(SpringLayout.WEST, resultText, 10, SpringLayout.EAST, resultLabel);
		tspLayout.putConstraint(SpringLayout.EAST, resultText, -10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, resultText, 20, SpringLayout.NORTH, resultText);
	}
	
	public void stopTask() {
		runButton.setText("Run");
		isRun = false;
	}
	
	public DataTemplateExtractionParameter getDataTemplateExtractionParameter() throws Exception {
		DataTemplateExtractionParameter collectorParameter = new DataTemplateExtractionParameter();
		
		if (indexText.getText() == null || indexText.getText().equals("") == true) {
			throw new Exception("the index is null");
		}
		collectorParameter.setIndex(indexText.getText());
		
		if (tfratioText.getText() != null && tfratioText.getText().equals("") == false) {
			collectorParameter.setTfRatio(tfratioText.getText());
		}
		
		if (similarText.getText() != null && similarText.getText().equals("") == false) {
			collectorParameter.setSimilarRatio(similarText.getText());
		}
		
		return collectorParameter;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(248, 250, 241));
	}
	
	public void displayAnalyseTemplateStatus(String status) {
		resultText.setText(status);
	}

}
