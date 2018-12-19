package enn.monitor.ai.ga.selection;

public class GASelectionFactory {
	
	public static GASelectionInterface getGASelectionRouletteWheel(int totalSize, int nBest, int nPer, int nWorst) {
		return new GASelectionRouletteWheel(totalSize, nBest, nPer, nWorst);
	}
	
	public static GASelectionInterface getGASelectionSUS(int totalSize, int nBest, int nPer, int nWorst) {
		return new GASelectionSUS(totalSize, nBest, nPer, nWorst);
	}
	
	public static GASelectionInterface getGASelectionTournament(int totalSize, int batchNum, int nBest, int nPer, int nWorst) {
		return new GASelectionTournament(totalSize, batchNum, nBest, nPer, nWorst);
	}
}
