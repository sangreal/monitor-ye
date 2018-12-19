package enn.monitor.demo.drawing.main;

import java.awt.Color;      
import java.awt.Dimension;      
import java.awt.Graphics;      
import java.awt.Image;      
import java.awt.Toolkit;      
import java.awt.event.MouseEvent;      
import java.awt.event.MouseListener;      
import java.awt.event.MouseMotionListener;      
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;       

public class Game extends JFrame implements MouseMotionListener, MouseListener {   
	
	public static void main(String[] args) {              
		new Game();          
	}       

	private List<Position> positionList = new ArrayList<Position>();
	
	public Game() {              
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);              
		this.setUndecorated(false);              
		this.setBackground(Color.white);              
		this.setSize(800, 600);              
		this.addMouseMotionListener(this);              
		this.addMouseListener(this);              
		this.setVisible(true);      
		this.createBufferStrategy(2);       
	}       
	
	@Override          
	public void mouseDragged(MouseEvent e) {
		int i;
		
		BufferStrategy bf = this.getBufferStrategy();              
		Graphics g = null;        
		try {     
			if (positionList.get(positionList.size() - 1).x != e.getX() && 
					positionList.get(positionList.size() - 1).y != e.getY()) {
				positionList.add(new Position(e.getX(), e.getY()));
			}
      
			Dimension size = getSize();          
			Image image = createImage(size.width, size.height);     
			Graphics graphics = image.getGraphics();  
			
			for (i = 1; i < positionList.size(); ++i) {
				graphics.drawLine(positionList.get(i - 1).x, positionList.get(i - 1).y, 
						positionList.get(i).x, positionList.get(i).y);
			}
			graphics.drawLine(positionList.get(positionList.size() - 1).x, positionList.get(positionList.size() - 1).y, 
					e.getX(), e.getY());
			
			g = bf.getDrawGraphics();           
			g.drawImage(image, 0, 0, this);          
			bf.show();     
		} finally {       
			// It is best to dispose() a Graphics object when done with it.             
			g.dispose();          
		}     
		
		// Tell the System to do the Drawing now, otherwise it can take a few        
		// extra ms until          
		// Drawing is done which looks very jerky        
		Toolkit.getDefaultToolkit().sync();     
	}    
	
	@Override        
	public void mouseMoved(MouseEvent e) {          }     
	
	@Override      
	public void mouseClicked(MouseEvent e) {             
		// TODO Auto-generated method stub
	}

	@Override     
	public void mousePressed(MouseEvent e) {    
		positionList.add(new Position(e.getX(), e.getY()));  
	}     
	
	@Override       
	public void mouseEntered(MouseEvent e) {         
		// TODO Auto-generated method stub    
	}     
	
	@Override        
	public void mouseExited(MouseEvent e) {        
		// TODO Auto-generated method stub    
	}     
	
	@Override     
	public void mouseReleased(MouseEvent e) {    
		positionList.add(new Position(e.getX(), e.getY()));  
		positionList.clear();
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


	
		
		
		
		
		
		
		
		
		
		
		
		