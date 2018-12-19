package enn.monitor.ai.ga.common;

import java.util.List;

import enn.monitor.ai.ga.common.TSPCircleParameter.Position;
import enn.monitor.ai.ga.crossover.GACrossoverEnum;
import enn.monitor.ai.ga.mutation.GAMutationEnum;
import enn.monitor.ai.ga.scaleratio.GAScaleRatioEnum;
import enn.monitor.ai.ga.selection.GASelectionEnum;

public class TSPParameter {
	
	private int totalSize = 0;
	private int cities = 0;
	private double crossover = 0.0;
	private double mutation = 0.0;
	
	private double boltzmanndt = 0.0;
	private double boltzmannmin = 0.0;
	private GAScaleRatioEnum scaleRatioEnum = null;
	
	private int nBest = 0;
	private int nPer = 0;
	private int nWorst = 0;
	private int batchNum = 0;
	private GASelectionEnum selectionEnum = null;
	
	private GACrossoverEnum crossoverEnum = null;
	
	private GAMutationEnum mutationEnum = null;
	
	private List<Position> positionList = null;

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	
	public int getCities() {
		return cities;
	}

	public void setCities(int cities) {
		this.cities = cities;
	}

	public double getCrossover() {
		return crossover;
	}

	public void setCrossover(double crossover) {
		this.crossover = crossover;
	}

	public double getMutation() {
		return mutation;
	}

	public void setMutation(double mutation) {
		this.mutation = mutation;
	}

	public double getBoltzmanndt() {
		return boltzmanndt;
	}

	public void setBoltzmanndt(double boltzmanndt) {
		this.boltzmanndt = boltzmanndt;
	}

	public double getBoltzmannmin() {
		return boltzmannmin;
	}

	public void setBoltzmannmin(double boltzmannmin) {
		this.boltzmannmin = boltzmannmin;
	}

	public GAScaleRatioEnum getScaleRatioEnum() {
		return scaleRatioEnum;
	}

	public void setScaleRatioEnum(GAScaleRatioEnum scaleRatioEnum) {
		this.scaleRatioEnum = scaleRatioEnum;
	}

	public int getnBest() {
		return nBest;
	}

	public void setnBest(int nBest) {
		this.nBest = nBest;
	}

	public int getnPer() {
		return nPer;
	}

	public void setnPer(int nPer) {
		this.nPer = nPer;
	}

	public int getnWorst() {
		return nWorst;
	}

	public void setnWorst(int nWorst) {
		this.nWorst = nWorst;
	}

	public int getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(int batchNum) {
		this.batchNum = batchNum;
	}

	public GASelectionEnum getSelectionEnum() {
		return selectionEnum;
	}

	public void setSelectionEnum(GASelectionEnum selectionEnum) {
		this.selectionEnum = selectionEnum;
	}

	public GACrossoverEnum getCrossoverEnum() {
		return crossoverEnum;
	}

	public void setCrossoverEnum(GACrossoverEnum crossoverEnum) {
		this.crossoverEnum = crossoverEnum;
	}

	public GAMutationEnum getMutationEnum() {
		return mutationEnum;
	}

	public void setMutationEnum(GAMutationEnum mutationEnum) {
		this.mutationEnum = mutationEnum;
	}
	
	public List<Position> getPositionList() {
		return positionList;
	}
	
	public void setPositionList(List<Position> positionList) {
		this.positionList = positionList;
	}
	
}
