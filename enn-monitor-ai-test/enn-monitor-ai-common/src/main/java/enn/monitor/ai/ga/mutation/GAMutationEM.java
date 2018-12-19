package enn.monitor.ai.ga.mutation;

import java.util.List;

public class GAMutationEM extends GAMutationInterface {

	public GAMutationEM(double mutationRate) {
		super(mutationRate);
	}

	@Override
	public void mutate(List<Integer> genome) {
		int pos1, pos2;
		int tmp;
		
		if (random.nextDouble() > mutationRate) {
			return;
		}
		
		pos1 = random.nextInt(genome.size());
		pos2 = random.nextInt(genome.size());
		while (pos1 == pos2) {
			pos2 = random.nextInt(genome.size());
		}
		
		tmp = genome.get(pos1);
		genome.set(pos1, genome.get(pos2));
		genome.set(pos2, tmp);
	}

}
