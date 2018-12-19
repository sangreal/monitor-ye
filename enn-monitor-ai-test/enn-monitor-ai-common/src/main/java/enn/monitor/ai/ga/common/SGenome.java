package enn.monitor.ai.ga.common;

import java.util.ArrayList;
import java.util.List;

public class SGenome {
	
	protected List<Integer> genome = new ArrayList<Integer>();
	private double dFitness = 0.0;
	
	public void setGene(int pos, int value) {
		genome.set(pos, value);
	}
	
	public List<Integer> getGenome() {
		return genome;
	}
	
	public void setGenome(List<Integer> genome) {
		this.genome = genome;
	}
	
	public double getdFitness() {
		return dFitness;
	}
	
	public void setdFitness(double dFitness) {
		this.dFitness = dFitness;
	}
	
	public SGenome cloneSGenome() {
		SGenome entry = new SGenome();
		
		entry.setdFitness(this.getdFitness());
		entry.setGenome(new ArrayList<Integer>(this.getGenome()));
		
		return entry;
	}
	
}
