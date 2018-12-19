package enn.monitor.framework.ai.ga.scaleratio;

import java.util.List;

import enn.monitor.framework.ai.ga.common.SGenome;

public interface GAScaleRatioInterface {
	public void scaleRatio(List<SGenome> genomeList, double average);
}
