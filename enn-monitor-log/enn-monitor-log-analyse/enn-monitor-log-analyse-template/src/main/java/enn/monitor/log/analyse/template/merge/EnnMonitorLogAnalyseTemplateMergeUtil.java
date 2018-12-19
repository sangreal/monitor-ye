package enn.monitor.log.analyse.template.merge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import enn.monitor.framework.common.unionfindset.EnnMonitorFrameworkCommonUnionFindSet;
import enn.monitor.log.analyse.template.core.EnnMonitorAnalyseTemplateCore;
import enn.monitor.log.analyse.template.data.EnnMonitorLogAnalyseTemplateMergeTerm;
import enn.monitor.log.analyse.template.util.EnnMonitorLogAnalyseTemplateUtil;

public class EnnMonitorLogAnalyseTemplateMergeUtil {
	
	private static CounterMetric similarTemplateMetrics1 = MetricManager.getCounterMetric(EnnMonitorAnalyseTemplateCore.class, "similarTemplate1");
	private static CounterMetric similarTemplateMetrics2 = MetricManager.getCounterMetric(EnnMonitorAnalyseTemplateCore.class, "similarTemplate2");
	private static CounterMetric similarTemplateMetrics3 = MetricManager.getCounterMetric(EnnMonitorAnalyseTemplateCore.class, "similarTemplate3");
	
	public static Map<String, Set<String>> mergeSimilarTemplate(double similarRatio, 
			Map<String, Set<String>> templateMap, 
			Map<Long, Set<String>> addKeySet,
			Map<Long, Set<String>> filterKeySet) throws Exception {
		long tokenId = -1l;
		String[] tokens = null;
		
		List<EnnMonitorLogAnalyseTemplateMergeTerm> templateList = null;
		Map<Long, List<EnnMonitorLogAnalyseTemplateMergeTerm>> templateByIdMap = new HashMap<Long, List<EnnMonitorLogAnalyseTemplateMergeTerm>>();
		
		Set<String> templateSet = null;
		Map<String, Set<String>> mergeTemplateMap = null;
		
		Map<String, Set<String>> resultMap = new HashMap<String, Set<String>>();
		
		// 按照tokenId归类
		for (String template : templateMap.keySet()) {
			similarTemplateMetrics1.markEvent();
			
			EnnMonitorLogAnalyseTemplateMergeTerm mergeTerm = new EnnMonitorLogAnalyseTemplateMergeTerm();
			
			mergeTerm.template = template;
			tokens = template.split("-");
			
			tokenId = Long.parseLong(tokens[0]);
			
			for (int i = 1; i < tokens.length; ++i) {
				if (filterKeySet.containsKey(tokenId) == true && filterKeySet.get(tokenId).contains(tokens[i]) == true) {
					continue;
				}
				
				if (addKeySet.containsKey(tokenId) == true && addKeySet.get(tokenId).contains(tokens[i]) == true) {
					mergeTerm.keyTemplate = mergeTerm.keyTemplate + "-" + tokens[i];
					continue;
				}
				
				mergeTerm.wordSet.add(tokens[i]);
			}
			
			templateList = templateByIdMap.get(tokenId);
			if (templateList == null) {
				templateList = new ArrayList<EnnMonitorLogAnalyseTemplateMergeTerm>();
				templateByIdMap.put(tokenId, templateList);
			}
			
			templateList.add(mergeTerm);
		}
		
		for (Map.Entry<Long, List<EnnMonitorLogAnalyseTemplateMergeTerm>> entry : templateByIdMap.entrySet()) {
			mergeTemplateMap = mergeSimilarTemplateById(similarRatio, entry.getKey(), entry.getValue());
			for (Map.Entry<String, Set<String>> templateSetEntry : mergeTemplateMap.entrySet()) {
				templateSet = new HashSet<String>();
				for (String template : templateSetEntry.getValue()) {
					templateSet.addAll(templateMap.get(template));
				}
				resultMap.put(templateSetEntry.getKey(), templateSet);
			}
		}
		
		return resultMap;
	}
	
