package enn.monitor.log.analyse.tester.read;

import java.util.List;
import java.util.Map;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.broadcast.Broadcast;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;

import enn.monitor.framework.ai.nn.NNFramework;
import enn.monitor.framework.ai.nn.activation.NNActivationEnum;
import enn.monitor.framework.ai.nn.weights.NNWeightEnum;
import enn.monitor.log.analyse.data.EnnMonitorLogAnalyseData;
import enn.monitor.log.analyse.tester.parameters.Parameters;
import enn.monitor.log.analyse.tester.util.EnnMonitorLogAnalyseTesterMetricsUtil;
import scala.Tuple2;

public class EnnMonitorAnalyseTesterRead {
	
	private static CounterMetric readLogMetrics = MetricManager.getCounterMetric(EnnMonitorAnalyseTesterRead.class, "readRDD");
	
	public static void readLogPairRDD(
			Broadcast<Parameters> sparkParameters, Broadcast<EnnMonitorLogAnalyseData> sparkEnnMonitorLogAnalyseData, 
			JavaSparkContext javaSparkContext, String index, String token) throws Exception {
		
		String query = "{\"query\": {\"match\": { \"token\" : %tokenId% }}}";
		query = query.replace("%tokenId%", token);
		
		JavaPairRDD<String, Map<String, Object>> esRDD = JavaEsSpark.esRDD(javaSparkContext, index, query.toString());
		
		JavaRDD<Map<String, ?>> readRDD = esRDD.map(new Function<Tuple2<String, Map<String, Object>>, Map<String, ?>>() {

			private static final long serialVersionUID = 6909510644694059185L;

			@Override
			public Map<String, ?> call(Tuple2<String, Map<String, Object>> arg0) throws Exception {
				String log = null;
				
				Map<String, Object> logMap = null;
				
				EnnMonitorLogAnalyseData analyseData = sparkEnnMonitorLogAnalyseData.getValue();
				
				EnnMonitorLogAnalyseTesterMetricsUtil.initMetrics(sparkParameters.getValue());
				
            	readLogMetrics.markEvent();
            	
            	logMap = arg0._2();
            	
            	log = getLog(logMap);
            	
            	analyseLog(log, logMap, analyseData);
            	
            	return logMap;
			}
			
		});
		
		JavaEsSpark.saveToEs(readRDD, "java-test/doc");
	}
	
	private static String getLog(Map<String, Object> logMap) throws Exception {
		String log = null;
		
		if (logMap.containsKey("log") == false) {
    		log = "";
    	} else {
    		log = ((String) logMap.get("log")).trim();
    		
    		if (log.length() >= 2 && log.charAt(log.length() - 2) == '\\' && log.charAt(log.length() - 1) == 'n') {
				log = log.substring(0, log.length() - 2);
			}
    		
    		log = log.toLowerCase();
    	}
		
		return log;
	}
	
	private static void analyseLog(String log, Map<String, Object> logMap, EnnMonitorLogAnalyseData analyseData) throws Exception {
		int i;
		double maxMatch = Double.MIN_VALUE;
		int pos = -1;
		
		String template = null;
		
		List<Double> outputList = null;
		
		if (log == null || log.equals("") == true) {
    		return;
    	}
    	
    	outputList = NNFramework.compute(
    			analyseData.deployNNObject(), analyseData.getTrainNNData().formatInputList(log), 
				null, NNActivationEnum.Sigmoid, NNWeightEnum.Momentum, 0, 0);
    	
		for (i = 0; i < outputList.size(); ++i) {
			if (maxMatch < outputList.get(i)) {
				maxMatch = outputList.get(i);
				pos = i;
			}
		}
		
		template = analyseData.getTrainNNData().getResults(pos);
		if (template == null) {
			return;
		}
		logMap.put("templateKey", template);
		logMap.put("match", maxMatch);
	}
	
}
