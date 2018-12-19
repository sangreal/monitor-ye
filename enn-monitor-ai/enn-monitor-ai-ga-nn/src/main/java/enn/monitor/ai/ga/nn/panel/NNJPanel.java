package enn.monitor.ai.ga.nn.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.avaje.metric.GaugeDouble;
import org.avaje.metric.MetricManager;

import enn.monitor.ai.ga.nn.common.GAConfigureParameter;
import enn.monitor.ai.ga.nn.common.NNConfigureParameter;
import enn.monitor.ai.ga.nn.common.NNResult;
import enn.monitor.ai.ga.nn.main.NNThread;
import enn.monitor.ai.ga.nn.panel.common.NNEvent;
import enn.monitor.ai.ga.nn.panel.common.NNEventEnum;

public class NNJPanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 2002012308498717087L;
	
	private static final int labelWidth = 120;
	private static final int textWidth = 200;
	
	private GAConfigureJPanel parameterJPanel = null;
	private NNResultJPanel resultJPanel = null;
	private NNCircleJPanel circleJPanel = null;
	private NNConfigureJPanel nnJPanel = null;
	
	private JButton button = null;
	
	private boolean isRunning = false;

	private BlockingQueue<NNEvent> tspEventQueue = new LinkedBlockingQueue<NNEvent>();
	
	public NNJPanel() {
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		parameterJPanel = new GAConfigureJPanel(labelWidth, textWidth);
		add(parameterJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, parameterJPanel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, parameterJPanel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, parameterJPanel, 400, SpringLayout.WEST, parameterJPanel);
		tspLayout.putConstraint(SpringLayout.SOUTH, parameterJPanel, 420, SpringLayout.NORTH, parameterJPanel);
		
		resultJPanel = new NNResultJPanel(labelWidth, textWidth);
		add(resultJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, resultJPanel, 10, SpringLayout.SOUTH, parameterJPanel);
		tspLayout.putConstraint(SpringLayout.WEST, resultJPanel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, resultJPanel, 400, SpringLayout.WEST, resultJPanel);
		tspLayout.putConstraint(SpringLayout.SOUTH, resultJPanel, 200, SpringLayout.NORTH, resultJPanel);
		
		button = new JButton();
		button.setText("开始");
		add(button);
		tspLayout.putConstraint(SpringLayout.SOUTH, button, -10, SpringLayout.SOUTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, button, 10, SpringLayout.WEST, this);
		
		nnJPanel = new NNConfigureJPanel();
		add(nnJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, nnJPanel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, nnJPanel, 250, SpringLayout.NORTH, nnJPanel);
		tspLayout.putConstraint(SpringLayout.WEST, nnJPanel, 10, SpringLayout.EAST, parameterJPanel);
		tspLayout.putConstraint(SpringLayout.EAST, nnJPanel, -10, SpringLayout.EAST, this);
		
		circleJPanel = new NNCircleJPanel();
		add(circleJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, circleJPanel, 10, SpringLayout.SOUTH, nnJPanel);
		tspLayout.putConstraint(SpringLayout.SOUTH, circleJPanel, -10, SpringLayout.SOUTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, circleJPanel, 10, SpringLayout.EAST, parameterJPanel);
		tspLayout.putConstraint(SpringLayout.EAST, circleJPanel, -10, SpringLayout.EAST, this);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NNEvent tspEvent = null;
				
				if (isRunning == true) {
					button.setText("开始");
					isRunning = false;
					
					tspEvent = new NNEvent();
					tspEvent.setTspEventEnum(NNEventEnum.Stop);
					tspEventQueue.add(tspEvent);
				} else {
					button.setText("暂停");
					isRunning = true;
					
					tspEvent = new NNEvent();
					tspEvent.setTspEventEnum(NNEventEnum.Start);
					tspEventQueue.add(tspEvent);
				}
			}
			
		});
		
		new Thread(this).start();
	}
	
	private static class GaugeDoubleClass implements GaugeDouble {
		public double bestFitess = 0.0;
		
		public double getValue() {
			return bestFitess;
	    }
	}
	
	@Override
	public void run() {
		GAConfigureParameter gaParameter = null;
		NNConfigureParameter nnParameter = null;
		
		NNEvent nnEvent = null;
		NNThread nnThread = null;
		
		NNResult nnResult = null;
		
		GaugeDoubleClass bestFitess = new GaugeDoubleClass();
		MetricManager.register(MetricManager.name(NNJPanel.class, "shortestRoute"), bestFitess);
		
		try {
			while (true) {
				nnEvent = tspEventQueue.poll(100, TimeUnit.MICROSECONDS);
				
				if (nnEvent == null) {
					continue;
				}
				
				switch (nnEvent.getTspEventEnum()) {
				case Start:
					button.setText("暂停");
					isRunning = true;
					
					gaParameter = parameterJPanel.getTSPParameter();
					nnParameter = nnJPanel.getNNConfigureParameter();
					
					nnThread = new NNThread(gaParameter, nnParameter, tspEventQueue);
					nnThread.startThread();
					break;
				case Stop:
					button.setText("开始");
					isRunning = false;
					
					nnThread.stopThead();
					break;
				case Update:
					nnResult = (NNResult) nnEvent.getData();
					
					bestFitess.bestFitess = nnResult.getShortestRoute();
					circleJPanel.drawCircle(nnResult.getPositionList());
					resultJPanel.setTSPResult(nnResult, circleJPanel.getRadius());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
