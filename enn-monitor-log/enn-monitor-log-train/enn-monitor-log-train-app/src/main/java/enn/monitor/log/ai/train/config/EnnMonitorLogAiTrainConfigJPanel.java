package enn.monitor.log.ai.train.config;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.log.train.util.CoreContextUtil;
import enn.monitor.log.train.util.EnnMonitorConfigServiceClientUtil;

public class EnnMonitorLogAiTrainConfigJPanel extends JPanel {

	private static final long serialVersionUID = 7827890550778255513L;
	
	private static final Logger logger = LogManager.getLogger();
	
	private int labelWidth = 40;
	private int textWidth = 100;
	private int buttonWidth = 80;
	
	private JLabel tokenLabel = new JLabel();
	private JTextField tokenText = new JTextField();
	
	private JCheckBox isTemplate = new JCheckBox("isTemplate") ;
	
	private JButton addButton = new JButton();
	
	public EnnMonitorLogAiTrainConfigJPanel() {
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		// --------------------------------------------------------------------------------------------------------
		tokenLabel.setText("Token");
		add(tokenLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, tokenLabel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, tokenLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, tokenLabel, labelWidth, SpringLayout.WEST, tokenLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, tokenLabel, 20, SpringLayout.NORTH, tokenLabel);
		
		tokenText.setText("EC70929FBA0BAE0652548CD9C0B9F92E");
		add(tokenText);
		tspLayout.putConstraint(SpringLayout.NORTH, tokenText, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, tokenText, 10, SpringLayout.EAST, tokenLabel);
		tspLayout.putConstraint(SpringLayout.EAST, tokenText, -10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, tokenText, 20, SpringLayout.NORTH, tokenText);
		
		// --------------------------------------------------------------------------------------------------------
		add(isTemplate);
		tspLayout.putConstraint(SpringLayout.NORTH, isTemplate, 10, SpringLayout.SOUTH, tokenLabel);
		tspLayout.putConstraint(SpringLayout.WEST, isTemplate, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, isTemplate, textWidth * 2, SpringLayout.WEST, isTemplate);
		tspLayout.putConstraint(SpringLayout.SOUTH, isTemplate, 20, SpringLayout.NORTH, isTemplate);
		
		// --------------------------------------------------------------------------------------------------------
		addButton.setText("生效");
		add(addButton);
		tspLayout.putConstraint(SpringLayout.NORTH, addButton, 10, SpringLayout.SOUTH, isTemplate);
		tspLayout.putConstraint(SpringLayout.WEST, addButton, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, addButton, buttonWidth, SpringLayout.WEST, addButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, addButton, 20, SpringLayout.NORTH, addButton);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isTemplate.isSelected()) {
					setTemplateTrue();
				} else {
					setTemplateFalse();
				}
			}
			
		});
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(248, 250, 241));
	}
	
	private void setTemplateTrue() {
		try {
			CoreContextUtil.setTemplate(true);
			CoreContextUtil.setTokenId(0);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	private void setTemplateFalse() {
		if (tokenText.getText() == null || tokenText.getText().equals("") == true) {
			logger.error("the token is null");
			return;
		}
		
		long tokenId;
		try {
			tokenId = EnnMonitorConfigServiceClientUtil.getTokenId(tokenText.getText());
			if (tokenId <= 0) {
				throw new Exception("tokenId is not get");
			}
			
			CoreContextUtil.setTokenId(tokenId);
			CoreContextUtil.setTemplate(false);
			
			logger.info("config data valid");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
