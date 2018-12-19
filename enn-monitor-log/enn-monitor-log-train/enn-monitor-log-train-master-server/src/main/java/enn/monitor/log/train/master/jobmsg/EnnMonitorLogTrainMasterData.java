package enn.monitor.log.train.master.jobmsg;

import enn.monitor.framework.ai.nn.NNObject;
import enn.monitor.framework.common.heap.EnnMonitorHeapData;

public class EnnMonitorLogTrainMasterData implements EnnMonitorHeapData {
	
	private NNObject nnObject = null;
	
	public EnnMonitorLogTrainMasterData(NNObject nnObject) {
		this.nnObject = nnObject;
	}

	@Override
	public int compare(EnnMonitorHeapData item) {
		EnnMonitorLogTrainMasterData data = (EnnMonitorLogTrainMasterData) item;
		
		if (nnObject.getError() > data.getNNObject().getError()) {
			return -1;
		}
		
		if (nnObject.getError() < data.getNNObject().getError()) {
			return 1;
		}
		
		return 0;
	}
	
	public NNObject getNNObject() {
		return nnObject;
	}

}
