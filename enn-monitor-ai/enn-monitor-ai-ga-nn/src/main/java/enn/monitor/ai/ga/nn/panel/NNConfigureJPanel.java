package enn.monitor.ai.ga.nn.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import enn.monitor.ai.ga.nn.common.NNConfigureParameter;
import enn.monitor.framework.ai.nn.activation.NNActivationEnum;

public class NNConfigureJPanel extends JPanel {

	private static final long serialVersionUID = -82236800496408040L;
	
	private JLabel inputLabel = new JLabel();
	private JTextField inputText = new JTextField();
	private JButton inputButton = new JButton();
	
	private JLabel outputLabel = new JLabel();
	private JTextField outputText = new JTextField();
	private JButton outputButton = new JButton();
	
	private JLabel hiddenLayerLabel = new JLabel();
	private JTextField hiddenLayerText = new JTextField();
	private JButton addButton = new JButton();
	
	private JLabel deleteHiddenLayerLabel = new JLabel();
	private JTextField deleteHiddenLayerText = new JTextField();
	private JButton delButton = new JButton();
	
	private JLabel weightOffLabel = new JLabel();
	private JTextField weightOffText = new JTextField();
	
	private final String columnHeader[] = {"ID", "类型", "神经元数目"};
	private JTable table = new JTable(new DefaultTableModel());
	private JScrollPane scrollPane = new JScrollPane(table);
	
	private JLabel activationLabel = new JLabel();
	private JComboBox activationComboBox = null;
	
	private NNConfigureParameter nnConfigureParameter = new NNConfigureParameter();
	
	private int labelWidth = 80;
	private int textWidth = 100;
	private int buttonWidth = 80;
	
