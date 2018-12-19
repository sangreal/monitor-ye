package enn.monitor.log.data.template;

public class DataTemplateEvent {
	
	private DataTemplateEventEnum dataTemplateEventEnum = null;
	private Object data = null;
	
	public DataTemplateEvent(DataTemplateEventEnum dataTemplateEventEnum, Object data) {
		this.dataTemplateEventEnum = dataTemplateEventEnum;
		this.data = data;
	}

	public DataTemplateEventEnum getDataTemplateEventEnum() {
		return dataTemplateEventEnum;
	}

	public void setDataTemplateEventEnum(DataTemplateEventEnum dataTemplateEventEnum) {
		this.dataTemplateEventEnum = dataTemplateEventEnum;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
