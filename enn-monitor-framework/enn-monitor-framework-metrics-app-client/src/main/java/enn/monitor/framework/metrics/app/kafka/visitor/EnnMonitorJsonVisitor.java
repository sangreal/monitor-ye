package enn.monitor.framework.metrics.app.kafka.visitor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.avaje.metric.BucketTimedMetric;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.GaugeDoubleGroup;
import org.avaje.metric.GaugeDoubleMetric;
import org.avaje.metric.GaugeLongGroup;
import org.avaje.metric.GaugeLongMetric;
import org.avaje.metric.Metric;
import org.avaje.metric.MetricVisitor;
import org.avaje.metric.TimedMetric;
import org.avaje.metric.ValueMetric;
import org.avaje.metric.report.ReportMetrics;

import com.google.gson.Gson;

import enn.monitor.framework.common.constval.OpentsdbMetricsConst;
import enn.monitor.framework.common.opentsdb.OpentsdbJsonCommon;
import enn.monitor.framework.common.opentsdb.OpentsdbJsonWithDouble;

public abstract class EnnMonitorJsonVisitor implements MetricVisitor {

	static final String METRIC_NAME_DELIMITER = ".";
	static final String TIME_METRIC_SUCCESS_SUFFIX = "success";
	static final String TIME_METRIC_ERROR_SUFFIX = "error";

	private ReportMetrics reportMetrics = null;
	protected OpentsdbJsonWithDouble opentsdbJsonWithDouble = null;
	
	protected long collectionTime;
	protected Map<String, String> defaultTagMap = null;

	public EnnMonitorJsonVisitor() {
	}
	
	public void setReportMetrics(ReportMetrics reportMetrics) throws Exception {
		this.reportMetrics = reportMetrics;
		this.collectionTime = reportMetrics.getCollectionTime();
		
		opentsdbJsonWithDouble  = new OpentsdbJsonWithDouble();
		
		initOpentsdbJson(opentsdbJsonWithDouble);
	}

	public String getJsonString() throws Exception {
		if (this.reportMetrics == null || this.reportMetrics.getMetrics() == null) {
			return null;
		}

		List<Metric> metrics = this.reportMetrics.getMetrics();
		for (Metric metric : metrics) {
			metric.visit(this);
		}
		
		return new Gson().toJson(opentsdbJsonWithDouble);
	}

	@Override
	public void visit(TimedMetric timedMetric) {
		String metricName = timedMetric.getName().toString();
		
		opentsdbJsonWithDouble.setValue(metricName + METRIC_NAME_DELIMITER + TIME_METRIC_SUCCESS_SUFFIX, (double) timedMetric.getCollectedSuccessStatistics().getMean());
		opentsdbJsonWithDouble.setValue(metricName + METRIC_NAME_DELIMITER + TIME_METRIC_ERROR_SUFFIX, (double) timedMetric.getCollectedErrorStatistics().getMean());
	}

	@Override
	public void visit(BucketTimedMetric bucketTimedMetric) throws IOException {
		for (TimedMetric timedMetric : bucketTimedMetric.getBuckets()) {
			visit(timedMetric);
		}
	}

	@Override
	public void visit(ValueMetric valueMetric) throws IOException {
		opentsdbJsonWithDouble.setValue(valueMetric.getName().toString(), (double) valueMetric.getCollectedStatistics().getMean());
	}

	@Override
	public void visit(CounterMetric counterMetric) throws IOException {
		opentsdbJsonWithDouble.setValue(counterMetric.getName().toString(), (double) counterMetric.getCollectedStatistics().getCount());
	}

	@Override
	public void visit(GaugeDoubleMetric gaugeDoubleMetric) throws IOException {
		opentsdbJsonWithDouble.setValue(gaugeDoubleMetric.getName().toString(), gaugeDoubleMetric.getValue());
	}

	@Override
	public void visit(GaugeDoubleGroup gaugeDoubleGroup) throws IOException {
		for (GaugeDoubleMetric gaugeDoubleMetric : gaugeDoubleGroup.getGaugeMetrics()) {
			visit(gaugeDoubleMetric);
		}
	}

	@Override
	public void visit(GaugeLongMetric gaugeLongMetric) throws IOException {
		opentsdbJsonWithDouble.setValue(gaugeLongMetric.getName().toString(), (double) gaugeLongMetric.getValue());
	}

	@Override
	public void visit(GaugeLongGroup gaugeLongGroup) throws IOException {
		for (GaugeLongMetric gaugeLongMetric : gaugeLongGroup.getGaugeMetrics()) {
			visit(gaugeLongMetric);
		}
	}

	protected void initOpentsdbJson(OpentsdbJsonCommon opentsdbJsonCommon) {
		Map<String, String> tagsMap = new HashMap<String, String>();
		
		opentsdbJsonCommon.setTimestamp(getCollection());
		
		tagsMap.put(OpentsdbMetricsConst.NAMESPACE, reportMetrics.getHeaderInfo().getEnv());
		tagsMap.put(OpentsdbMetricsConst.PODNAME, reportMetrics.getHeaderInfo().getServer());
//		tagsMap.put(OpentsdbMetricsConst.APPNAME, reportMetrics.getHeaderInfo().getApp());
		tagsMap.put(OpentsdbMetricsConst.TYPE, OpentsdbMetricsConst.TYPE_APP);
		tagsMap.put(OpentsdbMetricsConst.TOKEN, reportMetrics.getHeaderInfo().getKey());
		if (defaultTagMap != null) {
			tagsMap.putAll(defaultTagMap);
		}
		
		opentsdbJsonCommon.setTags(tagsMap);
	}
	
	abstract protected long getCollection();

}
