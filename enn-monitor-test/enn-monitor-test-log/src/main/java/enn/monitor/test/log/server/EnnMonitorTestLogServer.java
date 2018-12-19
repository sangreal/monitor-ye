package enn.monitor.test.log.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beust.jcommander.JCommander;

import enn.monitor.test.log.parameters.Parameters;

public class EnnMonitorTestLogServer {
	
	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) throws Exception {
		int i;
		
		Parameters parameters = new Parameters();
	    JCommander jc = new JCommander(parameters, args);
	    if (parameters.help) {
	    	jc.usage();
	    	return;
	    }
	    
		while (true) {
			for (i = 0; i < parameters.batchNum; ++i) {
				logger.info("falsdjflasjdflafalsdjflasjdflafalsdjflasjdflafalsdjflasjdflafalsdjflasjdflafalsdjflasjdfla");
			}
			Thread.sleep(parameters.waitMills);
		}
	}

}
