package enn.monitor.log.data.knowledge;

public class DataKnowledgeParameter {
	
	private long id = -1l;
	private long belongToServiceId = -1l;
	
	private String key = null;
	private String filter = null;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getBelongToServiceId() {
		return belongToServiceId;
	}

	public void setBelongToServiceId(long belongToServiceId) {
		this.belongToServiceId = belongToServiceId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
	
}
