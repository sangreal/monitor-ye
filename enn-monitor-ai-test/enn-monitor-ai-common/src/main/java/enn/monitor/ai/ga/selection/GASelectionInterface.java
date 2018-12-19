package enn.monitor.ai.ga.selection;

import java.util.List;

import enn.monitor.ai.ga.common.GAUtil;
import enn.monitor.ai.ga.common.SGenome;

public abstract class GASelectionInterface {
	
	protected int totalSize;
	protected int nBest = 0;
	protected int nPer = 0;
	protected int nWorst = 0;
	
	public GASelectionInterface(int totalSize, int nBest, int nPer, int nWorst) {
		this.totalSize = totalSize;
		this.nBest = nBest;
		this.nPer = nPer;
		this.nWorst = nWorst;
	}
	
	public void selection(List<SGenome> parentList, List<SGenome> sonList) {
		int i, j;
		
		GAUtil.sort(parentList, -1);
		
		if (nBest != 0 || nWorst != 0) {
			for (i = 0; i < nWorst && parentList.size() > 0; ++i) {
				parentList.remove(parentList.size() - 1);
			}
			
			if (parentList.size() <= 0) {
				return;
			}
			
			for (i = 0; i < nBest && sonList.size() < totalSize; ++i) {
				for (j = 0; j < nPer && sonList.size() < totalSize; ++j) {
					sonList.add(parentList.get(i));
				}
			}
		}
		
		internalSelection(parentList, sonList);
	}
	
	protected abstract void internalSelection(List<SGenome> parentList, List<SGenome> sonList);
}
