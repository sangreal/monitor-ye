package enn.monitor.log.normalizing.logformat.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.framework.lang.format.aggregator.LangFormatAggregatorFSM;
import enn.monitor.framework.lang.format.regex.core.LangFormatRegexFSM;

public class EnnMonitorLogNormalizingLogformatCache {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static Map<Long, EnnMonitorLogNormalizingLogFormat> logformatMap = new ConcurrentHashMap<Long, EnnMonitorLogNormalizingLogFormat>();
	
	public static void add(Long id, String regex, String logformat) {
		EnnMonitorLogNormalizingLogFormat data = new EnnMonitorLogNormalizingLogFormat();
		
		try {
			data.regexFSM.parse(regex);
			data.aggregatorFSM.parse(logformat);
			logformatMap.put(id, data);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public static void delete(Long id) {
		logformatMap.remove(id);
	}
	
	public static EnnMonitorLogNormalizingLogFormat getEnnMonitorLogNormalizingLogFormat(long id) {
		return logformatMap.get(id);
	}
	
	public static class EnnMonitorLogNormalizingLogFormat {
		public LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		public LangFormatAggregatorFSM aggregatorFSM = new LangFormatAggregatorFSM();
	}

}
