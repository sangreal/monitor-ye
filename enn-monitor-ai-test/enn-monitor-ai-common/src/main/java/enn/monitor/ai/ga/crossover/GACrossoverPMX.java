package enn.monitor.ai.ga.crossover;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enn.monitor.ai.ga.common.SGenome;

public class GACrossoverPMX extends GACrossoverInterface {
	
	private Map<Integer, Integer> parent1Map = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> parent2Map = new HashMap<Integer, Integer>();
	
	public GACrossoverPMX(double crossoverRate) {
		super(crossoverRate);
	}

	@Override
	public void crossover(List<SGenome> genomeList, int nBest) {
		int i, j;
		
		int begin, end, tmp;
		
		int gene1;
		int gene2;
		
		int pos1;
		int pos2;
		
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
			
			son1 = parent1;
			son2 = parent2;
			
			begin = random.nextInt(parent1.getGenome().size() - 2);
			end = random.nextInt(parent1.getGenome().size() - 1);
			while (end == begin) {
				end = random.nextInt(parent1.getGenome().size() - 1);
			}
			
			if (end < begin) {
				tmp = begin;
				begin = tmp;
				end = begin;
			}
			
			initGenome(son1.getGenome(), son2.getGenome());
			for (j = begin; j <= end; ++j) {
				gene1 = son1.getGenome().get(j);
				gene2 = son2.getGenome().get(j);
				
				if (gene1 != gene2) {
					pos1 = j;
					pos2 = parent1Map.get(gene2);
					swap(son1.getGenome(), pos1, pos2);
					parent1Map.put(gene1, pos2);
					parent1Map.put(gene2, pos1);
					
					pos1 = parent2Map.get(gene1);
					pos2 = j;
					swap(son2.getGenome(), pos1, pos2);
					parent2Map.put(gene1, pos2);
					parent2Map.put(gene2, pos1);
				}
			}
			
			genomeList.set(i, son1);
			genomeList.set(i + 1, son2);
		}
	}
	
	private void swap(List<Integer> genome, int pos1, int pos2) {
		int tmp;
		
		tmp = genome.get(pos1);
		genome.set(pos1, genome.get(pos2));
		genome.set(pos2, tmp);
	}
	
	private void initGenome(List<Integer> genome1, List<Integer> genome2) {
		int i;
		
		parent1Map.clear();
		parent2Map.clear();
		
		for (i = 0; i < genome1.size(); ++i) {
			parent1Map.put(genome1.get(i), i);
			parent2Map.put(genome2.get(i), i);
		}
	}

}
