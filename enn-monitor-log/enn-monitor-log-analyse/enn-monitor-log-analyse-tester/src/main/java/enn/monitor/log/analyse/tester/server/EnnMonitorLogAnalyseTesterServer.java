package enn.monitor.log.analyse.tester.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.beust.jcommander.JCommander;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;

import enn.monitor.log.analyse.data.EnnMonitorLogAnalyseData;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageTable;
import enn.monitor.log.analyse.tester.parameters.Parameters;
import enn.monitor.log.analyse.tester.read.EnnMonitorAnalyseTesterRead;
import enn.monitor.log.analyse.tester.util.EnnMonitorLogAnalyseStorageTableCtl;
import enn.monitor.log.analyse.tester.util.EnnMonitorLogAnalyseStorageTableField;
import enn.monitor.log.analyse.tester.util.EnnMonitorLogAnalyseTesterMetricsUtil;
import enn.monitor.log.analyse.tester.util.MongoConfig;

public class EnnMonitorLogAnalyseTesterServer extends HttpServlet {
	
	private static final long serialVersionUID = -295594192131551250L;

	private static final Logger logger = LogManager.getLogger();
	
	private static volatile Broadcast<Parameters> sparkParameters = null;
	private static volatile Broadcast<EnnMonitorLogAnalyseData> sparkEnnMonitorLogAnalyseData = null;
	
	private static Gson gson = new Gson();
	
	private static boolean isRunning = false;
	
	private static Parameters parameters = null;
	
	private static EnnMonitorLogAnalyseStorageTableCtl analyseStorageTableCtl = null;
	
	public static void main(String[] args) throws Exception {
		parameters = new Parameters();
        JCommander jc = new JCommander(parameters, args);
        if (parameters.help) {
            jc.usage();
            return;
        }
        
        MongoConfig.setParameters(parameters);
        
        Server server = new Server(parameters.listenPort);
		
        ServletContextHandler context = new ServletContextHandler();  
        context.setContextPath("/spark");   //这里是请求的上下文，比如http://localhost:8090/MyServer  
        server.setHandler(context); 
        context.addServlet(new ServletHolder(new EnnMonitorLogAnalyseTesterServer()), "/tester");
        
        analyseStorageTableCtl = new EnnMonitorLogAnalyseStorageTableCtl(MongoConfig.getMongoUrl(), MongoConfig.getDBName(), MongoConfig.getLogAnalyseStorageTable());
        
        logger.info("server start");
        server.start();  
        server.join(); 
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		String responseBody = null;
        String index = req.getParameter("index");
        String token = req.getParameter("token");
        
        PrintWriter out = null;  
        
        response.setCharacterEncoding("UTF-8");  

        out = response.getWriter();  
        
        try {
        	if (token == null || token.equals("") == true) {
        		throw new Exception("the token is null");
        	}
        	
        	if (isRunning == false) {
            	if (index != null && index.equals("") == false) {
            		startSparkJob(parameters, index, token);
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
	
	private static void startSparkJob(Parameters parameters, String index, String token) throws Exception {
		EnnMonitorLogAnalyseData ennMonitorLogAnalyseData = null;
		
		SparkConf sparkConf = getSparkConf(parameters);
		JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
		
		isRunning = true;
		
		try {
			ennMonitorLogAnalyseData = getNNObject(token);
			if (ennMonitorLogAnalyseData == null) {
				throw new Exception("the ennMonitorLogAnalyseData is null");
			}
			
	        sparkParameters = javaSparkContext.broadcast(parameters);
	        sparkEnnMonitorLogAnalyseData = javaSparkContext.broadcast(ennMonitorLogAnalyseData);
	        
	        EnnMonitorAnalyseTesterRead.readLogPairRDD(sparkParameters, sparkEnnMonitorLogAnalyseData, javaSparkContext, index, token);
		    
		} catch (Exception e) {
			throw e;
		} finally {
			isRunning = false;
			javaSparkContext.stop();
		}
	}
	
	private static SparkConf getSparkConf(Parameters parameters) throws Exception {
		SparkConf conf = new SparkConf();
		
        conf.setAppName("LogAnalyseTemplate");
        if (parameters.enableTest) {
        	conf.setMaster("local[48]");
        } else {
        	EnnMonitorLogAnalyseTesterMetricsUtil.initMetrics(parameters);
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
	
	private static EnnMonitorLogAnalyseData getNNObject(String token) throws Exception {
		BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	List<EnnMonitorLogAnalyseStorageTable> templateTableList = null;
    	
    	query.put(EnnMonitorLogAnalyseStorageTableField.TokenIdFieldName, Long.parseLong(token));
    	order.put(EnnMonitorLogAnalyseStorageTableField.SeqNoFieldName, -1);
    	
    	try {
    		templateTableList = analyseStorageTableCtl.getMongoDBCtrl().searchData(query, order, 0, 1);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
    	
    	if (templateTableList == null || templateTableList.size() <= 0) {
    		return null;
    	}
    	
    	return gson.fromJson(templateTableList.get(0).getNndata(), EnnMonitorLogAnalyseData.class);
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
	
}
