package enn.monitor.demo.coal.panel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class CoalStatusJPanel extends JPanel {

	private static final long serialVersionUID = -6195834123155869677L;
	
	private JLabel statusLabel = new JLabel();
	private JLabel statusTextLabel = new JLabel();
	
	private JLabel sensor1Label = new JLabel();
	private JLabel sensor1TextLabel = new JLabel();
	private double sensor1 = 0.0;
	
	private JLabel sensor2Label = new JLabel();
	private JLabel sensor2TextLabel = new JLabel();
	private double sensor2 = 0.0;
	
	private JLabel bellows1Label = new JLabel();
	private JLabel bellows1TextLabel = new JLabel();
	private double bellows1 = 0.0;
	
	private JLabel bellows2Label = new JLabel();
	private JLabel bellows2TextLabel = new JLabel();
	private double bellows2 = 0.0;
	
	public CoalStatusJPanel() {
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		statusLabel.setText("坑道状态");
		add(statusLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, statusLabel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, statusLabel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, statusLabel, 100, SpringLayout.WEST, statusLabel);
		tspLayout.putConstraint(SpringLayout.SOUTH, statusLabel, 20, SpringLayout.NORTH, statusLabel);
		
		statusTextLabel.setText("");
		add(statusTextLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, statusTextLabel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, statusTextLabel, 10, SpringLayout.EAST, statusLabel);
		tspLayout.putConstraint(SpringLayout.EAST, statusTextLabel, 10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, statusTextLabel, 20, SpringLayout.NORTH, statusTextLabel);
		
		sensor1Label.setText("传感器1");
		add(sensor1Label);
		tspLayout.putConstraint(SpringLayout.NORTH, sensor1Label, 30, SpringLayout.SOUTH, statusLabel);
		tspLayout.putConstraint(SpringLayout.WEST, sensor1Label, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, sensor1Label, 100, SpringLayout.WEST, sensor1Label);
		tspLayout.putConstraint(SpringLayout.SOUTH, sensor1Label, 20, SpringLayout.NORTH, sensor1Label);
		
		add(sensor1TextLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, sensor1TextLabel, 30, SpringLayout.SOUTH, statusTextLabel);
		tspLayout.putConstraint(SpringLayout.WEST, sensor1TextLabel, 10, SpringLayout.EAST, sensor1Label);
		tspLayout.putConstraint(SpringLayout.EAST, sensor1TextLabel, 10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, sensor1TextLabel, 20, SpringLayout.NORTH, sensor1TextLabel);
		
		sensor2Label.setText("传感器2");
		add(sensor2Label);
		tspLayout.putConstraint(SpringLayout.NORTH, sensor2Label, 10, SpringLayout.SOUTH, sensor1Label);
		tspLayout.putConstraint(SpringLayout.WEST, sensor2Label, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, sensor2Label, 100, SpringLayout.WEST, sensor2Label);
		tspLayout.putConstraint(SpringLayout.SOUTH, sensor2Label, 20, SpringLayout.NORTH, sensor2Label);
		
		add(sensor2TextLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, sensor2TextLabel, 10, SpringLayout.SOUTH, sensor1TextLabel);
		tspLayout.putConstraint(SpringLayout.WEST, sensor2TextLabel, 10, SpringLayout.EAST, sensor2Label);
		tspLayout.putConstraint(SpringLayout.EAST, sensor2TextLabel, 10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, sensor2TextLabel, 20, SpringLayout.NORTH, sensor2TextLabel);
		
		bellows1Label.setText("鼓风机1");
		add(bellows1Label);
		tspLayout.putConstraint(SpringLayout.NORTH, bellows1Label, 10, SpringLayout.SOUTH, sensor2Label);
		tspLayout.putConstraint(SpringLayout.WEST, bellows1Label, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, bellows1Label, 100, SpringLayout.WEST, bellows1Label);
		tspLayout.putConstraint(SpringLayout.SOUTH, bellows1Label, 20, SpringLayout.NORTH, bellows1Label);
		
		add(bellows1TextLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, bellows1TextLabel, 10, SpringLayout.SOUTH, sensor2TextLabel);
		tspLayout.putConstraint(SpringLayout.WEST, bellows1TextLabel, 10, SpringLayout.EAST, bellows1Label);
		tspLayout.putConstraint(SpringLayout.EAST, bellows1TextLabel, 10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, bellows1TextLabel, 20, SpringLayout.NORTH, bellows1TextLabel);
		
		bellows2Label.setText("鼓风机2");
		add(bellows2Label);
		tspLayout.putConstraint(SpringLayout.NORTH, bellows2Label, 10, SpringLayout.SOUTH, bellows1Label);
		tspLayout.putConstraint(SpringLayout.WEST, bellows2Label, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, bellows2Label, 100, SpringLayout.WEST, bellows2Label);
		tspLayout.putConstraint(SpringLayout.SOUTH, bellows2Label, 20, SpringLayout.NORTH, bellows2Label);
		
		bellows2TextLabel.setText("30 m^3/s");
		add(bellows2TextLabel);
		tspLayout.putConstraint(SpringLayout.NORTH, bellows2TextLabel, 10, SpringLayout.SOUTH, bellows1TextLabel);
		tspLayout.putConstraint(SpringLayout.WEST, bellows2TextLabel, 10, SpringLayout.EAST, bellows2Label);
		tspLayout.putConstraint(SpringLayout.EAST, bellows2TextLabel, 10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, bellows2TextLabel, 20, SpringLayout.NORTH, bellows2TextLabel);
	}
	
	public void setSensor1(double sensor1) {
		if (this.sensor1 == sensor1) {
			return;
		}
		this.sensor1 = sensor1;
		this.updateUI();
	}
	
	public void setSensor2(double sensor2) {
		if (this.sensor2 == sensor2) {
			return;
		}
		this.sensor2 = sensor2;
		this.updateUI();
	}
	
	public void setBellow1(double bellows1) {
		if (this.bellows1 == bellows1) {
			return;
		}
		this.bellows1 = bellows1;
		this.updateUI();
	}
	
	public void setBellow2(double bellows2) {
		if (this.bellows2 == bellows2) {
			return;
		}
		this.bellows2 = bellows2;
		this.updateUI();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(248, 250, 241));
		
		if (sensor1 < 0) {
			sensor1TextLabel.setText("设备状态: 传感器收不到数据");
		} else {
			sensor1TextLabel.setText("" + sensor1 + " %");
		}
		
		if (sensor2 < 0) {
			sensor2TextLabel.setText("设备状态: 传感器收不到数据");
		} else {
			sensor2TextLabel.setText("" + sensor2 + " %");
		}
		
		if (bellows1 < 0) {
			bellows1TextLabel.setText("设备状态: 鼓风机收不到数据");
		} else {
			bellows1TextLabel.setText("" + bellows1 + " m^3/s");
		}
		
		if (bellows2 < 0) {
			bellows2TextLabel.setText("设备状态: 鼓风机收不到数据");
		} else {
			bellows2TextLabel.setText("" + bellows2 + " m^3/s");
		}
	}

}
