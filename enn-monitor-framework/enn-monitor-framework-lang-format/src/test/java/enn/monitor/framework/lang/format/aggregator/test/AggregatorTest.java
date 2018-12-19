package enn.monitor.framework.lang.format.aggregator.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.framework.lang.format.aggregator.LangFormatAggregatorFSM;

public class AggregatorTest {
	
	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) throws Exception {
		LangFormatAggregatorFSM fsm1 = new LangFormatAggregatorFSM();
		fsm1.parse("%dateTime%,%logLevel%,%threadName%,%position%,%customTopic%,%log%");
		fsm1.logPrint(0);
		
		logger.info("-------------------------------------------------------------------------");
		
		LangFormatAggregatorFSM fsm2 = new LangFormatAggregatorFSM();
		fsm2.parse("%logLevel[I:INFO/W:WARN/E:ERROR/F:FATAL]%,%position%,%log%,<dateTime>$year$-%month%-%day% %time%");
		fsm2.logPrint(0);
	}

}
