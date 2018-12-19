package enn.monitor.log.analyse.template.extraction.server;

public class EnnMonitorLogAnalyseTemplateTest {
	
	public static void main(String[] args) throws Exception {
//		System.out.println(StringUtils.isNumeric("16465413651351354"));
//		
//		int j;
//		String log = " directkafk\\ainp/utdst ream@79b^`02e;12?fs|ef!sefsef";
//		
//		String[] words = log.split("[,:\\s\\\\=()\\[\\]\\{\\}\"-/_@^`;?|!]");
//		
//		Set<Character> charSet = new HashSet<Character>();
//		charSet.add(',');
//		charSet.add(':');
//		charSet.add('\\');
//		charSet.add('=');
//		charSet.add('(');
//		charSet.add(')');
//		charSet.add('[');
//		charSet.add(']');
//		charSet.add('{');
//		charSet.add('}');
//		charSet.add('\"');
//		charSet.add('-');
//		charSet.add('/');
//		charSet.add('_');
//		charSet.add('@');
//		charSet.add('^');
//		charSet.add('`');
//		charSet.add(';');
//		charSet.add('?');
//		charSet.add('|');
//		charSet.add('!');
//		charSet.add('#');
//		charSet.add('$');
//		charSet.add('%');
//		charSet.add('&');
//		charSet.add('*');
//		charSet.add('+');	
//		
//		boolean isContainNumber = false;
//		StringBuilder word = new StringBuilder();
//		
//		for (int i = 0; i < log.length(); ++i) {
//			if (Character.isWhitespace(log.charAt(i)) == true || charSet.contains(log.charAt(i)) == true) {
//				if (word.length() > 0 && isContainNumber == false) {
//					System.out.println(word.toString());
//					word = new StringBuilder();
//				} else {
//					isContainNumber = false;
//				}
//				
//				continue;
//			}
//			
//			if (Character.isDigit(log.charAt(i)) == true) {
//				isContainNumber = true;
//			}
//			
//			word.append(log.charAt(i));
//		}
//		
//		
//		for (int i = 0; i < words.length; ++i) {
//			if (words[i].trim().equals("") == true) {
//				continue;
//			}
//			System.out.println(words[i]);
//		}
//		
//		List<Pattern> patternList = new ArrayList<Pattern>();
//		
//		patternList.add(Pattern.compile("^[\\d\\.]+$"));
//		patternList.add(Pattern.compile("^\\d+gb$"));
//		
//		for (j = 0; j < patternList.size(); ++j) {
//			Matcher m = patternList.get(j).matcher("117gb");
//		    if (m.find() == true) {
//		    	break;
//		    }
//		}
//		if (j < patternList.size()) {
//			System.out.println("true");
//		} else {
//			System.out.println("false");
//		}
		
//		System.out.println(Math.log(2));
//		
//		Set<String> a = new HashSet<String>();
//		a.add("a");
//		a.add("b");
//		
//		String s[] = (String[])a.toArray(new String[a.size()]);
//		
//		for (int i = 0; i < s.length; ++i) {
//			System.out.println(s[i]);
//		}
		
		String log = "a-b-c";
		System.out.println(log.substring(log.indexOf('-') + 1));
		
	}

}
