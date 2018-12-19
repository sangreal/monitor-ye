package enn.monitor.framework.common.env;

import java.net.InetAddress;

public class EnnMonitorEnvAppUtil {
//	private final static String APP_NAME = "APPNAME";
	private final static String APP_NAMESPACE = "NAMESPACE";
	private final static String APP_PODNAME = "PODNAME";
	
//	private static String appName = null;
	private static String appNamespace = null;
	private static String appPodName = null;
	
	static {
//		appName = System.getenv(APP_NAME);
		appNamespace = System.getenv(APP_NAMESPACE);
		appPodName = System.getenv(APP_PODNAME);
	}
	
	/*public static String getAppName() throws Exception {
		String appname = "default";
		
		Properties prop = new Properties();     
		//读取属性文件a.properties
		InputStream in = EnnMonitorAppUtil.class.getResourceAsStream("/properties/appname.properties");
		if (in == null) {
			throw new Exception("appname file is not exists");
		}
		
		try {
			prop.load(in);     ///加载属性列表
			appname = prop.getProperty(APPNAME, "default");
		} finally {
			in.close();
		}
		
		return appname;
	}*/
	
//	public static String getAppName() {
//		return appName;
//	}
	
	public static String getNameSpace() {
		return appNamespace;
	}
	
	public static String getPodName() {
		return appPodName;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println((InetAddress.getLocalHost()).getHostName());
	}

}
