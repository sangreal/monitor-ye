package enn.monitor.ai.ga.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import enn.monitor.ai.ga.common.TSPCircleParameter;
import enn.monitor.ai.ga.common.TSPCircleParameter.Position;

public class TSPCircleJPanel extends JPanel {

	private static final long serialVersionUID = -3212620382188255863L;
	
	private int width = 0;
	private int height = 0;
	private int centerX = 0;
	private int centerY = 0;
	private int radius = 0;
	
	private List<Position> posList = new ArrayList<Position>();
	private List<Integer> cityRouteList = null;
	
	// city数量
	private int cities = 0;
	
	public TSPCircleJPanel(int cities) {
		width = this.getWidth();
		height = this.getHeight();
		
		init(cities);
	}
	
	public void init(int cities) {
		this.cities = cities;
		reCompute();
		this.updateUI();
	}
	
	public void drawRoute(List<Integer> cityRouteList) {
		this.cityRouteList = cityRouteList;
		this.updateUI();
	}
	
	public List<Position> getPositionList() {
		return posList;
	}
	
	protected void paintComponent(Graphics g) {
		internalPaintComponent(g);
	}
	
	private void internalPaintComponent(Graphics g) {
		super.paintComponent(g);
		
		setBackground(new Color(248, 250, 241));
		
		computeAxis();
		drawCircle(g);
		drawCityRoute(g);
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
		
		tmpRadius = (tmpRadius - 30) / 2;
		
		if (tmpRadius != radius) {
			radius = tmpRadius;
			reCompute();
		}
	}
	
	private void reCompute() {
		int i;
		double angle = 2 * Math.PI / cities;
		double degree = 0.0;
		
		double x;
		double y;
		
		posList.clear();
		for (i = 0; i < cities; ++i, degree += angle) {
			x = radius * Math.cos(degree) + centerX;
			y = radius * Math.sin(degree) + centerY;
			posList.add(new TSPCircleParameter.Position((int)x, (int)y));
		}
	}
	
	private void drawCircle(Graphics g) {
		int i;
		
		for (i = 0; i < cities; ++i) {
			g.drawOval(posList.get(i).x, posList.get(i).y, 1, 1);
		}
	}
	
	private void drawCityRoute(Graphics g) {
		int i; 
		int from, to;
		
		if (cityRouteList == null) {
			return;
		}
		
		for (i = 0; i < cityRouteList.size(); ++i) {
			from = cityRouteList.get(i);
			if (i == cityRouteList.size() - 1 ) {
				to = cityRouteList.get(0);
			} else {
				to = cityRouteList.get(i + 1);
			}
			
			g.drawLine(posList.get(from).x, posList.get(from).y, posList.get(to).x, posList.get(to).y);
		}
	}

}
