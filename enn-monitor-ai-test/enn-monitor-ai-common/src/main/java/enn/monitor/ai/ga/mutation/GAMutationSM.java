package enn.monitor.ai.ga.mutation;

import java.util.List;

public class GAMutationSM extends GAMutationInterface {

	public GAMutationSM(double mutationRate) {
		super(mutationRate);
	}

	@Override
	public void mutate(List<Integer> genome) {
		int pos1, pos2;
		int spos1, spos2;
		int count;
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
		
		count = random.nextInt(pos2 - pos1 + 1);
		while (count > 0) {
			spos1 = pos1 + random.nextInt(pos2 - pos1 + 1);
			spos2 = pos1 + random.nextInt(pos2 - pos1 + 1);
			if (spos1 != spos2) {
				value = genome.get(spos1);
				genome.set(spos1, genome.get(spos2));
				genome.set(spos2, value);
			}
			--count;
		}
	}

}
