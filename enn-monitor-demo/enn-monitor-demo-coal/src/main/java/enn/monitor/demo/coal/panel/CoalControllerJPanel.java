package enn.monitor.demo.coal.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import enn.monitor.demo.coal.resource.BellowProducer;
import enn.monitor.demo.coal.resource.GasProducer;
import enn.monitor.demo.coal.resource.SensorProducer;

public class CoalControllerJPanel extends JPanel {

	private static final long serialVersionUID = -3585864668653679189L;
	
	private JLabel sensor1 = new JLabel();
	private ButtonGroup sensor1Group = new ButtonGroup();
	private JRadioButton s1Good = new JRadioButton("好", true);  
	private JRadioButton s1NotGood = new JRadioButton("坏");
    private SensorListener sensor1Listener = new SensorListener(s1Good, s1NotGood);
	
	private JLabel sensor2 = new JLabel();
	private ButtonGroup sensor2Group = new ButtonGroup();
	private JRadioButton s2Good = new JRadioButton("好", true);  
	private JRadioButton s2NotGood = new JRadioButton("坏");
    private SensorListener sensor2Listener = new SensorListener(s2Good, s2NotGood);
    
    private JLabel bellow1 = new JLabel();
    private ButtonGroup bellow1Group = new ButtonGroup();
    private JRadioButton b1Good = new JRadioButton("好", true);  
    private JRadioButton b1NotGood = new JRadioButton("坏");
    private BellowListener bellow1Listener = new BellowListener(b1Good, b1NotGood);
    
    private JLabel bellow2 = new JLabel();
    private ButtonGroup bellow2Group = new ButtonGroup();
    private JRadioButton b2Good = new JRadioButton("好", true);  
    private JRadioButton b2NotGood = new JRadioButton("坏");
    private BellowListener bellow2Listener = new BellowListener(b2Good, b2NotGood);
    
	private JLabel gasSource = new JLabel();
	private JTextField gasText = new JTextField();
	private JLabel gasUnit = new JLabel();
	private JButton gasBtn = new JButton();
	
	private GasProducer gasProducer = null;
	
