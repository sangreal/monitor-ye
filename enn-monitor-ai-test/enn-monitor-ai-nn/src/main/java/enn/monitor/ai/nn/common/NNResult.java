package enn.monitor.ai.nn.common;

import enn.monitor.ai.nn.panel.common.NNModeEnum;

public class NNResult {
	
	private NNModeEnum nnModeEnum = null;
	
	private int generation = -1;
	private double error = -1.0;
	
	private String recognize = null;
	private double matchTolerance = -1.0;

	public NNModeEnum getNnModeEnum() {
		return nnModeEnum;
	}

	public void setNnModeEnum(NNModeEnum nnModeEnum) {
		this.nnModeEnum = nnModeEnum;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public double getError() {
		return error;
	}

	public void setError(double error) {
		this.error = error;
	}

	public String getRecognize() {
		return recognize;
	}

	public void setRecognize(String recognize) {
		this.recognize = recognize;
	}

	public double getMatchTolerance() {
		return matchTolerance;
	}

	public void setMatchTolerance(double matchTolerance) {
		this.matchTolerance = matchTolerance;
	}
	
}
