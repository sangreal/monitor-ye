package enn.monitor.log.data.tag;

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

import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventTable;

public class DataTagJPanel extends JPanel {

	private static final long serialVersionUID = 3025351139793520493L;
	
	private static final Logger logger = LogManager.getLogger();
	
	private int labelWidth = 40;
	private int textWidth = 100;
	private int buttonWidth = 80;
	
	private JLabel moduleLabel = new JLabel();
	
	private JLabel idLabel = new JLabel();
	private JTextField idText = new JTextField();
	
	private JLabel belongToIdLabel = new JLabel();
	private JTextField belongToIdText = new JTextField();
	
	private JLabel tagLabel = new JLabel();
	private JTextField tagText = new JTextField();
	
	private JButton queryButton = new JButton();
	private JButton addButton = new JButton();
	private JButton changeButton = new JButton();
	private JButton deleteButton = new JButton();
	
	private final String columnHeader[] = {"Id", "BelongToServiceId", "Tag"};
	private JTable table = new JTable(new DefaultTableModel());
	private JScrollPane scrollPane = new JScrollPane(table);
	
	public DataTagJPanel(DataTagFSM dataTagFSM) {
		TableColumn column = null;
		DefaultTableModel defaultTableModel = null;
		
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		moduleLabel.setText("标签管理");
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
		
		// key
		belongToIdLabel.setText("BelongToServiceId");
		add(belongToIdLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, belongToIdLabel, 10, SpringLayout.SOUTH, moduleLabel);
		tspLayout.putConstraint(SpringLayout.WEST, belongToIdLabel, 10, SpringLayout.EAST, idText);
		tspLayout.putConstraint(SpringLayout.EAST, belongToIdLabel, labelWidth * 3, SpringLayout.WEST, belongToIdLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, belongToIdLabel, 20, SpringLayout.NORTH, belongToIdLabel);
		
		add(belongToIdText);
		belongToIdText.setText("-1");
		tspLayout.putConstraint(SpringLayout.NORTH, belongToIdText, 10, SpringLayout.SOUTH, moduleLabel);
		tspLayout.putConstraint(SpringLayout.WEST, belongToIdText, 10, SpringLayout.EAST, belongToIdLabel);
		tspLayout.putConstraint(SpringLayout.EAST, belongToIdText, textWidth, SpringLayout.WEST, belongToIdText);
		tspLayout.putConstraint(SpringLayout.SOUTH, belongToIdText, 20, SpringLayout.NORTH, belongToIdText);
		
		// filter
		tagLabel.setText("Tag");
		add(tagLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, tagLabel, 10, SpringLayout.SOUTH, moduleLabel);
		tspLayout.putConstraint(SpringLayout.WEST, tagLabel, 10, SpringLayout.EAST, belongToIdText);
		tspLayout.putConstraint(SpringLayout.EAST, tagLabel, labelWidth, SpringLayout.WEST, tagLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, tagLabel, 20, SpringLayout.NORTH, tagLabel);
		
		add(tagText);
		tspLayout.putConstraint(SpringLayout.NORTH, tagText, 10, SpringLayout.SOUTH, moduleLabel);
		tspLayout.putConstraint(SpringLayout.WEST, tagText, 10, SpringLayout.EAST, tagLabel);
		tspLayout.putConstraint(SpringLayout.EAST, tagText, -10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, tagText, 20, SpringLayout.NORTH, tagText);
		
		// -----------------------------------------------------------------------------------------------------
		queryButton.setText("Query");
		add(queryButton);
		tspLayout.putConstraint(SpringLayout.NORTH, queryButton, 10, SpringLayout.SOUTH, tagLabel);
		tspLayout.putConstraint(SpringLayout.WEST, queryButton, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, queryButton, buttonWidth, SpringLayout.WEST, queryButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, queryButton, 20, SpringLayout.NORTH, queryButton);
		queryButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dataTagFSM.addEvent(new DataTagEvent(DataTagEventEnum.Search, getDataTagParameter()));
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
			
		});
		
		addButton.setText("Add");
		add(addButton);
		tspLayout.putConstraint(SpringLayout.NORTH, addButton, 10, SpringLayout.SOUTH, tagLabel);
		tspLayout.putConstraint(SpringLayout.WEST, addButton, 10, SpringLayout.EAST, queryButton);
		tspLayout.putConstraint(SpringLayout.EAST, addButton, buttonWidth, SpringLayout.WEST, addButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, addButton, 20, SpringLayout.NORTH, addButton);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dataTagFSM.addEvent(new DataTagEvent(DataTagEventEnum.Insert, getDataTagParameter()));
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
			
		});
		
		changeButton.setText("Change");
		add(changeButton);
		tspLayout.putConstraint(SpringLayout.NORTH, changeButton, 10, SpringLayout.SOUTH, tagLabel);
		tspLayout.putConstraint(SpringLayout.WEST, changeButton, 10, SpringLayout.EAST, addButton);
		tspLayout.putConstraint(SpringLayout.EAST, changeButton, buttonWidth, SpringLayout.WEST, changeButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, changeButton, 20, SpringLayout.NORTH, changeButton);
		changeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dataTagFSM.addEvent(new DataTagEvent(DataTagEventEnum.Update, getDataTagParameter()));
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
			
		});
		
		deleteButton.setText("Delete");
		add(deleteButton);
		tspLayout.putConstraint(SpringLayout.NORTH, deleteButton, 10, SpringLayout.SOUTH, tagLabel);
		tspLayout.putConstraint(SpringLayout.WEST, deleteButton, 10, SpringLayout.EAST, changeButton);
		tspLayout.putConstraint(SpringLayout.EAST, deleteButton, buttonWidth, SpringLayout.WEST, deleteButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, deleteButton, 20, SpringLayout.NORTH, deleteButton);
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dataTagFSM.addEvent(new DataTagEvent(DataTagEventEnum.Delete, getDataTagParameter()));
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
		
		table.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e) {
		    	DataTagParameter dataTagParameter = new DataTagParameter();
		    	int selectedRow = table.getSelectedRow();
		    	
		    	dataTagParameter.setId(Long.parseLong((String) table.getValueAt(selectedRow, 0)));
		    	dataTagParameter.setBelongToServiceId(Long.parseLong((String) table.getValueAt(selectedRow, 1)));
		    	dataTagParameter.setTag((String) table.getValueAt(selectedRow, 2));
		    	
		    	try {
		    		dataTagFSM.addEvent(new DataTagEvent(DataTagEventEnum.Display, dataTagParameter));
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
	

	public DataTagParameter getDataTagParameter() {
		DataTagParameter dataTagParameter = new DataTagParameter();
		
		if (idText.getText() != null && idText.getText().equals("") == false) {
			dataTagParameter.setId(Long.parseLong(idText.getText()));
		}
		
		if (belongToIdText.getText() != null && belongToIdText.getText().equals("") == false) {
			dataTagParameter.setBelongToServiceId(Long.parseLong(belongToIdText.getText()));
		}
		
		if (tagText.getText() != null && tagText.getText().equals("") == false) {
			dataTagParameter.setTag(tagText.getText());
		}
		
		return dataTagParameter;
	}
	
	public void setEnnMonitorLogConfigEventTables(List<EnnMonitorLogConfigEventTable> tables) {
		int i;
				
		EnnMonitorLogConfigEventTable eventTable = null;
				
		Vector<String> rowData = null;
		DefaultTableModel defaultTableModel = null;
				
		defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.getDataVector().clear();
		for (i = 0; i < tables.size(); ++i) {
			rowData = new Vector<String>();
			eventTable = tables.get(i);
			
			rowData.add("" + eventTable.getId());
			rowData.add("" + eventTable.getBelongToServiceId());
			if (eventTable.getEventName() != null && eventTable.getEventName().equals("") == false) {
				rowData.add(eventTable.getEventName());
			} else {
				rowData.add("");
			}
			
			defaultTableModel.getDataVector().add(rowData);
		}
		
		table.updateUI();
	}
	
	public void displayEnnMonitorLogConfigEventTable(DataTagParameter dataTagParameter) {
		idText.setText("" + dataTagParameter.getId());
		belongToIdText.setText("" + dataTagParameter.getBelongToServiceId());
		tagText.setText(dataTagParameter.getTag());
	}

}
