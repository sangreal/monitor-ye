package enn.monitor.log.analyse.template.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.storage.StorageLevel;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.beust.jcommander.JCommander;

import enn.monitor.framework.common.map.sort.EnnMonitorFrameworkCommonMapSort;
import enn.monitor.log.analyse.template.core.EnnMonitorAnalyseTemplateCore;
import enn.monitor.log.analyse.template.data.EnnMonitorLogAnalyseTemplateDataTerm;
import enn.monitor.log.analyse.template.merge.EnnMonitorLogAnalyseTemplateMergeUtil;
import enn.monitor.log.analyse.template.parameters.Parameters;
import enn.monitor.log.analyse.template.read.EnnMonitorAnalyseTemplateRead;
import enn.monitor.log.analyse.template.tdidf.EnnMonitorAnalyseTemplateTDIDF;
import enn.monitor.log.analyse.template.util.EnnMonitorLogAnalyseTemplateMetricsUtil;
import enn.monitor.log.config.gateway.analyse.term.proto.EnnMonitorLogConfigGatewayAnalyseTermResponse;
import enn.monitor.log.config.gateway.batchid.proto.EnnMonitorLogConfigGatewayBatchIdResponse;
import enn.monitor.log.config.gateway.client.EnnMonitorLogConfigGatewayClient;
import enn.monitor.log.config.gateway.template.proto.EnnMonitorLogConfigGatewayTemplateRequest;
import enn.monitor.log.config.gateway.template.term.proto.EnnMonitorLogConfigGatewayTemplateTermRequest;
import scala.Tuple2;

public class EnnMonitorLogAnalyseTemplateServer extends HttpServlet {
	
	private static final long serialVersionUID = -295594192131551250L;

	private static final Logger logger = LogManager.getLogger();
	
	private static volatile Broadcast<Parameters> sparkParameters = null;
	private static volatile Broadcast<Map<Long, Map<String, EnnMonitorLogAnalyseTemplateDataTerm>>> sparkTotalTDValueMap = null;
	
	private static boolean isRunning = false;
	
	private static Parameters parameters = null;
	
	private static EnnMonitorLogConfigGatewayClient ennMonitorLogConfigGatewayClient = null;
	
	private static CounterMetric saveTemplate1 = MetricManager.getCounterMetric(EnnMonitorAnalyseTemplateCore.class, "saveTemplate1");
//	private static CounterMetric saveTemplate2 = MetricManager.getCounterMetric(EnnMonitorAnalyseTemplateCore.class, "saveTemplate2");
	
	public static void main(String[] args) throws Exception {
		parameters = new Parameters();
        JCommander jc = new JCommander(parameters, args);
        if (parameters.help) {
            jc.usage();
            return;
        }
        
        ennMonitorLogConfigGatewayClient = new EnnMonitorLogConfigGatewayClient(parameters.configGatewayHost, parameters.configGatewayPort);
        
        Server server = new Server(parameters.listenPort);
		
        ServletContextHandler context = new ServletContextHandler();  
        context.setContextPath("/spark");   //这里是请求的上下文，比如http://localhost:8090/MyServer  
        server.setHandler(context); 
        context.addServlet(new ServletHolder(new EnnMonitorLogAnalyseTemplateServer()), "/job");
        
        logger.info("server start");
        server.start();  
        server.join(); 
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		double ratio = 0.2;
		double similarRatio = 0.5;
		
		String responseBody = null;
        String index = req.getParameter("index");
        String filterRatio = req.getParameter("tfRatio");
        String similarRatioStr = req.getParameter("similarRatio");
        
        PrintWriter out = null;  
        
        response.setCharacterEncoding("UTF-8");  

        out = response.getWriter();  
        
        try {
        	if (isRunning == false) {
            	if (index != null && index.equals("") == false) {
            		if (filterRatio != null && filterRatio.equals("") == false) {
            			ratio = Double.parseDouble(filterRatio);
            		}
            		if (similarRatioStr != null && similarRatioStr.equals("") == false) {
            			similarRatio = Double.parseDouble(similarRatioStr);
            		}
            		startSparkJob(parameters, index, ratio, similarRatio);
            		responseBody = "ok";
            	} else {
            		responseBody = "index is null";
            	}
            } else {
            	responseBody = "Hava a job Running";
            }
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        	responseBody = e.getMessage();
        } finally {
        	out.append(responseBody);  
            response.setStatus(HttpServletResponse.SC_OK);
            
            if (out != null) {  
                out.close();  
            }  
        }
	}
	
