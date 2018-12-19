package enn.monitor.log.data.tag;

public class DataTagEvent {
	
	private DataTagEventEnum dataTagEventEnum = null;
	private Object data = null;
	
	public DataTagEvent(DataTagEventEnum dataTagEventEnum, Object data) {
		this.dataTagEventEnum = dataTagEventEnum;
		this.data = data;
	}
	
	public DataTagEventEnum getDataTagEventEnum() {
		return dataTagEventEnum;
	}
	
	public void setDataTagEventEnum(DataTagEventEnum dataTagEventEnum) {
		this.dataTagEventEnum = dataTagEventEnum;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}

}
