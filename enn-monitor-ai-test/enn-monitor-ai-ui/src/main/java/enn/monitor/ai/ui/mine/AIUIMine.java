package enn.monitor.ai.ui.mine;

import java.awt.Graphics;

import enn.monitor.ai.ui.common.AIUIPosition;

public class AIUIMine {
	
	private final int numberOfPoint = 4;
	private final AIUIPosition[] mine = {
			new AIUIPosition(-1, -1), 
			new AIUIPosition(-1, 1), 
			new AIUIPosition(1, 1), 
			new AIUIPosition(1, -1)};	

	public void drawMine(Graphics g, int x, int y) {
		
	}
}
