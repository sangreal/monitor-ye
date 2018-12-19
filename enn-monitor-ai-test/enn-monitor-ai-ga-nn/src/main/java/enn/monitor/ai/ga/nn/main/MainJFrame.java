package enn.monitor.ai.ga.nn.main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import enn.monitor.ai.ga.nn.panel.NNJPanel;

public class MainJFrame extends JFrame {
	
	private static final long serialVersionUID = -8008221899785868824L;

	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJFrame frame = new MainJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1800, 1200);
		
		setContentPane(new NNJPanel());
	}
}
