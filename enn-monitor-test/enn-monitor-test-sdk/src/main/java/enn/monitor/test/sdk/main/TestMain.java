package enn.monitor.test.sdk.main;

import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import enn.monitor.security.gateway.metrics.EnnMonitorGatewayParameter;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;

public class TestMain {
	
	public static void main(String[] args) throws Exception {
		
		EnnMonitorMetricsGatewayGrpc metricsGrpc = null;
		
		metricsGrpc = new EnnMonitorMetricsGatewayGrpc();
	    metricsGrpc.startMetricsCollector(true, new EnnMonitorGatewayParameter("10.19.248.200", 30111), 1,
        		"micklongen-gateway-server");
	    
	    for (int i = 0; i < 16; ++i) {
//	    	new Thread(new NullOp()).start();
	    	
//		    new Thread(new IntAdd()).start();
//		    new Thread(new IntSub()).start();
//		    new Thread(new IntMul()).start();
//		    new Thread(new IntDiv()).start();
//		    new Thread(new IntMod()).start();
		    
//		    new Thread(new FloatAdd()).start();
//		    new Thread(new FloatSub()).start();
//		    new Thread(new FloatMul()).start();
//		    new Thread(new FloatDiv()).start();
		    
		    new Thread(new FloatExp()).start();
	    }
	    
	    Thread.sleep(100000000);
	}
	
	public static class NullOp implements Runnable {
		private CounterMetric noOpMetrics = MetricManager.getCounterMetric(TestMain.class, "no_op");

		@Override
		public void run() {
		    while (true) {
		    	noOpMetrics.markEvent();
		    }
		}
	}
	
	public static class IntAdd implements Runnable {
		
		private static CounterMetric intAddMetrics = MetricManager.getCounterMetric(TestMain.class, "int_add_compute");
		
		private int resultInt = 0;
		private long count = 0;

		@Override
		public void run() {
			int a1 = 1;
		    int a2 = 2;
		    
		    int sum = 0;
		    
		    while (count < 100000000) {
		    	a1 += 3;
		    	a2 += 101;
		    	sum = a1 + a2;
		    	resultInt += sum;
		    	intAddMetrics.markEvent();
		    }
		    
		    System.out.println(resultInt);
		}
		
	}
	
	public static class IntSub implements Runnable {
		
		private static CounterMetric intSubMetrics = MetricManager.getCounterMetric(TestMain.class, "int_sub_compute");
		
		private int resultInt = 0;
		private long count = 0;

		@Override
		public void run() {
			int a1 = 1;
		    int a2 = 2;
		    
		    int sum = 0;
		    
		    while (count < 100000000) {
		    	a1 += 3;
		    	a2 += 101;
		    	sum = a1 - a2;
		    	resultInt += sum;
		    	intSubMetrics.markEvent();
		    }
		    
		    System.out.println(resultInt);
		}
		
	}

	public static class IntMul implements Runnable {
		
		private static CounterMetric intMulMetrics = MetricManager.getCounterMetric(TestMain.class, "int_mul_compute");
		
		private int resultInt = 0;
		private long count = 0;
	
		@Override
		public void run() {
			int a1 = 1;
		    int a2 = 2;
		    
		    int sum = a1 * a2;
		    
		    while (count < 100000000) {
		    	a1 += 3;
		    	a2 += 101;
		    	sum = a1 * a2;
		    	resultInt += sum;
		    	intMulMetrics.markEvent();
		    }
		    
		    System.out.println(resultInt);
		}
		
	}
	
	public static class IntDiv implements Runnable {
		
		private static CounterMetric intDivMetrics = MetricManager.getCounterMetric(TestMain.class, "int_div_compute");
		
		private int resultInt = 0;
		private long count = 0;
	
		@Override
		public void run() {
			int a1 = 1;
		    int a2 = 2;
		    
		    int sum = 0;
		    
		    while (count < 100000000) {
		    	a1 += 3;
		    	a2 += 101;
		    	sum = a2 / a1;
		    	resultInt += sum;
		    	intDivMetrics.markEvent();
		    }
		    
		    System.out.println(resultInt);
		}
		
	}
	
