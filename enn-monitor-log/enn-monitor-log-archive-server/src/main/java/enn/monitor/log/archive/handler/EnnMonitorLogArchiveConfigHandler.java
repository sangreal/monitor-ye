package enn.monitor.log.archive.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.protocol.HTTP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;

import enn.monitor.log.archive.api.EnnMonitorLogArchiveConfigGetResponse;
import enn.monitor.log.archive.api.EnnMonitorLogArchiveConfigPutRequest;
import enn.monitor.log.archive.api.EnnMonitorLogArchiveConfigPutResponse;
import enn.monitor.log.archive.table.EnnMonitorLogArchiveConfigTable;
import enn.monitor.log.archive.tablectl.EnnMonitorLogArchiveConfigCtl;
import enn.monitor.log.archive.tablectl.EnnMonitorLogArchiveConfigField;
import enn.monitor.log.archive.task.EnnMonitorLogArchiveTask;

public class EnnMonitorLogArchiveConfigHandler {
    private static final Logger logger = LogManager.getLogger();
    
    private static EnnMonitorLogArchiveConfigCtl archiveStorageCtl = null;
    
    public static void init(EnnMonitorLogArchiveConfigCtl archiveStorageCtl1) {
    	archiveStorageCtl = archiveStorageCtl1;
    }
    
    public static class EnnMonitorLogArchiveConfigPutHandler extends HttpServlet {

		private static final long serialVersionUID = 7635504795976867647L;
		
		private EnnMonitorLogArchiveTask ennMonitorLogArchiveTask = null;
		
		public EnnMonitorLogArchiveConfigPutHandler(EnnMonitorLogArchiveTask ennMonitorLogArchiveTask) {
			this.ennMonitorLogArchiveTask = ennMonitorLogArchiveTask;
		}
    	
		@Override  
	    protected void doPost(HttpServletRequest req, HttpServletResponse response)  
	            throws ServletException, IOException {  
	    	String line = null;
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
	        StringBuilder stringBuilder = new StringBuilder();
	        BasicDBObject query = null;
	        
	        EnnMonitorLogArchiveConfigPutRequest putRequest = null;
	        EnnMonitorLogArchiveConfigTable configTable = new EnnMonitorLogArchiveConfigTable();
	        
	        Gson gson = new Gson();
	        
	        EnnMonitorLogArchiveConfigPutResponse configResponse = new EnnMonitorLogArchiveConfigPutResponse();
	        
	        while((line = bufferedReader.readLine())!=null){
	            stringBuilder.append(line);
	        }

	        // 将资料解码
	        String reqBody = stringBuilder.toString();
	        String json = URLDecoder.decode(reqBody, HTTP.UTF_8);
	        
	        putRequest = gson.fromJson(json, EnnMonitorLogArchiveConfigPutRequest.class);
	        configTable.setDays(putRequest.getDays());
	        configTable.setLastUpdateTime(System.currentTimeMillis());
	        try {
	        	query = new BasicDBObject();
	        	query.put(EnnMonitorLogArchiveConfigField.IdFieldName, configTable.getId());
				archiveStorageCtl.getMongoDBCtrl().update(query, configTable, true, true, false);
				
				ennMonitorLogArchiveTask.setDays(configTable.getDays());
				
				configResponse.setSuccess(true);
			} catch (Exception e1) {
				configResponse.setSuccess(false);
				configResponse.setError(e1.getMessage());
				logger.error(e1.getMessage(), e1);
			}
	        
	        response.setCharacterEncoding("UTF-8");  
	        response.setContentType("application/json; charset=utf-8");  
	        PrintWriter out = null;  
	        try {  
	            out = response.getWriter();  
	            out.append(gson.toJson(configResponse));  
	            response.setStatus(HttpServletResponse.SC_OK);
	        } catch (IOException e) {  
	            logger.error(e.getMessage(), e);
	        } finally {  
	            if (out != null) {  
	                out.close();  
	            }  
	        }  
	    }  
    }
    
    public static class EnnMonitorLogArchiveConfigGetHandler extends HttpServlet {

		private static final long serialVersionUID = -2955264529983857686L;
    	
		@Override  
	    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {  
	        Gson gson = new Gson();
	        
	        BasicDBObject query = null;
	        
	        EnnMonitorLogArchiveConfigGetResponse configResponse = new EnnMonitorLogArchiveConfigGetResponse();
	        
	        List<EnnMonitorLogArchiveConfigTable> archiveConfigTableList = null;
	        
	        try {
	        	query = new BasicDBObject();
	        	query.put(EnnMonitorLogArchiveConfigField.IdFieldName, 1l);
	        	archiveConfigTableList = archiveStorageCtl.getMongoDBCtrl().searchData(query, null, 0, 100);
				configResponse.setSuccess(true);
				
				if (archiveConfigTableList == null || archiveConfigTableList.size() <= 0) {
					configResponse.setDays(30);
				} else {
					configResponse.setDays(archiveConfigTableList.get(0).getDays());
				}
			} catch (Exception e1) {
				configResponse.setSuccess(false);
				configResponse.setError(e1.getMessage());
				logger.error(e1.getMessage(), e1);
			}
	        
	        response.setCharacterEncoding("UTF-8");  
	        response.setContentType("application/json; charset=utf-8");  
	        PrintWriter out = null;  
	        try {  
	            out = response.getWriter();  
	            out.append(gson.toJson(configResponse));  
	            response.setStatus(HttpServletResponse.SC_OK);
	        } catch (IOException e) {  
	            logger.error(e.getMessage(), e);
	        } finally {  
	            if (out != null) {  
	                out.close();  
	            }  
	        }  
	    }  
    }
    
}
