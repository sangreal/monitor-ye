package enn.monitor.ai.ga.crossover;

import java.util.List;
import java.util.Random;

import enn.monitor.ai.ga.common.SGenome;

public abstract class GACrossoverInterface {
	
	protected double crossoverRate = 0.7;
	
	protected Random random = new Random();
	
	public GACrossoverInterface(double crossoverRate) {
		this.crossoverRate = crossoverRate;
	}
	
	public abstract void crossover(List<SGenome> genomeList, int nBest);
	
}
