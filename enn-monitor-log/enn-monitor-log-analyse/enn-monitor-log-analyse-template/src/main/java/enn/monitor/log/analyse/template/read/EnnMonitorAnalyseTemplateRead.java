package enn.monitor.log.analyse.template.read;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.broadcast.Broadcast;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;

import enn.monitor.log.analyse.template.parameters.Parameters;
import enn.monitor.log.analyse.template.util.EnnMonitorLogAnalyseTemplateMetricsUtil;
import enn.monitor.log.train.common.util.EnnMonitorLogTrainCommonUtil;
import scala.Tuple2;

public class EnnMonitorAnalyseTemplateRead {
	
	private static CounterMetric readLogMetrics = MetricManager.getCounterMetric(EnnMonitorAnalyseTemplateRead.class, "readRDD");
	private static CounterMetric filterMetrics = MetricManager.getCounterMetric(EnnMonitorAnalyseTemplateRead.class, "filterRDD");
	
	private static Random random = new Random();

	private static Set<Character> splitCharSet = new HashSet<Character>();
	static {
		splitCharSet.add(',');
		splitCharSet.add(':');
		splitCharSet.add('\\');
		splitCharSet.add('=');
		splitCharSet.add('(');
		splitCharSet.add(')');
		splitCharSet.add('[');
		splitCharSet.add(']');
		splitCharSet.add('{');
		splitCharSet.add('}');
		splitCharSet.add('\"');
		splitCharSet.add('-');
		splitCharSet.add('/');
		splitCharSet.add('_');
		splitCharSet.add('@');
		splitCharSet.add('^');
		splitCharSet.add('`');
		splitCharSet.add(';');
		splitCharSet.add('?');
		splitCharSet.add('|');
		splitCharSet.add('!');
		splitCharSet.add('#');
		splitCharSet.add('$');
		splitCharSet.add('%');
		splitCharSet.add('&');
		splitCharSet.add('*');
		splitCharSet.add('+');	
		splitCharSet.add('.');
		splitCharSet.add('~');
		splitCharSet.add('\'');
	}
	
	
	public static JavaRDD<Set<String>> readLogPairRDD(Broadcast<Parameters> sparkParameters, JavaSparkContext javaSparkContext, String index) throws Exception {
		StringBuffer query = new StringBuffer("{\"query\": {\"match_all\": { }}}");
		
		JavaPairRDD<String, Map<String, Object>> esRDD = JavaEsSpark.esRDD(javaSparkContext, index, query.toString());
		
		JavaRDD<Set<String>> readRDD = esRDD.map(new Function<Tuple2<String, Map<String, Object>>, Set<String>>() {

			private static final long serialVersionUID = 6909510644694059185L;

			@Override
			public Set<String> call(Tuple2<String, Map<String, Object>> arg0) throws Exception {
				Long tokenId = null;
				String log = null;
				
				Set<String> wordsSet = null;
				
				Map<String, Object> logMap = null;
				
				EnnMonitorLogAnalyseTemplateMetricsUtil.initMetrics(sparkParameters.getValue());
				
            	readLogMetrics.markEvent();
            	
            	logMap = arg0._2();
            	
            	tokenId = getTokenId(logMap);
            	log = getLog(logMap);
            	
            	wordsSet = cutWords(tokenId, log);
            	
            	return wordsSet;
			}
			
		}).filter(new Function<Set<String>, Boolean>() {

			private static final long serialVersionUID = -40670782937818170L;

			@Override
			public Boolean call(Set<String> arg0) throws Exception {
				filterMetrics.markEvent();
				
				if (arg0.size() <= 0) {
					return false;
				}
				
				return true;
			}
			
		});
        
        return readRDD;
	}
	
	private static Long getTokenId(Map<String, Object> logMap) throws Exception {
		Long tokenId = null;
		
		if (logMap.containsKey("token") == false) {
    		tokenId = -1l;
    	} else {
    		tokenId = Long.parseLong((String) logMap.get("token"));
    	}
		
		return tokenId;
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
	
	private static Set<String> cutWords(long tokenId, String log) throws Exception {
		Set<String> wordSet = null;
		Set<String> resultSet = new HashSet<String>();
		
		StringBuilder tokeIdPrefix = new StringBuilder();
		
		if (tokenId == -1 || log == null || log.equals("") == true) {
			return resultSet;
		}
		
		tokeIdPrefix.append(random.nextLong() & 0x3fff);
		
		tokeIdPrefix.append("-");
		tokeIdPrefix.append(tokenId);
		tokeIdPrefix.append("-");
		
		wordSet = EnnMonitorLogTrainCommonUtil.convertToWords(log);
		for (String word : wordSet) {
			resultSet.add(tokeIdPrefix + word);
		}

		return resultSet;
	}
	
	public static void main(String[] args) throws Exception {
		Set<String> words = cutWords(36, "ConnectionManager: [id: 0x31e4e923, /172.16.126.34:41652 =\u003e /172.16.100.60:4242] OPEN\\n");
		for(String word : words) {
			System.out.println(word);
		}
	}
	
}
