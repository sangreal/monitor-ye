package enn.monitor.log.analyse.template.data;

public class EnnMonitorLogAnalyseTemplateDataTerm implements Comparable<EnnMonitorLogAnalyseTemplateDataTerm> {
	
	public Long value = null;
	public Long batchId = null;
	public boolean isSelected = false;
	
	@Override
	public int compareTo(EnnMonitorLogAnalyseTemplateDataTerm o) {
		return (int) (value - o.value);
	}

}
