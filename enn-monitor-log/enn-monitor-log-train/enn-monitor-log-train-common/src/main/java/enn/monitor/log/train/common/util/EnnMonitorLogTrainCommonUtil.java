package enn.monitor.log.train.common.util;

import java.util.HashSet;
import java.util.Set;

public class EnnMonitorLogTrainCommonUtil {
	
	public static Set<String> convertToWords(String log) throws Exception {
		int i;
		boolean isContainNumber = false;
		
		StringBuilder keyWords = new StringBuilder();
		Set<String> wordSet = new HashSet<String>();
		
		for (i = 0; i < log.length(); ++i) {
			if (Character.isAlphabetic(log.charAt(i)) == true) {
				keyWords.append(log.charAt(i));
				continue;
			}
			
			if (Character.isDigit(log.charAt(i)) == true) {
				isContainNumber = true;
				continue;
			}
			
			addWord(wordSet, keyWords, isContainNumber);
			isContainNumber = false;
		}
		
		addWord(wordSet, keyWords, isContainNumber);
		isContainNumber = false;
		
		return wordSet;
	}
	
	private static void addWord(Set<String> wordSet, StringBuilder keyWords, boolean isContainNumber) throws Exception {
		if (keyWords.length() > 0 && isContainNumber == false) {
			wordSet.add(keyWords.toString());
		}
		
		keyWords.delete(0, keyWords.length());
	}

}
