package enn.monitor.ai.nn.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.ai.nn.data.NNTrainData;
import enn.monitor.ai.nn.panel.common.NNEvent;
import enn.monitor.ai.nn.panel.common.NNEventEnum;
import enn.monitor.ai.nn.panel.common.NNModeEnum;
import enn.monitor.ai.ui.common.AIUIPosition;

public class NNDrawingJPanel extends JPanel implements MouseMotionListener, MouseListener {

	private static final long serialVersionUID = -3212620382188255863L;
	
	private static final Logger logger = LogManager.getLogger();
	
	private List<AIUIPosition> positionList = new ArrayList<AIUIPosition>();
	List<AIUIPosition> outputPositionList = new ArrayList<AIUIPosition>();
	
	private BlockingQueue<NNEvent> eventQueue = new LinkedBlockingQueue<NNEvent>();
	
	private int curX = 0;
	private int curY = 0;
	
	private NNTrainData nnTrainData = null;
	
	private NNModeEnum nnModeEnum = NNModeEnum.Start;
	
	public NNDrawingJPanel(BlockingQueue<NNEvent> eventQueue, NNTrainData nnTrainData) {
		this.eventQueue = eventQueue;
		this.addMouseMotionListener(this);              
		this.addMouseListener(this);
		this.nnTrainData = nnTrainData;
	}
	
	@Override          
	public void mouseDragged(MouseEvent e) {
		if (nnModeEnum != NNModeEnum.Active && nnModeEnum != NNModeEnum.Learning) {
			return;
		}
		
		if (positionList.get(positionList.size() - 1).x != e.getX() && 
				positionList.get(positionList.size() - 1).y != e.getY()) {
			positionList.add(new AIUIPosition(e.getX(), e.getY()));
		}
		
		curX = e.getX();
		curY = e.getY();

		this.updateUI();
	}    
	
	@Override        
	public void mouseMoved(MouseEvent e) {          }     
	
	@Override      
	public void mouseClicked(MouseEvent e) {             
	}

	@Override     
	public void mousePressed(MouseEvent e) {    
		if (nnModeEnum != NNModeEnum.Active && nnModeEnum != NNModeEnum.Learning) {
			return;
		}
		
		outputPositionList.clear();
		positionList.clear();
		positionList.add(new AIUIPosition(e.getX(), e.getY()));  
	}     
	
	@Override       
	public void mouseEntered(MouseEvent e) {         
	}     
	
	@Override        
	public void mouseExited(MouseEvent e) {        
	}     
	
	@Override     
	public void mouseReleased(MouseEvent e) {    
		List<Double> outputList = null;
		
		if (nnModeEnum != NNModeEnum.Active && nnModeEnum != NNModeEnum.Learning) {
			return;
		}
		
		positionList.add(new AIUIPosition(e.getX(), e.getY()));
		
		outputList = smooth();
		
		switch (nnModeEnum) {
		case Active:
			eventQueue.add(new NNEvent(NNEventEnum.Recognize, outputList));
			break;
		case Learning:
			eventQueue.add(new NNEvent(NNEventEnum.LearningData));
			JOptionPane.showInputDialog("输入手势的名称");
			break;
		default:
			logger.error("unexpect modeEnum, it is " + nnModeEnum.name());
			break;
		}
		
		this.updateUI();
	}   
	
	public NNModeEnum getNnModeEnum() {
		return nnModeEnum;
	}

	public void setNnModeEnum(NNModeEnum nnModeEnum) {
		this.nnModeEnum = nnModeEnum;
	}

	protected void paintComponent(Graphics g) {
		int i;
		
		super.paintComponent(g);
		setBackground(new Color(248, 250, 241));
		
		if ((nnModeEnum != NNModeEnum.Active && nnModeEnum != NNModeEnum.Learning) || positionList.size() <= 0) {
			return;
		}
		
		for (i = 1; i < positionList.size(); ++i) {
			g.drawLine((int)positionList.get(i - 1).x, (int)positionList.get(i - 1).y, 
					(int)positionList.get(i).x, (int)positionList.get(i).y);
		}
		g.drawLine((int)positionList.get(positionList.size() - 1).x, (int)positionList.get(positionList.size() - 1).y, 
				curX, curY); 
		
		for (i = 0; i < outputPositionList.size(); ++i) {
			g.drawRect((int)outputPositionList.get(i).x - 1, (int)outputPositionList.get(i).y - 1, 2, 2);
		}
	}
	
	private List<Double> smooth() {
		int i;
		int pos = 0;
		double length;
		double minLength;
		
		double x, y;
		
		List<Double> outputList = new ArrayList<Double>();
		
		outputPositionList.addAll(positionList);
		if (outputPositionList.size() < nnTrainData.getInputSize() / 2 + 1) {
			return null;
		}
		
		while (outputPositionList.size() > nnTrainData.getInputSize() / 2 + 1) {
			minLength = Double.MAX_VALUE;
			for (i = 1; i < outputPositionList.size(); ++i) {
				length = (outputPositionList.get(i - 1).x - outputPositionList.get(i).x) * (outputPositionList.get(i - 1).x - outputPositionList.get(i).x) + 
						(outputPositionList.get(i - 1).y - outputPositionList.get(i).y) * (outputPositionList.get(i - 1).y - outputPositionList.get(i).y);
				if (minLength > length) {
					minLength = length;
					pos = i;
				}
			}
			
			outputPositionList.set(pos - 1, new AIUIPosition((outputPositionList.get(pos - 1).x + outputPositionList.get(pos).x) / 2,
					(outputPositionList.get(pos - 1).y + outputPositionList.get(pos).y) / 2));
			
			outputPositionList.remove(pos);
		}
		
		for (i = 1; i < outputPositionList.size(); ++i) {
			x = outputPositionList.get(i).x - outputPositionList.get(i - 1).x;
			y = outputPositionList.get(i).y - outputPositionList.get(i - 1).y;
			
			outputList.add(x / Math.sqrt(x * x + y * y));
			outputList.add(y / Math.sqrt(x * x + y * y));
		}
		
		return outputList;
	}
	
}
