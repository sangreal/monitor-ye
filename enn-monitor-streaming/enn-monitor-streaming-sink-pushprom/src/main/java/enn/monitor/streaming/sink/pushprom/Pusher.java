package enn.monitor.streaming.sink.pushprom;

import enn.monitor.framework.log.proto.ESLog;
import io.prometheus.client.Counter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by weize on 2017/3/31.
 */
public class Pusher implements Serializable {
    private static PromPusher promPusher;
    private String kafkaTopic;

    public Pusher(String prometheusPushUrl, String kafkaTopic) {
        promPusher = new PromPusher(prometheusPushUrl);
        this.kafkaTopic = kafkaTopic;
    }

    public void count(ESLog errorLog) {
        if (!"log-k8s-system".equals(kafkaTopic) && !"log-k8s-pod".equals(kafkaTopic)) {
            return;
        }
        Counter c = (Counter) Counter.build(errorLog.getLogLevel(), "count error logs to trigger alerts").create();
        c.inc();
        Map<String, String> groupKey = new HashMap<>();
        groupKey.put("node", errorLog.getNodeName() == null ? "" : errorLog.getNodeName());
        groupKey.put("app", errorLog.getAppName() == null ? "" : errorLog.getAppName());
        groupKey.put("namespace", errorLog.getNameSpace() == null ? "" : errorLog.getNameSpace());
        groupKey.put("pod", errorLog.getPodName() == null ? "" : errorLog.getPodName());
        groupKey.put("position", errorLog.getPosition() == null ? "" : errorLog.getPosition());
        try {
            promPusher.push(c, kafkaTopic, groupKey);
        } catch (Exception e) {
//            pushFailMetrics.markEvent();
        }
    }
}
