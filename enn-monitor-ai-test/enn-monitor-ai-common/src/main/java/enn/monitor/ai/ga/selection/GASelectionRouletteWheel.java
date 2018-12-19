package enn.monitor.ai.ga.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import enn.monitor.ai.ga.common.SGenome;

public class GASelectionRouletteWheel extends GASelectionInterface {
	
	private Random random = new Random();
	
	public GASelectionRouletteWheel(int totalSize, int nBest, int nPer, int nWorst) {
		super(totalSize, nBest, nPer, nWorst);
	}
	
	protected void internalSelection(List<SGenome> parentList, List<SGenome> sonList) {
		int i, j, mid;
		
		double fSlice = 0.0;
		
		double totalFitness = 0.0;
		List<Double> fitness = new ArrayList<Double>();
		
		for (i = 0; i < parentList.size(); ++i) {
			totalFitness += parentList.get(i).getdFitness();
			fitness.add(totalFitness);
		}
		
		mid = 0;
		while (sonList.size() < totalSize) {
			fSlice = random.nextDouble() * totalFitness;
			
			i = 0;
			j = fitness.size() - 1;
			while (i < j) {
				mid = (i + j) / 2;
				
				if (fSlice > fitness.get(mid)) {
					i = mid + 1;
					continue;
				}
				
				if (mid == i) {
					break;
				}
				
				if (fSlice > fitness.get(mid - 1) && fSlice <= fitness.get(mid)) {
					break;
				}
				
				j = mid - 1;
			}
			
			sonList.add(parentList.get(mid).cloneSGenome());
			
			/*for (i = 0; i < fitness.size(); ++i) {
				if (fSlice < fitness.get(i)) {
					break;
				}
			}
			
			sonList.add(parentList.get(i).cloneSGenome());*/
		}
	}

}
