package enn.monitor.log.archive.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import enn.monitor.log.archive.api.EnnMonitorLogArchiveDiskUsageResponse;
import enn.monitor.log.archive.util.EnnMonitorLogStorageUtil;
import enn.monitor.log.archive.util.EnnMonitorLogStorageUtil.NodeStatsStorage;

public class EnnMonitorLogArchiveDiskHandler extends HttpServlet {
    private static final long serialVersionUID = 2271797150647771294L;  
    
    private static final Logger logger = LogManager.getLogger();
    
    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse response) {  
    	EnnMonitorLogArchiveDiskUsageResponse diskResponse = new EnnMonitorLogArchiveDiskUsageResponse();
    	
    	Gson gson = new Gson();
    	
    	NodeStatsStorage storage = null;
    	
        storage = EnnMonitorLogStorageUtil.getNodeStatsStorageInfo();
        if (storage != null) {
        	diskResponse.setSuccess(true);
        	diskResponse.setCapacity(storage.total);
        	diskResponse.setAvailable(storage.total - storage.usage);
        } else {
        	diskResponse.setSuccess(false);
        	diskResponse.setError("the storage get failed");
        }
        
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/json; charset=utf-8");  
        PrintWriter out = null;  
        try {  
            out = response.getWriter();  
            out.append(gson.toJson(diskResponse));  
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
