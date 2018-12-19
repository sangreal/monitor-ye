package enn.monitor.ai.ga.nn.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import enn.monitor.ai.ga.nn.common.CircleSGenome;
import enn.monitor.ai.ga.nn.common.GAConfigureParameter;
import enn.monitor.ai.ga.nn.common.NNConfigureParameter;
import enn.monitor.ai.ga.nn.common.NNResult;
import enn.monitor.ai.ga.nn.panel.common.NNEvent;
import enn.monitor.ai.ga.nn.panel.common.NNEventEnum;
import enn.monitor.ai.ui.common.AIUIPosition;
import enn.monitor.framework.ai.ga.common.SGenome;
import enn.monitor.framework.ai.ga.scaleratio.GAScaleRatioFactory;
import enn.monitor.framework.ai.ga.scaleratio.GAScaleRatioInterface;
import enn.monitor.framework.ai.ga.selection.GASelectionFactory;
import enn.monitor.framework.ai.ga.selection.GASelectionInterface;
import enn.monitor.framework.ai.nn.NNFramework;
import enn.monitor.framework.ai.nn.NNObject;
import enn.monitor.framework.ai.nn.NNObject.NNRelation;
import enn.monitor.framework.ai.nn.weights.NNWeightEnum;

public class NNThread extends Thread {
	
	private AtomicInteger status = new AtomicInteger(0);
	
	private GAConfigureParameter gaParameter = null;
	private NNConfigureParameter nnParameter = null;
	
	private BlockingQueue<NNEvent> nnEventQueue = null;
	
	private Random random = new Random();
	
	private GAScaleRatioInterface gaScaleRatio = null;
	private GASelectionInterface gaSelection = null;
	
	public NNThread(GAConfigureParameter gaParameter, NNConfigureParameter nnParameter, BlockingQueue<NNEvent> nnEventQueue) {
		this.gaParameter = gaParameter;
		this.nnParameter = nnParameter;
		
		this.nnEventQueue = nnEventQueue;
		
		switch (gaParameter.getScaleRatioEnum()) {
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
					gaParameter.getBoltzmanndt(), gaParameter.getBoltzmannmin(), gaParameter.getTotalSize() * 2);
			break;
		}
		
