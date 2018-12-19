package enn.monitor.log.archive.task;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;

import com.mongodb.BasicDBObject;

import enn.monitor.framework.common.time.EnnDatetimeUtil;
import enn.monitor.framework.common.time.EnnTimezoneUtil;
import enn.monitor.log.archive.table.EnnMonitorLogArchiveConfigTable;
import enn.monitor.log.archive.tablectl.EnnMonitorLogArchiveConfigCtl;
import enn.monitor.log.archive.tablectl.EnnMonitorLogArchiveConfigField;
import enn.monitor.log.archive.util.EnnMonitorLogElasticsearchUtil;
import enn.monitor.log.archive.util.EnnMonitorLogStorageUtil;
import enn.monitor.log.archive.util.EnnMonitorLogStorageUtil.NodeStatsStorage;

public class EnnMonitorLogArchiveTask implements Runnable {
	private long days = 30l;
	
	private static final Logger logger = LogManager.getLogger();
	
	public EnnMonitorLogArchiveTask(EnnMonitorLogArchiveConfigCtl archiveStorageCtl) throws Exception {
		days = getDays(archiveStorageCtl);
	}
	
	public void setDays(long days) {
		this.days = days;
	}

	@Override
	public void run() {
		while (true) {
			logger.info("archive start");
			
			try {
				// 1. 删除过期日志
				deleteIndicesByDate(days);
				
				// 2. 从最早的日志开始
				deleteIndicesByStorage();
				
				Thread.sleep(3600000);
//				Thread.sleep(1000);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
		}
	}
	
	private void deleteIndicesByStorage() throws Exception {
		double percent;
		
		String indexName = null;
		
		long currentMills = System.currentTimeMillis();
		String dateTime = null;
		String[] items = null;
		
		int year, month, day;
		
		percent = computeStoragePercent();
		if (percent < 0.85) {
			return;
		}
		
		currentMills -= days * 24 * 60 * 60 * 1000;
		
		dateTime = EnnDatetimeUtil.convertLongToStringWithDate(currentMills, EnnTimezoneUtil.getChinaTimeZone());
		items = dateTime.split("-");
		
		year = Integer.parseInt(items[0]);
		month = Integer.parseInt(items[1]);
		day = Integer.parseInt(items[2]);
		while (percent >= 0.80) {
			indexName = "*-" + year;
			if (month < 10) {
				indexName += "-0" + month;
			} else {
				indexName += "-" + month;
			}
			if (day < 10) {
				indexName += "-0" + day;
			} else {
				indexName += "-" + day;
			}
			
			logger.info("the index name is {}", indexName);
			
			++day;
			if (day > 31) {
				day = 1;
				++month;
				if (month > 12) {
					month = 1;
					++year;
				}
			}
			
			IndicesExistsResponse  response = 
					EnnMonitorLogElasticsearchUtil.getElasticsearchClient().admin().indices().exists( 
	                        new IndicesExistsRequest().indices(new String[]{indexName})).actionGet();
			if (response.isExists() == false) {
				continue;
			}
			
			deleteIndices(indexName);
			
			percent = computeStoragePercent();
		}
	}
	
	private double computeStoragePercent() {
		NodeStatsStorage storage = null;
		
		double percent = -1.0;
		
		storage = EnnMonitorLogStorageUtil.getNodeStatsStorageInfo();
		if (storage != null) {
			if (storage.total > 0) {
				percent = (double)storage.usage / (double)storage.total;
			}
		}

		logger.info("the storage percent is {}", percent);
        return percent;
	}
	
	private void deleteIndicesByDate(long days) throws Exception {
		int i;
		String indexName = null;
		
		String testPrefix = "*";
		String indexPrefix = null;
		
		long currentMills = System.currentTimeMillis();
		String dateTime = null;
		String[] items = null;
		
		int year, month, day;
		
		currentMills -= days * 24 * 60 * 60 * 1000;
		
		dateTime = EnnDatetimeUtil.convertLongToStringWithDate(currentMills, EnnTimezoneUtil.getChinaTimeZone());
		items = dateTime.split("-");
//		items[1] = "10";
		
		// delete by year
		year = Integer.parseInt(items[0]);
		for (i = 2000; i < year; ++i) {
			indexName = testPrefix + "-" + i + "-*";
			logger.info("delete index name {}", indexName);
			deleteIndices(indexName);
		}
		
		// delete by month
		month = Integer.parseInt(items[1]);
		i = 1;
		indexPrefix = testPrefix + "-" + year;
		if (month >= 10) {
			i = 10;
			indexName = indexPrefix + "-0*";
			logger.info("delete index name {}", indexName);
			deleteIndices(indexName);
		}
		for (; i < month; ++i) {
			if (i < 10) {
				indexName = indexPrefix + "-0" + i + "-*";
			} else {
				indexName = indexPrefix + "-" + i + "-*";
			}
			logger.info("delete index name {}", indexName);
			deleteIndices(indexName);
		}
		
		// delete by day
		day = Integer.parseInt(items[2]);
		i = 1;
		indexPrefix = testPrefix + "-" + year;
		if (month < 10) {
			indexPrefix += "-0" + month;
		} else {
			indexPrefix += "-" + month;
		}
		if (day >= 10) {
			i = 10;
			indexName = indexPrefix + "-0*";
			logger.info("delete index name {}", indexName);
			deleteIndices(indexName);
		}
		if (day >= 20) {
			i = 20;
			indexName = indexPrefix + "-1*";
			logger.info("delete index name {}", indexName);
			deleteIndices(indexName);
		}
		if (day >= 30) {
			i = 30;
			indexName = indexPrefix + "-2*";
			logger.info("delete index name {}", indexName);
			deleteIndices(indexName);
		}
		for (; i < day; ++i) {
			if (i < 10) {
				indexName = indexPrefix + "-0" + i;
			} else {
				indexName = indexPrefix + "-" + i;
			}
			logger.info("delete index name {}", indexName);
			
			IndicesExistsResponse  response = 
					EnnMonitorLogElasticsearchUtil.getElasticsearchClient().admin().indices().exists( 
	                        new IndicesExistsRequest().indices(new String[]{indexName})).actionGet();
			if (response.isExists() == false) {
				continue;
			}
			
			deleteIndices(indexName);
		}
	}
	
	private void deleteIndices(String indices) {
		int count = 3;
	
		DeleteIndexRequest deleteIndexRequest = null;
		ActionFuture<DeleteIndexResponse> response = null;
		
		deleteIndexRequest = new DeleteIndexRequest();
		deleteIndexRequest.indices(indices);
		response = EnnMonitorLogElasticsearchUtil.getElasticsearchClient().admin().indices().delete(deleteIndexRequest);
	
		count = 3;
		while (count > 0) {
			try {
				if (response.actionGet().isAcknowledged() == true) {
					return;
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				
				try {
//					Thread.sleep(100);
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					logger.error(e1.getMessage(), e1);
				}
				--count;
				response = EnnMonitorLogElasticsearchUtil.getElasticsearchClient().admin().indices().delete(deleteIndexRequest);
			}
		}
		logger.error("indexName delete failed");
	}
	
	private long getDays(EnnMonitorLogArchiveConfigCtl archiveStorageCtl) throws Exception {
		BasicDBObject query = null;
		
		List<EnnMonitorLogArchiveConfigTable> archiveConfigTableList = null;
		
		query = new BasicDBObject();
    	query.put(EnnMonitorLogArchiveConfigField.IdFieldName, 1l);
    	archiveConfigTableList = archiveStorageCtl.getMongoDBCtrl().searchData(query, null, 0, 100);
		
    	if (archiveConfigTableList != null && archiveConfigTableList.size() > 0) {
    		return archiveConfigTableList.get(0).getDays();
    	}
    	
    	return 30l;
	}

}
