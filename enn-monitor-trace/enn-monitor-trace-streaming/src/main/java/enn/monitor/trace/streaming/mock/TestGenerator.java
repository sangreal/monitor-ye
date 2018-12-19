package enn.monitor.trace.streaming.mock;

import com.beust.jcommander.JCommander;
import enn.monitor.trace.streaming.Parameters;
import enn.monitor.trace.proto.model.common.Aggregates;
import enn.monitor.trace.proto.model.dependency.EnnDependencyLink;
import enn.monitor.trace.proto.model.dependency.EnnEndpoint;
import enn.monitor.trace.streaming.elasticsearch.ESSender;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weize on 18-1-5.
 */
public class TestGenerator {
    public static void main(String[] args) throws Exception {
        Parameters parameters = new Parameters();
        JCommander jc = new JCommander(parameters, args);
        if (parameters.help) {
            jc.usage();
            return;
        }

        TestGenerator generator = new TestGenerator();
        ESSender sender = new ESSender(parameters.esTcp, "elasticsearch",
                "trace-aggregate",
                100);
        sender.send(generator.dataSet());
    }

    private List<EnnDependencyLink> dataSet() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        String content = IOUtils.toString(classLoader.getResourceAsStream("test_data.txt"));
        String[] lines = content.split("\n");
        List<EnnDependencyLink> res = new ArrayList<>();
        for (int i = 1; i < lines.length; i ++) {
            String line = lines[i];
            String[] splits = line.split(",");
            if (splits.length < 12) continue;
            EnnEndpoint caller = new EnnEndpoint(splits[0], splits[1], splits[2], splits[3]);
            EnnEndpoint callee = new EnnEndpoint(splits[4], splits[5], splits[6], splits[7]);
            long count = Long.parseLong(splits[8]);
            long error = Long.parseLong(splits[9]);
            long avg_latency = Long.parseLong(splits[10]);
            long minute = Long.parseLong(splits[11]);
            Aggregates aggregates = new Aggregates(count, error, avg_latency);

            EnnDependencyLink link = EnnDependencyLink.newBuilder()
                    .aggregates(aggregates)
                    .caller(caller)
                    .callee(callee)
                    .minute(minute)
                    .build();
            res.add(link);
        }
        return res;
    }
}
