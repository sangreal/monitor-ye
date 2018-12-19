package enn.monitor.log.ai.train.test.core;

import enn.monitor.log.ai.common.CommonEventInterface;

public class TestEvent extends CommonEventInterface {

	private TestEventEnum testEventEnum = null;
	private Object data = null;
	
	public TestEvent(TestEventEnum testEventEnum, Object data) {
		this.testEventEnum = testEventEnum;
		this.data = data;
	}
	
	public TestEventEnum getTestEventEnum() {
		return testEventEnum;
	}
	
	public void setTestEventEnum(TestEventEnum testEventEnum) {
		this.testEventEnum = testEventEnum;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
}
