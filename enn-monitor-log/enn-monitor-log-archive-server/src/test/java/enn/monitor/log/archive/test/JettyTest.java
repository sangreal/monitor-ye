package enn.monitor.log.archive.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class JettyTest {
	
	private static HttpClient httpClient = HttpClientBuilder.create()
			.setConnectionManager(new PoolingHttpClientConnectionManager())
			.build();
	
	public static class TestTask implements Runnable {

		@Override
		public void run() {
			while (true) {
				HttpGet httpGet = new HttpGet("http://10.19.138.175:8090/log/archive/config/get");
				try {
				    HttpResponse response = httpClient.execute(httpGet);
				    String strResult = EntityUtils.toString(response.getEntity());  
				    System.out.println(Thread.currentThread().getId() + ":" + strResult);
				} catch (IOException e) {
				    e.printStackTrace();
				}finally {
				    try {
				    	httpGet.releaseConnection();//释放资源
				    } catch (Exception e) {
				        e.printStackTrace();
				    }
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		int num = 10;
		
		for (int i = 0; i < num; ++i) {
			new Thread(new TestTask()).start();
		}
		
		Thread.sleep(10000000);
	}

}
