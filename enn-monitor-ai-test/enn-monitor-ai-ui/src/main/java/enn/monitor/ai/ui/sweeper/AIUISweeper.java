package enn.monitor.ai.ui.sweeper;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import enn.monitor.ai.ui.common.AIUIPosition;

public class AIUISweeper {
	private double xSize = 0.0;
	private double ySize = 0.0;
	
	private final int numberOfPoint = 16;
	private final AIUIPosition[] sweeper = {
			new AIUIPosition(-1, -1),
			new AIUIPosition(-1, 1),
			new AIUIPosition(-0.5, 1),
			new AIUIPosition(-0.5, -1),
			
			new AIUIPosition(0.5, -1),
			new AIUIPosition(1, -1),
			new AIUIPosition(1, 1),
			new AIUIPosition(0.5, 1),
			
			new AIUIPosition(-0.5, -0.5),
			new AIUIPosition(0.5, -0.5),
			
			new AIUIPosition(-0.5, 0.5),
			new AIUIPosition(-0.25, 0.5),
			new AIUIPosition(-0.25, 1.75),
			new AIUIPosition(0.25, 1.75),
			new AIUIPosition(0.25, 0.5),
			new AIUIPosition(0.5, 0.5)
	};
	
	public AIUISweeper(double xSize, double ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
	}
	
	public void drawSweeper(Graphics g, double dx, double dy, double rotation) {
		int i;
		
		AIUIPosition position = null;
		List<AIUIPosition> positionList = new ArrayList<AIUIPosition>();
		
		for (i = 0; i < sweeper.length; ++i) {
			position = computeAIUIPosition(sweeper[i].x, sweeper[i].y, dx, dy, rotation);
			positionList.add(position);
		}
		
		for (i = 1; i < 4; ++i) {
			g.drawLine((int)positionList.get(i - 1).x, (int)positionList.get(i - 1).y, (int)positionList.get(i).x, (int)positionList.get(i).y);
		}
		g.drawLine((int)positionList.get(0).x, (int)positionList.get(0).y, (int)positionList.get(3).x, (int)positionList.get(3).y);
		
		for (i = 5; i < 8; ++i) {
			g.drawLine((int)positionList.get(i - 1).x, (int)positionList.get(i - 1).y, (int)positionList.get(i).x, (int)positionList.get(i).y);
		}
		g.drawLine((int)positionList.get(4).x, (int)positionList.get(4).y, (int)positionList.get(7).x, (int)positionList.get(7).y);
		
		g.drawLine((int)positionList.get(8).x, (int)positionList.get(8).y, (int)positionList.get(9).x, (int)positionList.get(9).y);
		
		for (i = 11; i < 16; ++i) {
			g.drawLine((int)positionList.get(i - 1).x, (int)positionList.get(i - 1).y, (int)positionList.get(i).x, (int)positionList.get(i).y);
		}
	}
	
	private AIUIPosition computeAIUIPosition(double x, double y, double dx, double dy, double rotation) {
		double pX = x * xSize * Math.cos(rotation) - y * ySize * Math.sin(rotation) + dx;
		double pY = y * ySize * Math.sin(rotation) + y * ySize * Math.cos(rotation) + dy;
		
		return new AIUIPosition(pX, pY);
	}
}
