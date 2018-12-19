package enn.monitor.ai.nn.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.ai.nn.common.NNConfigureParameter;
import enn.monitor.ai.nn.common.NNConfigureParameter.NNItem;
import enn.monitor.ai.nn.common.NNResult;
import enn.monitor.ai.nn.core.NNCore;
import enn.monitor.ai.nn.data.NNTrainData;
import enn.monitor.ai.nn.panel.common.NNEvent;
import enn.monitor.ai.nn.panel.common.NNEventEnum;
import enn.monitor.ai.nn.panel.common.NNModeEnum;
import enn.monitor.framework.ai.nn.NNFramework;
import enn.monitor.framework.ai.nn.NNObject;
import enn.monitor.framework.ai.nn.weights.NNWeightEnum;

public class NNJPanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 2002012308498717087L;
	
	private static final Logger logger = LogManager.getLogger();
	
	private static final int labelWidth = 120;
	private static final int textWidth = 200;

	private NNResultJPanel resultJPanel = null;
	private NNDrawingJPanel drawingJPanel = null;
	private NNConfigureJPanel nnConfigureJPanel = null;
	
	private NNCore nnCore = null;
	
	private JButton addTrainDataBtn = null;
	private JButton trainBtn = null;
	
	private NNModeEnum nnModeEnum = NNModeEnum.Start;

	private BlockingQueue<NNEvent> eventQueue = new LinkedBlockingQueue<NNEvent>();
	
	private NNTrainData nnTrainData = new NNTrainData();
	
	private NNConfigureParameter nnConfigureParameter = null;
	private NNObject nnObject = null;
	
	private boolean isRunning = false;
	
	public NNJPanel() throws Exception {
		SpringLayout tspLayout = new SpringLayout();
		setLayout(tspLayout);
		
		nnConfigureJPanel = new NNConfigureJPanel();
		add(nnConfigureJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, nnConfigureJPanel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, nnConfigureJPanel, 250, SpringLayout.NORTH, nnConfigureJPanel);
		tspLayout.putConstraint(SpringLayout.WEST, nnConfigureJPanel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, nnConfigureJPanel, 1200, SpringLayout.WEST, nnConfigureJPanel);

		resultJPanel = new NNResultJPanel(labelWidth, textWidth);
		add(resultJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, resultJPanel, 10, SpringLayout.NORTH, this);
		tspLayout.putConstraint(SpringLayout.SOUTH, resultJPanel, 250, SpringLayout.NORTH, resultJPanel);
		tspLayout.putConstraint(SpringLayout.WEST, resultJPanel, 10, SpringLayout.EAST, nnConfigureJPanel);
		tspLayout.putConstraint(SpringLayout.EAST, resultJPanel, -10, SpringLayout.EAST, this);
		
		trainBtn = new JButton();
		trainBtn.setText("开始");
		add(trainBtn);
		tspLayout.putConstraint(SpringLayout.SOUTH, trainBtn, -10, SpringLayout.SOUTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, trainBtn, 10, SpringLayout.WEST, this);
		trainBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (isRunning == true) {
						isRunning = false;
						trainBtn.setText("开始");
						eventQueue.put(new NNEvent(NNEventEnum.Start));
					} else {
						isRunning = true;
						trainBtn.setText("暂停");
						eventQueue.put(new NNEvent(NNEventEnum.Training));
					}
				} catch (Exception exception) {
					logger.error(exception.getMessage(), exception);
				}
			}
			
		});
		
		addTrainDataBtn = new JButton();
		addTrainDataBtn.setText("添加新的手势");
		add(addTrainDataBtn);
		tspLayout.putConstraint(SpringLayout.SOUTH, addTrainDataBtn, -10, SpringLayout.SOUTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, addTrainDataBtn, 10, SpringLayout.EAST, trainBtn);
		addTrainDataBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					eventQueue.put(new NNEvent(NNEventEnum.LearningMode));
				} catch (Exception exception) {
					logger.error(exception.getMessage(), exception);
				}
			}
			
		});
		
		drawingJPanel = new NNDrawingJPanel(eventQueue, nnTrainData);
		add(drawingJPanel);
		tspLayout.putConstraint(SpringLayout.NORTH, drawingJPanel, 10, SpringLayout.SOUTH, nnConfigureJPanel);
		tspLayout.putConstraint(SpringLayout.SOUTH, drawingJPanel, -40, SpringLayout.SOUTH, this);
		tspLayout.putConstraint(SpringLayout.WEST, drawingJPanel, 10, SpringLayout.WEST, this);
		tspLayout.putConstraint(SpringLayout.EAST, drawingJPanel, -10, SpringLayout.EAST, this);
		
		//eventQueue.put(new NNEvent(NNEventEnum.Training));
		nnCore = new NNCore(eventQueue);
		
		new Thread(this).start();
	}
	
	
	@Override
	public void run() {
		int i;
		double match = 0.0;
		int position = 0;
		
		List<Double> inputList = null;
		List<Double> outputList = null;
		NNEvent nnEvent = null;
		NNResult nnResult = null;
		
		try {
			nnResult = new NNResult();
			nnResult.setNnModeEnum(NNModeEnum.Start);
			resultJPanel.setResult(nnResult);
			
			while (true) {
				nnEvent = eventQueue.poll(100, TimeUnit.MICROSECONDS);
				
				if (nnEvent == null) {
					continue;
				}
				
				switch (nnModeEnum) {
				case Start:
					switch (nnEvent.getEventEnum()) {
					case Training:
						nnModeEnum = NNModeEnum.Training;
						nnResult = new NNResult();
						nnResult.setNnModeEnum(nnModeEnum);
						resultJPanel.setResult(nnResult);
						train();
						break;
					default:
						logger.error("the current mode is " + nnModeEnum.name() + ", the event is " + nnEvent.getEventEnum().name());
						break;
					}
					drawingJPanel.setNnModeEnum(nnModeEnum);
					break;
				case Training:
					switch (nnEvent.getEventEnum()) {
					case Active:
						nnModeEnum = NNModeEnum.Active;
						nnResult = new NNResult();
						nnResult.setNnModeEnum(nnModeEnum);
						resultJPanel.setResult(nnResult);
						break;
					case UpdateResult:
						nnResult = (NNResult) nnEvent.getData();
						resultJPanel.setResult(nnResult);
						break;
					case Start:
						nnModeEnum = NNModeEnum.Start;
						nnResult = new NNResult();
						nnResult.setNnModeEnum(nnModeEnum);
						resultJPanel.setResult(nnResult);
						nnCore.stop();
						break;
					default:
						logger.error("the current mode is " + nnModeEnum.name() + ", the event is " + nnEvent.getEventEnum().name());
						break;
					}
					drawingJPanel.setNnModeEnum(nnModeEnum);
					break;
				case Learning:
					switch (nnEvent.getEventEnum()) {
					case LearningData:
						nnModeEnum = NNModeEnum.Training;
						nnResult = new NNResult();
						nnResult.setNnModeEnum(nnModeEnum);
						resultJPanel.setResult(nnResult);
						
						train();
						break;
					case Start:
						nnModeEnum = NNModeEnum.Start;
						nnResult = new NNResult();
						nnResult.setNnModeEnum(nnModeEnum);
						resultJPanel.setResult(nnResult);
						nnCore.stop();
						break;
					default:
						logger.error("the current mode is " + nnModeEnum.name() + ", the event is " + nnEvent.getEventEnum().name());
						break;
					}
					drawingJPanel.setNnModeEnum(nnModeEnum);
					break;
				case Active:
					switch (nnEvent.getEventEnum()) {
					case Training:
						nnModeEnum = NNModeEnum.Training;
						nnResult = new NNResult();
						nnResult.setNnModeEnum(nnModeEnum);
						resultJPanel.setResult(nnResult);
						train();
						break;
					case Recognize:
						inputList = (List<Double>) nnEvent.getData();
						if (inputList != null) {
							outputList = NNFramework.compute(nnObject, inputList, null, nnConfigureParameter.getNnActivationEnum(), NNWeightEnum.Momentum, 0, 0);
							match = 0.0;
							for (i = 0; i < outputList.size(); ++i) {
								//System.out.println(outputList.get(i));
								if (match < outputList.get(i)) {
									match = outputList.get(i);
									position = i;
								}
							}
							
							nnResult = new NNResult();
							nnResult.setMatchTolerance(match);
							nnResult.setRecognize(nnTrainData.getNames(position));
						} else {
							nnResult = new NNResult();
							nnResult.setRecognize("point not enough");
						}
						
						resultJPanel.setResult(nnResult);
						break;
					case LearningMode:
						nnModeEnum = NNModeEnum.Learning;
						nnResult = new NNResult();
						nnResult.setNnModeEnum(nnModeEnum);
						resultJPanel.setResult(nnResult);
						break;
					case Start:
						nnModeEnum = NNModeEnum.Start;
						nnResult = new NNResult();
						nnResult.setNnModeEnum(nnModeEnum);
						resultJPanel.setResult(nnResult);
						nnCore.stop();
						break;
					default:
						logger.error("the current mode is " + nnModeEnum.name() + ", the event is " + nnEvent.getEventEnum().name());
						break;
					}
					drawingJPanel.setNnModeEnum(nnModeEnum);
					break;
				default:
					logger.error("the unexpect mode, the mode is " + nnModeEnum.name());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void train() {
		nnObject = new NNObject();
		nnConfigureParameter = nnConfigureJPanel.getNNConfigureParameter();
		
		nnObject.addNeuronsLayer(nnTrainData.getInputSize(), nnConfigureParameter.getBias());
		for (NNItem item : nnConfigureParameter.getNNLayer()) {
			nnObject.addNeuronsLayer(item.numberOfNeuron, nnConfigureParameter.getBias());
		}
		nnObject.setLearningRate(nnConfigureParameter.getLearningRate());
		nnObject.setMomentum(nnConfigureParameter.getMomentum());
		nnObject.setMaxNoise(nnConfigureParameter.getMaxNoise());
		nnObject.addNeuronsLayer(nnTrainData.getOutputSize(), nnConfigureParameter.getBias());
		
		nnCore.setNnObject(nnObject);
		nnCore.setInputListList(nnTrainData.getInputListList());
		nnCore.setTargetOutputList(nnTrainData.getOutputList());
		nnCore.setNNConfigureParameter(nnConfigureParameter);
		
		nnCore.train();
	}
	
}
