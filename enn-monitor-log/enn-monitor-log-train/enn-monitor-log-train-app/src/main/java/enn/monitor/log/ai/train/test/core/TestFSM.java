package enn.monitor.log.ai.train.test.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import enn.monitor.framework.ai.nn.NNFramework;
import enn.monitor.framework.ai.nn.activation.NNActivationEnum;
import enn.monitor.framework.ai.nn.weights.NNWeightEnum;
import enn.monitor.log.ai.common.CommonEventInterface;
import enn.monitor.log.ai.common.CommonFSMInterface;
import enn.monitor.log.ai.train.test.panel.TestRunJPanel;
import enn.monitor.log.ai.train.test.parameter.TestRunDisplayParameter;
import enn.monitor.log.ai.train.test.parameter.TestRunParameter;
import enn.monitor.log.train.util.CoreContextUtil;

public class TestFSM extends CommonFSMInterface {
	
	private TestRunJPanel testRunJPanel = null;
	
	public TestFSM(BlockingQueue<CommonEventInterface> eventQueue) {
		super("Test");
		this.eventQueue = eventQueue;
	}

	@Override
	public void runEvent(Object data) throws Exception {
		TestEvent testEvent = (TestEvent) data;
		
		displayResult((TestRunParameter) testEvent.getData());
	}
	
	private void displayResult(TestRunParameter testRunParameter) throws Exception {
		int i;
		List<Double> outputList = null;
		
		TestRunDisplayParameter parameter = null;
		List<TestRunDisplayParameter> parameterList = new ArrayList<TestRunDisplayParameter>();
		
		outputList = NNFramework.compute(
				CoreContextUtil.getNnObject(), CoreContextUtil.getTrainNNData().formatInputList(testRunParameter.getData()), 
				null, NNActivationEnum.Sigmoid, NNWeightEnum.Momentum, 0, 0);
		if (outputList != null) {
			for (i = 0; i < outputList.size(); ++i) {
				parameter = new TestRunDisplayParameter();
				parameterList.add(parameter);
				
				parameter.setName(CoreContextUtil.getTrainNNData().getResults(i));
				parameter.setMatch(outputList.get(i));
			}
		}
		
		testRunJPanel.displayTestResult(parameterList);
	}
	
	public void setTestRunJPanel(TestRunJPanel testRunJPanel) {
		this.testRunJPanel = testRunJPanel;
	}
	
}
