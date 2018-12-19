package enn.monitor.log.data.tag;

public class DataTagParameter {
	
	private long id = -1l;
	private long belongToServiceId = -1l;
	
	private String tag = null;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getBelongToServiceId() {
		return belongToServiceId;
	}

	public void setBelongToServiceId(long belongToId) {
		this.belongToServiceId = belongToId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