	public CoalControllerJPanel() {
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		// 传感器1
		sensor1.setText("传感器1");
		add(sensor1);
		tspLayout.putConstraint(SpringLayout.NORTH, sensor1, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, sensor1, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, sensor1, 100, SpringLayout.WEST, sensor1);
		tspLayout.putConstraint(SpringLayout.SOUTH, sensor1, 20, SpringLayout.NORTH, sensor1);
		
		sensor1Group.add(s1Good);
		sensor1Group.add(s1NotGood);
		add(s1Good);
		tspLayout.putConstraint(SpringLayout.NORTH, s1Good, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, s1Good, 10, SpringLayout.EAST, sensor1);
		tspLayout.putConstraint(SpringLayout.EAST, s1Good, 50, SpringLayout.WEST, s1Good);
		tspLayout.putConstraint(SpringLayout.SOUTH, s1Good, 20, SpringLayout.NORTH, s1Good);
		s1Good.addActionListener(sensor1Listener);
		
		add(s1NotGood);
		tspLayout.putConstraint(SpringLayout.NORTH, s1NotGood, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, s1NotGood, 10, SpringLayout.EAST, s1Good);
		tspLayout.putConstraint(SpringLayout.EAST, s1NotGood, 50, SpringLayout.WEST, s1NotGood);
		tspLayout.putConstraint(SpringLayout.SOUTH, s1NotGood, 20, SpringLayout.NORTH, s1NotGood);
		s1NotGood.addActionListener(sensor1Listener);
		
		// 传感器2
		sensor2.setText("传感器2");
		add(sensor2);
		tspLayout.putConstraint(SpringLayout.NORTH, sensor2, 10, SpringLayout.SOUTH, sensor1);
		tspLayout.putConstraint(SpringLayout.WEST, sensor2, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, sensor2, 100, SpringLayout.WEST, sensor2);
		tspLayout.putConstraint(SpringLayout.SOUTH, sensor2, 20, SpringLayout.NORTH, sensor2);
		
		sensor2Group.add(s2Good);
		sensor2Group.add(s2NotGood);
		add(s2Good);
		tspLayout.putConstraint(SpringLayout.NORTH, s2Good, 10, SpringLayout.SOUTH, s1Good);
		tspLayout.putConstraint(SpringLayout.WEST, s2Good, 10, SpringLayout.EAST, sensor2);
		tspLayout.putConstraint(SpringLayout.EAST, s2Good, 50, SpringLayout.WEST, s2Good);
		tspLayout.putConstraint(SpringLayout.SOUTH, s2Good, 20, SpringLayout.NORTH, s2Good);
		s2Good.addActionListener(sensor2Listener);
		
		add(s2NotGood);
		tspLayout.putConstraint(SpringLayout.NORTH, s2NotGood, 10, SpringLayout.SOUTH, s1NotGood);
		tspLayout.putConstraint(SpringLayout.WEST, s2NotGood, 10, SpringLayout.EAST, s2Good);
		tspLayout.putConstraint(SpringLayout.EAST, s2NotGood, 50, SpringLayout.WEST, s2NotGood);
		tspLayout.putConstraint(SpringLayout.SOUTH, s2NotGood, 20, SpringLayout.NORTH, s2NotGood);
		s2NotGood.addActionListener(sensor2Listener);
		
		// 鼓风机1
		bellow1.setText("鼓风机1");
		add(bellow1);
		tspLayout.putConstraint(SpringLayout.NORTH, bellow1, 10, SpringLayout.SOUTH, sensor2);
		tspLayout.putConstraint(SpringLayout.WEST, bellow1, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, bellow1, 100, SpringLayout.WEST, bellow1);
		tspLayout.putConstraint(SpringLayout.SOUTH, bellow1, 20, SpringLayout.NORTH, bellow1);
		
		bellow1Group.add(b1Good);
		bellow1Group.add(b1NotGood);
		add(b1Good);
		tspLayout.putConstraint(SpringLayout.NORTH, b1Good, 10, SpringLayout.SOUTH, s2Good);
		tspLayout.putConstraint(SpringLayout.WEST, b1Good, 10, SpringLayout.EAST, bellow1);
		tspLayout.putConstraint(SpringLayout.EAST, b1Good, 50, SpringLayout.WEST, b1Good);
		tspLayout.putConstraint(SpringLayout.SOUTH, b1Good, 20, SpringLayout.NORTH, b1Good);
		b1Good.addActionListener(bellow1Listener);
		
		add(b1NotGood);
		tspLayout.putConstraint(SpringLayout.NORTH, b1NotGood, 10, SpringLayout.SOUTH, s2NotGood);
		tspLayout.putConstraint(SpringLayout.WEST, b1NotGood, 10, SpringLayout.EAST, b1Good);
		tspLayout.putConstraint(SpringLayout.EAST, b1NotGood, 50, SpringLayout.WEST, b1NotGood);
		tspLayout.putConstraint(SpringLayout.SOUTH, b1NotGood, 20, SpringLayout.NORTH, b1NotGood);
		b1NotGood.addActionListener(bellow1Listener);
		
		// 鼓风机2
		bellow2.setText("鼓风机2");
		add(bellow2);
		tspLayout.putConstraint(SpringLayout.NORTH, bellow2, 10, SpringLayout.SOUTH, bellow1);
		tspLayout.putConstraint(SpringLayout.WEST, bellow2, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, bellow2, 100, SpringLayout.WEST, bellow2);
		tspLayout.putConstraint(SpringLayout.SOUTH, bellow2, 20, SpringLayout.NORTH, bellow2);
		
		bellow2Group.add(b2Good);
		bellow2Group.add(b2NotGood);
		add(b2Good);
		tspLayout.putConstraint(SpringLayout.NORTH, b2Good, 10, SpringLayout.SOUTH, b1Good);
		tspLayout.putConstraint(SpringLayout.WEST, b2Good, 10, SpringLayout.EAST, bellow2);
		tspLayout.putConstraint(SpringLayout.EAST, b2Good, 50, SpringLayout.WEST, b2Good);
		tspLayout.putConstraint(SpringLayout.SOUTH, b2Good, 20, SpringLayout.NORTH, b2Good);
		b2Good.addActionListener(bellow2Listener);
		
		add(b2NotGood);
		tspLayout.putConstraint(SpringLayout.NORTH, b2NotGood, 10, SpringLayout.SOUTH, b1NotGood);
		tspLayout.putConstraint(SpringLayout.WEST, b2NotGood, 10, SpringLayout.EAST, b2Good);
		tspLayout.putConstraint(SpringLayout.EAST, b2NotGood, 50, SpringLayout.WEST, b2NotGood);
		tspLayout.putConstraint(SpringLayout.SOUTH, b2NotGood, 20, SpringLayout.NORTH, b2NotGood);
		b2NotGood.addActionListener(bellow2Listener);
		
		// 瓦斯
		gasSource.setText("瓦斯产生速度");
		add(gasSource);
		tspLayout.putConstraint(SpringLayout.NORTH, gasSource, 10, SpringLayout.SOUTH, bellow2);
		tspLayout.putConstraint(SpringLayout.WEST, gasSource, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, gasSource, 100, SpringLayout.WEST, gasSource);
		tspLayout.putConstraint(SpringLayout.SOUTH, gasSource, 20, SpringLayout.NORTH, gasSource);
		
		gasText.setText("0.1");
		add(gasText);
		tspLayout.putConstraint(SpringLayout.NORTH, gasText, 10, SpringLayout.SOUTH, b2Good);
		tspLayout.putConstraint(SpringLayout.WEST, gasText, 10, SpringLayout.EAST, gasSource);
		tspLayout.putConstraint(SpringLayout.EAST, gasText, 50, SpringLayout.WEST, gasText);
		tspLayout.putConstraint(SpringLayout.SOUTH, gasText, 20, SpringLayout.NORTH, gasText);
		
		gasUnit.setText("%");
		add(gasUnit);
		tspLayout.putConstraint(SpringLayout.NORTH, gasUnit, 10, SpringLayout.SOUTH, b2Good);
		tspLayout.putConstraint(SpringLayout.WEST, gasUnit, 5, SpringLayout.EAST, gasText);
		tspLayout.putConstraint(SpringLayout.EAST, gasUnit, 10, SpringLayout.WEST, gasUnit);
		tspLayout.putConstraint(SpringLayout.SOUTH, gasUnit, 20, SpringLayout.NORTH, gasUnit);
		
		gasBtn.setText("生效");
		add(gasBtn);
		tspLayout.putConstraint(SpringLayout.NORTH, gasBtn, 10, SpringLayout.SOUTH, b2Good);
		tspLayout.putConstraint(SpringLayout.WEST, gasBtn, 10, SpringLayout.EAST, gasUnit);
		tspLayout.putConstraint(SpringLayout.EAST, gasBtn, 80, SpringLayout.WEST, gasBtn);
		tspLayout.putConstraint(SpringLayout.SOUTH, gasBtn, 20, SpringLayout.NORTH, gasBtn);
		gasBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gasText.getText() == null) {
					return;
				}
				
