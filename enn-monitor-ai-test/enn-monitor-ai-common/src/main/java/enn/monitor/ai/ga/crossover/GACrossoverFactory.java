package enn.monitor.ai.ga.crossover;

public class GACrossoverFactory {
	
	public static GACrossoverInterface getGACrossoverPMX(double crossoverRate) {
		return new GACrossoverPMX(crossoverRate);
	}
	
	public static GACrossoverInterface getGACrossoverPBX(double crossoverRate) {
		return new GACrossoverPBX(crossoverRate);
	}
	
	public static GACrossoverInterface getGACrossoverOBX(double crossoverRate) {
		return new GACrossoverOBX(crossoverRate);
	}

}
