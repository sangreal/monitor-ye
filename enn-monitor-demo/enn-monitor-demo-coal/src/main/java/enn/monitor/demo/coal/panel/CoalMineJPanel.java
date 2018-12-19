package enn.monitor.demo.coal.panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import enn.monitor.ai.common.AIMatrix;
import enn.monitor.ai.common.AIMatrixUtil;
import enn.monitor.ai.ui.common.AIUIPosition;

public class CoalMineJPanel extends JPanel {

	private static final long serialVersionUID = -6195834123155869677L;
	
	private int width = 0;
	private int height = 0;
	private int centerX = 0;
	private int centerY = 0;
	
	private final AIUIPosition[] minePosition = {
			new AIUIPosition(-1.05, -0.5), 
			new AIUIPosition(1, -0.5), 
			new AIUIPosition(1, -0.75), 
			
			new AIUIPosition(-1.05, -0.25), 
			new AIUIPosition(-0.25, -0.25), 
			new AIUIPosition(-0.25, 0.75), 
			new AIUIPosition(-1.05, 0.75), 
			
			new AIUIPosition(-1.05, 1), 
			new AIUIPosition(0, 1), 
			new AIUIPosition(0, -0.25), 
			new AIUIPosition(1.25, -0.25), 
			new AIUIPosition(1.25, -0.75)
		};	
	
	private final AIUIPosition bellows1 = new AIUIPosition(-1.15, -0.37);
	private int bellow1Status = 0;
	private final AIUIPosition bellows2 = new AIUIPosition(-1.15, 0.87);
	private int bellow2Status = 0;
	private final double bellowsRadius = 0.12;
	
	private final AIUIPosition sensor1 = new AIUIPosition(0.3, -0.37);
	private int sensor1Status = 0;
	private final AIUIPosition sensor2 = new AIUIPosition(0.7, -0.37);
	private int sensor2Status = 0;
	private final double sensorRadius = 0.12;
	
	private final AIUIPosition gasSource = new AIUIPosition(0.62, 0.37);
	private final double gasRadius = 0.62;
	
	public CoalMineJPanel() {
		
	}
	
	public void setSensor1(int status) {
		if (this.sensor1Status != status) {
			this.sensor1Status = status;
			updateUI();
		}
	}
	
	public void setSensor2(int status) {
		if (this.sensor2Status != status) {
			this.sensor2Status = status;
			updateUI();
		}
	}
	
	public void setBellow1(int status) {
		if (this.bellow1Status != status) {
			this.bellow1Status = status;
			updateUI();
		}
	}
	
	public void setBellow2(int status) {
		if (this.bellow2Status != status) {
			this.bellow2Status = status;
			updateUI();
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(248, 250, 241));
		
		computeAxis();
		drawMine(g);
	}
	
	private void computeAxis() {
		width = this.getWidth();
		height = this.getHeight();
		centerX = width >> 1;
		centerY = height >> 1;
	}