		switch (gaParameter.getSelectionEnum()) {
		case RouletteWheel:
			gaSelection = GASelectionFactory.getGASelectionRouletteWheel(
					gaParameter.getTotalSize(), gaParameter.getnBest(), gaParameter.getnPer(), gaParameter.getnWorst());
			break;
		case SUS:
			gaSelection = GASelectionFactory.getGASelectionSUS(
					gaParameter.getTotalSize(), gaParameter.getnBest(), gaParameter.getnPer(), gaParameter.getnWorst());
			break;
		case Tournament:
			gaSelection = GASelectionFactory.getGASelectionTournament(
					gaParameter.getTotalSize(), gaParameter.getBatchNum(), gaParameter.getnBest(), gaParameter.getnPer(), gaParameter.getnWorst());
			break;
		}
	}

	@Override
	public void run() {
		int i;
		
		NNEvent nnEvent = null;
		
		List<SGenome> parentGenomeList = null;
		List<SGenome> oldGenomeList = null;
		
		NNResult nnResult = new NNResult();
		
		try {
			parentGenomeList = generateSGenome(nnParameter);
			while (status.get() == 1) {
				nnResult.incGeneration();
				nnResult.reset();
				
				computeFitness(parentGenomeList, nnResult);
				
				nnEvent = new NNEvent();
				nnEvent.setTspEventEnum(NNEventEnum.Update);
				nnEvent.setData(nnResult.cloneTSPResult());
				nnEventQueue.add(nnEvent);
				
				if (nnResult.getShortestRoute() < 0.0000001) {
					break;
				}
				
				// 进行杂交
				if (gaScaleRatio != null) {
					gaScaleRatio.scaleRatio(parentGenomeList, nnResult.getAverage());
				}
				
				oldGenomeList = new ArrayList<SGenome>();
				gaSelection.selection(parentGenomeList, oldGenomeList);
				crossover(oldGenomeList, gaParameter.getnBest() * gaParameter.getnPer());
				
				for (i = gaParameter.getnBest() * gaParameter.getnPer(); i < oldGenomeList.size(); ++i) {
					mutate(((CircleSGenome)oldGenomeList.get(i)).getNnObject(), gaParameter.getMutationOff());
				}
				
				parentGenomeList = oldGenomeList;
				
				/*try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (status.get() == 1) {
			nnEvent = new NNEvent();
			nnEvent.setTspEventEnum(NNEventEnum.Stop);
			nnEventQueue.add(nnEvent);
			status.set(0);
		}
	}
	
	public void stopThead() {
		System.out.println("stopThread");
		status.set(0);
	}
	
	public void startThread() {
		status.set(1);
		start();
	}

	private void computeFitness(List<SGenome> genomeList, NNResult nnResult) throws Exception {
		int i, j;
		double fitness;
		double maxFitness = Double.MIN_VALUE;
		double totalFitness = 0.0;
		
		SGenome genome = null;
		
		List<Double> outputList = null;
		List<Double> tmpList = new ArrayList<Double>();
		List<AIUIPosition> positionList = new ArrayList<AIUIPosition>();
		
		for (i = 0; i < genomeList.size(); ++i) {
			positionList.clear();
			for (j = 0; j < ((CircleSGenome)genomeList.get(i)).getInputListList().size(); ++j) {
				outputList = NNFramework.compute(((CircleSGenome)genomeList.get(i)).getNnObject(), ((CircleSGenome)genomeList.get(i)).getInputListList().get(j), 
						null, nnParameter.getNnActivationEnum(), NNWeightEnum.Momentum, nnParameter.getA(), nnParameter.getMultiple());
				fitness = computeFitness(((CircleSGenome)genomeList.get(i)).getInputListList().get(j).get(0), outputList.get(0), outputList.get(1));
				positionList.add(new AIUIPosition(outputList.get(0), outputList.get(1)));
				if (i >= tmpList.size()) {
					tmpList.add(fitness);
				} else {
					switch (gaParameter.getGaAdaptabilityEnum()) {
					case LongestRoute:
						if (tmpList.get(i) < fitness) {
							tmpList.set(i, fitness);
						}
						break;
					case Total:
						tmpList.set(i, tmpList.get(i) + fitness);
						break;
					}
				}
			}
			
			
			if (maxFitness < tmpList.get(i)) {
				maxFitness = tmpList.get(i);
			}
			
			nnResult.setLongestRoute(tmpList.get(i));
			nnResult.setShortestRoute(tmpList.get(i), positionList);
		}
		
		for (i = 0; i < genomeList.size(); ++i) {
			genome = genomeList.get(i);
			genome.setdFitness(maxFitness - tmpList.get(i));
			totalFitness = totalFitness + genome.getdFitness();
		}
		
		nnResult.setTotalFee(totalFitness);
		nnResult.setAverage(totalFitness / genomeList.size());
	}
	
	private double computeFitness(double angle, double x, double y) {
		double xDistance = x - Math.cos(angle);
		double yDistance = y - Math.sin(angle);
		return Math.abs(xDistance * xDistance + yDistance * yDistance);
	}
	
	private List<SGenome> generateSGenome(NNConfigureParameter nnParameter) {
		int i, j;
		double angle = 0.0;
		double startPoint = 0.0;
		
		List<Double> angleList = null;
		CircleSGenome genome = null;
		
		List<SGenome> sgenomeList = new ArrayList<SGenome>();
		
		angle = 2 * Math.PI / gaParameter.getPointNum();
		
		// 获取初始基因
		for (i = 0; i < gaParameter.getTotalSize(); ++i) {
			genome = new CircleSGenome();
			
			genome.getNnObject().addNeuronsLayer(nnParameter.getNNLayer().get(0).numberOfNeuron, nnParameter.getwOff());
			for (j = 2; j < nnParameter.getNNLayer().size(); ++j) {
				genome.getNnObject().addNeuronsLayer(nnParameter.getNNLayer().get(j).numberOfNeuron, nnParameter.getwOff());
			}
			genome.getNnObject().addNeuronsLayer(nnParameter.getNNLayer().get(1).numberOfNeuron, nnParameter.getwOff());
			
			startPoint = random.nextDouble() * 2 * Math.PI;
			for (j = 0; j < gaParameter.getPointNum(); ++j) {
				angleList = new ArrayList<Double>();
				angleList.add(startPoint);
				genome.getInputListList().add(angleList);
				startPoint += angle;
			}
			
			sgenomeList.add(genome);
		}
		
		return sgenomeList;
	}
	
	private void crossover(List<SGenome> genomeList, int nBest) {
		int i, j;
		CircleSGenome parent1 = null;
		CircleSGenome parent2 = null;
		
		CircleSGenome son1 = null;
		CircleSGenome son2 = null;
		
		int split1;
		int split2;
		int tmp;
		
		NNRelation nnRelation = null;
		
		for (i = nBest; i < genomeList.size(); i += 2) {
			parent1 = (CircleSGenome) genomeList.get(i);
			parent2 = (CircleSGenome) genomeList.get(i + 1);
			
			if ((random.nextDouble() > gaParameter.getCrossover()) || (parent1 == parent2)) {
				continue;
			}
			
			son1 = (CircleSGenome) parent1.cloneSGenome();
			son2 = (CircleSGenome) parent2.cloneSGenome();
			
			split1 = random.nextInt(son1.getNnObject().getNumLayers());
			split2 = random.nextInt(son2.getNnObject().getNumLayers());
			
			if (split1 > split2) {
				tmp = split1;
				split1 = split2;
				split2 = tmp;
			}
			
			for (j = split1; j <= split2; ++j) {
				nnRelation = son1.getNnObject().getNNRelation(j);
				son1.getNnObject().setNNRelation(j, son2.getNnObject().getNNRelation(j));
				son2.getNnObject().setNNRelation(j, nnRelation);
			}
			
			genomeList.set(i, son1);
			genomeList.set(i + 1, son2);
		}
	}
	
	private void mutate(NNObject nnObject, double off) {
		int i, j, k;
		NNRelation nnRelation = null;
		
		for (i = 0; i < nnObject.getNumLayers(); ++i) {
			nnRelation = nnObject.getNNRelation(i);
			for (j = 0; j < nnRelation.getOutput(); ++j) {
				for (k = 0; k < nnRelation.getInput(); ++k) {
					if (random.nextDouble() > gaParameter.getMutation()) {
						continue;
					}
					
					nnRelation.getWeightListList().get(j).set(
							k, nnRelation.getWeightListList().get(j).get(k) + (random.nextDouble() * 2 - 1) * off);
				}
			}
		}
	}
	
}
