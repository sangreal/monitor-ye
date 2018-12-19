package enn.monitor.framework.metrics.app.kafka.visitor;

public class EnnMonitorJsonVisitorDefault extends EnnMonitorJsonVisitor {

	@Override
	protected long getCollection() {
		return this.collectionTime;
	}

}
