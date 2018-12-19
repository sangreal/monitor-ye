package enn.monitor.log.data.knowledge;

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

import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermTable;

public class DataKnowledgeJPanel extends JPanel {

	private static final long serialVersionUID = 3025351139793520493L;
	
	private static final Logger logger = LogManager.getLogger();
	
	private int labelWidth = 40;
	private int textWidth = 100;
	private int buttonWidth = 80;
	
	private JLabel moduleLabel = new JLabel();
	
	private JLabel idLabel = new JLabel();
	private JTextField idText = new JTextField();
	
	private JLabel belongToServiceIdLabel = new JLabel();
	private JTextField belongToServiceIdText = new JTextField();
	
	private JLabel keyLabel = new JLabel();
	private JTextField keyText = new JTextField();
	
	private JLabel filterLabel = new JLabel();
	private JTextField filterText = new JTextField();
	
	private JButton queryButton = new JButton();
	private JButton addButton = new JButton();
	private JButton changeButton = new JButton();
	private JButton deleteButton = new JButton();
	
	private final String columnHeader[] = {"Id", "BelongToServiceId", "AddTerm", "FilterTerm"};
	private JTable table = new JTable(new DefaultTableModel());
	private JScrollPane scrollPane = new JScrollPane(table);
	
	public DataKnowledgeJPanel(DataKnowledgeFSM dataKnowledgeFSM) {
		TableColumn column = null;
		DefaultTableModel defaultTableModel = null;
		
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		moduleLabel.setText("运维知识库");
		add(moduleLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, moduleLabel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, moduleLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, moduleLabel, labelWidth * 3, SpringLayout.WEST, moduleLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, moduleLabel, 20, SpringLayout.NORTH, moduleLabel);
		
		// id
		idLabel.setText("Id");
		add(idLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.SOUTH, moduleLabel);
		tspLayout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, idLabel, labelWidth, SpringLayout.WEST, idLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, idLabel, 20, SpringLayout.NORTH, idLabel);
		
		add(idText);
		idText.setText("-1");
		tspLayout.putConstraint(SpringLayout.NORTH, idText, 10, SpringLayout.SOUTH, moduleLabel);
		tspLayout.putConstraint(SpringLayout.WEST, idText, 10, SpringLayout.EAST, idLabel);
		tspLayout.putConstraint(SpringLayout.EAST, idText, textWidth, SpringLayout.WEST, idText);
		tspLayout.putConstraint(SpringLayout.SOUTH, idText, 20, SpringLayout.NORTH, idText);
		
		// belongToServiceIdLabel
		belongToServiceIdLabel.setText("belongToServiceId");
		add(belongToServiceIdLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, belongToServiceIdLabel, 10, SpringLayout.SOUTH, moduleLabel);
		tspLayout.putConstraint(SpringLayout.WEST, belongToServiceIdLabel, 10, SpringLayout.EAST, idText);
		tspLayout.putConstraint(SpringLayout.EAST, belongToServiceIdLabel, labelWidth * 3, SpringLayout.WEST, belongToServiceIdLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, belongToServiceIdLabel, 20, SpringLayout.NORTH, belongToServiceIdLabel);
		
		add(belongToServiceIdText);
		belongToServiceIdText.setText("-1");
		tspLayout.putConstraint(SpringLayout.NORTH, belongToServiceIdText, 10, SpringLayout.SOUTH, moduleLabel);
		tspLayout.putConstraint(SpringLayout.WEST, belongToServiceIdText, 10, SpringLayout.EAST, belongToServiceIdLabel);
		tspLayout.putConstraint(SpringLayout.EAST, belongToServiceIdText, textWidth, SpringLayout.WEST, belongToServiceIdText);
		tspLayout.putConstraint(SpringLayout.SOUTH, belongToServiceIdText, 20, SpringLayout.NORTH, belongToServiceIdText);
		
