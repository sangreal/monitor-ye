package enn.monitor.trace.proto.model.service;


/**
 * Created by wst_dreg on 2018-03-20.
 */
public class ServiceTopology{
    private String callerSvc = null;
    private String calleeSvc = null;
    private long count = 0;

    public ServiceTopology(String callerSvc, String calleeSvc, long count) {
        this.callerSvc = callerSvc;
        this.calleeSvc = calleeSvc;
        this.count = count;
    }

    public String getCallerSvc() {
        return callerSvc;
    }

    public String getCalleeSvc() {
        return calleeSvc;
    }

    public long getCount() {
        return count;
    }

    public void setCallerSvc(String callerSvc) {
        this.callerSvc = callerSvc;
    }

    public void setCalleeSvc(String calleeSvc) {
        this.calleeSvc = calleeSvc;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
