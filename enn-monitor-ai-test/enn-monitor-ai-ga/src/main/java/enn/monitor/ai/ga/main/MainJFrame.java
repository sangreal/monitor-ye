package enn.monitor.ai.ga.main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import enn.monitor.ai.ga.panel.TSPJPanel;
import enn.monitor.framework.log.kafka.filter.value.string.EnnKafkaProducerMsgValueStringFilter;
import enn.monitor.metrics.app.write.api.kafka.visitor.EnnMonitorJsonVisitorTimeTable;
import enn.monitor.metrics.app.write.api.metrics.EnnMonitorMetrics;

public class MainJFrame extends JFrame {
	
	private static final long serialVersionUID = -8008221899785868824L;

	public static void main(String[] args) throws Exception {
		EnnMonitorJsonVisitorTimeTable visitorTable = new EnnMonitorJsonVisitorTimeTable();
		
		visitorTable.setBaseTime(System.currentTimeMillis());
		EnnMonitorMetrics.startMetricsCollector(
        		true, "10.19.248.32:30291,10.19.248.33:30291,10.19.248.34:30291", "monitor-app", 1, "micklongen", new EnnKafkaProducerMsgValueStringFilter(), visitorTable);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJFrame frame = new MainJFrame(visitorTable);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainJFrame(EnnMonitorJsonVisitorTimeTable visitorTable) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1800, 1200);
		
		setContentPane(new TSPJPanel(visitorTable));
	}
}
