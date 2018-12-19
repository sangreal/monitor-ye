package enn.monitor.security.gateway.grpc.client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.google.common.io.Resources;

import enn.monitor.security.gateway.trace.TracerBuilder;

/**
 * Created by weize on 18-2-26.
 */
public class GrpcClientBuilder {
    public static GrpcClientConfig config;

    public static EnnMonitorSecurityGatewayGrpcClient get() {
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
                props.load((BufferedInputStream) Resources.getResource("monitor.properties").getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        config = new GrpcClientConfig(
                props.getProperty("trace.switch"),
                props.getProperty("gateway.grpcHost"),
                props.getProperty("gateway.grpcPort"),
                props.getProperty("gateway.token")
        );

        EnnMonitorSecurityGatewayGrpcClient client = new EnnMonitorSecurityGatewayGrpcClient(
                props.getProperty("gateway.grpcHost"),
                Integer.parseInt(props.getProperty("gateway.grpcPort")),
                props.getProperty("trace.switch").equals("off") ? null : TracerBuilder.get()
        );
        return client;
    }
}
