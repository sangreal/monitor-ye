package enn.monitor.log.analyse.template.tdidf;

import java.util.Iterator;
import java.util.Set;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import scala.Tuple2;

public class EnnMonitorAnalyseTemplateTDIDF {
	
	private static CounterMetric idfPre1Metrics = MetricManager.getCounterMetric(EnnMonitorAnalyseTemplateTDIDF.class, "idfRDDPre1");
	private static CounterMetric idfCompute1Metrics = MetricManager.getCounterMetric(EnnMonitorAnalyseTemplateTDIDF.class, "idfComputeRDD1");
	private static CounterMetric idfPre2Metrics = MetricManager.getCounterMetric(EnnMonitorAnalyseTemplateTDIDF.class, "idfRDDPre2");
	private static CounterMetric idfCompute2Metrics = MetricManager.getCounterMetric(EnnMonitorAnalyseTemplateTDIDF.class, "idfComputeRDD2");
	
	private static CounterMetric idfLogMetrics = MetricManager.getCounterMetric(EnnMonitorAnalyseTemplateTDIDF.class, "idfLogComputeRDD");
	
	public static JavaPairRDD<String, Long> computeIDF1(JavaRDD<Set<String>> tdRDD) throws Exception {
		JavaPairRDD<String, Long> idfRDD = tdRDD.flatMap(new FlatMapFunction<Set<String>, String>() {

			private static final long serialVersionUID = -5158548859921451537L;

			@Override
			public Iterator<String> call(Set<String> arg0) throws Exception {
				idfPre1Metrics.markEvent();
				return arg0.iterator();
			}

		})
		.mapToPair(s -> new Tuple2<String, Long>(s, 1l));

		JavaPairRDD<String, Long> idfComputeRDD = idfRDD.reduceByKey(new Function2<Long, Long, Long>() {

			private static final long serialVersionUID = -6003858846808424973L;

			@Override
			public Long call(Long arg0, Long arg1) throws Exception {
				idfCompute1Metrics.markEvent();
				return arg0 + arg1;
			}
			
		});
		
		return idfComputeRDD;
	}
	
	public static JavaPairRDD<String, Long> computeIDF2(JavaPairRDD<String, Long> idfRDD) throws Exception {
		return idfRDD.mapToPair(new PairFunction<Tuple2<String, Long>, String, Long>() {

			private static final long serialVersionUID = 6342310038651348928L;

			@Override
			public Tuple2<String, Long> call(Tuple2<String, Long> arg0) throws Exception {
				idfPre2Metrics.markEvent();
				return new Tuple2<String, Long>(arg0._1.substring(arg0._1.indexOf('-') + 1), arg0._2);
			}
			
		}).reduceByKey(new Function2<Long, Long, Long>() {

			private static final long serialVersionUID = 6743368089651883572L;

			@Override
			public Long call(Long arg0, Long arg1) throws Exception {
				idfCompute2Metrics.markEvent();
				return arg0 + arg1;
			}
			
		});
	}
	
	public static JavaPairRDD<String, Long> computeLog(JavaPairRDD<String, Long> idfRDD) throws Exception {
		return idfRDD.mapToPair(new PairFunction<Tuple2<String, Long>, String, Long>() {

			private static final long serialVersionUID = 6308427798866832921L;

			@Override
			public Tuple2<String, Long> call(Tuple2<String, Long> arg0) throws Exception {
				idfLogMetrics.markEvent();
				return new Tuple2<String, Long>(arg0._1, (long)Math.log(arg0._2));
			}
			
		});
	}

}
