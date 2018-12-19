package enn.monitor.log.ai.train.result;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnnMonitorLogAiTrainResultJPanel extends JPanel {

	private static final long serialVersionUID = 299839299523464428L;
	
	private static final Logger logger = LogManager.getLogger();
	
	private int labelWidth = 60;
	private int textWidth = 100;
	private int buttonWidth = 150;
	
	private JLabel tokenLabel = new JLabel();
	private JTextField tokenText = new JTextField();
	
	private JButton runButton = new JButton();
	
	private JLabel idLabel = new JLabel();
	private JLabel idText = new JLabel();
	
	private JLabel belongToServiceIdLabel = new JLabel();
	private JLabel belongToServiceIdText = new JLabel();
	
	private JLabel tagIdLabel = new JLabel();
	private JLabel tagIdText = new JLabel();
	
	private JLabel templateKeyLabel = new JLabel();
	private JLabel templateKeyText = new JLabel();
	
	private JButton sumbitButton = new JButton();
//	private JButton sumbitAllButton = new JButton();
	
	private final String columnHeader[] = {"Id", "belongToServiceId", "templateKey", "tagId", "tag", "match"};
	private JTable table = new JTable(new DefaultTableModel());
	private JScrollPane scrollPane = new JScrollPane(table);
	
	public EnnMonitorLogAiTrainResultJPanel(EnnMonitorLogAiTrainResultFSM ennMonitorLogAiTrainResultFSM) {
		TableColumn column = null;
		DefaultTableModel defaultTableModel = null;
		
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		// -------------------------------------------
		tokenLabel.setText("Token");
		add(tokenLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, tokenLabel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, tokenLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, tokenLabel, labelWidth, SpringLayout.WEST, tokenLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, tokenLabel, 20, SpringLayout.NORTH, tokenLabel);
		
		add(tokenText);
		tspLayout.putConstraint(SpringLayout.NORTH, tokenText, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, tokenText, 10, SpringLayout.EAST, tokenLabel);
		tspLayout.putConstraint(SpringLayout.EAST, tokenText, textWidth * 3, SpringLayout.WEST, tokenText);
		tspLayout.putConstraint(SpringLayout.SOUTH, tokenText, 20, SpringLayout.NORTH, tokenText);
		
		runButton.setText("Run");
		add(runButton);
		tspLayout.putConstraint(SpringLayout.NORTH, runButton, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, runButton, 50, SpringLayout.EAST, tokenText);
		tspLayout.putConstraint(SpringLayout.EAST, runButton, buttonWidth, SpringLayout.WEST, runButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, runButton, 20, SpringLayout.NORTH, runButton);
		runButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ennMonitorLogAiTrainResultFSM.addEvent(
							new EnnMonitorLogAiTrainResultEvent(EnnMonitorLogAiTrainResultEventEnum.Compute, getEnnMonitorLogAiTrainResultParameter()));
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
			
		});
		
		// -----------------------------------------------------------------------------------------------------------
		idLabel.setText("Id");
		add(idLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.SOUTH, tokenLabel);
		tspLayout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, idLabel, labelWidth, SpringLayout.WEST, idLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, idLabel, 20, SpringLayout.NORTH, idLabel);
		
		add(idText);
		tspLayout.putConstraint(SpringLayout.NORTH, idText, 10, SpringLayout.SOUTH, tokenLabel);
		tspLayout.putConstraint(SpringLayout.WEST, idText, 10, SpringLayout.EAST, idLabel);
		tspLayout.putConstraint(SpringLayout.EAST, idText, textWidth, SpringLayout.WEST, idText);
		tspLayout.putConstraint(SpringLayout.SOUTH, idText, 20, SpringLayout.NORTH, idText);
		
		belongToServiceIdLabel.setText("belongToServiceId");
		add(belongToServiceIdLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, belongToServiceIdLabel, 10, SpringLayout.SOUTH, tokenLabel);
		tspLayout.putConstraint(SpringLayout.WEST, belongToServiceIdLabel, 50, SpringLayout.EAST, idText);
		tspLayout.putConstraint(SpringLayout.EAST, belongToServiceIdLabel, labelWidth * 2, SpringLayout.WEST, belongToServiceIdLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, belongToServiceIdLabel, 20, SpringLayout.NORTH, belongToServiceIdLabel);
		
		add(belongToServiceIdText);
		tspLayout.putConstraint(SpringLayout.NORTH, belongToServiceIdText, 10, SpringLayout.SOUTH, tokenLabel);
		tspLayout.putConstraint(SpringLayout.WEST, belongToServiceIdText, 10, SpringLayout.EAST, belongToServiceIdLabel);
		tspLayout.putConstraint(SpringLayout.EAST, belongToServiceIdText, textWidth, SpringLayout.WEST, belongToServiceIdText);
		tspLayout.putConstraint(SpringLayout.SOUTH, belongToServiceIdText, 20, SpringLayout.NORTH, belongToServiceIdText);
		
		tagIdLabel.setText("TagId");
		add(tagIdLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, tagIdLabel, 10, SpringLayout.SOUTH, tokenLabel);
		tspLayout.putConstraint(SpringLayout.WEST, tagIdLabel, 10, SpringLayout.EAST, belongToServiceIdText);
		tspLayout.putConstraint(SpringLayout.EAST, tagIdLabel, labelWidth, SpringLayout.WEST, tagIdLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, tagIdLabel, 20, SpringLayout.NORTH, tagIdLabel);
		
		add(tagIdText);
		tspLayout.putConstraint(SpringLayout.NORTH, tagIdText, 10, SpringLayout.SOUTH, tokenLabel);
		tspLayout.putConstraint(SpringLayout.WEST, tagIdText, 10, SpringLayout.EAST, tagIdLabel);
		tspLayout.putConstraint(SpringLayout.EAST, tagIdText, textWidth, SpringLayout.WEST, tagIdText);
		tspLayout.putConstraint(SpringLayout.SOUTH, tagIdText, 20, SpringLayout.NORTH, tagIdText);
		
		// -----------------------------------------------------------------------------------------------------------
		
		templateKeyLabel.setText("TemplateKey");
		add(templateKeyLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, templateKeyLabel, 10, SpringLayout.SOUTH, idLabel);
		tspLayout.putConstraint(SpringLayout.WEST, templateKeyLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, templateKeyLabel, labelWidth * 2, SpringLayout.WEST, templateKeyLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, templateKeyLabel, 20, SpringLayout.NORTH, templateKeyLabel);
		
		add(templateKeyText);
		tspLayout.putConstraint(SpringLayout.NORTH, templateKeyText, 10, SpringLayout.SOUTH, idLabel);
		tspLayout.putConstraint(SpringLayout.WEST, templateKeyText, 10, SpringLayout.EAST, templateKeyLabel);
		tspLayout.putConstraint(SpringLayout.EAST, templateKeyText, -10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, templateKeyText, 20, SpringLayout.NORTH, templateKeyText);
		
		// -----------------------------------------------------------------------------------------------------------
		sumbitButton.setText("Sumbit");
		add(sumbitButton);
		tspLayout.putConstraint(SpringLayout.NORTH, sumbitButton, 10, SpringLayout.SOUTH, templateKeyLabel);
		tspLayout.putConstraint(SpringLayout.WEST, sumbitButton, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, sumbitButton, buttonWidth, SpringLayout.WEST, sumbitButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, sumbitButton, 20, SpringLayout.NORTH, sumbitButton);
		sumbitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ennMonitorLogAiTrainResultFSM.addEvent(
							new EnnMonitorLogAiTrainResultEvent(EnnMonitorLogAiTrainResultEventEnum.Update, getEnnMonitorLogAiTrainResultParameter()));
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
			
		});
		
