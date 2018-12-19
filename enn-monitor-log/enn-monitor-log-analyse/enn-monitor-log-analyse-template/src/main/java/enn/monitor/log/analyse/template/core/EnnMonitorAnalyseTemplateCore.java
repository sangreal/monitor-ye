package enn.monitor.log.analyse.template.core;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.broadcast.Broadcast;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import enn.monitor.log.analyse.template.data.EnnMonitorLogAnalyseTemplateDataTerm;
import enn.monitor.log.analyse.template.util.EnnMonitorLogAnalyseTemplateUtil;
import scala.Tuple2;

public class EnnMonitorAnalyseTemplateCore {
	
	private static CounterMetric template1Metrics = MetricManager.getCounterMetric(EnnMonitorAnalyseTemplateCore.class, "template1");
	
	public static JavaPairRDD<String, Set<String>> template(JavaRDD<Set<String>> tdPairRDD, Broadcast<Map<Long, Map<String, EnnMonitorLogAnalyseTemplateDataTerm>>> sparkTotalTDValueMap) throws Exception {
		JavaPairRDD<String, Set<String>> totalTemplate = tdPairRDD.mapToPair(new PairFunction<Set<String>, String, Set<String>>() {

			private static final long serialVersionUID = -3659826048877949932L;

			@Override
			public Tuple2<String, Set<String>> call(Set<String> words) throws Exception {
				long tokenId = -1l;
				String[] wordArray = null;
				
				String key = null;
				String keySelected = null;
				
				Set<String> wordSet = new HashSet<String>();
				Set<String> wordSelectedSet = new HashSet<String>();
				
				Set<String> templateSet = new HashSet<String>();
				
				EnnMonitorLogAnalyseTemplateDataTerm dataTerm = null;
				Map<String, EnnMonitorLogAnalyseTemplateDataTerm> dataTermMap = null;
				
				template1Metrics.markEvent();
				
				for (String word : words) {
					wordArray = word.split("-");
					if (tokenId < 0) {
						tokenId = Long.parseLong(wordArray[1]);
						dataTermMap = sparkTotalTDValueMap.getValue().get(tokenId);
					}
					
					dataTerm = dataTermMap.get(wordArray[2]);
					if (dataTerm != null) {
						wordSet.add(wordArray[2]);
						
						if (dataTerm.isSelected == true) {
							wordSelectedSet.add(wordArray[2]);
						}
					}
				}
				
				key = EnnMonitorLogAnalyseTemplateUtil.convertFromSetToString(tokenId, wordSet, true);
				keySelected = EnnMonitorLogAnalyseTemplateUtil.convertFromSetToString(tokenId, wordSelectedSet, false);
				
				if (key != null) {
					templateSet.add(key);
				}
				
				return new Tuple2<String, Set<String>>(keySelected, templateSet);
			}
			
		})
		.reduceByKey(new Function2<Set<String>, Set<String>, Set<String>> () {

			private static final long serialVersionUID = 3971545741447956506L;

			@Override
			public Set<String> call(Set<String> arg0, Set<String> arg1) throws Exception {
				Set<String> templateSet = new HashSet<String>();
				
				templateSet.addAll(arg0);
				templateSet.addAll(arg1);
				
				return templateSet;
			}
			
		});
		
		return totalTemplate;
	}
	
}
