package enn.monitor.demo.coal.resource;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.log.channel.ChannelWriteTask;

public class SensorProducer extends ChannelWriteTask {
	
	private AtomicBoolean sensorStatus = new AtomicBoolean(true);
	private double gasValue = 0.0;
	
	private long lastSendTime = 0l;
	
	private String name = null;
	
	protected BlockingQueue<ChannelWriteData> statsQueue = null;
	
	public SensorProducer(String name, BlockingQueue<ChannelWriteData> statsQueue) {
		this.statsQueue = statsQueue;
		this.name = name;
	}

	@Override
	protected void operator(ChannelWriteData stockWriteData) throws Exception {
		double gas = 0.0;
		
		gas = (double) stockWriteData.getObject();
		if (sensorStatus.get() == true) {
			gasValue += gas;
			if (gasValue < 0) {
				gasValue = 0;
			}
			
			sendStatsData();
		}
	}
	
	protected void operatorNull() {
		if (sensorStatus.get() == true) {
			sendStatsData();
		}
	}
	
	private void sendStatsData() {
		ChannelWriteData writeData = null;
		
		if (statsQueue == null) {
			return;
		}
		
		if (sensorStatus.get() == false) {
			return;
		}
		
		if (System.currentTimeMillis() - lastSendTime > 1000) {
			writeData = new ChannelWriteData();
			
			writeData.setKey(name);
			writeData.setObject(gasValue);
			statsQueue.add(writeData);
			
			lastSendTime = System.currentTimeMillis();
		}
	}
	
	public void setGood() {
		sensorStatus.set(true);
	}
	
	public void setNotGood() {
		sensorStatus.set(false);
	}
	
	public void addGas(double gasValue) {
		ChannelWriteData writeData = new ChannelWriteData();
		
		writeData.setObject(gasValue);
		stockQueue.add(writeData);
	}
	
	public String getSensorName() {
		return name;
	}
	
}
