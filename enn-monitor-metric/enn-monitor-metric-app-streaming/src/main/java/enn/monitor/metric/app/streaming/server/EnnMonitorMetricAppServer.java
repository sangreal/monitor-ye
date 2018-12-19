package enn.monitor.metric.app.streaming.server;

import java.util.ArrayList;
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
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import com.beust.jcommander.JCommander;
import com.google.gson.Gson;

import enn.monitor.framework.common.opentsdb.OpentsdbJsonWithDouble;
import enn.monitor.metric.app.streaming.data.EnnMonitorMetricAppStreamingData;
import enn.monitor.metric.app.streaming.parameters.Parameters;
import enn.monitor.metric.app.streaming.util.EnnMonitorMetricAppMetricsUtil;
import enn.monitor.streaming.common.proto.Metric;
import enn.monitor.streaming.sink.opentsdb.OpenTSDBSender;

/**
 * Created by weize on 17-8-3.
 */
public class EnnMonitorMetricAppServer {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static volatile Broadcast<Parameters> sparkParameters = null;
	
	private static CounterMetric appMetrics = MetricManager.getCounterMetric(EnnMonitorMetricAppServer.class, "appMetrics");

    public static void main(String[] args) throws Exception {
    	
    	JavaInputDStream<ConsumerRecord<String, String>> kafkaStream = null;
    	
        Parameters parameters = new Parameters();
        JCommander jc = new JCommander(parameters, args);
        if (parameters.help) {
            jc.usage();
            return;
        }

        SparkConf conf;
        if (parameters.env.equals("test")) {
            conf = new SparkConf().setMaster("local[32]").setAppName("Monitor-app-etl");
        } else {
            conf = new SparkConf().setAppName("Monitor-app-etl");
        }

        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(1));
        
        kafkaStream = getKafkaStreaming(parameters, jssc);
        
        sparkParameters = jssc.sparkContext().broadcast(parameters);
        sendData(sparkParameters, kafkaStream);
        
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
        Collection<String> topics = Arrays.asList(parameters.appTopic);

        JavaInputDStream<ConsumerRecord<String, String>> kafkaStream =
                KafkaUtils.createDirectStream(jssc, LocationStrategies.PreferConsistent(), ConsumerStrategies.Subscribe(topics, kafkaParams));
        
        return kafkaStream;
    }
    
    private static void sendData(Broadcast<Parameters> sparkParameters, JavaInputDStream<ConsumerRecord<String, String>> kafkaStream) throws Exception {
    	kafkaStream.foreachRDD((consumerRecordJavaRDD, time) -> {
            consumerRecordJavaRDD.foreachPartition(new VoidFunction<Iterator<ConsumerRecord<String, String>>>() {

				private static final long serialVersionUID = -2872820476368747176L;

				@Override
				public void call(Iterator<ConsumerRecord<String, String>> iter) throws Exception {
					ConsumerRecord<String, String> record = null;
					OpenTSDBSender sender = OpenTSDBSender.getInstance(sparkParameters.getValue().opentsdbUrl);
					
					EnnMonitorMetricAppMetricsUtil.initMetrics(sparkParameters.getValue());
					
					while (iter.hasNext()) {
						record = iter.next();
						
						try {
							EnnMonitorMetricAppStreamingData data = getEnnMonitorMetricAppStreamingData(record.value());
							if (data == null || data.tokenId == null || data.metric == null) {
								continue;
							}
							
							List<Metric> metrics = new ArrayList<>();
							data.metric.getValueMap().forEach((metricName, value) -> {
								Metric newMetric = new Metric();
								newMetric.setMetric(metricName);
								newMetric.setTimestamp(data.metric.getTimestamp());
								newMetric.setValue(value);
								Map<String, String> tags = data.metric.getTags();
								tags.put("TokenId", data.tokenId);
								newMetric.setTags(tags);
								metrics.add(newMetric);
							});
							
							if (metrics.size() > 0) {
								appMetrics.markEvents(metrics.size());
								sender.send(metrics);
							}
	                    } catch (Exception e) {
	                    	logger.error(e.getMessage(), e);
	                    }
					}
				}
            	
            });
    	});
    }
    
    private static EnnMonitorMetricAppStreamingData getEnnMonitorMetricAppStreamingData(String log) throws Exception {
    	EnnMonitorMetricAppStreamingData data = new EnnMonitorMetricAppStreamingData();
    	
        int splitIndex = log.indexOf("|");
        if (splitIndex != -1) {
        	data.tokenId = log.substring(0, splitIndex);
        	log = log.substring(splitIndex + 1, log.length());
        }
        data.metric = new Gson().fromJson(log, OpentsdbJsonWithDouble.class);
        
    	return data;
    }
    
    
}

