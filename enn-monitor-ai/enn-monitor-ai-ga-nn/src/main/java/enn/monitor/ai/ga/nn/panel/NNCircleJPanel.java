package enn.monitor.ai.ga.nn.panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import enn.monitor.ai.ui.common.AIUIPosition;
import enn.monitor.framework.ai.common.AIMatrix;
import enn.monitor.framework.ai.common.AIMatrixUtil;

public class NNCircleJPanel extends JPanel {

	private static final long serialVersionUID = -3212620382188255863L;
	
	private int width = 0;
	private int height = 0;
	private int centerX = 0;
	private int centerY = 0;
	private int radius = 0;
	
	private int axesMargin = 30;
	
	private List<AIUIPosition> positionList = null;
	
	//private Random random = new Random();
	
	public NNCircleJPanel() {
		width = this.getWidth();
		height = this.getHeight();
	}
	
	public void drawCircle(List<AIUIPosition> positionList) {
		int i;
		
		for (i = 0; i < positionList.size(); ++i) {
			positionList.get(i).x = positionList.get(i).x * radius;
			positionList.get(i).y = positionList.get(i).y * radius;
		}
		
		this.positionList = positionList;
		this.updateUI();
		
		//System.out.println("" + this.position.x + " ------------------ " + this.position.y);
	}
	
	public int getRadius() {
		return radius;
	}
	
	protected void paintComponent(Graphics g) {
		internalPaintComponent(g);
	}
	
	private void internalPaintComponent(Graphics g) {
		super.paintComponent(g);
		
		setBackground(new Color(248, 250, 241));
		
		try {
			computeAxis();
			drawAxes(g);
			drawSample(g);
			if (positionList == null) {
				return;
			}
			drawCircle(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void computeAxis() {
		int tmpRadius = 0;
		
		width = this.getWidth();
		height = this.getHeight();
		centerX = width >> 1;
		centerY = height >> 1;
		
		if (width < height) {
			tmpRadius = width;
		} else {
			tmpRadius = height;
		}
		
		tmpRadius = (tmpRadius - axesMargin * 3) / 2;
		
		if (tmpRadius != radius) {
			radius = tmpRadius;
		}
	}
	
	private void drawCircle(Graphics g) throws Exception {
		int i;
		AIUIPosition position = null;
		
		Color oldColor = g.getColor();
		g.setColor(Color.RED);
		
		for (i = 0; i < positionList.size(); ++i) {
			position = rotation(positionList.get(i));
			g.drawOval((int)position.x - 2, (int)position.y - 2, 5, 5);
		}
		
		g.setColor(oldColor);
	}
	
	private void drawAxes(Graphics g) throws Exception {
		Graphics2D g2 = (Graphics2D)g;
		
		AIUIPosition posXLeft, posXRight, posYTop, posYBottom;
		AIUIPosition posArrowX1, posArrowX2;
		AIUIPosition posArrowY1, posArrowY2;
		
		AIUIPosition xLeft = new AIUIPosition(-width/2 + axesMargin, 0);
		AIUIPosition xRight = new AIUIPosition(width/2 - axesMargin, 0);
		AIUIPosition yTop = new AIUIPosition(0, height/2 - axesMargin);
		AIUIPosition yBottom = new AIUIPosition(0, -height/2 + axesMargin);
		
		AIUIPosition arrowX1 = new AIUIPosition(xRight.x - 5, xRight.y - 5);
		AIUIPosition arrowX2 = new AIUIPosition(xRight.x - 5, xRight.y + 5);
		
		AIUIPosition arrowY1 = new AIUIPosition(yTop.x - 5, yTop.y - 5);
		AIUIPosition arrowY2 = new AIUIPosition(yTop.x + 5, yTop.y - 5);
		
		posXLeft = move(xLeft);
		posXRight = move(xRight);
		
		posArrowX1 = move(arrowX1);
		posArrowX2 = move(arrowX2);
		
		posYTop = rotation(yTop);
		posYBottom = rotation(yBottom);
		
		posArrowY1 = rotation(arrowY1);
		posArrowY2 = rotation(arrowY2);
		
		g2.setStroke(new BasicStroke(2.0f));
		g.drawLine((int)posXLeft.x, (int)posXLeft.y, (int)posXRight.x, (int)posXRight.y);
		g.drawLine((int)posYBottom.x, (int)posYBottom.y, (int)posYTop.x, (int)posYTop.y);
		
		g.drawLine((int)posArrowX1.x, (int)posArrowX1.y, (int)posXRight.x, (int)posXRight.y);
		g.drawLine((int)posArrowX2.x, (int)posArrowX2.y, (int)posXRight.x, (int)posXRight.y);
		
		g.drawLine((int)posArrowY1.x, (int)posArrowY1.y, (int)posYTop.x, (int)posYTop.y);
		g.drawLine((int)posArrowY2.x, (int)posArrowY2.y, (int)posYTop.x, (int)posYTop.y);
		
		g2.setStroke(new BasicStroke(1.0f));
	}
	
	private void drawSample(Graphics g) throws Exception {
		int i;
		int count = 1000;
		
		double angle = Math.PI * 2 / count;
		double drawAngle = 0.0;
		
		double x, y;
		
		for (i = 0; i < count; ++i, drawAngle += angle) {
			x = Math.cos(drawAngle) * radius;
			y = Math.sin(drawAngle) * radius;
			
			x += centerX;
			y += centerY;
			
			g.drawOval((int)x, (int)y, 1, 1);
		}
		
		//g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
	}
	
	private AIUIPosition move(AIUIPosition pos) throws Exception {
		return move(pos, centerX, centerY);
	}
	
	private AIUIPosition rotation(AIUIPosition pos) throws Exception {
		pos = rotation(pos, Math.PI);
		pos = move(pos);
		
		return pos;
	}
	
	private AIUIPosition move(AIUIPosition pos, int dx, int dy) throws Exception {
		AIMatrix posMatrix = new AIMatrix(1, 3);
		
		posMatrix.setAt(0, 0, pos.x);
		posMatrix.setAt(0, 1, pos.y);
		posMatrix.setAt(0, 2, 1);
		AIMatrixUtil.move(posMatrix, dx, dy);
		
		return new AIUIPosition(posMatrix.getAt(0, 0), posMatrix.getAt(0, 1));
	}
	
	private AIUIPosition rotation(AIUIPosition pos, double angle) throws Exception {
		AIMatrix posMatrix = new AIMatrix(1, 3);
		
		posMatrix.setAt(0, 0, pos.x);
		posMatrix.setAt(0, 1, pos.y);
		posMatrix.setAt(0, 2, 1);
		AIMatrixUtil.rotation(posMatrix, angle);
		
		return new AIUIPosition(posMatrix.getAt(0, 0), posMatrix.getAt(0, 1));
	}
}
