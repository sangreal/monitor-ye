package enn.monitor.config.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.BasicDBObject;

import enn.monitor.config.cache.EnnMonitorConfigBlackListCacheUtil;
import enn.monitor.config.cluster.impl.EnnMonitorConfigClusterHandler;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterStatus;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterTable;
import enn.monitor.config.cluster.tablectl.EnnMonitorConfigClusterTableCtl;
import enn.monitor.config.cluster.tablectl.EnnMonitorConfigClusterTableField;
import enn.monitor.config.service.impl.EnnMonitorConfigServiceHandler;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable;
import enn.monitor.config.service.tablectl.EnnMonitorConfigServiceTableCtl;
import enn.monitor.config.service.tablectl.EnnMonitorConfigServiceTableField;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteRequest;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineStatus;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineTable;
import enn.monitor.config.serviceline.impl.EnnMonitorConfigServiceLineHandler;
import enn.monitor.config.serviceline.tablectl.EnnMonitorConfigServiceLineTableCtl;
import enn.monitor.config.serviceline.tablectl.EnnMonitorConfigServiceLineTableField;

public class EnnMonitorConfigDataThread implements Runnable {
	
	private static final Logger logger = LogManager.getLogger();
	
	private Map<Long, Long> serviceLineMap = new HashMap<Long, Long>();
	private Map<Long, Long> clusterMap = new HashMap<Long, Long>();
	
	private EnnMonitorConfigServiceTableCtl serviceTableCtl = null;
	private EnnMonitorConfigServiceLineTableCtl serviceLineTableCtl = null;
	private EnnMonitorConfigClusterTableCtl clusterTableCtl = null;
	
	private EnnMonitorConfigServiceHandler serviceHandler = null;
	private EnnMonitorConfigServiceLineHandler serviceLineHandler = null;
	private EnnMonitorConfigClusterHandler clusterHandler = null;
	
	private static final int dataCount = 3;
	
	public EnnMonitorConfigDataThread(EnnMonitorConfigServiceTableCtl serviceTableCtl,
			EnnMonitorConfigServiceLineTableCtl serviceLineTableCtl,
			EnnMonitorConfigClusterTableCtl clusterTableCtl,
			EnnMonitorConfigServiceHandler serviceHandler,
			EnnMonitorConfigServiceLineHandler serviceLineHandler,
			EnnMonitorConfigClusterHandler clusterHandler) {
		this.serviceTableCtl = serviceTableCtl;
		this.serviceLineTableCtl = serviceLineTableCtl;
		this.clusterTableCtl = clusterTableCtl;
		
		this.serviceHandler = serviceHandler;
		this.serviceLineHandler = serviceLineHandler;
		this.clusterHandler = clusterHandler;
	}

