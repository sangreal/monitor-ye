package enn.monitor.security.gateway.trace.aop;

import com.google.common.io.Resources;
import com.google.inject.matcher.Matchers;
import enn.monitor.framework.common.env.EnnMonitorEnvAppUtil;
import enn.monitor.framework.tracing.exceptionhandler.TraceGuiceModule;
import enn.monitor.framework.tracing.model.TracerConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * Created by weize on 18-5-25.
 */
public class GatewayTraceGuiceModule extends TraceGuiceModule {
    @Override
    protected void configure() {
        super.configure();
        bindConfig();
        SwitchChecker switchChecker = new SwitchChecker();
        requestInjection(switchChecker);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(CheckSwitch.class), switchChecker);
    }

    private void bindConfig() {
        Properties defaults = new Properties();
        defaults.setProperty("trace.switch", "off");

        Properties props = new Properties(defaults);
        try {
            File file = new File("./monitor.properties");
            if (file.exists()) {
                FileInputStream fis;
                fis = new FileInputStream(file);
                props.load(fis);
                fis.close();
            } else {
                props.load((InputStream) Resources.getResource("monitor.properties").getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TracerConfig config = new TracerConfig(
                props.getProperty("trace.switch"),
                props.getProperty("gateway.grpcHost") + ":" + props.getProperty("gateway.grpcPort"),
                props.getProperty("trace.bizLine"),
                props.getProperty("trace.service"),
                props.getProperty("gateway.token")

        );
        String instance = null;

        try {
            String namespace = EnnMonitorEnvAppUtil.getNameSpace();
            String podname = EnnMonitorEnvAppUtil.getPodName();
            if (namespace != null && podname != null) {
                instance = namespace + "-" + podname;
            }
        } catch (Exception e) {
        }
        if (instance == null) {
            try {
                instance = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        if (instance == null) {
            instance = "unknown";
        }
        config.setInstance(instance);
        bind(TracerConfig.class).toInstance(config);
    }
}
