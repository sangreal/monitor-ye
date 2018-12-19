package enn.monitor.log.analyse.template.util;

import java.util.Arrays;
import java.util.Set;

public class EnnMonitorLogAnalyseTemplateUtil {
	
	public static String convertFromSetToString(long tokenId, Set<String> wordSet, boolean isValid) throws Exception {
		int i;
		String[] wordArray = null;
		
		StringBuilder key = new StringBuilder();
		
		if (isValid == true && (wordSet == null || wordSet.size() <= 0)) {
			return null;
		}
		
		wordArray = new String[wordSet.size()];
		i = 0;
		for (String word : wordSet) {
			wordArray[i++] = word;
		}
		Arrays.sort(wordArray);
		
		key.append(tokenId);
		for (i = 0; i < wordArray.length; ++i) {
			key.append("-").append(wordArray[i]);
		}
		
		return key.toString();
	}

}
