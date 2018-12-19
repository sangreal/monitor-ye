package enn.monitor.ai.ga.selection;

import java.util.List;
import java.util.Random;

import enn.monitor.ai.ga.common.SGenome;

public class GASelectionSUS extends GASelectionInterface {
	
	private Random random = new Random();
	
	public GASelectionSUS(int totalSize, int nBest, int nPer, int nWorst) {
		super(totalSize, nBest, nPer, nWorst);
	}
	
	protected void internalSelection(List<SGenome> parentList, List<SGenome> sonList) {
		int i;
		int addNum = 0;
		
		double totalFitness = 0.0;
		double pointGap = 0.0;
		double ptr = 0.0;
		double sum = 0.0;
		int index = 0;
		
		addNum = totalSize - sonList.size();
		
		for (i = 0; i < parentList.size(); ++i) {
			totalFitness += parentList.get(i).getdFitness();
		}
		pointGap = totalFitness / addNum;
		
		ptr = random.nextDouble() * pointGap;
		while (sonList.size() < totalSize) {
			for (sum += parentList.get(index).getdFitness(); sum > ptr; ptr += pointGap) {
				sonList.add(parentList.get(index).cloneSGenome());
				if (sonList.size() >= totalSize) {
					return;
				}
			}
			
			++index;
		}
	}

}