	@Override
	public void run() {
		while (true) {
			logger.info("the data thread running");
			
			try {
				dataServiceLine();
				dataCluster();
				
				Thread.sleep(5000);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	private void dataServiceLine() throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		BasicDBObject orderDBObject = new BasicDBObject();
		
		queryDBObject.put(EnnMonitorConfigServiceLineTableField.SeqNoFieldName, new BasicDBObject("$gt", -1));
		queryDBObject.put(EnnMonitorConfigServiceLineTableField.StatusFieldName, EnnMonitorConfigServiceLineStatus.ServiceLineDeleting.name());
		orderDBObject.put(EnnMonitorConfigServiceLineTableField.SeqNoFieldName, 1);
		
		List<EnnMonitorConfigServiceLineTable> serviceLineTableList = null; 
		
		serviceLineTableList = serviceLineTableCtl.getMongoDBCtrl().searchData(queryDBObject, orderDBObject, 0, 50);
		while (serviceLineTableList != null && serviceLineTableList.size() > 0) {
			for (int i = 0; i < serviceLineTableList.size(); ++i) {
				deleteServiceLine(serviceLineTableList.get(i).getId());
			}
			
			queryDBObject.put(EnnMonitorConfigServiceLineTableField.SeqNoFieldName, 
					new BasicDBObject("$gt", serviceLineTableList.get(serviceLineTableList.size() - 1).getSeqNo()));
			serviceLineTableList = serviceLineTableCtl.getMongoDBCtrl().searchData(queryDBObject, orderDBObject, 0, 50);
		}
	}
	
	private void deleteServiceLine(long id) throws Exception {
		BasicDBObject queryDBObject = null;
		BasicDBObject orderDBObject = null;
		
		List<EnnMonitorConfigServiceTable> serviceList = null;
		
		if (serviceLineMap.containsKey(id) == true) {
			if (serviceLineMap.get(id) >= dataCount) {
				serviceLineHandler.purgeEnnMonitorConfigServiceLine(
						EnnMonitorConfigServiceLineDeleteRequest.newBuilder().setId(id).build());
				return;
			}
			
			queryDBObject = new BasicDBObject();
			queryDBObject.put(EnnMonitorConfigServiceTableField.BelongToServiceLineFieldName, id);
			if (serviceTableCtl.getMongoDBCtrl().getCount(queryDBObject) <= 0) {
				serviceLineMap.put(id, serviceLineMap.get(id) + 1);
			} else {
				serviceLineMap.put(id, -1l);
			}
			
			return;
		}
		
		serviceLineMap.put(id, -1l);
		EnnMonitorConfigBlackListCacheUtil.addServicLineId(id);
		
		queryDBObject = new BasicDBObject(EnnMonitorConfigServiceTableField.BelongToServiceLineFieldName, id);
		queryDBObject.put(EnnMonitorConfigServiceTableField.SeqNoFieldName, new BasicDBObject("$gt", -1));
		orderDBObject = new BasicDBObject(EnnMonitorConfigServiceTableField.SeqNoFieldName, 1);
		
		serviceList = serviceTableCtl.getMongoDBCtrl().searchData(queryDBObject, orderDBObject, 0, 50);
		while (serviceList != null && serviceList.size() > 0) {
			for (int i = 0; i < serviceList.size(); ++i) {
				serviceHandler.deleteEnnMonitorConfigService(
						EnnMonitorConfigServiceDeleteRequest.newBuilder().setId(serviceList.get(i).getId()).build());
			}
			
			queryDBObject.put(EnnMonitorConfigServiceTableField.SeqNoFieldName, new BasicDBObject("$gt", serviceList.get(serviceList.size() - 1).getSeqNo()));
			serviceList = serviceTableCtl.getMongoDBCtrl().searchData(queryDBObject, orderDBObject, 0, 50);
		}
	}
	
	private void dataCluster() throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		BasicDBObject orderDBObject = new BasicDBObject();
		
		queryDBObject.put(EnnMonitorConfigClusterTableField.SeqNoFieldName, new BasicDBObject("$gt", -1));
		queryDBObject.put(EnnMonitorConfigClusterTableField.StatusFieldName, EnnMonitorConfigClusterStatus.ClusterDeleting.name());
		orderDBObject.put(EnnMonitorConfigClusterTableField.SeqNoFieldName, 1);
		
		List<EnnMonitorConfigClusterTable> clusterTableList = null; 
		
		clusterTableList = clusterTableCtl.getMongoDBCtrl().searchData(queryDBObject, orderDBObject, 0, 50);
		while (clusterTableList != null && clusterTableList.size() > 0) {
			for (int i = 0; i < clusterTableList.size(); ++i) {
				deleteCluster(clusterTableList.get(i).getId());
			}
			
			queryDBObject.put(EnnMonitorConfigClusterTableField.SeqNoFieldName, 
					new BasicDBObject("$gt", clusterTableList.get(clusterTableList.size() - 1).getSeqNo()));
			clusterTableList = clusterTableCtl.getMongoDBCtrl().searchData(queryDBObject, orderDBObject, 0, 50);
		}
	}
	
	private void deleteCluster(long id) throws Exception {
		BasicDBObject queryDBObject = null;
		BasicDBObject orderDBObject = null;
		
		List<EnnMonitorConfigServiceLineTable> serviceLineList = null;
		
		if (clusterMap.containsKey(id) == true) {
			if (clusterMap.get(id) >= dataCount) {
				clusterHandler.purgeEnnMonitorConfigCluster(
						EnnMonitorConfigClusterDeleteRequest.newBuilder().setId(id).build());
				return;
			}
			
			queryDBObject = new BasicDBObject();
			queryDBObject.put(EnnMonitorConfigServiceLineTableField.BelongToClusterFieldName, id);
			if (serviceLineTableCtl.getMongoDBCtrl().getCount(queryDBObject) <= 0) {
				clusterMap.put(id, clusterMap.get(id) + 1);
			} else {
				clusterMap.put(id, -1l);
			}
			
			return;
		}
		
		clusterMap.put(id, -1l);
		EnnMonitorConfigBlackListCacheUtil.addClusterId(id);
		
		queryDBObject = new BasicDBObject(EnnMonitorConfigServiceLineTableField.BelongToClusterFieldName, id);
		queryDBObject.put(EnnMonitorConfigServiceLineTableField.SeqNoFieldName, new BasicDBObject("$gt", -1));
		orderDBObject = new BasicDBObject(EnnMonitorConfigServiceLineTableField.SeqNoFieldName, 1);
		
		serviceLineList = serviceLineTableCtl.getMongoDBCtrl().searchData(queryDBObject, orderDBObject, 0, 50);
		while (serviceLineList != null && serviceLineList.size() > 0) {
			for (int i = 0; i < serviceLineList.size(); ++i) {
				serviceLineHandler.deleteEnnMonitorConfigServiceLine(
						EnnMonitorConfigServiceLineDeleteRequest.newBuilder().setId(serviceLineList.get(i).getId()).setLastUpdateUser(serviceLineList.get(i).getLastUpdateUser()).build());
			}
			
			queryDBObject.put(EnnMonitorConfigServiceLineTableField.SeqNoFieldName, new BasicDBObject("$gt", serviceLineList.get(serviceLineList.size() - 1).getSeqNo()));
			serviceLineList = serviceLineTableCtl.getMongoDBCtrl().searchData(queryDBObject, orderDBObject, 0, 50);
		}
	}

}
