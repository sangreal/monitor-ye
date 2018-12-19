package enn.monitor.trace.dataapi;

import com.beust.jcommander.JCommander;
import enn.monitor.security.gateway.metrics.grpc.EnnMonitorMetricsGatewayGrpc;
import enn.monitor.trace.dataapi.api.*;
import enn.monitor.trace.dataapi.util.EnnMonitorConfigUtil;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by weize on 18-1-5.
 */
//@SpringBootApplication
public class DataApiServer {

    public static void main(String[] args) throws Exception {
        EnnMonitorMetricsGatewayGrpc metricsGrpc = null;
        Parameters parameters = new Parameters();
        JCommander jc = new JCommander(parameters, args);
        if (parameters.help) {
            jc.usage();
            return;
        }
        metricsGrpc = new EnnMonitorMetricsGatewayGrpc();
        metricsGrpc.startMetricsCollector();

        // load global configs;
        Config.esSqlUrl =  "http://" + parameters.elasticSearchHost + ":" + parameters.elasticSearchPort + "/_sql?sql=";
        Config.esHttpUrl = "http://" + parameters.elasticSearchHost + ":" + parameters.elasticSearchPort + "/";

        EnnMonitorConfigUtil.setParameters(parameters);
        Server server = new Server(parameters.listenPort);
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/api/v1");   //这里是请求的上下文，比如http://localhost:8090/MyServer
        server.setHandler(context);
        context.addServlet(new ServletHolder(new ServiceListApi()), "/services");
        context.addServlet(new ServletHolder(new ServiceAggregatesApi()), "/services/aggregates");
        context.addServlet(new ServletHolder(new ServiceResourceAggregatesApi()), "/services/resources/aggregates");
        context.addServlet(new ServletHolder(new ServiceResourceInstanceAggregatesApi()), "/services/resources/instances/aggregates");
        context.addServlet(new ServletHolder(new ServiceResourceHistogramApi()), "/services/resources/histograms");
        context.addServlet(new ServletHolder(new ServiceResourcePercentilesApi()), "/services/resources/percentiles");
        context.addServlet(new ServletHolder(new ServiceResourceInstancePercentilesApi()), "/services/resources/instances/percentiles");
        context.addServlet(new ServletHolder(new TraceSpanListApi()),"/tracespans");
        context.addServlet(new ServletHolder(new TraceListApi()),"/trace/list");
        context.addServlet(new ServletHolder(new TraceLogApi()), "/tracelog");
        context.addServlet(new ServletHolder(new LogApi()), "/log");
        context.addServlet(new ServletHolder(new ServiceTopologyApi()), "/services/topology");
        context.addServlet(new ServletHolder(new ServiceDetailApi()), "/services/detail");
        context.addServlet(new ServletHolder(new ResourceSummaryApi()), "/resources");
        server.start();
        server.join();
    }
}

