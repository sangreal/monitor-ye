package enn.monitor.demo.coal.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.common.util.concurrent.AtomicDouble;

import enn.monitor.demo.coal.common.GasUtil;
import enn.monitor.framework.log.channel.ChannelWriteData;

public class BellowProducer extends Thread {
	
	private AtomicBoolean isHigh = new AtomicBoolean(false);
	private AtomicDouble gasV = new AtomicDouble(10);
	private AtomicBoolean bellowStatus = new AtomicBoolean(true);
	
	private String name = null;
	
	private List<SensorProducer> sensorProducerList = new ArrayList<SensorProducer>();
	
	protected BlockingQueue<ChannelWriteData> statsQueue = null;
	
	public BellowProducer(String name, BlockingQueue<ChannelWriteData> statsQueue) {
		this.statsQueue = statsQueue;
		this.name = name;
	}
	
	public void run() {
		int i;
		
		ChannelWriteData writeData = null;
		
		while (true) {
			if (bellowStatus.get() == true) {
				for (i = 0; i < sensorProducerList.size(); ++i) {
					sensorProducerList.get(i).addGas(GasUtil.bellows2gas(-gasV.get()));
				}
				
				if (statsQueue != null) {
					writeData = new ChannelWriteData();
					
					writeData.setKey(name);
					writeData.setObject(gasV.get());
					
					statsQueue.add(writeData);
				}
			}
			 
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addSensorProducer(SensorProducer sensor) {
		sensorProducerList.add(sensor);
	}
	
	public void setGood() {
		bellowStatus.set(true);
	}
	
	public void setNotGood() {
		bellowStatus.set(false);
	}
	
	public void setHigh() {
		if (isHigh.get() == true) {
			return;
		}
		
		isHigh.set(true);
		gasV.set(30);
	}
	
	public void setLow() {
		if (isHigh.get() == false) {
			return;
		}
		isHigh.set(false);
		gasV.set(10);
	}
	
	public String getBellowName() {
		return name;
	}
	
}
