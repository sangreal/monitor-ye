package enn.monitor.ai.ga.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import enn.monitor.ai.ga.common.TSPCircleParameter.Position;
import enn.monitor.ai.ga.common.TSPParameter;
import enn.monitor.ai.ga.common.TSPResult;
import enn.monitor.ai.ga.panel.common.TSPEvent;
import enn.monitor.ai.ga.panel.common.TSPEventEnum;
import enn.monitor.framework.ai.ga.common.SGenome;
import enn.monitor.framework.ai.ga.crossover.GACrossoverFactory;
import enn.monitor.framework.ai.ga.crossover.GACrossoverInterface;
import enn.monitor.framework.ai.ga.mutation.GAMutationFactory;
import enn.monitor.framework.ai.ga.mutation.GAMutationInterface;
import enn.monitor.framework.ai.ga.scaleratio.GAScaleRatioFactory;
import enn.monitor.framework.ai.ga.scaleratio.GAScaleRatioInterface;
import enn.monitor.framework.ai.ga.selection.GASelectionFactory;
import enn.monitor.framework.ai.ga.selection.GASelectionInterface;

public class TSPGAThread extends Thread {
	
	private AtomicInteger status = new AtomicInteger(0);
	
	private TSPParameter tspParameter = null;
	
	private int cities = -1;
	private double citiesDistance[][] = null;
	
	private BlockingQueue<TSPEvent> tspEventQueue = null;
	
	private Random random = new Random();
	
	private GAScaleRatioInterface gaScaleRatio = null;
	private GASelectionInterface gaSelection = null;
	private GACrossoverInterface gaCrossover = null;
	private GAMutationInterface gaMutation = null;
	
	public TSPGAThread(TSPParameter tspParameter, BlockingQueue<TSPEvent> tspEventQueue) {
		int i, j;
		
		this.tspParameter = tspParameter;
		
		if (tspParameter.getCities() != cities) {
			citiesDistance = new double[tspParameter.getCities()][tspParameter.getCities()];
		}
		
		for (i = 0; i < tspParameter.getCities(); ++i) {
			for (j = 0; j < tspParameter.getCities(); ++j) {
				citiesDistance[i][j] = -1.0;
			}
		}
		
		this.tspEventQueue = tspEventQueue;
		
		switch (tspParameter.getScaleRatioEnum()) {
		case None:
			gaScaleRatio = null;
			break;
		case Rank:
			gaScaleRatio = GAScaleRatioFactory.getGAScaleRatioRank();
			break;
		case Sigma:
			gaScaleRatio = GAScaleRatioFactory.getGAScaleRatioSigma();
			break;
		case Boltzmann:
			gaScaleRatio = GAScaleRatioFactory.getGAScaleRatioBoltzmann(
					tspParameter.getBoltzmanndt(), tspParameter.getBoltzmannmin(), tspParameter.getCities());
			break;
		}
		
		switch (tspParameter.getSelectionEnum()) {
		case RouletteWheel:
			gaSelection = GASelectionFactory.getGASelectionRouletteWheel(
					tspParameter.getTotalSize(), tspParameter.getnBest(), tspParameter.getnPer(), tspParameter.getnWorst());
			break;
		case SUS:
			gaSelection = GASelectionFactory.getGASelectionSUS(
					tspParameter.getTotalSize(), tspParameter.getnBest(), tspParameter.getnPer(), tspParameter.getnWorst());
			break;
		case Tournament:
			gaSelection = GASelectionFactory.getGASelectionTournament(
					tspParameter.getTotalSize(), tspParameter.getBatchNum(), tspParameter.getnBest(), tspParameter.getnPer(), tspParameter.getnWorst());
			break;
		}
		
		switch (tspParameter.getCrossoverEnum()) {
		case PMX:
			gaCrossover = GACrossoverFactory.getGACrossoverPMX(tspParameter.getCrossover());
			break;
		case OBX:
			gaCrossover = GACrossoverFactory.getGACrossoverOBX(tspParameter.getCrossover());
			break;
		case PBX:
			gaCrossover = GACrossoverFactory.getGACrossoverPBX(tspParameter.getCrossover());
			break;
		}
		
		switch (tspParameter.getMutationEnum()) {
		case EM:
			gaMutation = GAMutationFactory.getGAMutationEM(tspParameter.getMutation());
			break;
		case SM:
			gaMutation = GAMutationFactory.getGAMutationSM(tspParameter.getMutation());
			break;
		case DM:
			gaMutation = GAMutationFactory.getGAMutationDM(tspParameter.getMutation());
			break;
		case IM:
			gaMutation = GAMutationFactory.getGAMutationIM(tspParameter.getMutation());
			break;
		case RM1:
			gaMutation = GAMutationFactory.getGAMutationRM1(tspParameter.getMutation());
		case RM2:
			gaMutation = GAMutationFactory.getGAMutationRM2(tspParameter.getMutation());
		}
	}

