package enn.monitor.framework.ai.nn.util;

import java.util.Random;

public class NNParameterAdjustUtil {
	
	private static Random rd = new Random();
	
	public static double adjustParameter(double value, double min, double max) {
		double v = max - min;
		
		if (v <= 0) {
			return value;
		}
		
		double u = min / v;
		return value * (rd.nextDouble() + u) * v;
	}

}
