package enn.monitor.ai.ga.crossover;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import enn.monitor.ai.ga.common.SGenome;

public class GACrossoverPBX extends GACrossoverInterface {
	
	public GACrossoverPBX(double crossoverRate) {
		super(crossoverRate);
	}

	@Override
	public void crossover(List<SGenome> genomeList, int nBest) {
		int i;
		
		SGenome parent1 = null;
		SGenome parent2 = null;
		
		SGenome son1 = null;
		SGenome son2 = null;
		
		for (i = nBest; i < genomeList.size(); i += 2) {
			parent1 = genomeList.get(i);
			parent2 = genomeList.get(i + 1);
			
			if ((random.nextDouble() > crossoverRate) || (parent1 == parent2)) {
				continue;
			}
			
			son1 = new SGenome();
			son2 = new SGenome();
			
			PBX(parent1, parent2, son1);
			PBX(parent2, parent1, son2);
		}
	}
	
	private void PBX(SGenome parent1, SGenome parent2, SGenome son) {
		int i;
		int size;
		int pos;
		
		Set<Integer> geneSet = new HashSet<Integer>();
		
		for (i = 0; i < parent1.getGenome().size(); ++i) {
			son.getGenome().add(-1);
		}
		
		size = random.nextInt(parent1.getGenome().size() - 2);
		for (i = 0; i < size; ++i) {
			pos = random.nextInt(parent1.getGenome().size());
			while (geneSet.contains(parent1.getGenome().get(pos))) {
				pos = random.nextInt(parent1.getGenome().size());
			}
			
			son.getGenome().set(pos, parent1.getGenome().get(pos));
			
			geneSet.add(parent1.getGenome().get(pos));	
		}
		
		pos = 0;
		for (i = 0; i < parent2.getGenome().size(); ++i) {
			if (geneSet.contains(parent2.getGenome().get(i)) == true) {
				continue;
			}
			
			while (son.getGenome().get(pos) > 0) {
				++pos;
			}
			
			son.getGenome().set(pos, parent2.getGenome().get(i));
		}
	}

}