	private static void startSparkJob(Parameters parameters, String index, double filterRatio, double similarRatio) throws Exception {
		int i;
		Long tokenId = null;
		
		String[] items = null;
		
		Map<Long, Set<String>> addKeySet = new HashMap<Long, Set<String>>();
		Map<Long, Set<String>> filterKeySet = new HashMap<Long, Set<String>>();
		
		EnnMonitorLogAnalyseTemplateDataTerm dataTerm = null;
		
		Map<String, EnnMonitorLogAnalyseTemplateDataTerm> tdValueMap = null;
		Map<Long, Map<String, EnnMonitorLogAnalyseTemplateDataTerm>> totalTDValueMap = null;
		
		List<Tuple2<String, Long>> wordsList = null;
		
		Map<Long, Long> batchIdMap = new HashMap<Long, Long>();
		
		SparkConf sparkConf = getSparkConf(parameters);
		JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
		
		isRunning = true;
		
		try {
	        sparkParameters = javaSparkContext.broadcast(parameters);
	        
	        JavaRDD<Set<String>> tdPairRDD = EnnMonitorAnalyseTemplateRead.readLogPairRDD(sparkParameters, javaSparkContext, index);
		    tdPairRDD.persist(StorageLevel.MEMORY_AND_DISK_SER());
		    
		    JavaPairRDD<String, Long> idfRDD = EnnMonitorAnalyseTemplateTDIDF.computeIDF1(tdPairRDD);
		    idfRDD = EnnMonitorAnalyseTemplateTDIDF.computeIDF2(idfRDD);
		    JavaPairRDD<String, Long> idfLogRDD = EnnMonitorAnalyseTemplateTDIDF.computeLog(idfRDD);
		    
		    wordsList = idfLogRDD.collect();
		    
		    logger.info("key size: " + wordsList.size());
		    
		    totalTDValueMap = new HashMap<Long, Map<String, EnnMonitorLogAnalyseTemplateDataTerm>>();
		    for (i = 0; i < wordsList.size(); ++i) {
		    	items = wordsList.get(i)._1.split("-");
		    	tokenId = Long.parseLong(items[0]);
		    	
		    	if (totalTDValueMap.containsKey(tokenId) == false) {
		    		totalTDValueMap.put(tokenId, new HashMap<String, EnnMonitorLogAnalyseTemplateDataTerm>());
		    	}
		    	tdValueMap = totalTDValueMap.get(tokenId);
		    	
		    	dataTerm = new EnnMonitorLogAnalyseTemplateDataTerm();
		    	dataTerm.value = wordsList.get(i)._2;
		    	tdValueMap.put(items[1], dataTerm);
		    }
		    
		    keywords(filterRatio, totalTDValueMap, batchIdMap, addKeySet, filterKeySet);
//		    log(totalTDValueMap);
		    
		    sparkTotalTDValueMap = javaSparkContext.broadcast(totalTDValueMap);
		    
		    JavaPairRDD<String, Set<String>> templateSet = EnnMonitorAnalyseTemplateCore.template(tdPairRDD, sparkTotalTDValueMap);
		    Map<String, Set<String>> templateListMap = templateSet.collectAsMap();
		    logger.info("Template Count: " + templateListMap.size());

		    // template extract
		    // 1. merge similar template
		    templateListMap = EnnMonitorLogAnalyseTemplateMergeUtil.mergeSimilarTemplate(similarRatio, templateListMap, addKeySet, filterKeySet);
		    
		    logger.info("simmilar complete");
		    
		    // 2. save template
		    createTemplate(templateListMap.keySet(), batchIdMap, null, saveTemplate1);
//		    for (Map.Entry<String, Set<String>> entry : templateListMap.entrySet()) {
//		    	createTemplate(entry.getValue(), batchIdMap, entry.getKey(), saveTemplate2);
//		    }
		    
		} catch (Exception e) {
			throw e;
		} finally {
			isRunning = false;
			javaSparkContext.stop();
		}
	}
	
	private static void createTemplate(Set<String> templateSet, Map<Long, Long> batchIdMap, String parentTemplate, 
			CounterMetric saveTemplate) throws Exception {
		long tokenId = -1l;
		EnnMonitorLogConfigGatewayTemplateRequest templateRequest = null;
		
		for (String templateKey : templateSet) {
			saveTemplate.markEvent();
			
			tokenId = Long.parseLong(templateKey.split("-")[0]);
			
			templateRequest = new EnnMonitorLogConfigGatewayTemplateRequest();
	    	templateRequest.setBatchId(batchIdMap.get(tokenId));
	    	templateRequest.setBelongToParentTemplate(parentTemplate);
	    	templateRequest.setBelongToServiceId(tokenId);
	    	templateRequest.setTemplateKey(templateKey);
	    	
	    	ennMonitorLogConfigGatewayClient.createTemplate(templateRequest);
		}
	}
	
