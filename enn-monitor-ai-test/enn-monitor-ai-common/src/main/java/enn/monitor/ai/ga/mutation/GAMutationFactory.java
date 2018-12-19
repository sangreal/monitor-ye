package enn.monitor.ai.ga.mutation;

public class GAMutationFactory {
	
	public static GAMutationInterface getGAMutationEM(double mutationRate) {
		return new GAMutationEM(mutationRate);
	}
	
	public static GAMutationInterface getGAMutationIM(double mutationRate) {
		return new GAMutationIM(mutationRate);
	}
	
	public static GAMutationInterface getGAMutationSM(double mutationRate) {
		return new GAMutationSM(mutationRate);
	}
	
	public static GAMutationInterface getGAMutationDM(double mutationRate) {
		return new GAMutationDM(mutationRate);
	}
	
	public static GAMutationInterface getGAMutationRM1(double mutationRate) {
		return new GAMutationRM1(mutationRate);
	}
	
	public static GAMutationInterface getGAMutationRM2(double mutationRate) {
		return new GAMutationRM2(mutationRate);
	}

}
