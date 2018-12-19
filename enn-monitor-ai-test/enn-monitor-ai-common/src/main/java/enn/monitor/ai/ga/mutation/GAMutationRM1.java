package enn.monitor.ai.ga.mutation;

import java.util.List;

public class GAMutationRM1 extends GAMutationInterface {

	public GAMutationRM1(double mutationRate) {
		super(mutationRate);
	}

	@Override
	public void mutate(List<Integer> genome) {
		int pos1, pos2;
		int value;
		
		if (random.nextDouble() > mutationRate) {
			return;
		}
		
		pos1 = random.nextInt(genome.size());
		pos2 = random.nextInt(genome.size());
		while (pos1 == pos2) {
			pos2 = random.nextInt(genome.size());
		}
		
		if (pos1 > pos2) {
			value = pos1;
			pos1 = pos2;
			pos2 = value;
		}
		
		while (pos1 < pos2) {
			value = genome.get(pos1);
			genome.set(pos1, genome.get(pos2));
			genome.set(pos2, value);
			
			++pos1;
			--pos2;
		}
	}
	
}
