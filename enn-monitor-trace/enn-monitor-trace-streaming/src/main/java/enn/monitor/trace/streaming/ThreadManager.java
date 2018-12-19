package enn.monitor.trace.streaming;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by weize on 18-4-3.
 */
public class ThreadManager {
    private int corePoolSize = 50;
    private int maximumPoolSize = 100;
    private long keepAliveTime = 5;
    private TimeUnit unit = TimeUnit.SECONDS;
    BlockingQueue<Runnable> workQueue;
    private ThreadPoolExecutor threadPool;
    private static volatile ThreadManager threadManager;

    private ThreadManager() {
        workQueue = new LinkedBlockingDeque<>();
        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public void runTaskInPool(Runnable task) {
        threadPool.execute(task);
    }

    /**
     * 单例
     *
     * @return
     */
    public static ThreadManager getInstance() {
        if(threadManager == null){
            synchronized (ThreadManager.class){
                if(threadManager == null){
                    threadManager = new ThreadManager();
                }
            }
        }
        return threadManager;
    }

}