		// key
		keyLabel.setText("Key");
		add(keyLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, keyLabel, 10, SpringLayout.SOUTH, idLabel);
		tspLayout.putConstraint(SpringLayout.WEST, keyLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, keyLabel, labelWidth, SpringLayout.WEST, keyLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, keyLabel, 20, SpringLayout.NORTH, keyLabel);
		
		add(keyText);
		tspLayout.putConstraint(SpringLayout.NORTH, keyText, 10, SpringLayout.SOUTH, idLabel);
		tspLayout.putConstraint(SpringLayout.WEST, keyText, 10, SpringLayout.EAST, keyLabel);
		tspLayout.putConstraint(SpringLayout.EAST, keyText, textWidth, SpringLayout.WEST, keyText);
		tspLayout.putConstraint(SpringLayout.SOUTH, keyText, 20, SpringLayout.NORTH, keyText);
		
		// filter
		filterLabel.setText("Filter");
		add(filterLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, filterLabel, 10, SpringLayout.SOUTH, idLabel);
		tspLayout.putConstraint(SpringLayout.WEST, filterLabel, 10, SpringLayout.EAST, keyText);
		tspLayout.putConstraint(SpringLayout.EAST, filterLabel, labelWidth * 3, SpringLayout.WEST, filterLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, filterLabel, 20, SpringLayout.NORTH, filterLabel);
		
		add(filterText);
		tspLayout.putConstraint(SpringLayout.NORTH, filterText, 10, SpringLayout.SOUTH, idLabel);
		tspLayout.putConstraint(SpringLayout.WEST, filterText, 10, SpringLayout.EAST, filterLabel);
		tspLayout.putConstraint(SpringLayout.EAST, filterText, textWidth, SpringLayout.WEST, filterText);
		tspLayout.putConstraint(SpringLayout.SOUTH, filterText, 20, SpringLayout.NORTH, filterText);
		
