package enn.monitor.ai.ga.mutation;

import java.util.List;

public class GAMutationRM2 extends GAMutationInterface {

	public GAMutationRM2(double mutationRate) {
		super(mutationRate);
	}

	@Override
	public void mutate(List<Integer> genome) {
		int pos1, pos2;
		int value;
		int dir;
		
		if (random.nextDouble() > mutationRate) {
			return;
		}
		
		pos1 = random.nextInt(genome.size());
		pos2 = random.nextInt(genome.size());
		while (pos1 == pos2) {
			pos2 = random.nextInt(genome.size());
		}
		
		if (pos1 < pos2) {
			dir = 1;
		} else {
			dir = -1;
		}
		
		while (pos1 * dir < pos2 * dir) {
			value = genome.get(pos1);
			genome.set(pos1, genome.get(pos2));
			genome.set(pos2, value);
			
			++pos1;
			--pos2;
			
			if (pos1 >= genome.size()) {
				pos1 = 0;
				dir *= -1;
			}
			
			if (pos2 < 0) {
				pos2 = genome.size() - 1;
				dir *= -1;
			}
		}
	}
	
}
