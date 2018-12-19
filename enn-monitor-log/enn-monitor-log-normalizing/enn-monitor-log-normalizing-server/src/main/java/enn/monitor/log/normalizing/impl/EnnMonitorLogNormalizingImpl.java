package enn.monitor.log.normalizing.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import enn.monitor.framework.lang.format.aggregator.LangFormatAggregatorFSM;
import enn.monitor.framework.lang.format.regex.core.LangFormatRegexFSM;
import enn.monitor.framework.log.proto.ESLog;
import enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingRequest;
import enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingResponse;
import enn.monitor.log.normalizing.server.EnnMonitorLogNormalizingServerGrpc;

public class EnnMonitorLogNormalizingImpl extends EnnMonitorLogNormalizingServerGrpc.EnnMonitorLogNormalizingServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private Gson gson = new Gson();
	
	public void normalizing(EnnMonitorLogNormalizingRequest request,
	        io.grpc.stub.StreamObserver<EnnMonitorLogNormalizingResponse> responseObserver) {
		EnnMonitorLogNormalizingResponse.Builder responseBuilder = null;
		ESLog esLog = null;
		
		responseBuilder = EnnMonitorLogNormalizingResponse.newBuilder();
		
		if (request.getRegex() == null || request.getRegex().equals("") == true ||
				request.getLogformat() == null || request.getLogformat().equals("") == true ||
				request.getLog() == null || request.getLog().equals("") == true) {
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError("the input is illegal");
			logger.error("the input is illegal: " + request.toString());
		} else {
			try {
				LangFormatRegexFSM regexFSM = new LangFormatRegexFSM();
				LangFormatAggregatorFSM aggregatorFSM = new LangFormatAggregatorFSM();
				
				regexFSM.parse(request.getRegex());
				aggregatorFSM.parse(request.getLogformat());
				
				esLog = parse(regexFSM, aggregatorFSM, request.getLog());
				
				responseBuilder.setIsSuccess(true);
				responseBuilder.setResult(gson.toJson(esLog));
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				responseBuilder.setIsSuccess(false);
				responseBuilder.setError(e.getMessage());
			}
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
	
	private ESLog parse(LangFormatRegexFSM regexFSM, LangFormatAggregatorFSM aggregatorFSM, String log) throws Exception {
		Map<String, String> resultMap = null;
		
		ESLog esLog = new ESLog();
		
		resultMap = regexFSM.match(log);
		
		if (resultMap == null) {
			esLog.setLog(log);
		} else {
			resultMap = aggregatorFSM.aggregator(resultMap);
			esLog.parse(resultMap);
		}
		
		return esLog;
	}
}
