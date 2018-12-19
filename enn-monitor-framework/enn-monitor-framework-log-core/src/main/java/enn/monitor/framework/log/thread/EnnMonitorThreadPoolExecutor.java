package enn.monitor.framework.log.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EnnMonitorThreadPoolExecutor extends ThreadPoolExecutor {

	public EnnMonitorThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	public void execute(Runnable command) {
		EnnMonitorRunnable ennMonitorRunnable = new EnnMonitorRunnable(command);
		super.execute(ennMonitorRunnable);
	}
	
	private class EnnMonitorRunnable implements Runnable {
		
		private Runnable runnable = null;
		
		public EnnMonitorRunnable(Runnable runnable) {
			this.runnable = runnable;
		}

		@Override
		public void run() {
			if (runnable != null) {
				runnable.run();
			}
		}
		
	}
}
