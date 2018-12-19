package enn.monitor.ai.ga.scaleratio;

import java.util.List;

import enn.monitor.ai.ga.common.SGenome;

public class GAScaleRatioSigma implements GAScaleRatioInterface {

	@Override
	public void scaleRatio(List<SGenome> genomeList, double average) {
		double totalFittest = 0.0;
		double sigma = 0.0;
		
		for (SGenome entry : genomeList) {
			totalFittest += Math.pow(entry.getdFitness() - average, 2.0);
		}
		sigma = Math.sqrt(totalFittest / (genomeList.size() + 1));
		
		for (SGenome entry : genomeList) {
			entry.setdFitness((entry.getdFitness() - average) / (2 * sigma));
		}
	}

}
