package enn.monitor.ai.ga.scaleratio;

import java.util.ArrayList;
import java.util.List;

import enn.monitor.ai.ga.common.SGenome;

public class GAScaleRatioBoltzmann implements GAScaleRatioInterface {
	
	private double boltzmann_dt = 0.05;
	private double boltzmann_min = 1.0;
	
	private double boltzmann = boltzmann_min;
	
	public GAScaleRatioBoltzmann(double boltzmann_dt, double boltzmann_min, double boltzmann) {
		this.boltzmann_dt = boltzmann_dt;
		this.boltzmann_min = boltzmann_min;
		this.boltzmann = boltzmann;
	}

	@Override
	public void scaleRatio(List<SGenome> genomeList, double average) {
		int i;
		List<Double> expBoltz = new ArrayList<Double>();
		double averageBoltz = 0.0;
		
		boltzmann -= boltzmann_dt;
		
		if (boltzmann < boltzmann_min) {
			boltzmann = boltzmann_min;
		}
		
		for (i = 0; i < genomeList.size(); ++i) {
			expBoltz.add(Math.exp(genomeList.get(i).getdFitness() / boltzmann));
			averageBoltz += expBoltz.get(i);
		}
		averageBoltz = averageBoltz / (genomeList.size() + 1);
		
		for (i = 0; i < genomeList.size(); ++i) {
			genomeList.get(i).setdFitness(expBoltz.get(i) / average);
		}
	}
	
}
