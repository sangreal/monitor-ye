package enn.monitor.ai.ga.selection;

import java.util.List;
import java.util.Random;

import enn.monitor.ai.ga.common.SGenome;

public class GASelectionTournament extends GASelectionInterface {
	
	private int batchNum = 0;
	private Random random = new Random();
	
	public GASelectionTournament(int totalSize, int batchNum, int nBest, int nPer, int nWorst) {
		super(totalSize, nBest, nPer, nWorst);
		this.batchNum = batchNum;
	}
	
	protected void internalSelection(List<SGenome> parentList, List<SGenome> sonList) {
		int i;
		double bestFitness = 0.0;
		int index = 0;
		int selected = 0;
		
		while (sonList.size() < totalSize) {
			bestFitness = 0.0;
			
			for (i = 0; i < batchNum; ++i) {
				index = random.nextInt(parentList.size());
				if (parentList.get(index).getdFitness() > bestFitness) {
					bestFitness = parentList.get(index).getdFitness();
					selected = index;
				}
			}
			
			sonList.add(parentList.get(selected).cloneSGenome());
		}
	}
	
	public void setBatchNum(int batchNum) {
		this.batchNum = batchNum;
	}
	
}