	public static class IntMod implements Runnable {
		
		private int resultInt = 0;
		private long count = 0;
		
		private static CounterMetric intModMetrics = MetricManager.getCounterMetric(TestMain.class, "int_mod_compute");
	
		@Override
		public void run() {
			int a1 = 100;
		    int a2 = 23;
		    
		    int sum = 0;
		    
		    while (count < 100000000) {
		    	a1 += 3;
		    	a2 += 101;
		    	sum = a1 % a2;
		    	resultInt += sum;
		    	intModMetrics.markEvent();
		    }
		    
		    System.out.println(resultInt);
		}
		
	}
	
	public static class FloatAdd implements Runnable {
		
		private static CounterMetric floatAddMetrics = MetricManager.getCounterMetric(TestMain.class, "float_add_compute");
		
		private double resultFloat = 0.0;
		private long count = 0;

		@Override
		public void run() {
			double a1 = 1.11;
			double a2 = 2.22;
		    
			double sum = 0.0;
		    
		    while (count < 100000000) {
		    	a1 += 10.23;
		    	a2 += 1.23;
		    	sum = a1 + a2;
		    	resultFloat += sum;
		    	floatAddMetrics.markEvent();
		    }
		    
		    System.out.println(resultFloat);
		}
		
	}
	
	public static class FloatSub implements Runnable {
		
		private static CounterMetric floatSubMetrics = MetricManager.getCounterMetric(TestMain.class, "float_sub_compute");

		private double resultFloat = 0.0;
		private long count = 0;
		
		@Override
		public void run() {
			double a1 = 1.11;
			double a2 = 2.22;
		    
			double sum = 0.0;
		    
		    while (count < 100000000) {
		    	a1 += 10.23;
		    	a2 += 1.23;
		    	sum = a1 - a2;
		    	resultFloat += sum;
		    	floatSubMetrics.markEvent();
		    }
		    
		    System.out.println(resultFloat);
		}
		
	}

	public static class FloatMul implements Runnable {
		
		private static CounterMetric floatMulMetrics = MetricManager.getCounterMetric(TestMain.class, "float_mul_compute");
	
		private double resultFloat = 0.0;
		private long count = 0;
		
		@Override
		public void run() {
			double a1 = 1.11;
			double a2 = 2.22;
		    
			double sum = 0.0;
		    
		    while (count < 100000000) {
		    	a1 = a1 + 23.01;
		    	a2 = a2 + 18.01;
		    	sum = a1 * a2;
		    	resultFloat += sum;
		    	floatMulMetrics.markEvent();
		    }
		    
		    System.out.println(resultFloat);
		}
		
	}
	
	public static class FloatDiv implements Runnable {
		
		private static CounterMetric intDivMetrics = MetricManager.getCounterMetric(TestMain.class, "float_div_compute");
	
		private double resultFloat = 0.0;
		private long count = 0;
		
		@Override
		public void run() {
			double a1 = 1.11;
			double a2 = 2.22;
		    
			double sum = 0.0;
		    
		    while (count < 100000000) {
		    	a1 += 10.23;
		    	a2 += 1.23;
		    	sum = a1 / a2;
		    	resultFloat += sum;
		    	intDivMetrics.markEvent();
		    }
		    
		    System.out.println(resultFloat);
		}
		
	}
	
	public static class FloatExp implements Runnable {
		
		private static CounterMetric floatExpMetrics = MetricManager.getCounterMetric(TestMain.class, "float_exp_compute");
	
		private double resultFloat = 0.0;
		private long count = 0;
		
		@Override
		public void run() {
			double a2 = 2.22;
		    
			double sum = 0.0;
		    
		    while (count < 100000000) {
		    	a2 += 0.013;
		    	sum = Math.exp(-a2);
		    	resultFloat += sum;
		    	floatExpMetrics.markEvent();
		    }
		    
		    System.out.println(resultFloat);
		}
		
	}

}