	private static SparkConf getSparkConf(Parameters parameters) throws Exception {
		SparkConf conf = new SparkConf();
		
        conf.setAppName("LogAnalyseTemplate");
        if (parameters.enableTest) {
        	conf.setMaster("local[48]");
        } else {
        	EnnMonitorLogAnalyseTemplateMetricsUtil.initMetrics(parameters);
        }
        
        if (parameters.enableTest) {
        	conf.set("es.nodes.wan.only", "true");       // 云计算设置
        } else {
        	if (parameters.enableClient) {
        		conf.set("es.nodes.client.only", "true");
        	}
        }
        conf.set("es.nodes", parameters.esHost);
        conf.set("es.port", parameters.esPort);
        conf.set("es.scroll.size", "10000");
        
        conf.set("spark.broadcast.compress", "true");// 设置广播压缩
        conf.set("spark.rdd.compress", "true");      // 设置RDD压缩
        conf.set("spark.io.compression.codec", "org.apache.spark.io.LZFCompressionCodec");
        conf.set("spark.shuffle.file.buffer", "1280k");
        conf.set("spark.reducer.maxSizeInFlight", "1024m");
        conf.set("spark.reducer.maxMblnFlight", "1024m");
        
        // Kryo:快速、高效的序列化框架,序列化后的大小比Java序列化小，速度比Java快
        conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
        conf.registerKryoClasses(new Class[]{java.util.List.class, java.util.ArrayList.class, java.util.LinkedList.class, 
        		java.util.Map.class, java.util.HashMap.class, java.util.TreeMap.class, 
        		java.util.Set.class, java.util.HashSet.class, java.util.TreeSet.class});
        
        return conf;
	}
	
//	private static void log(Map<Long, Map<String, EnnMonitorLogAnalyseTemplateExtractionDataTerm>> totalIDFValueMap) throws Exception {
//		for (Map.Entry<Long, Map<String, EnnMonitorLogAnalyseTemplateExtractionDataTerm>> idfValueItem : totalIDFValueMap.entrySet()) {
//	    	System.out.println("TokenID: " + idfValueItem.getKey());
//
//	    	List<Map.Entry<String, EnnMonitorLogAnalyseTemplateExtractionDataTerm>> list = EnnMonitorFrameworkCommonMapSort.sortByValue(idfValueItem.getValue(), false);
//	    	for (int k = 0; k < list.size(); ++k) {
//	    		System.out.println(list.get(k).getKey() + "      " + list.get(k).getValue().value + "    " + list.get(k).getValue().isSelected);
//	    	}
//	    	
//	    	System.out.println();
//	    }
//	}
	
