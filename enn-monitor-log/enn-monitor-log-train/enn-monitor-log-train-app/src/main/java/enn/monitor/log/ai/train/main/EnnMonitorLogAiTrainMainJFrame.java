package enn.monitor.log.ai.train.main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.beust.jcommander.JCommander;

import enn.monitor.log.ai.parameters.Parameters;
import enn.monitor.log.train.util.EnnMonitorConfigServiceClientUtil;
import enn.monitor.log.train.util.EnnMonitorLogAnalyseStorageClientUtil;
import enn.monitor.log.train.util.EnnMonitorLogConfigCacheClientUtil;
import enn.monitor.log.train.util.EnnMonitorLogConfigTemplateClientUtil;
import enn.monitor.log.train.util.EnnMonitorLogTrainMasterClientUtil;

public class EnnMonitorLogAiTrainMainJFrame extends JFrame {
	
	private static final long serialVersionUID = -8008221899785868824L;

	public static void main(String[] args) throws Exception {
		Parameters parameters = new Parameters();
	    JCommander jc = new JCommander(parameters, args);
	    if (parameters.help) {
	    	jc.usage();
	    	return;
	    }
	    
	    EnnMonitorConfigServiceClientUtil.init(parameters);
	    EnnMonitorLogConfigTemplateClientUtil.init(parameters);
	    EnnMonitorLogAnalyseStorageClientUtil.init(parameters);
	    EnnMonitorLogConfigCacheClientUtil.init(parameters);
	    
	    EnnMonitorLogTrainMasterClientUtil.init(parameters);
	    
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnnMonitorLogAiTrainMainJFrame frame = new EnnMonitorLogAiTrainMainJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public EnnMonitorLogAiTrainMainJFrame() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1800, 1200);
		
		setContentPane(new EnnMonitorLogAiTrainMainJPanel());
	}
	
}
