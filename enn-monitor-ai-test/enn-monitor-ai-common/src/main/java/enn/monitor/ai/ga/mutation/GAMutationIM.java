package enn.monitor.ai.ga.mutation;

import java.util.List;

public class GAMutationIM extends GAMutationInterface {

	public GAMutationIM(double mutationRate) {
		super(mutationRate);
	}

	@Override
	public void mutate(List<Integer> genome) {
		int pos1, pos2;
		int value;
		
		if (random.nextDouble() > mutationRate) {
			return;
		}
		
		pos1 = random.nextInt(genome.size() - 1);
		pos2 = pos1 + 1 + random.nextInt(genome.size() - pos1 - 1);
		
		value = genome.get(pos2);
		while (pos2 > pos1) {
			genome.set(pos2, genome.get(pos2 - 1));
			--pos2;
		}
		genome.set(pos2, value);
	}

}