		// -----------------------------------------------------------------------------------------------------
		queryButton.setText("Query");
		add(queryButton);
		tspLayout.putConstraint(SpringLayout.NORTH, queryButton, 10, SpringLayout.SOUTH, filterLabel);
		tspLayout.putConstraint(SpringLayout.WEST, queryButton, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, queryButton, buttonWidth, SpringLayout.WEST, queryButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, queryButton, 20, SpringLayout.NORTH, queryButton);
		queryButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dataKnowledgeFSM.addEvent(new DataKnowledgeEvent(DataKnowledgeEventEnum.Search, getDataKnowledgeParameter()));
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
			
		});
		
		addButton.setText("Add");
		add(addButton);
		tspLayout.putConstraint(SpringLayout.NORTH, addButton, 10, SpringLayout.SOUTH, filterLabel);
		tspLayout.putConstraint(SpringLayout.WEST, addButton, 10, SpringLayout.EAST, queryButton);
		tspLayout.putConstraint(SpringLayout.EAST, addButton, buttonWidth, SpringLayout.WEST, addButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, addButton, 20, SpringLayout.NORTH, addButton);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dataKnowledgeFSM.addEvent(new DataKnowledgeEvent(DataKnowledgeEventEnum.Insert, getDataKnowledgeParameter()));
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
			
		});
		
		changeButton.setText("Change");
		add(changeButton);
		tspLayout.putConstraint(SpringLayout.NORTH, changeButton, 10, SpringLayout.SOUTH, filterLabel);
		tspLayout.putConstraint(SpringLayout.WEST, changeButton, 10, SpringLayout.EAST, addButton);
		tspLayout.putConstraint(SpringLayout.EAST, changeButton, buttonWidth, SpringLayout.WEST, changeButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, changeButton, 20, SpringLayout.NORTH, changeButton);
		changeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dataKnowledgeFSM.addEvent(new DataKnowledgeEvent(DataKnowledgeEventEnum.Update, getDataKnowledgeParameter()));
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
			
		});
		
		deleteButton.setText("Delete");
		add(deleteButton);
		tspLayout.putConstraint(SpringLayout.NORTH, deleteButton, 10, SpringLayout.SOUTH, filterLabel);
		tspLayout.putConstraint(SpringLayout.WEST, deleteButton, 10, SpringLayout.EAST, changeButton);
		tspLayout.putConstraint(SpringLayout.EAST, deleteButton, buttonWidth, SpringLayout.WEST, deleteButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, deleteButton, 20, SpringLayout.NORTH, deleteButton);
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dataKnowledgeFSM.addEvent(new DataKnowledgeEvent(DataKnowledgeEventEnum.Delete, getDataKnowledgeParameter()));
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
		column.setPreferredWidth(200);
		column.setMinWidth(200);
		column.setMaxWidth(200);
		
		table.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e) {
		    	DataKnowledgeParameter dataKnowledgeParameter = new DataKnowledgeParameter();
		    	int selectedRow = table.getSelectedRow();
		    	
		    	dataKnowledgeParameter.setId(Long.parseLong((String) table.getValueAt(selectedRow, 0)));
		    	dataKnowledgeParameter.setBelongToServiceId(Long.parseLong((String) table.getValueAt(selectedRow, 1)));
		    	dataKnowledgeParameter.setKey((String) table.getValueAt(selectedRow, 2));
		    	dataKnowledgeParameter.setFilter((String) table.getValueAt(selectedRow, 3));
		    	
		    	try {
		    		dataKnowledgeFSM.addEvent(new DataKnowledgeEvent(DataKnowledgeEventEnum.Display, dataKnowledgeParameter));
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
	
	public DataKnowledgeParameter getDataKnowledgeParameter() {
		DataKnowledgeParameter dataKnowledgeParameter = new DataKnowledgeParameter();
		
		if (idText.getText() != null && idText.getText().equals("") == false) {
			dataKnowledgeParameter.setId(Long.parseLong(idText.getText()));
		}
		
		if (belongToServiceIdText.getText() != null && belongToServiceIdText.getText().equals("") == false) {
			dataKnowledgeParameter.setBelongToServiceId(Long.parseLong(belongToServiceIdText.getText()));
		}
		
		if (keyText.getText() != null && keyText.getText().equals("") == false) {
			dataKnowledgeParameter.setKey(keyText.getText());
		}
		
		if (filterText.getText() != null && filterText.getText().equals("") == false) {
			dataKnowledgeParameter.setFilter(filterText.getText());
		}
		
		return dataKnowledgeParameter;
	}
	
	public void setEnnMonitorLogConfigAnalyseTermTables(List<EnnMonitorLogConfigAnalyseTermTable> tables) {
		int i;
				
		EnnMonitorLogConfigAnalyseTermTable analyseTermTable = null;
				
		Vector<String> rowData = null;
		DefaultTableModel defaultTableModel = null;
				
		defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.getDataVector().clear();
		for (i = 0; i < tables.size(); ++i) {
			rowData = new Vector<String>();
			analyseTermTable = tables.get(i);
			
			rowData.add("" + analyseTermTable.getId());
			rowData.add("" + analyseTermTable.getBelongToServiceId());
			if (analyseTermTable.getAddTerm() != null && analyseTermTable.getAddTerm().equals("") == false) {
				rowData.add(analyseTermTable.getAddTerm());
			} else {
				rowData.add("");
			}
			if (analyseTermTable.getFilterTerm() != null && analyseTermTable.getFilterTerm().equals("") == false) {
				rowData.add(analyseTermTable.getFilterTerm());
			} else {
				rowData.add("");
			}
			
			defaultTableModel.getDataVector().add(rowData);
		}
		
		table.updateUI();
	}
	
	public void displayEnnMonitorLogConfigAnalyseTermTable(DataKnowledgeParameter dataKnowledgeEventParameter) {
		idText.setText("" + dataKnowledgeEventParameter.getId());
		belongToServiceIdText.setText("" + dataKnowledgeEventParameter.getBelongToServiceId());
		keyText.setText(dataKnowledgeEventParameter.getKey());
		filterText.setText(dataKnowledgeEventParameter.getFilter());
	}

}
