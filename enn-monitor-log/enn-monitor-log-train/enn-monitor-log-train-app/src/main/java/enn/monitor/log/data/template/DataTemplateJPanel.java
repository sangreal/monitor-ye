package enn.monitor.log.data.template;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateTable;

public class DataTemplateJPanel extends JPanel {

	private static final long serialVersionUID = -2908731306877174829L;
	
	private static final Logger logger = LogManager.getLogger();
	
	private int labelWidth = 40;
	private int textWidth = 100;
	private int buttonWidth = 80;
	
	private JLabel moduleLabel = new JLabel();
	
	private JLabel idLabel = new JLabel();
	private JTextField idText = new JTextField();
	
	private JLabel belongToServiceIdLabel = new JLabel();
	private JTextField belongToServiceIdText = new JTextField();
	
	private JLabel tagIdLabel = new JLabel();
	private JTextField tagIdText = new JTextField();
	
	private JCheckBox isRoot = new JCheckBox("Root") ;
	
	private JLabel templateKeyJLabel = new JLabel();
	private JTextField templateKeyText = new JTextField();
	
	private JLabel belongToParentTemplateLabel = new JLabel();
	private JTextField belongToParentTemplateText = new JTextField();
	
	private JButton queryButton = new JButton();
	private JButton addButton = new JButton();
	private JButton changeButton = new JButton();
	private JButton deleteButton = new JButton();
	
	private final String columnHeader[] = {"Id", "belongToServiceId", "belongToParentTemplate", "templateKey", "tagId", "SetType"};
	private JTable table = new JTable(new DefaultTableModel());
	private JScrollPane scrollPane = new JScrollPane(table);
	
	public DataTemplateJPanel(DataTemplateFSM dataTemplateFSM) {
		TableColumn column = null;
		DefaultTableModel defaultTableModel = null;
		
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		moduleLabel.setText("数据标注和管理");
		add(moduleLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, moduleLabel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, moduleLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, moduleLabel, labelWidth * 3, SpringLayout.WEST, moduleLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, moduleLabel, 20, SpringLayout.NORTH, moduleLabel);
		
		// -------------------------------------------
		idLabel.setText("Id");
		add(idLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.SOUTH, moduleLabel);
		tspLayout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, idLabel, labelWidth, SpringLayout.WEST, idLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, idLabel, 20, SpringLayout.NORTH, idLabel);
		
		add(idText);
		tspLayout.putConstraint(SpringLayout.NORTH, idText, 10, SpringLayout.SOUTH, moduleLabel);
		tspLayout.putConstraint(SpringLayout.WEST, idText, 10, SpringLayout.EAST, idLabel);
		tspLayout.putConstraint(SpringLayout.EAST, idText, textWidth, SpringLayout.WEST, idText);
		tspLayout.putConstraint(SpringLayout.SOUTH, idText, 20, SpringLayout.NORTH, idText);
		
		belongToServiceIdLabel.setText("BelongToServiceId");
		add(belongToServiceIdLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, belongToServiceIdLabel, 10, SpringLayout.SOUTH, moduleLabel);
		tspLayout.putConstraint(SpringLayout.WEST, belongToServiceIdLabel, 10, SpringLayout.EAST, idText);
		tspLayout.putConstraint(SpringLayout.EAST, belongToServiceIdLabel, labelWidth * 3, SpringLayout.WEST, belongToServiceIdLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, belongToServiceIdLabel, 20, SpringLayout.NORTH, belongToServiceIdLabel);
		
		add(belongToServiceIdText);
		tspLayout.putConstraint(SpringLayout.NORTH, belongToServiceIdText, 10, SpringLayout.SOUTH, moduleLabel);
		tspLayout.putConstraint(SpringLayout.WEST, belongToServiceIdText, 10, SpringLayout.EAST, belongToServiceIdLabel);
		tspLayout.putConstraint(SpringLayout.EAST, belongToServiceIdText, textWidth, SpringLayout.WEST, belongToServiceIdText);
		tspLayout.putConstraint(SpringLayout.SOUTH, belongToServiceIdText, 20, SpringLayout.NORTH, belongToServiceIdText);
		
