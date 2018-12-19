package enn.monitor.log.train.worker.server;

import com.beust.jcommander.JCommander;

import enn.monitor.log.train.master.client.EnnMonitorLogTrainMasterClient;
import enn.monitor.log.train.worker.jobmsg.EnnMonitorLogTrainWorkerJobmsg;
import enn.monitor.log.train.worker.parameter.Parameters;
import enn.monitor.log.train.worker.task.EnnMonitorLogTrainWorkerGetJob;
import enn.monitor.log.train.worker.task.EnnMonitorLogTrainWorkerGetJobCtl;
import enn.monitor.log.train.worker.task.EnnMonitorLogTrainWorkerReportJobStatus;

public class EnnMonitorLogTrainWorkerServer {
	
	public static void main(String[] args) throws Exception {
		EnnMonitorLogTrainWorkerJobmsg jobMsgTask = null;
		
		EnnMonitorLogTrainMasterClient client = null;
		
		Parameters parameters = new Parameters();
	    JCommander jc = new JCommander(parameters, args);
	    if (parameters.help) {
	    	jc.usage();
	    	return;
	    }
		
	    client = new EnnMonitorLogTrainMasterClient(parameters.masterUrl, parameters.masterPort);
	    
	    jobMsgTask = new EnnMonitorLogTrainWorkerJobmsg();
	    jobMsgTask.start();
	    
	    // getJob
	    new Thread(new EnnMonitorLogTrainWorkerGetJob(jobMsgTask, client)).start();
	    
	    // getJobCtl
	    new Thread(new EnnMonitorLogTrainWorkerGetJobCtl(jobMsgTask, client)).start();
	    
	    // reportJobStatus
	    new Thread(new EnnMonitorLogTrainWorkerReportJobStatus(jobMsgTask, client)).start();
		
		while (true) {
			Thread.sleep(1000000000);
		}

	}

}
