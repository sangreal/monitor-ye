package enn.monitor.log.config.gateway.template.term.proto;

public class EnnMonitorLogConfigGatewayTemplateTermRequest {
	
    private String templateTerm = null;
    private long belongToServiceId = -1l;
	
    private double termValue = 0.0;
    private boolean isSelected = true;
	
    private long batchId = -1l;

	public String getTemplateTerm() {
		return templateTerm;
	}

	public void setTemplateTerm(String templateTerm) {
		this.templateTerm = templateTerm;
	}

	public long getBelongToServiceId() {
		return belongToServiceId;
	}

	public void setBelongToServiceId(long belongToServiceId) {
		this.belongToServiceId = belongToServiceId;
	}

	public double getTermValue() {
		return termValue;
	}

	public void setTermValue(double termValue) {
		this.termValue = termValue;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public long getBatchId() {
		return batchId;
	}

	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}

}