	private static void keywords(double filterRatio, 
			Map<Long, Map<String, EnnMonitorLogAnalyseTemplateDataTerm>> totalTDValueMap, 
			Map<Long, Long> batchIdMap, Map<Long, Set<String>> addKeySet, Map<Long, Set<String>> filterKeySet) throws Exception {
		Set<String> addKeys = null;
		Set<String> filterKeys = null;
		
		Long total = 0l;
		Long filter = 0l;
		Long sum = 0l;
		
		long batchId = -1l;
		
		EnnMonitorLogAnalyseTemplateDataTerm dataTerm = null;
		EnnMonitorLogConfigGatewayBatchIdResponse batchIdResponse = null;
		EnnMonitorLogConfigGatewayAnalyseTermResponse analyseTermResponse = null;
		EnnMonitorLogConfigGatewayAnalyseTermResponse analyseTermRootResponse = null;
		
		EnnMonitorLogConfigGatewayTemplateTermRequest templateTermRequest = null;
		
		analyseTermRootResponse = ennMonitorLogConfigGatewayClient.getAnalyseTerm(-1l);
		
		for (Map.Entry<Long, Map<String, EnnMonitorLogAnalyseTemplateDataTerm>> idfValueItem : totalTDValueMap.entrySet()) {
			List<Map.Entry<String, EnnMonitorLogAnalyseTemplateDataTerm>> list = EnnMonitorFrameworkCommonMapSort.sortByValue(idfValueItem.getValue(), true);
			
			batchIdResponse = ennMonitorLogConfigGatewayClient.getBatchId("" + idfValueItem.getKey());
			if (batchIdResponse.isSuccess() == true) {
				batchId = batchIdResponse.getBatchId();
				batchIdMap.put(idfValueItem.getKey(), batchId);
			} else {
				throw new Exception(batchIdResponse.getError());
			}
			
			if (analyseTermRootResponse.isSuccess() == true) {
				addKeys = new HashSet<String>();
				addKeys.addAll(analyseTermRootResponse.getAddTermSet());
				filterKeys = new HashSet<String>();
				filterKeys.addAll(analyseTermRootResponse.getFilterTermSet());
			}
			
			analyseTermResponse = ennMonitorLogConfigGatewayClient.getAnalyseTerm(idfValueItem.getKey());
			if (analyseTermResponse.isSuccess() == true) {
				if (analyseTermResponse.getAddTermSet() != null) {
					if (addKeys != null) {
						addKeys.addAll(analyseTermResponse.getAddTermSet());
					} else {
						addKeys = analyseTermResponse.getAddTermSet();
					}
					
					if (filterKeys != null) {
						filterKeys.removeAll(analyseTermResponse.getAddTermSet());
					}
				}
				
				if (analyseTermResponse.getFilterTermSet() != null) {
					if (filterKeys != null) {
						filterKeys.addAll(analyseTermResponse.getFilterTermSet());
					} else {
						filterKeys = analyseTermResponse.getFilterTermSet();
					}
					
					if (addKeys != null) {
						addKeys.removeAll(analyseTermResponse.getFilterTermSet());
					}
				}
			}
			
			total = 0l;
			for (Map.Entry<String, EnnMonitorLogAnalyseTemplateDataTerm> entry : list) {
				total += entry.getValue().value;
			}

			filter = (long) (total * filterRatio);
			sum = 0l;
			for (int i = 0; i < list.size(); ++i) {
				sum += list.get(i).getValue().value;
				if (sum >= filter) {
					for (; i < list.size(); ++i) {
//						templateTermRequest = new EnnMonitorLogConfigGatewayTemplateTermRequest();
//						templateTermRequest.setBatchId(batchId);
//						templateTermRequest.setBelongToServiceId(idfValueItem.getKey());
//						templateTermRequest.setSelected(true);
//						templateTermRequest.setTemplateTerm(list.get(i).getKey());
//						templateTermRequest.setTermValue(list.get(i).getValue().value);
//						ennMonitorLogConfigGatewayClient.createTemplateTerm(templateTermRequest);
						
						idfValueItem.getValue().get(list.get(i).getKey()).batchId = batchId;
						idfValueItem.getValue().get(list.get(i).getKey()).isSelected = true;
					}
					break;
				}
				idfValueItem.getValue().get(list.get(i).getKey()).batchId = batchId;
				idfValueItem.getValue().get(list.get(i).getKey()).isSelected = false;
				
//				templateTermRequest = new EnnMonitorLogConfigGatewayTemplateTermRequest();
//				templateTermRequest.setBatchId(batchId);
//				templateTermRequest.setBelongToServiceId(idfValueItem.getKey());
//				templateTermRequest.setSelected(false);
//				templateTermRequest.setTemplateTerm(list.get(i).getKey());
//				templateTermRequest.setTermValue(list.get(i).getValue().value);
//				ennMonitorLogConfigGatewayClient.createTemplateTerm(templateTermRequest);
			}
			
	    	for (String key : addKeys) {
	    		dataTerm = new EnnMonitorLogAnalyseTemplateDataTerm();
	    		dataTerm.batchId = batchId;
	    		dataTerm.value = 1000l;
	    		dataTerm.isSelected = true;
	    		idfValueItem.getValue().put(key, dataTerm);
	    	}
	    	
	    	for (String key : filterKeys) {
	    		if (idfValueItem.getValue().containsKey(key) == true) {
	    			idfValueItem.getValue().get(key).isSelected = false;
	    		}
	    	}
	    	
	    	for (Map.Entry<String, EnnMonitorLogAnalyseTemplateDataTerm> item : idfValueItem.getValue().entrySet()) {
	    		templateTermRequest = new EnnMonitorLogConfigGatewayTemplateTermRequest();
				templateTermRequest.setBatchId(batchId);
				templateTermRequest.setBelongToServiceId(idfValueItem.getKey());
				templateTermRequest.setSelected(item.getValue().isSelected);
				templateTermRequest.setTemplateTerm(item.getKey());
				ennMonitorLogConfigGatewayClient.createTemplateTerm(templateTermRequest);
	    	}
	    	
	    	addKeySet.put(idfValueItem.getKey(), addKeys);
	    	filterKeySet.put(idfValueItem.getKey(), filterKeys);
	    }
		
	}
	
}