		tagIdLabel.setText("TagId");
		add(tagIdLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, tagIdLabel, 10, SpringLayout.SOUTH, moduleLabel);
		tspLayout.putConstraint(SpringLayout.WEST, tagIdLabel, 10, SpringLayout.EAST, belongToServiceIdText);
		tspLayout.putConstraint(SpringLayout.EAST, tagIdLabel, labelWidth, SpringLayout.WEST, tagIdLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, tagIdLabel, 20, SpringLayout.NORTH, tagIdLabel);
		
		add(tagIdText);
		tspLayout.putConstraint(SpringLayout.NORTH, tagIdText, 10, SpringLayout.SOUTH, moduleLabel);
		tspLayout.putConstraint(SpringLayout.WEST, tagIdText, 10, SpringLayout.EAST, tagIdLabel);
		tspLayout.putConstraint(SpringLayout.EAST, tagIdText, textWidth, SpringLayout.WEST, tagIdText);
		tspLayout.putConstraint(SpringLayout.SOUTH, tagIdText, 20, SpringLayout.NORTH, tagIdText);
		
		add(isRoot);
		tspLayout.putConstraint(SpringLayout.NORTH, isRoot, 10, SpringLayout.SOUTH, moduleLabel);
		tspLayout.putConstraint(SpringLayout.WEST, isRoot, 10, SpringLayout.EAST, tagIdText);
		tspLayout.putConstraint(SpringLayout.EAST, isRoot, textWidth, SpringLayout.WEST, isRoot);
		tspLayout.putConstraint(SpringLayout.SOUTH, isRoot, 20, SpringLayout.NORTH, isRoot);
		
		templateKeyJLabel.setText("templateKey");
		add(templateKeyJLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, templateKeyJLabel, 10, SpringLayout.SOUTH, idLabel);
		tspLayout.putConstraint(SpringLayout.WEST, templateKeyJLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, templateKeyJLabel, labelWidth * 4, SpringLayout.WEST, templateKeyJLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, templateKeyJLabel, 20, SpringLayout.NORTH, templateKeyJLabel);
		
		add(templateKeyText);
		tspLayout.putConstraint(SpringLayout.NORTH, templateKeyText, 10, SpringLayout.SOUTH, idLabel);
		tspLayout.putConstraint(SpringLayout.WEST, templateKeyText, 10, SpringLayout.EAST, templateKeyJLabel);
		tspLayout.putConstraint(SpringLayout.EAST, templateKeyText, -10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, templateKeyText, 20, SpringLayout.NORTH, templateKeyText);
		
		belongToParentTemplateLabel.setText("belongToParentTemplate");
		add(belongToParentTemplateLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, belongToParentTemplateLabel, 10, SpringLayout.SOUTH, templateKeyJLabel);
		tspLayout.putConstraint(SpringLayout.WEST, belongToParentTemplateLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, belongToParentTemplateLabel, labelWidth * 4, SpringLayout.WEST, belongToParentTemplateLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, belongToParentTemplateLabel, 20, SpringLayout.NORTH, belongToParentTemplateLabel);
		
		add(belongToParentTemplateText);
		tspLayout.putConstraint(SpringLayout.NORTH, belongToParentTemplateText, 10, SpringLayout.SOUTH, templateKeyJLabel);
		tspLayout.putConstraint(SpringLayout.WEST, belongToParentTemplateText, 10, SpringLayout.EAST, belongToParentTemplateLabel);
		tspLayout.putConstraint(SpringLayout.EAST, belongToParentTemplateText, -10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, belongToParentTemplateText, 20, SpringLayout.NORTH, belongToParentTemplateText);
		
