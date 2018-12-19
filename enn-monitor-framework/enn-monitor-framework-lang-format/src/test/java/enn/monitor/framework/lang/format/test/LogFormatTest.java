package enn.monitor.framework.lang.format.test;

import java.util.Map;

import enn.monitor.framework.lang.format.aggregator.LangFormatAggregatorFSM;
import enn.monitor.framework.lang.format.regex.core.LangFormatRegexFSM;
import junit.framework.TestCase;

public class LogFormatTest extends TestCase {
	
	public void test0() throws Exception {
		Map<String, String> resultMap = null;
		
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		LangFormatAggregatorFSM aggregatorFSM = new LangFormatAggregatorFSM();
		
		regexFSM.parse("^(?<logLevel>[\\w])(?<month>\\d\\d)(?<day>\\d\\d)[\\s]*(?<time>[^\\s]*)[\\s]*(?<pid>[\\d]*)[\\s]*(?<position>[^\\s]*)\\][\\s]*(?<log>.*)$");
		resultMap = regexFSM.match("I0718 17:07:47.529308     758 server.go:770] Started logformatlet v1.5.1-148+69ea644b53bc73-dirty");
		aggregatorFSM.parse("%logLevel[I:INFO/W:WARN/E:ERROR/F:FATAL]%,%position%,%log%,<dateTime>$year$-%month%-%day% %time%");
		resultMap = aggregatorFSM.aggregator(resultMap);
		logPrint(resultMap);
	}
	
	public void test1() throws Exception {
		Map<String, String> resultMap = null;
		
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		LangFormatAggregatorFSM aggregatorFSM = new LangFormatAggregatorFSM();
		
		regexFSM.parse("^(?<date>[\\d\\-]+)T(?<time>[\\d\\:\\.]+)[^\\s]*[\\s]*(?<logLevel>[^\\s]+)[\\s]*(?<position>[^\\s]*)[\\s]*\\[(?<threadName>[^\\s\\]]*)\\][\\s]*(?<log>.*)$");
		resultMap = regexFSM.match("2017-07-16T15:55:48.059+0800 I NETWORK  [conn13272] received client metadata from 172.5.240.8:37262 conn13272: { application: { name: \"MongoDB Shell\" }, driver: { name: \"MongoDB Internal Client\", version: \"3.4.6\" }, os: { type: \"Linux\", name: \"Ubuntu\", architecture: \"x86_64\", version: \"16.04\" } }\n");
		aggregatorFSM.parse("%logLevel[I:INFO/W:WARN/E:ERROR/F:FATAL]%,%position%,%log%,<dateTime>%date% %time%,%threadName%");
		resultMap = aggregatorFSM.aggregator(resultMap);
		logPrint(resultMap);
	}
	
	public void test2() throws Exception {
		Map<String, String> resultMap = null;
		
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		LangFormatAggregatorFSM aggregatorFSM = new LangFormatAggregatorFSM();
		
		regexFSM.parse("^(?<dateTime>[\\d\\-]+[\\s]*[\\d\\:\\.]+)[\\s]*(?<logLevel>[\\w]+)[\\s]*(?<pid>[\\d]+)[\\s\\-]*\\[(?<threadName>[^\\]]+)\\][\\s]*(?<position>[^\\s]+)[\\s:]*(\\[topic=(?<customTopic>[^\\]]*)\\])?[\\s]*(?<log>.*)$");
		resultMap = regexFSM.match("2017-06-14 16:39:04.229  INFO 26069 --- [onPool-worker-1] c.g.filters.post.RequestAuditFilter      : [topic=console-audit] RequestAuditMessage=RequestAuditMessage{service=GATEWAY, requestId='1be6e1c5-2857-426d-a308-2cbf814dc87e', userId='null', namespaceName='null', url='https://localhost:8809/gw/as/api/v1', httpMethod='GET', httpStatus=200, clientIp='127.0.0.1', startTime=Wed Jun 14 16:39:04 CST 2017, elapsed=80, extras='{}'}");
		aggregatorFSM.parse("%dateTime%,%logLevel%,%threadName%,%position%,%customTopic%,%log%");
		resultMap = aggregatorFSM.aggregator(resultMap);
		logPrint(resultMap);
	}
	
	public void test3() throws Exception {
		Map<String, String> resultMap = null;
		
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		LangFormatAggregatorFSM aggregatorFSM = new LangFormatAggregatorFSM();
		
		regexFSM.parse("^(?<dateTime>[\\d\\-]+[\\s]*[\\d\\:\\.]+)[\\s]*(?<logLevel>[\\w]+)[\\s]*(?<pid>[\\d]+)[\\s\\-]*\\[(?<threadName>[^\\]]+)\\][\\s]*(?<position>[^\\s]+)[\\s:]*(\\[topic=(?<customTopic>[^\\]]*)\\])?[\\s]*(?<log>.*)$");
		resultMap = regexFSM.match("2017-06-14 16:39:04.229  INFO 26069 --- [onPool-worker-1] c.g.filters.post.RequestAuditFilter      : RequestAuditMessage=RequestAuditMessage{service=GATEWAY, requestId='1be6e1c5-2857-426d-a308-2cbf814dc87e', userId='null', namespaceName='null', url='https://localhost:8809/gw/as/api/v1', httpMethod='GET', httpStatus=200, clientIp='127.0.0.1', startTime=Wed Jun 14 16:39:04 CST 2017, elapsed=80, extras='{}'}");
		aggregatorFSM.parse("%dateTime%,%logLevel%,%threadName%,%position%,%customTopic%,%log%");
		resultMap = aggregatorFSM.aggregator(resultMap);
		logPrint(resultMap);
	}
	
	public void test4() throws Exception {
		Map<String, String> resultMap = null;
		
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		LangFormatAggregatorFSM aggregatorFSM = new LangFormatAggregatorFSM();
		
		regexFSM.parse("^(?<dateTime>[\\d\\-]+[\\s]*[\\d\\:\\.]+)[\\s]*(?<logLevel>[\\w]+)[\\s]*(?<pid>[\\d]+)[\\s\\-]*\\[(?<threadName>[^\\]]+)\\][\\s]*(?<position>[^\\s]+)[\\s:]*(?<log>.*)$");
		resultMap = regexFSM.match("INFO 26069 --- [onPool-worker-1] c.g.filters.post.RequestAuditFilter      : RequestAuditMessage=RequestAuditMessage{service=GATEWAY, requestId='1be6e1c5-2857-426d-a308-2cbf814dc87e', userId='null', namespaceName='null', url='https://localhost:8809/gw/as/api/v1', httpMethod='GET', httpStatus=200, clientIp='127.0.0.1', startTime=Wed Jun 14 16:39:04 CST 2017, elapsed=80, extras='{}'}");
		aggregatorFSM.parse("%dateTime%,%logLevel%,%threadName%,%position%,%customTopic%,%log%");
		resultMap = aggregatorFSM.aggregator(resultMap);
		logPrint(resultMap);
	}
	
	public void test5() throws Exception {
		Map<String, String> resultMap = null;
		
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		LangFormatAggregatorFSM aggregatorFSM = new LangFormatAggregatorFSM();
		
		regexFSM.parse("^(?<year>\\d\\d)/(?<month>\\d\\d)/(?<day>\\d\\d)");
		resultMap = regexFSM.match("18/07/12 15:46:01 INFO BlockManager: Removing RDD 6627");
		aggregatorFSM.parse("<dateTime>20%year%-%month%-%day%");
		resultMap = aggregatorFSM.aggregator(resultMap);
		logPrint(resultMap);
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
