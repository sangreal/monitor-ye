package enn.monitor.log.ai.train.nn.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.log.ai.train.nn.core.TrainNNEvent;
import enn.monitor.log.ai.train.nn.core.TrainNNEventEnum;
import enn.monitor.log.ai.train.nn.core.TrainNNFSM;
import enn.monitor.log.ai.train.nn.parameter.TrainNNResultParameter;

public class TrainNNResultJPanel extends JPanel {

	private static final long serialVersionUID = -1396329941639001597L;
	
	private static final Logger logger = LogManager.getLogger();
	
	private int buttonWidth = 120;
	
	JLabel nnModeLable = new JLabel();
	JLabel nnModeText = new JLabel();
	
	private final String columnHeader[] = {"identitiyId", "jobId", "bestGeneration", "bestError", "worstGeneration", "worstError"};
	private JTable table = new JTable(new DefaultTableModel());
	private JScrollPane scrollPane = new JScrollPane(table);
	
	private JButton replaceButton = new JButton();
	private JButton replaceAllButton = new JButton();
	
	public TrainNNResultJPanel(TrainNNFSM trainNNFSM, int labelWidth, int textWidth) {
		TableColumn column = null;
		DefaultTableModel defaultTableModel = null;
		
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
		
		// -----------------------------------------------------------------------------------------------------
		add(scrollPane);
		tspLayout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.SOUTH, nnModeLable);
		tspLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -50, SpringLayout.SOUTH, this);
		
		defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setColumnIdentifiers(columnHeader);
		
		column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(200);
		column.setMinWidth(200);
		column.setMaxWidth(200);
		
		column = table.getColumnModel().getColumn(1);
		column.setPreferredWidth(100);
		column.setMinWidth(100);
		column.setMaxWidth(100);
		
		column = table.getColumnModel().getColumn(2);
		column.setPreferredWidth(100);
		column.setMinWidth(100);
		column.setMaxWidth(100);
		
		column = table.getColumnModel().getColumn(3);
		column.setPreferredWidth(200);
		column.setMinWidth(200);
		column.setMaxWidth(200);
		
		column = table.getColumnModel().getColumn(4);
		column.setPreferredWidth(100);
		column.setMinWidth(100);
		column.setMaxWidth(100);
		
		column = table.getColumnModel().getColumn(5);
		column.setPreferredWidth(200);
		column.setMinWidth(200);
		column.setMaxWidth(200);
		
		// -----------------------------------------------------------------------------------------------------
		replaceButton.setText("Replace");
		add(replaceButton);
		tspLayout.putConstraint(SpringLayout.NORTH, replaceButton, 10, SpringLayout.SOUTH, scrollPane);
		tspLayout.putConstraint(SpringLayout.WEST, replaceButton, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, replaceButton, buttonWidth, SpringLayout.WEST, replaceButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, replaceButton, -10, SpringLayout.SOUTH, this);
		replaceButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					trainNNFSM.addEvent(new TrainNNEvent(TrainNNEventEnum.Replace, getTrainNNResultParameter(false)));
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
			
		});
		
		replaceAllButton.setText("ReplaceAll");
		add(replaceAllButton);
		tspLayout.putConstraint(SpringLayout.NORTH, replaceAllButton, 10, SpringLayout.SOUTH, scrollPane);
		tspLayout.putConstraint(SpringLayout.WEST, replaceAllButton, 10, SpringLayout.EAST, replaceButton);
		tspLayout.putConstraint(SpringLayout.EAST, replaceAllButton, buttonWidth, SpringLayout.WEST, replaceAllButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, replaceAllButton, -10, SpringLayout.SOUTH, this);
		replaceAllButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					trainNNFSM.addEvent(new TrainNNEvent(TrainNNEventEnum.Replace, getTrainNNResultParameter(true)));
				} catch (Exception e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
			
		});
	}
	
	public void setTrainNNResult(TrainNNResultParameter nnResult, boolean isKeep) {
		int i;
		
		Vector<String> rowData = null;
		DefaultTableModel defaultTableModel = null;
		
		if (nnResult.getNnModeEnum() != null) {
			nnModeText.setText("" + nnResult.getNnModeEnum().name());
		}
		
		
		
		if (isKeep == false) {
			defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.getDataVector().clear();
			
			if (nnResult.getJobStatusList() != null && nnResult.getJobStatusList().size() > 0) {
				for (i = 0; i < nnResult.getJobStatusList().size(); ++i) {
					rowData = new Vector<String>();
					
					rowData.add(nnResult.getJobStatusList().get(i).getIdentityId());
					rowData.add("" + nnResult.getJobStatusList().get(i).getJobId());
					rowData.add("" + nnResult.getJobStatusList().get(i).getBestGeneration());
					rowData.add("" + nnResult.getJobStatusList().get(i).getBestError());
					rowData.add("" + nnResult.getJobStatusList().get(i).getWorstGeneration());
					rowData.add("" + nnResult.getJobStatusList().get(i).getWorstError());
					
					defaultTableModel.getDataVector().add(rowData);
				}
			}
		}
		
		table.updateUI();
	}
	
	private TrainNNResultParameter getTrainNNResultParameter(boolean isAll) {
		TrainNNResultParameter trainNNParameter = new TrainNNResultParameter();
		
		int selectedRow = table.getSelectedRow();
		if (isAll == false) {
			trainNNParameter.setIdentityId((String) table.getValueAt(selectedRow, 0));
		}
		trainNNParameter.setJobId(1000l);
		
		return trainNNParameter;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(248, 250, 241));
	}
}
