package enn.monitor.log.data.template.extraction;

public class DataTemplateExtractionEvent {
	
	private Object data = null;
	
	public DataTemplateExtractionEvent(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
