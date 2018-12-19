package enn.monitor.demo.coal.panel;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import enn.monitor.demo.coal.resource.BellowProducer;
import enn.monitor.demo.coal.resource.GasProducer;
import enn.monitor.demo.coal.resource.SensorProducer;
import enn.monitor.framework.log.channel.ChannelWriteData;

public class CoalJPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 3416189780492359845L;
	
	private CoalControllerJPanel coalControllerJPanel = null;
	private CoalStatusJPanel coalStatusJPanel = null;
	private CoalEventJPanel coalEventJPanel = null;
	private CoalMineJPanel coalMineJPanel = null;
	
	private Map<String, Long> lastUpdateDateTime = new HashMap<String, Long>();
	
	private int width = 400;
	
	// 控制逻辑
	private BlockingQueue<ChannelWriteData> stockQueue = new LinkedBlockingQueue<ChannelWriteData>();
	
	private GasProducer gasProducer = new GasProducer();
	private SensorProducer sensor1Producer = new SensorProducer("sensor1", stockQueue);
	private SensorProducer sensor2Producer = new SensorProducer("sensor2", stockQueue);
	private BellowProducer bellow1Producer = new BellowProducer("bellow1", stockQueue);
	private BellowProducer bellow2Producer = new BellowProducer("bellow2", stockQueue);
	
	public CoalJPanel() {
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		coalControllerJPanel = new CoalControllerJPanel();
		add(coalControllerJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, coalControllerJPanel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, coalControllerJPanel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, coalControllerJPanel, width, SpringLayout.WEST, coalControllerJPanel);
		tspLayout.putConstraint(SpringLayout.SOUTH, coalControllerJPanel, 160, SpringLayout.NORTH, coalControllerJPanel);
		
		coalStatusJPanel = new CoalStatusJPanel();
		add(coalStatusJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, coalStatusJPanel, 10, SpringLayout.SOUTH, coalControllerJPanel);
		tspLayout.putConstraint(SpringLayout.WEST, coalStatusJPanel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, coalStatusJPanel, width, SpringLayout.WEST, coalStatusJPanel);
		tspLayout.putConstraint(SpringLayout.SOUTH, coalStatusJPanel, 190, SpringLayout.NORTH, coalStatusJPanel);
		
		coalEventJPanel = new CoalEventJPanel();
		add(coalEventJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, coalEventJPanel, 10, SpringLayout.SOUTH, coalStatusJPanel);
		tspLayout.putConstraint(SpringLayout.WEST, coalEventJPanel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, coalEventJPanel, width, SpringLayout.WEST, coalEventJPanel);
		tspLayout.putConstraint(SpringLayout.SOUTH, coalEventJPanel, -10, SpringLayout.SOUTH, this);
		
		coalMineJPanel = new CoalMineJPanel();
		add(coalMineJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, coalMineJPanel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, coalMineJPanel, 10, SpringLayout.EAST, coalControllerJPanel);
		tspLayout.putConstraint(SpringLayout.EAST, coalMineJPanel, -10, SpringLayout.EAST, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, coalMineJPanel, -10, SpringLayout.SOUTH, this);
		
		sensor1Producer.start();
		sensor2Producer.start();
		coalControllerJPanel.addSensorProducer(sensor1Producer);
		coalControllerJPanel.addSensorProducer(sensor2Producer);
		
		gasProducer.addSensorProducer(sensor1Producer);
		gasProducer.addSensorProducer(sensor2Producer);
		gasProducer.start();
		coalControllerJPanel.setGasProducer(gasProducer);
		
		bellow1Producer.addSensorProducer(sensor1Producer);
		bellow1Producer.addSensorProducer(sensor2Producer);
		bellow1Producer.start();
		coalControllerJPanel.addBellowProducer(bellow1Producer);
		
		bellow2Producer.addSensorProducer(sensor1Producer);
		bellow2Producer.addSensorProducer(sensor2Producer);
		bellow2Producer.start();
		coalControllerJPanel.addBellowProducer(bellow2Producer);
		
		new Thread(this).start();
	}

	@Override
	public void run() {
		double gasV = 0.0;
		double bellowV = 0.0;
		ChannelWriteData channelWriteData = null;
		
		while (true) {
			try {
				channelWriteData = stockQueue.poll(200, TimeUnit.MILLISECONDS);
				if (channelWriteData != null) {
					switch (channelWriteData.getKey()) {
					case "sensor1":
						gasV = (double) channelWriteData.getObject();
						coalStatusJPanel.setSensor1(gasV);
						dealGas(1, gasV);
						lastUpdateDateTime.put("sensor1", System.currentTimeMillis());
						break;
					case "sensor2":
						gasV = (double) channelWriteData.getObject();
						coalStatusJPanel.setSensor2(gasV);
						lastUpdateDateTime.put("sensor2", System.currentTimeMillis());
						dealGas(2, gasV);
						break;
					case "bellow1":
						bellowV = (double) channelWriteData.getObject();
						if (bellowV <= 10.1) {
							coalMineJPanel.setBellow1(0);
							coalEventJPanel.setBellow1(0);
						} else {
							coalMineJPanel.setBellow1(1);
							coalEventJPanel.setBellow1(1);
						}
						coalStatusJPanel.setBellow1(bellowV);
						lastUpdateDateTime.put("bellow1", System.currentTimeMillis());
						break;
					case "bellow2":
						bellowV = (double) channelWriteData.getObject();
						if (bellowV <= 10.1) {
							coalMineJPanel.setBellow2(0);
							coalEventJPanel.setBellow2(0);
						} else {
							coalMineJPanel.setBellow2(1);
							coalEventJPanel.setBellow2(1);
						}
						coalStatusJPanel.setBellow2(bellowV);
						lastUpdateDateTime.put("bellow2", System.currentTimeMillis());
						break;
					}
				}
				
				for (Entry<String, Long> entry : lastUpdateDateTime.entrySet()) {
					if (System.currentTimeMillis() - entry.getValue() >= 5000) {
						switch (entry.getKey()) {
						case "sensor1":
							coalStatusJPanel.setSensor1(-1.0);
							coalMineJPanel.setSensor1(-1);
							coalEventJPanel.setSensor1(-1);
							break;
						case "sensor2":
							coalStatusJPanel.setSensor2(-1.0);
							coalMineJPanel.setSensor2(-1);
							coalEventJPanel.setSensor2(-1);
							break;
						case "bellow1":
							coalStatusJPanel.setBellow1(-1.0);
							coalMineJPanel.setBellow1(-1);
							coalEventJPanel.setBellow1(-1);
							break;
						case "bellow2":
							coalStatusJPanel.setBellow2(-1.0);
							coalMineJPanel.setBellow2(-1);
							coalEventJPanel.setBellow2(-1);;
							break;
						}
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void dealGas(int index, double gasV) {
		int value = 0;
		
		if (gasV <= 0.5) {
			value = 0;
			bellow1Producer.setLow();
			bellow2Producer.setLow();
		} else if (gasV <= 0.75) {
			value = 1;
			bellow1Producer.setHigh();
			bellow2Producer.setHigh();
		} else if (gasV <= 1) {
			value = 2;
			bellow1Producer.setHigh();
			bellow2Producer.setHigh();
		} else {
			value = 3;
			bellow1Producer.setHigh();
			bellow2Producer.setHigh();
		}
		
		if (index == 1) {
			coalMineJPanel.setSensor1(value);
			coalEventJPanel.setSensor1(value);
		} else {
			coalMineJPanel.setSensor2(value);
			coalEventJPanel.setSensor2(value);
		}
	}

}
