package enn.monitor.log.data.knowledge;

import enn.monitor.log.ai.common.CommonEventInterface;

public class DataKnowledgeEvent extends CommonEventInterface {
	
	private DataKnowledgeEventEnum dataKnowledgeEventEnum = null;
	private Object data = null;
	
	public DataKnowledgeEvent(DataKnowledgeEventEnum dataKnowledgeEventEnum, Object data) {
		this.dataKnowledgeEventEnum = dataKnowledgeEventEnum;
		this.data = data;
	}
	
	public DataKnowledgeEventEnum getDataKnowledgeEventEnum() {
		return dataKnowledgeEventEnum;
	}
	
	public void setDataKnowledgeEventEnum(DataKnowledgeEventEnum dataKnowledgeEventEnum) {
		this.dataKnowledgeEventEnum = dataKnowledgeEventEnum;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}

}