	private void drawMine(Graphics g) {
		int i;
		
		List<AIUIPosition> posList = new ArrayList<AIUIPosition>();
		AIUIPosition bellows1Pos = null;
		AIUIPosition bellows2Pos = null;
		AIUIPosition sensor1Pos = null;
		AIUIPosition sensor2Pos = null;
		AIUIPosition gasPos = null;
		
		Graphics2D g2 = (Graphics2D)g;
		
		double bRadius = 0.0;
		double sRadius = 0.0;
		double gRadius = 0.0;
		
		int gasSize = 100;
		
		g2.setStroke(new BasicStroke(2.0f));
		try {
			for (i = 0; i < minePosition.length; ++i) {
				posList.add(convertor(minePosition[i]));
			}
			
			for (i = 1; i <= 2; ++i) {
				g.drawLine((int)posList.get(i - 1).x, (int)posList.get(i - 1).y, (int)posList.get(i).x, (int)posList.get(i).y);
			}
			
			for (i = 4; i <= 6; ++i) {
				g.drawLine((int)posList.get(i - 1).x, (int)posList.get(i - 1).y, (int)posList.get(i).x, (int)posList.get(i).y);
			}
			
			for (i = 8; i <= 11; ++i) {
				g.drawLine((int)posList.get(i - 1).x, (int)posList.get(i - 1).y, (int)posList.get(i).x, (int)posList.get(i).y);
			}
			
			bellows1Pos = convertor(bellows1);
			bellows2Pos = convertor(bellows2);
			bRadius = (bellowsRadius * ((height - 200) / 1.75));
			drawbelow(g, (int)(bellows1Pos.x - bRadius / 2), (int)(bellows1Pos.y - bRadius), (int)(bRadius), (int)(bRadius * 2), bellow1Status);
			drawbelow(g, (int)(bellows2Pos.x - bRadius / 2), (int)(bellows2Pos.y - bRadius), (int)(bRadius), (int)(bRadius * 2), bellow2Status);
			
			sensor1Pos = convertor(sensor1);
			sensor2Pos = convertor(sensor2);
			sRadius = (sensorRadius * ((height - 200) / 1.75));
			drawSensor(g, (int)(sensor1Pos.x - sRadius / 2), (int)(sensor1Pos.y - sRadius / 2), (int)(sRadius), (int)(sRadius), sensor1Status);
			drawSensor(g, (int)(sensor2Pos.x - sRadius / 2), (int)(sensor2Pos.y - sRadius / 2), (int)(sRadius), (int)(sRadius), sensor2Status);
			
			gasPos = convertor(gasSource);
			gRadius = gasRadius * ((height - 200) / 2);
			g.drawOval((int)(gasPos.x - gRadius), (int)(gasPos.y - gRadius), (int)(gRadius * 2), (int)(gRadius * 2));
			
			Font font = new Font("宋体", Font.BOLD, 100);
			g.setFont(font);
			g.drawString("煤矿", (int)(gasPos.x - gasSize), (int)(gasPos.y + gasSize / 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		g2.setStroke(new BasicStroke(1.0f));
	}
	
	private void drawbelow(Graphics g, int x, int y, int width, int height, int value) {
		Color oldColor = g.getColor();
		
		switch (value) {
		case -1:
			g.setColor(Color.GRAY);
			break;
		case 0:
			g.setColor(Color.GREEN);
			break;
		case 1:
			g.setColor(Color.BLUE);
			break;
		}
		g.drawOval(x, y, width, height);
		g.setColor(oldColor);
	}
	
	private void drawSensor(Graphics g, int x, int y, int width, int height, int value) {
		Color oldColor = g.getColor();
		
		switch (value) {
		case -1:
			g.setColor(Color.GRAY);
			break;
		case 0:
			g.setColor(Color.GREEN);
			break;
		case 1:
			g.setColor(Color.YELLOW);
			break;
		case 2:
			g.setColor(Color.PINK);
			break;
		case 3:
			g.setColor(Color.RED);
			break;
		}
		
		g.drawOval(x, y, width, height);
		g.setColor(oldColor);
	}
	
	private AIUIPosition convertor(AIUIPosition pos) throws Exception {
		pos = scale(pos, (int) ((width - 30) / 2.5), (int) ((height - 200) / 1.75));
		pos = move(pos, centerX, centerY);
		
		return pos;
	}
	
	private AIUIPosition scale(AIUIPosition pos, int scaleX, int scaleY) throws Exception {
		AIMatrix posMatrix = new AIMatrix(1, 3);
		
		posMatrix.setAt(0, 0, pos.x);
		posMatrix.setAt(0, 1, pos.y);
		posMatrix.setAt(0, 2, 1);
		AIMatrixUtil.scale(posMatrix, scaleX, scaleY);
		
		return new AIUIPosition(posMatrix.getAt(0, 0), posMatrix.getAt(0, 1));
	}
	
	private AIUIPosition move(AIUIPosition pos, int dx, int dy) throws Exception {
		AIMatrix posMatrix = new AIMatrix(1, 3);
		
		posMatrix.setAt(0, 0, pos.x);
		posMatrix.setAt(0, 1, pos.y);
		posMatrix.setAt(0, 2, 1);
		AIMatrixUtil.move(posMatrix, dx, dy);
		
		return new AIUIPosition(posMatrix.getAt(0, 0), posMatrix.getAt(0, 1));
	}

}