	private static Map<String, Set<String>> mergeSimilarTemplateById(double similarRatio, long tokenId,
			List<EnnMonitorLogAnalyseTemplateMergeTerm> templateList) throws Exception {
		int i;
		
		String[] keyArr = null;
		Set<String> keywordSet = null;
		
		EnnMonitorFrameworkCommonUnionFindSet unionFindSet = null;
		
		List<Integer> idList = null;
		
		Map<Integer, List<Integer>> mergeTermByIdMap = null;
		
		Set<EnnMonitorLogAnalyseTemplateMergeTerm> mergeTermByKeySet = null;
		Map<String, Set<EnnMonitorLogAnalyseTemplateMergeTerm>> templateByKeyMap = 
				new HashMap<String, Set<EnnMonitorLogAnalyseTemplateMergeTerm>>();
		
		List<EnnMonitorLogAnalyseTemplateMergeTerm> mergeTermList = null;
		
		Map<String, Set<String>> resultMap = new HashMap<String, Set<String>>();
		
		// 按照关键字分类
		for (i = 0; i < templateList.size(); ++i) {
			mergeTermByKeySet = templateByKeyMap.get(templateList.get(i).keyTemplate);
			if (mergeTermByKeySet == null) {
				mergeTermByKeySet = new HashSet<EnnMonitorLogAnalyseTemplateMergeTerm>();
				templateByKeyMap.put(templateList.get(i).keyTemplate, mergeTermByKeySet);
			}
			mergeTermByKeySet.add(templateList.get(i));
		}
		
		// keyword
		for (Map.Entry<String, Set<EnnMonitorLogAnalyseTemplateMergeTerm>> entry : templateByKeyMap.entrySet()) {
			keyArr = entry.getKey().split("-");
			keywordSet = new HashSet<String>();
			for (i = 0; i < keyArr.length; ++i) {
				if (keyArr[i] != null && keyArr[i].equals("") == false) {
					keywordSet.add(keyArr[i]);
				}
			}
			
			mergeTermList = new ArrayList<EnnMonitorLogAnalyseTemplateMergeTerm>(entry.getValue());
			unionFindSet = new EnnMonitorFrameworkCommonUnionFindSet(mergeTermList.size());
			
			binMerge(similarRatio, keywordSet, 0, mergeTermList.size() - 1, unionFindSet, mergeTermList);
			
//			for (i = 0; i < mergeTermList.size(); ++i) {
//				for (j = i + 1; j < mergeTermList.size(); ++j) {
//					similarTemplateMetrics2.markEvent();
//					rootI = unionFindSet.getRootId(i, 0);
//					rootJ = unionFindSet.getRootId(j, 0);
//					if (rootI == rootJ) {
//						continue;
//					}
//					distance = computeDistance(keywordSet.size(), mergeTermList.get(i), mergeTermList.get(j));
//					if (distance <= similarRatio) {
//						continue;
//					}
//					unionFindSet.mergeSet(i, j);
//				}
//			}
			
			mergeTermByIdMap = new HashMap<Integer, List<Integer>>();
			for (i = 0; i < unionFindSet.size(); ++i) {
				int rootId = unionFindSet.getRootId(i, 0);
				idList = mergeTermByIdMap.get(rootId);
				if (idList == null) {
					idList = new ArrayList<Integer>();
					mergeTermByIdMap.put(rootId, idList);
				}
				idList.add(i);
			}
			
			extractionTemplate(tokenId, keywordSet, mergeTermByIdMap, mergeTermList, resultMap);
		}
		
		return resultMap;
	}
	
	private static void binMerge(double similarRatio, Set<String> keywordSet, int begin, int end, 
			EnnMonitorFrameworkCommonUnionFindSet unionFindSet, List<EnnMonitorLogAnalyseTemplateMergeTerm> mergeTermList) throws Exception {
		int i, j;
		int mid;
		
		int rootI, rootJ;
		
		double distance;
		
		similarTemplateMetrics2.markEvent();
		
		if (begin >= end) {
			return;
		}
		
		mid = (begin + end) / 2;
		binMerge(similarRatio, keywordSet, begin, mid, unionFindSet, mergeTermList);
		binMerge(similarRatio, keywordSet, mid + 1, end, unionFindSet, mergeTermList);
		
		for (i = begin; i <= mid; ++i) {
			for (j = mid + 1; j <= end; ++j) {
				rootI = unionFindSet.getRootId(i, 0);
				rootJ = unionFindSet.getRootId(j, 0);
				if (rootI == rootJ) {
					continue;
				}
				distance = computeDistance(keywordSet.size(), mergeTermList.get(i), mergeTermList.get(j));
				if (distance <= similarRatio) {
					continue;
				}
				unionFindSet.mergeSet(i, j);
			}
		}
		
		for (i = begin; i <= end; ++i) {
			unionFindSet.getRootId(i, 0);
		}
	}
	
	private static void extractionTemplate(long tokenId, Set<String> keywordSet, Map<Integer, List<Integer>> mergeTermByIdMap,
			List<EnnMonitorLogAnalyseTemplateMergeTerm> mergeTermList,
			Map<String, Set<String>> resultMap) throws Exception {
		int i;
		List<Integer> idList = null;
		
		Set<String> wordSet = null;
		Set<String> templateKeySet = null;
		
		String key = null;
		
		EnnMonitorLogAnalyseTemplateMergeTerm mergeTerm = null;
		
		for (Map.Entry<Integer, List<Integer>> entry: mergeTermByIdMap.entrySet()) {
			similarTemplateMetrics3.markEvent();
			
			idList = entry.getValue();
			
			wordSet = new HashSet<String>();
			if (idList.size() == 1) {
				wordSet.addAll(mergeTermList.get(idList.get(0)).wordSet);
			} else {
				for (String word : mergeTermList.get(idList.get(0)).wordSet) {
					for (i = 1; i < idList.size(); ++i) {
						mergeTerm = mergeTermList.get(idList.get(i));
						if (mergeTerm.wordSet.contains(word) == false) {
							break;
						}
					}
					if (i == idList.size()) {
						wordSet.add(word);
					}
				}
			}
			
			wordSet.addAll(keywordSet);
			key = EnnMonitorLogAnalyseTemplateUtil.convertFromSetToString(tokenId, wordSet, false);
			templateKeySet = resultMap.get(key);
			if (templateKeySet == null) {
				templateKeySet = new HashSet<String>();
				resultMap.put(key, templateKeySet);
			}
			for (Integer id : idList) {
				mergeTerm = mergeTermList.get(id);
				templateKeySet.add(mergeTerm.template);
			}
		}
	}

	private static double computeDistance(int commonSize, 
			EnnMonitorLogAnalyseTemplateMergeTerm term1, EnnMonitorLogAnalyseTemplateMergeTerm term2) throws Exception {
		Set<String> commonTermSet = new HashSet<String>();
		
		if (term1.wordSet.size() == 0 && term2.wordSet.size() == 0) {
			return 1;
		}
		
		if (term1.wordSet.size() == 0 || term2.wordSet.size() == 0) {
			return 0;
		}
		
		for (String term : term1.wordSet) {
			if (term2.wordSet.contains(term) == true) {
				commonTermSet.add(term);
			}
		}
		
		return (double)(commonTermSet.size() + commonSize) / (Math.sqrt(term1.wordSet.size() + commonSize) * Math.sqrt(term2.wordSet.size() + commonSize));
	}

}
