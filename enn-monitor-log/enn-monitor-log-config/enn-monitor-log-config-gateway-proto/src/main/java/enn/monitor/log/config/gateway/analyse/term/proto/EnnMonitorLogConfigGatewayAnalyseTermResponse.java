package enn.monitor.log.config.gateway.analyse.term.proto;

import java.util.HashSet;
import java.util.Set;

public class EnnMonitorLogConfigGatewayAnalyseTermResponse {
	
	private Set<String> addTermSet = new HashSet<String>();
	private Set<String> filterTermSet = new HashSet<String>();
	
	private boolean success = false;
	private String error = null;
	
	public Set<String> getAddTermSet() {
		return addTermSet;
	}
	
	public void addTerm(String term) {
		addTermSet.add(term);
	}

	public void setAddTermSet(Set<String> addTermSet) {
		this.addTermSet = addTermSet;
	}
	
	public void filterTerm(String term) {
		filterTermSet.add(term);
	}

	public Set<String> getFilterTermSet() {
		return filterTermSet;
	}

	public void setFilterTermSet(Set<String> filterTermSet) {
		this.filterTermSet = filterTermSet;
	}

	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
}
