package enn.monitor.log.ai.train.result;

public class EnnMonitorLogAiTrainResultParameter {
	
	private String token = null;
	
	private long id = -1l;
	private long belongToServiceId = -1l;
	private String templateKey = null;
	
	private long tagId = -1l;
	private String tag = null;
	
	private double match = 0.0;
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
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
	
	public long getTagId() {
		return tagId;
	}
	
	public void setTagId(long tagId) {
		this.tagId = tagId;
	}
	
	public String getTemplateKey() {
		return templateKey;
	}
	
	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public double getMatch() {
		return match;
	}

	public void setMatch(double match) {
		this.match = match;
	}
	
}
