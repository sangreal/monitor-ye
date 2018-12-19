package enn.monitor.log.ai.train.test.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.log.ai.train.test.core.TestEvent;
import enn.monitor.log.ai.train.test.core.TestEventEnum;
import enn.monitor.log.ai.train.test.core.TestFSM;
import enn.monitor.log.ai.train.test.parameter.TestRunDisplayParameter;
import enn.monitor.log.ai.train.test.parameter.TestRunParameter;

public class TestRunJPanel extends JPanel {

	private static final long serialVersionUID = 5622983750701533689L;
	
	private static final Logger logger = LogManager.getLogger();
	
	private JLabel dataLabel = new JLabel();
	private JTextField dataText = new JTextField();
	
	private JButton runBtn = new JButton();
	
	private final String columnHeader[] = {"Result", "Match"};
	private JTable table = new JTable(new DefaultTableModel());
	private JScrollPane scrollPane = new JScrollPane(table);
	
	public TestRunJPanel(TestFSM testFSM) {
		DefaultTableModel defaultTableModel = null;
		
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		dataLabel.setText("Data");
		add(dataLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, dataLabel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, dataLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, dataLabel, 50, SpringLayout.WEST, dataLabel);
		
		add(dataText);
		tspLayout.putConstraint(SpringLayout.NORTH, dataText, 9, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, dataText, 10, SpringLayout.EAST, dataLabel);
		tspLayout.putConstraint(SpringLayout.EAST, dataText, -10, SpringLayout.EAST, this);
		
		runBtn.setText("Test");
		add(runBtn);
		tspLayout.putConstraint(SpringLayout.NORTH, runBtn, 10, SpringLayout.SOUTH, dataLabel);
		tspLayout.putConstraint(SpringLayout.WEST, runBtn, 10, SpringLayout.WEST, this);
		runBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					testFSM.addEvent(new TestEvent(TestEventEnum.Test, getTestRunParameter()));
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
			
		});
		
		add(scrollPane);
		tspLayout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.SOUTH, runBtn);
		tspLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, this);
		
		defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setColumnIdentifiers(columnHeader);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(248, 250, 241));
	}
	
	public TestRunParameter getTestRunParameter() {
		TestRunParameter testRunParameter = new TestRunParameter();
		
		testRunParameter.setData(dataText.getText());
		
		return testRunParameter;
	}
	
	public void displayTestResult(List<TestRunDisplayParameter> displayList) {
		int i;
		
		Vector<String> rowData = null;
		DefaultTableModel defaultTableModel = null;
		
		defaultTableModel = (DefaultTableModel) table.getModel();
		
		defaultTableModel.getDataVector().clear();
		
		Collections.sort(displayList, new Comparator<TestRunDisplayParameter>() {

			@Override
			public int compare(TestRunDisplayParameter o1, TestRunDisplayParameter o2) {
				if (o1.getMatch() > o2.getMatch()) {
					return -1;
				}
				if (o1.getMatch() < o2.getMatch()) {
					return 1;
				}
				return 0;
			}
			
		});
		
		for (i = 0; i < displayList.size(); ++i) {
			rowData = new Vector<String>();
			
			rowData.add(displayList.get(i).getName());
			rowData.add("" + displayList.get(i).getMatch());
			
			defaultTableModel.getDataVector().add(rowData);
		}
		
		table.updateUI();
	}

}
