package enn.monitor.log.analyse.data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import enn.monitor.framework.ai.nn.NNObject;
import enn.monitor.log.train.common.nn.data.TrainNNData;

public class EnnMonitorLogAnalyseData {
	
	transient private BlockingQueue<NNObject> nnObjectList = new LinkedBlockingQueue<NNObject>();
	
	private NNObject nnObject = null;
	private TrainNNData trainNNData = null;
	
	public NNObject getNnObject() {
		return nnObject;
	}
	
	public void setNnObject(NNObject nnObject) {
		this.nnObject = nnObject;
	}
	
	public TrainNNData getTrainNNData() {
		return trainNNData;
	}
	
	public void setTrainNNData(TrainNNData trainNNData) {
		this.trainNNData = trainNNData;
	}
	
	public NNObject deployNNObject() {
		NNObject item = null;
		
		item = nnObjectList.poll();
		if (item == null) {
			item = nnObjectList.poll();
			if (item == null) {
				item = nnObject.cloneNNObject();
			}
		}
		
		return item;
	}
	
	public void receiveNNObject(NNObject item) {
		nnObjectList.add(item);
	}

}
