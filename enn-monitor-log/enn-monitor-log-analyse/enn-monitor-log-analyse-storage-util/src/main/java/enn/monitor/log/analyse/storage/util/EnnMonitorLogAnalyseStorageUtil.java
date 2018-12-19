package enn.monitor.log.analyse.storage.util;

import java.util.List;

import com.mongodb.BasicDBObject;

import enn.monitor.log.analyse.storage.data.EnnMonitorLogAnalyseStorageData;
import enn.monitor.log.analyse.storage.tablectl.EnnMonitorLogAnalyseStorageTableCtl;
import enn.monitor.log.analyse.storage.tablectl.EnnMonitorLogAnalyseStorageTableField;

public class EnnMonitorLogAnalyseStorageUtil {
	
	private static String uri = null;
	private static String dbName = null;
	private static String tableName = null;
	
	private static EnnMonitorLogAnalyseStorageTableCtl logAnalyseStorageClient = null;
	
	public static void init(String uri1, String dbName1, String tableName1) {
		uri = uri1;
		dbName = dbName1;
		tableName = tableName1;
	}
	
	/*public static EnnMonitorLogAnalyseStorageTableCtl getEnnMonitorLogStorageClient() throws Exception {
		if (logAnalyseStorageClient == null) {
			synchronized (EnnMonitorLogAnalyseStorageTableCtl.class) {
				if (logAnalyseStorageClient == null) {
					logAnalyseStorageClient = new EnnMonitorLogAnalyseStorageTableCtl(uri, dbName, tableName);
				}
			}
		}
		return logAnalyseStorageClient;
	}*/
	
	public static EnnMonitorLogAnalyseStorageData getLastestEnnMonitorLogAnalyseStorageData() throws Exception {
		BasicDBObject queryDBObject = new BasicDBObject();
		BasicDBObject sortDBObject = new BasicDBObject();
		
		List<EnnMonitorLogAnalyseStorageData> dataList = null;
		
		if (logAnalyseStorageClient == null) {
			synchronized (EnnMonitorLogAnalyseStorageTableCtl.class) {
				if (logAnalyseStorageClient == null) {
					logAnalyseStorageClient = new EnnMonitorLogAnalyseStorageTableCtl(uri, dbName, tableName);
				}
			}
		}
		
		sortDBObject.put(EnnMonitorLogAnalyseStorageTableField.SeqNoFieldName, -1);
		
		dataList = logAnalyseStorageClient.getMongoDBCtrl().searchData(queryDBObject, sortDBObject, 0, 2);
		if (dataList != null && dataList.size() > 0) {
			for (EnnMonitorLogAnalyseStorageData data : dataList) {
				if (data.getId() > 0) {
					return data;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		EnnMonitorLogAnalyseStorageUtil.init("mongodb://10.19.248.200:32017/EnnMonitorLogAnalyseStorage", "EnnMonitorLogAnalyseStorage", "EnnMonitorLogAnalyseStorageTable");
		EnnMonitorLogAnalyseStorageUtil.getLastestEnnMonitorLogAnalyseStorageData();
	}

}
