package enn.monitor.demo.coal.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import enn.monitor.framework.common.time.EnnDatetimeUtil;
import enn.monitor.framework.common.time.EnnTimezoneUtil;

public class CoalEventJPanel extends JPanel {

	private static final long serialVersionUID = -6195834123155869677L;
	
	private final String columnHeader[] = {"时间", "日志"};
	private JTable table = new JTable(new DefaultTableModel());
	private JScrollPane scrollPane = new JScrollPane(table);
	
	private List<EventLog> eventLogList = new ArrayList<EventLog>();
	
	private int sensor1 = -1000;
	private int sensor2 = -1000;
	private int bellow1 = -1000;
	private int bellow2 = -1000;
	
	public CoalEventJPanel() {
		DefaultTableModel defaultTableModel = null;
		
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setColumnIdentifiers(columnHeader);
		table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(150);
		table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(150);
		add(scrollPane);
		tspLayout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, this);
	}
	
	public void setSensor1(int value) {
		if (sensor1 != value) {
			sensor1 = value;
			logSensor(value, "传感器1");
			updateUI();
		}
	}
	
	public void setSensor2(int value) {
		if (sensor2 != value) {
			sensor2 = value;
			logSensor(value, "传感器2");
			updateUI();
		}
	}
	
	public void setBellow1(int value) {
		if (bellow1 != value) {
			bellow1 = value;
			logBellow(value, "鼓风机1");
			updateUI();
		}
	}
	
	public void setBellow2(int value) {
		if (bellow2 != value) {
			bellow2 = value;
			logBellow(value, "鼓风机2");
			updateUI();
		}
	}
	
	private void logBellow(int value, String name) {
		EventLog eventLog = new EventLog();
		
		eventLog.createDateTime = System.currentTimeMillis();
		switch (value) {
		case -1:
			eventLog.log = name + "收不到数据";
			break;
		case 0:
			eventLog.log = name + "正常速度";
			break;
		case 1:
			eventLog.log = name + "加快速度";
			break;
		}
		
		eventLogList.add(eventLog);
	}
	
	private void logSensor(int value, String name) {
		EventLog eventLog = new EventLog();
		
		eventLog.createDateTime = System.currentTimeMillis();
		switch (value) {
		case -1:
			eventLog.log = name + "收不到数据";
			break;
		case 0:
			eventLog.log = name + "正常";
			break;
		case 1:
			eventLog.log = name + "报警";
			break;
		case 2:
			eventLog.log = name + "通知相关人员";
			break;
		case 3:
			eventLog.log = name + "断电";
			break;
		}
		
		eventLogList.add(eventLog);
	}
	
	protected void paintComponent(Graphics g) {
		int i;
		
		Vector<String> rowData = null;
		DefaultTableModel defaultTableModel = null;
		
		super.paintComponent(g);
		setBackground(new Color(248, 250, 241));
		
		defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.getDataVector().clear();
		
		while (eventLogList.size() > 100) {
			eventLogList.remove(0);
		}
		
		for (i = eventLogList.size() - 1; i >= 0; --i) {
			rowData = new Vector<String>();
			
			rowData.add(EnnDatetimeUtil.convertLongToStringWithDateTime(eventLogList.get(i).createDateTime, EnnTimezoneUtil.getChinaTimeZone()));
			rowData.add(eventLogList.get(i).log);
			
			defaultTableModel.getDataVector().add(rowData);
		}
		
		table.updateUI();
	}
	
	private class EventLog {
		public long createDateTime;
		public String log;
	}

}
