package enn.monitor.framework.lang.format.regex.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import enn.monitor.framework.lang.format.common.LangFormatLogImpl;

public class LangFormatRegexFSM extends LangFormatLogImpl {
	
	private List<String> regexGroupKeyList = new ArrayList<String>();
	
	private String regex = null;
	
	public void parse(String origin) throws Exception {
		int index = 0;
		
		StringBuilder regexBuilder = new StringBuilder();
		
		if (origin == null || origin.equals("") == true) {
			throw new Exception("the regex is null");
		}
		
		while (true) {
			if (index >= origin.length()) {
				regex = regexBuilder.toString();
				return;
			}
			
			if (Character.isWhitespace(origin.charAt(index)) == false) {
				switch (origin.charAt(index)) {
				case '\\':
					regexBuilder.append(origin.charAt(index));
					++index;
					regexBuilder.append(origin.charAt(index));
					break;
				case '[':
					index = addBracketStatement(origin, index, regexBuilder);
					continue;
				case '(':
					regexBuilder.append(origin.charAt(index));
					index = group(origin, index + 1, regexBuilder);
					continue;
				default:
					regexBuilder.append(origin.charAt(index));
					break;
				}
			}
			
			++index;
		}
	}
	
	private int group(String regex, int index, StringBuilder regexBuilder) throws Exception {
		int status = 0;
		StringBuilder keyBuilder = new StringBuilder();
		
		while (true) {
			if (index >= regex.length()) {
				return index;
			}
			
			if (Character.isWhitespace(regex.charAt(index)) == false) {
				switch (status) {
				case 0:
					if (regex.charAt(index) == '?') {
						status = 1;
					} else {
						regexGroupKeyList.add(null);
						status = 3;
						continue;
					}
					break;
				case 1:
					if (regex.charAt(index) == '<') {
						status = 2;
					}
					break;
				case 2:
					if (regex.charAt(index) == '>') {
						regexGroupKeyList.add(keyBuilder.toString());
						keyBuilder.delete(0, keyBuilder.length());
						status = 3;
					} else {
						keyBuilder.append(regex.charAt(index));
					}
					break;
				case 3:
					switch (regex.charAt(index)) {
					case '[':
						index = addBracketStatement(regex, index, regexBuilder);
						continue;
					case '(':
						regexBuilder.append(regex.charAt(index));
						index = group(regex, index + 1, regexBuilder);
						continue;
					case ')':
						index = addCharacter(regex, index, regexBuilder);
						status = 4;
						continue;
					default:
						index = addCharacter(regex, index, regexBuilder);
						continue;
					}
				case 4:
					return index;
				}
			}
			
			++index;
		}
	}
	
	private int addBracketStatement(String regex, int index, StringBuilder regexBuilder) throws Exception {
		while (true) {
			if (index >= regex.length()) {
				return index;
			}
			
			if (Character.isWhitespace(regex.charAt(index)) == false) {
				if (regex.charAt(index) == ']') {
					index = addCharacter(regex, index, regexBuilder);
					return index;
				} else {
					index = addCharacter(regex, index, regexBuilder);
					continue;
				}
			}
			
			++index;
		}
	}
	
	private int addCharacter(String regex, int index, StringBuilder regexBuilder) throws Exception {
		if (regex.charAt(index) == '\\') {
			regexBuilder.append(regex.charAt(index));
			++index;
			regexBuilder.append(regex.charAt(index));
		} else {
			regexBuilder.append(regex.charAt(index));
		}
		
		return index + 1;
	}
	
	@Override
	public void logPrint(int length) {
		for (String key : regexGroupKeyList) {
			printWhitspace(length);
			System.out.println("DataGroup: " + key);
		}
		printWhitspace(length);
		System.out.println("regex: " + regex);
	}

	public Map<String, String> match(String log) throws Exception {
		Map<String, String> result = null;
		String value = null;
		if (log == null || log.equals("") == true) {
			return null;
		}
		
		if (regex == null || regex.equals("") == true) {
			return null;
		}
		
		Pattern r = Pattern.compile(regex);
	 
	    Matcher m = r.matcher(log);
	    if (m.find() == true) {
	    	result = new HashMap<String, String>();
	    	
	    	for (int i = 0; i < regexGroupKeyList.size(); ++i) {
	    		if (regexGroupKeyList.get(i) != null && regexGroupKeyList.get(i).equals("") == false) {
	    			value = m.group(i + 1);
	    			if (value != null && value.equals("") == false) {
	    				result.put(regexGroupKeyList.get(i), value);
	    			}
	    		}
	    	}
	    	
	    	return result;
	    } else {
	    	return null;
	    }
	}

}
