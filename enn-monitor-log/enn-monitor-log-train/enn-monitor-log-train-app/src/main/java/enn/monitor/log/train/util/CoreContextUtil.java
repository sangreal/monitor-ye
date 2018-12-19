package enn.monitor.log.train.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.framework.ai.nn.NNObject;
import enn.monitor.log.train.common.nn.data.TrainNNData;

public class CoreContextUtil {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static long tokenId = -1l;
	
	private static boolean isTemplate = false;
	
	private static NNObject nnObject = null;
	
	private static TrainNNData trainNNData = null;
	
	private static Map<String, Long> tagMap = new HashMap<String, Long>();
	
	public static long getTokenId() {
		return tokenId;
	}

	public static void setTokenId(long tokenId) {
		logger.info("set tokenId, it is " + tokenId);
		CoreContextUtil.tokenId = tokenId;
	}

	public static NNObject getNnObject() {
		return nnObject;
	}
	
	public static boolean isTemplate() {
		return isTemplate;
	}

	public static void setTemplate(boolean isTemplate) {
		CoreContextUtil.isTemplate = isTemplate;
	}

	public static void setNnObject(NNObject nnObject) {
		logger.info("set nnObject");;
		CoreContextUtil.nnObject = nnObject;
	}

	public static TrainNNData getTrainNNData() {
		return trainNNData;
	}

	public static void setTrainNNData(TrainNNData trainNNData) {
		logger.info("set nnData");
		CoreContextUtil.trainNNData = trainNNData;
	}
	
	public static void addTag(String tag, long tagId) {
		tagMap.put(tag, tagId);
	}
	
	public static Long getTagId(String tag) {
		return tagMap.get(tag);
	}
	
	public static void tagClear() {
		tagMap.clear();
	}

}
