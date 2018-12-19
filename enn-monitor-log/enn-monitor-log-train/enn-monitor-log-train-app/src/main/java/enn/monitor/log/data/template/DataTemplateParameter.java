package enn.monitor.log.data.template;

public class DataTemplateParameter {
	
	private long id = -1l;
	private long belongToServiceId = -1l;
	
	private long tagId = -1l;
	
	private boolean isRoot = false;
	
	private String templateKey = null;
	private String belongToParentTemplate = null;
	
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
	
	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public String getTemplateKey() {
		return templateKey;
	}
	
	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}
	
	public String getBelongToParentTemplate() {
		return belongToParentTemplate;
	}
	
	public void setBelongToParentTemplate(String belongToParentTemplate) {
		this.belongToParentTemplate = belongToParentTemplate;
	}

}
