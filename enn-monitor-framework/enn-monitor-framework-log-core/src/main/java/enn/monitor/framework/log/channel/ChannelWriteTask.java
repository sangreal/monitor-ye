package enn.monitor.framework.log.channel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ChannelWriteTask extends Thread {
    private static final Logger logger = LogManager.getLogger(ChannelWriteTask.class);

    private AtomicBoolean threadRun = new AtomicBoolean(true);
    
    protected BlockingQueue<ChannelWriteData> stockQueue = new LinkedBlockingQueue<ChannelWriteData>(); // 本身是线程安全的
    
    private long waitMills = 200l;
    
    public ChannelWriteTask() { }
    
    public ChannelWriteTask(long waitMills) {
        this.waitMills = waitMills;
    }
    
    public void setTaskQueue(BlockingQueue<ChannelWriteData> taskQueue) {
        this.stockQueue = taskQueue;
    }
    
    public void run() {
        ChannelWriteData channelWriteData = null;

        try {
        	init();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return;
        }
        
        while (threadRun.get() == true) {
            try {
                channelWriteData = getChannelWriteData();
                if (channelWriteData != null) {
                    operator(channelWriteData);
                } else {
                	operatorNull();
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public void write(ChannelWriteData channelWriteData) throws Exception {
        stockQueue.put(channelWriteData);
    }
    
    public static void write(BlockingQueue<ChannelWriteData> stockQueue, ChannelWriteData channelWriteData) throws Exception {
        stockQueue.put(channelWriteData);
    }
    
    public void stopTask() {
        setThreadStatus(false);
    }

    public void setThreadStatus(boolean bool) {
        threadRun.set(bool);
    }

    public boolean isBusy() {
        return (stockQueue.size() != 0);
    }
    
    public int size() {
    	return stockQueue.size();
    }
    
    private ChannelWriteData getChannelWriteData() throws Exception {
    	return stockQueue.poll(waitMills, TimeUnit.MILLISECONDS);
    }
    
    protected void init() throws Exception {};
    protected void operatorNull() throws Exception { }

    abstract protected void operator(ChannelWriteData stockWriteData) throws Exception;
}