//		sumbitAllButton.setText("Sumbit All");
//		add(sumbitAllButton);
//		tspLayout.putConstraint(SpringLayout.NORTH, sumbitAllButton, 10, SpringLayout.SOUTH, templateKeyLabel);
//		tspLayout.putConstraint(SpringLayout.WEST, sumbitAllButton, 10, SpringLayout.EAST, sumbitButton);
//		tspLayout.putConstraint(SpringLayout.EAST, sumbitAllButton, buttonWidth, SpringLayout.WEST, sumbitAllButton);
//		tspLayout.putConstraint(SpringLayout.SOUTH, sumbitAllButton, 20, SpringLayout.NORTH, sumbitAllButton);
//		sumbitAllButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try {
//					ennMonitorLogAiTrainResultFSM.addEvent(
//							new EnnMonitorLogAiTrainResultEvent(EnnMonitorLogAiTrainResultEventEnum.UpdateAll, getEnnMonitorLogAiTrainResultParameter()));
//				} catch (Exception e1) {
//					logger.error(e1.getMessage(), e1);
//				}
//			}
//			
//		});
		
		// -----------------------------------------------------------------------------------------------------------
		add(scrollPane);
		tspLayout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.SOUTH, sumbitButton);
		tspLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, this);
		
		defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setColumnIdentifiers(columnHeader);
		
		column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(50);
		column.setMinWidth(50);
		column.setMaxWidth(50);
		
		column = table.getColumnModel().getColumn(1);
		column.setPreferredWidth(150);
		column.setMinWidth(150);
		column.setMaxWidth(150);
		
		column = table.getColumnModel().getColumn(3);
		column.setPreferredWidth(100);
		column.setMinWidth(100);
		column.setMaxWidth(100);
		
		column = table.getColumnModel().getColumn(4);
		column.setPreferredWidth(300);
		column.setMinWidth(300);
		column.setMaxWidth(300);
		
		column = table.getColumnModel().getColumn(5);
		column.setPreferredWidth(200);
		column.setMinWidth(200);
		column.setMaxWidth(200);
		
		table.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e) {
		    	EnnMonitorLogAiTrainResultParameter dataTemplateParameter = new EnnMonitorLogAiTrainResultParameter();
		    	int selectedRow = table.getSelectedRow();
		    	
		    	dataTemplateParameter.setId(Long.parseLong((String) table.getValueAt(selectedRow, 0)));
		    	dataTemplateParameter.setBelongToServiceId(Long.parseLong((String) table.getValueAt(selectedRow, 1)));
		    	dataTemplateParameter.setTemplateKey((String) table.getValueAt(selectedRow, 2));
		    	dataTemplateParameter.setTagId(Long.parseLong((String) table.getValueAt(selectedRow, 3)));
		    	
		    	try {
		    		ennMonitorLogAiTrainResultFSM.addEvent(new EnnMonitorLogAiTrainResultEvent(EnnMonitorLogAiTrainResultEventEnum.Display, dataTemplateParameter));
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
		    }
		});
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(248, 250, 241));
	}
	
	public EnnMonitorLogAiTrainResultParameter getEnnMonitorLogAiTrainResultParameter() {
		EnnMonitorLogAiTrainResultParameter ennMonitorLogAiTrainResultParameter = new EnnMonitorLogAiTrainResultParameter();
		
		if (tokenText.getText() != null && tokenText.getText().equals("") == false) {
			ennMonitorLogAiTrainResultParameter.setToken(tokenText.getText());
		}
		
		if (idText.getText() != null && idText.getText().equals("") == false) {
			ennMonitorLogAiTrainResultParameter.setId(Long.parseLong(idText.getText()));
		}
		
		if (belongToServiceIdText.getText() != null && belongToServiceIdText.getText().equals("") == false) {
			ennMonitorLogAiTrainResultParameter.setBelongToServiceId(Long.parseLong(belongToServiceIdText.getText()));
		}
		
		if (tagIdText.getText() != null && tagIdText.getText().equals("") == false) {
			ennMonitorLogAiTrainResultParameter.setTagId(Long.parseLong(tagIdText.getText()));
		}
		
		if (templateKeyText.getText() != null && templateKeyText.getText().equals("") == false) {
			ennMonitorLogAiTrainResultParameter.setTemplateKey(templateKeyText.getText());
		}
		
		return ennMonitorLogAiTrainResultParameter;
	}
	
	public void setEnnMonitorLogAiTrainResultParameters(List<EnnMonitorLogAiTrainResultParameter> tables) {
		int i;
				
		EnnMonitorLogAiTrainResultParameter parameterTable = null;
				
		Vector<String> rowData = null;
		DefaultTableModel defaultTableModel = null;
		
		if (tables == null) {
			return;
		}
				
		defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.getDataVector().clear();
		for (i = 0; i < tables.size(); ++i) {
			rowData = new Vector<String>();
			parameterTable = tables.get(i);
			
			rowData.add("" + parameterTable.getId());
			rowData.add("" + parameterTable.getBelongToServiceId());
			if (parameterTable.getTemplateKey() != null && parameterTable.getTemplateKey().equals("") == false) {
				rowData.add(parameterTable.getTemplateKey());
			} else {
				rowData.add("");
			}
			rowData.add("" + parameterTable.getTagId());
			
			if (parameterTable.getTag() != null && parameterTable.getTag().equals("") == false) {
				rowData.add("" + parameterTable.getTag());
				rowData.add("" + parameterTable.getMatch());
			} else {
				rowData.add("");
			}
			
			defaultTableModel.getDataVector().add(rowData);
		}
		
		table.updateUI();
	}
	
	public void displayEnnMonitorLogConfigTemplateTable(EnnMonitorLogAiTrainResultParameter ennMonitorLogAiTrainResultParameter) {
		idText.setText("" + ennMonitorLogAiTrainResultParameter.getId());
		belongToServiceIdText.setText("" + ennMonitorLogAiTrainResultParameter.getBelongToServiceId());
		tagIdText.setText("" + ennMonitorLogAiTrainResultParameter.getTagId());
		templateKeyText.setText(ennMonitorLogAiTrainResultParameter.getTemplateKey());
	}

}
