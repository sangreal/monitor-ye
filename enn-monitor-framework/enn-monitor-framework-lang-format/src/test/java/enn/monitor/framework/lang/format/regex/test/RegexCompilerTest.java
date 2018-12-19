package enn.monitor.framework.lang.format.regex.test;

import enn.monitor.framework.lang.format.regex.core.LangFormatRegexFSM;
import junit.framework.TestCase;

public class RegexCompilerTest extends TestCase {
	
	public void test0() throws Exception {
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		
		regexFSM.parse("^(?<logLevel>[\\w])(?<month>\\d\\d)(?<day>\\d\\d)[\\s]*(?<time>[^\\s]*)[\\s]*(?<pid>[\\d]*)[\\s]*(?<position>[^\\s]*)\\][\\s]*(?<log>.*)$");
		regexFSM.logPrint(0);
	}
	
	public void test1() throws Exception {
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		
		regexFSM.parse("(?<logLevel>[\\w])(?<month>\\d\\d)(?<day>\\d\\d)[\\s]*(?<time>[^\\s]*)[\\s]*(?<pid>[\\d]*)[\\s]*(?<position>[^\\s]*)\\][\\s]*(?<log>.*)$");
		regexFSM.logPrint(0);
	}
	
	public void test2() throws Exception {
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		
		regexFSM.parse("^(?<logLevel>[\\w])(?<month>\\d\\d)(?<day>\\d\\d)[\\s]*(?<time>[^\\s]*)[\\s]*(?<pid>[\\d]*)[\\s]*(?<position>[^\\s]*)\\][\\s]*(?<log>.*)");
		regexFSM.logPrint(0);
	}
	
	public void test3() throws Exception {
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		
		regexFSM.parse("(?<logLevel>[\\w])(?<month>\\d\\d)(?<day>\\d\\d)[\\s]*(?<time>[^\\s]*)[\\s]*(?<pid>[\\d]*)[\\s]*(?<position>[^\\s]*)\\][\\s]*(?<log>.*)");
		regexFSM.logPrint(0);
	}
	
	public void test4() throws Exception {
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		
		regexFSM.parse("\\d{5}\\W{5}(\\d){5}[\\d\\w]{5}\\d+");
		regexFSM.logPrint(0);
	}
	
	public void test5() throws Exception {
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		
		regexFSM.parse("\\d{5}(\\W | \\d\\w | .){5}(\\d\\W){5}[\\d\\w]{5}\\d+");
		regexFSM.logPrint(0);
	}
	
	public void test6() throws Exception {
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		
		regexFSM.parse("(\\W | \\d\\w | . | abcd){5}(\\d\\W){5}[\\d\\w]{5}\\d+");
		regexFSM.logPrint(0);
	}
	
	public void test7() throws Exception {
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		
		regexFSM.parse("[a-zA-Z0-9]");
		regexFSM.logPrint(0);
	}
	
	public void test8() throws Exception {
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		
		regexFSM.parse("[^a-zA-Z0-9\\]]");
		regexFSM.logPrint(0);
	}
	
	public void test9() throws Exception {
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		
		regexFSM.parse("(^a-zA-Z0-9\\))");
		regexFSM.logPrint(0);
	}
	
	public void test10() throws Exception {
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		
		regexFSM.parse("(ab(?<t1>s)(dlfjs(?<test1>l)f)j)");
		regexFSM.logPrint(0);
	}
	
	public void test11() throws Exception {
		LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
		
		regexFSM.parse("^(?<logLevel>[\\w])(?<month>\\d\\d)(?<day>\\d\\d)[\\s]*(?<time>[^\\s]*)[\\s]*(?<pid>[\\d]*)[\\s]*(?<position>[^\\s]*)\\][\\s]*(?<log>.*)$");
		regexFSM.logPrint(0);
	}

}
