package enn.monitor.metric.kubelet.streaming.server;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.api.java.function.VoidFunction2;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import com.beust.jcommander.JCommander;
import com.google.gson.Gson;

import enn.monitor.framework.metrics.kubelet.proto.ContainerInfo;
import enn.monitor.metric.kubelet.streaming.cache.CacheManager;
import enn.monitor.metric.kubelet.streaming.cache.ClusterInfo;
import enn.monitor.metric.kubelet.streaming.cache.ClusterInfoBroadcastWrapper;
import enn.monitor.metric.kubelet.streaming.parameters.Parameters;
import enn.monitor.metric.kubelet.streaming.util.EnnMonitorMetricKubeletMetricsUtil;
import enn.monitor.metric.kubelet.streaming.util.MetricsConvertor;
import enn.monitor.streaming.common.proto.Metric;
import enn.monitor.streaming.sink.opentsdb.OpenTSDBSender;
import enn.monitor.streaming.sink.pushgateway.PrometheusSender;

/**
 * Created by weize on 17-8-3.
 */
public class EnnMonitorMetricKubeletServer {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static CounterMetric kubeletOpenTSDBMetrics = MetricManager.getCounterMetric(EnnMonitorMetricKubeletServer.class, "kubeletOpenTSDBMetrics");
	private static CounterMetric kubeletPrometheusMetrics = MetricManager.getCounterMetric(EnnMonitorMetricKubeletServer.class, "kubeletPrometheusMetrics");
	
    public static void main(String[] args) throws Exception {
        Parameters parameters = new Parameters();
        JCommander jc = new JCommander(parameters, args);
        if (parameters.help) {
            jc.usage();
            return;
        }

        // Create a local StreamingContext with two working thread and batch interval of 1 second
        SparkConf conf;
        if (parameters.env.equals("test")) {
            conf = new SparkConf().setMaster("local[32]").setAppName("Monitor-metrics-etl");
        } else {
            conf = new SparkConf().setAppName("Monitor-metrics-etl");
        }

        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(parameters.batchInterval));
        Broadcast<Parameters> sparkParameters = jssc.sparkContext().broadcast(parameters);
        
        JavaInputDStream<ConsumerRecord<String, String>> kafkaDirectDStream = getKafkaStreaming(parameters, jssc);
        
        CacheManager cacheManager = CacheManager.getInstance(parameters.apiserver, parameters.apiuser, parameters.apipass);
        
        if ("off".equals(parameters.prometheusPushUrl) == false) {
        	cacheManager.start();
        }
        
        kafkaDirectDStream.foreachRDD(new VoidFunction2<JavaRDD<ConsumerRecord<String, String>>, Time>() {
        	
        	private static final long serialVersionUID = -2703874190091109593L;

			@Override
			public void call(JavaRDD<ConsumerRecord<String, String>> rdd, Time arg1) throws Exception {
				if ("off".equals(parameters.prometheusPushUrl) == false) {
                    ClusterInfoBroadcastWrapper.update(jssc.sparkContext(), cacheManager);
                }
				
                Broadcast<ClusterInfo> broadcastVar = ClusterInfoBroadcastWrapper.getInstance();

                VoidFunction vf = (VoidFunction<Iterator<ConsumerRecord<String, String>>>) iter -> {
                	sendData(iter, broadcastVar, sparkParameters);
                };

                if ("on".equals(parameters.async)) {
                    rdd.foreachPartitionAsync(vf);
                } else {
                    rdd.foreachPartition(vf);
                }
			}
        	
        });
        
        jssc.start();              // Start the computation
        jssc.awaitTermination();   // Wait for the computation to terminate
    }

    private static JavaInputDStream<ConsumerRecord<String, String>> getKafkaStreaming(Parameters parameters, JavaStreamingContext jssc) throws Exception {
    	Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", parameters.kafkaBootstrap);
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", parameters.kafkaGroupId);
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", true);
        Collection<String> topics = Arrays.asList(parameters.metricsTopic);

        JavaInputDStream<ConsumerRecord<String, String>> kafkaDirectDStream = KafkaUtils.createDirectStream(
                jssc,
                LocationStrategies.PreferConsistent(),
                ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
        );
        
        return kafkaDirectDStream;
    }
    
    private static void sendData(Iterator<ConsumerRecord<String, String>> iter,
    		Broadcast<ClusterInfo> broadcastVar, Broadcast<Parameters> sparkParameters) throws Exception {
    	Gson gson = new Gson();
    	
    	EnnMonitorMetricKubeletMetricsUtil.initMetrics(sparkParameters.getValue());
    	
    	OpenTSDBSender sender = OpenTSDBSender.getInstance(sparkParameters.getValue().opentsdbUrl);
    	PrometheusSender pSender = new PrometheusSender(sparkParameters.getValue().prometheusPushUrl);
    	
    	if (!"off".equals(sparkParameters.getValue().prometheusPushUrl)) {
    		if (broadcastVar.getValue() == null) {
    			logger.error("broadcastVar is null");
    		}
    	}
    	
    	while (iter.hasNext()) {
    		ConsumerRecord<String, String> record = iter.next();
    		ContainerInfo containerInfo = gson.fromJson(record.value(), ContainerInfo.class);
    		List<Metric> metrics = MetricsConvertor.convertor(containerInfo, "test-token");
    		
    		sender.send(metrics);
    		kubeletOpenTSDBMetrics.markEvents(metrics.size());
            
    		if ("off".equals(sparkParameters.getValue().prometheusPushUrl) == false) {
    			List<Metric> alertingMetrics = AlertingFilter.filter(metrics, broadcastVar.getValue());
    			pSender.send(alertingMetrics);
    			kubeletPrometheusMetrics.markEvents(alertingMetrics.size());
    		}
    	}
    }


}

