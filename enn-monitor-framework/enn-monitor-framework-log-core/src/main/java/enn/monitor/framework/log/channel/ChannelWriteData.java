package enn.monitor.framework.log.channel;

public class ChannelWriteData {
	
	// trace list
//	private EnnMonitorLogTraceData ennMonitorLogTraceData = null;
	
	// data
    private ChannelWriteOpType opEnum = null;
    private String key = null;
    private Object object = null;
    
    public ChannelWriteData(){}
    
    public ChannelWriteData(Object object) {
    	this.object = object;
    }
    
    public ChannelWriteData(ChannelWriteOpType opEnum, Object object) {
    	this.opEnum = opEnum;
    	this.object = object;
    }
    
//    public EnnMonitorLogTraceData getEnnMonitorLogTraceData() {
//		return ennMonitorLogTraceData;
//	}
//
//	public void setEnnMonitorLogTraceData(EnnMonitorLogTraceData ennMonitorLogTraceData) {
//		this.ennMonitorLogTraceData = ennMonitorLogTraceData;
//	}

	public ChannelWriteOpType getOpEnum() {
        return opEnum;
    }
    
    public void setOpEnum(ChannelWriteOpType opEnum) {
        this.opEnum = opEnum;
    }
    
    public String getKey() {
    	return key;
    }
    
    public void setKey(String key) {
    	this.key = key;
    }
    
    public Object getObject() {
        return object;
    }
    
    public void setObject(Object object) {
        this.object = object;
    }

}
