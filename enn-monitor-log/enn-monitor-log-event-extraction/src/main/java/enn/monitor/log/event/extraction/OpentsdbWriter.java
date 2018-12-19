package enn.monitor.log.event.extraction;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.JCommander;
import com.google.common.io.Resources;

import enn.monitor.streaming.common.proto.Metric;
import enn.monitor.streaming.sink.opentsdb.OpenTSDBSender;

/**
 * Created by weize on 18-7-6.
 */
public class OpentsdbWriter {
    public static void main(String[] args) throws IOException {
        Parameters parameters = new Parameters();
        JCommander jc = new JCommander(parameters, args);
        if (parameters.help) {
            jc.usage();
            return;
        }
        InputStream bis = (InputStream) Resources.getResource("ceph-events-07-04.csv").getContent();
        List<String> lines = IOUtils.readLines(bis);
        String[] headers = lines.get(0).split(",");
        List<Metric> metrics = new ArrayList<>();
        for (int i = 1; i < lines.size(); i ++) {
            String[] splits = lines.get(i).split(",");
            long timestamp = Long.parseLong(splits[0]) * 1000;
            for (int hi = 1; hi < splits.length; hi ++) {
                int value = 0;
                if (StringUtils.isNotEmpty(splits[hi])) {
                    value = Integer.parseInt(splits[hi]);
                }
                Metric metric = new Metric();
                metric.setMetric("Event");
                metric.setValue(value);
                Map<String, String> tags = new HashMap<>();
                tags.put("event_type", headers[hi]);
                tags.put("namespace", "ceph");
                metric.setTags(tags);
                metric.setTimestamp(timestamp);
                metrics.add(metric);
            }
        }
        OpenTSDBSender sender = OpenTSDBSender.getInstance(parameters.opentsdbUrl);
        sender.send(metrics);
    }
}
