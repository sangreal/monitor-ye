package enn.monitor.framework.common.opentsdb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OpentsdbHttpClient<T extends OpentsdbJsonCommon> {

	private static final Logger logger = LogManager.getLogger();
	
	private String opentsdbPutUrl = null;
	private HttpClient httpClient = null;

	public OpentsdbHttpClient(String host, int port) {
		this.opentsdbPutUrl = "http://" + host + ":" + port + "/api/put";
		httpClient = HttpClientBuilder.create()
				.setConnectionManager(new PoolingHttpClientConnectionManager())
				.build();
	}
	
	public List<List<T>> split(List<T> opentsdbJsonList, int batch) throws Exception {
		int i, j;
		
		List<T> tmpOpentsdbJsonList = null;
		List<List<T>> result = new ArrayList<List<T>>();
		
		if (opentsdbJsonList.size() > batch) {
			for (i = 0; i < opentsdbJsonList.size(); i += batch) {
				tmpOpentsdbJsonList = new ArrayList<T>();
				for (j = i; j < i + batch && j < opentsdbJsonList.size(); ++j) {
					tmpOpentsdbJsonList.add(opentsdbJsonList.get(j));
				}
				result.add(tmpOpentsdbJsonList);
			}
		} else {
			//sendData(new Gson().toJson(opentsdbJsonList));
			result.add(opentsdbJsonList);
		}
		
		return result;
	}
	
	public void sendData(String jsonString) throws Exception {
		HttpPost httpPost = new HttpPost(opentsdbPutUrl);
		RequestConfig requestConfig = RequestConfig.custom()
													.setSocketTimeout(20000)
													.setConnectTimeout(5000)
													.setConnectionRequestTimeout(10000)
													.build();
		httpPost.setConfig(requestConfig);
		httpPost.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
	    try {
			httpClient.execute(httpPost);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
	    	httpPost.releaseConnection();
		}
	}

}
