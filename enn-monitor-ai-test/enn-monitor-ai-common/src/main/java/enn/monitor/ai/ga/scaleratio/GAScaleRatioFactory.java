package enn.monitor.ai.ga.scaleratio;

public class GAScaleRatioFactory {
	
	public static GAScaleRatioInterface getGAScaleRatioRank() {
		return new GAScaleRatioRank();
	}
	
	public static GAScaleRatioInterface getGAScaleRatioSigma() {
		return new GAScaleRatioSigma();
	}
	
	public static GAScaleRatioInterface getGAScaleRatioBoltzmann(double boltz_dt, double boltz_min, double cities) {
		return new GAScaleRatioBoltzmann(boltz_dt, boltz_min, cities * 2);
	}

}