				gasProducer.setGasV(Double.parseDouble(gasText.getText()));
			}
			
		});
	}
	
	private class SensorListener implements  ActionListener {
		private JRadioButton good = null;
		private JRadioButton notGood = null;
		
		private SensorProducer sensorProducer = null;
		
		public SensorListener(JRadioButton good, JRadioButton notGood) {
			this.good = good;
			this.notGood = notGood;
		}
		
		public void actionPerformed(ActionEvent e){
			if (sensorProducer == null) {
				return;
			}
			
			if (e.getSource() == good) {
				sensorProducer.setGood();
			} else if (e.getSource() == notGood) {
				sensorProducer.setNotGood();
			}
		}
		
		public void setSensorProducer(SensorProducer sensorProducer) {
			this.sensorProducer = sensorProducer;
		}
	}
	
	private class BellowListener implements  ActionListener {
		private JRadioButton good = null;
		private JRadioButton notGood = null;
		
		private BellowProducer bellowProducer = null;
		
		public BellowListener(JRadioButton good, JRadioButton notGood) {
			this.good = good;
			this.notGood = notGood;
		}
		
		public void actionPerformed(ActionEvent e){
			if (bellowProducer == null) {
				return;
			}
			
			if (e.getSource() == good) {
				bellowProducer.setGood();
			} else if (e.getSource() == notGood) {
				bellowProducer.setNotGood();
			}
		}
		
		public void setBellowProducer(BellowProducer bellowProducer) {
			this.bellowProducer = bellowProducer;
		}
	 }
	
	public void addSensorProducer(SensorProducer sensorProducer) {
		switch (sensorProducer.getSensorName()) {
		case "sensor1":
			sensor1Listener.setSensorProducer(sensorProducer);
			break;
		case "sensor2":
			sensor2Listener.setSensorProducer(sensorProducer);
			break;
		}
	}
	
	public void addBellowProducer(BellowProducer bellowProducer) {
		switch (bellowProducer.getBellowName()) {
		case "bellow1":
			bellow1Listener.setBellowProducer(bellowProducer);
			break;
		case "bellow2":
			bellow2Listener.setBellowProducer(bellowProducer);
			break;
		}
	}
	
	public void setGasProducer(GasProducer gasProducer) {
		this.gasProducer = gasProducer;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(248, 250, 241));
	}

}