	public NNConfigureJPanel() {
		DefaultTableModel defaultTableModel = null;
		
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		// 输入
		inputLabel.setText("输入");
		add(inputLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, inputLabel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, inputLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, inputLabel, labelWidth, SpringLayout.WEST, inputLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, inputLabel, 20, SpringLayout.NORTH, inputLabel);
		
		add(inputText);
		tspLayout.putConstraint(SpringLayout.NORTH, inputText, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, inputText, 10, SpringLayout.EAST, inputLabel);
		tspLayout.putConstraint(SpringLayout.EAST, inputText, textWidth, SpringLayout.WEST, inputText);
		tspLayout.putConstraint(SpringLayout.SOUTH, inputText, 20, SpringLayout.NORTH, inputText);
		
		inputButton.setText("更新");
		add(inputButton);
		tspLayout.putConstraint(SpringLayout.NORTH, inputButton, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, inputButton, 10, SpringLayout.EAST, inputText);
		tspLayout.putConstraint(SpringLayout.EAST, inputButton, buttonWidth, SpringLayout.WEST, inputButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, inputButton, 20, SpringLayout.NORTH, inputButton);
		
		inputButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nnConfigureParameter.updateInputLayer(Integer.parseInt(inputText.getText()));
				updateTable();
			}
			
		});
		
		// 输出
		outputLabel.setText("输出");
		add(outputLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, outputLabel, 10, SpringLayout.SOUTH, inputLabel);
		tspLayout.putConstraint(SpringLayout.WEST, outputLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, outputLabel, labelWidth, SpringLayout.WEST, outputLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, outputLabel, 20, SpringLayout.NORTH, outputLabel);
		
		add(outputText);
		tspLayout.putConstraint(SpringLayout.NORTH, outputText, 10, SpringLayout.SOUTH, inputText);
		tspLayout.putConstraint(SpringLayout.WEST, outputText, 10, SpringLayout.EAST, outputLabel);
		tspLayout.putConstraint(SpringLayout.EAST, outputText, textWidth, SpringLayout.WEST, outputText);
		tspLayout.putConstraint(SpringLayout.SOUTH, outputText, 20, SpringLayout.NORTH, outputText);
		
		outputButton.setText("更新");
		add(outputButton);
		tspLayout.putConstraint(SpringLayout.NORTH, outputButton, 10, SpringLayout.SOUTH, inputButton);
		tspLayout.putConstraint(SpringLayout.WEST, outputButton, 10, SpringLayout.EAST, outputText);
		tspLayout.putConstraint(SpringLayout.EAST, outputButton, buttonWidth, SpringLayout.WEST, outputButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, outputButton, 20, SpringLayout.NORTH, outputButton);
		
		outputButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nnConfigureParameter.updateOuputLayer(Integer.parseInt(outputText.getText()));
				updateTable();
			}
			
		});
		
		// 添加隐藏层
		hiddenLayerLabel.setText("添加隐藏层");
		add(hiddenLayerLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, hiddenLayerLabel, 10, SpringLayout.SOUTH, outputLabel);
		tspLayout.putConstraint(SpringLayout.WEST, hiddenLayerLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, hiddenLayerLabel, labelWidth, SpringLayout.WEST, hiddenLayerLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, hiddenLayerLabel, 20, SpringLayout.NORTH, hiddenLayerLabel);
		
		add(hiddenLayerText);
		tspLayout.putConstraint(SpringLayout.NORTH, hiddenLayerText, 10, SpringLayout.SOUTH, outputText);
		tspLayout.putConstraint(SpringLayout.WEST, hiddenLayerText, 10, SpringLayout.EAST, hiddenLayerLabel);
		tspLayout.putConstraint(SpringLayout.EAST, hiddenLayerText, textWidth, SpringLayout.WEST, hiddenLayerText);
		tspLayout.putConstraint(SpringLayout.SOUTH, hiddenLayerText, 20, SpringLayout.NORTH, hiddenLayerText);
		
		addButton.setText("添加");
		add(addButton);
		tspLayout.putConstraint(SpringLayout.NORTH, addButton, 10, SpringLayout.SOUTH, outputButton);
		tspLayout.putConstraint(SpringLayout.WEST, addButton, 10, SpringLayout.EAST, hiddenLayerText);
		tspLayout.putConstraint(SpringLayout.EAST, addButton, buttonWidth, SpringLayout.WEST, addButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, addButton, 20, SpringLayout.NORTH, addButton);
		
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nnConfigureParameter.addHiddenLayer(Integer.parseInt(hiddenLayerText.getText()));
				updateTable();
			}
			
		});
		
		//删除隐藏层
		deleteHiddenLayerLabel.setText("删除隐藏层");
		add(deleteHiddenLayerLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, deleteHiddenLayerLabel, 10, SpringLayout.SOUTH, hiddenLayerLabel);
		tspLayout.putConstraint(SpringLayout.WEST, deleteHiddenLayerLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, deleteHiddenLayerLabel, labelWidth, SpringLayout.WEST, deleteHiddenLayerLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, deleteHiddenLayerLabel, 20, SpringLayout.NORTH, deleteHiddenLayerLabel);
		
		add(deleteHiddenLayerText);
		tspLayout.putConstraint(SpringLayout.NORTH, deleteHiddenLayerText, 10, SpringLayout.SOUTH, hiddenLayerText);
		tspLayout.putConstraint(SpringLayout.WEST, deleteHiddenLayerText, 10, SpringLayout.EAST, deleteHiddenLayerLabel);
		tspLayout.putConstraint(SpringLayout.EAST, deleteHiddenLayerText, textWidth, SpringLayout.WEST, deleteHiddenLayerText);
		tspLayout.putConstraint(SpringLayout.SOUTH, deleteHiddenLayerText, 20, SpringLayout.NORTH, deleteHiddenLayerText);
		
		delButton.setText("删除");
		add(delButton);
		tspLayout.putConstraint(SpringLayout.NORTH, delButton, 10, SpringLayout.SOUTH, hiddenLayerText);
		tspLayout.putConstraint(SpringLayout.WEST, delButton, 10, SpringLayout.EAST, deleteHiddenLayerText);
		tspLayout.putConstraint(SpringLayout.EAST, delButton, buttonWidth, SpringLayout.WEST, delButton);
		tspLayout.putConstraint(SpringLayout.SOUTH, delButton, 20, SpringLayout.NORTH, delButton);
		
		delButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nnConfigureParameter.deleteHiddenLayer(Integer.parseInt(deleteHiddenLayerText.getText()));
				updateTable();
			}
			
		});
		
		// 权重偏移量
		weightOffLabel.setText("权重偏移量");
		add(weightOffLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, weightOffLabel, 10, SpringLayout.SOUTH, deleteHiddenLayerLabel);
		tspLayout.putConstraint(SpringLayout.WEST, weightOffLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, weightOffLabel, labelWidth, SpringLayout.WEST, weightOffLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, weightOffLabel, 20, SpringLayout.NORTH, weightOffLabel);
		
		weightOffText.setText("1");
		add(weightOffText);
		tspLayout.putConstraint(SpringLayout.NORTH, weightOffText, 10, SpringLayout.SOUTH, deleteHiddenLayerText);
		tspLayout.putConstraint(SpringLayout.WEST, weightOffText, 10, SpringLayout.EAST, weightOffLabel);
		tspLayout.putConstraint(SpringLayout.EAST, weightOffText, textWidth, SpringLayout.WEST, weightOffText);
		tspLayout.putConstraint(SpringLayout.SOUTH, weightOffText, 20, SpringLayout.NORTH, weightOffText);
		
		// 激活函数
		activationLabel.setText("激励函数");
		add(activationLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, activationLabel, 10, SpringLayout.SOUTH, weightOffLabel);
		tspLayout.putConstraint(SpringLayout.WEST, activationLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, activationLabel, labelWidth, SpringLayout.WEST, activationLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, activationLabel, 20, SpringLayout.NORTH, activationLabel);
		
		List<String> activationList = new ArrayList<String>();
		for (NNActivationEnum item : NNActivationEnum.values()) {
			activationList.add(item.value());
		}
		activationComboBox = new JComboBox(activationList.toArray());
		add(activationComboBox);
		tspLayout.putConstraint(SpringLayout.NORTH, activationComboBox, 10, SpringLayout.SOUTH, weightOffText);
		tspLayout.putConstraint(SpringLayout.WEST, activationComboBox, 10, SpringLayout.EAST, activationLabel);
		tspLayout.putConstraint(SpringLayout.EAST, activationComboBox, textWidth, SpringLayout.WEST, activationComboBox);
		tspLayout.putConstraint(SpringLayout.SOUTH, activationComboBox, 20, SpringLayout.NORTH, activationComboBox);
		
		// 表格
		add(scrollPane);
		tspLayout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.EAST, addButton);
		tspLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, this);
		
		defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setColumnIdentifiers(columnHeader);
		
		updateTable();
	}
	
	public NNConfigureParameter getNNConfigureParameter() {
		NNActivationEnum nnActivationEnum = null;
		
		nnConfigureParameter.setwOff(Double.parseDouble(weightOffText.getText()));
		nnActivationEnum = NNActivationEnum.valueOf(activationComboBox.getSelectedItem().toString());
		nnConfigureParameter.setNnActivationEnum(nnActivationEnum);
		
		return nnConfigureParameter;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(248, 250, 241));
	}
	
	private void updateTable() {
		int i;
		
		Vector<String> rowData = null;
		DefaultTableModel defaultTableModel = null;
		
		defaultTableModel = (DefaultTableModel) table.getModel();
		
		defaultTableModel.getDataVector().clear();
		
		for (i = 0; i < nnConfigureParameter.getNNLayer().size(); ++i) {
			rowData = new Vector<String>();
			
			rowData.add("" + nnConfigureParameter.getNNLayer().get(i).id);
			rowData.add(nnConfigureParameter.getNNLayer().get(i).type);
			rowData.add("" + nnConfigureParameter.getNNLayer().get(i).numberOfNeuron);
			
			defaultTableModel.getDataVector().add(rowData);
		}
		
		table.updateUI();
	}
}
