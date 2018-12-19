package enn.monitor.log.ai.train.result;

public class EnnMonitorLogAiTrainResultEvent {
	
	private EnnMonitorLogAiTrainResultEventEnum ennMonitorLogAiTrainResultEventEnum = null;
	private Object data = null;
	
	public EnnMonitorLogAiTrainResultEvent(EnnMonitorLogAiTrainResultEventEnum ennMonitorLogAiTrainResultEventEnum, Object data) {
		this.ennMonitorLogAiTrainResultEventEnum = ennMonitorLogAiTrainResultEventEnum;
		this.data = data;
	}
	
	public EnnMonitorLogAiTrainResultEventEnum getEnnMonitorLogAiTrainResultEventEnum() {
		return ennMonitorLogAiTrainResultEventEnum;
	}

	public void setEnnMonitorLogAiTrainResultEventEnum(
			EnnMonitorLogAiTrainResultEventEnum ennMonitorLogAiTrainResultEventEnum) {
		this.ennMonitorLogAiTrainResultEventEnum = ennMonitorLogAiTrainResultEventEnum;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
