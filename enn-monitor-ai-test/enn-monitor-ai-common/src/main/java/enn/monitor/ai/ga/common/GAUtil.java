package enn.monitor.ai.ga.common;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GAUtil {

	public static void sort(List<SGenome> genomeList, int sort) {
		Collections.sort(genomeList, new Comparator<SGenome>() {
            public int compare(SGenome o1, SGenome o2) {
                if (o1.getdFitness() < o2.getdFitness()) {
                	return -1 * sort;
                }
                
                if (o1.getdFitness() > o2.getdFitness()) {
                	return 1 * sort;
                }
                
                return 0;
            }
        });
	}
	
}
