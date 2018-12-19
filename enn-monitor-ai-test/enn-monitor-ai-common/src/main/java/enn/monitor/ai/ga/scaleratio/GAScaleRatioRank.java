package enn.monitor.ai.ga.scaleratio;

import java.util.List;

import enn.monitor.ai.ga.common.GAUtil;
import enn.monitor.ai.ga.common.SGenome;

public class GAScaleRatioRank implements GAScaleRatioInterface {

	@Override
	public void scaleRatio(List<SGenome> genomeList, double average) {
		int i;
		
		GAUtil.sort(genomeList, 1);
		
		for (i = 0; i < genomeList.size(); ++i) {
			genomeList.get(i).setdFitness(i);
		}
	}

}
