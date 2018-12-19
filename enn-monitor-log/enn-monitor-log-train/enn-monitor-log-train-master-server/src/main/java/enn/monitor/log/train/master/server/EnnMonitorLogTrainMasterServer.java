package enn.monitor.log.train.master.server;

import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beust.jcommander.JCommander;

import enn.monitor.log.train.master.impl.EnnMonitorLogTrainMasterImpl;
import enn.monitor.log.train.master.parameter.Parameters;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

public class EnnMonitorLogTrainMasterServer {
	
	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) throws Exception {
		
		Parameters parameters = new Parameters();
	    JCommander jc = new JCommander(parameters, args);
	    if (parameters.help) {
	    	jc.usage();
	    	return;
	    }

	    // 启动服务
		Server server = ServerBuilder.forPort(parameters.listenPort)
	            .addService(ServerInterceptors.intercept(new EnnMonitorLogTrainMasterImpl()))
	            .executor(Executors.newFixedThreadPool(16))
	            .build();
	    logger.info("server start");
	    server.start();
	    server.awaitTermination();
	}

}
