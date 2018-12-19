package enn.monitor.ai.ga.crossover;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enn.monitor.ai.ga.common.SGenome;

public class GACrossoverOBX extends GACrossoverInterface {
	
	public GACrossoverOBX(double crossoverRate) {
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
			
			OBX(parent1, parent2, son1);
			OBX(parent2, parent1, son2);
		}
	}
	
	private void OBX(SGenome parent1, SGenome parent2, SGenome son) {
		int i;
		int size;
		int pos;
		int value;
		
		Map<Integer, Integer> geneMap = new HashMap<Integer, Integer>();
		
		size = random.nextInt(parent1.getGenome().size() - 2);
		for (i = 0; i < size; ++i) {
			pos = random.nextInt(parent1.getGenome().size());
			while (geneMap.containsKey(parent1.getGenome().get(pos))) {
				pos = random.nextInt(parent1.getGenome().size());
			}
			
			geneMap.put(parent1.getGenome().get(pos), pos);	
		}
		
		for (i = 0; i < parent2.getGenome().size(); ++i) {
			if (geneMap.containsKey(parent2.getGenome().get(i)) == true) {
				value = parent1.getGenome().get(geneMap.get(parent2.getGenome().get(i)));
			} else {
				value = parent2.getGenome().get(i);
			}
			
			son.getGenome().add(value);
		}
	}

}
