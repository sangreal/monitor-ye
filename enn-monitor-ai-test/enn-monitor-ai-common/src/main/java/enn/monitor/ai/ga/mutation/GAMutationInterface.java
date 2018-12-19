package enn.monitor.ai.ga.mutation;

import java.util.List;
import java.util.Random;

public abstract class GAMutationInterface {
	
	protected double mutationRate = 0.001;
	
	protected Random random = new Random();
	
	public GAMutationInterface(double mutationRate) {
		this.mutationRate = mutationRate;
	}
	
	abstract public void mutate(List<Integer> genome);

}
