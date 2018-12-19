package enn.monitor.trace.dataapi.util;

import enn.monitor.trace.dataapi.Parameters;

public class EnnMonitorConfigUtil {
    private static String elasticSearchHost = "10.19.140.200";
    private static int elasticSearchPort = 29400;

    public static void setParameters(Parameters parameters) {

        elasticSearchHost = parameters.elasticSearchHost;
        elasticSearchPort = parameters.elasticSearchPort;
    }

    public static String getElasticSearchHost() {
        return elasticSearchHost;
    }

    public static int getElasticSearchPort() {
        return elasticSearchPort;
    }
}
