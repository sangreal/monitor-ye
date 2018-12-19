package enn.monitor.ai.ga.nn.common;

import java.util.ArrayList;
import java.util.List;

import enn.monitor.ai.ui.common.AIUIPosition;

public class NNResult {
	
	private int generation = 0;
	private double result = 0.0;
	private List<AIUIPosition> positionList = null;
	private double shortestRoute = Double.MAX_VALUE;
	private double longestRoute = Double.MIN_VALUE;
	private double average = 0.0;
	private double totalFee = 0.0;
	
	public int getGeneration() {
		return generation;
	}
	
	public void setGeneration(int generation) {
		this.generation = generation;
	}
	
	public void incGeneration() {
		++generation;
	}
	
	public double getResult() {
		return result;
	}
	
	public void setResult(double result) {
		this.result = result;
	}
	
	public double getShortestRoute() {
		return shortestRoute;
	}
	
	public List<AIUIPosition> getPositionList() {
		return positionList;
	}
	
	public void setShortestRoute(double shortestRoute, List<AIUIPosition> positionList) {
		if (this.shortestRoute > shortestRoute) {
			this.shortestRoute = shortestRoute;
			this.positionList = new ArrayList<AIUIPosition>(positionList);
		}
	}
	
	public double getLongestRoute() {
		return longestRoute;
	}
	
	public void setLongestRoute(double longestRoute) {
		if (this.longestRoute < longestRoute) {
			this.longestRoute = longestRoute;
		}
	}
	
	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public double getTotalFee() {
		return totalFee;
	}
	
	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}
	
	public void reset() {
		this.shortestRoute = Double.MAX_VALUE;
		this.longestRoute = Double.MIN_VALUE;
		this.positionList = null;
		this.average = 0.0;
		this.totalFee = 0.0;
	}
	
	public NNResult cloneTSPResult() {
		NNResult out = new NNResult();
		
		out.setGeneration(generation);
		out.setResult(result);
		out.setShortestRoute(shortestRoute, positionList);
		out.setLongestRoute(longestRoute);
		out.setTotalFee(totalFee);
		
		return out;
	}
	
	public void log() {
		System.out.println("Generation:    " + this.getGeneration());
		System.out.println("Result:        " + this.getResult());
		System.out.println("ShortestRoute: " + this.getShortestRoute());
		System.out.println("LongestRoute:  " + this.getLongestRoute());
		System.out.println("TotalFee:      " + this.getTotalFee());
		System.out.println();
	}
	
}
