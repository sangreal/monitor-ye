package enn.monitor.log.ai.data.main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.beust.jcommander.JCommander;

import enn.monitor.log.ai.parameters.Parameters;
import enn.monitor.log.train.util.EnnMonitorConfigServiceClientUtil;
import enn.monitor.log.train.util.EnnMonitorLogAnalyseTemplateClientUtil;
import enn.monitor.log.train.util.EnnMonitorLogConfigAnalyseTermClientUtil;
import enn.monitor.log.train.util.EnnMonitorLogConfigEventClientUtil;
import enn.monitor.log.train.util.EnnMonitorLogConfigTemplateClientUtil;

public class EnnMonitorLogAiDataMainJFrame extends JFrame {

	private static final long serialVersionUID = 7129229964905296727L;
	
	public static void main(String[] args) throws Exception {
		Parameters parameters = new Parameters();
	    JCommander jc = new JCommander(parameters, args);
	    if (parameters.help) {
	    	jc.usage();
	    	return;
	    }
	    
	    EnnMonitorConfigServiceClientUtil.init(parameters);
	    EnnMonitorLogConfigEventClientUtil.init(parameters);
	    EnnMonitorLogConfigAnalyseTermClientUtil.init(parameters);
	    EnnMonitorLogAnalyseTemplateClientUtil.init(parameters);
	    EnnMonitorLogConfigTemplateClientUtil.init(parameters);
	    
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnnMonitorLogAiDataMainJFrame frame = new EnnMonitorLogAiDataMainJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public EnnMonitorLogAiDataMainJFrame() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1800, 1200);
		
		setContentPane(new EnnMonitorLogAiDataMainJPanel());
	}

}