		// -------------------------------------------------------------------------------------------------------------------------------
		queryButton.setText("Query");
		add(queryButton);
		tspLayout.putConstraint(SpringLayout.NORTH, queryButton, 10, SpringLayout.SOUTH, belongToParentTemplateLabel);
		tspLayout.putConstraint(SpringLayout.WEST, queryButton, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, queryButton, buttonWidth, SpringLayout.WEST, queryButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, queryButton, 20, SpringLayout.NORTH, queryButton);
		queryButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dataTemplateFSM.addEvent(new DataTemplateEvent(DataTemplateEventEnum.Search, getDataTemplateParameter()));
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
			
		});
		
		addButton.setText("Add");
		add(addButton);
		tspLayout.putConstraint(SpringLayout.NORTH, addButton, 10, SpringLayout.SOUTH, belongToParentTemplateLabel);
		tspLayout.putConstraint(SpringLayout.WEST, addButton, 10, SpringLayout.EAST, queryButton);
		tspLayout.putConstraint(SpringLayout.EAST, addButton, buttonWidth, SpringLayout.WEST, addButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, addButton, 20, SpringLayout.NORTH, addButton);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dataTemplateFSM.addEvent(new DataTemplateEvent(DataTemplateEventEnum.Insert, getDataTemplateParameter()));
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
			
		});
		
		changeButton.setText("Change");
		add(changeButton);
		tspLayout.putConstraint(SpringLayout.NORTH, changeButton, 10, SpringLayout.SOUTH, belongToParentTemplateLabel);
		tspLayout.putConstraint(SpringLayout.WEST, changeButton, 10, SpringLayout.EAST, addButton);
		tspLayout.putConstraint(SpringLayout.EAST, changeButton, buttonWidth, SpringLayout.WEST, changeButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, changeButton, 20, SpringLayout.NORTH, changeButton);
		changeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dataTemplateFSM.addEvent(new DataTemplateEvent(DataTemplateEventEnum.Update, getDataTemplateParameter()));
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
			
		});
		
		deleteButton.setText("Delete");
		add(deleteButton);
		tspLayout.putConstraint(SpringLayout.NORTH, deleteButton, 10, SpringLayout.SOUTH, belongToParentTemplateLabel);
		tspLayout.putConstraint(SpringLayout.WEST, deleteButton, 10, SpringLayout.EAST, changeButton);
		tspLayout.putConstraint(SpringLayout.EAST, deleteButton, buttonWidth, SpringLayout.WEST, deleteButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, deleteButton, 20, SpringLayout.NORTH, deleteButton);
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dataTemplateFSM.addEvent(new DataTemplateEvent(DataTemplateEventEnum.Delete, getDataTemplateParameter()));
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
			
		});
		
		// -----------------------------------------------------------------------------------------------------
		add(scrollPane);
		tspLayout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.SOUTH, deleteButton);
		tspLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, this);
		
		defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setColumnIdentifiers(columnHeader);
		
		column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(100);
		column.setMinWidth(100);
		column.setMaxWidth(100);
		
		column = table.getColumnModel().getColumn(1);
		column.setPreferredWidth(150);
		column.setMinWidth(150);
		column.setMaxWidth(150);
		
		column = table.getColumnModel().getColumn(2);
		column.setPreferredWidth(600);
		column.setMinWidth(600);
		column.setMaxWidth(600);
		
		column = table.getColumnModel().getColumn(4);
		column.setPreferredWidth(50);
		column.setMinWidth(50);
		column.setMaxWidth(50);
		
		column = table.getColumnModel().getColumn(5);
		column.setPreferredWidth(50);
		column.setMinWidth(50);
		column.setMaxWidth(50);
		
		table.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e) {
		    	DataTemplateParameter dataTemplateParameter = new DataTemplateParameter();
		    	int selectedRow = table.getSelectedRow();
		    	
		    	dataTemplateParameter.setId(Long.parseLong((String) table.getValueAt(selectedRow, 0)));
		    	dataTemplateParameter.setBelongToServiceId(Long.parseLong((String) table.getValueAt(selectedRow, 1)));
		    	dataTemplateParameter.setBelongToParentTemplate((String) table.getValueAt(selectedRow, 2));
		    	dataTemplateParameter.setTemplateKey((String) table.getValueAt(selectedRow, 3));
		    	dataTemplateParameter.setTagId(Long.parseLong((String) table.getValueAt(selectedRow, 4)));
		    	
		    	try {
		    		dataTemplateFSM.addEvent(new DataTemplateEvent(DataTemplateEventEnum.Display, dataTemplateParameter));
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
	
	public DataTemplateParameter getDataTemplateParameter() {
		DataTemplateParameter dataTemplateParameter = new DataTemplateParameter();
		
		if (idText.getText() != null && idText.getText().equals("") == false) {
			dataTemplateParameter.setId(Long.parseLong(idText.getText()));
		}
		
		if (belongToServiceIdText.getText() != null && belongToServiceIdText.getText().equals("") == false) {
			dataTemplateParameter.setBelongToServiceId(Long.parseLong(belongToServiceIdText.getText()));
		}
		
		if (tagIdText.getText() != null && tagIdText.getText().equals("") == false) {
			dataTemplateParameter.setTagId(Long.parseLong(tagIdText.getText()));
		}
		
		if (isRoot.isSelected() == true) {
			dataTemplateParameter.setRoot(true);
		}
		
		if (templateKeyText.getText() != null && templateKeyText.getText().equals("") == false) {
			dataTemplateParameter.setTemplateKey(templateKeyText.getText());
		}
		
		if (belongToParentTemplateText.getText() != null && belongToParentTemplateText.getText().equals("") == false) {
			dataTemplateParameter.setBelongToParentTemplate(belongToParentTemplateText.getText());
		}
		
		return dataTemplateParameter;
	}
	
	public void setEnnMonitorLogConfigTemplateTables(List<EnnMonitorLogConfigTemplateTable> tables) {
		int i;
				
		EnnMonitorLogConfigTemplateTable templateTable = null;
				
		Vector<String> rowData = null;
		DefaultTableModel defaultTableModel = null;
				
		defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.getDataVector().clear();
		for (i = 0; i < tables.size(); ++i) {
			rowData = new Vector<String>();
			templateTable = tables.get(i);
			
			rowData.add("" + templateTable.getId());
			rowData.add("" + templateTable.getBelongToServiceId());
			if (templateTable.getBelongToParentTemplate() != null && templateTable.getBelongToParentTemplate().equals("") == false) {
				rowData.add(templateTable.getBelongToParentTemplate());
			} else {
				rowData.add("");
			}
			if (templateTable.getTemplateKey() != null && templateTable.getTemplateKey().equals("") == false) {
				rowData.add(templateTable.getTemplateKey());
			} else {
				rowData.add("");
			}
			rowData.add("" + templateTable.getTagId());
			
			if (templateTable.getSetType() != null && templateTable.getSetTypeValue() > 0) {
				rowData.add("" + templateTable.getSetType().name());
			} else {
				rowData.add("");
			}
			
			defaultTableModel.getDataVector().add(rowData);
		}
		
		table.updateUI();
	}
	
	public void displayEnnMonitorLogConfigTemplateTable(DataTemplateParameter dataTemplateParameter) {
		idText.setText("" + dataTemplateParameter.getId());
		belongToServiceIdText.setText("" + dataTemplateParameter.getBelongToServiceId());
		tagIdText.setText("" + dataTemplateParameter.getTagId());
		belongToParentTemplateText.setText(dataTemplateParameter.getBelongToParentTemplate());
		templateKeyText.setText(dataTemplateParameter.getTemplateKey());
	}
	
}