	@Override
	public void run() {
		int i;
		TSPEvent tspEvent = null;
		
		List<SGenome> parentGenomeList = null;
		List<SGenome> oldGenomeList = null;
		
		TSPResult tspResult = new TSPResult();
		
		parentGenomeList = generateSGenome(tspParameter.getTotalSize(), tspParameter.getCities());
		computeBestRoute(tspResult);
		while (status.get() == 1) {
			tspResult.incGeneration();
			tspResult.reset();
			computeFitness(parentGenomeList, tspResult);
			
			//tspResult.log();
			tspEvent = new TSPEvent();
			tspEvent.setTspEventEnum(TSPEventEnum.Update);
			tspEvent.setData(tspResult.cloneTSPResult());
			tspEventQueue.add(tspEvent);
			
			if (tspResult.getShortestRoute() - tspResult.getResult() < 0.0000001 * tspParameter.getCities()) {
				break;
			}
			
			// 进行杂交
			if (gaScaleRatio != null) {
				gaScaleRatio.scaleRatio(parentGenomeList, tspResult.getAverage());
			}
			
			oldGenomeList = new ArrayList<SGenome>();
			gaSelection.selection(parentGenomeList, oldGenomeList);
			gaCrossover.crossover(oldGenomeList, tspParameter.getnBest() * tspParameter.getnPer());
			
			for (i = tspParameter.getnBest() * tspParameter.getnPer(); i < oldGenomeList.size(); ++i) {
				gaMutation.mutate(oldGenomeList.get(i).getGenome());
			}
			
			parentGenomeList = oldGenomeList;
			
			/*try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		
		if (status.get() == 1) {
			tspEvent = new TSPEvent();
			tspEvent.setTspEventEnum(TSPEventEnum.Stop);
			tspEventQueue.add(tspEvent);
			status.set(0);
		}
		
		System.out.println("exit work thread");
	}
	
	public void stopThead() {
		System.out.println("stopThread");
		status.set(0);
	}
	
	public void startThread() {
		status.set(1);
		start();
	}
	
	private List<SGenome> generateSGenome(int num, int cities) {
		int i, j;
		int city;
		
		SGenome genome = null;
		List<SGenome> genomeList = new ArrayList<SGenome>();
		
		Set<Integer> citiesSet = new HashSet<Integer>();
		
		for (i = 0; i < num; ++i) {
			genome = new SGenome();
			
			citiesSet.clear();
			for (j = 0; j < cities; ++j) {
				city = random.nextInt(cities);
				while (citiesSet.contains(city) == true) {
					city = random.nextInt(cities);
				}
				citiesSet.add(city);
				genome.getGenome().add(city);
			}
			
			genomeList.add(genome);
		}
		
		return genomeList;
	}
	
	private void computeFitness(List<SGenome> genomeList, TSPResult tspResult) {
		int i;
		Double maxDistance = Double.MIN_VALUE;
		double routeDistance = 0.0;
		double totalFitness = 0.0;
		SGenome genome = null;
		
		List<Double> routeList = new ArrayList<Double>();
		
		for (i = 0; i < genomeList.size(); ++i) {
			genome = genomeList.get(i);
			
			routeDistance = computeRoute(genome.getGenome());
			routeList.add(routeDistance);
			if (maxDistance < routeDistance) {
				maxDistance = routeDistance;
			}
			
			tspResult.setLongestRoute(routeDistance);
			tspResult.setShortestRoute(routeDistance, genome.getGenome());
		}
		
		for (i = 0; i < routeList.size(); ++i) {
			genome = genomeList.get(i);
			genome.setdFitness(maxDistance - routeList.get(i));
			totalFitness += genome.getdFitness();
		}
		tspResult.setTotalFee(totalFitness);
		
		tspResult.setAverage(tspResult.getTotalFee() / genomeList.size());
	}
	
	private double computeRoute(List<Integer> routeList) {
		int i;
		int begin, end;
		double totalFitness = 0.0;
		double distance = 0;
		
		for (i = 0; i < routeList.size(); ++i) {
			begin = routeList.get(i);
			if (i == routeList.size() - 1) {
				end = routeList.get(0);
			} else {
				end = routeList.get(i + 1);
			}
			
			distance = computeDistance(begin, end);
			totalFitness += distance;
		}
		
		return totalFitness;
	}
	
	private void computeBestRoute(TSPResult tspResult) {
		int i;
		
		double totalFitness = 0.0;
		
		for (i = 0; i < tspParameter.getPositionList().size(); ++i) {
			if (i == tspParameter.getPositionList().size() - 1) {
				totalFitness = totalFitness + computeDistance(i, 0) + 0.000001;
			} else {
				totalFitness = totalFitness + computeDistance(i, i + 1) + 0.000001;
			}
		}
		
		tspResult.setResult(totalFitness);
	}
	
	private double computeDistance(int begin, int end) {
		double distance = 0.0;
		
		Position pa, pb;
		
		if (citiesDistance[begin][end] < 0) {
			pa = tspParameter.getPositionList().get(begin);
			pb = tspParameter.getPositionList().get(end);
			distance = Math.sqrt((pa.x - pb.x) * (pa.x - pb.x) + (pa.y - pb.y) * (pa.y - pb.y));
			citiesDistance[begin][end] = citiesDistance[end][begin] = distance;
		}
		
		return citiesDistance[begin][end];
	}

}
