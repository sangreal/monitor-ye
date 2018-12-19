package enn.monitor.test.elasticsearch;

import java.net.InetAddress;
import java.util.concurrent.CountDownLatch;

import org.avaje.metric.MetricManager;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

import com.beust.jcommander.JCommander;
import com.google.gson.Gson;

import enn.monitor.framework.log.kafka.filter.value.string.EnnKafkaProducerMsgValueStringFilter;
import enn.monitor.log.proto.ESLog;
import enn.monitor.metrics.app.write.api.metrics.EnnMonitorMetrics;

public class ElasticSearchTest {
	
	private static org.avaje.metric.CounterMetric opentsdbPutMetrics = MetricManager.getCounterMetric(ElasticSearchTest.class, "test_send");
	
	public static void main(String[] args) throws Exception {
		Parameters parameters = new Parameters();
        JCommander jc = new JCommander(parameters, args);
        if (parameters.help) {
        	jc.usage();
        	return;
        }
		
		EnnMonitorMetrics.startMetricsCollector(
				parameters.enableMetrics, parameters.appMonitorKafka, parameters.appTopic, parameters.metricsFreq, 
                "micklongen", new EnnKafkaProducerMsgValueStringFilter());
		
		TransportClient esTransportClient = null;
		
		Settings settings = Settings.builder()
		        .put("cluster.name", parameters.esClusterName)
		        .put("client.transport.sniff", false)
		        .put("xpack.security.user", "elastic" + ":" + "changeme")
		        .build();
		
		esTransportClient = new PreBuiltXPackTransportClient(settings);
		
		String[] hostList = parameters.esUrl.split(",");
		String[] ipPort = null;
		for (int i = 0; i < hostList.length; ++i) {
			ipPort = hostList[i].split(":");
			esTransportClient.addTransportAddress(new InetSocketTransportAddress(
					InetAddress.getByName(ipPort[0]), Integer.parseInt(ipPort[1])));
		}
		
		BulkRequestBuilder bulkRequest = null;
		
		ESLog esLog = genESLog();
		
		bulkRequest = esTransportClient.prepareBulk();
		for (int i = 0; i < parameters.batchNum; ++i) {
			bulkRequest.add(esTransportClient.prepareIndex(parameters.esIndex, "test", null).setSource(new Gson().toJson(esLog)));
		}
		
		int threadNum = parameters.threadNum;
		long batchNum = parameters.batchNum;
		
		
	    CountDownLatch latch = new CountDownLatch(threadNum);
	    
	    long begin = System.currentTimeMillis();
	    
	    for (int i = 0; i < threadNum; ++i) {
	    	new Thread(new ESRunnable(bulkRequest, latch, batchNum)).start();
	    }
	    
	    latch.await();
	    
	    long end = System.currentTimeMillis();
	    System.out.println((end - begin) + "ms");
		
		Thread.sleep(2000);
		
		esTransportClient.close();
		
		Thread.sleep(2000);
	}
	
	public static class ESRunnable implements Runnable {
		private BulkRequestBuilder bulkRequest = null;
		private CountDownLatch latch = null;
		private long batchNum = 0;
		
		public ESRunnable(BulkRequestBuilder bulkRequest, CountDownLatch latch, long batchNum) {
			this.bulkRequest = bulkRequest;
			this.latch = latch;
			this.batchNum = batchNum;
		}

		@Override
		public void run() {
			for (long i = 0; i < batchNum; ++i) {
				opentsdbPutMetrics.markEvent();
		    	BulkResponse bulkResponse = bulkRequest.get();
				if (bulkResponse.hasFailures()) {
					System.out.println(bulkResponse.buildFailureMessage());
				}
		    }
			
			latch.countDown();
		}
		
	}
	
	private static ESLog genESLog() {
		ESLog esLog = new ESLog();
		
		esLog.setClusterName("micklongen");
		esLog.setNameSpace("default");
		esLog.setPodName("ennew_master_4");
		esLog.setLog("Hello World1-----Hello World1-----Hello World1-----Hello World1-----Hello World1-----Hello World1-----");
		
		return esLog;
	}

}
