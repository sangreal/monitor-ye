package enn.monitor.ai.ga.main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import enn.monitor.ai.ga.panel.TSPJPanel;
import enn.monitor.framework.metrics.app.kafka.visitor.EnnMonitorJsonVisitorTimeTable;
import enn.monitor.security.gateway.metrics.EnnMonitorGatewayParameter;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;

public class MainJFrame extends JFrame {
	
	private static final long serialVersionUID = -8008221899785868824L;

	public static void main(String[] args) throws Exception {
		EnnMonitorMetricsGatewayGrpc ennMonitorMetricsGatewayGrpc = new EnnMonitorMetricsGatewayGrpc();
		
		EnnMonitorJsonVisitorTimeTable visitorTable = new EnnMonitorJsonVisitorTimeTable();
		
		visitorTable.setBaseTime(System.currentTimeMillis());
		ennMonitorMetricsGatewayGrpc.startMetricsCollector(
        		true, new EnnMonitorGatewayParameter("10.19.248.200", 30111), 1, "monitor-app");
		
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
