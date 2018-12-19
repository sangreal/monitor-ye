package enn.monitor.framework.common.unionfindset;

public class EnnMonitorFrameworkCommonUnionFindSet {
	
	private int[] rootIds = null;
	
	public EnnMonitorFrameworkCommonUnionFindSet(int size) {
		int i;
		
		rootIds = new int[size];
		for (i = 0; i < size; ++i) {
			rootIds[i] = -1;
		}
	}
	
	public void mergeSet(int i, int j) throws Exception {
		int rootI, rootJ;
		
		rootI = getRootId(i, 0);
		rootJ = getRootId(j, 0);
		
		if (rootI == rootJ) {
			return;
		}
		
		rootIds[rootI] = rootJ;
	}
	
	public int getRootId(int index, int depth) throws Exception {
		int rootId = -1;
		
		if (depth > 50) {
			System.out.println("index: " + index + " depth: " + depth);
		}
		
		if (rootIds[index] != -1) {
			rootId = getRootId(rootIds[index], depth + 1);
			rootIds[index] = rootId;
		} else {
			rootId = index;
		}
		
		return rootId;
	}
	
	public int size() {
		return rootIds.length;
	}

}
