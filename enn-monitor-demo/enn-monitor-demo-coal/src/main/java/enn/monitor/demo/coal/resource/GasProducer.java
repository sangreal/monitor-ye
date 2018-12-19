package enn.monitor.demo.coal.resource;

import java.util.ArrayList;
import java.util.List;

import com.google.common.util.concurrent.AtomicDouble;

public class GasProducer extends Thread {

	private AtomicDouble gasV = new AtomicDouble(0.1);
	
	private List<SensorProducer> sensorProducerList = new ArrayList<SensorProducer>();
	
	public void run() {
		int i;
		
		while (true) {
			for (i = 0; i < sensorProducerList.size(); ++i) {
				sensorProducerList.get(i).addGas(gasV.get());
			}
			 
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addSensorProducer(SensorProducer sensor) {
		sensorProducerList.add(sensor);
	}
	
	public void setGasV(double gasV) {
		this.gasV.set(gasV);
	}
}
