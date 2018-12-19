package enn.monitor.ai.ga.common;

import java.util.ArrayList;
import java.util.List;

public class TSPCircleParameter {
	
	private List<Position> posList = new ArrayList<Position>();
	
	public List<Position> getPosList() {
		return posList;
	}

	public void setPosList(List<Position> posList) {
		this.posList = posList;
	}

	public static class Position {
		public int x;
		public int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
