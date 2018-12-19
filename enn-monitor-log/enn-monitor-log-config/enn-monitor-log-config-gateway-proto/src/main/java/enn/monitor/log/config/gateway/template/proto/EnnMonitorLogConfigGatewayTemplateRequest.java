package enn.monitor.log.config.gateway.template.proto;

public class EnnMonitorLogConfigGatewayTemplateRequest {
	
	public String templateKey = null;
	public String belongToParentTemplate = null;
	    
	public long belongToServiceId = -1l;
	 
	public long tagId = -1l;
	    
	public long batchId = -1l;

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

	public long getBatchId() {
		return batchId;
	}

	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}

}
