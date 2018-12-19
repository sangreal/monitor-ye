package enn.monitor.ai.ga.scaleratio;

import java.util.List;

import enn.monitor.ai.ga.common.SGenome;

public interface GAScaleRatioInterface {
	public void scaleRatio(List<SGenome> genomeList, double average);
}
