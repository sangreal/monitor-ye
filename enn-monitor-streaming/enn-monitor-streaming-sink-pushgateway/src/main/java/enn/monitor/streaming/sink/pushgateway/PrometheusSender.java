package enn.monitor.streaming.sink.pushgateway;

import enn.monitor.streaming.common.manager.ThreadManager;
import enn.monitor.streaming.common.proto.Metric;
import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.PushGateway;
import io.prometheus.client.exporter.common.TextFormat;
import org.apache.commons.codec.CharEncoding;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by weize on 17-8-29.
 */
public class PrometheusSender implements Serializable {
    private String hostPort;
    private static PushGateway pushGateway;

    public PrometheusSender(String prometheusPushUrl) {
        hostPort = prometheusPushUrl;
        if(pushGateway == null){
            synchronized (PrometheusSender.class){
                if(pushGateway == null){
                    pushGateway = new PushGateway(prometheusPushUrl);
                }
            }
        }
    }

    /**
     * metrics 格式：[
     *      {
     *          "metric":"cpu_total",
     *          "value":1241323,
     *          "tags": {"host":"10.19.140.200", "cluster_name":"yancheng",
     *                  "container_name": "mysql-123-fdsa", "namespace": "cc-dev",
     *                  "podname": "mysql-123", "appname": "mysql"},
     *          "timestamp": 1231321345
     *  ]
     *  如果container_name为"/"，则表示是主机信息；
     *  否则为容器信息；
     * @param metrics
     */
    public void send(List<Metric> metrics) {
        ThreadManager.getInstance().runTaskInPool(() -> {
            for (Metric metric : metrics) {
                if (metric.getInstance() == null) {
                    continue;
                }
                push(metric);
            }
        });
    }

    /**
     * TODO: Multi-thread security
     * @param metric
     */
    public void push(Metric metric) {
        Gauge g = (Gauge) Gauge.build().name(metric.getMetric()).help(metric.getMetric()).create();
        g.set(metric.getValue());
        try {
            Map<String, String> rawLabels = metric.getTags();
            Map<String, String> groupKey = new HashMap<>();
            rawLabels.forEach((key, value) -> {
                try {
                    groupKey.put(key, URLEncoder.encode(value, CharEncoding.UTF_8));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            });
            groupKey.put("instance", URLEncoder.encode(metric.getInstance(), CharEncoding.UTF_8));

            CollectorRegistry registry = new CollectorRegistry();
            g.register(registry);
            try {
                rawPush(registry, metric.getType(), groupKey, "POST", metric.getTimestamp());
//                pushMetrics.markEvent();
            } catch (Exception e) {
//                pushFailMetrics.markEvent();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private void rawPush(CollectorRegistry registry,
                         String job, Map<String, String> groupingKey, String method,
                         long timestamp) throws IOException {
        String url = "http://" + hostPort + "/metrics/job/" + URLEncoder.encode(job, "UTF-8");
        if (groupingKey != null) {
            for (Map.Entry<String, String> entry: groupingKey.entrySet()) {
                url += "/" + entry.getKey() + "/" + URLEncoder.encode(entry.getValue(), "UTF-8");
            }
        }
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("Content-Type", TextFormat.CONTENT_TYPE_004);
        if (!method.equals("DELETE")) {
            connection.setDoOutput(true);
        }
        connection.setRequestMethod(method);

        connection.setConnectTimeout(3 * 1000);
        connection.setReadTimeout(10 * 1000);
        connection.connect();

        try {
            if (!method.equals("DELETE")) {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                write004(writer, registry.metricFamilySamples(), timestamp);
                writer.flush();
                writer.close();
            }

            int response = connection.getResponseCode();
            if (response != HttpURLConnection.HTTP_ACCEPTED) {
                throw new IOException("Response code from " + url + " was " + response);
            }
        } finally {
            connection.disconnect();
        }
    }

    public static void write004(Writer writer, Enumeration<Collector.MetricFamilySamples> mfs, long timestamp) throws IOException {
    /* See http://prometheus.io/docs/instrumenting/exposition_formats/
     * for the output format specification. */
        for (Collector.MetricFamilySamples metricFamilySamples: Collections.list(mfs)) {
            writer.write("# HELP ");
            writer.write(metricFamilySamples.name);
            writer.write(' ');
            writeEscapedHelp(writer, metricFamilySamples.help);
            writer.write('\n');

            writer.write("# TYPE ");
            writer.write(metricFamilySamples.name);
            writer.write(' ');
            writer.write(typeString(metricFamilySamples.type));
            writer.write('\n');

            for (Collector.MetricFamilySamples.Sample sample: metricFamilySamples.samples) {
                writer.write(sample.name);
                if (sample.labelNames.size() > 0) {
                    writer.write('{');
                    for (int i = 0; i < sample.labelNames.size(); ++i) {
                        writer.write(sample.labelNames.get(i));
                        writer.write("=\"");
                        writeEscapedLabelValue(writer, sample.labelValues.get(i));
                        writer.write("\",");
                    }
                    writer.write('}');
                }
                writer.write(' ');
                writer.write(Collector.doubleToGoString(sample.value));
                writer.write(' ');
                writer.write(String.valueOf(timestamp));
                writer.write('\n');
            }
        }
    }

    private static void writeEscapedHelp(Writer writer, String s) throws IOException {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\\':
                    writer.append("\\\\");
                    break;
                case '\n':
                    writer.append("\\n");
                    break;
                default:
                    writer.append(c);
            }
        }
    }

    private static void writeEscapedLabelValue(Writer writer, String s) throws IOException {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\\':
                    writer.append("\\\\");
                    break;
                case '\"':
                    writer.append("\\\"");
                    break;
                case '\n':
                    writer.append("\\n");
                    break;
                default:
                    writer.append(c);
            }
        }
    }

    private static String typeString(Collector.Type t) {
        switch (t) {
            case GAUGE:
                return "gauge";
            case COUNTER:
                return "counter";
            case SUMMARY:
                return "summary";
            case HISTOGRAM:
                return "histogram";
            default:
                return "untyped";
        }
    }

}
