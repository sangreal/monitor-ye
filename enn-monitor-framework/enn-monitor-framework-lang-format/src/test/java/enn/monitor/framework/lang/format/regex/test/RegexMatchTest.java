package enn.monitor.framework.lang.format.regex.test;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import enn.monitor.framework.lang.format.regex.core.LangFormatRegexFSM;
import junit.framework.TestCase;

public class RegexMatchTest extends TestCase {
	
	public void test0() throws Exception {
		// 按指定模式在字符串查找
	      String line = "This order was placed for QT3000! OK?";
	      String pattern = "(T(h(\\D*)was(\\D*)))(\\d*)(.*)";
	 
	      // 创建 Pattern 对象
	      Pattern r = Pattern.compile(pattern);
	 
	      // 现在创建 matcher 对象
	      Matcher m = r.matcher(line);
	      if (m.find( )) {
	    	  System.out.println("Found value: " + m.group(0) );
	    	  System.out.println("Found value: " + m.group(1) );
	    	  System.out.println("Found value: " + m.group(2) );
	    	  System.out.println("Found value: " + m.group(3) ); 
	    	  System.out.println("Found value: " + m.group(4) ); 
	    	  System.out.println("Found value: " + m.group(5) ); 
	    	  System.out.println("Found value: " + m.group(6) ); 
	      } else {
	    	  System.out.println("NO MATCH");
	      }
	}
	
	public void test1() throws Exception {
		// 按指定模式在字符串查找
	      String line = "This order was placed for QT3000! OK?";
	      String pattern = "(\\D*)\\d+()(.*)";
	 
	      // 创建 Pattern 对象
	      Pattern r = Pattern.compile(pattern);
	 
	      // 现在创建 matcher 对象
	      Matcher m = r.matcher(line);
	      if (m.find( )) {
	         System.out.println("Found value: " + m.group(0) );
	         System.out.println("Found value: " + m.group(1) );
	         System.out.println("Found value: " + m.group(2) );
	         System.out.println("Found value: " + m.group(3) );
	      } else {
	         System.out.println("NO MATCH");
	      }
	}
	
	public void test2() throws Exception {
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		
		regexFSM.parse("^(?<logLevel>[\\w])(?<month>\\d\\d)(?<day>\\d\\d)[\\s]*(?<time>[^\\s]*)[\\s]*(?<pid>[\\d]*)[\\s]*(?<position>[^\\s]*)\\][\\s]*(?<log>.*)$");
		logPrint(regexFSM.match("I0718 17:07:47.529308     758 server.go:770] Started logformatlet v1.5.1-148+69ea644b53bc73-dirty"));
	}
	
	public void test3() throws Exception {
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		
		regexFSM.parse("^(?<date>[\\d\\-]+)T(?<time>[\\d\\:\\.]+)[^\\s]*[\\s]*(?<logLevel>[^\\s]+)[\\s]*(?<position>[^\\s]*)[\\s]*\\[(?<threadName>[^\\s\\]]*)\\][\\s]*(?<log>.*)$");
		logPrint(regexFSM.match("2017-07-16T15:55:48.059+0800 I NETWORK  [conn13272] received client metadata from 172.5.240.8:37262 conn13272: { application: { name: \"MongoDB Shell\" }, driver: { name: \"MongoDB Internal Client\", version: \"3.4.6\" }, os: { type: \"Linux\", name: \"Ubuntu\", architecture: \"x86_64\", version: \"16.04\" } }\n"));
	}
	
	public void test4() throws Exception {
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		
		regexFSM.parse("^(?<dateTime>[\\d\\-]+[\\s]*[\\d\\:\\.]+)[\\s]*(?<logLevel>[\\w]+)[\\s]*(?<pid>[\\d]+)[\\s\\-]*\\[(?<threadName>[^\\]]+)\\][\\s]*(?<position>[^\\s]+)[\\s:]*(\\[topic=(?<customTopic>[^\\]]*)\\])?[\\s]*(?<log>.*)$");
		logPrint(regexFSM.match("2017-06-14 16:39:04.229  INFO 26069 --- [onPool-worker-1] c.g.filters.post.RequestAuditFilter      : [topic=console-audit] RequestAuditMessage=RequestAuditMessage{service=GATEWAY, requestId='1be6e1c5-2857-426d-a308-2cbf814dc87e', userId='null', namespaceName='null', url='https://localhost:8809/gw/as/api/v1', httpMethod='GET', httpStatus=200, clientIp='127.0.0.1', startTime=Wed Jun 14 16:39:04 CST 2017, elapsed=80, extras='{}'}"));
	}
	
	public void test5() throws Exception {
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		
		regexFSM.parse("^(?<dateTime>[\\d\\-]+[\\s]*[\\d\\:\\.]+)[\\s]*(?<logLevel>[\\w]+)[\\s]*(?<pid>[\\d]+)[\\s\\-]*\\[(?<threadName>[^\\]]+)\\][\\s]*(?<position>[^\\s]+)[\\s:]*(\\[topic=(?<customTopic>[^\\]]*)\\])?[\\s]*(?<log>.*)$");
		logPrint(regexFSM.match("2017-06-14 16:39:04.229  INFO 26069 --- [onPool-worker-1] c.g.filters.post.RequestAuditFilter      : RequestAuditMessage=RequestAuditMessage{service=GATEWAY, requestId='1be6e1c5-2857-426d-a308-2cbf814dc87e', userId='null', namespaceName='null', url='https://localhost:8809/gw/as/api/v1', httpMethod='GET', httpStatus=200, clientIp='127.0.0.1', startTime=Wed Jun 14 16:39:04 CST 2017, elapsed=80, extras='{}'}"));
	}
	
	private void logPrint(Map<String, String> result) {
		if (result == null) {
			return;
		}
		
		System.out.println("log print");
		for (Map.Entry<String, String> entry : result.entrySet()) {
			System.out.println("Key: " + entry.getKey() + " ------ Value: " + entry.getValue());
		}
	}

}
