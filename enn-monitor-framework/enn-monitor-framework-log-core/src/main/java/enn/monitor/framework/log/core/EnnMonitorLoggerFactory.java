//package enn.monitor.framework.log.core;
//
//import java.util.Random;
//
//import org.apache.log4j.MDC;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class EnnMonitorLoggerFactory {
//
//	private static final Logger logger = LogManager.getLogger();
//
//	private static ThreadLocal<EnnMonitorLogTraceData> traceDataThreadLocal = new ThreadLocal<EnnMonitorLogTraceData>();
//
//	private static Random random = new Random();
//
//	public static void init() {
//		EnnMonitorLogTraceData traceData = new EnnMonitorLogTraceData();
//		traceData.setTraceId(genTraceId());
//		set(traceData);
//	}
//
//	public static void setTrace(String traceId, String parentPod) {
//		if (traceId == null) {
//			return;
//		}
//
//		EnnMonitorLogTraceData traceData = new EnnMonitorLogTraceData();
//		traceData.setTraceId(traceId);
//		traceData.setParentPod(parentPod);
//		set(traceData);
//	}
//
//	public static void clearTrace() {
//		traceDataThreadLocal.remove();
//		MDC.remove(EnnMonitorLogConstants.TRACE_ID);
//		MDC.remove(EnnMonitorLogConstants.PARENT_POD);
//	}
//
//	public static void set(EnnMonitorLogTraceData traceData) {
//		traceDataThreadLocal.set(traceData);
//		MDC.put(EnnMonitorLogConstants.TRACE_ID, traceData.getTraceId());
//		MDC.put(EnnMonitorLogConstants.PARENT_POD, traceData.getParentPod());
//	}
//
//	public static EnnMonitorLogTraceData get() {
//		return traceDataThreadLocal.get();
//	}
//
//	public static void setEnnMonitorLogDataTraceData(EnnMonitorLogTraceData tradeData) {
//		EnnMonitorLogTraceData tmpTradeData = copyEnnMonitorLogDataTraceData(tradeData);
//		if (tmpTradeData == null) {
//			logger.warn("the traceId is null");
//		} else {
//			set(tmpTradeData);
//		}
//	}
//
//	private static EnnMonitorLogTraceData copyEnnMonitorLogDataTraceData(EnnMonitorLogTraceData tradeData) {
//		if (tradeData == null) {
//			return null;
//		}
//
//		EnnMonitorLogTraceData tmpTradeData = null;
//
//		tmpTradeData = new EnnMonitorLogTraceData();
//		tmpTradeData.setTraceId(tradeData.getTraceId());
//		tmpTradeData.setParentPod(tradeData.getParentPod());
//
//		return tmpTradeData;
//	}
//
//	private static String genTraceId() {
//		return String.format("%016x", random.nextLong());
//	}
//
//	public static void main(String[] args) throws Exception {
//		EnnMonitorLoggerFactory.init();
//		System.out.println(EnnMonitorLoggerFactory.get().getTraceId());
//		logger.info("test");
//	}
//
//}
